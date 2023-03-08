package com.hndist.server.domain.dto;

import java.io.Serializable;

/**
 * @author zy
 * @version 1.1
 * @className MapserverToolQuery
 * @description TODO
 * @date 2022/12/1 15:59
 **/
public class MapserverToolQuery implements Serializable {
    private static final long serialVersionUID = 1L;
    private String serverUrl;
    private String serverType;

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

}
