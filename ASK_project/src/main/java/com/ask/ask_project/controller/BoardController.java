package com.ask.ask_project.controller;


import com.ask.ask_project.DTO.*;
import com.ask.ask_project.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 로그인
    @RequestMapping("login")
    public boolean login(@RequestBody UserDTO userDTO) {
        Emp_payDTO emp_payDTO = new Emp_payDTO();
        System.out.println("================================================");
        System.out.println("[ 로그인 ] 들어온 userDTO값: " + userDTO);
        System.out.println("================================================");
        int loginCheck = 0;
        try {
            loginCheck = boardService.loginCheck(userDTO);
            System.out.println("[ 로그인 ] 결과값 loginCheck : " + loginCheck);
            if (loginCheck == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 마스터 id 값 가져오기
    @RequestMapping("getMaster")
    public String getMaster(@RequestBody UserDTO userDTO){
        System.out.println("================================================");
        System.out.println("[ getMaster ] 들어온 userDTO값: " + userDTO);
        System.out.println("================================================");
        try{
            String userId = boardService.getMaster(userDTO);
            System.out.println("[ getMaster ] 결과값 userId : " + userId);
            return userId;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    // 회원가입
    @RequestMapping("joinProcess")
    public boolean joinProcess(@RequestBody MemberDTO memberDTO){
        System.out.println("[ 회원가입 ] 들어온 data : " + memberDTO);
        try{
            // 관리자 계정 insert
            int check = boardService.insert_memberInfo(memberDTO);
            System.out.println("[ 회원가입 ] 결과값 check : " + check);
            // 사용자 관리 insert
            int check2 = boardService.insert_userInfo(memberDTO);
            System.out.println("[ 회원가입 ] 결과값 check2 : " + check2);
            if(check == 1 && check2 == 1){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // 회원가입 - 아이디 중복 체크
    @RequestMapping("checkId")
    public boolean checkId(@RequestBody MemberDTO memberDTO) {
        int checkNum = -1;
        System.out.println("================================================");
        System.out.println("[ 회원가입 - id중복체크 ] 들어온 memberDTO값 : " + memberDTO);
        System.out.println("================================================");
        try {
            checkNum = boardService.checkId(memberDTO);
            System.out.println("[ 회원가입 - id중복체크 ] 결과값 checkNum 값 : " + checkNum);
            if (checkNum == 0) {
                return true;
            } else {

                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    //-------------------------------- < 사용자관리 > ----------------------------------
    // 사용자관리 (create)
    @RequestMapping("createUser")
    public boolean createUser(@RequestBody UserDTO userDTO) {
        System.out.println("================================================");
        System.out.println("[ 사용자관리(create) ] 들어온 userDTO 값 : " + userDTO);
        System.out.println("================================================");
        try {
            int check = boardService.createUser(userDTO);
            System.out.println("[ 사용자관리(create) ] 결과값 check 값 : " + check);
            if (check == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //사용자관리 (read)
    @RequestMapping("readUser")
    public List<UserDTO> readUser(@RequestBody UserDTO userDTO) {
        System.out.println("================================================");
        System.out.println("[ 사용자관리(Read) ] 들어온 userDTO값: " + userDTO);
        System.out.println("================================================");
        try {
            List<UserDTO> userList = boardService.readUser(userDTO);
            System.out.println("[ 사용자관리(Read) ] 결과값 userList 값 : " + userList);
            if (userList != null) {
                return userList;
            }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //사용자관리 (update)
    @RequestMapping("updateUser")
    public boolean updateUser(@RequestBody UserDTO userDTO){
        System.out.println("================================================");
        System.out.println("[ 사용자관리 (update) ] 들어온 userDTO 값: " + userDTO);
        System.out.println("================================================");
        try{
            int check = boardService.updateUser(userDTO);
            System.out.println("[ 사용자관리 (update) ] 결과값 check 값 : " + check);
            if(check > 0){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // 수정 눌러서 해당 id의 update창만 보기
    @RequestMapping("updateUserModal")
    public List<UserDTO> updateUserModal(@RequestBody UserDTO userDTO){
        System.out.println("================================================");
        System.out.println("[ 사용자관리 (updateUserModal) ] 들어온 userDTO 값: " + userDTO);
        System.out.println("================================================");
        try {
            List<UserDTO> userList = boardService.updateUserModal(userDTO);
            System.out.println("[ 사용자관리 (updateUserModal) ] 결과값 userList 값 : " + userList);
            if (userList != null) {
                return userList;
            }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    //사용자관리 (delete)
    @RequestMapping("deleteUser")
    public boolean deleteUser(@RequestBody UserDTO userDTO){
        System.out.println("================================================");
        System.out.println("[ 사용자관리(delete) ] 들어온 userDTO 값: " + userDTO);
        System.out.println("================================================");
        try {
            String[] list = userDTO.getDeleteList();
            int check = 0;
            for(int i=0; i<list.length; i++){
                check = boardService.deleteUser(list[i]);
                System.out.println("[ 사용자관리(delete) ] 결과값 check 값 : " + check);
                if(check == 0){
                    return false;
                }
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //-------------------------------- < 모바일관리 > ----------------------------------
    // CRUD (create, read, update, delete)
    // 모바일관리 - 모바일계정 정보 등록(create)
    @RequestMapping("createMobile")
    public boolean createMobile(@RequestBody MbUserDTO mbUserDTO) {
        System.out.println("================================================");
        System.out.println("[ 모바일관리 - 모바일계정 정보 등록(create) ] 들어온 mbUserDTO 값 : " + mbUserDTO);
        System.out.println("================================================");
        try {
            int check = boardService.createMobile(mbUserDTO);
            System.out.println("[ 모바일관리 - 모바일계정 정보 등록(create) ] 결과값 check 값 : " + check);
            if (check == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



    // 모바일관리 - 모바일계정 정보 불러오기(read)
    @RequestMapping("readMobile")
    public ArrayList<MbUserDTO> readMobile(@RequestBody MbUserDTO mbUserDTO){
        System.out.println("================================================");
        System.out.println("[ 모바일관리 - 모바일계정 정보 불러오기(read) ] 들어온 mbUserDTO 값: " + mbUserDTO);
        System.out.println("================================================");
        try {
            ArrayList<MbUserDTO> mbList = boardService.readMobile(mbUserDTO);
            System.out.println("[ 모바일관리 - 모바일계정 정보 불러오기(read) ] 결과값 mbList 값 : " + mbList);
            if(mbList.size() == 0){
                return null;
            }
            return mbList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    // 모바일관리 - 모바일계정 정보 (update)
    @RequestMapping("updateMobile")
    public boolean updateMobile(@RequestBody MbUserDTO mbUserDTO){
        System.out.println("================================================");
        System.out.println("[ 모바일관리 - 모바일계정 정보 (update) ] 들어온 mbUserDTO 값: " + mbUserDTO);
        System.out.println("================================================");
        try{
            int check = boardService.updateMobile(mbUserDTO);
            System.out.println("[ 모바일관리 - 모바일계정 정보 (update) ] 결과값 check 값 : " + check);
            if(check > 0){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    // 모바일관리 - 모바일계정 정보 수정 - 모달 창에 업데이트 할 값 보내기
    @RequestMapping("updateMobileModal")
    public ArrayList<MbUserDTO> updateMobileModal(@RequestBody MbUserDTO mbUserDTO){
        // param : code
        System.out.println("================================================");
        System.out.println("[ 모바일관리 (updateMobileModal) ] 들어온 mbUserDTO 값: " + mbUserDTO);
        System.out.println("================================================");
        try {
            ArrayList<MbUserDTO> mobileList = boardService.updateMobileModal(mbUserDTO);
            System.out.println("[ 모바일관리 (updateMobileModal) ] 결과값 mobileList 값 : " + mobileList);
            if (mobileList != null) {
                return mobileList;
            }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 모바일관리 - 모바일계정 정보 삭제(delete)
    @RequestMapping("deleteMobile")
    public boolean deleteMobile(@RequestBody MbUserDTO mbUserDTO) {
        System.out.println("================================================");
        System.out.println("[ 모바일관리 - 모바일계정 정보 삭제(delete) ] 들어온 userDTO 값: " + mbUserDTO);
        System.out.println("================================================");
        try {
            String[] list = mbUserDTO.getDeleteList();
            int check = 0;
            for (int i = 0; i < list.length; i++) {
                check = boardService.deleteMobile(list[i]);
                System.out.println("[ 모바일관리 - 모바일계정 정보 삭제(delete) ] 결과값 check 값 : " + check);
                if (check == 0) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



    //-------------------------------- < 회사 설정 > ----------------------------------
    // 회사 설정 - 회사 등록(create)
    @RequestMapping("createCompany")
    public boolean createCompany(@RequestBody CompanyDTO companyDTO) {
        System.out.println("================================================");
        System.out.println("[ 회사설정 - 회사등록 ] 들어온 companyDTO값: " + companyDTO);
        System.out.println("================================================");
        try {
            int check = boardService.createCompany(companyDTO);
            System.out.println("[ 회사설정 - 회사등록 ] 결과값 check 값 : " + check);
            if (check == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 회사 설정 - 회사정보 보기(read)
    @RequestMapping("readCompany")
    public ArrayList<CompanyDTO> readCompany(@RequestBody CompanyDTO companyDTO) {
        System.out.println("================================================");
        System.out.println("[ 회사설정 - 회사정보 보기 ] 들어온 companyDTO값: " + companyDTO);
        System.out.println("================================================");
        ArrayList<CompanyDTO> companyInfo = new ArrayList<>();
        try {
            companyInfo = boardService.readCompany(companyDTO);
            return companyInfo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 회사 설정 - 회사정보 수정(update)
    @RequestMapping("updateCompany")
    public boolean updateCompany(@RequestBody CompanyDTO companyDTO) {
        System.out.println("================================================");
        System.out.println("[ 회사설정 - 회사정보 수정 ] 들어온 companyDTO값: " + companyDTO);
        System.out.println("================================================");
        int check = 0;
        try {
            check = boardService.updateCompany(companyDTO);
            System.out.println("[ 회사설정 - 회사등록 ] 결과값 check 값 : " + check);
            if (check == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 회사 설정 - 회사정보 삭제(delete)
    @RequestMapping("deleteCompany")
    public boolean deleteCompany(@RequestBody CompanyDTO companyDTO) {
        System.out.println("================================================");
        System.out.println("[ 회사설정 - 회사정보 삭제 ] 들어온 companyDTO값: " + companyDTO);
        System.out.println("================================================");
        int check = 0;
        try {
            check = boardService.deleteCompany(companyDTO);
            System.out.println("[ 회사설정 - 회사등록 ] 결과값 check 값 : " + check);
            if (check == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    //-------------------------------- < 부서관리 > ----------------------------------
    // CRD (create, read, delete)
    // 부서관리 - 부서정보 등록(create)
    @RequestMapping("createDep")
    public boolean createDep(@RequestBody DepDTO depDTO){
        // param data : compCode, depCode, depName, depDetail
        System.out.println("================================================");
        System.out.println("[ 부서관리 - 부서정보 등록 ] 들어온 depDTO 값: " + depDTO);
        System.out.println("================================================");
        try {
            int check = boardService.createDep(depDTO);
            System.out.println("[ 부서관리 - 부서정보 등록 ] 결과값 check 값 : " + check);
            if(check == 1){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // 부서관리 - 부서정보 불러오기(read)
    @RequestMapping("readDep")
    public ArrayList<DepDTO> readDep(@RequestBody DepDTO depDTO){
        // param data : compCode
        System.out.println("================================================");
        System.out.println("[ 부서관리 - 부서정보 불러오기(read) ] 들어온 depDTO 값: " + depDTO);
        System.out.println("================================================");
        try {
            ArrayList<DepDTO> depList = boardService.readDep(depDTO);
            System.out.println("[ 부서관리 - 부서정보 불러오기(read) ] 결과값 check 값 : " + depList);
            return depList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    // 부서관리 - 부서정보 삭제(delete)
    @RequestMapping("deleteDep")
    public boolean deleteDep(@RequestBody DepDTO depDTO){
        // param data : compCode, depCode
        System.out.println("================================================");
        System.out.println("[ 부서관리 - 부서정보 삭제(delete) ] 들어온 depDTO 값: " + depDTO);
        System.out.println("================================================");
        try {
            int check = boardService.deleteDep(depDTO);
            System.out.println("[ 부서관리 - 부서정보 삭제(delete) ] 결과값 check 값 : " + check);
            if(check == 1){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //-------------------------------- < 사원관리 > ----------------------------------
    // CRUD (create, read, update, delete)
    // 사원관리 - 사원정보 등록(create)
    @RequestMapping("createEmp")
    public boolean createEmp(@RequestBody EmpDTO empDTO){
        // param data : 모든 데이터(compCode는 session값으로 받는다.) -
        System.out.println("================================================");
        System.out.println("[ 사원관리 - 사원정보 등록(create) ] 들어온 empDTO 값: " + empDTO);
        System.out.println("================================================");

        // [주민등록번호] 합치기( 주민번호 앞 + 주민번호 뒷 )
        empDTO.setEmpSSN(empDTO.getEmpFirstSSN() + "-" + empDTO.getEmpSecondSSN());
        System.out.println("완성된 주민등록번호: " + empDTO.getEmpSSN());

        // [입사일] 년/월/일 합치기
        empDTO.setEmpStart(empDTO.getEmpStartYear() + "-" + empDTO.getEmpStartMonth() + "-" + empDTO.getEmpStartDay());
        System.out.println("완성된 입사일: " + empDTO.getEmpStart());

        // [퇴사일] 년/월/일 합치기
        if(empDTO.getEmpEndYear() != null && empDTO.getEmpEndMonth() != null && empDTO.getEmpEndDay() != null){
            empDTO.setEmpEnd(empDTO.getEmpEndYear() + "-" + empDTO.getEmpEndMonth() + "-" + empDTO.getEmpEndDay());
            System.out.println("완성된 퇴사일: " + empDTO.getEmpEnd());
        }

        try {
            int check = boardService.createEmp(empDTO);
            System.out.println("[ 사원관리 - 사원정보 등록(create) ] 결과값 check 값 : " + check);
            if(check == 1){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // 사원관리 - 사원정보 불어오기(read)
    @RequestMapping("readEmp")
    public ArrayList<EmpDTO> readEmp(@RequestBody EmpDTO empDTO){
        // param data : compCode
        System.out.println("================================================");
        System.out.println("[ 사원관리 - 사원정보 불러오기(read) ] 들어온 empDTO 값: " + empDTO);
        System.out.println("================================================");

        try {
            ArrayList<EmpDTO> empList = boardService.readEmp(empDTO);
            System.out.println("[ 사원관리 - 사원정보 불어오기(read) ] 결과값 empList 값 : " + empList);
            if(empList == null){
                return null;
            }
            return empList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    // 사원관리 - 사원정보 수정(update)
    @RequestMapping("updateEmp")
    public boolean updateEmp(@RequestBody EmpDTO empDTO){
        // param data : emp의 거의 모든 데이터(compCode는 session값으로 받는다.)
        System.out.println("================================================");
        System.out.println("[ 사원관리 - 사원정보 수정(update) ] 들어온 empDTO 값: " + empDTO);
        System.out.println("================================================");

        // [주민등록번호] 합치기( 주민번호 앞 + 주민번호 뒷 )
        empDTO.setEmpSSN(empDTO.getEmpFirstSSN() + "-" + empDTO.getEmpSecondSSN());
        System.out.println("완성된 주민등록번호: " + empDTO.getEmpSSN());

        // [입사일] 년/월/일 합치기
        empDTO.setEmpStart(empDTO.getEmpStartYear() + "-" + empDTO.getEmpStartMonth() + "-" + empDTO.getEmpStartDay());
        System.out.println("완성된 입사일: " + empDTO.getEmpStart());

        // [퇴사일] 년/월/일 합치기
        if(empDTO.getEmpEndYear() != null){
            empDTO.setEmpEnd(empDTO.getEmpEndYear() + "-" + empDTO.getEmpEndMonth() + "-" + empDTO.getEmpEndDay());
            System.out.println("완성된 퇴사일: " + empDTO.getEmpEnd());
        }


        try {
            int check = boardService.updateEmp(empDTO);
            System.out.println("[ 사원관리 - 사원정보 수정(update) ] 결과값 check 값 : " + check);
            if(check == 1){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // 사원관리 - 사원정보 수정모달(updateEmpPayModal)
    @RequestMapping("updateEmpModal")
    public ArrayList<EmpDTO> updateEmpModal(@RequestBody EmpDTO empDTO){
        // param : empId
        System.out.println("================================================");
        System.out.println("[ 사원관리 - 사원정보 수정모달(updateEmpPayModal) ] 들어온 empDTO 값: " + empDTO);
        System.out.println("================================================");
        try {
            ArrayList<EmpDTO> empList = boardService.updateEmpModal(empDTO);
            System.out.println("[ 사원관리 - 사원정보 수정모달(updateEmpPayModal) ] 결과값 empList 값 : " + empList);
            if (empList != null) {
                return empList;
            }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 사원관리 - 사원정보 삭제(delete)
    @RequestMapping("deleteEmp")
    public boolean deleteEmp(@RequestBody EmpDTO empDTO){
        // param data : empId
        System.out.println("================================================");
        System.out.println("[ 사원관리 - 사원정보 삭제(delete) ] 들어온 empDTO 값: " + empDTO);
        System.out.println("================================================");
        try {
            int check = boardService.deleteEmp(empDTO);
            System.out.println("[ 사원관리 - 사원정보 삭제(delete) ] 결과값 check 값 : " + check);
            if(check == 1){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //-------------------------------- < 수당관리 > ----------------------------------
    // CRUD (create, read, update, delete)
    // 수당관리 - 수당항목 등록(create) - 배열로 받는다고 생각하고 만들기
    @RequestMapping("createEmpPay")
    public boolean createEmpPay(@RequestBody Emp_payDTO emp_payDTO){
        // param data : 모든 데이터
        System.out.println("================================================");
        System.out.println("[ 수당관리 - 수당항목 등록(create) ] 들어온 emp_payDTO 값: " + emp_payDTO);
        System.out.println("================================================");
        try {
            int check = 0;
            System.out.println("[ 수당관리 - 수당항목 등록(create) ] 결과값 paylist 값 : " + check);
            if(check == 0){
                return false;
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // 수당관리 - 수당항목 불러오기(read)
    @RequestMapping("readEmpPay")
    public ArrayList<Emp_payDTO> readEmpPay(@RequestBody Emp_payDTO emp_payDTO){
        // param data : compCode
        System.out.println("================================================");
        System.out.println("[ 수당관리 - 수당항목 불러오기(read) ] 들어온 emp_payDTO 값: " + emp_payDTO);
        System.out.println("================================================");
        try {
            ArrayList<Emp_payDTO> paylist = boardService.readEmpPay(emp_payDTO);
            System.out.println("[ 수당관리 - 수당항목 불러오기(read) ] 결과값 paylist 값 : " + paylist);
            if(paylist.size() == 0){
                return null;
            }
            return paylist;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    // 수당관리 - 수당항목 수정(update)
    @RequestMapping("updateEmpPay")
    public boolean updateEmpPay(@RequestBody Emp_payDTO emp_payDTO){
        // param data : 모든 데이터
        System.out.println("================================================");
        System.out.println("[ 수당관리 - 수당항목 수정(update) ] 들어온 emp_payDTO 값: " + emp_payDTO);
        System.out.println("================================================");
        try {
            int check = 0;
            check = boardService.updateEmpPay(emp_payDTO);
            System.out.println("[ 수당관리 - 수당항목 수정(update) ] 결과값 check 값 : " + check);
            if(check == 0){
                return false;
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // 수당관리 - 수당항목 수정모달(updateEmpPayModal)
    @RequestMapping("updateEmpPayModal")
    public ArrayList<Emp_payDTO> updateEmpPayModal(@RequestBody Emp_payDTO emp_payDTO){
        // param : empPayID
        System.out.println("================================================");
        System.out.println("[ 수당관리 - 수당항목 수정모달(updateEmpPayModal) ] 들어온 emp_payDTO 값: " + emp_payDTO);
        System.out.println("================================================");
        try {
            ArrayList<Emp_payDTO> empPayList = boardService.updateEmpPayModal(emp_payDTO);
            System.out.println("[ 수당관리 - 수당항목 수정모달(updateEmpPayModal) ] 결과값 empPayList 값 : " + empPayList);
            if (empPayList != null) {
                return empPayList;
            }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 수당관리 - 수당항목 삭제(delete)
    @RequestMapping("deleteEmpPay")
    public boolean deleteEmpPay(@RequestBody Emp_payDTO emp_payDTO){
        // param data : deleteEmpPay(리스트형태) - 리스트 안에는 empPayId 가 있음
        System.out.println("================================================");
        System.out.println("[ 수당관리 - 수당항목 삭제(delete) ] 들어온 emp_payDTO 값: " + emp_payDTO);
        System.out.println("================================================");
        String[] deleteArr;
        try {
            // 1. 배열로 들어가
            deleteArr = emp_payDTO.getDeleteEmpPay();
            for(int i=0; i<deleteArr.length; i++){
                int check = boardService.deleteEmpPay(deleteArr[i]);
                System.out.println("[ 수당관리 - 수당항목 삭제(delete) ] 결과값 check 값: " + check);
                if(check == 0){
                    return false;
                }
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    //-------------------------------- < 세금관리 > ----------------------------------
    // CRUD (create, read, update, delete)
    // 세금관리 - 세금정보 등록(create)
    @RequestMapping("createTax")
    public boolean createTax(@RequestBody TaxDTO taxDTO){
        System.out.println("==================================");
        System.out.println("[ 세금관리 - 세금정보 등록(create) ] 들어온 taxDTO: " + taxDTO);
        System.out.println("==================================");
        try {
            int check = boardService.createTax(taxDTO);
            System.out.println("[ 세금관리 - 세금정보 등록(create) ] 결과값 check : " + check);
            if(check == 0){
                return false;
            }else{
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


    // 세금관리 - 세금정보 불러오기(read)
    @RequestMapping("readTax")
    public ArrayList<TaxDTO> readTax(@RequestBody TaxDTO taxDTO){
        // param data : compCode
        System.out.println("================================================");
        System.out.println("[ 세금관리 - 세금정보 불러오기(read) ] 들어온 taxDTO 값: " + taxDTO);
        System.out.println("================================================");
        try {
            ArrayList<TaxDTO> taxList = boardService.readTax(taxDTO);
            System.out.println("[ 세금관리 - 세금정보 불러오기(read) ] 결과값 taxList 값 : " + taxList);
            if(taxList.size() == 0){
                return null;
            }
            return taxList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    // 세금관리 - 세금정보 수정(update)
    @RequestMapping("updateTax")
    public boolean updateTax(@RequestBody TaxDTO taxDTO){
        // param data : 모든 데이터
        System.out.println("================================================");
        System.out.println("[ 세금관리 - 세금정보 수정(update) ] 들어온 taxDTO 값: " + taxDTO);
        System.out.println("================================================");
        try {
            int check = boardService.updateTax(taxDTO);
            System.out.println("[ 세금관리 - 세금정보 수정(update) ] 결과값 check 값 : " + check);
            if(check == 0){
                return false;
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // 세금관리 - 세금 수정모달(updateEmpPayModal)
    @RequestMapping("updateTaxModal")
    public ArrayList<TaxDTO> updateTaxModal(@RequestBody TaxDTO taxDTO){
        // param : taxInfoID
        System.out.println("================================================");
        System.out.println("[ 세금관리 - 세금 수정모달(updateEmpPayModal) ] 들어온 taxDTO 값: " + taxDTO);
        System.out.println("================================================");
        try {
            ArrayList<TaxDTO> taxList = boardService.updateTaxModal(taxDTO);
            System.out.println("[ 세금관리 - 세금 수정모달(updateEmpPayModal) ] 결과값 taxList 값 : " + taxList);
            if (taxList != null) {
                return taxList;
            }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 세금관리 - 세금정보 삭제(delete)
    @RequestMapping("deleteTax")
    public boolean deleteTax(@RequestBody TaxDTO taxDTO){
        // param data : deleteEmpPay(리스트형태) - taxInfoID 가 리스트로 담겨있음.
        System.out.println("================================================");
        System.out.println("[ 수당관리 - 수당항목 삭제(delete) ] 들어온 taxDTO 값: " + taxDTO);
        System.out.println("================================================");
        String[] deleteArr;
        try {
            deleteArr = taxDTO.getDeleteTaxList();
            for(int i=0; i<deleteArr.length; i++){
                int check = boardService.deleteTax(deleteArr[i]);
                System.out.println("[ 수당관리 - 수당항목 삭제(delete) ] 결과값 check 값: " + check);
                if(check == 0){
                    return false;
                }
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 근태관리 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

    //휴가항목 등록 (create)
    //입력해야되는 데이터 : vactCode, vactName, vactDetail
    @RequestMapping("create_Vactcategory")
    public boolean create_vactcategory(@RequestBody Vact_CategoryDTO vact_categoryDTO){
        System.out.println("==================================");
        System.out.println("[ 휴가항목등록 - 휴가항목 추가 ] 들어온 vact_categoryDTO값: " + vact_categoryDTO);
        System.out.println("==================================");
        try {
            int check = boardService.createVactCategory(vact_categoryDTO);
            System.out.println("[ 휴가항목등록 - 휴가항목 추가 ] 결과값 check 값 : " + check);

            if(check == 1){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //휴가항목 등록 (read)
    //요청값 : compCode ( 회사코드 )
    //           리스트 (PK, AI)  회사코드   휴가코드    휴가명     휴가상세
    //return값 : vactNameListId, compCode, vactCode, vactName, vactDetail
    @RequestMapping("read_Vactcategory")
    public List<Vact_CategoryDTO> read_vactcategory(@RequestBody Vact_CategoryDTO vact_categoryDTO){
        System.out.println("==================================");
        System.out.println("[ 휴가항목등록 - 휴가항목 리스트 ] 들어온 vact_categoryDTO값: " + vact_categoryDTO);
        System.out.println("==================================");
        try{
            List<Vact_CategoryDTO> vactList = boardService.readVactCategory(vact_categoryDTO);
            if(vactList != null){
                return vactList;
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //휴가항목 등록 (update)
    //요청값 : compCode, vactNameListId
    @RequestMapping("update_Vactcategory")
    public boolean update_Vactcategory(@RequestBody Vact_CategoryDTO vact_categoryDTO){ //front로 휴가코드, 마스터아이디 요청
        System.out.println("==================================");
        System.out.println("[ 휴가항목등록 - 휴가항목 수정 ] 들어온 vact_categoryDTO값: " + vact_categoryDTO);
        System.out.println("==================================");
        try{
            int check = boardService.updateVactCategory(vact_categoryDTO);
            System.out.println("[ 휴가항목등록 - 휴가항목 수정 ] 결과값 check 값 : " + check);
            if(check == 1){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //휴가항목 등록 (delete)
    //요청값 : compCode, vactNameListId
    @RequestMapping("delete_Vactcategory")  //front로 getDeleteVactCode, compCode 요청
    public boolean delete_Vactcategory(@RequestBody Vact_CategoryDTO vact_categoryDTO){
        System.out.println("==================================");
        System.out.println("[ 휴가항목등록 - 휴가항목 삭제 ] 들어온 vact_categoryDTO값: " + vact_categoryDTO);
        System.out.println("==================================");
        try {
            int check = 0;
            String[] list = vact_categoryDTO.getDeleteVactCode();
            for(int i=0;i<list.length;i++){
                check = boardService.deleteVactCategory(list[i]);
                System.out.println("[ 휴가항목등록 - 휴가항목 삭제 ] 결과값 check 값 : " + check);
                if(check == 0){
                    return false;
                }
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //휴가처리 (create)
    //              시작일           종료일     사원번호  사원명    부서명     휴가기간    휴가항목명   상세
    // 입력데이터 : vactStartDate, vactEndDate, empNum, empName, depName, vactPeriod, vactName, vactDetail
    @RequestMapping("create_VactDispose")
    public boolean create_VactDispose (@RequestBody Vact_disposeDTO vact_disposeDTO){
        System.out.println("==================================");
        System.out.println("[ 휴가처리 -  추가 ] 들어온 vact_disposeDTO: " + vact_disposeDTO);
        System.out.println("==================================");
        try {
            int check = boardService.createVactDispose(vact_disposeDTO);
            System.out.println("[ 휴가처리 -  추가 ] 결과값 check 값 : " + check);
            if(check == 1){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    //휴가처리 (read)
    @RequestMapping("read_VactDispose")
    public List<Vact_disposeDTO> read_VactDispose(@RequestBody Vact_disposeDTO vact_dispose){
        try {
            System.out.println("==================================");
            System.out.println("[ 휴가처리 ] 들어온 vact_dispose: " + vact_dispose);
            System.out.println("==================================");
            List<Vact_disposeDTO> VactDispose = boardService.readVactDispose(vact_dispose);
            if(VactDispose != null){
                return VactDispose;
            }else{
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //휴가처리 (update) - 권한 승인
    // 요청값 : compCode, empName ,depName
    @RequestMapping("update_VactDispose")
    public int update_VactDispose(@RequestBody Vact_disposeDTO vact_disposeDTO) {
        System.out.println("==================================");
        System.out.println("[ 휴가처리 - 권한 승인 ] 들어온 vact_disposeDTO: " + vact_disposeDTO);
        System.out.println("==================================");
        try {
            int check1 = boardService.VactDispose(vact_disposeDTO); //잔여휴가 select 값
            System.out.println("잔여휴가 select 값 : " + check1);
            if(vact_disposeDTO.getVactPeriod() > check1){
                return 2;       //휴가신청일이 잔여휴가보다 클때 return 값
            }else{
                int check = boardService.updateVactDispose(vact_disposeDTO);
                System.out.println("보유휴가현황 업데이트 check값 :" + check);
                if(check == 1){
                    return 1;   //성공
                }else {
                    return 0;   //실패
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    //휴가 보유현황 (read)

    @RequestMapping("read_Vactlist")
    public List<EmpDTO> read_Vactlist(@RequestBody EmpDTO empDTO) {
        try{
            int check = boardService.updateRemindVact();
            System.out.println("휴가보유현황 check값 확인" + check);

            System.out.println("==================================");
            System.out.println("[ 휴가보유현황 ] 들어온 vact_listDTO: " + empDTO);
            System.out.println("==================================");
            List<EmpDTO> list = boardService.readVactlist(empDTO);
            if(list != null){
                return list;
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //휴가 보유현황 (update)
    //요청값 :  compCode, empName, empNum;
    @RequestMapping("update_Vactlist")
    public boolean update_Vactlist(@RequestBody EmpDTO empDTO){
        System.out.println("==================================");
        System.out.println("[ 보유휴가현황 - 보유휴가현황 수정 ] 들어온 vact_listDTO: " + empDTO);
        System.out.println("==================================");
        try {
            int check = boardService.updateVactlist(empDTO);
            System.out.println("보유휴가현황 업데이트 check값 :" + check);
            if(check == 1){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //출퇴근 관리 (create)
    @RequestMapping("create_inOutInfo")
    public boolean create_inOutInfo(@RequestBody InOut_infoDTO inOut_infoDTO) {
        System.out.println("==================================");
        System.out.println("[ 출퇴근관리 -  추가 ] 들어온 inOut_infoDTO: " + inOut_infoDTO);
        System.out.println("==================================");
        try {
            int check = boardService.createinOut_info(inOut_infoDTO);
            System.out.println("[ 출퇴근관리 -  추가 ] 들어온 inOut_infoDTO: " + check);
            if (check == 1) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //출퇴근 관리 (read)
    //          리스트 PK, AI  회사코드   사원코드  사원명    부서명    출근시간     퇴근시간    초과근무
    //return값 : inOutListId, compCode, empCode, empName, depName, inOutStart, inOutEnd, inOutOver
    @RequestMapping("read_inOutInfo")
    public List<InOut_infoDTO> read_inOutInfo(@RequestBody InOut_infoDTO inOut_infoDTO){
        try{
            System.out.println("==================================");
            System.out.println("[ 출퇴근관리 ] 들어온 inOut_infoDTO: " + inOut_infoDTO);
            System.out.println("==================================");
            List<InOut_infoDTO> list = boardService.readinout_info(inOut_infoDTO);
            if(list != null){
                return list;
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //출퇴근관리 (update)
    //요청값 : compCode (회사코드), inOutListId ( 리스트 PK, AI  )
    @RequestMapping("update_inOutInfo")
    public boolean update_inOutInfo (@RequestBody InOut_infoDTO inOut_infoDTO){
        System.out.println("==================================");
        System.out.println("[ 출퇴근관리 -  수정 ] 들어온 inOut_infoDTO: " + inOut_infoDTO);
        System.out.println("==================================");
        try {
            int check = boardService.updateinOut_info(inOut_infoDTO);
            System.out.println("출퇴근관리 업데이트 check값 :" + check);
            if(check == 1){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    //출퇴근관리 (delete)
    //요청값 : compCode (회사코드), getDeleteList (아이디)
    @RequestMapping("delete_inOutInfo")     //front로 getDeleteList, compCode 요청
    public boolean delete_inOutInfo (@RequestBody InOut_infoDTO inOut_infoDTO){
        System.out.println("==================================");
        System.out.println("[ 출퇴근관리 -  삭제 ] 들어온 inOut_infoDTO: " + inOut_infoDTO);
        System.out.println("==================================");
        try {
            int check = 0;
            int[] list = inOut_infoDTO.getDeleteList();
            for(int i=0;i<list.length;i++){
                check = boardService.deleteinOut_info(list[i]);
                System.out.println("[ 출퇴근관리 -  삭제 ] 결과값 check 값 : " + check);
                if(check == 0){
                    return false;
                }
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //출퇴근현황 ( search)
    //요청값 = compCode(회사코드), empName (이름), startDate (시작날짜), endDate(종료날짜)
    @RequestMapping("search_inOutInfo")
    public List<InOut_infoDTO> search_inOutInfo(@RequestBody InOut_infoDTO inOut_infoDTO){
        try{
            System.out.println("==================================");
            System.out.println("[ 출퇴근현황 ] 들어온 inOut_infoDTO: " + inOut_infoDTO);
            System.out.println("==================================");
            List<InOut_infoDTO> list = boardService.searchInout(inOut_infoDTO);
            if(list != null){
                return list;
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    //-------------------------------- < 일용직관리[일용직등록] > ----------------------------------
    // CRUD (create, read, update, delete)
    // 일용직관리[일용직등록] - 일용직정보 등록(create)
    @RequestMapping("createDailyEmp")
    public boolean createDailyEmp(@RequestBody DailyEmpDTO dailyEmpDTO){
        // param : 모든 데이터
        System.out.println("==================================");
        System.out.println("[ 일용직관리 - 일용직정보 등록(create) ] 들어온 dailyEmpDTO 값 : " + dailyEmpDTO);
        System.out.println("==================================");
        try {
            int check = boardService.createDailyEmp(dailyEmpDTO);
            System.out.println("[ 일용직관리 - 일용직정보 등록(create) ] 결과값 check 값 : " + check);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping("readDailyEmp")
    // 일용직관리[일용직등록] - 일용직정보 불러오기(read)
    public ArrayList<DailyEmpDTO> readDailyEmp(@RequestBody DailyEmpDTO dailyEmpDTO){
        // param : compCode
        System.out.println("==================================");
        System.out.println("[ 일용직관리 - 일용직정보 불러오기(read) ] 들어온 dailyEmpDTO 값 : " + dailyEmpDTO);
        System.out.println("==================================");
        try {
            ArrayList<DailyEmpDTO> dailyEmpList = boardService.readDailyEmp(dailyEmpDTO);
            System.out.println("[ 일용직관리 - 일용직정보 불러오기(read) ] 결과값 dailyEmpList 값 : " + dailyEmpList);
            if(dailyEmpList == null){
                return null;
            }
            return dailyEmpList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    // 일용직관리[일용직등록] - 일용직정보 수정(update)
    @RequestMapping("updateDailyEmp")
    public boolean updateDailyEmp(@RequestBody DailyEmpDTO dailyEmpDTO){
        // param : dailyId
        System.out.println("==================================");
        System.out.println("[ 일용직관리 - 일용직정보 수정(update) ] 들어온 dailyEmpDTO 값 : " + dailyEmpDTO);
        System.out.println("==================================");
        try {
            int check = boardService.updateDailyEmp(dailyEmpDTO);
            System.out.println("[ 일용직관리 - 일용직정보 불러오기(read) ] 결과값 check 값 : " + check);
            if(check == 0){
                return false;
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // 일용직관리[일용직등록] - 일용직정보 수정 모달
    @RequestMapping("updateDailyEmpModal")
    public ArrayList<DailyEmpDTO> updateDailyEmpModal(@RequestBody DailyEmpDTO dailyEmpDTO){
        // param : dailyId
        System.out.println("==================================");
        System.out.println("[ 일용직관리 - 일용직정보 수정 모달 ] 들어온 dailyEmpDTO 값 : " + dailyEmpDTO);
        System.out.println("==================================");
        try {
            ArrayList<DailyEmpDTO> dailyEmpModal = boardService.updateDailyEmpModal(dailyEmpDTO);
            System.out.println("[ 일용직관리 - 일용직정보 불러오기(read) ] 결과값 dailyEmpModal 값 : " + dailyEmpModal);
            if(dailyEmpDTO == null){
                return null;
            }
            return dailyEmpModal;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    // 일용직관리[일용직등록] - 일용직정보 삭제(delete)
    @RequestMapping("deleteDailyEmp")
    public boolean deleteDailyEmp(@RequestBody DailyEmpDTO dailyEmpDTO){
        // param : dailyId
        System.out.println("==================================");
        System.out.println("[ 일용직관리 - 일용직정보 삭제(delete) ] 들어온 dailyEmpDTO 값 : " + dailyEmpDTO);
        System.out.println("==================================");
        try {
            int check = boardService.deleteDailyEmp(dailyEmpDTO);
            System.out.println("[ 일용직관리 - 일용직정보 삭제(delete) ] 결과값 check 값 : " + check);
            if(check == 0){
                return false;
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //-------------------------------- < 일용직관리[일용직 수당] > ----------------------------------
    // CRUD (create, read, update, delete)
    // daily_pay
    // 일용직관리[일용직 수당] - 수당 등록
    @RequestMapping("createDailyPay")
    public boolean createDailyPay(@RequestBody DailyEmpPayDTO dailyEmpPayDTO){
        // param : 모든 데이터
        System.out.println("==================================");
        System.out.println("[ 일용직관리[일용직 수당] - 수당 등록 ] 들어온 dailyEmpPayDTO 값 : " + dailyEmpPayDTO);
        System.out.println("==================================");
        try {
            int check = boardService.createDailyPay(dailyEmpPayDTO);
            System.out.println("[ 일용직관리[일용직 수당] - 수당 등록 ] 결과값 check 값 : " + check);
            if(check == 0){
                return false;
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // 일용직관리[일용직 수당] - 수당 불러오기
    @RequestMapping("readDailyPay")
    public ArrayList<DailyEmpPayDTO> readDailyPay(@RequestBody DailyEmpPayDTO dailyEmpPayDTO){
        // param : compCode
        System.out.println("==================================");
        System.out.println("[ 일용직관리[일용직 수당] - 수당 불러오기 ] 들어온 dailyEmpPayDTO 값 : " + dailyEmpPayDTO);
        System.out.println("==================================");
        try {
            ArrayList<DailyEmpPayDTO> dailyEPList = boardService.readDailyPay(dailyEmpPayDTO);
            System.out.println("[ 일용직관리[일용직 수당] - 수당 불러오기 ] 결과값 dailyEPList 값 : " + dailyEPList);
            if(dailyEPList == null){
                return null;
            }
            return dailyEPList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    // 일용직관리[일용직 수당] - 수당 수정
    @RequestMapping("updateDailyPay")
    public boolean updateDailyPay(@RequestBody DailyEmpPayDTO dailyEmpPayDTO){
        // param : d_payId
        System.out.println("==================================");
        System.out.println("[ 일용직관리[일용직 수당] - 수당 수정 ] 들어온 dailyEmpPayDTO 값 : " + dailyEmpPayDTO);
        System.out.println("==================================");
        try {
            int check = boardService.updateDailyPay(dailyEmpPayDTO);
            System.out.println("[ 일용직관리[일용직 수당] - 수당 수정 ] 결과값 check 값 : " + check);
            if(check == 0){
                return false;
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    // 일용직관리[일용직 수당] - 수당 수정 모달(modal)
    @RequestMapping("updateDailyPayModal")
    public ArrayList<DailyEmpPayDTO> updateDailyPayModal(@RequestBody DailyEmpPayDTO dailyEmpPayDTO){
        // param : d_payId
        System.out.println("==================================");
        System.out.println("[ 일용직관리[일용직 수당] - 수당 수정 모달(modal) ] - 수당 등록 ] 들어온 dailyEmpPayDTO 값 : " + dailyEmpPayDTO);
        System.out.println("==================================");
        try {
            ArrayList<DailyEmpPayDTO> dailyEPMList = boardService.updateDailyPayModal(dailyEmpPayDTO);
            System.out.println("[ 일용직관리[일용직 수당] - 수당 수정 모달(modal)] 결과값 dailyEPList 값 : " + dailyEPMList);
            if(dailyEPMList == null){
                return null;
            }
            return dailyEPMList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    // 일용직관리[일용직 수당] - 수당 삭제
    @RequestMapping("deleteDailyPay")
    public boolean deleteDailyPay(@RequestBody DailyEmpPayDTO dailyEmpPayDTO){
        // param : deletePayCode(d_payId 배열 형태)
        System.out.println("==================================");
        System.out.println("[ 일용직관리[일용직 수당] - 수당 삭제 ] 들어온 dailyEmpPayDTO 값 : " + dailyEmpPayDTO);
        System.out.println("==================================");
        try {
            String[] deleteArr = dailyEmpPayDTO.getDeletePayCode();
            int check = 0;
            for(int i=0; i<deleteArr.length; i++){
                check = boardService.deleteDailyPay(dailyEmpPayDTO);
                System.out.println("[ 일용직관리[일용직 수당] - 수당 삭제 ] 결과값 check 값 : " + check);
                if(check == 0){
                    return false;
                }
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //-------------------------------- < 일용직관리[출퇴근관리] > ----------------------------------
    // daily_InOut
    // 일용직관리[출퇴근관리] - 일용직 정보 가져오기(read)
    @RequestMapping("searchDailyEmp")
    // 일용직관리[출퇴근관리] - 일용직 정보 가져오기(read) - 아마도 기존의 일용직 정보를 그대로 가져가도 될듯? 실험해보세요
    public ArrayList<DailyEmpDTO> searchDailyEmp(@RequestBody DailyEmpDTO dailyEmpDTO){
        // param : dailyId
        System.out.println("==================================");
        System.out.println("[ 일용직관리[출퇴근관리] - 일용직정보 불러오기(read) ] 들어온 dailyEmpDTO 값 : " + dailyEmpDTO);
        System.out.println("==================================");
        try {
            ArrayList<DailyEmpDTO> dailyEmpList = boardService.searchDailyEmp(dailyEmpDTO);
            System.out.println("[ 일용직관리[출퇴근관리] - 일용직정보 불러오기(read) ] 결과값 dailyEmpList 값 : " + dailyEmpList);
            if(dailyEmpList == null){
                return null;
            }
            return dailyEmpList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    // ------------------------
    // 일용직관리[출퇴근관리] - 일용직 출퇴근정보 등록(create)
//    @RequestMapping("createDailyInOut")
//    public boolean createDailyInOut(){
//
//    }

    // 일용직관리[출퇴근관리] - 일용직 출퇴근정보 불러오기(read)

    // 일용직관리[출퇴근관리] - 일용직 출퇴근정보 수정(update)

    // 일용직관리[출퇴근관리] - 일용직 출퇴근정보 수정모달(modal)

    // 일용직관리[출퇴근관리] - 일용직 출퇴근정보 삭제(delete)



    //-------------------------------- < 급여관리 > ----------------------------------
    // CRUD (create, read, update, delete)
    // 급여관리 - 급여명세서 등록(create)



}
