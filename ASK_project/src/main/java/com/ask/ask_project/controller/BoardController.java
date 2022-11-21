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
        System.out.println("==================================");
        System.out.println("[ 로그인 ] 들어온 memberDTO값: " + userDTO);
        System.out.println("==================================");
        int loginCheck = 0;
        try {
            loginCheck = boardService.loginCheck(userDTO);
            System.out.println("[ 로그인 ] 결과값 loginCheck : " + loginCheck);
            if(loginCheck == 1){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
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
    //마스터키
    @RequestMapping("getMaster")
    public String getMasdter(@RequestBody UserDTO userDTO){
        try{
            String uId = boardService.getMaster(userDTO);

            return uId;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    // 회원가입 - 아이디 중복 체크
    @RequestMapping("checkId")
    public boolean checkId(@RequestBody MemberDTO memberDTO){
        int checkNum = -1;
        System.out.println("들어온 dto 값 : " + memberDTO);
        try {
            checkNum = boardService.checkId(memberDTO);
            System.out.println("결과값 checkNum 값 : " + checkNum);
            if(checkNum == 0){
                return true;
            }else{

                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // 사용자관리 (create)
    @RequestMapping("createUser")
    public boolean createUser(@RequestBody UserDTO userDTO) {
        try {
            int check = boardService.createUser(userDTO);
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
        try {
            List<UserDTO> userList = boardService.readUser(userDTO);
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
        try{
        int check = boardService.updateUser(userDTO);
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
    //사용자관리 (updatemodal)
    @RequestMapping("updateUserModal")
    public List<UserDTO> updateUserModal(@RequestBody UserDTO userDTO){
        try {
            List<UserDTO> userList = boardService.updateUserModal(userDTO);
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
        try {
        int check = boardService.deleteUser(userDTO);
        if(check >0){
            return true;
        }else {
            return false;
        }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


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
    //회사설정 - 회사정보 수정(updateModal)


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

    // ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 근태관리 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

    //휴가항목 등록 (create)
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
    @RequestMapping("delete_Vactcategory")  //front로 휴가코드, 마스터아이디 요청
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

    //휴가처리 create 기능은 모바일과 같이 개발
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

    //휴가 보유현황 (read)
    @RequestMapping("read_Vactlist")
    public List<Vact_listDTO> read_Vactlist(@RequestBody Vact_listDTO vact_listDTO) throws Exception {
//        Vact_disposeDTO vact_disposeDTO = new Vact_disposeDTO();
//        boardService.total(vact_disposeDTO);
//        boardService.insert_vaclist(vact_listDTO);
        try{
            System.out.println("==================================");
            System.out.println("[ 휴가처리 ] 들어온 vact_listDTO: " + vact_listDTO);
            System.out.println("==================================");
            List<Vact_listDTO> vactList = boardService.readVactlist(vact_listDTO);
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

    //출퇴근 관리
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
}
