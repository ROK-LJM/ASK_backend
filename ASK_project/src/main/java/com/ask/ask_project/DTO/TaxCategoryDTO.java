package com.ask.ask_project.DTO;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("taxCategoryDTO")
@Data
public class TaxCategoryDTO {
    private String taxListId;   // 세금항목리스트ID(PK, AI)
    private String taxCode;     // 세금코드
    private String taxName;     // 세금명
    private String taxItem;     // 세금항목명
    private String taxCalc;     // 계산식
}
