/**
 * 上午9:58:18
 * power
 */
package dao;

import java.util.Set;

import pojo.FileStorage;

/**
 * 
 * @author cz
 * 2018年4月23日上午9:58:18
 */
public interface IFileDao {
	
	/**
	 * 将文件存入数据库
	 * @param file
	 * @return
	 * @author cz
	 * @time 2018年4月23日上午10:08:11
	 */
	String saveFile(FileStorage file);
	
	/**
	 * 将文件取出
	 * @param userId
	 * @return
	 * @author cz
	 * @time 2018年4月23日上午10:08:14
	 */
	Set<FileStorage> ListFilesByCurrentRootID(String userId);
	
	
	/**
	 * 获取文件名
	 * @param userId
	 * @param itemId
	 * @return
	 * @author cz
	 * @time 2018年4月23日下午12:12:53
	 */
	FileStorage getFileByUserIdAndItemId(String userId,String itemId);

}
