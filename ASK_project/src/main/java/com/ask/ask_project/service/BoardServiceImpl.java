package com.ask.ask_project.service;

import com.ask.ask_project.DTO.CompanyDTO;
import com.ask.ask_project.DTO.MemberDTO;
import com.ask.ask_project.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//멤버 service 구현class
@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardMapper boardMapper;

    @Override
    public int loginCheck(MemberDTO memberDTO) throws Exception {
        return boardMapper.loginCheck(memberDTO);
    }

    @Override
    public int insert_memberInfo(MemberDTO memberDTO) throws Exception {
        return boardMapper.insert_memberInfo(memberDTO);
    }

    @Override
    public int createCompany(CompanyDTO companyDTO) throws Exception {
        return boardMapper.createCompany(companyDTO);
    }

    @Override
    public int checkId(MemberDTO memberDTO) throws Exception {
        System.out.println("serviceImpl id 값 : " + memberDTO.getId());
        return boardMapper.checkId(memberDTO);
    }
}
