package com.bla.svn.webConsole.controller;

import com.alibaba.fastjson.JSONObject;
import com.bla.svn.webConsole.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 张巧
 * @Description:
 * @Date: Created in 2019/1/15 16:03
 * @Modified By:
 * @ModifiedDate:
 * @ModifiedDescription
 */
@RestController
@RequestMapping("/option")
public class OptionController {

    @Autowired
    OptionService optionService;

    @RequestMapping("/commitPrepare")
    public JSONObject commitPrepare(@RequestBody JSONObject param) {
        String mode = param.getString("mode");
        String uuid = param.getString("uuid");

        return optionService.commitPrepare(mode, uuid);
    }

    @RequestMapping("/cancelPrepare")
    public JSONObject cancelPrepare(@RequestBody JSONObject param) {
        String mode = param.getString("mode");
        String uuid = param.getString("uuid");
        String logUuid = param.getString("logUuid");

        return optionService.cancelPrepare(mode, uuid, logUuid);
    }

}
