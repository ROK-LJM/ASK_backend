package com.ask.ask_project.DTO;

import lombok.Data;
import org.apache.ibatis.type.Alias;
@Alias("dailyEmpPayDTO")
@Data
public class DailyEmpPayDTO {
    private int dailyId;            // 일용직리스트ID
    private String compCode;        // 회사코드
    private String d_payCode;       // 수당항목코드
    private String d_payName;       // 수당항목명
    private String d_taxFreeCode;   // 비과세 코드
    private String d_taxFreeName;   // 비과세 명
    private String d_taxFreeType;   // 지급유형
    private String d_taxFreeCalc;   // 계산식

    private String[] deletePayCode; // 삭제할 수당항목 배열

}
