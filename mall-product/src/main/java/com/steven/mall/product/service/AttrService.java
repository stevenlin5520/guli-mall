package com.steven.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.steven.common.utils.PageUtils;
import com.steven.mall.product.entity.AttrEntity;

import java.util.Map;

/**
 * 商品属性
 *
 * @author steven
 * @email stevenlin5520@163.com
 * @date 2020-12-14 16:43:04
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

