package com.xhp.crowdfunding_backend.dao.Impl;
import com.xhp.crowdfunding_backend.entity.Homecarousel;
import com.xhp.crowdfunding_backend.dao.HomecarouselDao;
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
public class HomecarouselDaoImpl implements HomecarouselDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(readOnly = true)
    public Homecarousel findOne(String hcid) {
        try {
            return jdbcTemplate.queryForObject("select * from homecarousel where hcid=?", new Object[]{hcid}, new HomecarouselRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Homecarousel> findAll() {
        return jdbcTemplate.query("select * from homecarousel", new HomecarouselRowMapper());
    }
    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(PageRequest pageRequest) {
        return new Pagination("select * from homecarousel",pageRequest,jdbcTemplate);
    }
    @Override
    public Homecarousel create(Homecarousel homecarousel) {
                    String hcid = UUID.randomUUID().toString().replaceAll("-", "");
            homecarousel.setHcid(hcid);
            final String sql = "insert into homecarousel values( ?  ,  ?   )";
            jdbcTemplate.update(sql, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                                        ps.setString(1,homecarousel.getHcid());
                                        ps.setString(2,homecarousel.getHcimage());
                                    }
            });
            return homecarousel;
            }

    @Override
    public void update(Homecarousel homecarousel){
        final String sql = "update homecarousel set  HCimage=? where HCid=?";
        jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                                                ps.setString(2,homecarousel.getHcid());
                                                                ps.setString(1,homecarousel.getHcimage());
                                            }
        });
    }
    @Override
    public boolean delete(String hcid){
        String sql = "delete from homecarousel  where HCid=?";
        Object[] params = new Object[]{hcid};
        int rowNum = jdbcTemplate.update(sql, params);
        return rowNum > 0 ? true : false;
    }
}

class HomecarouselRowMapper implements RowMapper<Homecarousel> {

    @Override
    public Homecarousel mapRow(ResultSet rs, int rowNum) throws SQLException {
        Homecarousel homecarousel =new Homecarousel();
                homecarousel.setHcid(rs.getString("hcid"));
                homecarousel.setHcimage(rs.getString("hcimage"));
                return homecarousel;
    }

}