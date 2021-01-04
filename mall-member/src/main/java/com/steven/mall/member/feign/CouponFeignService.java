package com.steven.mall.member.feign;

import com.steven.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author steven
 * @desc
 * @date 2020/12/15 17:01
 */
@FeignClient("mall-coupon")
public interface CouponFeignService {

    @GetMapping("/coupon/coupon/hatSale")
    public R redSale();
}