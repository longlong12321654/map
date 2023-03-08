package com.hndist.cim.gisserver.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author FWY
 * @version V1.0.0
 * @ClassName: Layers
 * @Description: TODO
 * @date 2022/6/16 2:21 下午
 * @Copyright: http://www.hndist.com All rights reserved.
 * 注意：本内容仅限于河南数慧信息技术有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class Layers {

    //文件夹名称
    private String name;

    //定义文件夹
    List<String> folders = new ArrayList<String>();

    //定义子图层
    List<Layers> childrenLayers = new ArrayList<Layers>();

    //服务
    List<Service> services = new ArrayList<Service>();

    public List<String> getFolders() {
        return folders;
    }

    public void setFolders(List<String> folders) {
        this.folders = folders;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public List<Layers> getChildrenLayers() {
        return childrenLayers;
    }

    public void setChildrenLayers(List<Layers> childrenLayers) {
        this.childrenLayers = childrenLayers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
