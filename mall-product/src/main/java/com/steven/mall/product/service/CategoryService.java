package com.steven.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.steven.common.utils.PageUtils;
import com.steven.mall.product.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author steven
 * @email stevenlin5520@163.com
 * @date 2020-12-14 16:43:03
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryEntity> listTree();

    void removeMenuByIds(List<Long> catIds);
}

