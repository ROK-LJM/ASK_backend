package com.ask.ask_project.service;

import com.ask.ask_project.DTO.*;
import com.ask.ask_project.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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

    //-------------------------------- < 모바일관리 > ----------------------------------
    // CRUD (create, read, update, delete)
    // 모바일관리 - 모바일계정 정보 등록(create)
    @Override
    public int createMobile(MbUserDTO mbUserDTO) throws Exception{
        return boardMapper.createMobile(mbUserDTO);
    }
    // 모바일관리 - 모바일계정 정보 불러오기(read)
    @Override
    public ArrayList<MbUserDTO> readMobile(MbUserDTO mbUserDTO) throws Exception{
        return boardMapper.readMobile(mbUserDTO);
    }

    // 모바일관리 - 모바일계정 정보 수정(update)
    @Override
    public int updateMobile(MbUserDTO mbUserDTO) throws Exception{
        return boardMapper.updateMobile(mbUserDTO);
    }
    // 모바일관리 - 모바일계정 정보 수정 - 모달 창에 업데이트 할 값 보내기
    @Override
    public ArrayList<MbUserDTO> updateMobileModal(MbUserDTO mbUserDTO) throws Exception{
        return boardMapper.updateMobileModal(mbUserDTO);
    }


    // 모바일관리 - 모바일계정 정보 등록(delete)
    @Override
    public int deleteMobile(String mb_id) throws Exception{
        return boardMapper.deleteMobile(mb_id);
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
    // 사원관리 - 사원정보 수정모달(updateEmpPayModal)
    @Override
    public ArrayList<EmpDTO> updateEmpModal(EmpDTO empDTO) throws Exception{
        return boardMapper.updateEmpModal(empDTO);
    }

    // 사원관리 - 사원정보 삭제(delete)
    @Override
    public int deleteEmp(EmpDTO empDTO) throws Exception{
        return boardMapper.deleteEmp(empDTO);
    }


    //-------------------------------- < 수당관리 > ----------------------------------
    // CRUD (create, read, update, delete)
    // 수당관리 - 수당항목 등록(create) - 배열로 받는다고 생각하고 만들기
    @Override
    public int createEmpPay(Emp_payDTO emp_payDTO) throws Exception{
        return boardMapper.createEmpPay(emp_payDTO);
    }

    // 수당관리 - 수당항목 불러오기(read)
    @Override
    public ArrayList<Emp_payDTO> readEmpPay(Emp_payDTO emp_payDTO) throws Exception{
        return boardMapper.readEmpPay(emp_payDTO);
    }

    // 수당관리 - 수당항목 수정(update)
    @Override
    public int updateEmpPay(Emp_payDTO emp_payDTO) throws Exception{
        return boardMapper.updateEmpPay(emp_payDTO);
    }
    // 수당관리 - 수당항목 모달
    @Override
    public ArrayList<Emp_payDTO> updateEmpPayModal(Emp_payDTO emp_payDTO) throws Exception {
        return boardMapper.updateEmpPayModal(emp_payDTO);
    }

    // 수당관리 - 수당항목 삭제(delete)
    @Override
    public int deleteEmpPay(String empPayID) throws Exception{
        return boardMapper.deleteEmpPay(empPayID);
    }

    //-------------------------------- < 세금관리 > ----------------------------------
    // CRUD (create, read, update, delete)
    // 세금관리 - 세금정보 등록(create)
    @Override
    public int createTax(TaxDTO taxDTO) throws Exception{
        return boardMapper.createTax(taxDTO);
    }

    // 세금관리 - 세금정보 불러오기(read)
    @Override
    public ArrayList<TaxDTO> readTax(TaxDTO taxDTO) throws Exception{
        return boardMapper.readTax(taxDTO);
    }

    // 세금관리 - 세금정보 수정(update)
    @Override
    public int updateTax(TaxDTO taxDTO) throws Exception{
        return boardMapper.updateTax(taxDTO);
    }
    // 세금관리 - 세금 수정모달
    @Override
    public ArrayList<TaxDTO> updateTaxModal(TaxDTO taxDTO) throws Exception{
        return boardMapper.updateTaxModal(taxDTO);
    }

    // 세금관리 - 세금정보 삭제(delete)
    @Override
    public int deleteTax(String taxInfoID) throws Exception{
        return boardMapper.deleteTax(taxInfoID);
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
    public int deleteVactCategory(String vactNameListId) throws Exception {
        return boardMapper.deleteVactCategory(vactNameListId);
    }
    //휴가처리 (create)
    @Override
    public int createVactDispose(Vact_disposeDTO vact_dispose) throws Exception {
        return boardMapper.createVactDispose(vact_dispose);
    }
    //휴가처리 ( 잔여휴가일수 예외처리 )
    @Override
    public int VactDispose(Vact_disposeDTO vact_disposeDTO) throws Exception {
        return boardMapper.VactDispose(vact_disposeDTO);
    }

    //휴가처리 (read)
    @Override
    public List<Vact_disposeDTO> readVactDispose(Vact_disposeDTO vact_dispose) throws Exception {
        return boardMapper.readVactDispose(vact_dispose);
    }
    // 휴가처리 (update) 권한승인
    @Override
    public int updateVactDispose(Vact_disposeDTO vact_disposeDTO) throws Exception {
        return boardMapper.updateVactDispose(vact_disposeDTO);
    }

    //보유휴가 현황 (read)
    @Override
    public List<EmpDTO> readVactlist(EmpDTO empDTO) throws Exception {
        return boardMapper.readVactlist(empDTO);
    }
    //보유휴가 현황 (update)
    @Override
    public int updateVactlist(EmpDTO empDTO) throws Exception {
        return boardMapper.updateVactlist(empDTO);
    }
    //보유휴가 현황 ( 잔여휴가 업데이트 )
    @Override
    public int updateRemindVact() throws Exception {
        return boardMapper.updateRemindVact();
    }


    //출퇴근관리 (create)
    @Override
    public int createinOut_info(InOut_infoDTO inOut_infoDTO) throws Exception {
        return boardMapper.createinOut_info(inOut_infoDTO);
    }
    //출퇴근관리 (read)
    @Override
    public List<InOut_infoDTO> readinout_info(InOut_infoDTO inOut_infoDTO) throws Exception {
        return boardMapper.readinout_info(inOut_infoDTO);
    }
    //출퇴근관리 (update)
    @Override
    public int updateinOut_info(InOut_infoDTO inOut_infoDTO) throws Exception {
        return boardMapper.updateinOut_info(inOut_infoDTO);
    }
    //출퇴근관리 (delete)
    @Override
    public int deleteinOut_info(int inOutListId) throws Exception {
        return boardMapper.deleteinOut_info(inOutListId);
    }
    //출퇴근현황 ( 예외처리 )
    @Override
    public List<InOut_infoDTO> searchInout(InOut_infoDTO inOut_infoDTO) throws Exception {
        return boardMapper.searchInout(inOut_infoDTO);
    }
    //-------------------------------- < 일용직관리[일용직등록] > ----------------------------------
    // CRUD (create, read, update, delete)
    // 일용직관리[일용직등록] - 일용직정보 등록(create)
    @Override
    public int createDailyEmp(DailyEmpDTO dailyEmpDTO) throws Exception{
        return boardMapper.createDailyEmp(dailyEmpDTO);
    }

    // 일용직관리[일용직등록] - 일용직정보 불러오기(read)
    @Override
    public ArrayList<DailyEmpDTO> readDailyEmp(DailyEmpDTO dailyEmpDTO) throws Exception{
        return boardMapper.readDailyEmp(dailyEmpDTO);
    }

    // 일용직관리[일용직등록] - 일용직정보 수정(update)
    @Override
    public int updateDailyEmp(DailyEmpDTO dailyEmpDTO) throws Exception{
        return boardMapper.updateDailyEmp(dailyEmpDTO);
    }

    // 일용직관리[일용직등록] - 일용직정보 수정 모달
    @Override
    public ArrayList<DailyEmpDTO> updateDailyEmpModal(DailyEmpDTO dailyEmpDTO) throws Exception{
        return boardMapper.updateDailyEmpModal(dailyEmpDTO);
    }

    // 일용직관리[일용직등록] - 일용직정보 삭제(delete)
    @Override
    public int deleteDailyEmp(DailyEmpDTO dailyEmpDTO) throws Exception{
        return boardMapper.deleteDailyEmp(dailyEmpDTO);
    }

    //-------------------------------- < 일용직관리[일용직 수당 등록] > ----------------------------------
    // daily_pay
    // 일용직관리[일용직 수당 등록] - 수당 등록
    @Override
    public int createDailyPay(DailyEmpPayDTO dailyEmpPayDTO) throws Exception{
        return boardMapper.createDailyPay(dailyEmpPayDTO);
    }

    // 일용직관리[일용직 수당 등록] - 수당 불러오기
    @Override
    public ArrayList<DailyEmpPayDTO> readDailyPay(DailyEmpPayDTO dailyEmpPayDTO) throws Exception{
        return boardMapper.readDailyPay(dailyEmpPayDTO);
    }

    // 일용직관리[일용직 수당 등록] - 수당 수정
    @Override
    public int updateDailyPay(DailyEmpPayDTO dailyEmpPayDTO) throws Exception{
        return boardMapper.updateDailyPay(dailyEmpPayDTO);
    }

    // 일용직관리[일용직 수당 등록] - 수당 수정 모달(modal)
    @Override
    public ArrayList<DailyEmpPayDTO> updateDailyPayModal(DailyEmpPayDTO dailyEmpPayDTO) throws Exception{
        return boardMapper.updateDailyPayModal(dailyEmpPayDTO);
    }

    // 일용직관리[일용직 수당 등록] - 수당 삭제
    @Override
    public int deleteDailyPay(DailyEmpPayDTO dailyEmpPayDTO) throws Exception{
        return boardMapper.deleteDailyPay(dailyEmpPayDTO);
    }

    //-------------------------------- < 일용직관리[출퇴근 관리] > ----------------------------------
    // daily_InOut
    // 일용직관리[출퇴근관리] - 일용직 정보 가져오기(read)
    @Override
    public ArrayList<DailyEmpDTO> searchDailyEmp(DailyEmpDTO dailyEmpDTO){
        return boardMapper.searchDailyEmp(dailyEmpDTO);
    }

    // ------------------------
    // 일용직관리[출퇴근관리] - 일용직 출퇴근정보 등록(create)

    // 일용직관리[출퇴근관리] - 일용직 출퇴근정보 불러오기(read)

    // 일용직관리[출퇴근관리] - 일용직 출퇴근정보 수정(update)

    // 일용직관리[출퇴근관리] - 일용직 출퇴근정보 모달(modal)

    // 일용직관리[출퇴근관리] - 일용직 출퇴근정보 삭제(delete)

}