package com.xhp.crowdfunding_backend.dao.Impl;
import com.xhp.crowdfunding_backend.entity.Homedisplay;
import com.xhp.crowdfunding_backend.dao.HomedisplayDao;
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
public class HomedisplayDaoImpl implements HomedisplayDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(readOnly = true)
    public Homedisplay findOne(String pid) {
        try {
            return jdbcTemplate.queryForObject("select * from homedisplay where pid=?", new Object[]{pid}, new HomedisplayRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Homedisplay> findAll() {
        return jdbcTemplate.query("select * from homedisplay", new HomedisplayRowMapper());
    }
    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(PageRequest pageRequest) {
        return new Pagination("select * from homedisplay",pageRequest,jdbcTemplate);
    }
    @Override
    public Homedisplay create(Homedisplay homedisplay) {
                    String pid = UUID.randomUUID().toString().replaceAll("-", "");
            homedisplay.setPid(pid);
            final String sql = "insert into homedisplay values( ?  ,  ?   )";
            jdbcTemplate.update(sql, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                                        ps.setString(1,homedisplay.getPid());
                                        ps.setString(2,homedisplay.getPcid());
                                    }
            });
            return homedisplay;
            }

    @Override
    public void update(Homedisplay homedisplay){
        final String sql = "update homedisplay set  PCid=? where Pid=?";
        jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                                                ps.setString(2,homedisplay.getPid());
                                                                ps.setString(1,homedisplay.getPcid());
                                            }
        });
    }
    @Override
    public boolean delete(String pid){
        String sql = "delete from homedisplay  where Pid=?";
        Object[] params = new Object[]{pid};
        int rowNum = jdbcTemplate.update(sql, params);
        return rowNum > 0 ? true : false;
    }
}

class HomedisplayRowMapper implements RowMapper<Homedisplay> {

    @Override
    public Homedisplay mapRow(ResultSet rs, int rowNum) throws SQLException {
        Homedisplay homedisplay =new Homedisplay();
                homedisplay.setPid(rs.getString("pid"));
                homedisplay.setPcid(rs.getString("pcid"));
                return homedisplay;
    }

}