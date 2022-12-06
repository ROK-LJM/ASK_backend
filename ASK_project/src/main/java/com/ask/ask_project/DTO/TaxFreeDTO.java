package com.ask.ask_project.DTO;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("taxFreeDTO")
@Data
public class TaxFreeDTO {
    private int check;
    private String taxFreeCode;     // 비과세코드
    private String taxFreeName;     // 비과세 명
    private String taxFreeCalc;     // 계산식
    private String taxFreeDetail;   // 상세내용
}
