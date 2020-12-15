package com.steven.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.steven.common.utils.PageUtils;
import com.steven.mall.ware.entity.UndoLogEntity;

import java.util.Map;

/**
 * 
 *
 * @author steven
 * @email stevenlin5520@163.com
 * @date 2020-12-15 12:42:32
 */
public interface UndoLogService extends IService<UndoLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

