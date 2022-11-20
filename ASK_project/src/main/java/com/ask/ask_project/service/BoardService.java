package com.ask.ask_project.service;

import com.ask.ask_project.DTO.CompanyDTO;
import com.ask.ask_project.DTO.MemberDTO;
import com.ask.ask_project.DTO.UserDTO;
import org.apache.catalina.User;

import java.util.List;

//멤버 service
public interface BoardService {
    //로그인
    int loginCheck(MemberDTO memberDTO) throws Exception;

    //회원가입
    int insert_memberInfo(MemberDTO memberDTO) throws Exception;

    //사용자관리 (create)
    int createUser(UserDTO userDTO) throws Exception;

    //사용자관리 (read)
    List<UserDTO> readUser(UserDTO userDTO) throws Exception;
    //사용자관리 (update)

    //사용자관리 (delete)

    int createCompany(CompanyDTO companyDTO) throws Exception;

    int checkId(MemberDTO memberDTO) throws Exception;
}
