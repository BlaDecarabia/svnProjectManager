package com.bla.svn.webConsole.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @Author: 张巧
 * @Description:
 * @Date: Created in 2019/1/15 16:05
 * @Modified By:
 * @ModifiedDate:
 * @ModifiedDescription
 */
@Component
public interface OptionDao {

    void insertUatCommitInfo(@Param("uuid") String uuid, @Param("user") String user);

    void updateUatCommitInfo(@Param("uuid") String uuid, @Param("user") String user, @Param("flag") String flag);

    void updateCommitLogUat(@Param("uuid") String uuid, @Param("flag") String flag);

    void insertScCommitInfo(@Param("uuid") String uuid, @Param("user") String user);

    void updateScCommitInfo(@Param("uuid") String uuid, @Param("user") String user, @Param("flag") String flag);

    void updateCommitLogSc(@Param("uuid") String uuid, @Param("flag") String flag);
}
