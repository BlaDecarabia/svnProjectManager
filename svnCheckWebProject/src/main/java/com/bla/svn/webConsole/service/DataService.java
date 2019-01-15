package com.bla.svn.webConsole.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author: 张巧
 * @Description:
 * @Date: Created in 2019/1/15 11:23
 * @Modified By:
 * @ModifiedDate:
 * @ModifiedDescription
 */
public interface DataService {

    JSONObject getCommitLogList(int page, int rows, String mode, String user);

    JSONObject getCommitInfoList(int page, int rows, String mode, String user);

    JSONObject getFileList(String version);
}
