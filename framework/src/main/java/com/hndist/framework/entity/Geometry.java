package com.hndist.framework.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author FWY
 * @version V1.0.0
 * @ClassName: Geometry
 * @Description: TODO
 * @date 2022/6/8 10:17 上午
 * @Copyright: http://www.hndist.com All rights reserved.
 * 注意：本内容仅限于河南数慧信息技术有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class Geometry implements Serializable {

    //经纬度信息
    private ArrayList[] rings = null;


    public ArrayList[] getRings() {
        return rings;
    }

    public void setRings(ArrayList[] rings) {
        this.rings = rings;
    }
}
