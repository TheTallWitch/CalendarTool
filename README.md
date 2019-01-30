# CalendarTool
A Java library to convert Gregorian, Solar and Lunar dates.

# Installing
Step 1. Add the JitPack repository to your build file
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Step 2. Add the dependency
```
dependencies {
	        implementation 'com.github.spournasseh:CalendarTool:0.2.0'
	}
```

# Usage
1. Initializing the calendar tool:
```
CalendarTool tool = new CalendarTool(); //sets current date
CalendarTool tool = new CalendarTool(2019, 1, 30); //sets current date with Gregorian ones
```

2. Working with Solar Dates (Hijri Shamsi)
```
tool.setIranianDate(int year, int month, int day);

tool.getIranianDate(); //returns string as: "1397/10/23"
tool.getIranianYear(); //returns integer as: 1397
tool.getIranianMonth(); //returns integer as: 10
tool.getIranianDay(); //returns integer as: 23
tool.getIranianWeekDay(); //returns string as : "دوشنبه"
tool.getIranianStringShorter(); //returns string as "23 دی"
tool.getIranianStringShort(); //returns string as "23 دی 1397"
tool.getIranianStringLong();//returns string as "دوشنبه 23 دی 1397"
tool.dateDifference(String date1, String date2); //returns string as: "۱ سال ۲ ماه ۱۲ روز" and "بدون اختلاف" if the same. passed dates should be like "1397/10/23"
tool.dateDifferenceInDays(String date1, String date2); //returns integer as: 125
tool.isPersianLeapYear(int year); //returns boolean
```

2. Working with Gregorian Dates (Miladi)
```
tool.setGregorianDate(int year, int month, int day);

tool.getGregorianDate(); //returns string as: "2019/1/30"
tool.getGregorianYear(); //returns integer as: 2019
tool.getGregorianMonth(); //returns integer as: 1
tool.getGregorianDay(); //returns integer as: 30
tool.getGregorianWeekDay(); //returns string as : "Monday"
tool.getGregorianStringShort(); //returns string as "30 January 2019"
tool.getGregorianStringLong();//returns string as "Monday 30 January 2019"
tool.getGregorianStringFarsiShort(); //returns string as "30 ژانویه 2019"
tool.getGregorianStringFarsiLong();//returns string as "دوشنبه 30 ژانویه 2019"
tool.isGregorianLeapYear(int year); //returns boolean
```

2. Working with Lunar Dates (Hijri Ghamari)
```
tool.setLunarDate(int year, int month, int day);

tool.getLunarDate(); //returns string as: "1440/5/23"
tool.getLunarYear(); //returns integer as: 1440
tool.getLunarMonth(); //returns integer as: 5
tool.getLunarDay(); //returns integer as: 23
tool.getLunarWeekDay(); //returns string as : "الاثنین"
tool.getLunarStringShort(); //returns string as "23 جمادی الاول 1440"
tool.getLunarStringLong();//returns string as "الاثنین 23 جمادی الاول 1440"
tool.isLunarLeapYear(int year); //returns boolean
```

3. Other Methods
```
tool.nextDay(); //method to move the object to the next day, handling month or year changes
tool.nextDay(int days); //method to move the object to the next mentioned days, handling month or year changes
tool.previousDay(); //method to move the object to the previous day, handling month or year changes
tool.previousDay(int days); //method to move the object to the previous mentioned days, handling month or year changes
```

# License
This project is licensed under the Apache 2.0 License - see the LICENSE.md file for details

# Acknowledgments
This project uses the [Joda Time Library](https://github.com/JodaOrg/joda-time) to convert Lunar dates
