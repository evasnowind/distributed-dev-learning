package com.prayerlaputa.productservice.controller;

import com.prayerlaputa.productservice.dto.ProductReduceStockDTO;
import com.prayerlaputa.productservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    private Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @PostMapping("/reduce-stock")
    public Boolean reduceStock(@RequestBody ProductReduceStockDTO productReduceStockDTO) {
        logger.info("[reduceStock] 收到减少库存请求, 商品:{}, 价格:{}", productReduceStockDTO.getProductId(),
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

}
