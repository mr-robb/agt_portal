package mx.com.ebs.inter.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by robb on 29/04/2015.
 */
public class DateUtil {

    public static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";
    public static final String YEAR_MONTH_DATE_FORMAT = "yyyy-MM";

    private static final SimpleDateFormat defaultDateFormatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
    private static final SimpleDateFormat yearMonthFormatter = new SimpleDateFormat(YEAR_MONTH_DATE_FORMAT);

    public static String format(final Date dateToFormat ) throws Exception{
        return defaultDateFormatter.format(dateToFormat);
    }

    public static Date parse( final String dateToParse ) throws Exception{
        return defaultDateFormatter.parse(dateToParse);
    }

    public static String formatYearMonth(final Date dateToFormat) throws Exception{
        return yearMonthFormatter.format(dateToFormat);
    }

    public static Date parseYearMonth( final String dateToParse ) throws Exception{
        return yearMonthFormatter.parse(dateToParse);
    }
}