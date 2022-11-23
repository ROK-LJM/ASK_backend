package com.ask.ask_project.DTO;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("dailyEmpDTO")
@Data
public class DailyEmpDTO {
    private int dailyId;        // 일용직리스트ID
    private String compCode;    // 회사코드
    private String d_code;      // 일용직원 코드
    private String d_name;      // 성명
    private String depCode;     // 부서코드
    private String d_address;   // 주소
    private String d_ssn;       // 주민등록번호
    private String d_phone;     // 전화번호
    private String d_bankName;  // 은행명
    private String d_bankNum;   // 계좌번호
    private String d_bankOwner; // 예금주
    private String d_start;     // 입사일자
    private int d_pay;          // 일급
}
