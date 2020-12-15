package com.steven.mall.member.dao;

import com.steven.mall.member.entity.UmsMemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author steven
 * @email stevenlin5520@163.com
 * @date 2020-12-15 12:51:45
 */
@Mapper
public interface UmsMemberDao extends BaseMapper<UmsMemberEntity> {
	
}
