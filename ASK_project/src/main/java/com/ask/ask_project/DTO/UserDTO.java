package com.ask.ask_project.DTO;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.ArrayList;

//사용자관리
@Alias("userDTO")
@Data
public class UserDTO {
    private String compCode;
    private String userId;
    private String userPw;
    private String userName;
    private int userGrant;
    private String preUserId;
    private String[] deleteList;
}
