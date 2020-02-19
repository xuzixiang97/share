package com.xuzi.share.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Random;


/**
 * CommonUtil
 * create by chenshihang on 2018/12/30
 */
public class BeanUtil {






    /**
     * 初始化类数据
     * @param cls
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T initClassInfo(Class<T> cls) {
        T bean=null;
        try{
            bean = cls.newInstance();
            Method[] methods = cls.getDeclaredMethods();
            Field[] fields = cls.getDeclaredFields();

            for(Field field:fields){
                String fieldSetName = parSetName(field.getName());
                if (!checkSetMet(methods, fieldSetName)) {
                    continue;
                }
                Method fieldSetMet = cls.getMethod(fieldSetName,
                        field.getType());

                fieldSet(bean,fieldSetMet,field);
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("initClassInfo调用异常");
        }
        return bean;
    }






    /**
     * 生成随机数据
     * @param type
     * @return
     */
    private static Object getRandomInfo(String type) {

        try{
            if(type.equals("String")){
                return "text";
            } else if(type.equals("Integer")){
                return 0;
            }else if(type.equals("int")){
                return 0;
            }
            else if(type.equals("Long")){
                return (long)(Math.random()*100000);
            }
        }catch (Exception e){
            System.out.println("未找到匹配类型,初始化数据失败"+type);
        }
        return "haha";


    }

    /**
     * 拼接在某属性的 set方法
     *
     * @param fieldName
     * @return String
     */
    private static String parSetName(String fieldName) {
        if (null == fieldName || "".equals(fieldName)) {
            return null;
        }
        int startIndex = 0;
        if (fieldName.charAt(0) == '_')
            startIndex = 1;
        return "set"
                + fieldName.substring(startIndex, startIndex + 1).toUpperCase()
                + fieldName.substring(startIndex + 1);
    }

    /**
     * 判断是否存在某属性的 set方法
     *
     * @param methods
     * @param fieldSetMet
     * @return boolean
     */
    private static boolean checkSetMet(Method[] methods, String fieldSetMet) {
        for (Method met : methods) {
            if (fieldSetMet.equals(met.getName())) {
//                System.out.println(fieldSetMet+" true");
                return true;
            }
        }
//        System.out.println(fieldSetMet+" false");
        return false;
    }




    /**
     * 调用某个set方法set数据
     * @param bean
     * @param fieldSetMet
     * @param field
     * @throws Exception
     */
    private static void fieldSet(Object bean,Method fieldSetMet,Field field) throws Exception{


        String fieldType = field.getType().getSimpleName();
        Object value = getRandomInfo(fieldType);
        if ("String".equals(fieldType)) {
            fieldSetMet.invoke(bean, (String)value);
        } else if ("Date".equals(fieldType)) {
            Date temp = (Date)value;
            fieldSetMet.invoke(bean, temp);
        } else if ("Integer".equals(fieldType)
                || "int".equals(fieldType)) {
            Integer intval = (Integer)value;
            fieldSetMet.invoke(bean, intval);
        } else if ("Long".equalsIgnoreCase(fieldType)) {
            Long temp = (Long)value;
            fieldSetMet.invoke(bean, temp);
        } else if ("Double".equalsIgnoreCase(fieldType)) {
            Double temp = (Double)value;
            fieldSetMet.invoke(bean, temp);
        } else if ("Boolean".equalsIgnoreCase(fieldType)) {
            Boolean temp =(Boolean)value;
            fieldSetMet.invoke(bean, temp);
        } else {
            System.out.println("未找到匹配类型" + fieldType);
        }
    }




}