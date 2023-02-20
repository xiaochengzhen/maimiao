package com.example.mybatisplusdemo.model;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author xiaobo
 */
@Data
public class PageUtils<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer page;

    private Integer size;

    private Integer total;

    private transient List<T> list;

    public PageUtils(Integer page, Integer size, Integer total, List<T> list) {
        this.page = page;
        this.size = size;
        this.total = total;
        this.list = list;
    }

    public static <T> PageUtils<T> genByPageInfo(PageInfo<T> pageInfo) {
        return new PageUtils<>(pageInfo.getPageNum(), pageInfo.getPageSize(), (int) pageInfo.getTotal(), pageInfo.getList());
    }

    public static <T> PageUtils<T> genByList(List<T> pageList) {
        return PageUtils.genByPageInfo(new PageInfo<>(pageList));
    }
}
