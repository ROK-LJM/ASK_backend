package com.ask.ask_project.mapper;

import com.ask.ask_project.DTO.*;

import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

//멤버 mapper
@Mapper
public interface BoardMapper {
    // ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 회원가입 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
    int loginCheck(UserDTO userDTO) throws Exception;
    int insert_memberInfo(MemberDTO memberDTO) throws Exception;
    int checkId(MemberDTO memberDTO) throws Exception;
    int insert_userInfo(MemberDTO memberDTO) throws Exception;
    // ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 사용자 관리 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
    //사용자관리 ( create )
    int createUser(UserDTO userDTO) throws Exception;
    //사용자관리 ( reqd )
    List<UserDTO> readUser(UserDTO userDTO) throws Exception;
    //사용자관리 ( update )
    int updateUser(UserDTO userDTO) throws Exception;
    //사용자관리 (updateModal)
    List<UserDTO> updateUserModal(UserDTO userDTO) throws Exception;
    //사용자관리 ( delete )
    int deleteUser(UserDTO userDTO) throws Exception;
    //마스터키
    String getMaster(UserDTO userDTO) throws Exception;

    //-------------------------------- < 회사 설정 > ----------------------------------
    // 회사 설정 - 회사 등록(create)
    int createCompany(CompanyDTO companyDTO) throws Exception;

    // 회사 설정 - 회사 정보 불러오기(read)
    ArrayList<CompanyDTO> readCompany(CompanyDTO companyDTO) throws Exception;

    // 회사 설정 - 회사 수정(modify)
    int updateCompany(CompanyDTO companyDTO) throws Exception;

    // 회사 설정 - 회사 삭제(delete)
    int deleteCompany(CompanyDTO companyDTO) throws Exception;

    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 근태관리 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
    //휴가항목 등록 (create)
    int createVactCategory(Vact_CategoryDTO vact_categoryDTO) throws Exception;
    //휴가항목 등록 (read)
    List<Vact_CategoryDTO> readVactCategory(Vact_CategoryDTO vact_categoryDTO) throws Exception;
    // 휴가등록 (update)
    int updateVactCategory(Vact_CategoryDTO vact_categoryDTO) throws Exception;
    //휴가등록 (delete)
    int deleteVactCategory(String vactCode) throws Exception;

    //휴가처리 (read)
    List<Vact_disposeDTO> readVactDispose(Vact_disposeDTO vact_dispose) throws Exception;

    //보유휴가현황 (read)
    List<Vact_listDTO> readVactlist(Vact_listDTO vact_listDTO) throws Exception;

    //휴가 가저오기
    int total(Vact_disposeDTO vact_disposeDTO) throws Exception;

    //보유휴가 테이블에 사원정보 넣기
    int insert_vaclist(Vact_listDTO vact_listDTO) throws Exception;

    //출퇴근관리 (create)
    int createinOut_info(InOut_infoDTO inOut_infoDTO) throws Exception;
}
