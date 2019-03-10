package cn.edu.ctbu.sbadmin.common.core;


import cn.edu.ctbu.sbadmin.common.utils.MQuery;
import cn.edu.ctbu.sbadmin.common.utils.MQueryParam;
import cn.edu.ctbu.sbadmin.common.utils.QueryAndItems;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 基于通用MyBatis Mapper插件的Service接口的实现
 */
@Service
public abstract class AbstractService<T> implements BaseService<T> {

    @Autowired
    protected MyMapper<T> mapper;

    private Class<T> modelClass;    // 当前泛型真实类型的Class

    public AbstractService() {
        // 获得具体model，通过反射来根据属性条件查找数据
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<T>) pt.getActualTypeArguments()[0];
    }

    /**
     * 保存记录
     *
     * @param model
     * @return
     */
    @Override
    public int save(T model) {
        return mapper.insertSelective(model);
    }

    @Override
    public T get(Object id) {
        return mapper.selectByPrimaryKey(id);
    }


    @Override
    public PageInfo<T> list(Map<String, Object> map) {

        // 第一步，先判断是否有offset，limit，如果有，则使用分页
        Example example = new Example(modelClass);


        for (Map.Entry<String, Object> entry : map.entrySet()) {
            //System.out.println("key值："+entry.getKey()+" value值："+entry.getValue());
            String key = entry.getKey();
            Object value = entry.getValue();
            if (!key.equals("offset") && !key.equals("limit")) {
                //不是关键字
                example.createCriteria().andEqualTo(key, value);
            }
        }
        Integer currentPage = 1;

        Integer pageSize = 9999;//默认取相当大的值

        if (map.containsKey("offset") && map.containsKey(("limit"))) {
            currentPage = Integer.parseInt(map.get("offset").toString());
            pageSize = Integer.parseInt(map.get("limit").toString());
        }
        PageHelper.startPage(currentPage, pageSize);
        //第二步，使用example对象构造对应属性
        List<T> pages = mapper.selectByCondition(example);

        PageInfo<T> pageInfo = new PageInfo<T>(pages);
        return pageInfo;
    }


    @Override
    public List<T> list(MQuery mQuery) {


        return getByPage(mQuery).getList();

    }

    @Override
    public PageInfo<T> getByPage(MQuery mQuery) {

        Example example = new Example(modelClass);

        Integer currentPage = 1;

        Integer pageSize = 9999;//默认取相当大的值


        currentPage = mQuery.getOffset();
        pageSize = mQuery.getLimit();

        List<QueryAndItems> Pares = mQuery.getWherePara();  //获取4元组参数列表
        Example.Criteria tj = example.createCriteria();
        Map<String, EntityColumn> mm = example.getPropertyMap();  //获取属性，只处理属性中的字段

        for (QueryAndItems items : Pares) {
            //现阶段只处理了and项，其它的还没有处理

            for (MQueryParam item : items.getQueryList()) {
                String op = item.getAction().replace(" ", "").toLowerCase();
                String P = item.getKey();
                if (mm.containsKey(P)) {
                    String ClassType = mm.get(P).getJavaType().getName();
                    Object V1 = item.getValue1();
                    Object V2 = item.getValue2();

                    switch (op) {
                        case "=":
                            tj.andEqualTo(P, V1);
                            break;
                        case "<":
                            tj.andLessThan(P, V1);
                            break;
                        case ">":
                            tj.andGreaterThan(P, V1);
                            break;
                        case "<=":
                            tj.andLessThanOrEqualTo(P, V1);
                            break;
                        case "=<":
                            tj.andLessThanOrEqualTo(P, V1);
                            break;
                        case ">=":
                            tj.andGreaterThanOrEqualTo(P, V1);
                            break;
                        case "=>":
                            tj.andGreaterThanOrEqualTo(P, V1);
                            break;
                        case "like":
                            tj.andLike(P, "%" + V1 + "%");
                            break;
                        case "between":

                            String S1 = V1.toString();
                            String S2 = V2.toString();

                            if (!StringUtils.isAllBlank(S1))
                                tj.andGreaterThanOrEqualTo(P, S1);
                            if (!StringUtils.isAllBlank(S2))
                                tj.andLessThanOrEqualTo(P, S2);
                            break;

                        default:
                            tj.andEqualTo(P, V1);

                    }
                }
            }
            //end of items


        }

        PageHelper.startPage(currentPage, pageSize);
        List<T> pages = mapper.selectByCondition(example);

        PageInfo<T> pageInfo = new PageInfo<T>(pages);
        return pageInfo;
    }


    @Override
    public Long count(MQuery mQuery) {
        return getByPage(mQuery).getTotal();
    }


    @Override
    public int update(T model) {
        return mapper.updateByPrimaryKeySelective(model);
    }


    @Override
    public int delete(Object id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int batchDelete(String ids) {
        return mapper.deleteByIds(ids);
    }



    @Override
    public List<T> findByIds(Long[] ids) {


        List<T> models= new ArrayList<T>();

        for (Long id:
             ids) {
            T  newItem=get(id);
            models.add(newItem);

        }
        return models;
    }


    @Override
    public List<T> findByIds(long[] ids) {


        List<T> models= new ArrayList<T>();

        for (Long id:
                ids) {
            T  newItem=get(id);
            if(newItem!=null)
                models.add(newItem);

        }
        return models;
    }



    @Override
    public T findBy(String fieldName, Object value) throws TooManyResultsException {
        try {
            T model = modelClass.newInstance();
            Field field = modelClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(model, value);
            return mapper.selectOne(model);
        } catch (ReflectiveOperationException e) {
            throw new TooManyResultsException(e.getMessage(), e);
        }
    }

    @Override
    public List<T> findByAttrName(String AttName, Object value) {
        Example example = new Example(modelClass);
        example.createCriteria().andEqualTo(AttName, value);
        return mapper.selectByCondition(example);

    }

    @Override
    public List<T> findAll() {

        return mapper.selectAll();

    }



}
