package com.ask.ask_project.controller;


import com.ask.ask_project.DTO.*;
import com.ask.ask_project.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 로그인
    @RequestMapping("login")
    public boolean login(@RequestBody UserDTO userDTO) {
        System.out.println("==================================");
        System.out.println("[ 로그인 ] 들어온 userDTO값: " + userDTO);
        System.out.println("==================================");
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
        System.out.println("==================================");
        System.out.println("[ getMaster ] 들어온 userDTO값: " + userDTO);
        System.out.println("==================================");
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
            int check = boardService.insert_memberInfo(memberDTO);
            System.out.println("[ 회원가입 ] 결과값 check : " + check);
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
        System.out.println("==================================");
        System.out.println("[ 회원가입 - id중복체크 ] 들어온 memberDTO값 : " + memberDTO);
        System.out.println("==================================");
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
        System.out.println("==================================");
        System.out.println("[ 사용자관리(create) ] 들어온 userDTO 값 : " + userDTO);
        System.out.println("==================================");
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
        System.out.println("==================================");
        System.out.println("[ 사용자관리(Read) ] 들어온 userDTO값: " + userDTO);
        System.out.println("==================================");
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
        System.out.println("==================================");
        System.out.println("[ 사용자관리 (update) ] 들어온 userDTO 값: " + userDTO);
        System.out.println("==================================");
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
        System.out.println("==================================");
        System.out.println("[ 사용자관리 (updateUserModal) ] 들어온 userDTO 값: " + userDTO);
        System.out.println("==================================");
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
        System.out.println("==================================");
        System.out.println("[ 사용자관리(delete) ] 들어온 userDTO 값: " + userDTO);
        System.out.println("==================================");
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

    //-------------------------------- < 회사 설정 > ----------------------------------
    // 회사 설정 - 회사 등록(create)
    @RequestMapping("createCompany")
    public boolean createCompany(@RequestBody CompanyDTO companyDTO) {
        System.out.println("==================================");
        System.out.println("[ 회사설정 - 회사등록 ] 들어온 companyDTO값: " + companyDTO);
        System.out.println("==================================");
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
        System.out.println("==================================");
        System.out.println("[ 회사설정 - 회사정보 보기 ] 들어온 companyDTO값: " + companyDTO);
        System.out.println("==================================");
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
        System.out.println("==================================");
        System.out.println("[ 회사설정 - 회사정보 수정 ] 들어온 companyDTO값: " + companyDTO);
        System.out.println("==================================");
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
        System.out.println("==================================");
        System.out.println("[ 회사설정 - 회사정보 삭제 ] 들어온 companyDTO값: " + companyDTO);
        System.out.println("==================================");
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
        System.out.println("==================================");
        System.out.println("[ 부서관리 - 부서정보 등록 ] 들어온 depDTO 값: " + depDTO);
        System.out.println("==================================");
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
        System.out.println("==================================");
        System.out.println("[ 부서관리 - 부서정보 불러오기(read) ] 들어온 depDTO 값: " + depDTO);
        System.out.println("==================================");
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
        System.out.println("==================================");
        System.out.println("[ 부서관리 - 부서정보 삭제(delete) ] 들어온 depDTO 값: " + depDTO);
        System.out.println("==================================");
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
        // param data : emp의 거의 모든 데이터(compCode는 session값으로 받는다.)
        System.out.println("==================================");
        System.out.println("[ 사원관리 - 사원정보 등록(create) ] 들어온 empDTO 값: " + empDTO);
        System.out.println("==================================");
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
    public boolean readEmp(@RequestBody EmpDTO empDTO){
        // param data : compCode
        System.out.println("==================================");
        System.out.println("[ 사원관리 - 사원정보 불러오기(read) ] 들어온 empDTO 값: " + empDTO);
        System.out.println("==================================");
        try {
            int check = boardService.createEmp(empDTO);
            System.out.println("[ 사원관리 - 사원정보 불어오기(read) ] 결과값 check 값 : " + check);
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

    // 사원관리 - 사원정보 수정(update)
    @RequestMapping("updateEmp")
    public boolean updateEmp(@RequestBody EmpDTO empDTO){
        // param data : emp의 거의 모든 데이터(compCode는 session값으로 받는다.)
        System.out.println("==================================");
        System.out.println("[ 사원관리 - 사원정보 수정(update) ] 들어온 empDTO 값: " + empDTO);
        System.out.println("==================================");
        try {
            int check = boardService.createEmp(empDTO);
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

    // 사원관리 - 사원정보 삭제(delete)
    @RequestMapping("deleteEmp")
    public boolean deleteEmp(@RequestBody EmpDTO empDTO){
        // param data : compCode, empNum
        System.out.println("==================================");
        System.out.println("[ 사원관리 - 사원정보 삭제(delete) ] 들어온 empDTO 값: " + empDTO);
        System.out.println("==================================");
        try {
            int check = boardService.createEmp(empDTO);
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
    // 수당관리 - 수당항목 등록(create)


    // 수당관리 - 수당항목 불러오기(read)

    // 수당관리 - 수당항목 수정(update)

    // 수당관리 - 수당항목 삭제(delete)


}
