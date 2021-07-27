package com.steven.mall.coupon.controller;

import java.util.Arrays;
import java.util.Map;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import com.steven.mall.coupon.entity.CouponEntity;
import com.steven.mall.coupon.service.CouponService;
import com.steven.common.utils.PageUtils;
import com.steven.common.utils.R;



/**
 * 优惠券信息
 *
 * @author steven
 * @email stevenlin5520@163.com
 * @date 2020-12-15 12:30:23
 */
@RefreshScope
@RestController
@RequestMapping("coupon/coupon")
public class CouponController {
    @Autowired
    private CouponService couponService;

    @GetMapping("hatSale")
    public R hatSale(){
        CouponEntity entity = new CouponEntity();
        entity.setCouponName("满100减99");
        return R.ok().put("coupon", entity);
    }

    @Value("${coupon.user.name}")
    private String name;
    @Value("${coupon.user.age}")
    private int age;

    @GetMapping("testConfig")
    public R testConfig(){
        return R.ok().put("name",name).put("age",age);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = couponService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		CouponEntity coupon = couponService.getById(id);

        return R.ok().put("coupon", coupon);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CouponEntity coupon){
		couponService.save(coupon);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CouponEntity coupon){
		couponService.updateById(coupon);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		couponService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
