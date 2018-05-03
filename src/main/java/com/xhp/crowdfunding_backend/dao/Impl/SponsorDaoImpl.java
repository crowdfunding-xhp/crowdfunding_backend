package com.xhp.crowdfunding_backend.dao.Impl;
import com.xhp.crowdfunding_backend.entity.Sponsor;
import com.xhp.crowdfunding_backend.dao.SponsorDao;
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
public class SponsorDaoImpl implements SponsorDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(readOnly = true)
    public Sponsor findOne(String sid) {
        try {
            return jdbcTemplate.queryForObject("select * from sponsor where sid=?", new Object[]{sid}, new SponsorRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sponsor> findAll() {
        return jdbcTemplate.query("select * from sponsor", new SponsorRowMapper());
    }
    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(PageRequest pageRequest) {
        return new Pagination("select * from sponsor",pageRequest,jdbcTemplate);
    }
    @Override
    public Sponsor create(Sponsor sponsor) {
                    String sid = UUID.randomUUID().toString().replaceAll("-", "");
            sponsor.setSid(sid);
            final String sql = "insert into sponsor values( ?  ,  ?  ,  ?   )";
            jdbcTemplate.update(sql, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                                        ps.setString(1,sponsor.getSid());
                                        ps.setString(2,sponsor.getSname());
                                        ps.setInt(3,sponsor.getSmoney());
                                    }
            });
            return sponsor;
            }

    @Override
    public void update(Sponsor sponsor){
        final String sql = "update sponsor set  Sname=?,Smoney=? where Sid=?";
        jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                                                ps.setString(3,sponsor.getSid());
                                                                ps.setString(1,sponsor.getSname());
                                                                ps.setInt(2,sponsor.getSmoney());
                                            }
        });
    }
    @Override
    public boolean delete(String sid){
        String sql = "delete from sponsor  where Sid=?";
        Object[] params = new Object[]{sid};
        int rowNum = jdbcTemplate.update(sql, params);
        return rowNum > 0 ? true : false;
    }
}

class SponsorRowMapper implements RowMapper<Sponsor> {

    @Override
    public Sponsor mapRow(ResultSet rs, int rowNum) throws SQLException {
        Sponsor sponsor =new Sponsor();
                sponsor.setSid(rs.getString("sid"));
                sponsor.setSname(rs.getString("sname"));
                sponsor.setSmoney(rs.getInt("smoney"));
                return sponsor;
    }

}