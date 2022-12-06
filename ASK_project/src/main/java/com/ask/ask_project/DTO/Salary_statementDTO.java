package com.ask.ask_project.DTO;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("Salary_statementDTO")
public class Salary_statementDTO {
    private String compCode;        // 회사코드
    private String payStatementId;  // 급여명세서아이디 pk
    private String payTitle;        // 급여대장명칭
    //    private String monthYear;       // 대상기간
    private String payDay;          // 지급일
    private String empNum;          // 사원번호
    private String empName;         // 사원명
    private String depName;         // 부서명
    private int empPay;             // 기본급
    private int weeklyPay;          // 주휴수당
    private double nightTimePay;       // 야간근무수당
    private double overtimePay;        // 연장근무수당
    private int weekendPay;         // 주말근무수당
    private int annualAllowance;    // 연차수당
    private int dpndnAlwnc;         // 부양가족수당
    private int incomeTax;          // 소득세
    private int localTaxes;         // 지방소득세
    private int ntnlPnsn;           // 국민연금
    private int hlthInsrn;          // 건강보험
    private int empIns;             // 고용보험
    private int lngTrmCrIns;        // 장기요양보험
    private int chldbChalw;         // 출산보육수당
    private int foodPay;            // 식대
    private int carStatePay;        // 차량유지비
    private int Expense;            // 경비
    private double totalPay;           // 지급총액
    private int ddctn;              // 공제금액
    private double actlPymnt;          // 실지급액

    private double totalAddPay;     // 각 사원별 총 추가수당
    private double totalAddTax;     // 각 사원별 세금+경비값
    private double sumTotalAdd;     // 각 사원별 합계

    private String startDate;       // 대상기간시작
    private String endDate;         // 대상기간끝

    private int sumWage;            // 기본급 합
    private double sumPay;             // 추가수당 합
    private double sumTax;             // 세금+경비 합
    private double totalSum;           // 기본급, 추가수당, 세금 총 합

    private String[] printButton;
}
