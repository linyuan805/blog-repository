package com.linyuan.blog.service.impl;

import com.linyuan.blog.NotFoundException;
import com.linyuan.blog.dao.BlogMapper;
import com.linyuan.blog.domain.Blog;
import com.linyuan.blog.domain.BlogTypeVO;
import com.linyuan.blog.service.BlogService;
import com.linyuan.blog.util.MarkdownUtils;
import com.linyuan.blog.util.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author LinYuan
 * @version 1.0
 * @date 2019/11/20 19:48
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public Blog findBlogById(long id) {
        Blog blog = blogMapper.findBlogById(id);

        if (blog == null){
            throw new NotFoundException("该博客不存在");
        }

        return blog;
    }

    @Override
    public Blog findAndConvertBlogById(Long id) {
        Blog blog = blogMapper.findBlogById(id);
        if (blog == null){
            throw new NotFoundException("该博客不存在");
        }
        Blog blogConvert = new Blog();
        BeanUtils.copyProperties(blog, blogConvert);
        blogConvert.setContent(MarkdownUtils.markdownToHtmlExtensions(blogConvert.getContent()));
        return blogConvert;
    }

    @Override
    public List<Blog> findAllBlog(BlogTypeVO blog) {

        List<Blog> blogList = blogMapper.findAllBlog(blog);

        return blogList;
    }

    @Override
    public Integer insertBlog(Blog blog) {

        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        return blogMapper.insertBlog(blog);

    }

    @Override
    public Integer updateBlog( Blog blog) {
        Blog blogCheck = blogMapper.findBlogById(blog.getId());
        if (blogCheck == null){
            throw new NotFoundException("该博客不存在");
        }
        BeanUtils.copyProperties(blog, blogCheck, MyBeanUtils.getNullPropertyNames(blog));
        blog.setUpdateTime(new Date());
        return blogMapper.updateBlog(blog);
    }

    @Override
    public Integer deleteBlogById(long id) {
        return blogMapper.deleteBlogById(id);
    }

    @Override
    public Integer insertBlogTagId(Long blogId, String tagIdList) {

        if (tagIdList != null){
            Integer[] tagIdInt = arrayToInt(tagIdList);
            return blogMapper.insertBlogTagId(blogId, tagIdInt);
        }else {
            return null;
        }
    }


    @Override
    public void deleteBlogTagId(Long id) {
        blogMapper.deleteBlogTagId(id);
    }

    @Override
    public List<Blog> findBlogByQuery(String query) {
        return blogMapper.findBlogByQuery(query);
    }

    @Override
    public void updateViews(Long blogId) {
        blogMapper.updateViewsByBlogId(blogId);
    }

    @Override
    public List<Blog> findBlogByTagId(Long tagId) {
        return blogMapper.findBlogByTagId(tagId);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {

        List<String> groupYear = blogMapper.findGroupYear();
        HashMap<String, List<Blog>> map = new HashMap<>();
        for (String year : groupYear) {
            map.put(year, blogMapper.findBlogByYear(year));
        }
        return map;
    }

    @Override
    public Long countBlog() {
        return blogMapper.countBlog();
    }

    @Override
    public List<Blog> findBlogRecommendTop(Integer top) {
        return blogMapper.findBlogRecommendTop(top);
    }

    /**
     * 把字符串转为整形数组，并去掉空值。
     * @param tagIdList
     * @return
     */
    private Integer[] arrayToInt(String tagIdList){

        String[] tagIdStr = tagIdList.split(",");

        List<String> list=new ArrayList<String>();

        for(int i = 0; i < tagIdStr.length; i++){
            if("null".equals(tagIdStr[i])){
                continue;
            }else {
                list.add(tagIdStr[i]);
            }
        }
        String[] newArray=new String[list.size()];
        for(int i = 0; i < newArray.length; i ++){
            newArray[i] = list.get(i);
        }

        Integer[] tagIdInt = new Integer[newArray.length];

        for(int i = 0; i < newArray.length; i ++){

            tagIdInt[i] = Integer.parseInt(newArray[i]);

        }
        return tagIdInt;
    }
}
