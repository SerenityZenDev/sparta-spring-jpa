package org.example.mybatis.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.mybatis.vo.AccountMyBatisVO;

@Mapper
public interface AccountMapperV2 {

    @Select("SELECT * FROM account WHERE id = #{id}")
    AccountMyBatisVO selectAccount(Integer id);

    @Insert("INSERT INTO ACCOUNT (username, password) VALUES (#{username}, #{password})")
    void insertAccount(AccountMyBatisVO vo);
}
