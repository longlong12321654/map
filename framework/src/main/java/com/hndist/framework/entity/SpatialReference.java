package com.hndist.framework.entity;

import java.io.Serializable;

/**
 * @author FWY
 * @version V1.0.0
 * @ClassName: SpatialReference
 * @Description: TODO
 * @date 2022/6/6 4:30 下午
 * @Copyright: http://www.hndist.com All rights reserved.
 * 注意：本内容仅限于河南数慧信息技术有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class SpatialReference implements Serializable {

    private int latestWkid;

    private int wkid;

    public int getLatestWkid() {
        return latestWkid;
    }

    public void setLatestWkid(int latestWkid) {
        this.latestWkid = latestWkid;
    }

    public int getWkid() {
        return wkid;
    }

    public void setWkid(int wkid) {
        this.wkid = wkid;
    }
}
