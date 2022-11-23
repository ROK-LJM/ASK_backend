package com.ask.ask_project.DTO;

import lombok.Data;
import org.apache.ibatis.type.Alias;

// 모바일 계정 정보 DTO
@Alias("mbUserDTO")
@Data
public class MbUserDTO {
    private String compCode;
    private String mb_id;
    private String mb_pw;
    private String mb_code;
    private String mb_name;
    private int firstCheck;
    private String preMobileId;
    private String[] deleteList;
}
