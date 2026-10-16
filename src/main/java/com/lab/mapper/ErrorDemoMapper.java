package com.lab.mapper;

import com.lab.entity.Emp;

import java.util.List;

public interface ErrorDemoMapper {

    List<Emp> selectAllWithError1();

    List<Emp> selectAllWithError2();

    int insertWithError3(Emp emp);
}
