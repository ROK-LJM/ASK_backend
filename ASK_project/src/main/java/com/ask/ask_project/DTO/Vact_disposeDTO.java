package com.ask.ask_project.DTO;

import lombok.Data;
import org.apache.ibatis.type.Alias;

//휴가처리
@Data
@Alias("vact_disposeDTO")
public class Vact_disposeDTO {
    private String compCode;
    private int disposeVactListId;
    private String vactStartDate;
    private String vactEndDate;
    private String empName;
    private String depName;
    private int vactPeriod;
    private String vactName;
    private String vactDetail;
    private String vactState;
    private String vactNote;
}
