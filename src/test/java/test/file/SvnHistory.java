package test.java.test.file;
/*
 * ====================================================================
 * Copyright (c) 2004-2011 TMate Software Ltd.  All rights reserved.
 *
 * This software is licensed as described in the file COPYING, which
 * you should have received as part of this distribution.  The terms
 * are also available at http://svnkit.com/license.html
 * If newer versions of this license are posted there, you may use a
 * newer version instead, at your option.
 * ====================================================================
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNLogEntryPath;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

/*
 * The following example program demonstrates how you can use SVNRepository to
 * obtain a history for a range of revisions including (for each revision): all
 * changed paths, log message, the author of the commit, the timestamp when the 
 * commit was made. It is similar to the "svn log" command supported by the 
 * Subversion client library.
 * 
 * As an example here's a part of one of the program layouts (for the default
 * values):
 * 
 * ---------------------------------------------
 * revision: 1240
 * author: alex
 * date: Tue Aug 02 19:52:49 NOVST 2005
 * log message: 0.9.0 is now trunk
 *
 * changed paths:
 *  A  /trunk (from /branches/0.9.0 revision 1239)
 * ---------------------------------------------
 * revision: 1263
 * author: sa
 * date: Wed Aug 03 21:19:55 NOVST 2005
 * log message: updated examples, javadoc files
 *
 * changed paths:
 *  M  /trunk/doc/javadoc-files/javadoc.css
 *  M  /trunk/doc/javadoc-files/overview.html
 *  M  /trunk/doc/examples/src/org/tmatesoft/svn/examples/wc/StatusHandler.java
 * ...
 * 
 */
public class SvnHistory
{
	/*
	 * args parameter is used to obtain a repository location URL, a start
	 * revision number, an end revision number, user's account name & password
	 * to authenticate him to the server.
	 */
	static String csvpath = "D:/qiuyuan/Documents/SVNHistory/ufl_{}.csv";
	static String url = "http://192.168.200.233:8888/svn/CN/Develop/projects/branches/DEV/cnweb_oc";  static String tempPath = "/DEV";
//	static String url = "http://192.168.200.233:8888/svn/CN/Develop/projects/branches/SIT/cnweb_oc";  static String tempPath = "/SIT";
//	static String url = "http://192.168.200.233:8888/svn/CN/Develop/projects/branches/UAT/cnweb_oc";  static String tempPath = "/UAT";
	
	
	static String name = "qiuyuan";
	static String password = "64tR3H2g";
	static long startRevision = 54397;
	static long endRevision = -1;
	static Set<String> authors = new HashSet();
	static String svnMsg = "";

	public static void main(String[] args)
	{

//		authors.add("huangtonghai");
//		authors.add("lizhiyong");
		authors.add("qiuyuan");
		authors.add("tangwenhui");
		//		 authors.add("zhoukailiang");
		// authors.add("linguipei");
		//		 authors.add("duanyaochang");
		//		 authors.add("hewei");
		//authors.add("chenyuning");
		//		 authors.add("shanxiulian");
		/*
		 * Default values:
		 */
		//		long startRevision = 16363;
		//		long endRevision = 13293;
		// HEAD (the latest) revision
		// long endRevision = -1;//HEAD (the latest) revision
		/*
		 * Initializes the library (it must be done before ever using the
		 * library itself)
		 */
		setupLibrary();

		if (args != null)
		{
			/*
			 * Obtains a repository location URL
			 */
			url = (args.length >= 1) ? args[0] : url;
			/*
			 * Obtains the start point of the revisions range
			 */
			startRevision = (args.length >= 2) ? Long.parseLong(args[1]) : startRevision;
			/*
			 * Obtains the end point of the revisions range
			 */
			endRevision = (args.length >= 3) ? Long.parseLong(args[2]) : endRevision;
			/*
			 * Obtains an account name (will be used to authenticate the user to
			 * the server)
			 */
			name = (args.length >= 4) ? args[3] : name;
			/*
			 * Obtains a password
			 */
			password = (args.length >= 5) ? args[4] : password;
		}

		SVNRepository repository = null;

		try
		{
			/*
			 * Creates an instance of SVNRepository to work with the repository.
			 * All user's requests to the repository are relative to the
			 * repository location used to create this SVNRepository. SVNURL is
			 * a wrapper for URL strings that refer to repository locations.
			 */
			repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(url));
		}
		catch (SVNException svne)
		{
			/*
			 * Perhaps a malformed URL is the cause of this exception.
			 */
			System.err.println(
					"error while creating an SVNRepository for the location '" + url + "': " + svne.getMessage());
			System.exit(1);
		}

		/*
		 * User's authentication information (name/password) is provided via an
		 * ISVNAuthenticationManager instance. SVNWCUtil creates a default
		 * authentication manager given user's name and password.
		 * 
		 * Default authentication manager first attempts to use provided user
		 * name and password and then falls back to the credentials stored in
		 * the default Subversion credentials storage that is located in
		 * Subversion configuration area. If you'd like to use provided user
		 * name and password only you may use BasicAuthenticationManager class
		 * instead of default authentication manager:
		 * 
		 * authManager = new BasicAuthenticationsManager(userName,
		 * userPassword);
		 * 
		 * You may also skip this point - anonymous access will be used.
		 */
		ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(name, password);
		repository.setAuthenticationManager(authManager);

		/*
		 * Gets the latest revision number of the repository
		 */
		try
		{
			long svnEendRevision = repository.getLatestRevision();

			endRevision = endRevision < svnEendRevision ? endRevision : svnEendRevision;
			if (endRevision == -1)
			{
				endRevision = svnEendRevision;
			}
		}
		catch (SVNException svne)
		{
			System.err.println("error while fetching the latest repository revision: " + svne.getMessage());
			System.exit(1);
		}

		Collection logEntries = null;
		try
		{
			/*
			 * Collects SVNLogEntry objects for all revisions in the range
			 * defined by its start and end points [startRevision, endRevision].
			 * For each revision commit information is represented by
			 * SVNLogEntry.
			 * 
			 * the 1st parameter (targetPaths - an array of path strings) is set
			 * when restricting the [startRevision, endRevision] range to only
			 * those revisions when the paths in targetPaths were changed.
			 * 
			 * the 2nd parameter if non-null - is a user's Collection that will
			 * be filled up with found SVNLogEntry objects; it's just another
			 * way to reach the scope.
			 * 
			 * startRevision, endRevision - to define a range of revisions you
			 * are interested in; by default in this program - startRevision=0,
			 * endRevision= the latest (HEAD) revision of the repository.
			 * 
			 * the 5th parameter - a boolean flag changedPath - if true then for
			 * each revision a corresponding SVNLogEntry will contain a map of
			 * all paths which were changed in that revision.
			 * 
			 * the 6th parameter - a boolean flag strictNode - if false and a
			 * changed path is a copy (branch) of an existing one in the
			 * repository then the history for its origin will be traversed; it
			 * means the history of changes of the target URL (and all that
			 * there's in that URL) will include the history of the origin
			 * path(s). Otherwise if strictNode is true then the origin path
			 * history won't be included.
			 * 
			 * The return value is a Collection filled up with SVNLogEntry
			 * Objects.
			 */
			logEntries = repository.log(new String[] { "" }, null, startRevision, endRevision, true, true);

		}
		catch (SVNException svne)
		{
			System.out.println("error while collecting log information for '" + url + "': " + svne.getMessage());
			System.exit(1);
		}

		Map<String, Map<String, String>> allMap = new HashMap<String, Map<String, String>>();
		System.out.println("size=" + logEntries.size());
		for (Iterator entries = logEntries.iterator(); entries.hasNext();)
		{
			/*
			 * gets a next SVNLogEntry
			 */
			SVNLogEntry logEntry = (SVNLogEntry) entries.next();
			if (authors.isEmpty() == Boolean.FALSE)
			{
				if (authors.contains(logEntry.getAuthor()) == false)
				{
					continue;
				}
			}
			// Map<String, Set<Map<String, String>>> authorMap =
			// allMsg.get(logEntry.getAuthor());
			// if(authorMap == null){
			// authorMap = new HashMap<String, Set<Map<String,String>>>();
			// allMsg.put(logEntry.getAuthor(), authorMap);
			// }
			System.out.println("---------------------------------------------");
			/*
			 * gets the revision number
			 */
			System.out.println("revision: " + logEntry.getRevision());
			/*
			 * gets the author of the changes made in that revision
			 */
			System.out.println("author: " + logEntry.getAuthor());
			/*
			 * gets the time moment when the changes were committed
			 */
			System.out.println("date: " + logEntry.getDate());
			/*
			 * gets the commit log message
			 */
			System.out.println("log message: " + logEntry.getRevision() + " " + logEntry.getMessage());

			if ("".equals(svnMsg) == Boolean.FALSE)
			{
				if (logEntry.getMessage().contains(svnMsg) == Boolean.FALSE)
					continue;
			}
			/*
			 * displaying all paths that were changed in that revision; cahnged
			 * path information is represented by SVNLogEntryPath.
			 */
			if (logEntry.getChangedPaths().size() > 0)
			{
				System.out.println();
				System.out.println("changed paths:");
				/*
				 * keys are changed paths
				 */
				Set changedPathsSet = logEntry.getChangedPaths().keySet();

				for (Iterator changedPaths = changedPathsSet.iterator(); changedPaths.hasNext();)
				{
					/*
					 * obtains a next SVNLogEntryPath
					 */
					SVNLogEntryPath entryPath = logEntry.getChangedPaths().get(changedPaths.next());
					/*
					 * SVNLogEntryPath.getPath returns the changed path itself;
					 * 
					 * SVNLogEntryPath.getType returns a charecter describing
					 * how the path was changed ('A' - added, 'D' - deleted or
					 * 'M' - modified);
					 * 
					 * If the path was copied from another one (branched) then
					 * SVNLogEntryPath.getCopyPath &
					 * SVNLogEntryPath.getCopyRevision tells where it was copied
					 * from and what revision the origin path was at.
					 */
					String path = entryPath.getPath();
					System.out.println(" " + entryPath.getType() + "	" + path + ((entryPath.getCopyPath() != null)
							? " (from " + entryPath.getCopyPath() + " revision " + entryPath.getCopyRevision() + ")"
							: ""));

					if (path.contains("."))
					{
						path = path.substring(path.indexOf(tempPath));
						Map<String, String> pathMap = allMap.get(path);
						if (pathMap == null)
						{
							pathMap = new HashMap<String, String>();
							allMap.put(path, pathMap);
						}
						pathMap.put("author", logEntry.getAuthor());
						pathMap.put("revision", logEntry.getRevision() + "");
						String type = pathMap.get("type");
						if ("A".equals(type) && 'D' == entryPath.getType())
						{
							allMap.remove(path);
						}
						if (!"A".equals(type))
						{
							pathMap.put("type", entryPath.getType() + "");
						}
					}
				}
			}
		}
		Set<String> keySet = allMap.keySet();
		Map<String, Set<String>> authorMsgMap = new HashMap();
		Set<String> allMsgSet = new TreeSet<String>();

		String contentFormat = "%s,%s,%s,%s";
		for (String key : keySet)
		{
			// if(key.contains("/appinfo/")||key.contains("/verify/")||
			// key.contains("/app/") || key.contains("/contract/")){
			// continue;
			// }
			// if(!key.contains("/appinfo/")){
			// continue;
			// }
			Map<String, String> paths = allMap.get(key);
			String author = paths.get("author");
			Set<String> authorMsgs = authorMsgMap.get(author);
			if (authorMsgs == null)
			{
				authorMsgs = new TreeSet<String>();
				authorMsgMap.put(author, authorMsgs);
			}
			String type = paths.get("type");
			String msgAuth = type + "\t" + paths.get("revision") + "\t" + author + "\t" + key;

			String typeDesc = "";
			if ("A".equals(type))
			{
				typeDesc = "����";
			}
			else if ("M".equals(type))
			{
				typeDesc = "�޸�";
			}
			else if ("D".equals(type))
			{
				typeDesc = "ɾ��";
			}
			// String msg = key+','+paths.get("revision")+','+typeDesc,;
			String msg = String.format(contentFormat, key, typeDesc, paths.get("revision"), getCurrDate("YYYY/MM/dd"));
			allMsgSet.add(msg);

			authorMsgs.add(msgAuth);
		}
		keySet = authorMsgMap.keySet();

		System.out.println("--------------------");
		StringBuffer csvCtx = new StringBuffer();
		for (String msg : allMsgSet)
		{
			System.out.println(msg);
			csvCtx.append(msg + "\n");
		}
		createCsv(csvCtx.toString());
		System.out.println("--------------------");
		for (String key : keySet)
		{
			for (String msg : authorMsgMap.get(key))
			{
				System.out.println(msg);
			}
		}
	}

	/*
	 * Initializes the library to work with a repository via different
	 * protocols.
	 */
	private static void setupLibrary()
	{
		/*
		 * For using over http:// and https://
		 */
		DAVRepositoryFactory.setup();
		/*
		 * For using over svn:// and svn+xxx://
		 */
		SVNRepositoryFactoryImpl.setup();

		/*
		 * For using over file:///
		 */
		FSRepositoryFactory.setup();
	}

	static void createCsv(String content)
	{
		try
		{
			File file = new File(csvpath.replace("{}", getCurrDate("yyyy-MM-dd_HH-mm-ss")));
			System.out.println(file.getPath());
			if (file.getParentFile().exists() == Boolean.FALSE)
			{
				file.getParentFile().mkdirs();
			}
			file.delete();
			file.createNewFile();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "gbk"));
			writer.write(content);
			writer.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	public static String getCurrDate(String format)
	{
		return getDate(new Date(), format);
	}

	public static String getDate(Date date, String format)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
}