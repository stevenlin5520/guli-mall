package com.steven.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.steven.common.valid.AddGroup;
import com.steven.common.valid.ShowStatusValue;
import com.steven.common.valid.UpdateGroup;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

/**
 * 品牌
 * 
 * @author steven
 * @email stevenlin5520@163.com
 * @date 2020-12-14 16:43:03
 */
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 品牌id
	 */
	@Null(message = "新增品牌ID必须为空",groups = {AddGroup.class})
	@NotNull(message = "修改品牌ID不能为空",groups = UpdateGroup.class)
	@TableId
	private Long brandId;
	/**
	 * 品牌名
	 */
	@NotBlank(message = "品牌名不能为空",groups = {AddGroup.class,UpdateGroup.class})
	private String name;
	/**
	 * 品牌logo地址
	 */
	@NotBlank(message = "Logo地址不能为空",groups = {AddGroup.class})
	@URL(message = "请传入一个合法的URL地址",groups = {UpdateGroup.class})
	private String logo;
	/**
	 * 介绍
	 */
	private String descript;
	/**
	 * 显示状态[0-不显示；1-显示]
	 */
//	@NotNull(message = "显示状态不能为空",groups = {AddGroup.class,UpdateGroup.class})
	@ShowStatusValue(values = {0,1},groups = AddGroup.class)
	private Integer showStatus;
	/**
	 * 检索首字母
	 */
	@NotNull(message = "首字母不能为空",groups = {AddGroup.class})
	private String firstLetter;
	/**
	 * 排序
	 */
	@Min(message = "排序必须大于等于0", value = 0, groups = {AddGroup.class})
	private Integer sort;

}
