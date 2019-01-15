package com.bla.svn.webConsole.aop;

import com.alibaba.fastjson.JSONObject;
import com.bla.svn.webConsole.constant.SystemConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 张巧
 * @Description:
 * @Date: Created in 2018/6/25 17:59
 * @Modified By:
 * @ModifiedDate:
 * @ModifiedDescription
 */
@ControllerAdvice
@ResponseBody
public class ExceptionHandlerAop {
    Logger logger = LoggerFactory.getLogger(ExceptionHandlerAop.class);

    @ExceptionHandler(value = Throwable.class)
    public JSONObject operationError(Exception exception) {

        JSONObject object = createResp(SystemConstants.CODE_FAIL, "未知系统异常：" + exception.toString());
        logger.error("未知系统异常：", exception);
        return object;
    }


    /**
     * 创建异常返回，并解决跨域问题
     * @param code
     * @param errMsg
     * @return
     */
    private JSONObject createResp(String code, String errMsg) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse resp = attributes.getResponse();

        //解决异常返回时的跨域问题
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST");
        resp.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");

        JSONObject object = new JSONObject();
        object.put("rtnCode", code);
        object.put("errMsg", errMsg);
        return object;
    }
}
