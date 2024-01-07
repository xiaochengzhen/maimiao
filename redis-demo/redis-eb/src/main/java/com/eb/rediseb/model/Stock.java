package com.eb.rediseb.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description 
 * @author xiaobo
 * @date 2024/1/3 15:22
 */
public class Stock implements Serializable {

    private String symbol;
    private BigDecimal price;
    private Integer count;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "symbol='" + symbol + '\'' +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
}
