package com.steven.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.steven.common.utils.PageUtils;
import com.steven.mall.order.entity.OrderEntity;

import java.util.Map;

/**
 * 订单
 *
 * @author steven
 * @email stevenlin5520@163.com
 * @date 2020-12-16 00:17:26
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

