package com.linyuan.blog.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LinYuan
 * @version 1.0
 * @date 2019/12/5 17:01
 */
public class BlogTypeVO {



    private Long typeId;

    private String title;


    private Boolean recommend;

    public BlogTypeVO() {
    }

    public BlogTypeVO(Boolean recommend) {
        this.recommend = recommend;
    }

    public BlogTypeVO(Long typeId) {
        this.typeId = typeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "BlogTypeVO{" +
                ", typeId=" + typeId +
                ", title='" + title + '\'' +
                ", recommend=" + recommend +
                '}';
    }
}
