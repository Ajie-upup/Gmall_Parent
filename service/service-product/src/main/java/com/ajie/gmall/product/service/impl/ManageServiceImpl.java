package com.ajie.gmall.product.service.impl;

import com.ajie.gmall.model.product.*;
import com.ajie.gmall.product.mapper.*;
import com.ajie.gmall.product.service.ManageService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ajie
 * @date 2023/3/6
 * @description:
 */
@Service
public class ManageServiceImpl implements ManageService {

    @Resource
    private BaseCategory1Mapper baseCategory1Mapper;

    @Resource
    private BaseCategory2Mapper baseCategory2Mapper;

    @Resource
    private BaseCategory3Mapper baseCategory3Mapper;

    @Resource
    private BaseAttrInfoMapper baseAttrInfoMapper;

    @Resource
    private BaseAttrValueMapper baseAttrValueMapper;

    /**
     * 查询所有一级分类
     *
     * @return
     */
    @Override
    public List<BaseCategory1> getCategory1() {

        return baseCategory1Mapper.selectList(null);
    }

    /**
     * 根据一级分类id查询二级分类数据
     *
     * @param category1Id
     * @return
     */
    @Override
    public List<BaseCategory2> getCategory2(Long category1Id) {

        QueryWrapper<BaseCategory2> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category1_id", category1Id);
        return baseCategory2Mapper.selectList(queryWrapper);
    }

    /**
     * 根据二级分类Id查询三级分类
     *
     * @param category2Id
     * @return
     */
    @Override
    public List<BaseCategory3> getCategory3(Long category2Id) {
        QueryWrapper<BaseCategory3> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category2_id", category2Id);
        return baseCategory3Mapper.selectList(queryWrapper);
    }

    @Override
    public List<BaseAttrInfo> attrInfoList(Long category1Id, Long category2Id, Long category3Id) {

        return baseAttrInfoMapper.selectBaseAttrInfoList(category1Id, category2Id, category3Id);
    }

    /**
     * 新增和修改平台属性和平台属性值
     * Transactional 默认处理事务级别为 RuntimeException,执行多条SQL语句需要提高回滚级别
     *
     * @param baseAttrInfo
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {
        //根据 baseAttrInfo 中是否携带 id 属性决定新增与修改操作
        if (baseAttrInfo.getId() != null) {
            //携带了 id 证明为修改操作
            baseAttrInfoMapper.updateById(baseAttrInfo);
        } else {
            //新增 -- 插入数据
            baseAttrInfoMapper.insert(baseAttrInfo);
        }

        // baseAttrValue 平台属性值
        // 修改平台属性值
        QueryWrapper<BaseAttrValue> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("attr_id", baseAttrInfo.getId());
        // 逻辑删除
        baseAttrValueMapper.delete(queryWrapper);

        // 新增平台属性值
        List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
        if (attrValueList != null && attrValueList.size() > 0) {
            for (BaseAttrValue baseAttrValue : attrValueList) {
                // 将平台属性id 设置给平台属性值
                baseAttrValue.setAttrId(baseAttrInfo.getId());
                // 新增平台属性值
                baseAttrValueMapper.insert(baseAttrValue);
            }
        }
    }

    /**
     * 根据 attrId 查询平台属性对象,优先封装最新的平台属性值对象
     *
     * @param attrId
     * @return
     */
    @Override
    public BaseAttrInfo getAttrInfo(Long attrId) {
        //查询平台属性对象
        BaseAttrInfo baseAttrInfo = baseAttrInfoMapper.selectById(attrId);
        //设置属性对象的值
        baseAttrInfo.setAttrValueList(getAttrValueList(attrId));
        return baseAttrInfo;
    }

    /**
     * 根据 attr_id 查询平台属性值
     *
     * @param attrId
     * @return
     */
    private List<BaseAttrValue> getAttrValueList(Long attrId) {
        //查询最新的平台属性值信息
        QueryWrapper<BaseAttrValue> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("attr_id", attrId);
        List<BaseAttrValue> baseAttrValueList = baseAttrValueMapper.selectList(queryWrapper);
        return baseAttrValueList;
    }


}
