package cn.edu.ctbu.sbadmin.common.utils;

import java.util.List;


/**
 * 自定义的常用类，用于分页查询支持
 */
public class MQuery {
    private static final long serialVersionUID = 1L;
    /**
     * 起始偏移
     */
    private int offset;


    /**
     * 每页的条数
     */
    private int limit;

    /**
     * 排序字段
     */
    private String sort;


    /**
     * 方向
     */
    private String direct;

    /**
     * where参数是一个两重循环的三元组。
     * 其中的三元组分别为 字段名,操作符,值
     * 外层循环(List)为
     */
    private List<QueryAndItems> wherePara;


    /**
     * 构造方法
     * @param wherePara where条件参数对象
     * @param offset
     * @param limit
     * @param sort
     * @param direct
     */
    public MQuery(List<QueryAndItems> wherePara,int offset,int limit,String sort,String direct) {

        this.limit=limit;
        this.offset=offset;
        this.sort=sort;
        this.direct=direct;
        this.wherePara=wherePara;
    }

    public MQuery() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    public String getSort() {
        return sort;
    }

    public String getDirect() {
        return direct;
    }

    public List<QueryAndItems> getWherePara() {
        return wherePara;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setDirect(String direct) {
        this.direct = direct;
    }

    public void setWherePara(List<QueryAndItems> wherePara) {
        this.wherePara = wherePara;
    }

    @Override
    public String toString() {
        return "MQuery{" +
                "offset=" + offset +
                ", limit=" + limit +
                ", sort='" + sort + '\'' +
                ", direct='" + direct + '\'' +
                ", wherePara=" + wherePara +
                '}';
    }
}
