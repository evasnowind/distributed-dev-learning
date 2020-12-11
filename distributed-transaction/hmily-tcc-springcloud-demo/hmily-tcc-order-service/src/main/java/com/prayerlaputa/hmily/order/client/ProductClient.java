package com.prayerlaputa.hmily.order.client;

import com.prayerlaputa.hmily.common.dto.TccProductReduceStockDTO;
import org.dromara.hmily.annotation.Hmily;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author chenglong.yu
 * created on 2020/12/11
 */
@FeignClient(value = "product-service")
public interface ProductClient {


    /**
     * 查询库存
     *
     * @param productId
     * @return
     */
    @RequestMapping("/product/stock")
    @Hmily
    Integer getStock(@RequestParam Long productId);


    @RequestMapping("/product/reduce-stock")
    @Hmily
    Boolean reduceStock(TccProductReduceStockDTO productReduceStockDTO);

}
