package com.app.gradationback.service;

import com.app.gradationback.domain.*;
import com.app.gradationback.mapper.UserMapper;
import com.app.gradationback.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final CommentDAO commentDAO;
    private final ArtDAO artDAO;
    private final ArtImgDAO artImgDAO;
    private final ArtPostDAO artPostDAO;
    private final UserMapper userMapper;

    //    일반 회원가입
    @Override
    public void joinNormal(UserVO userVO) {
        userDAO.saveNormal(userVO);
    }

    //    소셜 로그인 후 회원가입
    @Override
    public void joinSocial(UserVO userVO) {
        userDAO.saveSocial(userVO);
    }

    //    전체 회원 조회
    @Override
    public List<UserVO> getUserList() {
        return userDAO.findAllUser();
    }

    //    단일 회원 조회 (이메일로)
    @Override
    public Optional<UserVO> getUserByEmail(String userEmail) {
        return userDAO.findUserByEmail(userEmail);
    }

    //    단일 회원 조회 (아이디로)
    @Override
    public Optional<UserVO> getUserByIdentification(String userIdentification) {
        return userDAO.findUserByIdentification(userIdentification);
    }

    //    로그인
    @Override
    public String login(UserVO userVO) {
        return userDAO.login(userVO);
    }

    //    아이디 찾기 (이름 + 이메일)
    @Override
    public UserVO getIdentificationByEmailAndName(UserVO userVO) {
        return userDAO.findIdentificationByEmailAndName(userVO);
    }

    //    비밀번호 찾기 (이메일)
    @Override
    public UserVO getPasswordByEmail(UserVO userVO) {
        return userDAO.findPasswordByEmail(userVO);
    }

    //    아이디로 이메일 조회
    @Override
    public String getEmailById(Long id) {
        return userDAO.findEmailById(id);
    }

    //    이메일로 ID 조회
    @Override
    public Long getIdByEmail(String userEmail) {
        return userDAO.findIdByEmail(userEmail);
    }

    //    비밀번호 수정
    @Override
    public void modifyPassword(UserVO userVO) {
        UserVO foundUser = userDAO.findUserByIdentification(userVO.getUserIdentification())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        if (foundUser.getUserPassword().equals(userVO.getUserPassword())) {
            throw new IllegalArgumentException("기존 비밀번호와 일치하는 비밀번호는 사용할 수 없습니다.");
        }

        userDAO.updatePassword(userVO);
    }

    //    대학교 인증
    @Override
    public void modifyUniversityStatus(UserVO userVO) {
        userDAO.updateUniversityStatus(userVO);
    }

    //    회원 정보 수정
    @Override
    public void modifyUser(UserVO userVO) {
        userDAO.updateUser(userVO);
    }

    //    회원 프로필 이미지 수정
    @Override
    public void modifyProfileImg(UserVO userVO) {
        userDAO.updateProfileImg(userVO);
    }

    //    회원 탈퇴 (댓글, 게시글 삭제)
    @Override
    public void withdraw(String userEmail) {
        Long userId = userDAO.findIdByEmail(userEmail);

        commentDAO.deleteAllByUserId(userId);

        List<ArtPostDTO> postList = artPostDAO.findAllByUserId(userId);
        for (ArtPostDTO post : postList) {
            Long postId = post.getId();
            commentDAO.deleteAllByPostId(postId);
            artPostDAO.deleteById(postId);
        }

        List<ArtVO> artList = artDAO.findAllByUserId(userId);
        for (ArtVO art : artList) {
            Long artId = art.getId();
            artImgDAO.deleteAllByArtId(artId);
            artDAO.deleteById(artId);
        }
        userDAO.deleteUser(userEmail);

    }

    //    관리자용 유저 정지 처리 및 상태 변경
    @Override
    public void banUser(BanDTO banDTO) {
        userDAO.insertBan(banDTO);
        userDAO.updateUserBanStatus(banDTO);
    }

    //    관리자용 유저 정지 해제
    @Override
    public void updateUserBanStatus(BanDTO banDTO) {
        userDAO.updateUserBanStatus(banDTO);
    }

    //    댓글 작성 시 정지 유저 확인
    @Override
    public Optional<UserVO> findUserByIdForWrite(Long id){
        return userDAO.findUserByIdForWrite(id);
    };
}
