package com.bla.svn.webConsole.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 张巧
 * @Description:
 * @Date: Created in 2019/1/14 10:40
 * @Modified By:
 * @ModifiedDate:
 * @ModifiedDescription
 */
@Controller
@RequestMapping("/page")
public class PageController {

    @RequestMapping("/index")
    public String index() {
        return "/index";
    }

}
