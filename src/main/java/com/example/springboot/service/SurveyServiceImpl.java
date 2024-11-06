package com.example.springboot.service;

import com.example.springboot.dto.MemberAnswerDTO;
import com.example.springboot.dto.QuestionDTO;
import com.example.springboot.dto.QuestionOptionDTO;
import com.example.springboot.entity.QuestionEntity;
import com.example.springboot.entity.QuestionOptionEntity;
import com.example.springboot.exception.MemberAnswerProcessingException;
import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.repository.MemberAnswerRepository;
import com.example.springboot.repository.MemberRepository;
import com.example.springboot.repository.QuestionRepository;
import com.example.springboot.vo.MemberAnswerVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SurveyServiceImpl implements SurveyService {

    private final QuestionRepository questionRepository;
    private final MemberAnswerRepository memberAnswerRepository;
    private final MemberRepository memberRepository;

    @Override
    public QuestionDTO getSurveyQuestion(long questionId) {
        // 설문 질문 가져오기
        QuestionEntity questionEntity = questionRepository.selectSurveyQuestion(questionId);

        if (questionEntity == null) {
            log.error("Question not found for questionId: {}", questionId);
            throw new ResourceNotFoundException("Question not found for questionId: " + questionId);
        }

        // Question -> QuestionDTO로 변환 (필드 직접 설정)
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setQuestionId(questionEntity.getQuestionId());
        questionDTO.setContent(questionEntity.getContent());

        // 질문 옵션 가져오기 및 DTO로 변환
        List<QuestionOptionEntity> questionOptionEntityList = questionRepository.selectOptionsByQuestionId(questionId);
        List<QuestionOptionDTO> questionOptionDTOList = new ArrayList<>(questionOptionEntityList.size()); // 옵션 리스트 크기 미리 설정

        for (QuestionOptionEntity questionOptionEntity : questionOptionEntityList) {
            QuestionOptionDTO questionOptionDTO = new QuestionOptionDTO();
            questionOptionDTO.setQuestionOptionId(questionOptionEntity.getQuestionOptionId());
            questionOptionDTO.setContent(questionOptionEntity.getContent());
            questionOptionDTO.setScore(questionOptionEntity.getScore());
            questionOptionDTOList.add(questionOptionDTO);
        }

        questionDTO.setOptions(questionOptionDTOList); // 변환된 옵션 리스트 설정
        log.info("Survey question retrieved: {}", questionDTO);

        return questionDTO;
    }

    @Override
    public void insertOrUpdateMemberAnswer(String memberId, long questionId, MemberAnswerDTO memberAnswerDTO) {
        try {
            // 기존 답변 확인
            MemberAnswerVO existingAnswer = memberAnswerRepository.selectMemberAnswer(memberId, questionId);

            // MemberAnswerDTO -> MemberAnswerVO 변환
            MemberAnswerVO memberAnswerVO = new MemberAnswerVO();
            memberAnswerVO.setMemberId(memberId);
            memberAnswerVO.setQuestionId(questionId);
            memberAnswerVO.setQuestionOptionId(memberAnswerDTO.getOptionId());

            if (existingAnswer == null) {
                // 새 답변 삽입
                int insertResult = memberAnswerRepository.insertMemberAnswer(memberAnswerVO);
                if (insertResult <= 0) {
                    log.error("Failed to insert answer for memberId: {}, questionId: {}", memberId, questionId);
                    throw new MemberAnswerProcessingException("Failed to insert answer for memberId: " + memberId);
                }
                log.info("New answer inserted for memberId: {}, questionId: {}, optionId: {}", memberId, questionId, memberAnswerDTO.getOptionId());
            } else {
                // 기존 답변 업데이트
                int updateResult = memberAnswerRepository.updateMemberAnswer(memberAnswerVO);
                if (updateResult <= 0) {
                    log.error("Failed to update answer for memberId: {}, questionId: {}", memberId, questionId);
                    throw new MemberAnswerProcessingException("Failed to update answer for memberId: " + memberId);
                }
                log.info("Answer updated for memberId: {}, questionId: {}, optionId: {}", memberId, questionId, memberAnswerDTO.getOptionId());
            }

            // 모든 질문에 대한 답변이 완료되었는지 확인
            int totalQuestions = questionRepository.getTotalQuestionsCount(); // 전체 질문 수
            int answeredQuestions = memberAnswerRepository.getTotalAnsweredQuestions(memberId); // 답변한 질문 수

            if (answeredQuestions == totalQuestions) {
                // 모든 질문이 완료되었으면 설문 상태를 'Y'로 업데이트
                updateSurveyStatus(memberId, 'Y');
            }


        } catch (Exception e) {
            log.error("Error while inserting/updating member answer for memberId: {}, questionId: {}", memberId, questionId, e);
            throw new MemberAnswerProcessingException("Error while inserting/updating member answer");
        }
    }

    @Override
    public void updateSurveyStatus(String memberId, char surveyStatus) {
        // 설문 상태를 업데이트
        memberRepository.updateSurveyStatus(memberId, surveyStatus);
        log.info("Survey status updated for memberId: {}, new status: {}", memberId, surveyStatus);
    }

    @Override
    public String getSurveyStatus(String memberId) {
        // 사용자 설문 상태 조회
        return memberRepository.getSurveyStatus(memberId);
    }

    @Override
    public boolean areAllQuestionsAnswered(String memberId) {
        // 전체 질문 수와 답변한 질문 수를 비교
        int totalQuestions = questionRepository.getTotalQuestionsCount();
        int answeredQuestions = memberAnswerRepository.getTotalAnsweredQuestions(memberId);

        return answeredQuestions == totalQuestions;
    }


}
