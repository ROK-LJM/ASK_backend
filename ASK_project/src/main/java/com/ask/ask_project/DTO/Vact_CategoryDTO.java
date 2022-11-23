package com.ask.ask_project.DTO;

import lombok.Data;
import org.apache.ibatis.type.Alias;

//휴가항목 등록
@Data
@Alias("vactCategoryDTO")
public class Vact_CategoryDTO {
    private String compCode;
    private int vactNameListId;
    private String vactCode;
    private String vactName;
    private String vactDetail;
    private String[] deleteVactCode;    //삭제할 휴가항목코드
}
