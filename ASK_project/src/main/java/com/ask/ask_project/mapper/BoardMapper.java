package com.ask.ask_project.mapper;

import com.ask.ask_project.DTO.CompanyDTO;
import com.ask.ask_project.DTO.MemberDTO;

import com.ask.ask_project.DTO.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//멤버 mapper
@Mapper
public interface BoardMapper {

    //사용자관리 ( create )
    int createUser() throws Exception;
    //사용자관리 ( reqd )
    List<UserDTO> readUser() throws Exception;
    //사용자관리 ( update )

    //사용자관리 ( delete )

    int loginCheck(MemberDTO memberDTO) throws Exception;
    int insert_memberInfo(MemberDTO memberDTO) throws Exception;
    int createCompany(CompanyDTO companyDTO) throws  Exception;
    int checkId(MemberDTO memberDTO) throws Exception;

}
