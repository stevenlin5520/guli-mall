package com.steven.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.steven.common.utils.PageUtils;
import com.steven.mall.order.entity.OrderItemEntity;

import java.util.Map;

/**
 * 订单项信息
 *
 * @author steven
 * @email stevenlin5520@163.com
 * @date 2020-12-16 00:17:25
 */
public interface OrderItemService extends IService<OrderItemEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

