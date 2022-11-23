package com.ask.ask_project.DTO;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

//보유휴가 현황
@Data
@Alias("vact_listDTO")
public class Vact_listDTO {
     private String compCode;
     private String empNum;
     private String depName;
     private String empName;
     private int totalVacation;
     private int takeVacation;
     private int remindVacation;
}
