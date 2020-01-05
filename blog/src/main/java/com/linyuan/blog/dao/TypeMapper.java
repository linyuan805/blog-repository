package com.linyuan.blog.dao;

import com.linyuan.blog.domain.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LinYuan
 * @version 1.0
 * @date 2019/11/5 10:32
 */
@Repository
@Mapper
public interface TypeMapper  {

    /**
     * 插入type
     * @param type
     * @return
     */
    Integer insertType(Type type);

    /**
     * 根据id查询Type
     * @param typeId
     * @return
     */
    Type findTypeById(Long typeId);

    /**
     * 根据名称查询type
     * @param type
     * @return
     */
    Type findTypeByName(Type type);

    /**
     * 查找所有Type
     * @return
     */
    List<Type> findAllType();

    /**
     * 根据id修改名称
     * @param type
     */
    void updateTypeNameById(Type type);

    /**
     * 删除类型
     * @param typeId
     */
    void deleteTypeById(long typeId);
}
