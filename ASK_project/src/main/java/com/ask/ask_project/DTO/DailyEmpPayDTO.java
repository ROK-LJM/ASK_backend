package com.ask.ask_project.DTO;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("dailyEmpPayDTO")
@Data
public class DailyEmpPayDTO {
    private int dailyPayId;            // 일용직리스트ID
    private String compCode;           // 회사코드
    private String dailyPayCode;       // 수당항목코드
    private String dailyPayName;       // 수당항목명
    private String dailyTaxFreeCode;   // 비과세 코드
    private String dailyTaxFreeName;   // 비과세 명
    private String dailyTaxFreeType;   // 지급유형
    private String dailyTaxFreeCalc;   // 계산식
}
