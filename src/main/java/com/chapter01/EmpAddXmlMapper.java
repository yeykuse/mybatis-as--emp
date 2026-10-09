package com.chapter01;

import com.entity.Emp;

/**
 * XML方式 - 新增 + 自增主键回填
 */
public interface EmpAddXmlMapper {

    int addEmpXml(Emp emp);
}