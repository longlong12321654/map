package com.hndist.framework.entity;

import java.util.List;

public class OutGeometry {


    private CustomSpatialReference spatialReference;
    private  List<List<Double[]>> rings;

    public CustomSpatialReference getSpatialReference() {
        return spatialReference;
    }

    public void setSpatialReference(CustomSpatialReference spatialReference) {
        this.spatialReference = spatialReference;
    }

    public List<List<Double[]>> getRings() {
        return rings;
    }

    public void setRings(List<List<Double[]>> rings) {
        this.rings = rings;
    }
}
