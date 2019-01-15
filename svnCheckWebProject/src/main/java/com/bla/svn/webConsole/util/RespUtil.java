package com.bla.svn.webConsole.util;

import com.alibaba.fastjson.JSONObject;
import com.bla.svn.webConsole.constant.SystemConstants;

/**
 * @Author: 张巧
 * @Description:
 * @Date: Created in 2018/11/12 15:08
 * @Modified By:
 * @ModifiedDate:
 * @ModifiedDescription
 */
public class RespUtil {
    public static JSONObject createSuccessObj(String msg) {
        JSONObject result = new JSONObject();
        result.put("rtnCode", SystemConstants.CODE_SUCCESS);
        result.put("msg", msg);
        return result;
    }

    public static JSONObject createSuccessObj(String msg, JSONObject exInfo) {
        JSONObject result = new JSONObject();
        result.put("rtnCode", SystemConstants.CODE_SUCCESS);
        result.put("msg", msg);
        result.put("exInfo", exInfo);
        return result;
    }

    public static JSONObject createSuccessObj(JSONObject msg) {
        JSONObject result = new JSONObject();
        result.put("rtnCode", SystemConstants.CODE_SUCCESS);
        result.put("msg", msg);
        return result;
    }
}
