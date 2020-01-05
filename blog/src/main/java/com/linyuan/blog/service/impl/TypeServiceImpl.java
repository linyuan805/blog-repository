package com.linyuan.blog.service.impl;

import com.linyuan.blog.NotFoundException;
import com.linyuan.blog.dao.TypeMapper;
import com.linyuan.blog.domain.Type;
import com.linyuan.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LinYuan
 * @version 1.0
 * @date 2019/11/5 10:30
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public Integer insertType(Type type) {
        return typeMapper.insertType(type);
    }

    @Override
    public Type getType(Long typeId) {

        Type oneType = typeMapper.findTypeById(typeId);
        return oneType;
    }

    @Override
    public List<Type> findAllType() {
        return typeMapper.findAllType();
    }

    @Override
    public Type checkTypeNameNotExit(Type type) {
        return typeMapper.findTypeByName(type);
    }

    @Override
    public void updateTypeName(Type type) {
        typeMapper.updateTypeNameById(type);
    }

    @Override
    public void deleteTypeById(long typeId) {
        typeMapper.deleteTypeById(typeId);
    }
}
