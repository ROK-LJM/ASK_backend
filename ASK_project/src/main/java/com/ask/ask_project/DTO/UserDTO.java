package com.ask.ask_project.DTO;

import lombok.Data;

//사용자관리
@Data
public class UserDTO {
    private String uId;
    private String uPw;
    private String uName;
    private int uGrant;
}
