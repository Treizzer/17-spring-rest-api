package com.treizer.spring_boot_rest.util;

import java.lang.reflect.Field;

public class Utils {

    public static boolean isNullClassfields(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field f : fields) {
            f.setAccessible(true);
            // ReflectionUtils.makeAccessible(f);
            try {
                if (f.get(obj) == null) {
                    return true;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }

}
