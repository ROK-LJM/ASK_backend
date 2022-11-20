package com.ask.ask_project.mapper;

import com.ask.ask_project.DTO.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//멤버 mapper
@Mapper
public interface BoardMapper {
    //로그인
    int loginCheck() throws Exception;
    //회원가입
    int insert_memberInfo() throws Exception;

    //사용자관리 ( create )
    int createUser() throws Exception;
    //사용자관리 ( reqd )
    List<UserDTO> readUser() throws Exception;
    //사용자관리 ( update )

    //사용자관리 ( delete )
}
