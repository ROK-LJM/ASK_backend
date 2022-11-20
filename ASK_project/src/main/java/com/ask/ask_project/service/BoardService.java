package com.ask.ask_project.service;

import com.ask.ask_project.DTO.CompanyDTO;
import com.ask.ask_project.DTO.MemberDTO;
//멤버 service
public interface BoardService {
    int loginCheck(MemberDTO memberDTO) throws Exception;
    int insert_memberInfo(MemberDTO memberDTO) throws Exception;
    int createCompany(CompanyDTO companyDTO) throws Exception;
    int checkId(MemberDTO memberDTO) throws Exception;
}
