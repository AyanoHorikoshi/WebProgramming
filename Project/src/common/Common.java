package common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {
	public static Date convertDate(String str) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // Date型変換
        try {
			return sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
