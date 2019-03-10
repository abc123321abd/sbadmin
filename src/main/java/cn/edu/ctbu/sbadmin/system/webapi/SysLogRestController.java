package cn.edu.ctbu.sbadmin.system.webapi;


import cn.edu.ctbu.sbadmin.common.controller.BaseRestController;
import cn.edu.ctbu.sbadmin.common.datatables.DataTablesInput;
import cn.edu.ctbu.sbadmin.common.datatables.DataTablesOutput;
import cn.edu.ctbu.sbadmin.common.domain.PageDO;
import cn.edu.ctbu.sbadmin.common.utils.*;
import cn.edu.ctbu.sbadmin.system.domain.SysLogDO;
import cn.edu.ctbu.sbadmin.system.service.SysLogService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/webapi/system/log")
public class SysLogRestController extends BaseRestController {

    @Autowired
    SysLogService sysLogService;

    /**
     * 取数据
     */

    @RequestMapping("/get")
    @RequiresPermissions("system:log:log")
    public R get(Long id) {
        SysLogDO sysLog = sysLogService.get(id);
        if (sysLog != null) {
            return R.ok("data", sysLog);
        }
        return R.error(-1, "出错了，找不到此数据!");
    }

//    /**
//     * 暂时使用list来做测试
//     *
//     * @param params
//     * @return
//     */
//    @GetMapping("/list")
//    @RequiresPermissions("system:log:log")
//    PageInfo<SysLogDO> list(@RequestParam Map<String, Object> params) {
//        Query query = new Query(params);
//        PageInfo<SysLogDO> page = sysLogService.getByPage(query);
//        return page;
//    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("system:log:add")
    public R save(SysLogDO sysLog) {
        //由于sysLog中的time中间有个T，需要去掉
        sysLog.setGmtCreate(new Date());
        sysLog.setUserId(getUser().getId());
        sysLog.setUsername(getUser().getUsername());

        // 获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        // 设置IP地址
        sysLog.setIp(IPUtils.getIpAddr(request));

        if (sysLogService.save(sysLog) > 0) {
            return R.ok("data", sysLog);
        }
        return R.error();
    }

    /**
     * 更新
     */
    @PostMapping("/update")
    @RequiresPermissions("system:log:edit")
    public R update(SysLogDO sysLog) {
        //由于sysLog中的time中间有个T，需要去掉
        if (sysLogService.update(sysLog) > 0) {
            return R.ok("data", sysLog);
        }
        return R.error();
    }


    @RequestMapping("/getByPage")

    public PageUtils getByPage(@RequestParam("where") String where,
                               @RequestParam("pageSize") Integer pageSize,
                               @RequestParam("pageIndex") Integer pageIndex,
                               @RequestParam("sort") String sort,
                               @RequestParam("direct") String direct
    )  throws  Exception{
        //首先，把where条件的参数转为QueryAndItems
//        其中,where条件需要我们自己组装.示例:
//        name=张^:like&time=2^:lg&dmtCreate=2017-01-01^^2018-01-01
//        这个表示:
//        (name like '%张%' )and ( time<2 ) and (dmtCreate between '2017-01-01' and '2018-01-01)

        MQuery mQuery= MQueryHelper.GenQuery(where,pageSize,pageIndex,sort,direct);

        PageInfo<SysLogDO> sysLogList = sysLogService.getByPage(mQuery);

        PageUtils pageUtils = new PageUtils(sysLogList.getList(),sysLogList.getTotal());
        return pageUtils;
    }


    @RequestMapping(value = "/getByPage2", method = RequestMethod.GET)
    @RequiresPermissions("system:log:log")
    public DataTablesOutput<SysLogDO> getByPage2(@Valid DataTablesInput input)  throws Exception{
        MQuery mQuery = new MQuery();

        //首先，把where条件的参数转为QueryAndItems
//        其中,where条件需要我们自己组装.示例:
//        name=张^:like&time=2^:lg&dmtCreate=2017-01-01^^2018-01-01
//        这个表示:
//        (name like '%张%' )and ( time<2 ) and (dmtCreate between '2017-01-01' and '2018-01-01)
        Integer sortColIndex=input.getOrder().get(0).getColumn();
        String where = input.getSearch().getValue();
        String sort = input.getColumns().get(sortColIndex).getData();//列名
        String direct = input.getOrder().get(0).getDir();
        Integer pageSize = input.getLength();
        Integer pageIndex = input.getStart();
        Integer draw = input.getDraw();

        where = URLDecoder.decode(where,"UTF-8");

        List<QueryAndItems> queryAndItemsList =  MQueryHelper.WhereToQueryItems(where);
        mQuery.setWherePara(queryAndItemsList);

        mQuery.setSort(sort);
        mQuery.setDirect(direct);
        mQuery.setLimit(pageSize);
        mQuery.setOffset(pageIndex);

        PageInfo<SysLogDO> sysLogList = sysLogService.getByPage(mQuery);

        Long total = sysLogList.getTotal();

        //PageUtils pageUtils = new PageUtils(sysLogList.getRows(),total);

        DataTablesOutput<SysLogDO> rs = new DataTablesOutput<>();
        rs.setData(sysLogList.getList());
        rs.setDraw(draw);
        rs.setRecordsFiltered(total);
        rs.setRecordsTotal(total);

        return rs;
    }

//
//    @RequestMapping("/getByPage")
//
//    public PageUtils getByPage(@RequestParam("where") String where,
//                               @RequestParam("pageSize") Integer pageSize,
//                               @RequestParam("pageIndex") Integer pageIndex,
//                               @RequestParam("sort") String sort,
//                               @RequestParam("direct") String direct
//    )  throws  Exception{
//        //首先，把where条件的参数转为QueryAndItems
////        其中,where条件需要我们自己组装.示例:
////        name=张^:like&time=2^:lg&dmtCreate=2017-01-01^^2018-01-01
////        这个表示:
////        (name like '%张%' )and ( time<2 ) and (dmtCreate between '2017-01-01' and '2018-01-01)
//
//        where= URLDecoder.decode(where,"UTF-8");
//
//        MQuery mQuery = new MQuery();
//
//        List<QueryAndItems> queryAndItemsList=  MQueryHelper.WhereToQueryItems(where);
//        mQuery.setWherePara(queryAndItemsList);
//
//        mQuery.setSort(sort);
//        mQuery.setDirect(direct);
//        mQuery.setLimit(pageSize);
//        mQuery.setOffset(pageIndex);
//
//        PageDO<SysLog> sysLogList = sysLogService.getByPage(mQuery);
//
//        int total= sysLogList.getTotal();
//
//        PageUtils pageUtils = new PageUtils(sysLogList.getRows(),total);
//        return pageUtils;
//    }


}
