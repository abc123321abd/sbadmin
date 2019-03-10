package cn.edu.ctbu.sbadmin.system.controller;


import cn.edu.ctbu.sbadmin.common.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 菜单管理
 * 
 * @author Fromtech
 * @email tms@fromtech.net
 * @date 2018-10-28 17:40:27
 */
 
@Controller
@RequestMapping
public class SysMenuController extends BaseController {
    /**
    * {comments}管理的基础页面
    * @return
    */
    @RequestMapping("/system/menu/list")
    @RequiresPermissions("system:menu:menu")
    public  String list(){
        return "system/menu/list";
    }

    /**
     * {comments}管理的编辑及新增页面
     * @return
     */
    @RequestMapping("/system/menu/edit")
    @RequiresPermissions("system:menu:edit")
    public  String edit(){
        return "system/menu/edit";
    }

}
