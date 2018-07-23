package cn.eddy.utils.dateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *  时间工具 Utils
 *  @author 申晓琛
 *  @deprecated 时间操作
 */
public class DateUtil {
	
	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

	private final static SimpleDateFormat sdfDay = new SimpleDateFormat(
			"yyyy-MM-dd");
	
	private final static SimpleDateFormat sdfDays = new SimpleDateFormat(
	"yyyyMMdd");

	private final static SimpleDateFormat sdfTime = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/**
	 * 获取YYYY格式
	 * 
	 * @return
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD格式
	 * 
	 * @return
	 */
	public static String getDay() {
		return sdfDay.format(new Date());
	}
	
	/**
	 * 获取YYYYMMDD格式
	 * 
	 * @return
	 */
	public static String getDays(){
		return sdfDays.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 * 
	 * @return
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}

	/**
	* @Title: compareDate
	* @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
	* @param s
	* @param e
	* @return boolean  
	* @throws
	* @author luguosui
	 */
	public static boolean compareDate(String s, String e) {
		if(fomatDate(s)==null||fomatDate(e)==null){
			return false;
		}
		return fomatDate(s).getTime() >=fomatDate(e).getTime();
	}

	/**
	 * 格式化日期
	 * 
	 * @return
	 */
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 校验日期是否合法
	 * 
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}
	public static int getDiffYear(String startTime,String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			long aa=0;
			int years=(int) (((fmt.parse(endTime).getTime()-fmt.parse(startTime).getTime())/ (1000 * 60 * 60 * 24))/365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}
	  /**
     * <li>功能描述：时间相减得到天数
     * @param beginDateStr
     * @param endDateStr
     * @return
     * long 
     * @author 申晓琛
     */
    public static long getDaySub(String beginDateStr,String endDateStr){
        long day=0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Date beginDate = null;
        java.util.Date endDate = null;

            try {
				beginDate = format.parse(beginDateStr);
				endDate= format.parse(endDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
            //System.out.println("相隔的天数="+day);

        return day;
    }

    /**
     * 得到n天之后的日期
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
    	int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);

        return dateStr;
    }

    /**
     * 得到n天之后是周几
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
    	int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);

        return dateStr;
    }

    /**
     *字符串的日期格式的计算
     *俩日期相差时间
     */
    public static int daysBetween(String smdate,String bdate) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 将数组转化为字符串，用“，”分隔
     * @param result
     * @return
     */
    public static String format(int[] result)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("");
        for (int i = 0; i < result.length - 1; i++)
            sb.append(result[i] + ",");
        sb.append(result[result.length - 1] + "");
        return sb.toString();
    }
    /**
     * 将字符串数组，转换成数字数组
     * @param strings
     * @return
     */
    public static int[] stringTint(String[] strings){
        int[] in = new int[]{Integer.parseInt(strings[0]),Integer.parseInt(strings[1]),Integer.parseInt(strings[2])};
        return in;
    }
    /**
     * 参数from（格式为19990101）
     * 参数to（格式为19990101）
     * 返回值int[2]
     *       int[0]:相差多少年
     *       int[1]:一年内相差多少月
     *       int[2]:一年内相差多少天
     * @param from
     * @param to
     * @return
     */
    public static int[] getDateLength(String from, String to) {
        java.text.DateFormat df = new java.text.SimpleDateFormat("yyyyMMdd");
        java.util.Calendar c1 = java.util.Calendar.getInstance();
        java.util.Calendar c2 = java.util.Calendar.getInstance();
        try
        {
            c1.setTime(df.parse(from));
            c2.setTime(df.parse(to));
        } catch (java.text.ParseException e)
        {
            // XXX Auto-generated catch block
            System.err.println("日期格式不正确，应该是yyyyMMdd");
            e.printStackTrace();
        }
        int[] result = new int[3];
        result[0] = (c2.get(java.util.Calendar.YEAR) - c1.get(java.util.Calendar.YEAR)) < 0 ? 0 : c2
                .get(java.util.Calendar.YEAR)
                - c1.get(java.util.Calendar.YEAR);
        result[1] = (c2.get(java.util.Calendar.MONTH) - c1.get(java.util.Calendar.MONTH)) < 0 ? 0 : c2
                .get(java.util.Calendar.MONTH)
                - c1.get(java.util.Calendar.MONTH);
        result[2] = (c2.get(java.util.Calendar.DAY_OF_MONTH) - c1.get(java.util.Calendar.DAY_OF_MONTH)) < 0 ? 0 : c2
                .get(java.util.Calendar.DAY_OF_MONTH)
                - c1.get(java.util.Calendar.DAY_OF_MONTH);
        return result;
    }
    /**
     * 获取当前时间，并且拼装格式为20180112
     * @return
     */
    public static String dateForTrue(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String  ad = sdf.format(new Date());
        ad = ad.substring(0,4)+ad.substring(5,7)+ad.substring(8,10);
        return ad;
    }
    /**
     * 测试方法
     * @param args
     */
    public static void main(String[] args) {

    	format(getDateLength(dateForTrue(),"20170901"));
    	try {
    		System.err.println(dateForTrue());
    		System.err.println(getDateLength(dateForTrue(),"20170901")[0]);
    		System.err.println(getDateLength(dateForTrue(),"20170901")[1]);
    		System.err.println(format(getDateLength(dateForTrue(),"20170901")));
    		System.err.println(daysBetween("2017-09-01","2018-01-17"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
	}
}
