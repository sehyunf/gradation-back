package com.app.gradationback.mapper;

import com.app.gradationback.domain.ArtPostDTO;
import com.app.gradationback.domain.ArtPostVO;
import com.app.gradationback.domain.ArtVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface ArtPostMapper {

//    작품 게시글 등록
    public void insert(ArtPostVO artPostVO);

//    작품 게시글 전체 조회
    public List<ArtPostDTO> selectAll();

//    작품 게시글 단일 조회
    public Optional<ArtPostDTO> select(Long id);

//    작품 게시글 전체 조회 (userId로)
    public List<ArtPostDTO> selectAllByUserId(Long userId);

//    등록순으로 상위 50개 작품 조회
    public List<ArtPostDTO> selectAllForMain(Map<String, Object> params);

//    카테고리 + 드롭다운 + 페이지네이션
    public List<ArtPostDTO> selectArtListByCategoryAndDropdown(Map<String, Object> params);

    public Integer selectCountArtList(Map<String, Object> params);

//    내 작품 리스트
    public List<ArtPostDTO> selectAllMyArt(Long userId);

//    내 작품 좋아요
    public List<ArtPostDTO> selectAllLikedArt(Long userId);

//    경매 가능 작품 조회 (좋아요 50개 이상)
    public List<ArtPostDTO> selectAllForAuction(Long userId);

//    작품 게시글 수정
    public void update(ArtPostVO artPostVO);

//    작품 게시글 삭제
    public void deleteById(Long id);

//    작품 게시 전체 삭제 (회원 탈퇴)
    public void deleteAllByUserId(Long userId);

    public void deleteAllByArtId(Long artId);
}
