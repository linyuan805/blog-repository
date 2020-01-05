package com.linyuan.blog.service;

import com.linyuan.blog.domain.Blog;
import com.linyuan.blog.domain.BlogTypeVO;

import java.util.List;
import java.util.Map;

/**
 * @author LinYuan
 * @version 1.0
 * @date 2019/11/20 19:44
 */
public interface BlogService {
    /**
     * 根据id查询
     * @param id
     * @return
     */
    Blog findBlogById(long id);

    /**
     * 根据id查找blog并且转成markdown
     * @param id
     * @return
     */
    Blog findAndConvertBlogById(Long id);

    /**
     * 根据条件查找所有blog
     * @param blog
     * @return
     */
    List<Blog> findAllBlog(BlogTypeVO blog);

    /**
     * 插入一条博客
     * @param blog
     * @return
     */
    Integer insertBlog(Blog blog);

    /**
     * 更新博客
     * @param blog
     * @return
     */
    Integer updateBlog(Blog blog);

    /**
     * 删除博客
     * @param id
     * @return
     */
    Integer deleteBlogById(long id);

    /**
     * 根据blogId和tagIdList插入多对对关系
     * @param blogId
     * @param tagIdList
     * @return
     */
    Integer insertBlogTagId(Long blogId, String tagIdList);

    /**
     * 根据blogid删除blog_tag表的对应关系
     * @param id
     */
    void deleteBlogTagId(Long id);

    /**
     * 根据搜索框内容插叙标题和内容
     * @param query
     * @return
     */
    List<Blog> findBlogByQuery(String query);

    /**
     * 增加浏览次数
     * @param blogId
     */
    void updateViews(Long blogId);

    /**
     * 根据tagId查询所有blog
     * @param tagId
     * @return
     */
    List<Blog> findBlogByTagId(Long tagId);

    /**
     * 根据年份归档博客
     * @return
     */
    Map<String, List<Blog>> archiveBlog();

    /**
     * 查询博客数目
     * @return
     */
    Long countBlog();

    /**
     * 查询指定数量博客
     * @param top
     * @return
     */
    List<Blog> findBlogRecommendTop(Integer top);


}
