package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Converter {
	
	public static String formatFechaString(Date date){
		String fecha = "";
		if(date!=null) {
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			fecha = df.format(date);
			return fecha;
		}
		return "";
	}
	
	public static Date formatFechaDate(String date){
		try {
			if( date!=null && !date.isEmpty() ){
				Date d = (new SimpleDateFormat("dd-MM-yyyy")).parse(date);
				return d;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date formatDate(Date date){
		if(date!=null) {
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			try {return df.parse(df.format(date));} catch (ParseException e) {return null;}
		}
		return null;
	}

}
