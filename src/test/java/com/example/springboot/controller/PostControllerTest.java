package com.example.springboot.controller;

import com.example.springboot.dto.PostRequestDTO;
import com.example.springboot.dto.PostResponseDTO;
import com.example.springboot.entity.domain.MemberEntity;
import com.example.springboot.repository.MemberRepository;
import com.example.springboot.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MemberRepository memberRepository;

    @MockBean
    private PostService postService;

    @BeforeEach
    public void setUp() {
        // 가짜 멤버 생성 및 findById 설정
        MemberEntity testMember = MemberEntity.builder()
                .id("testUser1")
                .name("Test User")
                .build();
        Mockito.when(memberRepository.findById("testUser1")).thenReturn(Optional.of(testMember));

        // 가짜 Post 데이터 설정
        PostResponseDTO postResponseDTO = PostResponseDTO.builder()
                .postId(1L)
                .memberId(1L)
                .mainCategoryId(1L)
                .title("주식 수익 인증")
                .content("이번 달 주식 수익 인증합니다..")
                .viewCount(0L)
                .likeCount(0L)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        Mockito.when(postService.findById(1L)).thenReturn(postResponseDTO);
        Mockito.when(postService.findAll()).thenReturn(Collections.singletonList(postResponseDTO));
    }

    @Test
    public void savePost() throws Exception {
        // Given
        String uri = "/api/v1/posts/save";

        PostRequestDTO postRequestDTO = PostRequestDTO.builder()
                .memberId("testUser1")
                .mainCategoryId(1L)
                .title("주식 수익 인증")
                .content("이번 달 주식 수익 인증합니다..")
                .build();

        String content = objectMapper.writeValueAsString(postRequestDTO);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void findPostById() throws Exception {
        // Given
        String uri = "/api/v1/posts";  // postId는 쿼리 파라미터로 전달
        Long postId = 1L;

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get(uri)
                        .param("postId", String.valueOf(postId))  // 쿼리 파라미터로 전달
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseEntity.body[0].postId").value(postId));
    }

    @Test
    public void findAllPosts() throws Exception {
        // Given
        String uri = "/api/v1/posts";

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON);

        // When
        ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);

        // Then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseEntity.body[0].postId").value(1L));
    }

}
