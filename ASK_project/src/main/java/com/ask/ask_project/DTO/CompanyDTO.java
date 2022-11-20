package com.ask.ask_project.DTO;

import lombok.Data;
import org.apache.ibatis.type.Alias;

// 회사 설정 - 자회사 정보
@Alias("companyDTO")
@Data
public class CompanyDTO {
    private String compNum;
    private String compName;
    private String compCEO;
    private String compAddress;
    private String compType;
    private String compItems;
    private String compEmail;
}
