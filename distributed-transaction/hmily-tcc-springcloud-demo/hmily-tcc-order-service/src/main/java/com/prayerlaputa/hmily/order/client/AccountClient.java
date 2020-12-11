package com.prayerlaputa.hmily.order.client;

import com.prayerlaputa.hmily.common.dto.TccAccountReduceBalanceDTO;
import org.dromara.hmily.annotation.Hmily;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author chenglong.yu
 * created on 2020/12/11
 */
@FeignClient(value = "account-service")
public interface AccountClient {

    /**
     * 获取账户余额
     *
     * @param userId
     * @return
     */
    @RequestMapping("/account/balance")
    @Hmily
    Integer getBalance(@RequestParam Long userId);

    /**
     * 减少余额
     *
     * @param accountReduceBalanceDTO
     * @return
     */
    @RequestMapping("/account/reduce-balance")
    @Hmily
    Boolean reduceBalance(TccAccountReduceBalanceDTO accountReduceBalanceDTO);
}
