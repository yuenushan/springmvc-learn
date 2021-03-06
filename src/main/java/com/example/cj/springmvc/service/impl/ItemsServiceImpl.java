package com.example.cj.springmvc.service.impl;

import com.example.cj.springmvc.mapper.ItemsMapper;
import com.example.cj.springmvc.mapper.ItemsMapperCustom;
import com.example.cj.springmvc.po.Items;
import com.example.cj.springmvc.po.ItemsCustom;
import com.example.cj.springmvc.po.ItemsQueryVo;
import com.example.cj.springmvc.service.ItemsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ItemsServiceImpl implements ItemsService {

    @Autowired
    private ItemsMapperCustom itemsMapperCustom;
    @Autowired
    private ItemsMapper itemsMapper;

    @Override
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
        return itemsMapperCustom.findItemsList(itemsQueryVo);
    }

    @Override
    public ItemsCustom findItemsById(Integer id) throws Exception {
        Items items = itemsMapper.selectByPrimaryKey(id);
        //中间对商品信息进行业务处理
        //....
        //返回ItemsCustom
        ItemsCustom itemsCustom = new ItemsCustom();
        //将items的属性值拷贝到itemsCustom
        BeanUtils.copyProperties(items, itemsCustom);

        return itemsCustom;
    }

    @Override
    public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception {
        //添加业务校验，通常在service接口对关键参数进行校验
        //校验 id是否为空，如果为空抛出异常
        //更新商品信息使用updateByPrimaryKeyWithBLOBs根据id更新items表中所有字段，包括 大文本类型字段
        //updateByPrimaryKeyWithBLOBs要求必须转入id
        itemsCustom.setId(id);
        itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
    }
}
