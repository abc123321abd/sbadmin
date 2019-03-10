package cn.edu.ctbu.sbadmin.system.service.impl;

import cn.edu.ctbu.sbadmin.common.utils.Helper;
import cn.edu.ctbu.sbadmin.common.utils.MQuery;
import cn.edu.ctbu.sbadmin.common.utils.MQueryHelper;
import cn.edu.ctbu.sbadmin.common.utils.QueryAndItems;
import cn.edu.ctbu.sbadmin.system.domain.MenuDO;
import cn.edu.ctbu.sbadmin.system.service.MenuService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;



@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuServiceImplTest {

    @Autowired
    MenuService menuService;

    @Test
    public void list() {
        //测试基本的list

        Map<String,Object> mymap=new HashMap<String,Object>();
        mymap.put("limit",10);
        mymap.put("offset",1);

        mymap.put("parentId",0);


        PageInfo<MenuDO> menuDOPageInfo=menuService.list(mymap);

        System.out.println("menu list:"+menuDOPageInfo.toString());



    }


    @Test
    public void getByPage()  throws  Exception{
        //测试基本的list
        String where= "parentId=0^:="; //truename=张^:like
        where = URLDecoder.decode(where, "UTF-8");

        MQuery mQuery = new MQuery();
        Integer pageIndex=1, pageSize=10;
        String sort="id", direct="desc";


        sort = Helper.camelToUnderline(sort);//反驼峰转换
        List<QueryAndItems> queryAndItemsList = MQueryHelper.WhereToQueryItems(where);

        mQuery.setWherePara(queryAndItemsList);
        mQuery.setSort(sort);
        mQuery.setDirect(direct);
        mQuery.setLimit(pageSize);
        mQuery.setOffset(pageIndex);


        PageInfo<MenuDO> menuDOPageInfo=menuService.getByPage(mQuery);

        System.out.println("menu list:"+menuDOPageInfo.toString());



    }


}