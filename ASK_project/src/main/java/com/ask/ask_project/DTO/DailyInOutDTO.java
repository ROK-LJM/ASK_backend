package com.ask.ask_project.DTO;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("dailyInOutDTO")
@Data
public class DailyInOutDTO {
    private String compCode;                // 회사코드
    private int dailyListId;                // 출퇴근리스트ID
    private String dailyDate;               // 날짜
    private String dailyCode;               // 일용직원 코드
    private String dailyName;               // 성명
    private String dailyRank;               // 직급
    private String dailyInOutStart;         // 출근시간
    private String dailyInOutEnd;           // 퇴근시간
    private String dailyInOutDetail;        // 상세내용
    private String dailyInOutOver;          // 초과근무시간
    private String dailyInOutNight;         // 야간근무시간
    private String dailyPayType;            // 연장 지급유형
    private String dailyPayTypeNight;       // 야간 지급유형

    // 점심시간 뺄 때 사용하는 변수
    private int lunchTime;                  // 점심시간

    private String dailyStartDate;          // 검색 시작 날짜
    private String dailyEndDate;            // 검색 마지막 날짜
}