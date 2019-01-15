package com.bla.svn.webConsole.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bla.svn.webConsole.dao.OptionDao;
import com.bla.svn.webConsole.service.OptionService;
import com.bla.svn.webConsole.util.RespUtil;
import com.bla.svn.webConsole.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: 张巧
 * @Description:
 * @Date: Created in 2019/1/15 16:05
 * @Modified By:
 * @ModifiedDate:
 * @ModifiedDescription
 */
@Service
@Transactional
public class OptionServiceImpl implements OptionService {

    @Autowired
    OptionDao optionDao;

    @Override
    public JSONObject commitPrepare(String mode, String uuid) {
        String user = SecurityUtil.getUser();

        if ("UAT".equals(mode)) {
            optionDao.insertUatCommitInfo(uuid, user);
            optionDao.updateCommitLogUat(uuid, "Y");
        } else if ("SC".equals(mode)) {
            optionDao.insertScCommitInfo(uuid, user);
            optionDao.updateCommitLogSc(uuid, "Y");
        } else {
            throw new RuntimeException("无该模式的提交！提交的模式：" + mode);
        }

        return RespUtil.createSuccessObj("提交发布成功！");
    }

    @Override
    public JSONObject cancelPrepare(String mode, String uuid, String logUuid) {
        String user = SecurityUtil.getUser();
        if ("UAT".equals(mode)) {
            optionDao.updateUatCommitInfo(uuid, user, "C");
            optionDao.updateCommitLogUat(logUuid, "N");
        } else if ("SC".equals(mode)) {
            optionDao.updateScCommitInfo(uuid, user, "C");
            optionDao.updateCommitLogSc(logUuid, "N");
        } else {
            throw new RuntimeException("无该模式的取消！提交的模式：" + mode);
        }

        return RespUtil.createSuccessObj("取消发布成功！");
    }
}
