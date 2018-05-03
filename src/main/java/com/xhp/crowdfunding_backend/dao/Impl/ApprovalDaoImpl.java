package com.xhp.crowdfunding_backend.dao.Impl;
import com.xhp.crowdfunding_backend.entity.Approval;
import com.xhp.crowdfunding_backend.dao.ApprovalDao;
import com.xhp.crowdfunding_backend.common.Pagination;
import com.xhp.crowdfunding_backend.common.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.EmptyResultDataAccessException;
import java.sql.*;
import java.util.List;
import java.util.UUID;

/**
 * 
 *
 * @author yuchu
 * @email 
 * @date 2018-05-03 10:00:35
 */
@Repository
public class ApprovalDaoImpl implements ApprovalDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(readOnly = true)
    public Approval findOne(String aid) {
        try {
            return jdbcTemplate.queryForObject("select * from approval where aid=?", new Object[]{aid}, new ApprovalRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Approval> findAll() {
        return jdbcTemplate.query("select * from approval", new ApprovalRowMapper());
    }
    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(PageRequest pageRequest) {
        return new Pagination("select * from approval",pageRequest,jdbcTemplate);
    }
    @Override
    public Approval create(Approval approval) {
                    String aid = UUID.randomUUID().toString().replaceAll("-", "");
            approval.setAid(aid);
            final String sql = "insert into approval values( ?  ,  ?  ,  ?  ,  ?   )";
            jdbcTemplate.update(sql, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                                        ps.setString(1,approval.getAid());
                                        ps.setString(2,approval.getAstate());
                                        ps.setString(3,approval.getPid());
                                        ps.setDate(4,new Date(approval.getAdate().getTime()));
                                    }
            });
            return approval;
            }

    @Override
    public void update(Approval approval){
        final String sql = "update approval set  Astate=?,Pid=?,Adate=? where Aid=?";
        jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                                                ps.setString(4,approval.getAid());
                                                                ps.setString(1,approval.getAstate());
                                                                ps.setString(2,approval.getPid());
                                                                ps.setDate(3,new Date(approval.getAdate().getTime()));
                                            }
        });
    }
    @Override
    public boolean delete(String aid){
        String sql = "delete from approval  where Aid=?";
        Object[] params = new Object[]{aid};
        int rowNum = jdbcTemplate.update(sql, params);
        return rowNum > 0 ? true : false;
    }
}

class ApprovalRowMapper implements RowMapper<Approval> {

    @Override
    public Approval mapRow(ResultSet rs, int rowNum) throws SQLException {
        Approval approval =new Approval();
                approval.setAid(rs.getString("aid"));
                approval.setAstate(rs.getString("astate"));
                approval.setPid(rs.getString("pid"));
                approval.setAdate(rs.getDate("adate"));
                return approval;
    }

}