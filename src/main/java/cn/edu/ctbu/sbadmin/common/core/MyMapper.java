package cn.edu.ctbu.sbadmin.common.core;

import tk.mybatis.mapper.common.*;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface MyMapper<T>
        extends
        BaseMapper<T>,
        ConditionMapper<T>,
        IdsMapper<T>,
        InsertListMapper<T>
        {


}