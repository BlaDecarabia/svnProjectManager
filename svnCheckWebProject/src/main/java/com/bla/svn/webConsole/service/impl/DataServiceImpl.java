package com.bla.svn.webConsole.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bla.svn.webConsole.dao.DataDao;
import com.bla.svn.webConsole.service.DataService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: 张巧
 * @Description:
 * @Date: Created in 2019/1/15 11:23
 * @Modified By:
 * @ModifiedDate:
 * @ModifiedDescription
 */
@Service
public class DataServiceImpl implements DataService {

    @Autowired
    DataDao commonDao;


    @Override
    public JSONObject getCommitLogList(int page, int rows, String mode, String user) {
        JSONObject result = new JSONObject();

        Page<Map<String, String>> pager = PageHelper.startPage(page, rows);
        if ("UAT".equals(mode)) {
            commonDao.getUatCommitLogList(user);
            result.put("total", pager.getTotal());
            result.put("rows", pager.getResult());
        } else if ("SC".equals(mode)) {
            commonDao.getScCommitLogList(user);
            result.put("total", pager.getTotal());
            result.put("rows", pager.getResult());
        } else {
            result.put("rows", new JSONArray());
            result.put("total", 0);
        }
        pager.close();
        return result;
    }

    @Override
    public JSONObject getCommitInfoList(int page, int rows, String mode, String user) {
        JSONObject result = new JSONObject();
        Page<Map<String, String>> pager = PageHelper.startPage(page, rows);

        if ("UAT".equals(mode)) {
            commonDao.getUatCommitInfoList(user);

            result.put("total", pager.getTotal());
            result.put("rows", pager.getResult());
        } else if ("SC".equals(mode)) {
            commonDao.getScCommitInfoList(user);

            result.put("total", pager.getTotal());
            result.put("rows", pager.getResult());
        } else {
            result.put("rows", new JSONArray());
            result.put("total", 0);
        }
        pager.close();

        return result;
    }
}
