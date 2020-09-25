package br.com.saulofarias.customerapi.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static Date stringToDate(String date) throws ParseException {
		String pattern = "yyyy-MM-dd";
		return new SimpleDateFormat(pattern).parse(date);
	}

	public static String dateToString(Date date) throws ParseException {
		String pattern = "yyyy-MM-dd";
		return new SimpleDateFormat(pattern).format(date);
	}
}
