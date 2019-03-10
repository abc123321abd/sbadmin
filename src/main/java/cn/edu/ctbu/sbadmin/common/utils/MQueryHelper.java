package cn.edu.ctbu.sbadmin.common.utils;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class MQueryHelper {
    static String signalSQL=",<>,>,<,=,like,";
    public static List<QueryAndItems> WhereToQueryItems(String where){
        //注意：这个方法只支持and，不支持or
      //  把where条件的参数转为QueryAndItems,默认操作为=号
//        其中,where条件需要我们自己组装.示例:
//        name=张^:like&time=2^:<&dmtCreate=2017-01-01^^2018-01-01
//        这个表示:
//        (name like '%张%' )and ( time<2 ) and (dmtCreate between '2017-01-01' and '2018-01-01)




        // 查询列表数据
        List<QueryAndItems> queryAndItemsList = new ArrayList<QueryAndItems>();


        //(1)按&分成数组
        String[] whereParas=where.split("&");
        QueryAndItems queryAndItems = new QueryAndItems();
        for (String wherePara:whereParas) {

            if(wherePara!=null && !wherePara.equals("")){

                //再按=分割
                String[] keyVs=wherePara.split("=");
                String key=keyVs[0];
                String values=keyVs[1];//键值对
                String[] values1=values.split("\\^:");
                if(values1.length>1){
                    //有指定符号，按符号分割
                    String singal=values1[1];//这是符号,只支持
                    if(signalSQL.indexOf(","+singal+",")>=0){
                        //合法参数

                        //key = Helper.camelToUnderline(key);//反驼峰转换
                        queryAndItems.pushAndItem(singal,key ,"%" +values1[0]+"%", "");

                    }else{
                        //非法参数，去掉
                    }



                }else{
                    //没有了，试着使用^^分割，找between
                    String[] values2=values.split("\\^\\^");
                    if(values2.length>1){
                        //使用between and
                        queryAndItems.pushAndItem("between",key,values2[0],values2[1]);
                    }else{
                        //没有任何分割了，只是使用=号即可。这是默认操作
                        queryAndItems.pushAndItem("=",key , values1[0], "");
                    }

                }




            }

            if(queryAndItems.getSize()>0){
                queryAndItemsList.add(queryAndItems);//已经放了内容，加入
            }


            
        }



        return queryAndItemsList;
    }

    /**
     * 根据参数，生成MQuery对象
     * @param where
     * @param pageSize
     * @param pageIndex
     * @param sort
     * @param direct
     * @return
     * @throws Exception
     */
    public static MQuery GenQuery(String where,
                                  Integer pageSize,
                                  Integer pageIndex,
                                  String sort,
                                  String direct)throws Exception{
        where= URLDecoder.decode(where,"UTF-8");

        MQuery mQuery = new MQuery();

        List<QueryAndItems> queryAndItemsList=  MQueryHelper.WhereToQueryItems(where);
        mQuery.setWherePara(queryAndItemsList);

        mQuery.setSort(sort);
        mQuery.setDirect(direct);
        mQuery.setLimit(pageSize);
        mQuery.setOffset(pageIndex);

        return mQuery;

    }

}
