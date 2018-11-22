package com.isoftstone.pmit.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author MeasurePlatform
 * 公共方法类
 */
public class Utils {

    public static final Logger logger = LoggerFactory.getLogger(Utils.class);


    /**
     * 解析字符串公式
     * @param formula
     * @return
     */
    public static String analysisFormula(String formula) {
        String result = "";
        DecimalFormat df = new DecimalFormat("#.00");
        ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
        try {
            result = df.format(jse.eval(formula));
        } catch (Exception e) {
            return "";
        }
        return result.equals("∞") ? "" : result;
    }

    /**
     * 校验 String，Map，List，Set
     * String == ""、null|| Map = null、size()==0 " || List = null、size()==0 || Set = null、size()==0 return true
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object... obj) {
        List<Boolean> result = new ArrayList<>();
        for (int i = 0; i < obj.length; i++) {
            boolean flag = false;
            Object param = obj[i];
            if (null == param) {
                flag = true;
            } else if (param instanceof String) {
                flag = ((String) param).equals("");
            } else if (param instanceof List) {
                flag = ((List) param).size() == 0;
            } else if (param instanceof Map) {
                flag = ((Map) param).size() == 0;
            } else if (param instanceof Set) {
                flag = ((Set) param).size() == 0;
            } else if (param.getClass().isArray()) {
                flag = Array.getLength(param) == 0;
            }
            result.add(flag);
        }
        return result.contains(true);
    }


    /**
     * 校验String类型可以转为float
     * @param value
     * @return
     */
    public static boolean parseFloat(String value) {
        try {
            Float.parseFloat(value);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }



    /**
     * 拼接get方法
     * @param fieldName
     * @return String
     */
    public static String parGetName(String fieldName) {
        if (null == fieldName || "".equals(fieldName)) {
            return null;
        }
        int startIndex = 0;
        if (fieldName.charAt(0) == '_'){
            startIndex = 1;
        }
        return "get"
                + fieldName.substring(startIndex, startIndex + 1).toUpperCase()
                + fieldName.substring(startIndex + 1);
    }

    /**
     * 拼接 set方法
     * @param fieldName
     * @return String
     */
    public static String parSetName(String fieldName) {
        if (null == fieldName || "".equals(fieldName)) {
            return null;
        }
        int startIndex = 0;
        if (fieldName.charAt(0) == '_'){
            startIndex = 1;
        }
        return "set"
                + fieldName.substring(startIndex, startIndex + 1).toUpperCase()
                + fieldName.substring(startIndex + 1);
    }


    /**
     * 校验value值是不是数字
     * @param value
     * @return
     */
    public static boolean isNumber(Object value) {
        try {
            Float.valueOf(value.toString());
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    /**
     * java 处理 小数点后保留两位有效数字
     * @param  b double类型
     * @return double 类型
     *
     */
    public static double doubleFormat(double b){
        DecimalFormat df=new DecimalFormat("0.00");
        return Double.parseDouble(df.format(b));
    }

    /**
     * java 处理 小数点后保留两位有效数字
     * @param  str String 类型
     * @return String 类型
     *
     */
    public static String getStringFormat(String str){
        if(null != str && (!str.equals(""))){
            double d = Double.parseDouble(str);
            DecimalFormat df=new DecimalFormat("0.00");
            return df.format(d);
        }else {
            return str;
        }

    }






    /**
     * List<Map<String, Object>> 去重
     * @param oldParam 需要去重的List
     * @param str 按照该字段去除重复
     * @return 去重过的List
     */
    public static List<Map<String, String>> deleteListRepeat(List<Map<String, String>> oldParam, String str) {

        List<Map<String, String>> newList = new ArrayList<>();
        for (Map<String, String> oldMap : oldParam) {
            if (newList.size() > 0) {
                boolean isContain = false;
                for (Map<String, String> newMap : newList) {
                    if (newMap.get(str).equals(oldMap.get(str))) {
                        for (String key : oldMap.keySet()) {
                            newMap.put(key, oldMap.get(key));
                        }
                        isContain = true;
                        break;
                    }
                }
                if (!isContain) {
                    newList.add(oldMap);
                }
            } else {
                newList.add(oldMap);
            }
        }
        return newList;
    }

    /**
     * 判断字符串是否为数字 9.26
     * 字符串为空时,返回false
     * 数字：ASCII 码 (0:48 -- 9：57)
     * @param str
     * @return
     */
    public static boolean isNumeric(String str)
    {
        if(null != str && (!str.equals(""))){
            for(int i=str.length();--i>=0;){
                int chr=str.charAt(i);
                if(chr<48 || chr>57){
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }

    }

    public static int getDutyDays(String strStartDate,String strEndDate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate=null;
        Date endDate = null;
        try {
            startDate=df.parse(strStartDate);
            endDate = df.parse(strEndDate);
        } catch (ParseException e) {
            System.out.println("非法的日期格式,无法进行转换");
            e.printStackTrace();
        }
        int result = 0;
        while (startDate.compareTo(endDate) <= 0) {
            if (startDate.getDay() != 6 && startDate.getDay() != 0){
                result++;
            }
            startDate.setDate(startDate.getDate() + 1);
        }
        // 此处 -1 不包含结束日期的那一天
        return result-1;
    }
}
