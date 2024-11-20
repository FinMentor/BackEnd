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
        SELECT cg1.MEMBER_ID AS senderId, 
               cg2.MEMBER_ID AS receiverId,
               cg1.CHATROOM_ID,
               m2.PROFILE_IMAGE_URL AS receiverProfileImage,
               ms.CONTENT AS recentMessage,
               ms.CREATED_AT 
        FROM CHATROOM_GROUP cg1
        JOIN CHATROOM_GROUP cg2 ON cg1.CHATROOM_ID = cg2.CHATROOM_ID
        JOIN MEMBER m1 ON m1.MEMBER_ID = cg1.MEMBER_ID
        JOIN MEMBER m2 ON m2.MEMBER_ID = cg2.MEMBER_ID
        JOIN (
            SELECT CHATROOM_ID, CONTENT, CREATED_AT
            FROM MESSAGE ms1
            WHERE ms1.CREATED_AT = (
                SELECT MAX(ms2.CREATED_AT)
                FROM MESSAGE ms2
                WHERE ms1.CHATROOM_ID = ms2.CHATROOM_ID
            )
        ) ms 
            ON ms.CHATROOM_ID = cg1.CHATROOM_ID
        WHERE cg1.MEMBER_ID <> cg2.MEMBER_ID
        AND cg1.MEMBER_ID = :memberId
        """, nativeQuery = true)
    List<Object[]> getListChatroomByMemberId(Long memberId);
}
