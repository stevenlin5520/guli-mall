package com.steven.mall.coupon.dao;

import com.steven.mall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author steven
 * @email stevenlin5520@163.com
 * @date 2020-12-15 12:30:23
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
