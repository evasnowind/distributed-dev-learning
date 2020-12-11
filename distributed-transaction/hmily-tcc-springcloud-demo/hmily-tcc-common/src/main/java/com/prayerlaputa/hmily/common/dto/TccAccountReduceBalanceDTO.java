package com.prayerlaputa.hmily.common.dto;

/**
 * @author chenglong.yu
 * created on 2020/12/9
 */
public class TccAccountReduceBalanceDTO {

    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 扣减金额
     */
    private Integer price;

    public Long getUserId() {
        return userId;
    }

    public TccAccountReduceBalanceDTO setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public TccAccountReduceBalanceDTO setPrice(Integer price) {
        this.price = price;
        return this;
    }
}
