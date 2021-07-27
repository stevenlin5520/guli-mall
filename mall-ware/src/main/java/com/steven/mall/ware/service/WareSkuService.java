package com.steven.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.steven.common.utils.PageUtils;
import com.steven.mall.ware.entity.WareSkuEntity;

import java.util.Map;

/**
 * 商品库存
 *
 * @author steven
 * @email stevenlin5520@163.com
 * @date 2020-12-15 12:42:30
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

