package com.ask.ask_project.DTO;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("depDTO")
public class DepDTO {
    private String depCode;
    private String depName;
    private String depDetail;
    private String compCode;

}
