package com.bla.svn.postCommit;

import com.bla.svn.postCommit.util.PropertiesUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author: 张巧
 * @Description:
 * @Date: Created in 2019/1/9 15:37
 * @Modified By:
 * @ModifiedDate:
 * @ModifiedDescription
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException {
        String author = args[0];
        String versionId = args[1];
        String commitMsg = args[2];
        String commitFile = args[3];
        String commitDate = args[4];

        Class.forName(PropertiesUtil.getValueByKey("jdbcDriver"));

        try (Connection conn = DriverManager.getConnection(PropertiesUtil.getValueByKey("jdbcUrl"), PropertiesUtil.getValueByKey("jdbcUser"), PropertiesUtil.getValueByKey("jdbcPwd"));
             PreparedStatement insertCommitInfoPs = conn.prepareStatement(PropertiesUtil.getValueByKey("insertCommitInfoSql"));
             PreparedStatement insertCommitFilePs = conn.prepareStatement(PropertiesUtil.getValueByKey("insertFileInfoSql"));
             FileReader fileReader = new FileReader(PropertiesUtil.getValueByKey("baseFilePath") + commitFile);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            conn.setAutoCommit(false);

            //插入基本提交信息
            insertCommitInfoPs.setString(1, versionId);
            insertCommitInfoPs.setString(2, commitMsg);
            insertCommitInfoPs.setString(3, author);
            insertCommitInfoPs.setString(4, commitDate);

            //插入文件内信息
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] info = line.split("\\s+");
                String fileType = info[0];
                String fileUrl = info[1];

                insertCommitFilePs.setString(1, versionId);
                insertCommitFilePs.setString(2, fileUrl);
                insertCommitFilePs.setString(3, fileType);

                insertCommitFilePs.addBatch();
            }

            insertCommitInfoPs.execute();
            insertCommitFilePs.executeBatch();

            conn.commit();
        }

        new File(PropertiesUtil.getValueByKey("baseFilePath") + commitFile).delete();
    }
}
