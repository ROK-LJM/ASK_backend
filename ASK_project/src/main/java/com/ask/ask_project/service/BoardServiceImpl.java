package com.ask.ask_project.service;

import com.ask.ask_project.DTO.MemberDTO;
import com.ask.ask_project.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardMapper boardMapper;

    @Override
    public int loginCheck(MemberDTO memberDTO) throws Exception {
        return boardMapper.loginCheck();
    }
}
