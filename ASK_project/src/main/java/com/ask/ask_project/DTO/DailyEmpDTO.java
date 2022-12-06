package com.ask.ask_project.DTO;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("dailyEmpDTO")
@Data
public class DailyEmpDTO {
    private String compCode;        // 회사코드
    private int dailyId;            // 일용직리스트ID
    private String dailyName;       // 성명
    private String dailyCode;       // 일용직번호
    private String dailySsn;        // 주민등록번호
    private String dailyPhone;      // 전화번호
    private String dailyEmail;      // 이메일
    private String dailyAddress;    // 주소
    private String dailyRank;       // 직위/직급
    private String dailyStart;      // 입사일자
    private String dailyEnd;        // 퇴사일
    private String dailyEndDetail;  // 퇴사사유
    private String dailyBankName;   // 은행명
    private String dailyBankNum;    // 계좌번호
    private String dailyBankOwner;  // 예금주
    private int dailyPay;           // 일급

}
