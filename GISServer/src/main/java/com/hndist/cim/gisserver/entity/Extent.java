package com.hndist.cim.gisserver.entity;

/**
 * @author 河南数慧信息技术有限公司
 * @version V1.0.0
 * @ClassName: 地图范围
 * @Description: TODO
 * @date 2022/12/10 10:59
 * @Copyright: www.hndist.com All rights reserved.
 * 注意：本内容仅限于共享研发中心内部传阅，禁止外泄以及用于其他的商业目
 */
public class Extent {

    private Double xmin;

    private Double ymin;

    private Double xmax;

    private Double ymax;

    private Reference spatialReference;

    public Extent(){

    }

    public Extent(double xmin,double ymin,double xmax,double ymax,Reference spatialReference){
        this.xmin = xmin;
        this.ymin = ymin;
        this.xmax = xmax;
        this.ymax = ymax;
        this.spatialReference = spatialReference;
    }

    public Double getXmin() {
        return xmin;
    }

    public void setXmin(Double xmin) {
        this.xmin = xmin;
    }

    public Double getYmin() {
        return ymin;
    }

    public void setYmin(Double ymin) {
        this.ymin = ymin;
    }

    public Double getXmax() {
        return xmax;
    }

    public void setXmax(Double xmax) {
        this.xmax = xmax;
    }

    public Double getYmax() {
        return ymax;
    }

    public void setYmax(Double ymax) {
        this.ymax = ymax;
    }

    @Override
    public String toString() {
        return xmin +
                "," + ymin +
                "," + xmax +
                "," + ymax ;
    }
}
