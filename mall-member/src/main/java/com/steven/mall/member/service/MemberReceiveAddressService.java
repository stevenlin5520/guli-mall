package com.steven.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.steven.common.utils.PageUtils;
import com.steven.mall.member.entity.MemberReceiveAddressEntity;

import java.util.Map;

/**
 * 会员收货地址
 *
 * @author steven
 * @email stevenlin5520@163.com
 * @date 2020-12-16 00:24:05
 */
public interface MemberReceiveAddressService extends IService<MemberReceiveAddressEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

