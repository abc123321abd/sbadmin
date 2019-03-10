package cn.edu.ctbu.sbadmin.common.service;



import cn.edu.ctbu.sbadmin.common.core.BaseService;
import cn.edu.ctbu.sbadmin.common.domain.DictDO;
import cn.edu.ctbu.sbadmin.system.domain.UserDO;

import java.util.List;
import java.util.Map;

/**
 * 字典表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-29 18:28:07
 */
public interface DictService extends BaseService<DictDO> {
	

	String getName(String type, String value);

	/**
	 * 获取爱好列表
	 * @return
     * @param userDO
	 */
	List<DictDO> getHobbyList(UserDO userDO);

	/**
	 * 获取性别列表
 	 * @return
	 */
	List<DictDO> getSexList();

	/**
	 * 根据type获取数据
	 * @param type
	 * @return
	 */
	List<DictDO> listByType(String type);


	List<DictDO> listType();

}
