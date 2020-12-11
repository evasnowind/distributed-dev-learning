package com.prayerlaputa.hmily.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author chenglong.yu
 * created on 2020/12/11
 */
@AllArgsConstructor
@Getter
public enum OrderStatusEnum {

    NOT_PAY(1, "未支付"),
    PAYING(2, "支付中"),
    PAID_FAIL(3, "支付失败"),
    PAID_SUCCESS(4, "支付成功");

    private final int code;
    private final String desc;
}
