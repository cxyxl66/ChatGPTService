package com.cxyxl.util;

/**
 * @author cxyxl
 * @date 2023/6/20
 */
public class UUID {
    public static String getUUID() {
        return java.util.UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
    public static String getUUID10(){
        return java.util.UUID.randomUUID().toString().replace("-", "").substring(0,10);
    }
    public static String getUUID32(){
        return java.util.UUID.randomUUID().toString().replace("-", "").substring(0,32);
    }
    public static String getUUID4(){
        return java.util.UUID.randomUUID().toString().replace("-", "").toLowerCase().substring(0,4);
    }
}
