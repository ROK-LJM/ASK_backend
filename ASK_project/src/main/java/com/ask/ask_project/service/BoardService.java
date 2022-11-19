package com.ask.ask_project.service;

import com.ask.ask_project.DTO.MemberDTO;
//멤버 service
public interface BoardService {
    int loginCheck(MemberDTO memberDTO) throws Exception;
    int insert_memberInfo(MemberDTO memberDTO) throws Exception;
}
