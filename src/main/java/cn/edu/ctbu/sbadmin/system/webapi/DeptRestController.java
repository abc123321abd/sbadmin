package cn.edu.ctbu.sbadmin.system.webapi;




import cn.edu.ctbu.sbadmin.common.controller.BaseRestController;
import cn.edu.ctbu.sbadmin.common.datatables.DataTablesInput;
import cn.edu.ctbu.sbadmin.common.datatables.DataTablesOutput;
import cn.edu.ctbu.sbadmin.common.domain.PageDO;
import cn.edu.ctbu.sbadmin.common.utils.*;
import cn.edu.ctbu.sbadmin.system.domain.DeptDO;
import cn.edu.ctbu.sbadmin.system.service.DeptService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 *
 * @author tms
 * @email tms2003@126.com
 * @date 2018-03-07 18:16:14
 */

@RestController
@RequestMapping("/webapi/system/dept")
public class DeptRestController extends BaseRestController {
	@Autowired
	private DeptService deptService;

	/**
	 * 取数据
	 */
	@RequiresPermissions("system:dept:dept")
	@RequestMapping("/get")
	public R get(Integer id) {
		DeptDO Dept = deptService.get(id);

		if (Dept != null) {
			return R.ok("data", Dept);
		}
		return R.error(-1, "出错了，找不到此数据!");
	}

	@RequiresPermissions("system:dept:dept")
	@RequestMapping("/getAll")
	public List<DeptDO> getAll() throws Exception {
		MQuery mQuery = new MQuery();
		List<QueryAndItems> queryAndItemsList = MQueryHelper.WhereToQueryItems("");

		mQuery.setWherePara(queryAndItemsList);
		mQuery.setSort("id");
		mQuery.setDirect("desc");
		mQuery.setLimit(999999);
		mQuery.setOffset(0);

		List<DeptDO> deptList = deptService.list(mQuery);

		return deptList;
	}



	/**
	 * 为boottable准备的分页
	 * @param where
	 * @param pageSize
	 * @param pageIndex
	 * @param sort
	 * @param direct
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("system:dept:dept")
	@RequestMapping("/getByPage")
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

		PageInfo<DeptDO> deptList = deptService.getByPage(mQuery);

		PageUtils pageUtils = new PageUtils(deptList.getList(),deptList.getTotal());

		return pageUtils;
	}


	/**
	 * return all tree
	 */
	@GetMapping("/ztreeAll")
	@RequiresPermissions("system:dept:dept")
	public  List<ZTreeNode> ZTreeAll()
	{
		MQuery mQuery = new MQuery();
		List<QueryAndItems> queryAndItemsList = MQueryHelper.WhereToQueryItems("");

		mQuery.setWherePara(queryAndItemsList);
		mQuery.setSort("parent_id asc,order_num");
		mQuery.setDirect("asc");
		mQuery.setLimit(999999);
		mQuery.setOffset(0);

		List<DeptDO> items = deptService.list(mQuery);
		List<ZTreeNode> zNodes = new ArrayList<ZTreeNode>();

		//写入根结点
		ZTreeNode myNode = new ZTreeNode();
		myNode.setName("根结点");
		myNode.setOpen(true);
		myNode.setId(0L);
		myNode.setPId(-1L);
		zNodes.add(myNode);

		for (int i = 0; i < items.size(); i++)
		{
			myNode = new ZTreeNode();
			DeptDO item = items.get(i);
			myNode.setName(item.getName());
			myNode.setOpen(false);

			myNode.setId(item.getId().longValue());
			myNode.setPId(item.getParentId().longValue());
			myNode.setTip(item.getName());
			//自动扩展1,2级
			if (item!= null && item.getLevel()!=null&& item.getLevel()< 2) {
				myNode.setOpen(true);
			}
			zNodes.add( myNode);
		}

		return zNodes;
	}

	/**
	 * 保存
	 */
	@PostMapping("/save")
	@RequiresPermissions("system:dept:add")
	public R save( DeptDO dept){
		if(deptService.save(dept) > 0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:dept:edit")
	public R update( DeptDO dept){
		deptService.update(dept);
		return R.ok();
	}



	/**
	 * 物理删除
	 */
	@PostMapping( "/delete")
	@RequiresPermissions("system:dept:remove")
	public R delete( Integer id){
		if(deptService.delete(id) > 0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 逻辑删除，多条
	 */
	@RequiresPermissions("system:dept:remove")
	@RequestMapping("/batchRemove")
	public R batchRemove(@RequestParam("ids[]") Integer[] ids) {
		List<DeptDO> deptDOList = new ArrayList<DeptDO>();
		Integer count = ids.length;

		for (Integer i = 0; i < count; i++) {
			DeptDO dept =deptService.get(ids[i]);
			if (dept != null) {
				deptService.delete(dept);
			}
		}

		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchDelete")
	@ResponseBody
	@RequiresPermissions("system:dept:batchRemove")
	public R batchDelete(@RequestParam("ids[]") Integer[] ids){
		deptService.delete(Arrays.toString(ids));
		return R.ok();
	}

}
