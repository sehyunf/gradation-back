package com.app.gradationback.service;

import com.app.gradationback.domain.ArtPostDTO;
import com.app.gradationback.domain.CommentDTO;
import com.app.gradationback.domain.CommentVO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CommentService {

//    댓글 작성
    public void write(CommentVO commentVO);

//    댓글 전체 조회
    public List<CommentVO> getCommentList();

//    댓글 단일 조회
    public Optional<CommentVO> getComment(Long id);

//    댓글 전체 조회 (userId로)
    public List<ArtPostDTO> getCommentListByUserId(Long userId);

//    댓글 전체 조회 (postId로)
    public List<CommentDTO> getAllCommentByPostId(Map<String, Object> params);

    public Integer getCountComment(Map<String, Object> params);

//    댓글 삭제
    public void removeComment(Long commentId);

//    댓글 수정
    public void modifyComment(CommentVO commentVO);

//    댓글 전체 삭제 (회원 탈퇴)
    public void removeCommentByUserId(Long userId);

//    댓글 전체 삭제 (게시글 삭제)
    public void removeCommentByPostId(Long postId);

}
