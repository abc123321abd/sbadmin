package cn.edu.ctbu.sbadmin.common.core;


import cn.edu.ctbu.sbadmin.common.utils.MQuery;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * create @ ctbu
 * Description:
 * User: minttang
 * Date: 2019-02-21
 * Time: 16:11
 */
public  interface BaseService<T> {


    /**
     * 取指定id对应的记录
     *
     * @param id
     * @return
     */
    T get(Object id);


    /**
     * 返回查询记录的结果，支持分页
     *
     * @param mQuery
     * @return
     */
    public List<T> list(MQuery mQuery);

    /**
     * 返回查询记录的结果，支持分页
     * @param map
     * @return
     */
    public PageInfo<T> list(Map<String, Object> map);



    /**
     * 返回查询记录的结果，支持分页
     *
     * @param mQuery
     * @return
     */
    public PageInfo<T> getByPage(MQuery mQuery);

    /**
     * 查询指定条件的计数，复杂条件支持
     *
     * @param mQuery
     * @return
     */
    Long count(MQuery mQuery);


    /**
     * 保存记录
     *
     * @param model
     * @return
     */
    int save(T model);


    /**
     * 更新记录
     *
     * @param model
     * @return
     */
    int update(T model);



    /**
     * 删除指定id的对象，真删除
     * <<<<<<< HEAD
     *
     * @param id
     * @return
     */
    int delete(Object id);


    /**
     * 批量删除，真删除
     *
     * @param ids
     * @return
     */
    int batchDelete(String ids);


    /**
     * 读取对应的ids下的所有记录
     *
     * @param ids
     * @return
     */
    List<T> findByIds(Long[] ids);


    /**
     * 读取对应的ids下的所有记录,只是 类型不同
     *
     * @param ids
     * @return
     */
    List<T> findByIds(long[] ids);


    /**
     * 根据属性名来取对应唯一对象
     * @param fieldName
     * @param value
     * @return
     */
    public T findBy(String fieldName, Object value);

    /**
     *
     * @param AttName 根据属性名来取对象
     * @param value
     * @return
     */
    public List<T> findByAttrName(String AttName, Object value);


    /**
     *返回所有的数据
     *
     * @return
     */
    List<T> findAll();



}