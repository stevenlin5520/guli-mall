package com.steven.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.steven.common.utils.PageUtils;
import com.steven.mall.product.entity.BrandEntity;

import java.util.Map;

/**
 * 品牌
 *
 * @author steven
 * @email stevenlin5520@163.com
 * @date 2020-12-14 16:43:03
 */
public interface BrandService extends IService<BrandEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

