package com.xhp.crowdfunding_backend.dao.Impl;
import com.xhp.crowdfunding_backend.entity.Manager;
import com.xhp.crowdfunding_backend.dao.ManagerDao;
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
public class ManagerDaoImpl implements ManagerDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(readOnly = true)
    public Manager findOne(Integer mid) {
        try {
            return jdbcTemplate.queryForObject("select * from manager where mid=?", new Object[]{mid}, new ManagerRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Manager> findAll() {
        return jdbcTemplate.query("select * from manager", new ManagerRowMapper());
    }
    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(PageRequest pageRequest) {
        return new Pagination("select * from manager",pageRequest,jdbcTemplate);
    }
    @Override
    public Manager create(Manager manager) {
                    final String sql = "insert into manager( mpassword  ,  mname   ) values(   ?,  ?)";
            KeyHolder holder = new GeneratedKeyHolder();
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection)
                        throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                                                                                                                        ps.setString(1,manager.getMpassword());
                                                                                ps.setString(2,manager.getMname());
                                                            return ps;
                }
            },holder);
            int newId = holder.getKey().intValue();
            manager.setMid(newId);
            return manager;
            }

    @Override
    public void update(Manager manager){
        final String sql = "update manager set  mpassword=?,mname=? where mid=?";
        jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                                                ps.setInt(3,manager.getMid());
                                                                ps.setString(1,manager.getMpassword());
                                                                ps.setString(2,manager.getMname());
                                            }
        });
    }
    @Override
    public boolean delete(Integer mid){
        String sql = "delete from manager  where mid=?";
        Object[] params = new Object[]{mid};
        int rowNum = jdbcTemplate.update(sql, params);
        return rowNum > 0 ? true : false;
    }
}

class ManagerRowMapper implements RowMapper<Manager> {

    @Override
    public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {
        Manager manager =new Manager();
                manager.setMid(rs.getInt("mid"));
                manager.setMpassword(rs.getString("mpassword"));
                manager.setMname(rs.getString("mname"));
                return manager;
    }

}