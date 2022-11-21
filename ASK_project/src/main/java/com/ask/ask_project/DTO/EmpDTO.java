package com.ask.ask_project.DTO;

import lombok.Data;
import org.apache.ibatis.type.Alias;

// 사원 정보 DTO
@Alias("empDTO")
@Data
public class EmpDTO {
    private String empNum;          //사원번호
    private String empName;         //사원명
    private String empSSN;          //주민등록번호
    private String empPhone;        //전화번호
    private String empEmail;        //이메일
    private String depCode;         //부서코드
    private String depName;         //부서명
    private String empRank;         //직위/직급
    private String empStart;        //입사일
    private String empEnd;          //퇴사일
    private String totalVacation;   //총휴가
    private String remindVacation;  //잔여휴가
    private String empEndReason;    //퇴사사유
    private String bankName;        //은행명
    private String bankNum;         //계좌번호
    private String bankOwner;       //예금주
    private String empFamilyNum;    //부양가족수
    private String empPay;          //기본급여
    private String empAddress;      //주소
    private String empPhoto;        //사진 ( 경로 )
    private String empResume;       //이력서 ( 경로 )
}
