package com.hndist.server.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Treeselect树结构实体类
 * 
 * @author ruoyi
 */
public class GeoTreeSelect implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 节点ID */
    private Long id;

    /** 节点名称 */
    private String label;

    /** 子节点 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<GeoTreeSelect> children;

    public GeoTreeSelect() { }

    public GeoTreeSelect(OmpMenuBase menu)
    {
        this.id = menu.getMenuId();
        this.label = menu.getName();
        this.children = menu.getChildren().stream().map(GeoTreeSelect::new).collect(Collectors.toList());
    }

    public GeoTreeSelect(OmpMenuSys menu)
    {
        this.id = menu.getMenuId();
        this.label = menu.getName();
        this.children = menu.getChildren().stream().map(GeoTreeSelect::new).collect(Collectors.toList());
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public List<GeoTreeSelect> getChildren()
    {
        return children;
    }

    public void setChildren(List<GeoTreeSelect> children)
    {
        this.children = children;
    }
}
