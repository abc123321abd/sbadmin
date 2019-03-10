package cn.edu.ctbu.sbadmin.system.service;



import cn.edu.ctbu.sbadmin.common.core.BaseService;
import cn.edu.ctbu.sbadmin.common.domain.PageDO;
import cn.edu.ctbu.sbadmin.common.utils.MQuery;
import cn.edu.ctbu.sbadmin.system.domain.ConfigDO;
import cn.edu.ctbu.sbadmin.system.domain.RoleDO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tms on 18/2/27.
 */
@Service
public interface RoleService  extends BaseService<RoleDO> {


    /**
     * 根据 roleName取记录
     * @param roleName
     * @return
     */
    List<RoleDO> getByRoleName(String roleName);

    /**
     * 根据 roleSign取记录
     * @param roleSign
     * @return
     */
    List<RoleDO> getByRoleSign(String roleSign);


}
