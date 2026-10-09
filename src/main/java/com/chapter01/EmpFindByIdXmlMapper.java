package com.chapter01;

import com.entity.Emp;
import org.apache.ibatis.annotations.Param;

/**
 * XML方式 - 按ID查
 */
public interface EmpFindByIdXmlMapper {

    Emp findByIdXml(@Param("empno") Integer empno);
}