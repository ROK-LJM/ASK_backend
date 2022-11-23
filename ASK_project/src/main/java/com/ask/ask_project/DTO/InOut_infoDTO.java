package com.ask.ask_project.DTO;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("inOut_infoDTO")
public class InOut_infoDTO {
    private int inOutListId;    //리스트 증가수 pk
    private String compCode;
    private String inOutDate;
    private String empCode;
    private String empName;
    private String depName;
    private String inOutStart;
    private String inOutEnd;
    private int inOutOver;

    private int[] deleteList;   //삭제할 아이디
    private String startDate; //시작날짜
    private String endDate; //종료날짜
}
