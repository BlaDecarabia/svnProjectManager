package com.bla.svn.download;

import com.bla.svn.download.manager.SvnManager;
import com.bla.svn.download.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tmatesoft.svn.core.SVNException;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

/**
 * @Author: 张巧
 * @Description:
 * @Date: Created in 2019/1/9 17:39
 * @Modified By:
 * @ModifiedDate:
 * @ModifiedDescription
 */
public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException, SVNException {
        String sql;

        logger.info("测试日志");

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("请输入发布的版本（1.测试环境；2.生产环境）：");
            while (true) {
                String codeType = scanner.nextLine();
                if ("1".equals(codeType)) {
                    sql = PropertiesUtil.getValueByKey("uatCodeSql");
                    break;
                } else if ("2".equals(codeType)) {
                    sql = PropertiesUtil.getValueByKey("scCodeSql");
                    break;
                } else {
                    System.out.println("请输入正确的版本（1.测试环境；2.生产环境）：");
                }
            }

        }

        Class.forName(PropertiesUtil.getValueByKey("jdbcDriver"));
        SvnManager svnManager = new SvnManager();

        try (Connection conn = DriverManager.getConnection(PropertiesUtil.getValueByKey("jdbcUrl"), PropertiesUtil.getValueByKey("jdbcUser"), PropertiesUtil.getValueByKey("jdbcPwd"));
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int version = rs.getInt("VERSION_ID");
                String fileUrl = rs.getString("FILE_URL");
                String fileType = rs.getString("FILE_TYPE").trim();

                if ("U".equals(fileType) || "A".equals(fileType)) {//更新或新增代码
                    logger.info("下载文件：" + fileUrl + "；版本号：" + version);
                    svnManager.downloadFile(fileUrl, version);
                } else if ("D".equals(fileType)) { //删除代码
                    logger.info("删除文件：" + fileUrl + "；版本号：" + version);
                    svnManager.deleteFile(fileUrl);
                }
            }
        }

    }
}
