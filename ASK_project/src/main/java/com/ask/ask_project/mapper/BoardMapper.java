package com.ask.ask_project.mapper;

import com.ask.ask_project.DTO.*;

import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

//멤버 mapper
@Mapper
public interface BoardMapper {
    //-------------------------------- < 로그인, 회원가입 > ----------------------------------
    // 로그인 - 로그인 체크
    int loginCheck(UserDTO userDTO) throws Exception;

    // 회원가입 - id 중복체크
    int checkId(MemberDTO memberDTO) throws Exception;

    // 회원가입
    int insert_memberInfo(MemberDTO memberDTO) throws Exception;

    // 회원가입 - 마스터 -> user_info 넣어주기
    int insert_userInfo(MemberDTO memberDTO) throws Exception;

    //-------------------------------- < 사용자관리 > ----------------------------------
    //사용자관리 ( create )
    int createUser(UserDTO userDTO) throws Exception;
    //사용자관리 ( read )
    List<UserDTO> readUser(UserDTO userDTO) throws Exception;

    //사용자관리 ( update )
    int updateUser(UserDTO userDTO) throws Exception;
    //사용자관리 ( delete )
    int deleteUser(String userId) throws Exception;
    //마스터키
    String getMaster(UserDTO userDTO) throws Exception;

    // 수정 눌러서 해당 id의 update창만 보기
    List<UserDTO> updateUserModal(UserDTO userDTO) throws Exception;

    //-------------------------------- < 회사 설정 > ----------------------------------
    // 회사 설정 - 회사 등록(create)
    int createCompany(CompanyDTO companyDTO) throws Exception;

    // 회사 설정 - 회사 정보 불러오기(read)
    ArrayList<CompanyDTO> readCompany(CompanyDTO companyDTO) throws Exception;

    // 회사 설정 - 회사 수정(modify)
    int updateCompany(CompanyDTO companyDTO) throws Exception;

    // 회사 설정 - 회사 삭제(delete)
    int deleteCompany(CompanyDTO companyDTO) throws Exception;

    //-------------------------------- < 부서관리 > ----------------------------------
    // CRD (create, read, delete)
    // 부서관리 - 부서정보 등록(create)
    int createDep(DepDTO depDTO) throws Exception;

    // 부서관리 - 부서정보 불러오기(read)
    ArrayList<DepDTO> readDep(DepDTO depDTO) throws Exception;

    // 부서관리 - 부서정보 삭제(delete)
    int deleteDep(DepDTO depDTO) throws Exception;

    //-------------------------------- < 사원관리 > ----------------------------------
    // CRUD (create, read, update, delete)
    // 사원관리 - 사원정보 등록(create)
    int createEmp(EmpDTO empDTO) throws Exception;

    // 사원관리 - 사원정보 불어오기(read)
    ArrayList<EmpDTO> readEmp(EmpDTO empDTO) throws Exception;

    // 사원관리 - 사원정보 수정(update)
    int updateEmp(EmpDTO empDTO) throws Exception;

    // 사원관리 - 사원정보 삭제(delete)
    int deleteEmp(EmpDTO empDTO) throws Exception;




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
