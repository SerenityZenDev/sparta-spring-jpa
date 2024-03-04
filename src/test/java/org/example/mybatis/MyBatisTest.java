package org.example.mybatis;

import org.example.mybatis.configuration.DBConfiguration;
import org.example.mybatis.mapper.AccountMapper;
import org.example.mybatis.mapper.AccountMapperV2;
import org.example.mybatis.vo.AccountMyBatisVO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(DBConfiguration.class)
public class MyBatisTest {

    @Autowired
    private AccountMapper accountMapper; // xml 로 쿼리관리

    @Autowired
    private AccountMapperV2 accountMapperV2; // annotation 으로 쿼리관리

    @Test
    @DisplayName("SQL Mapper - MyBatis 실습")
    void sqlMapper_MyBatisTest() {
        // given

        // when
        accountMapper.insertAccount(new AccountMyBatisVO("new user3", "new password3"));
        var account = accountMapper.selectAccount(1);

        // then
        assert !account.getUsername().isEmpty();
    }

    @Test
    @DisplayName("SQL Mapper - MyBatis V2 실습")
    void sqlMapper_MyBatisV2Test() {
        // given

        // when
        accountMapperV2.insertAccount(new AccountMyBatisVO("new user4", "new password4"));
        var account = accountMapperV2.selectAccount(1);

        // then
        assert !account.getUsername().isEmpty();
    }
}
