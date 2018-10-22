package com.juyao.jmvp.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author juyao
 * @date 2017/12/24
 * @email juyao0909@gmail.com
 */
public class DateUtilsTest {
    @Test
    public void dataIsBefore() throws Exception {
        assertEquals(DateUtils.dateIsBefore("2017-12-26","2017-12-25"),true);

    }
}