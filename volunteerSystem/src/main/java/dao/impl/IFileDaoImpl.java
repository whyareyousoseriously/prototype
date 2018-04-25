/**
 * 上午9:58:59
 * power
 */
package dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dao.IFileDao;
import pojo.FileStorage;
import utils.db.DBOperation;

/**
 * 
 * @author cz
 * 2018年4月23日上午9:58:59
 */
public class IFileDaoImpl implements IFileDao {
	/* (non-Javadoc)
	 * @see dao.ExcelFileDAO#addFile(entity.ExcelFile)
	 * @author cz
	 * @time 2018年4月10日上午11:26:53
	 */
	public String saveFile(FileStorage file) {
		// 调用工具类DBOperation
		FileStorage save_feedback = (FileStorage) DBOperation.saveOrUpdateData("FileStorage", file);
		
		return save_feedback==null ? "add_failure" : "add_success";
	}
	
	/* (non-Javadoc)
	 * @see dao.ExcelFileDAO#AllExcelFilesByCurrentRootID(java.lang.String)
	 * @author cz
	 * @time 2018年4月10日上午11:34:18
	 */
	public Set<FileStorage> ListFilesByCurrentRootID(String userId){
		// 调用工具类DBOperation
		Set<FileStorage> Files = new HashSet<FileStorage>(DBOperation.getDataByCondition("FileStorage", "userId", userId));
		return Files = (Files == null ? new HashSet<FileStorage>() : Files);
	}

	/* (non-Javadoc)
	 * @see dao.IFileDao#getFileNameByUserIdAndItemId(java.lang.String, java.lang.String)
	 * @author cz
	 * @time 2018年4月23日下午12:13:07
	 */
	@Override
	public FileStorage getFileByUserIdAndItemId(String userId, String itemId) {
		// TODO Auto-generated method stub
		List<FileStorage> files = DBOperation.listDataByTwoCondition("FileStorage", "userId", userId, "volunteerItemId", itemId);
		return files.isEmpty() ? new FileStorage() : files.get(0);
	}

}
