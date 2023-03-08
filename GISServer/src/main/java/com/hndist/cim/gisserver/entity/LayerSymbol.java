package com.hndist.cim.gisserver.entity;

import com.hndist.cim.gisserver.utils.ColorUtil;
import com.hndist.cim.gisserver.utils.StringUtils;

/**
 * @author 河南数慧信息技术有限公司
 * @version V1.0.0
 * @ClassName: LayerSymbol
 * @Description: TODO
 * @date 2022/11/23 16:10
 * @Copyright: www.hndist.com All rights reserved.
 * 注意：本内容仅限于共享研发中心内部传阅，禁止外泄以及用于其他的商业目
 */
public class LayerSymbol {

    //颜色
    private String color;
    /**
     * 轮廓颜色
     */
    private String outlineColor;

    /**
     * 轮廓宽度
     */
    private int width;
    /**
     * 值
     */
    private String value;
    /**
     * 标注
     */
    private String tag;
    /**
     * 计数
     */
    private Integer count;

    public LayerSymbol() {

    }

    public LayerSymbol(String value, String tag, Integer count) {
        this.value = value;
        this.tag = tag;
        this.count = count;
    }

    public String getColor() {
        if(color==null){
            color = ColorUtil.sjys().toString();
        }
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOutlineColor() {
        return outlineColor;
    }

    public void setOutlineColor(String outlineColor) {
        this.outlineColor = outlineColor;
    }

    public int getWidth() {
        if (width == 0) {
            width = 5;
        }
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getCount() {
        if (count == null) {
            count = 0;
        }
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
