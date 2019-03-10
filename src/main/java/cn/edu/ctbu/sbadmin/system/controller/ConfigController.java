package cn.edu.ctbu.sbadmin.system.controller;


import cn.edu.ctbu.sbadmin.common.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 *
 * @author Fromtech
 * @email tms@fromtech.net
 * @date 2018-11-19 15:45:15
*/
@Controller
@RequestMapping
public class ConfigController extends BaseController {
    /**
     * {comments}管理的基础页面
     * @return
    */
    @RequestMapping("/system/config/list")
    @RequiresPermissions("system:config:config")
    public  String list(){
        return "/system/config/list";
    }

    /**
     * {comments}管理的编辑及新增页面
     * @return
    */
    @RequestMapping("/system/config/edit")
    @RequiresPermissions("system:config:edit")
    public  String edit(){
        return "/system/config/edit";
    }

}
