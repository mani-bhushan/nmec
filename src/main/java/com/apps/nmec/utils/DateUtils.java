package com.apps.nmec.utils;

import java.util.Date;

public class DateUtils {

	public static Date convertStringToUtilDate (String dateString) {
		final Date utilDate = ConcurrentDateFormat.convertStringToDate(dateString);
		return utilDate;
	}

	public static String convertUtilDateToString (Date date){
		return date.toString();
	}

	

}
