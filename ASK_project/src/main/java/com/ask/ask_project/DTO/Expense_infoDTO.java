package com.ask.ask_project.DTO;

import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

@Data
@Alias("Expense_infoDTO")
public class Expense_infoDTO {
    private String compCode;
    private int expenseListId;
    private String empNum;
    private String empName;
    private String depCode;
    private String depName;
    private String shopName;        //매장명
    private String shopNum;         //사업자등록번호 (매장)
    private String expenseDate;     //날짜
    private String expenseName;     //항목(경비종류)
    private String history;         //내역
    private int price;              //금액
    private String documentation;   //증빙서류
    private String approval;        // 요청 승인여부
    private String approvalDate;    // 승인 날짜
    private String requestDate;     // 요청 날짜

    private String startDate;       //search 시작날짜
    private String endDate;         //search 종료날짜

    private int totalPrice;         //합계

    //-----------------------------------------------------------------
    private MultipartFile multipartFile;    // 이미지 파일을 받기위한 변수


    public Expense_infoDTO(){;}



    // s3에 업로드된 url이 들어오면 documentation에 해당 url값 세팅
    public Expense_infoDTO(String url){
        this.documentation = url;
    }
}
