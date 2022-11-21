package com.ask.ask_project.DTO;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("inOut_infoDTO")
public class InOut_infoDTO {
    private String compCode;
    private String inOutDate;
    private String empCode;
    private String empName;
    private String depName;
    private String inOutStart;
    private String inOutEnd;
    private int inOutOver;
}
