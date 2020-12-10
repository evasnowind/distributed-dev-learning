package com.prayerlaputa.hmily.product.dto;

/**
 * @author chenglong.yu
 * created on 2020/12/10
 */
public class TccProductReduceStockDTO {

    /**
     * 商品编号
     */
    private Long productId;
    /**
     * 数量
     */
    private Integer amount;

    public Long getProductId() {
        return productId;
    }

    public TccProductReduceStockDTO setProductId(Long productId) {
        this.productId = productId;
        return this;
    }

    public Integer getAmount() {
        return amount;
    }

    public TccProductReduceStockDTO setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }
}
