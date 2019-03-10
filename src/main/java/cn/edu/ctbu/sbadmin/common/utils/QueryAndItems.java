package cn.edu.ctbu.sbadmin.common.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 *  查询中的And子项，其中每个项之间都是一个四元组
 */
public class QueryAndItems implements Serializable{
    private List<MQueryParam> queryList;

    @Override
    public String toString() {
        return "QueryAndItems{" +
                "queryList=" + queryList +
                '}';
    }

    /**
     * 返回四元组中的元素个数
     * @return
     */
    public  int getSize(){
        return queryList.size();
    }

    public QueryAndItems() {
        super();

        this.queryList = new ArrayList<MQueryParam>();
    }

    public  void pushAndItem(String action,String key,Object value1,Object value2){
        this.queryList.add(new MQueryParam(action,key,value1,value2));

    }


    public void setQueryList(List<MQueryParam> queryList) {
        this.queryList = queryList;
    }

    public List<MQueryParam> getQueryList() {

        return queryList;
    }
}
