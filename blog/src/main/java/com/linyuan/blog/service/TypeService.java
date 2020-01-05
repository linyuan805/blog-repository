package com.linyuan.blog.service;

import com.linyuan.blog.domain.Type;

import java.util.List;

/**
 * @author LinYuan
 * @version 1.0
 * @date 2019/11/5 10:14
 */
public interface TypeService {

    /**
     * 新增
     * @param type
     * @return
     */
    Integer insertType(Type type);

    /**
     * 查询根据id
     * @param typeId
     * @return
     */
    Type getType(Long typeId);

    /**
     * 查询所有
     * @return
     */
    List<Type> findAllType();

    /**
     * 检查type的name是否存在
     * @param type
     * @return
     */
    Type checkTypeNameNotExit(Type type);

    /**
     * 更新类型名称
     * @param type
     */
    void updateTypeName(Type type);

    /**
     * 删除
     * @param typeId
     */
    void deleteTypeById(long typeId);
}
