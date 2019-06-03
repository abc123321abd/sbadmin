package cn.edu.ctbu.sbadmin.lablog.controller;


import cn.edu.ctbu.sbadmin.common.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DimTermController extends BaseController {

    /**
     * {comments}管理的基础页面
     * @return
     */
    @RequestMapping("/lablog/dimterm/list")
    @RequiresPermissions("lablog:dimterm:list")
    public  String list(){
        return "lablog/dimterm/list";
    }

    /**
     * {comments}管理的编辑及新增页面
     * @return
     */
    @RequestMapping("/lablog/dimterm/edit")
    @RequiresPermissions("lablog:dimterm:edit")
    public  String edit(){
        return "lablog/dimterm/edit";
    }

}
