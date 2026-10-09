package com.chapter01;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

/**
 * 注解方式 - 删除
 */
public interface EmpDeleteMapper {

    @Delete("DELETE FROM emp WHERE empno = #{empno}")
    int deleteEmp(@Param("empno") Integer empno);
}