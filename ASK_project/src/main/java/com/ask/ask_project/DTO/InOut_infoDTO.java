package com.ask.ask_project.DTO;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("inOut_infoDTO")
public class InOut_infoDTO {
    private String compCode;        // 회사코드
    private int inOutListId;        // 리스트 증가수 pk
    private String inOutDate;       // 날짜
    private String empCode;         // 사원코드
    private String empRank;         // 직급
    private String empName;         // 사원명
    private String depCode;         // 부서코드
    private String depName;         // 부서명
    private String inOutStart;      // 출근시간
    private String inOutEnd;        // 퇴근시간
    private int inOutOver;          // 초과근무시간
    private int intOutNight;        // 야간근무시간
    private String inOut_Note;       // 비고
    private String payType;         // 연장 지급유형
    private String payTypeNight;    // 야간 지급유형

    // 테이블에는 없고 계산하는 용도의 변수들
    private int[] deleteList;       //삭제할 아이디
    private String startDate;       //시작날짜
    private String endDate;         //종료날짜
    private String nightTime;       // 야간
}
