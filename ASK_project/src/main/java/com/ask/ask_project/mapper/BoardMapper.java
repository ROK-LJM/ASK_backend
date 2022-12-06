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

    // 모바일관리 - 모바일계정 정보 삭제(delete)
    int deleteMobile(String mb_id) throws Exception;

    //-------------------------------- < 회사 설정 > ----------------------------------
    // 회사 설정 - 회사 중복 체크
    int checkCompany(CompanyDTO companyDTO) throws Exception;

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

    // 부서관리 - 부서정보 수정(update)
    int updateDep(DepDTO depDTO) throws Exception;

    // 부서관리 - depCode 리스트 가져오기
    int getDepCode(DepDTO depDTO) throws Exception;

    // 부서관리 - 부서정보 수정 모달(modal)
    ArrayList<DepDTO> updateDepModal(DepDTO depDTO) throws Exception;

    // 부서관리 - 부서정보 삭제(delete)
    int deleteDep(DepDTO depDTO) throws Exception;

    //-------------------------------- < 사원관리 > ----------------------------------
    // CRUD (create, read, update, delete)
    // 사원관리 - 사원정보 등록(create)
    int createEmp(EmpDTO empDTO) throws Exception;

    // 사원관리 - 사원정보 중복 처리
    int getEmpCode(EmpDTO empDTO) throws Exception;

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
    // 수당관리 - 수당항목 등록(create)
    int createEmpPay(Emp_payDTO emp_payDTO) throws Exception;

    // 수당관리 - 수당항목 불러오기(read)
    ArrayList<Emp_payDTO> readEmpPay(Emp_payDTO emp_payDTO) throws Exception;

    // 수당관리 - 수당항목 수정(update)
    int updateEmpPay(Emp_payDTO emp_payDTO) throws Exception;
    // 수당관리 - 수당항목 모달
    ArrayList<Emp_payDTO> updateEmpPayModal(Emp_payDTO emp_payDTO) throws Exception;

    // 수당관리 - 수당 계산식 update
    int updateCalc(Emp_payDTO emp_payDTO) throws Exception;

    // 수당관리 - 수당항목 삭제(delete)
    int deleteEmpPay(Emp_payDTO emp_payDTO) throws Exception;

    // 수당관리 - 비과세 목록 가져오기(read)
    ArrayList<TaxFreeDTO> readTaxFree(TaxFreeDTO taxFreeDTO) throws Exception;

    // 수당관리 - payCode 수당코드 중복값 확인
    int getPayCode(Emp_payDTO emp_payDTO) throws Exception;

    //-------------------------------- < 세금관리 > ----------------------------------
    // CRUD (create, read, update, delete)
    // 세금관리 - 세금정보 등록(create)
    int createTax(TaxDTO taxDTO) throws Exception;

    // 세금관리 - 세금코드 중복확인
    int checkCreateTax(TaxDTO taxDTO) throws Exception;

    // 세금관리 - 세금정보 불러오기(read)
    ArrayList<TaxDTO> readTax(TaxDTO taxDTO) throws Exception;

    // 세금관리 - 세금 카테고리 불러오기(read)
    ArrayList<TaxCategoryDTO> readTaxCategory(TaxCategoryDTO taxCategoryDTO) throws Exception;

    // 세금관리 - 선택한 세금항목 값 주기(read)
    ArrayList<TaxCategoryDTO> getTaxCategory(TaxCategoryDTO taxCategoryDTO) throws Exception;

    // 세금관리 - 세금정보 수정(update)
    int updateTax(TaxDTO taxDTO) throws Exception;

    // 세금관리 - 세금코드 중복값 확인
    int getTaxCode(TaxDTO taxDTO) throws Exception;

    // 세금관리 - 세금 수정모달
    ArrayList<TaxDTO> updateTaxModal(TaxDTO taxDTO) throws Exception;

    // 세금관리 - 세금정보 삭제(delete)
    int deleteTax(TaxDTO taxDTO) throws Exception;



    //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 근태관리 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
    //휴가항목 등록 (create)
    int createVactCategory(Vact_CategoryDTO vact_categoryDTO) throws Exception;
    //휴가등록 예외처리
    int checkVactCategory(Vact_CategoryDTO vact_categoryDTO) throws Exception;
    //휴가항목 등록 (read)
    List<Vact_CategoryDTO> readVactCategory(Vact_CategoryDTO vact_categoryDTO) throws Exception;
    //휴가항목 등록 모달창
    List<Vact_CategoryDTO> modalVactCetegory(Vact_CategoryDTO vact_categoryDTO) throws Exception;
    // 휴가등록 (update)
    int updateVactCategory(Vact_CategoryDTO vact_categoryDTO) throws Exception;
    //휴가등록 (delete)
    int deleteVactCategory(Vact_CategoryDTO vact_categoryDTO) throws Exception;
    //휴가처리 (create)
    int createVactDispose(Vact_disposeDTO vact_dispose) throws Exception;

    //휴가처리 ( 잔여휴가 예외처리 )
    Integer VactDispose(Vact_disposeDTO vact_disposeDTO) throws Exception;

    //휴가처리 (read) - 전체
    List<Vact_disposeDTO> readVactDispose(Vact_disposeDTO vact_dispose) throws Exception;
    //휴가처리  - 승인
    List<Vact_disposeDTO> Approval_VactDispose(Vact_disposeDTO vact_disposeDTO) throws Exception;
    //휴가처리 - 요청
    List<Vact_disposeDTO> request_VactDispose(Vact_disposeDTO vact_disposeDTO) throws Exception;
    //휴가처리 - 미승인
    List<Vact_disposeDTO> process_VactDispose(Vact_disposeDTO vact_disposeDTO) throws Exception;

    //휴가처리 상세정보 모달창
    List<Vact_disposeDTO> modaldetailVactlist(Vact_disposeDTO vact_disposeDTO) throws Exception;
    //휴가처리 - 휴가항목 모달창
    List<Vact_CategoryDTO> modalVactCategory (Vact_CategoryDTO vact_categoryDTO) throws Exception;

    //휴가처리 (update) 권한 승인
    int updateVactDispose(Vact_disposeDTO vact_disposeDTO) throws Exception;

    //보유휴가현황 (read)
    List<EmpDTO> readVactlist(EmpDTO empDTO) throws Exception;

    //보유휴가현황 모달창
    List<Vact_disposeDTO> modalVactlist(Vact_disposeDTO vactDisposeDTO) throws Exception;

    //보유휴가 현황 (update)
    int updateVactlist(EmpDTO empDTO) throws Exception;

    //보유휴가 현황 ( 잔여휴가 업데이트 )
    int updateRemindVact(Vact_disposeDTO vact_disposeDTO) throws Exception;

    //출퇴근관리 (모바일 출근요청)
    int createinOut_info(InOut_infoDTO inOut_infoDTO) throws Exception;
    //출퇴근관리 (모바일 퇴근요청)
    int createEnd_inOutInfo(InOut_infoDTO inOut_infoDTO) throws Exception;
    //출퇴근관리 모바일 read
    List<InOut_infoDTO> readMb_inOutInfo(InOut_infoDTO inOut_infoDTO) throws Exception;

    //출퇴근관리 (create)
    int createAdd_inOutInfo(InOut_infoDTO inOut_infoDTO) throws Exception;
    //출퇴근관리 (read)
    List<InOut_infoDTO> readinout_info(InOut_infoDTO inOut_infoDTO) throws Exception;
    //출퇴근관리 초과근무시간 업데이트
    int OvertimeUpdate(InOut_infoDTO inOut_infoDTO) throws Exception;
    //출퇴근관리  모달창
    List<InOut_infoDTO> modal_inOutInfo(InOut_infoDTO inOut_infoDTO) throws Exception;
    //출퇴근관리 (update)
    int updateinOut_info(InOut_infoDTO inOut_infoDTO) throws Exception;
    // 출퇴근관리 (delete)
    int deleteinOut_info(InOut_infoDTO inOutListId) throws Exception;

    //출퇴근 현황 (search)
    List<InOut_infoDTO> searchInout(InOut_infoDTO inOut_infoDTO) throws Exception;

    //출퇴근 현황 이름만 검색
    List<InOut_infoDTO> searchInoutName(InOut_infoDTO inOut_infoDTO) throws Exception;
    //출퇴근 현황 날짜만 검색
    List<InOut_infoDTO> searchInoutDate(InOut_infoDTO inOut_infoDTO) throws Exception;
    //출퇴근 현황 시작날짜 + 이름 검색
    List<InOut_infoDTO> searchInoutstartDate(InOut_infoDTO inOut_infoDTO) throws Exception;
    //출퇴근 현황 시작날짜만 검색
    List<InOut_infoDTO> searchInoutlastDate(InOut_infoDTO inOut_infoDTO) throws Exception;
    //출퇴근 현황 종료날짜만 검색
    List<InOut_infoDTO> searchInoutendDate(InOut_infoDTO inOut_infoDTO) throws Exception;

    //출퇴근 현황 종료날짜 + 이름 검색
    List<InOut_infoDTO> searchInoutEndNameDate(InOut_infoDTO inOut_infoDTO) throws Exception;
    //-------------------------------- < 일용직관리[일용직등록] > ----------------------------------
    // CRUD (create, read, update, delete)
    // 일용직관리[일용직등록] - 일용직정보 중복 처리
    int getDailyCode(DailyEmpDTO dailyEmpDTO) throws Exception;

    // 일용직관리[일용직등록] - 일용직정보 등록(create)
    int createDailyEmp(DailyEmpDTO dailyEmpDTO) throws Exception;

    // 일용직관리[일용직등록] - 일용직정보 불러오기(read)
    ArrayList<DailyEmpDTO> readDailyEmp(DailyEmpDTO dailyEmpDTO) throws Exception;

    // 일용직관리[일용직등록] - 일용직정보 수정(update)
    int updateDailyEmp(DailyEmpDTO dailyEmpDTO) throws Exception;

    // 일용직관리[일용직등록] - 일용직정보 수정 중복 확인
    int getUpdateDailyCode(DailyEmpDTO dailyEmpDTO) throws Exception;

    // 일용직관리[일용직등록] - 일용직정보 수정 모달
    ArrayList<DailyEmpDTO> updateDailyEmpModal(DailyEmpDTO dailyEmpDTO) throws Exception;

    // 일용직관리[일용직등록] - 일용직정보 삭제(delete)
    int deleteDailyEmp(DailyEmpDTO dailyEmpDTO) throws Exception;

    //-------------------------------- < 일용직관리[일용직 수당 등록] > ----------------------------------
    // daily_pay
    // 일용직관리[일용직 수당 등록] - 수당 중복 처리
    int getDPayCode(DailyEmpPayDTO dailyEmpPayDTO) throws Exception;

    // 일용직관리[일용직 수당 등록] - 수당 등록
    int createDailyPay(DailyEmpPayDTO dailyEmpPayDTO) throws Exception;

    // 일용직관리[일용직 수당 등록] - 수당 불러오기
    ArrayList<DailyEmpPayDTO> readDailyPay(DailyEmpPayDTO dailyEmpPayDTO) throws Exception;

    // 일용직관리[일용직 수당 등록] - 일용직 비과세 정보 가져오기
    ArrayList<DailyTaxFreeDTO> readDailyTaxFree(DailyTaxFreeDTO dailyTaxFreeDTO) throws Exception;

    // 일용직관리[일용직 수당 등록] - 수당 수정
    int updateDailyPay(DailyEmpPayDTO dailyEmpPayDTO) throws Exception;

    // 일용직관리[일용직 수당 등록] - 수당 수정 모달(modal)
    ArrayList<DailyEmpPayDTO> updateDailyPayModal(DailyEmpPayDTO dailyEmpPayDTO) throws Exception;

    // 일용직관리[일용직 수당 등록] - 수당 삭제
    int deleteDailyPay(DailyEmpPayDTO dailyEmpPayDTO) throws Exception;

    //-------------------------------- < 일용직관리[출퇴근 관리] > ----------------------------------
    // daily_InOut
    // 일용직관리[출퇴근관리] - 일용직 출퇴근정보 중복처리
    int getInOutDCode(DailyInOutDTO dailyInOutDTO) throws Exception;

    // 일용직관리[출퇴근관리] - 일용직 정보 가져오기(read)
    ArrayList<DailyEmpDTO> searchDailyEmp(DailyEmpDTO dailyEmpDTO) throws Exception;

    // ------------------------
    // 일용직관리[출퇴근관리] - 일용직 출퇴근정보 등록(create)
    int createDailyInOut(DailyInOutDTO dailyInOutDTO) throws Exception;

    // 일용직관리[출퇴근관리] - 일용직 출퇴근정보 불러오기(read)
    ArrayList<DailyInOutDTO> readDailyInOut(DailyInOutDTO dailyInOutDTO) throws Exception;

    // 일용직관리[출퇴근관리] - 일용직 출퇴근정보 수정(update)
    int updateDailyInOut(DailyInOutDTO dailyInOutDTO) throws  Exception;

    // 일용직관리[출퇴근관리] - 일용직 출퇴근정보 모달(modal)
    ArrayList<DailyInOutDTO> updateDailyInOutModal(DailyInOutDTO dailyInOutDTO) throws Exception;

    // 일용직관리[출퇴근관리] - 일용직 출퇴근정보 삭제(delete)
    int deleteDailyInOut(DailyInOutDTO dailyInOutDTO) throws Exception;

    // 일용직관리[출퇴근관리] - 초과근무시간 update
    int updateOverTime(DailyInOutDTO dailyInOutDTO) throws Exception;

    // 일용직관리[출퇴근관리] - 야간근무시간 update
    int updateNightTime(DailyInOutDTO dailyInOutDTO) throws Exception;

    //-------------------------------- < 일용직관리[출퇴근현황] > ----------------------------------
    // [ 날짜만 선택한 경우 ]
    ArrayList<DailyInOutDTO> searchDailyInOut1(DailyInOutDTO dailyInOutDTO) throws Exception;

    // [ 날짜랑 이름 둘 다 선택한 경우 ]
    ArrayList<DailyInOutDTO> searchDailyInOut2(DailyInOutDTO dailyInOutDTO) throws Exception;

    // [ 이름만 선택한 경우 ]
    ArrayList<DailyInOutDTO> searchDailyInOut3(DailyInOutDTO dailyInOutDTO) throws Exception;

    // [ startDate만 입력한 경우 ]
    ArrayList<DailyInOutDTO> searchDailyInOut4(DailyInOutDTO dailyInOutDTO) throws Exception;

    // [ startDate와 이름만 입력한 경우 ]
    ArrayList<DailyInOutDTO> searchDailyInOut5(DailyInOutDTO dailyInOutDTO) throws Exception;

    // [ endDate 값만 입력한 경우 ]
    ArrayList<DailyInOutDTO> searchDailyInOut6(DailyInOutDTO dailyInOutDTO) throws Exception;

    // [ endDate 값과 이름을 입력한 경우 ]
    ArrayList<DailyInOutDTO> searchDailyInOut7(DailyInOutDTO dailyInOutDTO) throws Exception;

    //-------------------------------- < 급여관리[계산 관련]> ----------------------------------
    //기본급여
    int getbaseWage(EmpDTO empDTO) throws Exception;
    //부양가족수
    Integer getfamilyNum(EmpDTO empDTO) throws Exception;
    //야간근로시간 변동(시간)
    String getinOutnight(EmpDTO empDTO) throws Exception;
    //야간근로시간 변동(일)
    String getinOutnightday(EmpDTO empDTO) throws Exception;
    //연장근로시간 변동(시간)
    String getinOutOver(EmpDTO empDTO) throws Exception;
    //연장근로시간 변동(일)
    String getinOutOverDayTax(EmpDTO empDTO) throws Exception;
    //소득세
    List<SimpleTaxTableDTO> getincomeTax(EmpDTO empDTO) throws Exception;
    // 급여관리 - 원하는 사용자의 출퇴근 정보
    ArrayList<InOut_infoDTO> readOverInOutInfo(InOut_infoDTO inOut_infoDTO) throws Exception;

    // [ 야간근로시간 정보 가져오기 ]
    ArrayList<InOut_infoDTO> readNightTime(InOut_infoDTO inOut_infoDTO) throws Exception;

    // [ 야간근로수당 계산식 가져오기 ]
    ArrayList<String> getNightCalc(Emp_payDTO emp_payDTO) throws Exception;

    //-------------------------------- < 급여관리[임원직]> ----------------------------------
    // 급여관리[임원직] - 급여 명세서 중복처리
    int duplicateCheck(Salary_statementDTO salary_statementDTO) throws Exception;

    // 급여관리[임원직] - 급여 명세서 추가(create)
    int createSalary(Salary_statementDTO salary_statementDTO) throws Exception;

    // 급여관리[임원직] - 원하는 사원 정보 가져오기(read)
    EmpDTO getEmpInfo(EmpDTO empDTO) throws Exception;

    // 급여관리 - [메인]급여 명세서 불러오기(임원직)(Read)
    ArrayList<Salary_statementDTO> readMainSalary(Salary_statementDTO salary_statementDTO) throws Exception;

    // 급여관리 - [모달]급여 명세서 불러오기(임원직)(Read)
    ArrayList<Salary_statementDTO> readModalSalary(Salary_statementDTO salary_statementDTO) throws Exception;

    // 급여관리 - 급여 명세서 수정 중복확인
    int duplicateCheckUpdate(Salary_statementDTO salary_statementDTO) throws Exception;

    // 급여관리 - 급여 명세서 수정(임원직)(Update)
    int updateSalary(Salary_statementDTO salary_statementDTO) throws Exception;

    // 급여관리 - 급여 명세서 삭제(임원직)(Delete)
    int deleteSalary(Salary_statementDTO salary_statementDTO) throws Exception;

    // 급여관리 - 수당 계산식 불러오기(read)
    String getCalc(Emp_payDTO emp_payDTO) throws Exception;

    // 급여관리 - 출력 버튼(read)
    Salary_statementDTO getSalaryList(String salaryId) throws Exception;

    // 급여관리 - 경비 값 가져오기(read)(임원직)
    int getExpense(EmpDTO empDTO) throws Exception;

    //-------------------------------- < 급여관리[일용직]> ----------------------------------
    //기본급여
    int getbaseWage2(DailySalaryStatementDTO dailySalaryStatementDTO) throws Exception;
    //부양가족수
    Integer getfamilyNum2(DailySalaryStatementDTO dailySalaryStatementDTO) throws Exception;
    //야간근로시간 변동(시간)
    String getinOutnight2(DailySalaryStatementDTO dailySalaryStatementDTO) throws Exception;
    //야간근로시간 변동(일)
    String getinOutnightday2(DailySalaryStatementDTO dailySalaryStatementDTO) throws Exception;
    //연장근로시간 변동(시간)
    String getinOutOver2(DailySalaryStatementDTO dailySalaryStatementDTO) throws Exception;
    //연장근로시간 변동(일)
    String getinOutOverDayTax2(DailySalaryStatementDTO dailySalaryStatementDTO) throws Exception;
    //소득세
    List<SimpleTaxTableDTO> getincomeTax2(DailySalaryStatementDTO dailySalaryStatementDTO) throws Exception;
    // 급여관리 - 원하는 사용자의 출퇴근 정보
    ArrayList<InOut_infoDTO> readOverInOutInfo2(InOut_infoDTO inOut_infoDTO) throws Exception;

    // [ 야간근로시간 정보 가져오기 ]
    ArrayList<InOut_infoDTO> readNightTime2(InOut_infoDTO inOut_infoDTO) throws Exception;

    // [ 야간근로수당 계산식 가져오기 ]
    ArrayList<String> getNightCalc2(Emp_payDTO emp_payDTO) throws Exception;


    //---------------------------------------------------------
    // 급여관리[일용직] - 급여 명세서 중복처리
    int duplicateCheck2(Salary_statementDTO salary_statementDTO) throws Exception;

    // 급여관리[일용직] - 급여 명세서 추가(create)
    int createDailySalary(DailySalaryStatementDTO dailySalaryStatementDTO) throws Exception;

    // 급여관리[일용직] - 원하는 사원 정보 가져오기(read)
    EmpDTO getEmpInfo2(EmpDTO empDTO) throws Exception;

    // 급여관리 - [메인]급여 명세서 불러오기(일용직)(Read)
    ArrayList<DailySalaryStatementDTO> readDailyMainSalary(DailySalaryStatementDTO dailySalaryStatementDTO) throws Exception;

    // 급여관리 - [모달]급여 명세서 불러오기(일용직)(Read)
    ArrayList<DailySalaryStatementDTO> readDailyModalSalary(DailySalaryStatementDTO dailySalaryStatementDTO) throws Exception;

    // 급여관리 - 급여 명세서 수정 중복확인(일용직)
    int duplicateCheckUpdate2(Salary_statementDTO salary_statementDTO) throws Exception;

    // 급여관리 - 급여 명세서 수정(일용직)(Update)
    int updateSalary2(Salary_statementDTO salary_statementDTO) throws Exception;


    // 급여관리 - 급여 명세서 삭제(일용직)(Delete)
    int deleteDailySalary(DailySalaryStatementDTO dailySalaryStatementDTO) throws Exception;

    // 급여관리 - 수당 계산식 불러오기(일용직)(read)
    String getCalc2(Emp_payDTO emp_payDTO) throws Exception;

    // 급여관리 - 출력 버튼(일용직)(read)
    DailySalaryStatementDTO getSalaryList2(String dailySalaryId) throws Exception;

    // 급여관리 - 경비 값 가져오기(read)
    int getExpense2(DailySalaryStatementDTO dailySalaryStatementDTO) throws Exception;
    //------------------------------------------------- 경비 관리 -------------------------------------------------------
    //경비관리 create
    int expenseCreate(Expense_infoDTO expense_infoDTO) throws Exception;
    //경비관리 read
    List<Expense_infoDTO> expenseRead(Expense_infoDTO expense_infoDTO) throws Exception;
    //경비관리 update 요청날짜 변경
    int expenseUpdateRequest(Expense_infoDTO expense_infoDTO) throws Exception;
    //경비관리 update 승인날짜 변경
    int expenseUpdateApproval(Expense_infoDTO expense_infoDTO) throws Exception;
    //경비관리 delete
    int expenseDelete(Expense_infoDTO expense_infoDTO) throws Exception;
    //경비관리 모달창
    List<Expense_infoDTO> expenseModal (Expense_infoDTO expense_infoDTO) throws Exception;
    //경비관리  (search)
    List<Expense_infoDTO> expenseSearch(Expense_infoDTO expense_infoDTO) throws Exception;
    //경비처리 이름만 검색
    List<Expense_infoDTO> expenseName(Expense_infoDTO expense_infoDTO) throws Exception;
    //경비처리 날짜만 검색
    List<Expense_infoDTO> expensedate(Expense_infoDTO expense_infoDTO) throws Exception;
    //경비처리 시작날짜 + 이름 검색
    List<Expense_infoDTO> expenseStartDateName(Expense_infoDTO expense_infoDTO) throws Exception;
    //경비처리시작날짜만 검색
    List<Expense_infoDTO> expenseStartDate(Expense_infoDTO expense_infoDTO) throws Exception;
    //경비처리 종료날짜만 검색
    List<Expense_infoDTO> expenseEndDate(Expense_infoDTO expense_infoDTO) throws Exception;

    //경비처리종료날짜 + 이름 검색
    List<Expense_infoDTO> expenseEndDateName(Expense_infoDTO expense_infoDTO) throws Exception;

    // 경비처리 - 영수증 카운트를 위해 id값 카운트
    int getCountReceipt(Expense_infoDTO expenseInfoDTO) throws Exception;

    // ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 경리/회계 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
    //거래처관리 (create)
    int clientCreate(Client_infoDTO client_infoDTO) throws Exception;
    //거래처관리 (read)
    List<Client_infoDTO> clientRead(Client_infoDTO client_infoDTO) throws Exception;
    //거래처관리 (update)
    int clientUpdate(Client_infoDTO client_infoDTO) throws Exception;
    //거래처관리 (delete)
    int clientDelete(Client_infoDTO client_infoDTO) throws Exception;
    //거래처관리 모달창
    List<Client_infoDTO> clientModal(Client_infoDTO client_infoDTO) throws Exception;
    //거래상세페이지
    List<IncomeDTO> clientdetail(Client_infoDTO client_infoDTO) throws Exception;


    //매입관리 create
    int income_Create(IncomeDTO income_outcomeDTO) throws Exception;
    //매입관리 read
    List<IncomeDTO> income_Read (IncomeDTO income_outcomeDTO) throws Exception;
    //합계금액
    List<IncomeDTO> income_total(IncomeDTO incomeDTO) throws Exception;

    //합계금액
    List<IncomeDTO> income_total1(IncomeDTO incomeDTO) throws Exception;

    //매입관리 상세페이지
    List<IncomeDTO> income_Modal(IncomeDTO incomeDTO) throws Exception;
    //매입관리 update
    int income_Update(IncomeDTO income_outcomeDTO) throws Exception;
    //매입관리 delete
    int income_Delete(IncomeDTO income_outcomeDTO) throws Exception;
    //매입관리  (search)
    List<IncomeDTO> income_Search(IncomeDTO incomeDTO) throws Exception;
    //매입관리  (search) 합계금액
    List<IncomeDTO> income_SearchTotal(IncomeDTO incomeDTO) throws Exception;
    //매입 + 매출검색
    List<IncomeDTO> income_checkALL(IncomeDTO incomeDTO) throws Exception;
    //매입검색
    List<IncomeDTO> income_income(IncomeDTO incomeDTO) throws Exception;
    //매입검색 합계금액
    List<IncomeDTO> income_incomeTotal(IncomeDTO incomeDTO) throws Exception;
    //매출검색
    List<IncomeDTO> income_outcome(IncomeDTO incomeDTO) throws Exception;
    //매출검색 합계금액
    List<IncomeDTO> income_outcomeTotal(IncomeDTO incomeDTO) throws Exception;
    //매입관리 이름만 검색
    List<IncomeDTO> income_Name(IncomeDTO incomeDTO) throws Exception;
    //매입매출 공급처 검색 합계금액
    List<IncomeDTO> income_NameTotal(IncomeDTO incomeDTO) throws Exception;
    //매입관리 날짜만 검색
    List<IncomeDTO> income_Date(IncomeDTO incomeDTO) throws Exception;
    //매입매출 날짜검색 합계금액
    List<IncomeDTO> income_DateTotal(IncomeDTO incomeDTO) throws Exception;
    //매입관리 시작날짜 + 이름 검색
    List<IncomeDTO> income_StartDateName(IncomeDTO incomeDTO) throws Exception;
    //매입관리 시작날짜 + 이름 검색 합계금액
    List<IncomeDTO> income_StartDateNameTotal(IncomeDTO incomeDTO) throws Exception;
    //매입관리 시작날짜만 검색
    List<IncomeDTO> income_StartDate(IncomeDTO incomeDTO) throws Exception;
    //매입관리 시작날짜만 검색 합계금액
    List<IncomeDTO> income_StartDateTotal(IncomeDTO incomeDTO) throws Exception;
    //매입관리 종료날짜만 검색
    List<IncomeDTO> income_EndDate(IncomeDTO incomeDTO) throws Exception;
    //매입관리 종료날짜만 검색 합계금액
    List<IncomeDTO> income_EndDateTotal(IncomeDTO incomeDTO) throws Exception;
    //매입관리 종료날짜 + 이름 검색
    List<IncomeDTO> income_EndDateName(IncomeDTO incomeDTO) throws Exception;
    //매입관리 종료날짜 + 이름 검색 합계금액
    List<IncomeDTO> income_EndDateNameTotal(IncomeDTO incomeDTO) throws Exception;


    //매입매출 파일,데이터 create
    int taxfile_create(IncomeDTO incomeDTO) throws Exception;

    //매입매출 카운트
    int getCount(IncomeDTO incomeDTO) throws Exception;
    //세금계산서 거래내역보내기
    TaxPaperDTO getTransaction(int purchaseId) throws Exception;
    //세금계산서 거래처정보 가져오기
    List<String> getClientInfo(int purchaseId) throws Exception;
    //세금계산서 거래처 정보 1
    List<TaxPaperDTO> getClientInfo1(String p_clientCompNum) throws Exception;
    //우리회사정보 가져오기
    List<TaxPaperDTO> getCompanyinfo(String compCode) throws Exception;
    //총합금액 가져오가
    List<TaxPaperDTO> getTotalPay(int purchaseId) throws Exception;

    //------------------------------------------< 매입, 매출 차트 >-------------------------------------------------------
    // 1년치 월 매입, 매출 데이터 가져오기

    // 매입
    Integer getIncomeData(IncomeDTO incomeDTO) throws Exception;

    // 매출
    Integer getOutcomeData(IncomeDTO incomeDTO) throws Exception;



    //------------------------------------------ < 모바일 로그인 > -------------------------------------------------------
    // 모바일 로그인 - 계정 존재 여부 확인
    int checkMbLogin(MbUserDTO mbUserDTO) throws Exception;

    // 모바일 로그인 - 계정 정보 불러오기
    MbUserDTO getMbData(MbUserDTO mbUserDTO) throws Exception;

    // 모바일 로그인 - 최초 로그인(비밀번호 변경)
    int mbFirstUpdate(MbUserDTO mbUserDTO) throws Exception;

    //------------------------------------------ < 모바일 메인페이지 > ----------------------------------------------------
    // 모바일 메인페이지 - 출퇴근 현황 표시
    InOut_infoDTO getInOutData(InOut_infoDTO inOut_infoDTO) throws Exception;
}
