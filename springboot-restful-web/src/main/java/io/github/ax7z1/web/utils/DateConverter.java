package io.github.ax7z1.web.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateConverter implements Converter<String, Date> {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date convert(String time) {
        try {
            return sdf.parse(time);  //这里记得加return ！
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // return null; 允许为空的话，可以直接返回null
        throw new IllegalArgumentException(); // 类型转换失败 抛400
    }
}
