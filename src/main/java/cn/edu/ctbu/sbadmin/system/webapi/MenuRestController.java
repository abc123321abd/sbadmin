package cn.edu.ctbu.sbadmin.system.webapi;


import cn.edu.ctbu.sbadmin.common.config.MyAppConfig;
import cn.edu.ctbu.sbadmin.common.controller.BaseRestController;
import cn.edu.ctbu.sbadmin.common.domain.TreeDO;
import cn.edu.ctbu.sbadmin.common.utils.*;
import cn.edu.ctbu.sbadmin.system.domain.MenuDO;
import cn.edu.ctbu.sbadmin.system.domain.UserDO;
import cn.edu.ctbu.sbadmin.system.service.MenuService;
import cn.edu.ctbu.sbadmin.system.service.UserService;
import cn.edu.ctbu.sbadmin.system.vo.UserVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.util.*;

/**
 * 菜单管理
 *
 * @author tms
 * @email tms2003@126.com
 * @date 2018-03-11 13:03:34
 */

@RestController
@RequestMapping("/webapi/system/menu")
public class MenuRestController extends BaseRestController {
	@Autowired
	private MenuService menuService;
	@Autowired
	private MyAppConfig myAppConfig;
	@Autowired
	private UserService userService;

	/**
	 * 取数据
	 */
	@RequiresPermissions("system:menu:menu")
	@RequestMapping("/get")
	public R get(Integer id) {
		MenuDO Menu = menuService.get(id);

		if (Menu != null) {
			return R.ok("data", Menu);
		}

		return R.error(-1, "出错了，找不到此数据!");
	}

	@RequiresPermissions("system:menu:menu")
	@RequestMapping("/getAll")
	public List<MenuDO> getAll() throws Exception {

		List<MenuDO> menuList = menuService.findAll();


		return menuList;
	}

	@RequiresPermissions("system:menu:menu")
	@RequestMapping("/list")
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

		where = URLDecoder.decode(where, "UTF-8");

		MQuery mQuery = new MQuery();

		List<QueryAndItems> queryAndItemsList = MQueryHelper.WhereToQueryItems(where);

		mQuery.setWherePara(queryAndItemsList);

		mQuery.setSort(sort);
		mQuery.setDirect(direct);
		mQuery.setLimit(pageSize);
		mQuery.setOffset(pageIndex);

		List<MenuDO> menuList = menuService.list(mQuery);
		Long total = menuService.count(mQuery);
		PageUtils pageUtils = new PageUtils(menuList, total.intValue());

		return pageUtils;
	}

	/**
	 * 保存
	 */

	@PostMapping("/save")
	@RequiresPermissions("system:menu:add")
	public R save( MenuDO menu){
		Integer pId = menu.getParentId();

		if (pId > 0){
			//读取父结点，
			MenuDO parent = menuService.get(pId);
			menu.setLevel(parent.getLevel()+1);
		} else {
			//一级结点，level=1
			menu.setLevel(1);
		}

		menu.setGmtCreate(new Date());

		if (menuService.save(menu)>0) {
			return R.ok();
		}

		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:menu:edit")
	public R update( MenuDO menu){
		Integer pId = menu.getParentId();

		if (pId > 0) {
			//读取父结点，
			MenuDO parent = menuService.get(pId);
			menu.setLevel(parent.getLevel()+1);
		} else {
			//一级结点，level=1
			menu.setLevel(1);
		}

		menu.setGmtModified(new Date());
		menuService.update(menu);

		return R.ok();
	}

	/**
	 * 物理删除
	 */
	@PostMapping( "/delete")
	@RequiresPermissions("system:menu:remove")
	public R delete(Integer id){
		if (menuService.delete(id) > 0){
			return R.ok();
		}

		return R.error();
	}



	/**
	 * 删除
	 */
	@PostMapping( "/batchDelete")
	@RequiresPermissions("system:menu:batchRemove")
	public R batchDelete(@RequestParam("ids[]") Integer[] ids){
		menuService.batchDelete(Arrays.toString(ids));
		return R.ok();
	}

	@GetMapping("/tree")
	@RequiresPermissions("system:menu:menu")
	TreeDO<MenuDO> tree() {
		TreeDO<MenuDO> tree = new TreeDO<MenuDO>();
		tree = menuService.getTree();
		return tree;
	}

	@GetMapping("/menuids/{roleId}")
	@RequiresPermissions("system:menu:menu")
	R getIdsByRoleId(@PathVariable("roleId") Integer roleId){
		String  menuIds = menuService.listMenuIdByRoleId(roleId);

		return R.ok("data",menuIds);
	}

	@GetMapping("/tree/{roleId}")
	@RequiresPermissions("system:menu:menu")
	TreeDO<MenuDO> tree(@PathVariable("roleId") Integer roleId) {
		TreeDO<MenuDO> tree = new TreeDO<MenuDO>();
		tree = menuService.getTree(roleId);
		return tree;
	}

	/**
	 * return all tree
	 */
	@GetMapping("/ztreeAll")
	@RequiresPermissions("system:menu:menu")
	public  List<ZTreeNode> ZTreeAll()
	{
		MQuery mQuery = new MQuery();
		List<QueryAndItems> queryAndItemsList = MQueryHelper.WhereToQueryItems("");

		mQuery.setWherePara(queryAndItemsList);
		mQuery.setSort("parent_id asc,order_num");
		mQuery.setDirect("asc");
		mQuery.setLimit(999999);
		mQuery.setOffset(0);

		List<MenuDO> items = menuService.list(mQuery);
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
			MenuDO item=items.get(i);
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
	 * 写入角色权限
	 */
	@PostMapping( "/rolemenus")
	@RequiresPermissions("system:menu:menu")
	public R roleMenus(@RequestParam("ids[]") Integer[] ids, @RequestParam("roleId")Integer roleId){
		menuService.SaveRoleMenu(roleId,ids);
		return R.ok();
	}

	/**
	 * 登录用户菜单
	 * @return
	 */
	@GetMapping({ "/getusermenu" })
	@RequiresPermissions("system:menu:menu")
	public R  getusermenu() {
		Long userId=getUserId();
		List<TreeDO<MenuDO>> menus = menuService.listMenuTree(userId);
		UserDO userDO = userService.get(userId);
		UserVO userVO = new UserVO();

		//需求：把s1的属性值拷贝到S2中，注意参数的顺序,在spring中是反的
		//BeanUtils.copyProperties(s1, s2);
		BeanUtils.copyProperties( userDO,userVO);

//		model.addAttribute("menus", menus);
//		model.addAttribute("name", getUser().getName());
//		FileDO fileDO = fileService.get(getUser().getPicId());
//		if(fileDO!=null&&fileDO.getUrl()!=null){
//			if(fileService.isExist(fileDO.getUrl())){
//				model.addAttribute("picUrl",fileDO.getUrl());
//			}else {
//				model.addAttribute("picUrl","/img/photo_s.jpg");
//			}
//		}else {
//			model.addAttribute("picUrl","/img/photo_s.jpg");
//		}
//		model.addAttribute("username", getUser().getUsername());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data",menus);
		map.put("sysconfig",myAppConfig);//系统基础信息
		map.put("user",userVO);

		return R.ok(map);
	}

}
