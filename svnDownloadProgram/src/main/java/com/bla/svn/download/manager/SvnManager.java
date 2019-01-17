package com.bla.svn.download.manager;

import com.bla.svn.download.util.PropertiesUtil;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.internal.wc17.SVNWCContext;
import org.tmatesoft.svn.core.wc.*;

import java.io.File;
import java.io.IOException;


/**
 * @Author: 张巧
 * @Description:
 * @Date: Created in 2019/1/9 17:52
 * @Modified By:
 * @ModifiedDate:
 * @ModifiedDescription
 */
public class SvnManager {

    private SVNUpdateClient svnUpdateClient;

    /**
     * 初始化SVN客户端
     * @throws IOException
     */
    private void initSvnUpdateClient() throws IOException {
        DAVRepositoryFactory.setup();
        SVNRepositoryFactoryImpl.setup();
        FSRepositoryFactory.setup();

        ISVNOptions isvnOptions = SVNWCUtil.createDefaultOptions(true);
        SVNClientManager manager = SVNClientManager.newInstance((DefaultSVNOptions) isvnOptions, PropertiesUtil.getValueByKey("svnAccount"), PropertiesUtil.getValueByKey("svnPwd"));
        SVNUpdateClient svnUpdateClient = manager.getUpdateClient();
        svnUpdateClient.setIgnoreExternals(true);

        this.svnUpdateClient = svnUpdateClient;
    }

    /**
     * 下载SVN文件的方法
     * @param svnFileUrl
     * @param version
     * @throws IOException
     * @throws SVNException
     */
    public void downloadFile(String svnFileUrl, int version) throws IOException, SVNException {
        String svnUrl = PropertiesUtil.getValueByKey("svnUrl") + svnFileUrl;
        String filePath = PropertiesUtil.getValueByKey("projectBasePath") + svnFileUrl.substring("trunk".length());

        if (this.svnUpdateClient == null) {
            initSvnUpdateClient();
        }

        SVNRevision svnRevision = SVNRevision.create(version);
        SVNURL url = SVNURL.parseURIEncoded(svnUrl);
        File destFile = new File(filePath);

        this.svnUpdateClient.doExport(url, destFile, svnRevision, svnRevision, SVNWCContext.SVNEolStyle.Native.toString(), true, SVNDepth.EMPTY);
    }

    public void deleteFile(String svnFileUrl) throws IOException {
        String filePath = PropertiesUtil.getValueByKey("projectBasePath") + svnFileUrl.substring("trunk".length());
        File file = new File(filePath);

        if (file.exists()) {
            file.delete();
        }
    }
}
