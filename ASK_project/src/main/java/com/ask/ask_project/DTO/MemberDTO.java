package com.ask.ask_project.DTO;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("memberDTO")
//멤버정보
@Data
public class MemberDTO {
    private String id;
    private String pw;
    private String name;
    private String email;
}
