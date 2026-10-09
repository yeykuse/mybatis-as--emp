package com.chapter01;

import com.entity.Emp;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 注解方式 - 按ID查
 */
public interface EmpFindByIdMapper {

    @Select("SELECT * FROM emp WHERE empno = #{empno}")
    Emp findById(@Param("empno") Integer empno);
}