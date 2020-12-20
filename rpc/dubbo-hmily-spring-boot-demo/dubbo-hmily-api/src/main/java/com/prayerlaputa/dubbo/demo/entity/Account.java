package com.prayerlaputa.dubbo.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenglong.yu
 * created on 2020/12/18
 */
@Data
public class Account implements Serializable {


    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Long cnWallet;

    private Long usWallet;

}
