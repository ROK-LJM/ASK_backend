package com.ask.ask_project.DTO;

import lombok.Data;
import org.apache.ibatis.type.Alias;
@Alias("empDTO")
@Data
public class EmpDTO {
    private int empId;              //사원리스트ID
    private String compCode;        //회사코드
    private String empName;         //사원명
    private String empNum;          //사원번호
    private String empSSN;          //주민등록번호
    private String empPhone;        //전화번호
    private String empEmail;        //이메일
    private String depCode;         //부서코드
    private String depName;         //부서명
    private String empRank;         //직위/직급
    private String empStart;        //입사일
    private String empEnd;          //퇴사일
    private int totalVacation;      //총휴가
    private int remindVacation;     //잔여휴가
    private String empEndReason;    //퇴사사유
    private String bankName;        //은행명
    private String bankNum;         //계좌번호
    private String bankOwner;       //예금주
    private int empFamilyNum;       //부양가족수
    private int empPay;             //기본급여
    private String empAddress;      //주소
    private String empPhoto;        //사진 ( 경로 )
    private String empResume;       //이력서 ( 경로 )
    private int mealPay;         //식대
    private int carPay;          //차량유지비
    private int childPay;        //출산보육수당


    private int takeVacation; // 사용휴가


    // DB에 저장 혹은 로드하는건 아니지만, 서버에서 처리해야할 데이터
    private String empFirstSSN;     // 주민번호(앞)
    private String empSecondSSN;    // 주민번호(뒤)
    private String empStartYear;    // 입사일(year)
    private String empStartMonth;   // 입사일(month)
    private String empStartDay;     // 입사일(day)
    private String empEndYear;      // 퇴사일(year)
    private String empEndMonth;     // 퇴사일(month)
    private String empEndDay;       // 퇴사일(day)

    private String empSearchMonth; //항목수당 날짜검색시 받아올변수

    // [급여계산 용도]
    private String startDate;
    private String endDate;
}