package com.chapter01;

import com.entity.Emp;
import org.apache.ibatis.annotations.Update;

/**
 * 注解方式 - 修改
 */
public interface EmpUpdateMapper {

    @Update("UPDATE emp SET ename = #{ename}, job = #{job}, mgr = #{mgr}, " +
            "sal = #{sal}, comm = #{comm}, deptno = #{deptno} WHERE empno = #{empno}")
    int updateEmp(Emp emp);
}