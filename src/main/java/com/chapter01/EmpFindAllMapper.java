package com.chapter01;

import com.entity.Emp;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 注解方式 - 查全部
 */
public interface EmpFindAllMapper {

    @Select("SELECT * FROM emp")
    List<Emp> findAll();
}