package com.juyao.jmvp.utils;

import java.util.regex.Pattern;

/**
 * @author juyao
 * @date 2018/2/12
 * @email juyao0909@gmail.com
 */

public class AppValidationMgr {
    /**
     *  手机号表达式
     */
    private final static Pattern phone_pattern = Pattern.compile("^(13|15|18|17)\\d{9}$");
    /**
     * 验证手机号是否正确
     * @param phone 手机号码
     * @return boolean
     */
    public static boolean isPhone(String phone) {
        return phone_pattern.matcher(phone).matches();
    }
}
