package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	/*
	 * 
	 * */
	public static String formatDay(Date date) {
		if(date == null) {
			return "";
		}else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			return sdf.format(date);
		}
	}
}
