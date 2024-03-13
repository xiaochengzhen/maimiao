package com.example.javabase.extendsTest;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

/**
 * @description 
 * @author xiaobo
 * @date 2024/3/12 13:48
 */
@Data
public class Body extends Parent{
    private String ext;


    public static void main(String[] args) {
        Parent parent = new Parent("adg");

        Body body = new Body();
        String s = JSONObject.toJSONString(parent);
        System.out.println(s);

        BeanUtils.copyProperties(parent, body);
        System.out.println(body.getAge()+body.getName());
    }
}
