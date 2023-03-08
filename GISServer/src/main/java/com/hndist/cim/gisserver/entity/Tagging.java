package com.hndist.cim.gisserver.entity;

import java.io.Serializable;

/**
 * @author zy
 * @version 1.1
 * @className Tagging
 * @description 标注
 * @date 2022/12/7 10:49
 **/
public class Tagging implements Serializable {
    private static final long serialVersionUID = 1L;

    private String tagFiled;
    private String tagColor;
    private String tagSize;
    private String tagFont;

    public String getTagFiled() {
        return tagFiled;
    }

    public void setTagFiled(String tagFiled) {
        this.tagFiled = tagFiled;
    }

    public String getTagColor() {
        return tagColor;
    }

    public void setTagColor(String tagColor) {
        this.tagColor = tagColor;
    }

    public String getTagSize() {
        return tagSize;
    }

    public void setTagSize(String tagSize) {
        this.tagSize = tagSize;
    }

    public String getTagFont() {
        return tagFont;
    }

    public void setTagFont(String tagFont) {
        this.tagFont = tagFont;
    }
}
