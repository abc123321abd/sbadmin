package cn.edu.ctbu.sbadmin.system.webapi;

import cn.edu.ctbu.sbadmin.common.controller.BaseRestController;
import cn.edu.ctbu.sbadmin.common.datatables.DataTablesInput;
import cn.edu.ctbu.sbadmin.common.datatables.DataTablesOutput;
import cn.edu.ctbu.sbadmin.common.domain.PageDO;
import cn.edu.ctbu.sbadmin.common.utils.*;
import cn.edu.ctbu.sbadmin.system.domain.RoleDO;
import cn.edu.ctbu.sbadmin.system.service.RoleService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by tms on 18/2/27.
 */
@RestController
@RequestMapping("/webapi/system/role")
@Slf4j
public class RoleRestController extends BaseRestController {
    @Autowired
    RoleService roleService;

    /**
     * 为select2准备的数据查询
     * @param q
     * @return
     * @throws Exception
     */
    @RequiresPermissions("system:role:role")
    @RequestMapping("queryByQ")
    public List<RoleDO> queryByQ(@RequestParam(value="q", defaultValue="")  String q) throws Exception {
        MQuery mQuery = new MQuery();
        List<QueryAndItems> queryAndItemsList;

        if(q.equals("")){
            //没有参数
            queryAndItemsList = MQueryHelper.WhereToQueryItems("");
        }else {
            queryAndItemsList = MQueryHelper.WhereToQueryItems("role_name="+q+"^:like");
        }

        mQuery.setWherePara(queryAndItemsList);
        mQuery.setSort("id");
        mQuery.setDirect("desc");
        mQuery.setLimit(999999);
        mQuery.setOffset(0);

        List<RoleDO> roleList = roleService.list(mQuery);

        return roleList;
    }

    @RequestMapping("/getAll")
    @RequiresPermissions("system:role:role")
    public List<RoleDO> getAll() throws Exception {
        MQuery mQuery = new MQuery();
        List<QueryAndItems> queryAndItemsList = MQueryHelper.WhereToQueryItems("");

        mQuery.setWherePara(queryAndItemsList);
        mQuery.setSort("id");
        mQuery.setDirect("desc");
        mQuery.setLimit(999999);
        mQuery.setOffset(0);

        List<RoleDO> roleList = roleService.list(mQuery);

        return roleList;
    }
    /**
     * 取数据
     */
    @RequestMapping("/get")
    @RequiresPermissions("system:role:role")
    public R get(Integer id) {
        RoleDO sysRoleDO = roleService.get(id);

        if (sysRoleDO != null) {
            return R.ok("data", sysRoleDO);
        }

        return R.error(-1, "出错了，找不到此数据!");
    }



    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("system:role:add")
    public R save(RoleDO sysRoleDO) {
        //由于sysLog中的time中间有个T，需要去掉
        sysRoleDO.setGmtCreate(new Date());
        sysRoleDO.setIsDeleted(0);
        sysRoleDO.setUserIdCreate(getUser().getId());

        if (roleService.save(sysRoleDO) > 0) {
            return R.ok("data", sysRoleDO);
        }

        return R.error();
    }

    /**
     * 更新
     */
    @PostMapping("/update")
    @RequiresPermissions("system:role:edit")
    public R update(RoleDO sysRoleDO) {
        //由于sysLog中的time中间有个T，需要去掉
        sysRoleDO.setGmtModified(new Date());

        if (roleService.update(sysRoleDO) > 0) {
            return R.ok("data", sysRoleDO);
        }

        return R.error();
    }

    @RequestMapping("/batchDelete")
    @RequiresPermissions("system:role:remove")
    public R batchDelte(@RequestParam("ids[]") Integer[] ids) {
        List<RoleDO> roleDOList = new ArrayList<RoleDO>();
        Integer count = ids.length;

        for (Integer i = 0; i < count; i++) {

            RoleDO sysRoleDO = roleService.get(ids[i]);
            if (sysRoleDO != null) {

                roleService.delete(sysRoleDO);
            }
        }

        return R.ok();
    }

    //    @RequiresPermissions("system:role:list")
    @RequestMapping("/getByPage")
    @RequiresPermissions("system:role:role")
    public PageUtils list(@RequestParam("where") String where,
                          @RequestParam("pageSize") Integer pageSize,
                          @RequestParam("pageIndex") Integer pageIndex,
                          @RequestParam("sort") String sort,
                          @RequestParam("direct") String direct
    ) throws Exception {
        //首先，把where条件的参数转为QueryAndItems
//        其中,where条件需要我们自己组装.示例:
//        name=张^:like&time=2^:lg&dmtCreate=2017-01-01^^2018-01-01
//        这个表示:
//        (name like '%张%' )and ( time<2 ) and (dmtCreate between '2017-01-01' and '2018-01-01)
        MQuery mQuery= MQueryHelper.GenQuery(where,pageSize,pageIndex,sort,direct);

        PageInfo<RoleDO> roleDOPageInfo = roleService.getByPage(mQuery);

        PageUtils pageUtils = new PageUtils(roleDOPageInfo.getList(),roleDOPageInfo.getTotal());

        return pageUtils;
    }

    /**
     * 导出
     * @param request
     * @param response
     * @author
     * @return
     * @throws Exception
     */
    @RequestMapping(value="exportExcel")
    @RequiresPermissions("system:role:role")
    public void exportExcel(@RequestParam Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String filename = "SysRole"+format.format(new Date().getTime())+".xls";
        response.setContentType("application/ms-excel;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename="+new String(filename.getBytes(),"iso-8859-1"));
        OutputStream out = response.getOutputStream();

        try {
            String type = request.getParameter("type");
            MQuery mQuery = new MQuery();
            List<QueryAndItems> queryAndItemsList = MQueryHelper.WhereToQueryItems("");

            mQuery.setWherePara(queryAndItemsList);
            mQuery.setSort("id");
            mQuery.setDirect("asc");
            mQuery.setLimit(999999);
            mQuery.setOffset(0);

            List<RoleDO> roleList =roleService.list(mQuery);

            try {
                EasyPoiUtil.exportExcel("角色列表", "角色列表", true,
                        RoleDO.class, roleList,
                        response,
                        filename);
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("SysRole exportExcel出错"+e.getMessage());
        } finally{
            out.close();
        }
    }


    //验证角色名是否唯一
    @RequestMapping(value = "/validateRole", method = RequestMethod.POST)
    @RequiresPermissions("system:role:role")
    public R validateRole(Integer id, String roleName,String roleSign) {
        if (roleName == null || roleName.isEmpty())
        {
            return R.error("角色名不能为空!");
        }

        if (roleSign == null || roleSign.isEmpty())
        {
            return R.error("角色标识不能为空!");
        }

        /////////////验证角色名唯一性
        List<RoleDO> nameList = roleService.getByRoleName(roleName);

        if (id <= 0) {
            //新增
            if (nameList != null && nameList.size() > 0) {
                return R.error("角色名已经存在了!");
            }
        } else {
            //编辑
            if (nameList != null && nameList.size() > 0) {
               for (RoleDO item: nameList) {
                   if (item.getId().longValue() != id.longValue()) {
                       return R.error("角色名已经存在了!");
                   }
               }
            }
        }

        /////////////验证角色标识唯一性
        List<RoleDO> signList = roleService.getByRoleSign(roleSign);

        if (id <= 0) {
            //新增
            if (signList != null && signList.size() > 0) {
                return R.error("角色名已经存在了!");
            }
        } else {
            //编辑
            if (signList != null && signList.size() > 0) {
                for (RoleDO item: signList) {
                    if (item.getId().longValue() != id.longValue()) {
                        return R.error("角色标识已经存在了!");
                    }
                }
            }
        }

        return R.ok();
    }

}





