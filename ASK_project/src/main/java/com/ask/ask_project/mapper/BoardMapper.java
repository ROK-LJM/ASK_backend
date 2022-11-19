package com.ask.ask_project.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {
    int loginCheck() throws Exception;

}
