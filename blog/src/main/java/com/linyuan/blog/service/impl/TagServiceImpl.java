package com.linyuan.blog.service.impl;

import com.linyuan.blog.NotFoundException;
import com.linyuan.blog.dao.TagMapper;
import com.linyuan.blog.domain.Tag;
import com.linyuan.blog.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by limi on 2017/10/16.
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;


    @Override
    public Integer insertTag(Tag tag) {
        return tagMapper.insertTag(tag);
    }


    @Override
    public Tag findTagById(Long id) {
        return tagMapper.findTagById(id);
    }

    @Override
    public Tag findTagByName(String name) {
        return tagMapper.findTagByName(name);
    }


    @Override
    public List<Tag> findAllTag() {
        return tagMapper.findAllTag();
    }



    @Override
    public Integer updateTag(Long id, Tag tag) {
        Tag t = tagMapper.findTagById(id);
        if (t == null) {
            throw new NotFoundException("不存在该标签");
        }
        BeanUtils.copyProperties(tag,t);
        return tagMapper.updateTag(t);
    }


    @Override
    public void deleteTag(Long id) {
        tagMapper.deleteTag(id);
    }

    @Override
    public List<Tag> findTagList(String tagIdList) {
        if (tagIdList != null){
            Integer[] tagIdInt = ArrayToInt(tagIdList);
            return tagMapper.findTagList(tagIdInt);
        }else {
            return null;
        }
    }

    @Override
    public List<Tag> findTagByBlogId(Long blogId) {
        return tagMapper.findTagByBlogId(blogId);
    }

    /**
     * 把字符串转为整形数组，并去掉空值。
     * @param tagIdList
     * @return
     */
    private Integer[] ArrayToInt(String tagIdList){

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
