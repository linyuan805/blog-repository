package com.linyuan.blog.dao;

import com.linyuan.blog.domain.Blog;
import com.linyuan.blog.domain.BlogTypeVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LinYuan
 * @version 1.0
 * @date 2019/11/20 19:50
 */
@Repository
public interface BlogMapper {
    /**
     * 根据id查询blog
     * @param id
     * @return
     */
    Blog findBlogById(long id);

    /**
     * 根据条件查询blog
     * @param blog
     * @return
     */
    List<Blog> findAllBlog(BlogTypeVO blog);

    /**
     * 插入blog
     * @param blog
     * @return
     */
    Integer insertBlog(Blog blog);

    /**
     * 更新博客
     * @param blog
     * @return
     */
    Integer updateBlog( Blog blog);

    /**
     * 删除博客
     * @param id
     * @return
     */
    Integer deleteBlogById(long id);

    /**
     * 给tb_blog_tag表插入多对多关系
     * @param blogId
     * @param tagIdInt
     * @return
     */
    Integer insertBlogTagId(@Param("blogId") Long blogId, @Param("tagIdInt") Integer[] tagIdInt);

    /**
     * 删除blog_tag表的对应关系
     * @param id
     */
    void deleteBlogTagId(Long id);

    /**
     * 根据搜索框查询标题和内容
     * @param query
     * @return
     */
    List<Blog> findBlogByQuery(@Param("query") String query);

    /**
     * 浏览次数加1
     * @param blogId
     */
    void updateViewsByBlogId(Long blogId);

    /**
     * 根据tagId查到所有blog
     * @param tagId
     * @return
     */
    List<Blog> findBlogByTagId(Long tagId);

    /**
     * 查找所有的年份
     * @return
     */
    List<String> findGroupYear();

    /**
     * 根据年份归档博客
     * @param years
     * @return
     */
    List<Blog> findBlogByYear(String years);

    /**
     * 查询博客数目
     * @return
     */
    Long countBlog();

    /**
     * 查询推荐的前几篇博客按照时间
     * @param top
     * @return
     */
    List<Blog> findBlogRecommendTop(Integer top);
}
