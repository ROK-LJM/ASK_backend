package com.ask.ask_project.DTO;

import lombok.Data;
import org.apache.ibatis.type.Alias;

//사용자관리
@Data
@Alias("userDTO")
public class UserDTO {
    private String uId;
    private String uPw;
    private String uName;
    private String compCode;
    private int uGrant;

}
