package com.hndist.framework.entity;

public class CustomSpatialReference {
    int wkid;

    public int getWkid() {
        return wkid;
    }

    public void setWkid(int wkid) {
        this.wkid = wkid;
    }

    @Override
    public String toString() {
        return "SpatialReference{" +
                "wkid=" + wkid +
                '}';
    }
}
