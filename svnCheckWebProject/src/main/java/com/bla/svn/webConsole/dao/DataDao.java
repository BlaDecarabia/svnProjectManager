package com.bla.svn.webConsole.dao;

import com.alibaba.fastjson.JSONArray;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Author: 张巧
 * @Description:
 * @Date: Created in 2019/1/15 10:38
 * @Modified By:
 * @ModifiedDate:
 * @ModifiedDescription
 */
@Component
public interface DataDao {
    List<Map<String, String>> getUatCommitLogList(String user);

    List<Map<String, String>> getScCommitLogList(String user);

    List<Map<String, String>> getUatCommitInfoList(String user);

    List<Map<String, String>> getScCommitInfoList(String user);

    List<Map<String, String>> getFileList(@Param("version") String version, @Param("reposName") String reposName);

}
