package com.xyxy.coversafetyapp.utils;

/**
 * @Author: PengHaiChen
 * @Description:
 * @Date: Create in 09:27 2021/9/10
 */
public class CodeUtils {
    public static double[] isLongLat(String data) {
        boolean error = true;
        String[] split = data.split("-");
        double v0 = 0;
        double v1 = 0;
        if (split.length == 2) {
            String s0 = split[0];
            String s1 = split[1];
            try {
                v0 = Double.parseDouble(s0);
                v1 = Double.parseDouble(s1);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                error = false;
            }
        } else {
            error = false;
        }
        double[] info = null;
        if (error) {
            info = new double[]{v0, v1};
        }
        return info;
    }
}
