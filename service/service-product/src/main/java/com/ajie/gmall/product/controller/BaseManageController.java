package com.ajie.gmall.product.controller;

import com.ajie.gmall.common.result.Result;
import com.ajie.gmall.model.product.BaseCategory1;
import com.ajie.gmall.product.mapper.BaseCategory1Mapper;
import com.ajie.gmall.product.service.ManageService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ajie
 * @date 2023/3/6
 * @description:
 */
@Api("商品基础属性接口")
@RestController
@RequestMapping("/admin/product")
public class BaseManageController {

    @Resource
    private ManageService manageService;

    /**
     * 查询所有一级分类
     *
     * @return
     */
    public Result<List<BaseCategory1>> getCategory1() {

        List<BaseCategory1> baseCategory1List = manageService.getCategory1();
        return Result.ok(baseCategory1List);
    }

}
