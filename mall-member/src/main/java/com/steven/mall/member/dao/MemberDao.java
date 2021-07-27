package com.steven.mall.member.dao;

import com.steven.mall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author steven
 * @email stevenlin5520@163.com
 * @date 2020-12-16 00:24:06
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
