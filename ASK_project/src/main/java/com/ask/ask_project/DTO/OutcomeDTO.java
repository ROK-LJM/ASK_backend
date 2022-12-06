package com.ask.ask_project.DTO;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("OutcomeDTO")
public class OutcomeDTO {
    private String compCode;    //회사코드
    private int SalesId;        //id.pk
    private String s_division;  //구분
    private String s_date;      //날짜
    private String s_taxType;   //유형
    private String s_Item;      //품목
    private String clientstate; //업태
    private String clientevent; //종목
    private int  s_iCount;      //수량
    private int s_unitPrice;    //단가
    private int s_supplyValue;  //공급가액
    private int s_surTax;       //부가세
    private int s_totalPrice;   //합계
    private String s_clientCompNum;     //사업자등록번호
    private String s_clientName;        //거래처명
    private int totalsupplyValue; //공급가액 합계
    private int totalsurTax; // 부가세 합계
    private int total; //합계

    private String startDate;   //검색
    private String endDate;
}
