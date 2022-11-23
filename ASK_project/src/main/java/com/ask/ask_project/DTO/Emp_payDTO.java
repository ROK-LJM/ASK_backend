package com.ask.ask_project.DTO;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("emp_payDTO")
@Data
public class Emp_payDTO {
    private int empPayID;
    private String compCode;
    private String payCode;
    private String payName;
    private String taxFreeCode;
    private String taxFreeName;
    private String payType;
    private String payCalc;
    private String[] deleteEmpPay;
}
