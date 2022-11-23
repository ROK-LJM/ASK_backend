package com.ask.ask_project.DTO;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("taxDTO")
@Data
public class TaxDTO {
    private String taxInfoID;
    private String compCode;
    private String taxCode;
    private String taxName;
    private String taxItem;
    private String taxNote;
    private String[] deleteTaxList;
}