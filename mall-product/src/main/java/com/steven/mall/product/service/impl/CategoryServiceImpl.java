package com.steven.mall.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.steven.common.utils.PageUtils;
import com.steven.common.utils.Query;

import com.steven.mall.product.dao.CategoryDao;
import com.steven.mall.product.entity.CategoryEntity;
import com.steven.mall.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listTree() {
        List<CategoryEntity> list = baseMapper.selectList(null);
        //找到所有一级菜单
        List<CategoryEntity> collect = list.stream().filter(item -> item.getParentCid() == 0)
                .map(menu -> {
                    //设置子菜单
                    menu.setChildCategory(getChildCategory(menu,list));
                    return menu;
                })
                .sorted(Comparator.comparingInt(menu -> (menu.getSort() != null ? menu.getSort() : 0)))
                .collect(Collectors.toList());

        return collect;
    }

    /**
     * 递归查询所有菜单的子菜单
     * @param root
     * @param all
     * @return
     */
    private List<CategoryEntity> getChildCategory(CategoryEntity root, List<CategoryEntity> all){
        return all.stream().filter(categoryEntity -> categoryEntity.getParentCid().equals(root.getCatId()))
                .map(menu -> {
                    //设置子菜单
                    menu.setChildCategory(getChildCategory(menu,all));
                    return menu;
                })
                .sorted(Comparator.comparingInt(menu -> (menu.getSort() != null ? menu.getSort() : 0)))
                .collect(Collectors.toList());
    }

    @Override
    public void removeMenuByIds(List<Long> catIds) {
        //TODO 1、检查当前刹车农户的菜单是否被别的地方引用
        baseMapper.deleteBatchIds(catIds);
    }
}