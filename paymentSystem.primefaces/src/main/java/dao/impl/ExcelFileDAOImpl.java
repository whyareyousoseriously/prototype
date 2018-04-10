/**
 * 下午5:50:33
 * power
 */
package dao.impl;

import java.util.List;
import java.util.Set;

import dao.ExcelFileDAO;
import entity.ExcelFile;
import utils.db.DBOperation;

/**
 * 
 * @author cz 2018年4月8日下午5:50:33
 */
public class ExcelFileDAOImpl implements ExcelFileDAO {

	/* (non-Javadoc)
	 * @see dao.ExcelFileDAO#addFile(entity.ExcelFile)
	 * @author cz
	 * @time 2018年4月10日上午11:26:53
	 */
	public String addFile(ExcelFile excelFile) {
		// 调用工具类DBOperation
		String add_feedback = DBOperation.addData("excelfile", excelFile);
		/*if ("add_success".equals(add_feedback)) {
			return "add_success";
		} else {
			return "add_failure";
		}*/
		return "add_success".equals(add_feedback) ? "add_success" : "add_failure";
	}
	
	/* (non-Javadoc)
	 * @see dao.ExcelFileDAO#AllExcelFilesByCurrentRootID(java.lang.String)
	 * @author cz
	 * @time 2018年4月10日上午11:34:18
	 */
	public Set<ExcelFile> ListExcelFilesByCurrentRootID(String rootId){
		// 调用工具类DBOperation
		Set<ExcelFile> excelFiles = (Set<ExcelFile>) DBOperation.getDataByCondition("ExcelFile", "r_id", rootId);
		return excelFiles == null ? null : excelFiles;
	}

}
