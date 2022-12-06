package com.ask.ask_project.DTO;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("dailyTaxFreeDTO")
@Data
public class DailyTaxFreeDTO {
    private String dailyTaxFreeCode;    // 비과세 코드
    private String dailyTaxFreeName;    // 비과세 명
    private String dailyTaxFreeDetail;  // 비과세 상세내용
    private String dailyTaxFreeCalc;    // 비과세 계산식
}