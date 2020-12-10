package com.prayerlaputa.hmily.product.controller;

import com.prayerlaputa.hmily.product.dto.TccProductReduceStockDTO;
import com.prayerlaputa.hmily.product.service.TccProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/product")
public class TccProductController {

    @Autowired
    private TccProductService productService;

    @PostMapping("/reduce-stock")
    public Boolean reduceStock(@RequestBody TccProductReduceStockDTO productReduceStockDTO) {
        log.info("[reduceStock] 收到减少库存请求, 商品:{}, 价格:{}", productReduceStockDTO.getProductId(),
                productReduceStockDTO.getAmount());
        try {
            productService.reduceStock(productReduceStockDTO.getProductId(), productReduceStockDTO.getAmount());
            // 正常扣除库存，返回 true
            return true;
        } catch (Exception e) {
            // 失败扣除库存，返回 false
            return false;
        }
    }

    @GetMapping("/stock")
    public Integer getStock(Long productId) {
        return productService.getStock(productId);
    }
}
