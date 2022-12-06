package com.ask.ask_project.DTO;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("income_outcomeDTO")
public class IncomeDTO {
    private String compCode;    //회사코드
    private int purchaseId;     //매입id pk
    private String p_division;  //구분
    private String p_date;      //날짜
    private String p_taxType;   //유형
    private String p_item;      //품목
    private int p_iCount;       //수량
    private int p_unitPrice;    //단가
    private int p_supplyValue;  //공급가액
    private int p_surTax;       //부가세
    private int p_totalPrice;   //합계
    private String p_clientCompNum; //사업자등록번호
    private String  p_clientName;   //거레처명
    private String taxBill;     //파일경로



    private int totalsupplyValue; //공급가액 합계
    private int totalsurTax; // 부가세 합계
    private int total; //합계

    private int totalsupplyValue1; //공급가액 합계
    private int totalsurTax1; // 부가세 합계
    private int total1; //합계


    private String startDate;   //시작날짜
    private String endDate;     //종료날짜

}
