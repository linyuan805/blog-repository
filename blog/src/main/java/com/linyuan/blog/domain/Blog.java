package com.linyuan.blog.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author LinYuan
 * @version 1.0
 * @date 2019/11/1 23:04
 */

public class Blog {

    private Long id;
    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;
    /**
     * 首图地址
     */
    private String firstPicture;

    /**
     * 博客描述
     */
    private String description;

    /**
     * 原创/转载/翻译
     */
    private String flag;
    /**
     * 阅览数目
     */
    private Integer views;

    /**
     * 赞赏
     */
    private boolean appreciation;
    /**
     * 可否分享
     */
    private boolean shareStatement;

    /**
     * 可否评论
     */
    private boolean commentEnable;

    /**
     * 是否发布
     */
    private boolean published;
    /**
     * 是否推荐
     */
    private boolean recommend;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 类型
     */
    private Type type;
    /**
     * 标签
     */
    private List<Tag> tagList = new ArrayList<>();
    /**
     * 用户
     */
    private User user;

    /**
     * 评论
     */
    private List<Comment> commentList = new ArrayList<>();
    /**
     * 新建博客时存储的tagId
     */
    private String tagIdList;

    private Long typeId;

    public void initTagIdList() {
        this.tagIdList = tagsToIds(this.getTagList());
    }

    /**
     * 编辑时根据blog的tagList集合转化为字符串1，2，3，4
     * @param tags
     * @return
     */
    private String tagsToIds(List<Tag> tags) {
        if (!tags.isEmpty()) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getTagId());
            }
            return ids.toString();
        } else {
            return tagIdList;
        }
    }

    public Blog() {
    }

    public Blog(Long id, String title, String content, String firstPicture,
                String description, String flag, Integer views,
                boolean appreciation, boolean shareStatement,
                boolean commentEnable, boolean published, boolean recommend,
                Date createTime, Date updateTime, Type type, List<Tag> tagList,
                User user, List<Comment> commentList, String tagIdList) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.firstPicture = firstPicture;
        this.description = description;
        this.flag = flag;
        this.views = views;
        this.appreciation = appreciation;
        this.shareStatement = shareStatement;
        this.commentEnable = commentEnable;
        this.published = published;
        this.recommend = recommend;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.type = type;
        this.tagList = tagList;
        this.user = user;
        this.commentList = commentList;
        this.tagIdList = tagIdList;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }

    public boolean isShareStatement() {
        return shareStatement;
    }

    public void setShareStatement(boolean shareStatement) {
        this.shareStatement = shareStatement;
    }

    public boolean isCommentEnable() {
        return commentEnable;
    }

    public void setCommentEnable(boolean commentEnable) {
        this.commentEnable = commentEnable;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public String getTagIdList() {
        return tagIdList;
    }

    public void setTagIdList(String tagIdList) {
        this.tagIdList = tagIdList;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", recommend=" + recommend +
                ", updateTime=" + updateTime +
                ", type=" + type +
                ", tagList=" + tagList +
                '}';
    }
}
