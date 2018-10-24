package test.java.test.file;

import org.tmatesoft.svn.core.*;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author suguanting on 2018/7/20.
 */
public class SvnOper {
    /**
     * 通过不同的协议初始化版本库
     */
    public static void setupLibrary() {
        DAVRepositoryFactory.setup();
        SVNRepositoryFactoryImpl.setup();
        FSRepositoryFactory.setup();
    }

    /**
     * 验证登录svn
     */
    public static SVNClientManager authSvn(String svnRoot, String username,
                                           String password) {
        // 初始化版本库
        setupLibrary();

        // 创建库连接
        SVNRepository repository = null;
        try {
            repository = SVNRepositoryFactory.create(SVNURL
                    .parseURIEncoded(svnRoot));
        } catch (SVNException e) {
            System.out.println(e.getErrorMessage());
            return null;
        }

        // 身份验证
        ISVNAuthenticationManager authManager = SVNWCUtil

                .createDefaultAuthenticationManager(username, password);

        // 创建身份验证管理器
        repository.setAuthenticationManager(authManager);

        DefaultSVNOptions options = (DefaultSVNOptions) SVNWCUtil.createDefaultOptions(true);
        SVNClientManager clientManager = SVNClientManager.newInstance(options,
                authManager);
        return clientManager;
    }

    public static SVNRepository authSvnRep(String svnRoot, String username,
                                           String password) {
        // 初始化版本库
        setupLibrary();

        // 创建库连接
        SVNRepository repository = null;
        try {
            repository = SVNRepositoryFactory.create(SVNURL
                    .parseURIEncoded(svnRoot));
        } catch (SVNException e) {
            System.out.println(e.getErrorMessage());
            return null;
        }

        // 身份验证
        ISVNAuthenticationManager authManager = SVNWCUtil

                .createDefaultAuthenticationManager(username, password);

        // 创建身份验证管理器
        repository.setAuthenticationManager(authManager);

        return repository;
    }

    public static Map<String,String> getDirFiles(SVNRepository repository,Map<String,String> map,String dir) throws SVNException {
        Collection<SVNDirEntry> dirEntries = repository.getDir(dir, -1, null,(Collection) null);
        for(SVNDirEntry entry : dirEntries){
            //System.out.println("getDirFiles:"+entry.getURL().getPath().replace("/svn/CN",""));
            map.put(entry.getURL().getPath().replace("/svn/CN",""),"A");
            if("dir".equals(entry.getKind().toString())){
                map = SvnOper.getDirFiles(repository,map,entry.getURL().getPath().replace("/svn/CN",""));
            }
        }
        return map;
    }
    public static void main(String[] args) throws SVNException {
        //String url = "http://192.168.200.233:8888/svn/CN/Develop/projects/branches/SIT/hyper";
        String url = "http://192.168.190.233:8888/svn/CN/Develop/projects/branches/DEV/cnweb_eip";
        SVNRepository repository = SvnOper.authSvnRep(url,"suguanting","jhgb6efv");
        Collection<SVNLogEntry> logEntries = repository.log(new String[] { "" }, null, 60735, 60734, true, true);
        //System.out.println(logEntries.toString());
        Map<String,String> map = new HashMap<>();
        for(SVNLogEntry entry : logEntries){
            Map<String, SVNLogEntryPath> paths = entry.getChangedPaths();
            for(String key : paths.keySet()){
                SVNLogEntryPath pathEntry = paths.get(key);
                if(null == map.get(pathEntry.getPath())) {
                    if(!"dir".equals(pathEntry.getKind().toString())) {
                        map.put(pathEntry.getPath(), String.valueOf(pathEntry.getType()));
                    }
                    /*if("dir".equals(pathEntry.getKind().toString())){
                        map = SvnOper.getDirFiles(repository,map,pathEntry.getPath());
                    }*/
                }
            }
        }
        for(String key : map.keySet()){
            System.out.println(map.get(key)+":"+key);
        }
        System.out.println("总数："+map.size());
        //System.out.println(JSON.toJSONString(map));
    }


}
