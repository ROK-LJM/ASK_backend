package com.ask.ask_project.controller;


import com.ask.ask_project.DTO.CompanyDTO;
import com.ask.ask_project.DTO.MemberDTO;
import com.ask.ask_project.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 로그인
    @RequestMapping("login")
    public boolean login(@RequestBody MemberDTO memberDTO) {
        System.out.println("==================================");
        System.out.println("[ 로그인 ] 들어온 memberDTO값: " + memberDTO);
        System.out.println("==================================");
        int loginCheck = 0;
        try {
            loginCheck = boardService.loginCheck(memberDTO);
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


    // 회사 설정 - 회사 등록(create)
    @RequestMapping("createCompany")
    public boolean createCompany(@RequestBody CompanyDTO companyDTO){
        try {
            int check = boardService.createCompany(companyDTO);
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

    // 회사 설정 - 회사 리스트 보기(read)
    @RequestMapping("readCompany")
    public ArrayList<CompanyDTO> readCompany(@RequestBody CompanyDTO companyDTO){
        ArrayList<CompanyDTO> companyInfo = new ArrayList<>();
        try {

            return companyInfo;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    // 회사 설정 - 회사 정보 수정(update)
    @RequestMapping("updateCompany")
    public boolean updateCompany(@RequestBody CompanyDTO companyDTO){
        try {

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
