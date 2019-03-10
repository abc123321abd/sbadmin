package cn.edu.ctbu.sbadmin.common.service.impl;


import cn.edu.ctbu.sbadmin.common.core.AbstractService;
import cn.edu.ctbu.sbadmin.common.dao.DictDao;
import cn.edu.ctbu.sbadmin.common.domain.DictDO;
import cn.edu.ctbu.sbadmin.common.service.DictService;
import cn.edu.ctbu.sbadmin.common.utils.StringUtils;
import cn.edu.ctbu.sbadmin.system.domain.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;




@Service
public class DictServiceImpl  extends AbstractService<DictDO> implements DictService {
    @Autowired
    private DictDao dictDao;


    @Override
    public String getName(String type, String value) {
        Map<String, Object> param = new HashMap<String, Object>(16);
        param.put("type", type);
        param.put("value", value);
        String rString = super.list(param).getList().get(0).getName();
        return rString;
    }

    @Override
    public List<DictDO> getHobbyList(UserDO userDO) {
        Map<String, Object> param = new HashMap<>(16);
        param.put("type", "hobby");
        List<DictDO> hobbyList = super.list(param).getList();

        if (StringUtils.isNotEmpty(userDO.getHobby())) {
            String userHobbys[] = userDO.getHobby().split(";");
            for (String userHobby : userHobbys) {
                for (DictDO hobby : hobbyList) {
                    if (!Objects.equals(userHobby, hobby.getId().toString())) {
                        continue;
                    }
                    hobby.setRemarks("true");
                    break;
                }
            }
        }

        return hobbyList;
    }

    @Override
    public List<DictDO> getSexList() {
        Map<String, Object> param = new HashMap<>(16);
        param.put("type", "sex");
        return  super.list(param).getList();
    }

    @Override
    public List<DictDO> listByType(String type) {
        Map<String, Object> param = new HashMap<>(16);
        param.put("type", type);
        return super.list(param).getList();
    }

    @Override
    public List<DictDO> listType() {
        return  dictDao.listType();
    }

}
