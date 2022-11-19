package com.ask.ask_project.controller;


import com.ask.ask_project.DTO.MemberDTO;
import com.ask.ask_project.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;
    //로그인
    @RequestMapping("login")
    public boolean login(@RequestBody MemberDTO memberDTO) {
        int loginCheck = 0;
        try {
            loginCheck = boardService.loginCheck(memberDTO);
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
    //회원가입
    @RequestMapping("joinProcess")
    public boolean joinProcess(@RequestBody MemberDTO memberDTO){
        try{
            int check = boardService.insert_memberInfo(memberDTO);
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
}
