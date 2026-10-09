package com.chapter01;

import org.apache.ibatis.annotations.Param;

/**
 * XML方式 - 删除
 */
public interface EmpDeleteXmlMapper {

    int deleteEmpXml(@Param("empno") Integer empno);
}