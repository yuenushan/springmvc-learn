package com.example.cj.springmvc.mapper;

import com.example.cj.springmvc.po.ItemsCustom;
import com.example.cj.springmvc.po.ItemsQueryVo;

import java.util.List;

public interface ItemsMapperCustom {
    //商品查询列表
    List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
}
