package com.linyuan.blog.domain;

import java.util.ArrayList;
import java.util.List;


/**
 * @author LinYuan
 * @version 1.0
 * @date 2019/11/1 23:04
 */


public class Type {


    private Long typeId;

    private String typeName;

    private List<Blog> blogList;

    public Type() {
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<Blog> getBlogList() {
        return blogList;
    }

    public void setBlogList(List<Blog> blogList) {
        this.blogList = blogList;
    }

    @Override
    public String toString() {
        return "Type{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                ", blogList=" + blogList +
                '}';
    }
}
