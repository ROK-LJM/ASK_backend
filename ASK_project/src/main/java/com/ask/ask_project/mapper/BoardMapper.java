package com.ask.ask_project.mapper;

import org.apache.ibatis.annotations.Mapper;
//멤버 mapper
@Mapper
public interface BoardMapper {
    int loginCheck() throws Exception;
    int insert_memberInfo() throws Exception;
}
