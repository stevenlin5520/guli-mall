package com.steven.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.steven.common.utils.PageUtils;
import com.steven.mall.order.entity.RefundInfoEntity;

import java.util.Map;

/**
 * 退款信息
 *
 * @author steven
 * @email stevenlin5520@163.com
 * @date 2020-12-16 00:17:26
 */
public interface RefundInfoService extends IService<RefundInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

