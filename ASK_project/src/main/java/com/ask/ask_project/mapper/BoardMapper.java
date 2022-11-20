package com.ask.ask_project.mapper;

import com.ask.ask_project.DTO.CompanyDTO;
import com.ask.ask_project.DTO.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
//멤버 mapper
@Mapper
public interface BoardMapper {
    int loginCheck(MemberDTO memberDTO) throws Exception;
    int insert_memberInfo(MemberDTO memberDTO) throws Exception;
    int createCompany(CompanyDTO companyDTO) throws  Exception;
    int checkId(MemberDTO memberDTO) throws Exception;
}
