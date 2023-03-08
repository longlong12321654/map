package com.hndist.framework.entity;

/**
 * @author FWY
 * @version V1.0.0
 * @ClassName: Field
 * @Description: TODO
 * @date 2022/6/13 4:12 下午
 * @Copyright: http://www.hndist.com All rights reserved.
 * 注意：本内容仅限于河南数慧信息技术有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class Field {

    //字段名称
    private String name;
    //字段类型
    private String type;
    //字段别名
    private String alias;

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
}
