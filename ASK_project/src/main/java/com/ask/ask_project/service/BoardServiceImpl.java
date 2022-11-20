package com.ask.ask_project.service;

import com.ask.ask_project.DTO.MemberDTO;
import com.ask.ask_project.DTO.UserDTO;
import com.ask.ask_project.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//멤버 service 구현class
@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardMapper boardMapper;

    //로그인 체크
    @Override
    public int loginCheck(MemberDTO memberDTO) throws Exception {
        return boardMapper.loginCheck();
    }

    //회원가입
    @Override
    public int insert_memberInfo(MemberDTO memberDTO) throws Exception {
        return boardMapper.insert_memberInfo();
    }
    //사용자관리 (create)
    @Override
    public int createUser(UserDTO userDTO) throws Exception {
        return boardMapper.createUser();
    }

    //사용자관리 (read)
    @Override
    public List<UserDTO> readUser(UserDTO userDTO) throws Exception {
        return boardMapper.readUser();
    }
}
