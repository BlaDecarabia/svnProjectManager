package com.bla.svn.webConsole.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author: 张巧
 * @Description:
 * @Date: Created in 2019/1/15 16:05
 * @Modified By:
 * @ModifiedDate:
 * @ModifiedDescription
 */
public interface OptionService {

    JSONObject commitPrepare(String mode, String uuid);

    JSONObject cancelPrepare(String mode, String uuid, String logUuid);
}
