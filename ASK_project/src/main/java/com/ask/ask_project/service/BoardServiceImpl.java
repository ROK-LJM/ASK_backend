package com.ask.ask_project.service;

import com.ask.ask_project.DTO.*;
import com.ask.ask_project.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.springframework.web.multipart.MultipartFile;


//멤버 service 구현class
@Service
public class BoardServiceImpl implements BoardService {


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
        public int createMobile(MbUserDTO mbUserDTO) throws Exception {
            return boardMapper.createMobile(mbUserDTO);
        }

        // 모바일관리 - 모바일계정 정보 불러오기(read)
        @Override
        public ArrayList<MbUserDTO> readMobile(MbUserDTO mbUserDTO) throws Exception {
            return boardMapper.readMobile(mbUserDTO);
        }

        // 모바일관리 - 모바일계정 정보 수정(update)
        @Override
        public int updateMobile(MbUserDTO mbUserDTO) throws Exception {
            return boardMapper.updateMobile(mbUserDTO);
        }

        // 모바일관리 - 모바일계정 정보 수정 - 모달 창에 업데이트 할 값 보내기
        @Override
        public ArrayList<MbUserDTO> updateMobileModal(MbUserDTO mbUserDTO) throws Exception {
            return boardMapper.updateMobileModal(mbUserDTO);
        }


        // 모바일관리 - 모바일계정 정보 등록(delete)
        @Override
        public int deleteMobile(String mb_id) throws Exception {
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
        public ArrayList<CompanyDTO> readCompany(CompanyDTO companyDTO) throws Exception {
            return boardMapper.readCompany(companyDTO);
        }

        // 회사설정 - 회사정보 수정(modify)
        @Override
        public int updateCompany(CompanyDTO companyDTO) throws Exception {
            return boardMapper.updateCompany(companyDTO);
        }

        // 회사설정 - 회사정보 삭제(delete)
        @Override
        public int deleteCompany(CompanyDTO companyDTO) throws Exception {
            return boardMapper.deleteCompany(companyDTO);
        }

        //-------------------------------- < 부서관리 > ----------------------------------
        // CRD (create, read, delete)
        // 부서관리 - 부서정보 등록(create)
        @Override
        public int createDep(DepDTO depDTO) throws Exception {
            return boardMapper.createDep(depDTO);
        }

        // 부서관리 - 부서정보 불러오기(read)
        @Override
        public ArrayList<DepDTO> readDep(DepDTO depDTO) throws Exception {
            return boardMapper.readDep(depDTO);
        }

        // 부서관리 - 부서정보 삭제(delete)
        @Override
        public int deleteDep(DepDTO depDTO) throws Exception {
            return boardMapper.deleteDep(depDTO);
        }

        //-------------------------------- < 사원관리 > ----------------------------------
        // CRUD (create, read, update, delete)
        // 사원관리 - 사원정보 등록(create)
        @Override
        public int createEmp(EmpDTO empDTO) throws Exception {
            return boardMapper.createEmp(empDTO);
        }

        // 사원관리 - 사원정보 불어오기(read)
        @Override
        public ArrayList<EmpDTO> readEmp(EmpDTO empDTO) throws Exception {
            return boardMapper.readEmp(empDTO);
        }

        // 사원관리 - 사원정보 수정(update)
        @Override
        public int updateEmp(EmpDTO empDTO) throws Exception {
            return boardMapper.updateEmp(empDTO);
        }

        // 사원관리 - 사원정보 수정모달(updateEmpPayModal)
        @Override
        public ArrayList<EmpDTO> updateEmpModal(EmpDTO empDTO) throws Exception {
            return boardMapper.updateEmpModal(empDTO);
        }

        // 사원관리 - 사원정보 삭제(delete)
        @Override
        public int deleteEmp(EmpDTO empDTO) throws Exception {
            return boardMapper.deleteEmp(empDTO);
        }


        //-------------------------------- < 수당관리 > ----------------------------------
        // CRUD (create, read, update, delete)
        // 수당관리 - 수당항목 등록(create) - 배열로 받는다고 생각하고 만들기
        @Override
        public int createEmpPay(Emp_payDTO emp_payDTO) throws Exception {
            return boardMapper.createEmpPay(emp_payDTO);
        }

        // 수당관리 - 수당항목 불러오기(read)
        @Override
        public ArrayList<Emp_payDTO> readEmpPay(Emp_payDTO emp_payDTO) throws Exception {
            return boardMapper.readEmpPay(emp_payDTO);
        }

        // 수당관리 - 수당항목 수정(update)
        @Override
        public int updateEmpPay(Emp_payDTO emp_payDTO) throws Exception {
            return boardMapper.updateEmpPay(emp_payDTO);
        }

        // 수당관리 - 수당항목 모달
        @Override
        public ArrayList<Emp_payDTO> updateEmpPayModal(Emp_payDTO emp_payDTO) throws Exception {
            return boardMapper.updateEmpPayModal(emp_payDTO);
        }

        // 수당관리 - 수당항목 삭제(delete)
        @Override
        public int deleteEmpPay(String empPayID) throws Exception {
            return boardMapper.deleteEmpPay(empPayID);
        }

        //-------------------------------- < 세금관리 > ----------------------------------
        // CRUD (create, read, update, delete)
        // 세금관리 - 세금정보 등록(create)
        @Override
        public int createTax(TaxDTO taxDTO) throws Exception {
            return boardMapper.createTax(taxDTO);
        }

        // 세금관리 - 세금정보 불러오기(read)
        @Override
        public ArrayList<TaxDTO> readTax(TaxDTO taxDTO) throws Exception {
            return boardMapper.readTax(taxDTO);
        }

        // 세금관리 - 세금정보 수정(update)
        @Override
        public int updateTax(TaxDTO taxDTO) throws Exception {
            return boardMapper.updateTax(taxDTO);
        }

        // 세금관리 - 세금 수정모달
        @Override
        public ArrayList<TaxDTO> updateTaxModal(TaxDTO taxDTO) throws Exception {
            return boardMapper.updateTaxModal(taxDTO);
        }

        // 세금관리 - 세금정보 삭제(delete)
        @Override
        public int deleteTax(String taxInfoID) throws Exception {
            return boardMapper.deleteTax(taxInfoID);
        }

        //ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 근태관리 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
        // 휴가항목 등록 (create)
        @Override
        public int createVactCategory(Vact_CategoryDTO vact_categoryDTO) throws Exception {
            return boardMapper.createVactCategory(vact_categoryDTO);
        }

        //휴가등록 예외처리
        @Override
        public int checkVactCategory(Vact_CategoryDTO vact_categoryDTO) throws Exception {
            return boardMapper.checkVactCategory(vact_categoryDTO);
        }

        //휴가항목 등록 (read)
        @Override
        public List<Vact_CategoryDTO> readVactCategory(Vact_CategoryDTO vact_categoryDTO) throws Exception {
            return boardMapper.readVactCategory(vact_categoryDTO);
        }

        //휴가항목 등록창 모달
        @Override
        public List<Vact_CategoryDTO> modalVactCetegory(Vact_CategoryDTO vact_categoryDTO) throws Exception {
            return boardMapper.modalVactCetegory(vact_categoryDTO);
        }

        //휴가항목 등록 (update)
        @Override
        public int updateVactCategory(Vact_CategoryDTO vact_categoryDTO) throws Exception {
            return boardMapper.updateVactCategory(vact_categoryDTO);
        }

        //휴가항목 등록 (delete)
        @Override
        public int deleteVactCategory(Vact_CategoryDTO vact_categoryDTO) throws Exception {
            return boardMapper.deleteVactCategory(vact_categoryDTO);
        }

        //휴가처리 (create)
        @Override
        public int createVactDispose(Vact_disposeDTO vact_dispose) throws Exception {
            return boardMapper.createVactDispose(vact_dispose);
        }

        //휴가처리 ( 잔여휴가일수 예외처리 )
        @Override
        public Integer VactDispose(Vact_disposeDTO vact_disposeDTO) throws Exception {
            return boardMapper.VactDispose(vact_disposeDTO);
        }

        //휴가처리 (read)
        @Override
        public List<Vact_disposeDTO> readVactDispose(Vact_disposeDTO vact_dispose) throws Exception {
            return boardMapper.readVactDispose(vact_dispose);
        }

        // 휴가처리 승인
        @Override
        public List<Vact_disposeDTO> Approval_VactDispose(Vact_disposeDTO vact_disposeDTO) throws Exception {
            return boardMapper.Approval_VactDispose(vact_disposeDTO);
        }

        //휴가처리 요청
        @Override
        public List<Vact_disposeDTO> request_VactDispose(Vact_disposeDTO vact_disposeDTO) throws Exception {
            return boardMapper.request_VactDispose(vact_disposeDTO);
        }

        //휴가처리 미승인
        @Override
        public List<Vact_disposeDTO> process_VactDispose(Vact_disposeDTO vact_disposeDTO) throws Exception {
            return boardMapper.process_VactDispose(vact_disposeDTO);
        }

        //휴가처리 디테일 모달창
        @Override
        public List<Vact_disposeDTO> modaldetailVactlist(Vact_disposeDTO vact_disposeDTO) throws Exception {
            return boardMapper.modaldetailVactlist(vact_disposeDTO);
        }

        //휴가처리 - 휴가항목 모달창
        @Override
        public List<Vact_CategoryDTO> modalVactCategory(Vact_CategoryDTO vact_categoryDTO) throws Exception {
            return boardMapper.modalVactCategory(vact_categoryDTO);
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

        //보유휴가 현황 모달창
        @Override
        public List<Vact_disposeDTO> modalVactlist(Vact_disposeDTO vactDisposeDTO) throws Exception {
            return boardMapper.modalVactlist(vactDisposeDTO);
        }

        //보유휴가 현황 (update)
        @Override
        public int updateVactlist(EmpDTO empDTO) throws Exception {
            return boardMapper.updateVactlist(empDTO);
        }

        //보유휴가 현황 ( 잔여휴가 업데이트 )
        @Override
        public int updateRemindVact(Vact_disposeDTO vact_disposeDTO) throws Exception {
            return boardMapper.updateRemindVact(vact_disposeDTO);
        }


        //출퇴근관리 (모바일 출근요청)
        @Override
        public int createinOut_info(InOut_infoDTO inOut_infoDTO) throws Exception {
            return boardMapper.createinOut_info(inOut_infoDTO);
        }

        //출퇴근관리 (모바일 퇴근요청)
        @Override
        public int createEnd_inOutInfo(InOut_infoDTO inOut_infoDTO) throws Exception {
            return boardMapper.createEnd_inOutInfo(inOut_infoDTO);
        }

        //출퇴근관리 모바일 raed
        @Override
        public List<InOut_infoDTO> readMb_inOutInfo(InOut_infoDTO inOut_infoDTO) throws Exception {
            return boardMapper.readMb_inOutInfo(inOut_infoDTO);
        }

        //출퇴근관리 초과근무시간 update
        @Override
        public int OvertimeUpdate(InOut_infoDTO inOut_infoDTO) throws Exception {
            return boardMapper.OvertimeUpdate(inOut_infoDTO);
        }

        @Override
        public int OvertimeNightUpdate(InOut_infoDTO inOut_infoDTO) throws Exception {
            return boardMapper.OvertimeNightUpdate(inOut_infoDTO);
        }

        @Override
        public List<InOut_infoDTO> getInOut_infoTime(InOut_infoDTO inOut_infoDTO) throws Exception {
            return boardMapper.getInOut_infoTime(inOut_infoDTO);
        }

        //출퇴근관리 create
        @Override
        public int createAdd_inOutInfo(InOut_infoDTO inOut_infoDTO) throws Exception {
            return boardMapper.createAdd_inOutInfo(inOut_infoDTO);
        }

        //출퇴근관리 (read)
        @Override
        public List<InOut_infoDTO> readinout_info(InOut_infoDTO inOut_infoDTO) throws Exception {
            return boardMapper.readinout_info(inOut_infoDTO);
        }

        //출퇴근관리 모달창
        @Override
        public List<InOut_infoDTO> modal_inOutInfo(InOut_infoDTO inOut_infoDTO) throws Exception {
            return boardMapper.modal_inOutInfo(inOut_infoDTO);
        }

        //출퇴근관리 (update)
        @Override
        public int updateinOut_info(InOut_infoDTO inOut_infoDTO) throws Exception {
            return boardMapper.updateinOut_info(inOut_infoDTO);
        }

        //출퇴근관리 (delete)
        @Override
        public int deleteinOut_info(InOut_infoDTO inOutListId) throws Exception {
            return boardMapper.deleteinOut_info(inOutListId);
        }

        //출퇴근현황 ( 검색 이름+날짜 )
        @Override
        public List<InOut_infoDTO> searchInout(InOut_infoDTO inOut_infoDTO) throws Exception {
            return boardMapper.searchInout(inOut_infoDTO);
        }

        //출퇴근현황 ( 검색 이름만 )
        @Override
        public List<InOut_infoDTO> searchInoutName(InOut_infoDTO inOut_infoDTO) throws Exception {
            return boardMapper.searchInoutName(inOut_infoDTO);
        }

        //출퇴근현황 ( 검색 날짜만 )
        @Override
        public List<InOut_infoDTO> searchInoutDate(InOut_infoDTO inOut_infoDTO) throws Exception {
            return boardMapper.searchInoutDate(inOut_infoDTO);
        }

        //출퇴근현황 시작날짜 + 이름검색
        @Override
        public List<InOut_infoDTO> searchInoutstartDate(InOut_infoDTO inOut_infoDTO) throws Exception {
            return boardMapper.searchInoutstartDate(inOut_infoDTO);
        }

        @Override
        public List<InOut_infoDTO> searchInoutlastDate(InOut_infoDTO inOut_infoDTO) throws Exception {
            return boardMapper.searchInoutlastDate(inOut_infoDTO);
        }

        // 종료날짜만 검색
        @Override
        public List<InOut_infoDTO> searchInoutendDate(InOut_infoDTO inOut_infoDTO) throws Exception {
            return boardMapper.searchInoutendDate(inOut_infoDTO);
        }

        //종료날짜 + 이름 검색
        @Override
        public List<InOut_infoDTO> searchInoutEndNameDate(InOut_infoDTO inOut_infoDTO) throws Exception {
            return boardMapper.searchInoutEndNameDate(inOut_infoDTO);
        }

    //-------------------------------- < 일용직관리[일용직등록] > ----------------------------------
    // CRUD (create, read, update, delete)
    // 일용직관리[일용직등록] - 일용직정보 중복 처리
    @Override
    public int getDailyCode(DailyEmpDTO dailyEmpDTO) throws Exception{
        return boardMapper.getDailyCode(dailyEmpDTO);
    }

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

    // 일용직관리[일용직등록] - 일용직정보 수정 중복 확인
    @Override
    public int getUpdateDailyCode(DailyEmpDTO dailyEmpDTO) throws Exception{
        return boardMapper.getUpdateDailyCode(dailyEmpDTO);
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
    // 일용직관리[일용직 수당 등록] - 수당 중복 처리
    @Override
    public int getDPayCode(DailyEmpPayDTO dailyEmpPayDTO) throws Exception{
        return boardMapper.getDPayCode(dailyEmpPayDTO);
    }

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

    // 일용직관리[일용직 수당 등록] - 일용직 비과세 정보 가져오기
    @Override
    public ArrayList<DailyTaxFreeDTO> readDailyTaxFree(DailyTaxFreeDTO dailyTaxFreeDTO) throws Exception{
        return boardMapper.readDailyTaxFree(dailyTaxFreeDTO);
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
    // 일용직관리[출퇴근관리] - 일용직 출퇴근정보 중복처리
    @Override
    public int getInOutDCode(DailyInOutDTO dailyInOutDTO) throws Exception{
        return boardMapper.getInOutDCode(dailyInOutDTO);
    }

    // 일용직관리[출퇴근관리] - 일용직 정보 가져오기(read)
    @Override
    public ArrayList<DailyEmpDTO> searchDailyEmp(DailyEmpDTO dailyEmpDTO) throws Exception{
        return boardMapper.searchDailyEmp(dailyEmpDTO);
    }

    // ------------------------
    // 일용직관리[출퇴근관리] - 일용직 출퇴근정보 등록(create)
    @Override
    public int createDailyInOut(DailyInOutDTO dailyInOutDTO) throws Exception{
        return boardMapper.createDailyInOut(dailyInOutDTO);
    }

    // 일용직관리[출퇴근관리] - 일용직 출퇴근정보 불러오기(read)
    @Override
    public ArrayList<DailyInOutDTO> readDailyInOut(DailyInOutDTO dailyInOutDTO) throws Exception{
        return boardMapper.readDailyInOut(dailyInOutDTO);
    }

    // 일용직관리[출퇴근관리] - 일용직 출퇴근정보 수정(update)
    @Override
    public int updateDailyInOut(DailyInOutDTO dailyInOutDTO) throws  Exception{
        return boardMapper.updateDailyInOut(dailyInOutDTO);
    }

    // 일용직관리[출퇴근관리] - 일용직 출퇴근정보 모달(modal)
    @Override
    public ArrayList<DailyInOutDTO> updateDailyInOutModal(DailyInOutDTO dailyInOutDTO) throws Exception{
        return boardMapper.updateDailyInOutModal(dailyInOutDTO);
    }

    // 일용직관리[출퇴근관리] - 일용직 출퇴근정보 삭제(delete)
    @Override
    public int deleteDailyInOut(DailyInOutDTO dailyInOutDTO) throws Exception{
        return boardMapper.deleteDailyInOut(dailyInOutDTO);
    }

    // 일용직관리[출퇴근관리] - 초과근무시간 update
    @Override
    public int updateOverTime(DailyInOutDTO dailyInOutDTO) throws Exception{
        return boardMapper.updateOverTime(dailyInOutDTO);
    }

    // 일용직관리[출퇴근관리] - 야간근무시간 update
    @Override
    public int updateNightTime(DailyInOutDTO dailyInOutDTO) throws Exception{
        return boardMapper.updateNightTime(dailyInOutDTO);
    }

    //-------------------------------- < 일용직관리[출퇴근현황] > ----------------------------------
    // [ 날짜만 선택한 경우 ]
    @Override
    public ArrayList<DailyInOutDTO> searchDailyInOut1(DailyInOutDTO dailyInOutDTO) throws Exception{
        return boardMapper.searchDailyInOut1(dailyInOutDTO);
    }

    // [ 날짜랑 이름 둘 다 선택한 경우 ]
    @Override
    public ArrayList<DailyInOutDTO> searchDailyInOut2(DailyInOutDTO dailyInOutDTO) throws Exception{
        return boardMapper.searchDailyInOut2(dailyInOutDTO);
    }

    // [ 이름만 선택한 경우 ]
    @Override
    public ArrayList<DailyInOutDTO> searchDailyInOut3(DailyInOutDTO dailyInOutDTO) throws Exception{
        return boardMapper.searchDailyInOut3(dailyInOutDTO);
    }


    // [ startDate만 입력한 경우 ]
    @Override
    public ArrayList<DailyInOutDTO> searchDailyInOut4(DailyInOutDTO dailyInOutDTO) throws Exception{
        return boardMapper.searchDailyInOut4(dailyInOutDTO);
    }

    // [ startDate와 이름만 입력한 경우 ]
    @Override
    public ArrayList<DailyInOutDTO> searchDailyInOut5(DailyInOutDTO dailyInOutDTO) throws Exception{
        return boardMapper.searchDailyInOut5(dailyInOutDTO);
    }

    // [ endDate 값만 입력한 경우 ]
    @Override
    public ArrayList<DailyInOutDTO> searchDailyInOut6(DailyInOutDTO dailyInOutDTO) throws Exception{
        return boardMapper.searchDailyInOut6(dailyInOutDTO);
    }

    // [ endDate 값과 이름을 입력한 경우 ]
    @Override
    public ArrayList<DailyInOutDTO> searchDailyInOut7(DailyInOutDTO dailyInOutDTO) throws Exception{
        return boardMapper.searchDailyInOut7(dailyInOutDTO);
    }

//
//    // 얘는 뭐쓸라고 했드라..?
//    @Override
//    public EmpDTO readEmpSalay(EmpDTO empDTO) throws Exception {
//        return null;
//    }
//-------------------------------- < 급여관리 > ----------------------------------
//기본급여
@Override
public int getbaseWage(EmpDTO empDTO) throws Exception {
    return boardMapper.getbaseWage(empDTO);
}
    //부양가족수
    @Override
    public Integer getfamilyNum(EmpDTO empDTO) throws Exception {
        return boardMapper.getfamilyNum(empDTO);
    }
    //소득세
    @Override
    public List<SimpleTaxTableDTO> getincomeTax(EmpDTO empDTO) throws Exception {
        return boardMapper.getincomeTax(empDTO);
    }
    //야간근로수당 변동(시간)
    @Override
    public String getinOutnight(EmpDTO empDTO) throws Exception {
        return boardMapper.getinOutnight(empDTO);
    }
    //야간근로수당 변동(일)
    @Override
    public String getinOutnightday(EmpDTO empDTO) throws Exception {
        return boardMapper.getinOutnightday(empDTO);
    }
    //연장근로수당 변동(시간)
    @Override
    public String getinOutOver(EmpDTO empDTO) throws Exception {
        return boardMapper.getinOutOver(empDTO);
    }
    //연장근로수당 변동(일)
    @Override
    public String getinOutOverDayTax(EmpDTO empDTO) throws Exception {
        return boardMapper.getinOutOverDayTax(empDTO);
    }
    // 급여관리 - 원하는 사용자의 출퇴근 정보
    @Override
    public ArrayList<InOut_infoDTO> readOverInOutInfo(InOut_infoDTO inOut_infoDTO) throws Exception{
        return boardMapper.readOverInOutInfo(inOut_infoDTO);
    }

//    @Override
//    public EmpDTO readEmpSalay(EmpDTO empDTO) throws Exception {
//        return boardMapper.readEmpSalay(empDTO);
//    }

    // [ 야간근로시간 정보 가져오기 ]
    @Override
    public ArrayList<InOut_infoDTO> readNightTime(InOut_infoDTO inOut_infoDTO) throws Exception{
        return boardMapper.readNightTime(inOut_infoDTO);
    }

    // [ 야간근로수당 계산식 가져오기 ]
    @Override
    public ArrayList<String> getNightCalc(Emp_payDTO emp_payDTO) throws Exception{
        return boardMapper.getNightCalc(emp_payDTO);
    }


    //-------------------------------- < 급여관리[임원직]> ----------------------------------
    // 급여관리[임원직] - 급여 중복처리
    @Override
    public int duplicateCheck(Salary_statementDTO salary_statementDTO) throws Exception{
        return boardMapper.duplicateCheck(salary_statementDTO);
    }

    // 급여관리[임원직] - 급여 명세서 추가(create)
    @Override
    public int createSalary(Salary_statementDTO salary_statementDTO) throws Exception{
        return boardMapper.createSalary(salary_statementDTO);
    }

    // 급여관리[임원직] - 원하는 사원 정보 가져오기(read)
    @Override
    public EmpDTO getEmpInfo(EmpDTO empDTO) throws Exception{
        return boardMapper.getEmpInfo(empDTO);
    }

    // 급여관리 - [메인]급여 명세서 불러오기(임원직)(Read)
    @Override
    public ArrayList<Salary_statementDTO> readMainSalary(Salary_statementDTO salary_statementDTO) throws Exception{
        return boardMapper.readMainSalary(salary_statementDTO);
    }

    // 급여관리 - [모달]급여 명세서 불러오기(임원직)(Read)
    @Override
    public ArrayList<Salary_statementDTO> readModalSalary(Salary_statementDTO salary_statementDTO) throws Exception{
        return boardMapper.readModalSalary(salary_statementDTO);
    }

    // 급여관리 - 급여 명세서 수정 중복확인
    @Override
    public int duplicateCheckUpdate(Salary_statementDTO salary_statementDTO) throws Exception{
        return boardMapper.duplicateCheckUpdate(salary_statementDTO);
    }

    // 급여관리 - 급여 명세서 수정(임원직)(Update)
    @Override
    public int updateSalary(Salary_statementDTO salary_statementDTO) throws Exception{
        return boardMapper.updateSalary(salary_statementDTO);
    }


    // 급여관리 - 급여 명세서 삭제(임원직)(Delete)
    @Override
    public int deleteSalary(Salary_statementDTO salary_statementDTO) throws Exception{
        return boardMapper.deleteSalary(salary_statementDTO);
    }


    // 급여관리 - 수당 계산식 불러오기(read)
    @Override
    public String getCalc(Emp_payDTO emp_payDTO) throws Exception{
        return boardMapper.getCalc(emp_payDTO);
    }


    // 급여관리 - 출력 버튼(read)
    @Override
    public  Salary_statementDTO getSalaryList(String salaryId) throws Exception{
        return boardMapper.getSalaryList(salaryId);
    }


    // 급여관리 - 경비 값 가져오기(read)
    @Override
    public int getExpense(EmpDTO empDTO) throws Exception{
        return boardMapper.getExpense(empDTO);
    }

    //-------------------------------- < 급여관리[일용직]> ----------------------------------
    //기본급여
    @Override
    public int getbaseWage2(DailySalaryStatementDTO dailySalaryStatementDTO) throws Exception {
        return boardMapper.getbaseWage2(dailySalaryStatementDTO);
    }
    //부양가족수
    @Override
    public Integer getfamilyNum2(DailySalaryStatementDTO dailySalaryStatementDTO) throws Exception {
        return boardMapper.getfamilyNum2(dailySalaryStatementDTO);
    }
    //소득세
    @Override
    public List<SimpleTaxTableDTO> getincomeTax2(DailySalaryStatementDTO dailySalaryStatementDTO) throws Exception {
        return boardMapper.getincomeTax2(dailySalaryStatementDTO);
    }
    //야간근로수당 변동(시간)
    @Override
    public String getinOutnight2(DailySalaryStatementDTO dailySalaryStatementDTO) throws Exception {
        return boardMapper.getinOutnight2(dailySalaryStatementDTO);
    }
    //야간근로수당 변동(일)
    @Override
    public String getinOutnightday2(DailySalaryStatementDTO dailySalaryStatementDTO) throws Exception {
        return boardMapper.getinOutnightday2(dailySalaryStatementDTO);
    }
    //연장근로수당 변동(시간)
    @Override
    public String getinOutOver2(DailySalaryStatementDTO dailySalaryStatementDTO) throws Exception {
        return boardMapper.getinOutOver2(dailySalaryStatementDTO);
    }
    //연장근로수당 변동(일)
    @Override
    public String getinOutOverDayTax2(DailySalaryStatementDTO dailySalaryStatementDTO) throws Exception {
        return boardMapper.getinOutOverDayTax2(dailySalaryStatementDTO);
    }
    // 급여관리 - 원하는 사용자의 출퇴근 정보
    @Override
    public ArrayList<InOut_infoDTO> readOverInOutInfo2(InOut_infoDTO inOut_infoDTO) throws Exception{
        return boardMapper.readOverInOutInfo2(inOut_infoDTO);
    }

    @Override
    public EmpDTO readEmpSalay2(EmpDTO empDTO) throws Exception {
        return null;
    }

    // [ 야간근로시간 정보 가져오기 ]
    @Override
    public ArrayList<InOut_infoDTO> readNightTime2(InOut_infoDTO inOut_infoDTO) throws Exception{
        return boardMapper.readNightTime2(inOut_infoDTO);
    }

    // [ 야간근로수당 계산식 가져오기 ]
    @Override
    public ArrayList<String> getNightCalc2(Emp_payDTO emp_payDTO) throws Exception{
        return boardMapper.getNightCalc2(emp_payDTO);
    }

    // --------------------------------------------------
    // 급여관리[일용직] - 급여 중복처리
    @Override
    public int duplicateCheck2(Salary_statementDTO salary_statementDTO) throws Exception{
        return boardMapper.duplicateCheck(salary_statementDTO);
    }

    // 급여관리[일용직] - 급여 명세서 추가(create)
    @Override
    public int createDailySalary(DailySalaryStatementDTO dailySalaryStatementDTO) throws Exception{
        return boardMapper.createDailySalary(dailySalaryStatementDTO);
    }

    // 급여관리[일용직] - 원하는 사원 정보 가져오기(read)
    @Override
    public EmpDTO getEmpInfo2(EmpDTO empDTO) throws Exception{
        return boardMapper.getEmpInfo(empDTO);
    }

    // 급여관리 - [메인]급여 명세서 불러오기(일용직)(Read)
    @Override
    public ArrayList<DailySalaryStatementDTO> readDailyMainSalary(DailySalaryStatementDTO dailySalaryStatementDTO) throws Exception{
        return boardMapper.readDailyMainSalary(dailySalaryStatementDTO);
    }

    // 급여관리 - [모달]급여 명세서 불러오기(일용직)(Read)
    @Override
    public ArrayList<DailySalaryStatementDTO> readDailyModalSalary(DailySalaryStatementDTO dailySalaryStatementDTO) throws Exception{
        return boardMapper.readDailyModalSalary(dailySalaryStatementDTO);
    }

    // 급여관리 - 급여 명세서 수정 중복확인(일용직)
    @Override
    public int duplicateCheckUpdate2(Salary_statementDTO salary_statementDTO) throws Exception{
        return boardMapper.duplicateCheckUpdate(salary_statementDTO);
    }

    // 급여관리 - 급여 명세서 수정(일용직)(Update)
    @Override
    public int updateSalary2(Salary_statementDTO salary_statementDTO) throws Exception{
        return boardMapper.updateSalary(salary_statementDTO);
    }


    // 급여관리 - 급여 명세서 삭제(일용직)(Delete)
    @Override
    public int deleteDailySalary(DailySalaryStatementDTO dailySalaryStatementDTO) throws Exception{
        return boardMapper.deleteDailySalary(dailySalaryStatementDTO);
    }


    // 급여관리 - 수당 계산식 불러오기(read)(일용직)
    @Override
    public String getCalc2(Emp_payDTO emp_payDTO) throws Exception{
        return boardMapper.getCalc(emp_payDTO);
    }


    // 급여관리 - 출력 버튼(read)(일용직)
    @Override
    public DailySalaryStatementDTO getSalaryList2(String dailySalaryId) throws Exception{
        return boardMapper.getSalaryList2(dailySalaryId);
    }


    // 급여관리 - 경비 값 가져오기(read)
    @Override
    public int getExpense2(DailySalaryStatementDTO dailySalaryStatementDTO) throws Exception{
        return boardMapper.getExpense2(dailySalaryStatementDTO);
    }




        //경비처리 create
        @Override
        public int expenseCreate(Expense_infoDTO expense_infoDTO) throws Exception {
            return boardMapper.expenseCreate(expense_infoDTO);
        }

        //경비관리 read
        @Override
        public List<Expense_infoDTO> expenseRead(Expense_infoDTO expense_infoDTO) throws Exception {
            return boardMapper.expenseRead(expense_infoDTO);
        }

        //경비관리 update
        @Override
        public int expenseUpdate(Expense_infoDTO expense_infoDTO) throws Exception {
            return boardMapper.expenseUpdate(expense_infoDTO);
        }

        //경비관리 delete
        @Override
        public int expenseDelete(Expense_infoDTO expense_infoDTO) throws Exception {
            return boardMapper.expenseDelete(expense_infoDTO);
        }

        //경비처리 모달창
        @Override
        public List<Expense_infoDTO> expenseModal(Expense_infoDTO expense_infoDTO) throws Exception {
            return boardMapper.expenseModal(expense_infoDTO);
        }

        //경비처리 둘다 검색
        @Override
        public List<Expense_infoDTO> expenseSearch(Expense_infoDTO expense_infoDTO) throws Exception {
            return boardMapper.expenseSearch(expense_infoDTO);
        }

        //경비처리 이름만검색
        @Override
        public List<Expense_infoDTO> expenseName(Expense_infoDTO expense_infoDTO) throws Exception {
            return boardMapper.expenseName(expense_infoDTO);
        }

        //경비처리 날짜만검색
        @Override
        public List<Expense_infoDTO> expensedate(Expense_infoDTO expense_infoDTO) throws Exception {
            return boardMapper.expensedate(expense_infoDTO);
        }

        //경비처리 시작날짜 + 이름 검색
        @Override
        public List<Expense_infoDTO> expenseStartDateName(Expense_infoDTO expense_infoDTO) throws Exception {
            return boardMapper.expenseStartDateName(expense_infoDTO);
        }

        //경비처리 시작날짜만 검색
        @Override
        public List<Expense_infoDTO> expenseStartDate(Expense_infoDTO expense_infoDTO) throws Exception {
            return boardMapper.expenseStartDate(expense_infoDTO);
        }

        //경비처리 종료날짜만 검색
        @Override
        public List<Expense_infoDTO> expenseEndDate(Expense_infoDTO expense_infoDTO) throws Exception {
            return boardMapper.expenseEndDate(expense_infoDTO);
        }

        //경비처리 종료날짜 + 이름
        @Override
        public List<Expense_infoDTO> expenseEndDateName(Expense_infoDTO expense_infoDTO) throws Exception {
            return boardMapper.expenseEndDateName(expense_infoDTO);
        }

        // ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 경리/회계 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
        //거래처관리 ( create )
        @Override
        public int clientCreate(Client_infoDTO client_infoDTO) throws Exception {
            return boardMapper.clientCreate(client_infoDTO);
        }

        //거래처 관리 (read)
        @Override
        public List<Client_infoDTO> clientRead(Client_infoDTO client_infoDTO) throws Exception {
            return boardMapper.clientRead(client_infoDTO);
        }

        //거래처관리 (update)
        @Override
        public int clientUpdate(Client_infoDTO client_infoDTO) throws Exception {
            return boardMapper.clientUpdate(client_infoDTO);
        }

        //거래처 관리 (delete)
        @Override
        public int clientDelete(Client_infoDTO client_infoDTO) throws Exception {
            return boardMapper.clientDelete(client_infoDTO);
        }

        //거래처관리 모달창
        @Override
        public List<Client_infoDTO> clientModal(Client_infoDTO client_infoDTO) throws Exception {
            return boardMapper.clientModal(client_infoDTO);
        }

        //매입관리 create
        @Override
        public int income_Create(IncomeDTO income_outcomeDTO) throws Exception {
            return boardMapper.income_Create(income_outcomeDTO);
        }

        //매입관리 read
        @Override
        public List<IncomeDTO> income_Read(IncomeDTO income_outcomeDTO) throws Exception {
            return boardMapper.income_Read(income_outcomeDTO);
        }
        //매입관리 합계금액
        @Override
        public List<IncomeDTO> income_total(IncomeDTO incomeDTO) throws Exception {
            return boardMapper.income_total(incomeDTO);
        }
        //매입관리 합계금액
        @Override
        public List<IncomeDTO> income_total1(IncomeDTO incomeDTO) throws Exception {
            return boardMapper.income_total1(incomeDTO);
        }


        //매입관리 상세페이지
        @Override
        public List<IncomeDTO> income_Modal(IncomeDTO incomeDTO) throws Exception {
            return boardMapper.income_Modal(incomeDTO);
        }


        //매입관리 update
        @Override
        public int income_Update(IncomeDTO income_outcomeDTO) throws Exception {
            return boardMapper.income_Update(income_outcomeDTO);
        }

        //매입관리 delete
        @Override
        public int income_Delete(IncomeDTO income_outcomeDTO) throws Exception {
            return boardMapper.income_Delete(income_outcomeDTO);
        }

        //매입관리 search
        @Override
        public List<IncomeDTO> income_Search(IncomeDTO incomeDTO) throws Exception {
            return boardMapper.income_Search(incomeDTO);
        }
        //매입매출  search 합계금액
        @Override
        public List<IncomeDTO> income_SearchTotal(IncomeDTO incomeDTO) throws Exception {
            return boardMapper.income_SearchTotal(incomeDTO);
        }

        //매입+매출 검색
        @Override
        public List<IncomeDTO> income_checkALL(IncomeDTO incomeDTO) throws Exception {
            return boardMapper.income_checkALL(incomeDTO);
        }

        //매입검색
        @Override
        public List<IncomeDTO> income_income(IncomeDTO incomeDTO) throws Exception {
            return boardMapper.income_income(incomeDTO);
        }
        //매입검색 합계금액
        @Override
        public List<IncomeDTO> income_incomeTotal(IncomeDTO incomeDTO) throws Exception {
            return boardMapper.income_incomeTotal(incomeDTO);
        }

        //매출검색
        @Override
        public List<IncomeDTO> income_outcome(IncomeDTO incomeDTO) throws Exception {
            return boardMapper.income_outcome(incomeDTO);
        }
        //매출검색 합계금액
        @Override
        public List<IncomeDTO> income_outcomeTotal(IncomeDTO incomeDTO) throws Exception {
            return boardMapper.income_outcomeTotal(incomeDTO);
        }

        //매입관리 공급처명만 검색
        @Override
        public List<IncomeDTO> income_Name(IncomeDTO incomeDTO) throws Exception {
            return boardMapper.income_Name(incomeDTO);
        }
        //매입매출 공급처검색 합계금액
        @Override
        public List<IncomeDTO> income_NameTotal(IncomeDTO incomeDTO) throws Exception {
            return boardMapper.income_NameTotal(incomeDTO);
        }

        //매입관리 날짜만 검색
        @Override
        public List<IncomeDTO> income_Date(IncomeDTO incomeDTO) throws Exception {
            return boardMapper.income_Date(incomeDTO);
        }
        //매입매출 날짜검색 합계금액
        @Override
        public List<IncomeDTO> income_DateTotal(IncomeDTO incomeDTO) throws Exception {
            return boardMapper.income_DateTotal(incomeDTO);
        }

        //매입관리 시작날짜 + 공급처명 검색
        @Override
        public List<IncomeDTO> income_StartDateName(IncomeDTO incomeDTO) throws Exception {
            return boardMapper.income_StartDateName(incomeDTO);
        }
        //매입매출 시작날짜+공급처명 합계금액
        @Override
        public List<IncomeDTO> income_StartDateNameTotal(IncomeDTO incomeDTO) throws Exception {
            return boardMapper.income_StartDateNameTotal(incomeDTO);
        }

        //매입관리 시작날짜만 검색
        @Override
        public List<IncomeDTO> income_StartDate(IncomeDTO incomeDTO) throws Exception {
            return boardMapper.income_StartDate(incomeDTO);
        }
        //매입매출 시작날짜검색 합계금액
        @Override
        public List<IncomeDTO> income_StartDateTotal(IncomeDTO incomeDTO) throws Exception {
            return boardMapper.income_StartDateTotal(incomeDTO);
        }

        //매입관리 종료날짜만 검색
        @Override
        public List<IncomeDTO> income_EndDate(IncomeDTO incomeDTO) throws Exception {
            return boardMapper.income_EndDate(incomeDTO);
        }
        //매입매출 종료날짜 합계금액
        @Override
        public List<IncomeDTO> income_EndDateTotal(IncomeDTO incomeDTO) throws Exception {
            return boardMapper.income_EndDateTotal(incomeDTO);
        }

        //매입관리 종료날짜 이름 검색
        @Override
        public List<IncomeDTO> income_EndDateName(IncomeDTO incomeDTO) throws Exception {
            return boardMapper.income_EndDateName(incomeDTO);
        }
        //매입매출 종료날짜 + 공급처명 합계금액
        @Override
        public List<IncomeDTO> income_EndDateNameTotal(IncomeDTO incomeDTO) throws Exception {
            return boardMapper.income_EndDateNameTotal(incomeDTO);
        }

        //주소 -> 위경도 변환
        //지오코딩 카카오 주소 API
        @Override
        public HashMap<String, String> getAddress(String address) throws Exception {
            //주소안에 띄어쓰기때문에 400에러가 나는것을 해결

            address = URLEncoder.encode(address, "UTF-8");

            String url = "https://dapi.kakao.com/v2/local/search/address.json?query=" + address;

            String jsonString = new String();

            String buf;

            URL Url = new URL(url);

            HttpsURLConnection conn = (HttpsURLConnection) Url.openConnection();
            String auth = "KakaoAK " + "f4c00857124a94959e789be2b9b6c882";
            conn.setRequestMethod("GET");
            conn.setRequestProperty("X-Requested-With", "curl");
            conn.setRequestProperty("Authorization", auth);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            while ((buf = br.readLine()) != null) {
                jsonString += buf;
            }
            JSONParser paser = new JSONParser();

            JSONObject J = (JSONObject) paser.parse(jsonString);
            JSONObject meta = (JSONObject) J.get("meta");

            JSONArray data = (JSONArray) J.get("documents");
            long size = (long) meta.get("total_count");
            System.out.println("size확인 :: " + size);

            if (size > 0) {
                HashMap<String,String> map = new HashMap<String,String>();
                JSONObject jsonX = (JSONObject) data.get(0);
                System.out.println(jsonX.get("x").toString());
                System.out.println(jsonX.get("y").toString());
                map.put("x",jsonX.get("x").toString());
                map.put("y",jsonX.get("y").toString());
                return map;
            }
            return null;
        }
        //매입매출 파일,데이터 create
        @Override
        public int taxfile_create(IncomeDTO incomeDTO) throws Exception {
            return boardMapper.taxfile_create(incomeDTO);
        }
        //매입매출 카운트
        @Override
        public int getCount(IncomeDTO incomeDTO) throws Exception {
            return boardMapper.getCount(incomeDTO);
        }
        //거래상세페이지
        @Override
        public List<IncomeDTO> clientdetail(Client_infoDTO client_infoDTO) throws Exception {
            return boardMapper.clientdetail(client_infoDTO);
        }
        //세금계산서 거래내역 내보내기
        @Override
        public TaxPaperDTO getTransaction(int purchaseId) throws Exception {
            return boardMapper.getTransaction(purchaseId);
        }
        //세금계산서 거레처내역
        @Override
        public List<String> getClientInfo(int purchaseId) throws Exception {
            return boardMapper.getClientInfo(purchaseId);
        }
    //세금계산서 거레처내역 정보 내보내기
        @Override
        public List<TaxPaperDTO> getClientInfo1(String p_clientCompNum) throws Exception {
            return boardMapper.getClientInfo1(p_clientCompNum);
        }
        //우리회사정보 내보내기
        @Override
        public List<TaxPaperDTO> getCompanyinfo(String compCode) throws Exception {
            return boardMapper.getCompanyinfo(compCode);
        }
        //세금계산서 합계금액 가져오가
        @Override
        public List<TaxPaperDTO> getTotalPay(int purchaseId) throws Exception {
            return boardMapper.getTotalPay(purchaseId);
        }


    // ------------------------
        // 일용직관리[출퇴근관리] - 일용직 출퇴근정보 등록(create)

        // 일용직관리[출퇴근관리] - 일용직 출퇴근정보 불러오기(read)

        // 일용직관리[출퇴근관리] - 일용직 출퇴근정보 수정(update)

        // 일용직관리[출퇴근관리] - 일용직 출퇴근정보 모달(modal)

        // 일용직관리[출퇴근관리] - 일용직 출퇴근정보 삭제(delete)

}