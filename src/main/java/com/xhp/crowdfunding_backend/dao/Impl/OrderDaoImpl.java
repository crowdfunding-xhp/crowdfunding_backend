package com.xhp.crowdfunding_backend.dao.Impl;
import com.xhp.crowdfunding_backend.entity.Order;
import com.xhp.crowdfunding_backend.dao.OrderDao;
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
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(readOnly = true)
    public Order findOne(String oid) {
        try {
            return jdbcTemplate.queryForObject("select * from order where oid=?", new Object[]{oid}, new OrderRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findAll() {
        return jdbcTemplate.query("select * from order", new OrderRowMapper());
    }
    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(PageRequest pageRequest) {
        return new Pagination("select * from order",pageRequest,jdbcTemplate);
    }
    @Override
    public Order create(Order order) {
                    String oid = UUID.randomUUID().toString().replaceAll("-", "");
            order.setOid(oid);
            final String sql = "insert into order values( ?  ,  ?  ,  ?  ,  ?  ,  ?   )";
            jdbcTemplate.update(sql, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                                        ps.setString(1,order.getOid());
                                        ps.setString(2,order.getPid());
                                        ps.setString(3,order.getUid());
                                        ps.setString(4,order.getOstate());
                                        ps.setInt(5,order.getOmoney());
                                    }
            });
            return order;
            }

    @Override
    public void update(Order order){
        final String sql = "update order set  Pid=?,Uid=?,Ostate=?,Omoney=? where Oid=?";
        jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                                                ps.setString(5,order.getOid());
                                                                ps.setString(1,order.getPid());
                                                                ps.setString(2,order.getUid());
                                                                ps.setString(3,order.getOstate());
                                                                ps.setInt(4,order.getOmoney());
                                            }
        });
    }
    @Override
    public boolean delete(String oid){
        String sql = "delete from order  where Oid=?";
        Object[] params = new Object[]{oid};
        int rowNum = jdbcTemplate.update(sql, params);
        return rowNum > 0 ? true : false;
    }
}

class OrderRowMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order =new Order();
                order.setOid(rs.getString("oid"));
                order.setPid(rs.getString("pid"));
                order.setUid(rs.getString("uid"));
                order.setOstate(rs.getString("ostate"));
                order.setOmoney(rs.getInt("omoney"));
                return order;
    }

}