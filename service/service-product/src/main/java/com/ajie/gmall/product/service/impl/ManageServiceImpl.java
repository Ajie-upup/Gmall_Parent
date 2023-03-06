package com.ajie.gmall.product.service.impl;

import com.ajie.gmall.model.product.BaseCategory1;
import com.ajie.gmall.product.mapper.BaseCategory1Mapper;
import com.ajie.gmall.product.service.ManageService;
import org.springframework.stereotype.Service;

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

    @Override
    public List<BaseCategory1> getCategory1() {

        return baseCategory1Mapper.selectList(null);
    }
}
