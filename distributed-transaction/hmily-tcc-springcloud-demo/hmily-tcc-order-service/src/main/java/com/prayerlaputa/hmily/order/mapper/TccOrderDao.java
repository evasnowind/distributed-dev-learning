package com.prayerlaputa.hmily.order.mapper;

import com.prayerlaputa.hmily.common.entity.OrderDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author chenglong.yu
 * created on 2020/12/10
 */
@Mapper
@Repository
public interface TccOrderDao {


    /**
     * 插入订单记录
     *
     * @param order 订单
     * @return 影响记录数量
     */
    @Insert("INSERT INTO orders (user_id, product_id, pay_amount) VALUES (#{userId}, #{productId}, #{payAmount})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int saveOrder(OrderDO order);

    /**
     * 更新账单状态
     *
     * @param order
     * @return
     */
    @Update("UPDATE orders SET status = #{status} WHERE id = #{id}")
//    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int updateStatus(OrderDO order);
}
