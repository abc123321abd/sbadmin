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
 * @date 2018-11-03 15:32:34
 */
 
@Controller
@RequestMapping
public class SysUserController extends BaseController {

    /**
    * {comments}管理的基础页面
    * @return
    */
    @RequestMapping("/system/user/list")
    @RequiresPermissions("system:user:user")
    public  String list(){
        return "system/user/list";
    }

    /**
     * {comments}管理的编辑及新增页面
     * @return
     */
    @RequestMapping("/system/user/edit")
    //@RequiresPermissions("system:user:edit")
    public  String edit(){
        return "system/user/edit";
    }

}
