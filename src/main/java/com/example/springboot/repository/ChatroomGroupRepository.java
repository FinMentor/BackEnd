package com.example.springboot.repository;

import com.example.springboot.dto.ChatroomDetailDTO;
import com.example.springboot.vo.ChatroomGroupVO;
import com.example.springboot.vo.id.ChatroomGroupId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatroomGroupRepository extends JpaRepository<ChatroomGroupVO, ChatroomGroupId> {
//    @Query("SELECT cg FROM ChatroomGroupVO cg JOIN FETCH cg.chatroomEntity c WHERE cg.memberId = :memberId")
//    List<ChatroomGroupVO> getListChatroomByMemberId(Long memberId);
    void deleteByChatroomId(Long chatroomId);

    @Query(value = """

            SELECT
            cg1.MEMBER_ID AS senderId,
            m1.NICKNAME AS senderNickName,
            cg2.MEMBER_ID AS receiverId,
            m2.NICKNAME AS receiverNickName,
            cg1.CHATROOM_ID,
            m2.PROFILE_IMAGE_URL AS receiverProfileImage,
            ms.CONTENT AS recentMessage,
            ms.CREATED_AT
        FROM
            CHATROOM_GROUP cg1
            JOIN CHATROOM_GROUP cg2 ON cg1.CHATROOM_ID = cg2.CHATROOM_ID
            JOIN MEMBER m1 ON m1.MEMBER_ID = cg1.MEMBER_ID
            JOIN MEMBER m2 ON m2.MEMBER_ID = cg2.MEMBER_ID
            LEFT JOIN (
                SELECT
                    ms1.CHATROOM_ID,
                    ms1.CONTENT,
                    ms1.CREATED_AT
                FROM
                    MESSAGE ms1
                INNER JOIN (
                    SELECT
                        CHATROOM_ID,
                        MAX(CREATED_AT) AS MaxCreatedAt
                    FROM
                        MESSAGE
                    GROUP BY
                        CHATROOM_ID
                ) ms2 ON ms1.CHATROOM_ID = ms2.CHATROOM_ID AND ms1.CREATED_AT = ms2.MaxCreatedAt
            ) ms ON ms.CHATROOM_ID = cg1.CHATROOM_ID
        WHERE
            cg1.MEMBER_ID <> cg2.MEMBER_ID
            AND cg1.MEMBER_ID = :memberId
        ORDER BY ms.CREATED_AT DESC
        """, nativeQuery = true)
    List<Object[]> getListChatroomByMemberId(Long memberId);
}
