package cn.edu.ctbu.sbadmin.common.service.impl;


import cn.edu.ctbu.sbadmin.common.config.BootdoConfig;
import cn.edu.ctbu.sbadmin.common.core.AbstractService;
import cn.edu.ctbu.sbadmin.common.dao.FileDao;
import cn.edu.ctbu.sbadmin.common.domain.FileDO;
import cn.edu.ctbu.sbadmin.common.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Map;


import org.springframework.util.StringUtils;


@Service
public class FileServiceImpl extends AbstractService<FileDO> implements FileService {
	@Autowired
	private FileDao sysFileMapper;

	@Autowired
	private BootdoConfig bootdoConfig;


    @Override
    public Boolean isExist(String url) {
		Boolean isExist = false;
		if (!StringUtils.isEmpty(url)) {
			String filePath = url.replace("/files/", "");
			filePath = bootdoConfig.getUploadPath() + filePath;
			File file = new File(filePath);
			if (file.exists()) {
				isExist = true;
			}
		}
		return isExist;
	}
	}
