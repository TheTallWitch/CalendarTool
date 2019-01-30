package com.spournasseh.calendartool;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.chrono.GregorianChronology;
import org.joda.time.chrono.IslamicChronology;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class CalendarTool {
    private List<String> persianMonths = Arrays.asList( "فروردین", "اردیبهشت", "خرداد", "تیر", "مرداد", "شهریور", "مهر", "آبان", "آذر", "دی", "بهمن", "اسفند" );
    private List<String> gregorianMonths = Arrays.asList( "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" );
    private List<String> gregorianFarsiMonths = Arrays.asList( "ژانویه", "فوریه", "مارس", "آوریل", "مه", "ژوئن", "ژوئیه", "اوت", "سپتامبر", "اکتبر", "نوامبر", "دسامبر" );
    private List<String> lunarMonths = Arrays.asList( "محرم", "صفر", "ربیع‌الاول", "ربیع‌الثانی", "جمادی‌الاول", "جمادی‌الثانی", "رجب", "شعبان", "رمضان", "شوال", "ذی‌القعده", "ذی‌الحجه" );
    private List<String> persianWeek = Arrays.asList( "دوشنبه", "سه شنبه", "چهارشنبه", "پنج شنبه", "جمعه", "شنبه", "یک شنبه" );
    private List<String> gregorianWeek = Arrays.asList( "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" );
    private List<String> lunarWeek = Arrays.asList( "الاثنين", "الثلاثاء", "الأربعاء", "الخميس", "الجمعة", "السبت", "الأحد" );

    public boolean isPersianLeapYear(int year) {
        int a = 0, b = 1309, c = year;
        for (int i = 1309; i <= c - 4; i += 4)
        {
            b += 4;
            a += 1;
            if (a % 8 == 0)
                b++;
        }
        if (c == b)
            return true;
        else
            return false;
    }
    public boolean isGregorianLeapYear(int year) {
        int d4 = year % 4;
        int d100 = year % 100;
        int d400 = year % 400;
        if (d4 == 0) {
            if (d100 == 0) {
                if(d400 == 0) {
                    return true;
                }
                else {
                    return false;
                }
            }
            else {
                return true;
            }
        }
        else {
            return false;
        }
    }
    public boolean isLunarLeapYear(int year) {
        int d30 = year % 30;
        if (d30 == 2 || d30 == 5 || d30 == 7 || d30 == 10 || d30 == 13 || d30 == 16 || d30 == 18 || d30 == 21 || d30 == 24 || d30 == 26 || d30 == 29) {
            return true;
        }
        else return false;
    }
    public String getJalaliWeekDay() {
        return persianWeek.get(getDayOfWeek());
    }
    public String getJalaliStringShorter() {

        return String.valueOf(irDay) + " " + persianMonths.get(irMonth - 1);
    }
    public String getJalaliStringShort() {

        return String.valueOf(irDay) + " " + persianMonths.get(irMonth - 1) + " " + irYear;
    }
    public String getGregorianStringShort() {

        return String.valueOf(gDay) + " " + gregorianMonths.get(gMonth - 1) + " " + gYear;
    }
    public String getLunarStringShort() {

        return String.valueOf(luDay) + " " + lunarMonths.get(luMonth - 1) + " " + luYear;
    }
    public String getJalaliStringLong() {

        return persianWeek.get(getDayOfWeek()) + " " + String.valueOf(irDay) + " " + persianMonths.get(irMonth - 1) + " " + irYear;
    }
    public String getGregorianStringLong() {

        return gregorianWeek.get(getDayOfWeek()) + " " + String.valueOf(gDay) + " " + gregorianMonths.get(gMonth - 1) + " " + gYear;
    }
    public String getLunarStringLong() {

        return lunarWeek.get(getDayOfWeek()) + " " + String.valueOf(luDay) + " " + lunarMonths.get(luMonth - 1) + " " + luYear;
    }
    public String getGregorianStringFarsiLong() {

        return persianWeek.get(getDayOfWeek()) + " " + String.valueOf(gDay) + " " + gregorianFarsiMonths.get(gMonth - 1) + " " + gYear;
    }
    public String getGregorianStringFarsiShort() {

        return String.valueOf(gDay) + " " + gregorianFarsiMonths.get(gMonth - 1) + " " + gYear;
    }

    public String dateDifference(String d1, String d2) {
        String temp;
        int yearIndex = 0, monthIndex = 0;
        String hasYear = "", hasMonth = "", hasDay = "";
        int daysDiff = 0;
        int year1, year2, month1, month2, day1, day2;

        String[] dateParts1 = d1.split("/");
        String[] dateParts2 = d2.split("/");

        setIranianDate(Integer.parseInt(dateParts1[0]), Integer.parseInt(dateParts1[1]), Integer.parseInt(dateParts1[2]));
        Calendar cal1 = Calendar.getInstance();
        cal1.set(getGregorianYear(), getGregorianMonth() - 1, getGregorianDay());
        year1 = getIranianYear();
        month1 = getIranianMonth();
        day1 = getIranianDay();

        setIranianDate(Integer.parseInt(dateParts2[0]), Integer.parseInt(dateParts2[1]), Integer.parseInt(dateParts2[2]));
        Calendar cal2 = Calendar.getInstance();
        cal2.set(getGregorianYear(), getGregorianMonth() - 1, getGregorianDay());
        year2 = getIranianYear();
        month2 = getIranianMonth();
        day2 = getIranianDay();

        if(cal2.getTime().after(cal1.getTime())) {
            temp = "- ";
            yearIndex = year2 - year1;
            monthIndex = month2 - month1;
            daysDiff = day2 - day1;
            if (daysDiff < 0) {
                daysDiff = 30 + daysDiff;
                monthIndex--;
            }
            if (monthIndex < 0) {
                monthIndex = 12 + monthIndex;
                yearIndex--;
            }
        }
        else {
            temp = "";
            yearIndex = year1 - year2;
            monthIndex = month1 - month2;
            daysDiff = day1 - day2;
            if (daysDiff < 0) {
                daysDiff = 30 + daysDiff;
                monthIndex--;
            }
            if (monthIndex < 0) {
                monthIndex = 12 + monthIndex;
                yearIndex--;
            }
        }
        if (yearIndex > 0) {
            hasYear = yearIndex + " سال ";
        }
        if (monthIndex > 0) {
            hasMonth = monthIndex + " ماه ";
        }
        if (daysDiff > 0) {
            hasDay = daysDiff + " روز ";
        }
        String output = hasYear + hasMonth + hasDay;
        if (!output.equals("")) {
            output = temp + output;
        }
        else {
            output = "بدون اختلاف";
        }
        return output;
    }
    public int dateDifferenceInDays(String d1, String d2) {
        String temp;
        boolean before = true;
        int yearIndex = 0, monthIndex = 0;
        int daysDiff = 0;
        int year1, year2, month1, month2, day1, day2;

        String[] dateParts1 = d1.split("/");
        String[] dateParts2 = d2.split("/");

        setIranianDate(Integer.parseInt(dateParts1[0]), Integer.parseInt(dateParts1[1]), Integer.parseInt(dateParts1[2]));
        Calendar cal1 = Calendar.getInstance();
        cal1.set(getGregorianYear(), getGregorianMonth() - 1, getGregorianDay());
        year1 = getIranianYear();
        month1 = getIranianMonth();
        day1 = getIranianDay();

        setIranianDate(Integer.parseInt(dateParts2[0]), Integer.parseInt(dateParts2[1]), Integer.parseInt(dateParts2[2]));
        Calendar cal2 = Calendar.getInstance();
        cal2.set(getGregorianYear(), getGregorianMonth() - 1, getGregorianDay());
        year2 = getIranianYear();
        month2 = getIranianMonth();
        day2 = getIranianDay();

        if(cal2.getTime().after(cal1.getTime())) {
            temp = "قبل";
            before = true;
            yearIndex = year2 - year1;
            monthIndex = month2 - month1;
            daysDiff = day2 - day1;
            if (daysDiff < 0) {
                daysDiff = 30 + daysDiff;
                monthIndex--;
            }
            if (monthIndex < 0) {
                monthIndex = 12 + monthIndex;
                yearIndex--;
            }
        }
        else {
            temp = "بعد";
            before = false;
            yearIndex = year1 - year2;
            monthIndex = month1 - month2;
            daysDiff = day1 - day2;
            if (daysDiff < 0) {
                daysDiff = 30 + daysDiff;
                monthIndex--;
            }
            if (monthIndex < 0) {
                monthIndex = 12 + monthIndex;
                yearIndex--;
            }
        }
        if (yearIndex > 0) {
            daysDiff += yearIndex * 365;
        }
        if (monthIndex > 0) {
            daysDiff += monthIndex * 30;
        }
        if (before) {
            daysDiff *= -1;
        }
        return daysDiff;
    }

    public CalendarTool()
    {
        Calendar calendar = new GregorianCalendar();
        setGregorianDate(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH)+1,
                calendar.get(Calendar.DAY_OF_MONTH));
    }

    public CalendarTool(int year, int month, int day)
    {
        setGregorianDate(year,month,day);
    }

    public int getIranianYear() {
        return irYear;
    }

    public int getIranianMonth() {
        return irMonth;
    }

    public int getIranianDay() {
        return irDay;
    }

    public int getGregorianYear() {
        return gYear;
    }

    public int getGregorianMonth() {
        return gMonth;
    }

    public int getGregorianDay() {
        return gDay;
    }
    public int getLunarYear() { return luYear; }
    public int getLunarMonth() { return luMonth; }
    public int getLunarDay() { return luDay; }

    public int getJulianYear() {
        return juYear;
    }

    public int getJulianMonth() {
        return juMonth;
    }

    public int getJulianDay() {
        return juDay;
    }

    public String getIranianDate()
    {
        return (irYear+"/"+irMonth+"/"+irDay);
    }

    public String getGregorianDate()
    {
        return (gYear+"/"+gMonth+"/"+gDay);
    }

    public String getLunarDate()
    {
        return (luYear+"/"+luMonth+"/"+luDay);
    }

    public String getJulianDate()
    {
        return (juYear+"/"+juMonth+"/"+juDay);
    }

    public String getWeekDayStr()
    {
        String weekDayStr[]={
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday",
                "Saturday",
                "Sunday"};
        return (weekDayStr[getDayOfWeek()]);
    }

    public String toString()
    {
        return (getWeekDayStr()+
                ", Gregorian:["+getGregorianDate()+
                "], Julian:["+getJulianDate()+
                "], Iranian:["+getIranianDate()+
                "], Lunar:["+getLunarDate()+"]");
    }

    public int getDayOfWeek()
    {
        return (JDN % 7);
    }

    public int getPersianDayOfWeek() {
        int w = getDayOfWeek() + 2;
        if (w >= 7) { w -= 7; }
        return w;
    }

    public void nextDay()
    {
        JDN++;
        JDNToIranian();
        JDNToJulian();
        JDNToGregorian();
        JDNToLunar();
    }

    public void nextDay(int days)
    {
        JDN+=days;
        JDNToIranian();
        JDNToJulian();
        JDNToGregorian();
        JDNToLunar();
    }

    public void previousDay()
    {
        JDN--;
        JDNToIranian();
        JDNToJulian();
        JDNToGregorian();
        JDNToLunar();
    }

    public void previousDay(int days)
    {
        JDN-=days;
        JDNToIranian();
        JDNToJulian();
        JDNToGregorian();
        JDNToLunar();
    }

    public void setIranianDate(int year, int month, int day)
    {
        irYear =year;
        irMonth = month;
        irDay = day;
        JDN = IranianDateToJDN();
        JDNToIranian();
        JDNToJulian();
        JDNToGregorian();
        JDNToLunar();
    }

    public void setGregorianDate(int year, int month, int day)
    {
        gYear = year;
        gMonth = month;
        gDay = day;
        JDN = gregorianDateToJDN(year,month,day);
        JDNToIranian();
        JDNToJulian();
        JDNToGregorian();
        JDNToLunar();
    }
    public void setLunarDate(int year, int month, int day)
    {
        luYear = year;
        luMonth = month;
        luDay = day;
        LDNToGregorian();
        LDNToIranian();
    }

    public void setJulianDate(int year, int month, int day)
    {
        juYear = year;
        juMonth = month;
        juDay = day;
        JDN = julianDateToJDN(year,month,day);
        JDNToIranian();
        JDNToJulian();
        JDNToGregorian();
        JDNToLunar();
    }

    private void IranianCalendar()
    {
        // Iranian years starting the 33-year rule
        int Breaks[]=
                {-61, 9, 38, 199, 426, 686, 756, 818,1111,1181,
                        1210,1635,2060,2097,2192,2262,2324,2394,2456,3178} ;
        int jm,N,leapJ,leapG,jp,j,jump;
        gYear = irYear + 621;
        leapJ = -14;
        jp = Breaks[0];
        // Find the limiting years for the Iranian year 'irYear'
        j=1;
        do{
            jm=Breaks[j];
            jump = jm-jp;
            if (irYear >= jm)
            {
                leapJ += (jump / 33 * 8 + (jump % 33) / 4);
                jp = jm;
            }
            j++;
        } while ((j<20) && (irYear >= jm));
        N = irYear - jp;
        // Find the number of leap years from AD 621 to the begining of the current
        // Iranian year in the Iranian (Jalali) calendar_month
        leapJ += (N/33 * 8 + ((N % 33) +3)/4);
        if ( ((jump % 33) == 4 ) && ((jump-N)==4))
            leapJ++;
        // And the same in the Gregorian date of Farvardin the first
        leapG = gYear/4 - ((gYear /100 + 1) * 3 / 4) - 150;
        march = 20 + leapJ - leapG;
        // Find how many years have passed since the last leap year
        if ( (jump - N) < 6 )
            N = N - jump + ((jump + 4)/33 * 33);
        leap = (((N+1) % 33)-1) % 4;
        if (leap == -1)
            leap = 4;
    }

    public boolean IsLeap(int irYear1) {
        // Iranian years starting the 33-year rule
        int Breaks[]= {-61, 9, 38, 199, 426, 686, 756, 818,1111,1181,1210,1635,2060,2097,2192,2262,2324,2394,2456,3178} ;
        int jm,N,leapJ,leapG,jp,j,jump;
        gYear = irYear1 + 621;
        leapJ = -14;
        jp = Breaks[0];
        // Find the limiting years for the Iranian year 'irYear'
        j=1;
        do {
            jm=Breaks[j];
            jump = jm-jp;
            if (irYear1 >= jm)
            {
                leapJ += (jump / 33 * 8 + (jump % 33) / 4);
                jp = jm;
            }
            j++;
        } while ((j<20) && (irYear1 >= jm));
        N = irYear1 - jp;
        // Find the number of leap years from AD 621 to the begining of the current
        // Iranian year in the Iranian (Jalali) calendar_month
        leapJ += (N/33 * 8 + ((N % 33) +3)/4);
        if ( ((jump % 33) == 4 ) && ((jump-N)==4))
            leapJ++;
        // And the same in the Gregorian date of Farvardin the first
        leapG = gYear/4 - ((gYear /100 + 1) * 3 / 4) - 150;
        march = 20 + leapJ - leapG;
        // Find how many years have passed since the last leap year
        if ( (jump - N) < 6 )
            N = N - jump + ((jump + 4)/33 * 33);
        leap = (((N+1) % 33)-1) % 4;
        if (leap == -1)
            leap = 4;
        if (leap==4 || leap==0)
            return true;
        else
            return false;

    }

    private int IranianDateToJDN() {
        IranianCalendar();
        return (gregorianDateToJDN(gYear,3,march)+ (irMonth-1) * 31 - irMonth/7 * (irMonth-7) + irDay -1);
    }

    private void JDNToIranian() {
        JDNToGregorian();
        irYear = gYear - 621;
        IranianCalendar(); // This invocation will update 'leap' and 'march'
        int JDN1F = gregorianDateToJDN(gYear,3,march);
        int k = JDN - JDN1F;
        if (k >= 0)
        {
            if (k <= 185)
            {
                irMonth = 1 + k/31;
                irDay = (k % 31) + 1;
                return;
            }
            else
                k -= 186;
        }
        else
        {
            irYear--;
            k += 179;
            if (leap == 1)
                k++;
        }
        irMonth = 7 + k/30;
        irDay = (k % 30) + 1;
    }

    private int julianDateToJDN(int year, int month, int day) {
        return (year + (month - 8) / 6 + 100100) * 1461/4 + (153 * ((month+9) % 12) + 2)/5 + day - 34840408;
    }

    private void JDNToJulian() {
        int j= 4 * JDN + 139361631;
        int i= ((j % 1461)/4) * 5 + 308;
        juDay = (i % 153) / 5 + 1;
        juMonth = ((i/153) % 12) + 1;
        juYear = j/1461 - 100100 + (8-juMonth)/6;
    }

    private int gregorianDateToJDN(int year, int month, int day) {
        int jdn = (year + (month - 8) / 6 + 100100) * 1461/4 + (153 * ((month+9) % 12) + 2)/5 + day - 34840408;
        jdn = jdn - (year + 100100+(month-8)/6)/100*3/4+752;
        return (jdn);
    }

    private void JDNToGregorian() {
        int j= 4 * JDN + 139361631;
        j = j + (((((4* JDN +183187720)/146097)*3)/4)*4-3908);
        int i= ((j % 1461)/4) * 5 + 308;
        gDay = (i % 153) / 5 + 1;
        gMonth = ((i/153) % 12) + 1;
        gYear = j/1461 - 100100 + (8-gMonth)/6;
    }
    private void JDNToLunar() {
        Chronology gregorian = GregorianChronology.getInstanceUTC();
        DateTime date = new DateTime(gYear, gMonth, gDay, 10, 0, gregorian);
        DateTime lunarDate = date.withChronology(IslamicChronology.getInstance());
        luYear = lunarDate.getYear();
        luMonth = lunarDate.getMonthOfYear();
        luDay = lunarDate.getDayOfMonth();
    }
    private void LDNToGregorian() {
        Chronology hijri = IslamicChronology.getInstanceUTC();
        try {
            DateTime date = new DateTime(luYear, luMonth, luDay, 10, 0, hijri);
            DateTime gregorianDate = date.withChronology(GregorianChronology.getInstance());
            gYear = gregorianDate.getYear();
            gMonth = gregorianDate.getMonthOfYear();
            gDay = gregorianDate.getDayOfMonth();
            setGregorianDate(gYear, gMonth, gDay);
        }
        catch (IllegalFieldValueException e) {
            e.printStackTrace();
        }
    }
    private void LDNToIranian() {
        irYear = getIranianYear();
        irMonth = getIranianMonth();
        irDay = getIranianDay();
    }

    private int irYear;
    private int irMonth;
    private int irDay;
    private int gYear;
    private int gMonth;
    private int gDay;
    private int juYear;
    private int juMonth;
    private int juDay;
    private int leap;
    private int JDN;
    private int march;
    private int luYear;
    private int luMonth;
    private int luDay;
}
