package cn.edu.ctbu.sbadmin.common.controller;

import java.util.Map;

import cn.edu.ctbu.sbadmin.common.domain.LogDO;
import cn.edu.ctbu.sbadmin.common.domain.PageDO;
import cn.edu.ctbu.sbadmin.common.service.LogService;
import cn.edu.ctbu.sbadmin.common.utils.Query;
import cn.edu.ctbu.sbadmin.common.utils.R;
import com.github.pagehelper.PageInfo;
import org.aspectj.weaver.tools.cache.AsynchronousFileCacheBacking.RemoveCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;




@RequestMapping("/common/log")
@Controller
public class LogController {
	@Autowired
	LogService logService;
	String prefix = "common/log";

	@GetMapping()
	String log() {
		return prefix + "/log";
	}

//	@ResponseBody
//	@GetMapping("/list")
//	PageInfo<LogDO> list(@RequestParam Map<String, Object> params) {
//		Query query = new Query(params);
//		PageInfo<LogDO> page = logService.getByPage(query);
//		return page;
//	}
	

}
