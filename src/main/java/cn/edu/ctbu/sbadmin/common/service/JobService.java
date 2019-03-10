package cn.edu.ctbu.sbadmin.common.service;

import java.util.List;
import java.util.Map;

import cn.edu.ctbu.sbadmin.common.core.BaseService;
import cn.edu.ctbu.sbadmin.common.domain.TaskDO;
import org.quartz.SchedulerException;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-26 20:53:48
 */
public interface JobService extends BaseService<TaskDO> {


	void initSchedule() throws SchedulerException;

	void changeStatus(Long jobId, String cmd) throws SchedulerException;

	void updateCron(Long jobId) throws SchedulerException;
}
