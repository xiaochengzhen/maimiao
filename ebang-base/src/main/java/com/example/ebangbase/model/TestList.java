package com.example.ebangbase.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StringCollectionDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description 
 * @author xiaobo
 * @date 2024/5/22 17:34
 */
@Data
public class TestList {

    private String name;
    @JsonDeserialize(using = StringCollectionDeserializer.class)
    private List<String> list;

    public TestList() {
    }

    public TestList(String name, List<String> list) {
        this.name = name;
        this.list = list;
    }
}
