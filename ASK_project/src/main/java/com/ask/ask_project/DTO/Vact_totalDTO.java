package com.ask.ask_project.DTO;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("vact_totalDTO")
public class Vact_totalDTO {
    private String empName;
    private int vactPeriod;
}
