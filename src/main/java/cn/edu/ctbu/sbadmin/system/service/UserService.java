package cn.edu.ctbu.sbadmin.system.service;



import cn.edu.ctbu.sbadmin.common.core.BaseService;
import cn.edu.ctbu.sbadmin.common.domain.PageDO;
import cn.edu.ctbu.sbadmin.common.utils.MQuery;
import cn.edu.ctbu.sbadmin.system.domain.UserDO;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 * 
 * @author tms
 * @email tms2003@126.com
 * @date 2018-03-04 10:26:21
 */
@Service
public interface UserService extends BaseService<UserDO> {
    PageInfo<UserDO> getByPage(MQuery mquery);


    /**
     * 根据 useName取记录
     * @param userName
     * @return
     */
    List<UserDO> getByUserName(String userName);

    /**
     * 根据 id取用戶密碼
     * @param id
     * @return
     * */
    String getUserPassword(Long id);


}
