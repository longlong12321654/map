package com.hndist.cim.gisserver.entity;

/**
 * @author 河南数慧信息技术有限公司
 * @version V1.0.0
 * @ClassName: 坐标系
 * @Description: TODO
 * @date 2022/12/10 11:00
 * @Copyright: www.hndist.com All rights reserved.
 * 注意：本内容仅限于共享研发中心内部传阅，禁止外泄以及用于其他的商业目
 */
public class Reference {

    private String wkid;

    private String latestWkid;

    public String getWkid() {
        return wkid;
    }

    public void setWkid(String wkid) {
        this.wkid = wkid;
    }

    public String getLatestWkid() {
        return latestWkid;
    }

    public void setLatestWkid(String latestWkid) {
        this.latestWkid = latestWkid;
    }
}
