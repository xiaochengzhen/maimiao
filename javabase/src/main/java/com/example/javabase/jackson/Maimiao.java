package com.example.javabase.jackson;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

/**
 * @author xiaobo
 * @description
 * @date 2023/3/8 08:19
 */
@Data
public class Maimiao {


    private Integer age;
    @JsonDeserialize(using = MySerial.class)
    private String name;

}
