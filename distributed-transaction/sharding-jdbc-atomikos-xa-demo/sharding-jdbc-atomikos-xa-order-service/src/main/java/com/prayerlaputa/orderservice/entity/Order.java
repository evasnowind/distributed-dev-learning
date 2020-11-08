package com.prayerlaputa.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 订单实体
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {

    /** 订单编号 **/
    private Integer id;

    /** 用户编号 **/
    private Long userId;

    /** 产品编号 **/
    private Long productId;

    /** 支付金额 **/
    private Integer payAmount;

    private Date addTime;


}
