package com.example.craw.test;
/**
 * @description 
 * @author xiaobo
 * @date 2023/12/4 10:21
 */
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
        List<String> dateStrings = new ArrayList<>();
        dateStrings.add("(FY)2013-12-31");
        dateStrings.add("(Q6)2011-06-30");
        dateStrings.add("(Q6)2013-06-30");
        dateStrings.add("(Q6)2010-06-30");
        dateStrings.add("(FY)2012-12-31");
        dateStrings.add("(FY)2011-12-31");
        dateStrings.add("(FY)2010-12-31");

        Comparator<String> comparator2 = Comparator.comparing(s-> StringUtils.substringAfter(s, ")"), Comparator.reverseOrder());
        dateStrings = dateStrings.stream().sorted(comparator2).collect(Collectors.toList());

        // 打印排序后的日期字符串
        for (String dateString : dateStrings) {
            System.out.println(dateString);
        }
    }


}

