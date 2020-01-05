package com.linyuan.blog.dao;

import com.linyuan.blog.domain.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LinYuan
 * @version 1.0
 * @date 2019/11/19 19:37
 */
@Repository
public interface TagMapper {
    /**
     * 插入
     * @param tag
     * @return
     */
    Integer insertTag(Tag tag);

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
     * 查找所有
     * @return
     */
    List<Tag> findAllTag();

    /**
     * 删除
     * @param id
     */
    void deleteTag(Long id);

    /**
     * 更新
     * @param tag
     * @return
     */
    Integer updateTag(Tag tag);

    /**
     * 根据id数组查询
     * @param tagIdInt
     * @return
     */
    List<Tag> findTagList(Integer[] tagIdInt);

    /**
     * 根据blogid查询所有tag
     * @param blogId
     * @return
     */
    List<Tag> findTagByBlogId(Long blogId);
}
