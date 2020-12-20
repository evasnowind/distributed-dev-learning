package com.prayerlaputa.dubbo.demo.mapper;

import com.prayerlaputa.dubbo.demo.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author chenglong.yu
 * created on 2020/12/18
 */
@Mapper
@Repository
public interface AccountMapper {


    /**
     * pay for money
     * @param account account
     */
    @Update("update `hmily_dubbo_account` set us_wallet = us_wallet + #{usWallet}, cn_wallet = cn_wallet + " +
            "#{cnWallet} where us_wallet >= #{usWallet} and cn_wallet >= #{cnWallet} and id = #{id}")
    boolean exchange(Account account);

    /**
     * query one
     * @param account account
     * @return account
     */
    @Select("select id, name, us_wallet as usWallet, cn_wallet as cnWallet from hmily_dubbo_account where id = #{id}")
    @ResultType(Account.class)
    Account select(Account account);
}
