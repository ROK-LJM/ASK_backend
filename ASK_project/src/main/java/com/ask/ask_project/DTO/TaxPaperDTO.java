package com.ask.ask_project.DTO;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("TaxPaperDTO")
public class TaxPaperDTO {
    private int  purchaseId;
    private int[] purchaseList;
    private String compCode;

    private String compNum;     //사업자번호
    private String compName;    //상호
    private String compCEO;     //대표자명
    private String compAddress; //주소
    private String compType;    //업태
    private String compItems;   //종목
    private String compEmail;   //이메일

    private String clientCompNum;   //거래처 사업자번호
    private String clientName;      //거래처 상호
    private String clientCEO;       //거래처 대표자명
    private String clientAddress;   //거래처 주소
    private String clientstate;     //거래처 업태
    private String clientevent;     //거래처 종목
    private String clientEmail;     //거래처 이메일

    private String p_date; //날짜
    private String p_item; //품목
    private int p_iCount; //수량
    private int p_unitPrice; //단가
    private int p_supplyValue; //공급가액
    private int p_surTax; //부가세
    private int p_totalPrice; //합계

    private int TotalsupplyValue;   //공급가액합계
    private int TotalsurTax;    //부가세 합계
    private int TotalPrice;     //합계금액 합계
}