package com.steven.mall.product.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.steven.common.valid.AddGroup;
import com.steven.common.valid.UpdateGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.steven.mall.product.entity.BrandEntity;
import com.steven.mall.product.service.BrandService;
import com.steven.common.utils.PageUtils;
import com.steven.common.utils.R;

import javax.validation.Valid;


/**
 * 品牌
 * <p>
 *     JSR303数据校验
 * </p>
 * @author steven
 * @email stevenlin5520@163.com
 * @date 2020-12-14 16:47:59
 */
@RestController
@RequestMapping("product/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = brandService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{brandId}")
    public R info(@PathVariable("brandId") Long brandId){
		BrandEntity brand = brandService.getById(brandId);

        return R.ok().put("brand", brand);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@Validated(AddGroup.class) @RequestBody BrandEntity brand){
		brandService.save(brand);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@Validated(UpdateGroup.class) @RequestBody BrandEntity brand /*, BindingResult result*/){
        /*if(result.hasErrors()){
            Map<String,String> map = new HashMap<>(16);
            result.getFieldErrors().forEach(error -> {
                map.put(error.getField(),error.getDefaultMessage());
            });
            return R.error(400,"参数错误").put("data",map);
        }*/
		brandService.updateById(brand);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] brandIds){
		brandService.removeByIds(Arrays.asList(brandIds));

        return R.ok();
    }

}
