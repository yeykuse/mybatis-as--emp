package com.lab.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.math.BigDecimal;

@TableName("salgrade")
public class Salgrade {
    @TableId(value = "grade", type = IdType.AUTO)
    private Integer grade;

    private BigDecimal losal;
    private BigDecimal hisal;

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public BigDecimal getLosal() {
        return losal;
    }

    public void setLosal(BigDecimal losal) {
        this.losal = losal;
    }

    public BigDecimal getHisal() {
        return hisal;
    }

    public void setHisal(BigDecimal hisal) {
        this.hisal = hisal;
    }

    @Override
    public String toString() {
        return "Salgrade{grade=" + grade + ", losal=" + losal + ", hisal=" + hisal + "}";
    }
}