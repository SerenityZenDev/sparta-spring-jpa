package org.example.jdbc.template;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.example.jdbc.vo.AccountVO;
import org.springframework.jdbc.core.RowMapper;

public class AccountRowMapper implements RowMapper<AccountVO> {

    @Override
    public AccountVO mapRow(ResultSet rs, int rowNum) throws SQLException {
        var vo = new AccountVO();
        vo.setId(rs.getInt(1));
        vo.setUsername(rs.getString("USERNAME"));
        vo.setPassword(rs.getString("PASSWORD"));
        return vo;
    }
}
