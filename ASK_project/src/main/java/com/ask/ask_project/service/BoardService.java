package com.ask.ask_project.service;

import com.ask.ask_project.DTO.MemberDTO;

public interface BoardService {
    int loginCheck(MemberDTO memberDTO) throws Exception;
}
