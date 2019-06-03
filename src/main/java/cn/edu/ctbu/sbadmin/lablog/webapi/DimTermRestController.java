package cn.edu.ctbu.sbadmin.lablog.webapi;

import cn.edu.ctbu.sbadmin.common.controller.BaseRestController;
import cn.edu.ctbu.sbadmin.common.utils.MQuery;
import cn.edu.ctbu.sbadmin.common.utils.MQueryHelper;
import cn.edu.ctbu.sbadmin.common.utils.PageUtils;
import cn.edu.ctbu.sbadmin.common.utils.R;
import cn.edu.ctbu.sbadmin.lablog.domain.DimTermDO;
import cn.edu.ctbu.sbadmin.lablog.service.DimTermService;
import cn.edu.ctbu.sbadmin.system.domain.RoleDO;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/webapi/lablog/dimterm")
public class DimTermRestController extends BaseRestController {

    @Autowired
    DimTermService dimTermService;


    @RequestMapping("/getByPage")
    @RequiresPermissions("lablog:dimterm:list")
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

        PageInfo<DimTermDO> dimTermDOPageInfo = dimTermService.getByPage(mQuery);

        PageUtils pageUtils = new PageUtils(dimTermDOPageInfo.getList(),dimTermDOPageInfo.getTotal());

        return pageUtils;
    }


    /**
     * 按id读取数据，只有一条
     * @param id
     * @return
     */
    @RequestMapping("/get")
    @RequiresPermissions("lablog:dimterm:list")
    public R get(Long id){
        DimTermDO dimTermDO=dimTermService.get(id);
        if(dimTermDO!=null){
            return R.ok("data",dimTermDO);
        }

        return R.error(-1,"出错，找不到数据!");

    }

    /**
     * 保存数据
     * @param dimTermDO
     * @return
     */
    @RequestMapping("/save")
    @RequiresPermissions("lablog:dimterm:add")
    public R save(DimTermDO dimTermDO){

        if(dimTermService.save(dimTermDO)>0){
            return R.ok("data",dimTermDO);
        }
        return R.error();

    }

    /**
     * 更新数据
     * @param dimTermDO
     * @return
     */
    @RequestMapping("/update")
    @RequiresPermissions("lablog:dimterm:add")
    public R update(DimTermDO dimTermDO){

        if(dimTermService.save(dimTermDO)>0){
            return R.ok("data",dimTermDO);
        }
        return R.error();

    }

    @RequestMapping("/batchDelete")
    @RequiresPermissions("lablog:dimterm:remove")
    public R batchDelete(@RequestParam("ids[]") Long[] ids){
        List<DimTermDO> dimTermDOS=dimTermService.findByIds(ids);
        if(dimTermDOS.size()>0){
            for(DimTermDO dimTermDO:
            dimTermDOS){
                dimTermService.delete(dimTermDO.getId());
            }
        }
        return R.ok();
    }


}
