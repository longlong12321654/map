package com.hndist.cim.gisserver.entity;

import java.io.Serializable;

/**
 * @author FWY
 * @version V1.0.0
 * @ClassName: Field
 * @Description: TODO
 * @date 2022/6/13 4:12 下午
 * @Copyright: http://www.hndist.com All rights reserved.
 * 注意：本内容仅限于河南数慧信息技术有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class Field implements Serializable {

    private static final long serialVersionUID = 1L;

    //字段名称
    private String name;
    //字段类型
    private String type;
    //字段别名
    private String alias;
    //是否高亮
    private Boolean highlight;
    //允许空值
    private Boolean allowNulls;

    public Field(){

    }

    public Field(String name,String type){
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Boolean getHighlight() {
        return highlight;
    }

    public void setHighlight(Boolean highlight) {
        this.highlight = highlight;
    }

    public Boolean getAllowNulls() {
        return allowNulls;
    }

    public void setAllowNulls(Boolean allowNulls) {
        this.allowNulls = allowNulls;
    }
}
