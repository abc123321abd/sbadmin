                                                    
package cn.edu.ctbu.sbadmin.system.webapi;

import cn.edu.ctbu.sbadmin.common.controller.BaseRestController;
import cn.edu.ctbu.sbadmin.common.datatables.DataTablesInput;
import cn.edu.ctbu.sbadmin.common.datatables.DataTablesOutput;
import cn.edu.ctbu.sbadmin.common.domain.PageDO;
import cn.edu.ctbu.sbadmin.common.utils.*;
import cn.edu.ctbu.sbadmin.system.domain.ConfigDO;
import cn.edu.ctbu.sbadmin.system.service.ConfigService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 *
 * @author Fromtech
 * @email tms@fromtech.net
 * @date 2018-11-19 15:45:15
*/
@RestController
@RequestMapping("/webapi/system/config")
@Slf4j
public class ConfigRestController extends BaseRestController {
    @Autowired
    private ConfigService configService;

    /**
    * 为select2准备的数据查询
    * @param q
    * @return
    * @throws Exception
    */
    @RequestMapping("queryByQ")
    @RequiresPermissions("system:config:config")
     public List<ConfigDO> queryByQ(@RequestParam(value="q", defaultValue="") String q) throws Exception {
        MQuery mQuery = new MQuery();
        List<QueryAndItems> queryAndItemsList;

        if (q.equals("")){
            //没有参数
            queryAndItemsList = MQueryHelper.WhereToQueryItems("");
        }else {
            queryAndItemsList = MQueryHelper.WhereToQueryItems("name=" + q + "^:like");
        }

        mQuery.setWherePara(queryAndItemsList);
        mQuery.setSort("id");
        mQuery.setDirect("desc");
        mQuery.setLimit(999999);
        mQuery.setOffset(0);

        List<ConfigDO> configList = configService.getByPage(mQuery).getList();

        return configList;
    }

    /**
     * 根据 id取记录
    */
    @RequestMapping("/get")
    @RequiresPermissions("system:config:config")
    public R get(Integer id) {
        ConfigDO config = configService.get(id);

        if (config != null){
            return R.ok("data", config);
        }

        return R.error(-1, "出错了，找不到此数据!");
    }

    /**
     * 取所有记录
    */
    @RequestMapping("/getAll")
    @RequiresPermissions("system:config:config")
    public List<ConfigDO> getAll() throws Exception {
        MQuery mQuery = new MQuery();
        List<QueryAndItems> queryAndItemsList = MQueryHelper.WhereToQueryItems("");

        mQuery.setWherePara(queryAndItemsList);
        mQuery.setSort("id");
        mQuery.setDirect("desc");
        mQuery.setLimit(999999);
        mQuery.setOffset(0);

        List<ConfigDO> configList = configService.list(mQuery);

        return configList;
    }

    /**
      * 根据条件取所有记录
    */
    @RequestMapping("/getByPage")
    @RequiresPermissions("system:config:config")
    public PageUtils list(@RequestParam("where") String where,
                          @RequestParam("pageSize") Integer pageSize,
                          @RequestParam("pageIndex") Integer pageIndex,
                          @RequestParam("sort") String sort,
                          @RequestParam("direct") String direct) throws Exception {
        //首先，把where条件的参数转为QueryAndItems
        //其中,where条件需要我们自己组装.示例:
        //name=张^:like&time=2^:lg&dmtCreate=2017-01-01^^2018-01-01
        //这个表示:
        //(name like '%张%' )and ( time<2 ) and (dmtCreate between '2017-01-01' and '2018-01-01)
        MQuery mQuery= MQueryHelper.GenQuery(where,pageSize,pageIndex,sort,direct);

        PageInfo<ConfigDO> configList = configService.getByPage(mQuery);

        PageUtils pageUtils = new PageUtils(configList.getList(), configList.getTotal());

        return pageUtils;
    }



    /**
     * 保存记录
    */
    @PostMapping("/save")
    @RequiresPermissions("system:config:add")
    public R save(ConfigDO config) {
        if (configService.save(config) > 0) {
            return R.ok();
        }

        return R.error();
    }

    /**
     * 修改记录
    */
    @RequestMapping("/update")
    @RequiresPermissions("system:config:edit")
    public R update(ConfigDO config) {
        configService.update(config);
        return R.ok();
    }


    /**
     * 物理删除记录
    */
    @PostMapping("/delete")
    @RequiresPermissions("system:config:remove")
    public R delete(Integer id) {
        if (configService.delete(id) > 0) {
            return R.ok();
        }

        return R.error();
    }

    /**
     * 逻辑删除，多条记录
    */
    @RequestMapping("/batchRemove")
    @RequiresPermissions("system:config:batchRemove")
    public R batchRemove(@RequestParam("ids[]") Integer[]ids) {
        List<ConfigDO> configList = new ArrayList<ConfigDO>();
        Integer count = ids.length;

        for (Integer i = 0; i < count; i++) {
            ConfigDO config = configService.get(ids[i]);
            if (config != null){
                configService.delete(config);
            }
        }

        return R.ok();
    }

    /**
     * 物理删除
     */
    @PostMapping("/batchDelete")
    @RequiresPermissions("system:config:batchRemove")
    public R batchDelete(@RequestParam("ids[]") Long[]ids) {

        configService.batchDelete(Arrays.toString(ids));

        return R.ok();
    }

    /**
     * 导出Excel
     * @param request
     * @param response
     * @author
     * @return
     * @throws Exception
    */
    @RequestMapping(value="exportExcel")
    @RequiresPermissions("system:config:excel")
    public void exportExcel(@RequestParam Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String filename = "config" + format.format(new Date().getTime()) + ".xls";

        response.setContentType("application/ms-excel;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes(),"iso-8859-1"));
        OutputStream out = response.getOutputStream();

        try {
            MQuery mQuery = new MQuery();
            List<QueryAndItems> queryAndItemsList = MQueryHelper.WhereToQueryItems("");

            mQuery.setWherePara(queryAndItemsList);
            mQuery.setSort("id");
            mQuery.setDirect("asc");
            mQuery.setLimit(999999);
            mQuery.setOffset(0);

            List<ConfigDO> configList = configService.list(mQuery);

            try {
                EasyPoiUtil.exportExcel("列表", "列表", true,
                                        ConfigDO.class, configList,
                                        response,
                                        filename);
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("config exportExcel出错" + e.getMessage());
        } finally {
            out.close();
        }
    }

}
