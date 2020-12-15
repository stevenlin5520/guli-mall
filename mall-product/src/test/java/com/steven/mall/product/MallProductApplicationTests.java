package com.steven.mall.product;

import com.steven.mall.product.entity.CategoryEntity;
import com.steven.mall.product.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class MallProductApplicationTests {

    @Resource
    private CategoryService categoryService;

    @Test
    void contextLoads() {
    }


    @Test
    private void test(){
        List<CategoryEntity> list = categoryService.list();
        list.forEach(item -> System.out.println("item = " + item));
    }
}
