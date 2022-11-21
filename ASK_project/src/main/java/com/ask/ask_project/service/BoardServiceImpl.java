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
    //사용자관리 (updateModal)
    @Override
    public List<UserDTO> updateUserModal(UserDTO userDTO) throws Exception {
        return boardMapper.updateUserModal(userDTO);
    }

    //사용자관리 (delete)
    @Override
    public int deleteUser(UserDTO userDTO) throws Exception {
        return boardMapper.deleteUser(userDTO);
    }
    //마스터키
    @Override
    public String getMaster(UserDTO userDTO) throws Exception {
        Vact_disposeDTO vact_disposeDTO = new Vact_disposeDTO();

        //휴가계산
        int sum=0;
        Vact_listDTO vact_listDTO = new Vact_listDTO();
        int total = vact_listDTO.getTotalVacation();
        int prerod = vact_disposeDTO.getVactPeriod();
        sum = total - prerod;
        vact_listDTO.setTakeVacation(prerod);
        vact_listDTO.setRemindVacation(sum);

        return boardMapper.getMaster(userDTO);
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


    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 근태관리 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
    // 휴가항목 등록 (create)
    @Override
    public int createVactCategory(Vact_CategoryDTO vact_categoryDTO) throws Exception {
        return boardMapper.createVactCategory(vact_categoryDTO);
    }

    //휴가항목 등록 (read)
    @Override
    public List<Vact_CategoryDTO>  readVactCategory(Vact_CategoryDTO vact_categoryDTO) throws Exception {
        return boardMapper.readVactCategory(vact_categoryDTO);
    }
    //휴가항목 등록 (update)
    @Override
    public int updateVactCategory(Vact_CategoryDTO vact_categoryDTO) throws Exception {
        return boardMapper.updateVactCategory(vact_categoryDTO);
    }

    //휴가항목 등록 (delete)
    @Override
    public int deleteVactCategory(String vactCode) throws Exception {
        return boardMapper.deleteVactCategory(vactCode);
    }

    //휴가처리 (read)
    @Override
    public List<Vact_disposeDTO> readVactDispose(Vact_disposeDTO vact_dispose) throws Exception {
        return boardMapper.readVactDispose(vact_dispose);
    }
    //보유휴가 현황 (read)
    @Override
    public List<Vact_listDTO> readVactlist(Vact_listDTO vact_listDTO) throws Exception {
        return boardMapper.readVactlist(vact_listDTO);
    }
    //휴가계산
    @Override
    public int total(Vact_disposeDTO vact_disposeDTO) throws Exception {
        return boardMapper.total(vact_disposeDTO);
    }

    //보유휴가 테이블에 사원정보 삽입
    @Override
    public int insert_vaclist(Vact_listDTO vact_listDTO) throws Exception {
        return boardMapper.insert_vaclist(vact_listDTO);
    }

    //출퇴근관리 (create)
    @Override
    public int createinOut_info(InOut_infoDTO inOut_infoDTO) throws Exception {
        return boardMapper.createinOut_info(inOut_infoDTO);
    }

}