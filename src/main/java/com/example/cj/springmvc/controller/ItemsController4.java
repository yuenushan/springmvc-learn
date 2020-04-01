package com.example.cj.springmvc.controller;

import com.example.cj.springmvc.po.ItemsCustom;
import com.example.cj.springmvc.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ItemsController4 {

    @Autowired
    private ItemsService itemsService;

    //商品查询列表
    @RequestMapping("/queryItems4")
    //实现 对queryItems方法和url进行映射，一个方法对应一个url
    //一般建议将url和方法写成一样
    public ModelAndView queryItems() throws Exception{
        //调用service查找数据库，查询商品列表
        List<ItemsCustom> itemsList = itemsService.findItemsList(null);

        //返回ModelAndView
        ModelAndView modelAndView = new ModelAndView();
        //相当于request的setAttribute方法,在jsp页面中通过itemsList取数据
        modelAndView.addObject("itemsList",itemsList);

        //指定视图
        //下边的路径，如果在视图解析器中配置jsp的路径前缀和后缀，修改为items/itemsList
        //modelAndView.setViewName("/WEB-INF/jsp/items/itemsList.jsp");
        //下边的路径配置就可以不在程序中指定jsp路径的前缀和后缀
        modelAndView.setViewName("itemsList");

        return modelAndView;
    }

    //商品信息修改页面显示
    @RequestMapping("/editItems")
    //限制http请求方法，可以post和get
    //@RequestMapping(value="/editItems",method={RequestMethod.POST, RequestMethod.GET})
    public ModelAndView editItems(@RequestParam(value="id",required=true) Integer items_id)throws Exception {

        //调用service根据商品id查询商品信息
        ItemsCustom itemsCustom = itemsService.findItemsById(items_id);

        // 返回ModelAndView
        ModelAndView modelAndView = new ModelAndView();

        //将商品信息放到model
        modelAndView.addObject("itemsCustom", itemsCustom);

        //商品修改页面
        modelAndView.setViewName("editItems");

        return modelAndView;
    }

    //商品信息修改提交
    @RequestMapping("/editItemsSubmit")
    public ModelAndView editItemsSubmit(HttpServletRequest request, Integer id, ItemsCustom itemsCustom)throws Exception {

        //调用service更新商品信息，页面需要将商品信息传到此方法
        itemsService.updateItems(id, itemsCustom);

        //返回ModelAndView
        ModelAndView modelAndView = new ModelAndView();
        //返回一个成功页面
        modelAndView.setViewName("success");
        return modelAndView;
    }
}
