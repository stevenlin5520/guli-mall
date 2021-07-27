package com.steven.mall.order.dao;

import com.steven.mall.order.entity.OrderItemEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单项信息
 * 
 * @author steven
 * @email stevenlin5520@163.com
 * @date 2020-12-16 00:17:25
 */
@Mapper
public interface OrderItemDao extends BaseMapper<OrderItemEntity> {
	
}
