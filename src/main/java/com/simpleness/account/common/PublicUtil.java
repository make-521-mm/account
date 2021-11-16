package com.simpleness.account.common;

import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.util.Map;

/**
 * ---------------------------
 * If this comment is removed the program will blow up
 * ---------------------------
 * 作者：make
 * 时间：2021/6/21 14:27
 * 说明：
 * ---------------------------
 */
public class PublicUtil {

    /**
     * 等待N毫秒
     * @param millis 毫秒数，1000 毫秒为1秒.
     */
    public static void sleepByMillis(long millis) {
        try {
            if (millis > 0) {
                Thread.sleep(millis);
            }
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 将文本格式化为json所要求的格式
     * @param value 带格式化文本
     * @return 符合json的文本
     */
    public static String formatToJson(String value) {
        if (null == value || "".equals(value)) {
            return value;
        } else {
            // 顺序不能乱，一定是最后才替换\
            return value.replace("\"{", "{").replace("}\"", "}").replace("\\", "");
        }
    }

    /**
     * MD5签名转base64
     * @param str
     * @return
     */
    public static String md5EncryptAndBase64(String str) {
        return encodeBase64(md5Encrypt(str));
    }

    /**
     * MD5签名
     * @param encryptStr
     * @return
     */
    public static byte[] md5Encrypt(String encryptStr) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(encryptStr.getBytes("utf8"));
            return md5.digest();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String encodeBase64(byte[] b) {
        @SuppressWarnings("restriction")
        BASE64Encoder base64Encode = new BASE64Encoder();
        @SuppressWarnings("restriction")
        String str = base64Encode.encode(b);
        return str;
    }


    /**
     * @description 对象是否为空判断
     * @author make
     * @date 2021/7/20 11:39
     * @param object
     * @return {@link String}
     **/
    public static String isNullObject(Object object) {
        if (object == null || "".equals(object) || "null".equals(object.toString())) {
            return "";
        }
        return object.toString().trim();
    }

    public static Boolean isNotNull(Object object) {
        if (object == null || "".equals(object)) {
            return false;
        }
        return true;
    }

    public static Boolean isNotNull(Map<String, Object> object) {
        if (object == null || "".equals(object)) {
            return false;
        }
        return true;
    }

    /**
     * @description  对象是否为空判断 为空返回null
     * @author make
     * @date 2021/7/20 11:41
     * @param object
     * @return {@link String}
     **/
    public static String isNullObjectNULL(Object object) {
        if (object == null || "".equals(object)) {
            return null;
        }
        return object.toString().trim();
    }

    /**
     * @description unicode转换为中文
     * @author make
     * @date 2021/7/20 14:49
     * @param unicode
     * @return {@link String}
     **/
    public static String unicodetoString(String unicode) {
        if (unicode == null || "".equals(unicode)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int i = -1;
        int pos = 0;
        while ((i = unicode.indexOf("\\u", pos)) != -1) {
            sb.append(unicode.substring(pos, i));
            if (i + 5 < unicode.length()) {
                pos = i + 6;
                sb.append((char) Integer.parseInt(unicode.substring(i + 2, i + 6), 16));
            }
        }
        return sb.toString();
    }

    /**
     * @description 从xml中找到对应字段的值
     * @author make
     * @date 2021/7/20 15:19
     * @param xmlInfo xml文本
     * @param fieldName 字段名称到值之前的信息 比如"position":"306"需传 "position":"
     * @return {@link String}
     **/
    public static String getXmlField(String xmlInfo, String fieldName) {
        if (!(PublicUtil.isNotNull(xmlInfo) && PublicUtil.isNotNull(fieldName))) {
            return "";
        }
        int postionLocation = xmlInfo.indexOf(fieldName) + fieldName.length();
        return xmlInfo.substring(postionLocation, xmlInfo.indexOf("\"", postionLocation));
    }

    /**
     * 获取e.printStackTrace() 的具体信息，赋值给String 变量，并返回
     * @param e Exception
     * @return e.printStackTrace() 中 的信息
     */
    public static String getStackTraceInfo(Throwable e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            //将出错的栈信息输出到printWriter中
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
            return sw.toString();
        } catch (Exception ex) {
            return "printStackTrace()转换错误";
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
    }
}
