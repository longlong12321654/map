package com.hndist.server.domain.dto;

import java.io.Serializable;

/**
 * @author zy
 * @version 1.1
 * @className OutputStatistics
 * @description 输出统计
 * @date 2023/1/5 8:47
 **/
public class OutputStatistics implements Serializable {
    private static final long serialVersionUID = 1L;
    private String statisticType;
    private String onStatisticField;
    private String outStatisticFieldName;

    public String getStatisticType() {
        return statisticType;
    }

    public void setStatisticType(String statisticType) {
        this.statisticType = statisticType;
    }

    public String getOnStatisticField() {
        return onStatisticField;
    }

    public void setOnStatisticField(String onStatisticField) {
        this.onStatisticField = onStatisticField;
    }

    public String getOutStatisticFieldName() {
        return outStatisticFieldName;
    }

    public void setOutStatisticFieldName(String outStatisticFieldName) {
        this.outStatisticFieldName = outStatisticFieldName;
    }

    @Override
    public String toString() {
        return "OutputStatistics{" +
                "statisticType='" + statisticType + '\'' +
                ", onStatisticField='" + onStatisticField + '\'' +
                ", outStatisticFieldName='" + outStatisticFieldName + '\'' +
                '}';
    }
}
