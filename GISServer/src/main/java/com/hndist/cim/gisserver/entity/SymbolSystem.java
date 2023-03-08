package com.hndist.cim.gisserver.entity;

import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 河南数慧信息技术有限公司
 * @version V1.0.0
 * @ClassName: SymbolSystem
 * @Description: 符号系统
 * @date 2022/11/23 16:10
 * @Copyright: www.hndist.com All rights reserved.
 * 注意：本内容仅限于共享研发中心内部传阅，禁止外泄以及用于其他的商业目
 */
public class SymbolSystem implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 值字段
     */
    private String valueFileds;
    /**
     * 符号列表
     */
    private List<LayerSymbol> symbolList;

    /**
     * 显示类型 1要素2类别3数量4图表5多个属性
     */
    private String displayType;

    public SymbolSystem() {
    }

    public SymbolSystem(String valueFileds, List<LayerSymbol> symbolList) {
        this.valueFileds = valueFileds;
        this.symbolList = symbolList;
    }

    public String getValueFileds() {
        return valueFileds;
    }

    public void setValueFileds(String valueFileds) {
        this.valueFileds = valueFileds;
    }

    public List<LayerSymbol> getSymbolList() {
        if (CollectionUtils.isEmpty(symbolList)) {
            symbolList = new ArrayList<>();
//            LayerSymbol layerSymbol = new LayerSymbol();
//            layerSymbol.setValue("<其他所有值>");
//            layerSymbol.setTag("<其他所有值>");
//            layerSymbol.setWith("0.4");
//            symbolList.add(layerSymbol);
        }
        return symbolList;
    }

    public void setSymbolList(List<LayerSymbol> symbolList) {
        this.symbolList = symbolList;
    }

    public String getDisplayType() {
        return displayType;
    }

    public void setDisplayType(String displayType) {
        this.displayType = displayType;
    }

    @Override
    public String toString() {
        return "SymbolSystem{" +
                "valueFileds='" + valueFileds + '\'' +
                ", symbolList=" + symbolList +
                ", displayType='" + displayType + '\'' +
                '}';
    }
}
