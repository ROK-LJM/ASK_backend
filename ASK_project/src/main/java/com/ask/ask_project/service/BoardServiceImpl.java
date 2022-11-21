package com.ask.ask_project.service;

import com.ask.ask_project.DTO.*;
import com.ask.ask_project.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//멤버 service 구현class
@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardMapper boardMapper;

    //-------------------------------- < 로그인, 회원가입 > ----------------------------------
    //로그인 체크
    @Override
    public int loginCheck(UserDTO userDTO) throws Exception {
        return boardMapper.loginCheck(userDTO);
    }

    //회원가입 - id중복체크
    @Override
    public int checkId(MemberDTO memberDTO) throws Exception {
        System.out.println("serviceImpl id 값 : " + memberDTO.getId());
        return boardMapper.checkId(memberDTO);
    }

    //회원가입
    @Override
    public int insert_memberInfo(MemberDTO memberDTO) throws Exception {
        return boardMapper.insert_memberInfo(memberDTO);
    }

    // 회원가입 - 마스터 -> user_info 넣어주기
    @Override
    public int insert_userInfo(MemberDTO memberDTO) throws Exception {
        return boardMapper.insert_userInfo(memberDTO);
    }

    //-------------------------------- < 사용자관리 > ----------------------------------
    //사용자관리 (create)
    @Override
    public int createUser(UserDTO userDTO) throws Exception {
        return boardMapper.createUser(userDTO);
    }

    //사용자관리 (read)
    @Override
    public List<UserDTO> readUser(UserDTO userDTO) throws Exception {
        return boardMapper.readUser(userDTO);
    }
    //사용자관리 (update)
    @Override
    public int updateUser(UserDTO userDTO) throws Exception {
        return boardMapper.updateUser(userDTO);
    }

    //사용자관리 (delete)
    @Override
    public int deleteUser(String userId) throws Exception {
        return boardMapper.deleteUser(userId);
    }
    //마스터키
    @Override
    public String getMaster(UserDTO userDTO) throws Exception {
        return boardMapper.getMaster(userDTO);
    }

    // 수정 눌러서 해당 id의 update창만 보기
    @Override
    public List<UserDTO> updateUserModal(UserDTO userDTO) throws Exception {
        return boardMapper.updateUserModal(userDTO);
    }
    //-------------------------------- < 회사 설정 > ----------------------------------
    //회사설정 - 회사등록
    @Override
    public int createCompany(CompanyDTO companyDTO) throws Exception {
        return boardMapper.createCompany(companyDTO);
    }


    // 회사설정 - 회사정보 불러오기(read)
    @Override
    public ArrayList<CompanyDTO> readCompany(CompanyDTO companyDTO) throws Exception{
        return boardMapper.readCompany(companyDTO);
    }

    // 회사설정 - 회사정보 수정(modify)
    @Override
    public int updateCompany(CompanyDTO companyDTO) throws Exception{
        return boardMapper.updateCompany(companyDTO);
    }

    // 회사설정 - 회사정보 삭제(delete)
    @Override
    public int deleteCompany(CompanyDTO companyDTO) throws Exception{
        return boardMapper.deleteCompany(companyDTO);
    }

    //-------------------------------- < 부서관리 > ----------------------------------
    // CRD (create, read, delete)
    // 부서관리 - 부서정보 등록(create)
    @Override
    public int createDep(DepDTO depDTO) throws Exception{
        return boardMapper.createDep(depDTO);
    }

    // 부서관리 - 부서정보 불러오기(read)
    @Override
    public ArrayList<DepDTO> readDep(DepDTO depDTO) throws Exception{
        return boardMapper.readDep(depDTO);
    }

    // 부서관리 - 부서정보 삭제(delete)
    @Override
    public int deleteDep(DepDTO depDTO) throws Exception{
        return boardMapper.deleteDep(depDTO);
    }

    //-------------------------------- < 사원관리 > ----------------------------------
    // CRUD (create, read, update, delete)
    // 사원관리 - 사원정보 등록(create)
    @Override
    public int createEmp(EmpDTO empDTO) throws Exception{
        return boardMapper.createEmp(empDTO);
    }

    // 사원관리 - 사원정보 불어오기(read)
    @Override
    public ArrayList<EmpDTO> readEmp(EmpDTO empDTO) throws Exception{
        return boardMapper.readEmp(empDTO);
    }

    // 사원관리 - 사원정보 수정(update)
    @Override
    public int updateEmp(EmpDTO empDTO) throws Exception{
        return boardMapper.updateEmp(empDTO);
    }

    // 사원관리 - 사원정보 삭제(delete)
    @Override
    public int deleteEmp(EmpDTO empDTO) throws Exception{
        return boardMapper.deleteEmp(empDTO);
    }
}
