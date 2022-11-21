package com.ask.ask_project.DTO;

import lombok.Data;
import org.apache.ibatis.type.Alias;

// 부서 정보 DTO
@Alias("depDTO")
@Data
public class DepDTO {
    private String compCode;
    private String depCode;
    private String depName;
    private String depDetail;
}
