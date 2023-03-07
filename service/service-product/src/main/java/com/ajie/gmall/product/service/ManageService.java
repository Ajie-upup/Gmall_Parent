package com.ajie.gmall.product.service;

import com.ajie.gmall.model.product.BaseAttrInfo;
import com.ajie.gmall.model.product.BaseCategory1;
import com.ajie.gmall.model.product.BaseCategory2;
import com.ajie.gmall.model.product.BaseCategory3;

import java.util.List;

/**
 * @author ajie
 * @date 2023/3/6
 * @description:
 */
public interface ManageService {
    /**
     * 查询所有一级分类
     *
     * @return
     */
    List<BaseCategory1> getCategory1();

    /**
     * 根据 一级分类Id查询二级分类
     *
     * @param category1Id
     * @return
     */
    List<BaseCategory2> getCategory2(Long category1Id);

    /**
     * 根据二级分类Id查询三级分类
     *
     * @param category2Id
     * @return
     */
    List<BaseCategory3> getCategory3(Long category2Id);

    /**
     * 根据分类查询平台属性
     *
     * @param category1Id
     * @param category2Id
     * @param category3Id
     * @return
     */
    List<BaseAttrInfo> attrInfoList(Long category1Id, Long category2Id, Long category3Id);

    /**
     * 新增和修改平台属性和平台属性值
     * @param baseAttrInfo
     */
    void saveAttrInfo(BaseAttrInfo baseAttrInfo);

    /**
     * 根据attrId 查询平台属性对象
     * @param attrId
     * @return
     */
    BaseAttrInfo getAttrInfo(Long attrId);
}
