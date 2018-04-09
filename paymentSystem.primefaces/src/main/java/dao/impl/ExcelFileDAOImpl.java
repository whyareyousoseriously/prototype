/**
 * 下午5:50:33
 * power
 */
package dao.impl;

import dao.ExcelFileDAO;
import entity.ExcelFile;
import utils.db.DBOperation;

/**
 * 
 * @author cz 2018年4月8日下午5:50:33
 */
public class ExcelFileDAOImpl implements ExcelFileDAO {

	public String addFile(ExcelFile excelFile) {
		// 调用工具类DBOperation
		String add_feedback = DBOperation.addData("excelfile", excelFile);
		if ("add_success".equals(add_feedback)) {
			return "add_success";
		} else {
			return "add_failure";
		}
	}

}
