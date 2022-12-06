package com.ask.ask_project.DTO;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("dailySalaryStatement")
public class DailySalaryStatementDTO {
    private String compCode;        // 회사코드
    private int statementId;        // 일용직 급여 리스트ID
    private String dailyCode;       // 일용직원 코드
    private String dailyPayTitle;   // 급여대상명칭
    private String dailyStartDate;  // 대상기간시작
    private String dailyEndDate;    // 대상기간끝
    private String dailyPayDay;     // 지급일
    private String dailyRank;       // 직급/직위
    private String dailyName;       // 성명
    private int dailyPay;           // 일급
    private int dailyWeeklyPay;     // 주휴수당
    private double dailyOvertimePay;   // 연장근무수당
    private double dailyNightTimePay;   // 야간근무수당
    private double dailyWeekendPay;    // 주말근무수당
    private int dailyAnnual;        // 연차수당
    private int dailyFoodPay;       // 식대
    private int dailyCarStatePay;   // 차량유지비
    private int dailyIncomeTax;     // 소득세
    private int dailyLocalTaxes;    // 지방소득세
    private int dailyNtnlPnsn;      // 국민연금
    private int dailyHlthInsrn;     // 건강보험
    private int dailyEmpIns;        // 고용보험
    private int dailyIngTrmCrIns;   // 장기요양보험
    private int dailyTotalPay;      // 지급총액
    private int dailyDdctn;         // 공제금액
    private int dailyActlPymnt;     // 실지급액
    private int dailyTotalAddPay;   // 각 일용직원 별 총 추가수당
    private int dailyTotalAddTax;   // 각 일용직원 별 총 세금
    private int dailySumTotalAdd;   // 각 일용직원 별 총 합계
    private int dailyExpense;        // 경비

    private String[] printButton;
}