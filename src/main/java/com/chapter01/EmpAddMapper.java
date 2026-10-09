package com.chapter01;

import com.entity.Emp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

/**
 * 注解方式 - 新增 + 自增主键回填
 */
public interface EmpAddMapper {

    @Insert("INSERT INTO emp(ename, job, mgr, hiredate, sal, comm, deptno) " +
            "VALUES(#{ename}, #{job}, #{mgr}, #{hiredate}, #{sal}, #{comm}, #{deptno})")
    @Options(useGeneratedKeys = true, keyProperty = "empno")
    int addEmp(Emp emp);
}