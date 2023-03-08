package com.hndist.cim.gisserver.entity;

/**
 * @author 河南数慧信息技术有限公司
 * @version V1.0.0
 * @ClassName: Parsing
 * @Description: TODO
 * @date 2023/1/31 09:25
 * @Copyright: www.hndist.com All rights reserved.
 * 注意：本内容仅限于共享研发中心内部传阅，禁止外泄以及用于其他的商业目
 */
public class Parsing {

    //路径
    private String path;

    //类型 arcgis/shmap,默认shmap
    private String type;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
