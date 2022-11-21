package com.ask.ask_project.service;

import com.ask.ask_project.DTO.*;
import org.apache.catalina.User;

import java.util.ArrayList;
import java.util.List;

//멤버 service
public interface BoardService {
    //-------------------------------- < 로그인, 회원가입 > ----------------------------------
    //로그인
    int loginCheck(UserDTO userDTO) throws Exception;

    //회원가입
    int insert_memberInfo(MemberDTO memberDTO) throws Exception;

    //회원가입 - ID 중복처리
    int checkId(MemberDTO memberDTO) throws Exception;

    // 회원가입 - 마스터 -> user_info 넣어주기
    int insert_userInfo(MemberDTO memberDTO) throws Exception;

    //-------------------------------- < 사용자관리 > ----------------------------------
    //사용자관리 (create)
    int createUser(UserDTO userDTO) throws Exception;
    //사용자관리 (read)
    List<UserDTO> readUser(UserDTO userDTO) throws Exception;
    //사용자관리 (update)
    int updateUser(UserDTO userDTO) throws Exception;

    //사용자관리 (delete)
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
}
