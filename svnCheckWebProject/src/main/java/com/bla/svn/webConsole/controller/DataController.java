package com.bla.svn.webConsole.controller;

import com.alibaba.fastjson.JSONObject;
import com.bla.svn.webConsole.service.DataService;
import com.bla.svn.webConsole.util.RespUtil;
import com.bla.svn.webConsole.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 张巧
 * @Description:
 * @Date: Created in 2019/1/14 14:16
 * @Modified By:
 * @ModifiedDate:
 * @ModifiedDescription
 */
@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    DataService dataService;

    @RequestMapping("/getUser")
    public JSONObject getUser() {
        return RespUtil.createSuccessObj(SecurityUtil.getUser());
    }

    @RequestMapping("/commitLogList")
    public JSONObject commitLogList(int page, int rows, String mode) {
        String user = SecurityUtil.getUser();
        return dataService.getCommitLogList(page, rows, mode, user);
    }

    @RequestMapping("/commitInfoList")
    public JSONObject commitInfoList(int page, int rows, String mode) {
        String user = SecurityUtil.getUser();
        return dataService.getCommitInfoList(page, rows, mode, user);
    }

    @RequestMapping("/fileList")
    public JSONObject fileList(String version) {
        return dataService.getFileList(version);
    }
}
