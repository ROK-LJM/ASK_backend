package com.ask.ask_project.service;

import com.ask.ask_project.DTO.*;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Array;
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

    //-------------------------------- < 모바일관리 > ----------------------------------
    // CRUD (create, read, update, delete)
    // 모바일관리 - 모바일계정 정보 등록(create)
    int createMobile(MbUserDTO mbUserDTO) throws Exception;
    // 모바일관리 - 모바일계정 정보 불러오기(read)
    ArrayList<MbUserDTO> readMobile(MbUserDTO mbUserDTO) throws Exception;

    // 모바일관리 - 모바일계정 정보 수정(update)
    int updateMobile(MbUserDTO mbUserDTO) throws Exception;
    // 모바일관리 - 모바일계정 정보 수정 - 모달 창에 업데이트 할 값 보내기
    ArrayList<MbUserDTO> updateMobileModal(MbUserDTO mbUserDTO) throws Exception;

    // 모바일관리 - 모바일계정 정보 등록(delete)
    int deleteMobile(String mb_id) throws Exception;

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
    // 사원관리 - 사원정보 수정모달(updateEmpPayModal)
    ArrayList<EmpDTO> updateEmpModal(EmpDTO empDTO) throws Exception;

    // 사원관리 - 사원정보 삭제(delete)
    int deleteEmp(EmpDTO empDTO) throws Exception;

    //-------------------------------- < 수당관리 > ----------------------------------
    // CRUD (create, read, update, delete)
    // 수당관리 - 수당항목 등록(create) - 배열로 받는다고 생각하고 만들기
    int createEmpPay(Emp_payDTO emp_payDTO) throws Exception;

    // 수당관리 - 수당항목 불러오기(read)
    ArrayList<Emp_payDTO> readEmpPay(Emp_payDTO emp_payDTO) throws Exception;

    // 수당관리 - 수당항목 수정(update)
    int updateEmpPay(Emp_payDTO emp_payDTO) throws Exception;
    // 수당관리 - 수당항목 모달
    ArrayList<Emp_payDTO> updateEmpPayModal(Emp_payDTO emp_payDTO) throws Exception;

    // 수당관리 - 수당항목 삭제(delete)
    int deleteEmpPay(String empPayID) throws Exception;


    //-------------------------------- < 세금관리 > ----------------------------------
    // CRUD (create, read, update, delete)
    // 세금관리 - 세금정보 등록(create)
    int createTax(TaxDTO taxDTO) throws Exception;

    // 세금관리 - 세금정보 불러오기(read)
    ArrayList<TaxDTO> readTax(TaxDTO taxDTO) throws Exception;

    // 세금관리 - 세금정보 수정(update)
    int updateTax(TaxDTO taxDTO) throws Exception;
    // 세금관리 - 세금 수정모달
    ArrayList<TaxDTO> updateTaxModal(TaxDTO taxDTO) throws Exception;

    // 세금관리 - 세금정보 삭제(delete)
    int deleteTax(String taxInfoID) throws Exception;


    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 근태관리 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
    //휴가항목 등록 (create)
    int createVactCategory(Vact_CategoryDTO vact_categoryDTO) throws Exception;
    //휴가항목 등록 (read)
    List<Vact_CategoryDTO> readVactCategory(Vact_CategoryDTO vact_categoryDTO) throws Exception;
    //휴가항목 등록 (update)
    int updateVactCategory(Vact_CategoryDTO vact_categoryDTO) throws Exception;
    //휴가항목등록 (delete)
    int deleteVactCategory(String vactNameListId) throws Exception;
    //휴가처리 (create)
    int createVactDispose(Vact_disposeDTO vact_dispose) throws Exception;
    //휴가처리 ( 잔여휴가 예외처리 )
    int VactDispose(Vact_disposeDTO vact_disposeDTO) throws Exception;
    //휴가처리 (read)
    List<Vact_disposeDTO> readVactDispose(Vact_disposeDTO vact_dispose) throws Exception;
    //휴가처리 (update) 권한 승인
    int updateVactDispose(Vact_disposeDTO vact_disposeDTO) throws Exception;

    //보유휴가 현황 (read)
    List<EmpDTO> readVactlist(EmpDTO empDTO) throws Exception;
    //보유휴가 현황 (update)
    int updateVactlist(EmpDTO empDTO) throws Exception;
    //보유휴가 현황 ( 잔여휴가 업데이트 )
    int updateRemindVact() throws Exception;
    //출퇴근 관리 (create)
    int createinOut_info(InOut_infoDTO inOut_infoDTO) throws Exception;
    //출퇴근관리 (read)
    List<InOut_infoDTO> readinout_info(InOut_infoDTO inOut_infoDTO) throws Exception;
    //출퇴근관리 (update)
    int updateinOut_info(InOut_infoDTO inOut_infoDTO) throws Exception;
    // 출퇴근관리 (delete)
    int deleteinOut_info(int inOutListId) throws Exception;
    //출퇴근 현황 (search)
    List<InOut_infoDTO> searchInout(InOut_infoDTO inOut_infoDTO) throws Exception;

    //-------------------------------- < 일용직관리[일용직등록] > ----------------------------------
    // CRUD (create, read, update, delete)
    // 일용직관리[일용직등록] - 일용직정보 등록(create)
    int createDailyEmp(DailyEmpDTO dailyEmpDTO) throws Exception;

    // 일용직관리[일용직등록] - 일용직정보 불러오기(read)
    ArrayList<DailyEmpDTO> readDailyEmp(DailyEmpDTO dailyEmpDTO) throws Exception;

    // 일용직관리[일용직등록] - 일용직정보 수정(update)
    int updateDailyEmp(DailyEmpDTO dailyEmpDTO) throws Exception;

    // 일용직관리[일용직등록] - 일용직정보 수정 모달
    ArrayList<DailyEmpDTO> updateDailyEmpModal(DailyEmpDTO dailyEmpDTO) throws Exception;

    // 일용직관리[일용직등록] - 일용직정보 삭제(delete)
    int deleteDailyEmp(DailyEmpDTO dailyEmpDTO) throws Exception;

    //-------------------------------- < 일용직관리[일용직 수당 등록] > ----------------------------------
    // daily_pay
    // 일용직관리[일용직 수당 등록] - 수당 등록
    int createDailyPay(DailyEmpPayDTO dailyEmpPayDTO) throws Exception;

    // 일용직관리[일용직 수당 등록] - 수당 불러오기
    ArrayList<DailyEmpPayDTO> readDailyPay(DailyEmpPayDTO dailyEmpPayDTO) throws Exception;

    // 일용직관리[일용직 수당 등록] - 수당 수정
    int updateDailyPay(DailyEmpPayDTO dailyEmpPayDTO) throws Exception;

    // 일용직관리[일용직 수당 등록] - 수당 수정 모달(modal)
    ArrayList<DailyEmpPayDTO> updateDailyPayModal(DailyEmpPayDTO dailyEmpPayDTO) throws Exception;

    // 일용직관리[일용직 수당 등록] - 수당 삭제
    int deleteDailyPay(DailyEmpPayDTO dailyEmpPayDTO) throws Exception;


    //-------------------------------- < 일용직관리[출퇴근 관리] > ----------------------------------
    // daily_InOut
    // 일용직관리[출퇴근관리] - 일용직 정보 가져오기(read)
    ArrayList<DailyEmpDTO> searchDailyEmp(DailyEmpDTO dailyEmpDTO);

    // ------------------------
    // 일용직관리[출퇴근관리] - 일용직 출퇴근정보 등록(create)

    // 일용직관리[출퇴근관리] - 일용직 출퇴근정보 불러오기(read)

    // 일용직관리[출퇴근관리] - 일용직 출퇴근정보 수정(update)

    // 일용직관리[출퇴근관리] - 일용직 출퇴근정보 삭제(delete)
}
