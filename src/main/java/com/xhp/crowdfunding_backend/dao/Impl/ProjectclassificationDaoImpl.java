package com.xhp.crowdfunding_backend.dao.Impl;
import com.xhp.crowdfunding_backend.entity.Projectclassification;
import com.xhp.crowdfunding_backend.dao.ProjectclassificationDao;
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
public class ProjectclassificationDaoImpl implements ProjectclassificationDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(readOnly = true)
    public Projectclassification findOne(String pcid) {
        try {
            return jdbcTemplate.queryForObject("select * from projectclassification where pcid=?", new Object[]{pcid}, new ProjectclassificationRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Projectclassification> findAll() {
        return jdbcTemplate.query("select * from projectclassification", new ProjectclassificationRowMapper());
    }
    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(PageRequest pageRequest) {
        return new Pagination("select * from projectclassification",pageRequest,jdbcTemplate);
    }
    @Override
    public Projectclassification create(Projectclassification projectclassification) {
                    String pcid = UUID.randomUUID().toString().replaceAll("-", "");
            projectclassification.setPcid(pcid);
            final String sql = "insert into projectclassification values( ?  ,  ?  ,  ?  ,  ?   )";
            jdbcTemplate.update(sql, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                                        ps.setString(1,projectclassification.getPcid());
                                        ps.setString(2,projectclassification.getPcname());
                                        ps.setInt(3,projectclassification.getPcnumber());
                                        ps.setString(4,projectclassification.getPcimage());
                                    }
            });
            return projectclassification;
            }

    @Override
    public void update(Projectclassification projectclassification){
        final String sql = "update projectclassification set  Pcname=?,Pcnumber=?,Pcimage=? where Pcid=?";
        jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                                                ps.setString(4,projectclassification.getPcid());
                                                                ps.setString(1,projectclassification.getPcname());
                                                                ps.setInt(2,projectclassification.getPcnumber());
                                                                ps.setString(3,projectclassification.getPcimage());
                                            }
        });
    }
    @Override
    public boolean delete(String pcid){
        String sql = "delete from projectclassification  where Pcid=?";
        Object[] params = new Object[]{pcid};
        int rowNum = jdbcTemplate.update(sql, params);
        return rowNum > 0 ? true : false;
    }
}

class ProjectclassificationRowMapper implements RowMapper<Projectclassification> {

    @Override
    public Projectclassification mapRow(ResultSet rs, int rowNum) throws SQLException {
        Projectclassification projectclassification =new Projectclassification();
                projectclassification.setPcid(rs.getString("pcid"));
                projectclassification.setPcname(rs.getString("pcname"));
                projectclassification.setPcnumber(rs.getInt("pcnumber"));
                projectclassification.setPcimage(rs.getString("pcimage"));
                return projectclassification;
    }

}