package com.ask.ask_project.DTO;

import lombok.Data;
import org.apache.ibatis.type.Alias;

// 모바일 계정 정보 DTO
@Alias("mbUserDTO")
@Data
public class MbUserDTO {
    private String compCode;        // 회사코드
    private String mb_id;           // 모바일 아이디
    private String mb_pw;           // 모바일 비밀번호
    private String mb_code;         // 사원코드
    private String mb_name;         // 사용자명
    private int firstCheck;         // 최초계정확인


    private String updatePw;        // 최초 비밀번호 변경

    private String preMobileId;     // 이전 모바일 아이디
    private String[] deleteList;    // 삭제 리스트
}
