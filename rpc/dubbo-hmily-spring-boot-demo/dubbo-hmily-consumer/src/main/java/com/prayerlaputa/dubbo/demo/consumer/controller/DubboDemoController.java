package com.prayerlaputa.dubbo.demo.consumer.controller;

import com.prayerlaputa.dubbo.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenglong.yu
 * created on 2020/12/19
 */
@RestController
@RequestMapping("/api/dubbo")
public class DubboDemoController {

    @Autowired
    private TransactionService transactionService;

}
