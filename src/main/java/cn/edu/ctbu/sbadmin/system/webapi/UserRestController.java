package cn.edu.ctbu.sbadmin.system.webapi;



import cn.edu.ctbu.sbadmin.common.controller.BaseRestController;
import cn.edu.ctbu.sbadmin.common.datatables.DataTablesInput;
import cn.edu.ctbu.sbadmin.common.datatables.DataTablesOutput;
import cn.edu.ctbu.sbadmin.common.domain.PageDO;
import cn.edu.ctbu.sbadmin.common.utils.*;
import cn.edu.ctbu.sbadmin.system.domain.UserDO;
import cn.edu.ctbu.sbadmin.system.service.UserService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 *
 *
 * @author Fromtech
 * @email tms@fromtech.net
 * @date 2018-11-03 15:32:34
 *
 */

@RestController
@RequestMapping("/webapi/system/user")
public class UserRestController  extends BaseRestController {
    @Autowired
    private UserService userService;


    /**
     * 为select2准备的数据查询
     * @param q
     * @return
     * @throws Exception
     */
    @RequiresPermissions("system:user:get")
    @RequestMapping("queryByQ")
    public List<UserDO> queryByQ(@RequestParam(value="q", defaultValue="")  String q) throws Exception {
        MQuery mQuery = new MQuery();
        List<QueryAndItems> queryAndItemsList;

        if (q.equals("")){
            //没有参数
            queryAndItemsList = MQueryHelper.WhereToQueryItems("");
        }else {
            queryAndItemsList = MQueryHelper.WhereToQueryItems("name="+q+"^:like");
        }

        mQuery.setWherePara(queryAndItemsList);
        mQuery.setSort("id");
        mQuery.setDirect("desc");
        mQuery.setLimit(999999);
        mQuery.setOffset(0);

        List<UserDO> userList = userService.list(mQuery);

        return userList;
    }

    /**
     * 取数据
     */
    @RequestMapping("/get")
    @RequiresPermissions("system:user:user")
     public R get(@RequestParam("id") Long id) {
        UserDO user = userService.get(id);

        if (user != null){
            return R.ok("data", user);
        }

        return R.error(-1, "出错了，找不到此数据!");
    }

    @RequestMapping("/getAll")
    @RequiresPermissions("system:user:user")
    public List<UserDO> getAll() throws Exception {
        MQuery mQuery = new MQuery();
        List<QueryAndItems> queryAndItemsList = MQueryHelper.WhereToQueryItems("");

        mQuery.setWherePara(queryAndItemsList);
        mQuery.setSort("id");
        mQuery.setDirect("desc");
        mQuery.setLimit(999999);
        mQuery.setOffset(0);

        List<UserDO> userList = userService.list(mQuery);

        return userList;
    }

    @RequestMapping("/getByPage")
    @RequiresPermissions("system:user:user")
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

        PageInfo<UserDO> userDOPageInfo = userService.getByPage(mQuery);

        for (UserDO userDO:
             userDOPageInfo.getList()) {
            userDO.setPassword("");//去掉所有的密码
        }
        PageUtils pageUtils = new PageUtils(userDOPageInfo.getList(),userDOPageInfo.getTotal());


        return pageUtils;
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("system:user:add")
    public R save(UserDO user) {
        user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));   //对用户的密码在客户端进行MD5加密
        user.setGmtCreate(new Date());
        user.setIsDeleted(0);
        user.setUserIdCreate(getUser().getId());

        if (userService.save(user) > 0) {
            return R.ok();
        }

        return R.error();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("system:user:edit")
    public R update(UserDO user) {
        String userName = user.getUsername();
        String password = user.getPassword();

        if (!password.isEmpty() && password != "") {
            //密碼不爲空
            user.setPassword(MD5Utils.encrypt(userName, password));   //对用户的密码在客户端进行MD5加密
        } else {
            //密碼爲空
            user.setPassword(userService.getUserPassword(user.getId()));
         }

        user.setGmtModified(new Date());
        userService.update(user);

        return R.ok();
    }


    /**
     * 物理删除
     */
    @PostMapping("/delete")
    @RequiresPermissions("system:user:remove")
    public R delete( Long id) {
        if (userService.delete(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 逻辑删除，多条
     */
    @RequestMapping("/batchRemove")
    @RequiresPermissions("system:user:batchRemove")
    public R batchRemove(@RequestParam("ids[]") Long[] ids) {
        List<UserDO> userDOList = new ArrayList<UserDO>();
        Integer count = ids.length;

        for (Integer i = 0; i < count; i++) {
            UserDO user = userService.get(ids[i]);
            if (user != null){
                user.setGmtDelete(new Date());
                user.setDeleteUserid(getUser().getId());
                userService.delete(user);
             }
        }

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/batchDelete")
    @RequiresPermissions("system:user:batchRemove")
    public R batchDelete(@RequestParam("ids[]") Long[] ids) {
        userService.batchDelete(Arrays.toString(ids));
        return R.ok();
    }


    //变换用户状态
    @RequestMapping(value = "/changeUserStatus", method = RequestMethod.POST)
    @RequiresPermissions("system:user:user")
    public R changeUserStatus(Long id) {
        UserDO user = userService.get(id);
        Integer status = 1;

        if (user != null){
            status = (user.getStatus() > 0) ? 0 : 1;
            user.setStatus(status);
            user.setPassword(userService.getUserPassword(id));

            //更新状态
            userService.update(user);
            return R.ok("data", "");
        }

        return R.error(-1, "出错了，找不到此数据!");
    }

    //验证用户名是否唯一
    @RequestMapping(value = "/validateUserName", method = RequestMethod.POST)
    @RequiresPermissions("system:user:user")
    public R validateUserName(Long id, String userName) {
        if (userName == null || userName.isEmpty())
        {
            return R.error("用户名不能为空!");
        }

        //查找用户
        List<UserDO> userList = userService.getByUserName(userName);

        if (id <= 0) {
            //新增
            if (userList != null && userList.size() > 0) {
                return R.error("用户名已经存在了!");
            }
        } else {
            //编辑
            if (userList != null && userList.size() > 0) {
                for (UserDO item: userList) {
                    if (item.getId().longValue() != id.longValue()) {
                        return R.error("用户名已经存在了!");
                    }
                }
            }
        }

        return R.ok();
    }

}
