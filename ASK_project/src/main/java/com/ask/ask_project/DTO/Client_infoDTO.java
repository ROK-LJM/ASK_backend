package com.ask.ask_project.DTO;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("Client_infoDTO")
public class Client_infoDTO {
    private String compCode;
    private String clientCompNum;   //사업자등록번호
    private String clientName;      //거래처명
    private String clientCEO;       //대표자명
    private String clientstate;     //업태
    private String clientevent;     //종목
    private String clientAddress;   //주소
    private String clientPhone;       //전화번호
    private String clientEmail;      //이메일
    private String clientBankName;  //은행명
    private String clientBankNum;   //계좌번호
    private String clientBankOwner; //예금주
    private String clientDetail;     //상세내용

    private String startDate;   //시작날짜
    private String endDate;     //종료날짜
}
