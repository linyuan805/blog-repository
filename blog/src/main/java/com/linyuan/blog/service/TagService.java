package com.linyuan.blog.service;

import com.linyuan.blog.domain.Tag;

import java.util.List;

/**
 * Created by limi on 2017/10/16.
 */
public interface TagService {

    /**
     * 插入标签
     * @param type
     * @return
     */
    Integer insertTag(Tag type);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Tag findTagById(Long id);

    /**
     * 根据name查询
     * @param name
     * @return
     */
    Tag findTagByName(String name);

    /**
     * 查找所有tag
     * @return
     */
    List<Tag> findAllTag();

    /**
     * 更新
     * @param id
     * @param type
     * @return
     */

    Integer updateTag(Long id, Tag type);

    /**
     * 删除
     * @param id
     */
    void deleteTag(Long id);

    /**
     * 根据string类型id查找tag
     * @param tagIdList
     * @return
     */
    List<Tag> findTagList(String tagIdList);

    /**
     * 根据blogid查询所有tag
     * @param blogId
     * @return
     */
    List<Tag> findTagByBlogId(Long blogId);
}
