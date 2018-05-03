package com.xhp.crowdfunding_backend.dao.Impl;
import com.xhp.crowdfunding_backend.entity.Project;
import com.xhp.crowdfunding_backend.dao.ProjectDao;
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
public class ProjectDaoImpl implements ProjectDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(readOnly = true)
    public Project findOne(String pid) {
        try {
            return jdbcTemplate.queryForObject("select * from project where pid=?", new Object[]{pid}, new ProjectRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Project> findAll() {
        return jdbcTemplate.query("select * from project", new ProjectRowMapper());
    }
    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(PageRequest pageRequest) {
        return new Pagination("select * from project",pageRequest,jdbcTemplate);
    }
    @Override
    public Project create(Project project) {
                    String pid = UUID.randomUUID().toString().replaceAll("-", "");
            project.setPid(pid);
            final String sql = "insert into project values( ?  ,  ?  ,  ?  ,  ?  ,  ?  ,  ?  ,  ?  ,  ?  ,  ?  ,  ?  ,  ?  ,  ?  ,  ?  ,  ?  ,  ?  ,  ?  ,  ?  ,  ?  ,  ?  ,  ?   )";
            jdbcTemplate.update(sql, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                                        ps.setString(1,project.getPid());
                                        ps.setString(2,project.getPaccountname());
                                        ps.setString(3,project.getPidentify());
                                        ps.setString(4,project.getPaccounttel());
                                        ps.setString(5,project.getPloc());
                                        ps.setString(6,project.getPimage());
                                        ps.setString(7,project.getPname());
                                        ps.setString(8,project.getPcontent());
                                        ps.setString(9,project.getPdate());
                                        ps.setInt(10,project.getPtargetmoney());
                                        ps.setInt(11,project.getPraisedays());
                                        ps.setInt(12,project.getPpresentmoney());
                                        ps.setString(13,project.getPtags());
                                        ps.setString(14,project.getPdescriptitle());
                                        ps.setString(15,project.getPdescripcont());
                                        ps.setString(16,project.getPrepaytype());
                                        ps.setString(17,project.getPrepaytitle());
                                        ps.setString(18,project.getPrepaycont());
                                        ps.setInt(19,project.getPcid());
                                        ps.setInt(20,project.getUid());
                                    }
            });
            return project;
            }

    @Override
    public void update(Project project){
        final String sql = "update project set  paccountName=?,pidentify=?,paccountTel=?,ploc=?,pimage=?,pname=?,pcontent=?,pdate=?,ptargetMoney=?,praiseDays=?,ppresentMoney=?,ptags=?,pdescripTitle=?,pdescripCont=?,prepayType=?,prepayTitle=?,prepayCont=?,pcid=?,uid=? where pid=?";
        jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                                                ps.setString(20,project.getPid());
                                                                ps.setString(1,project.getPaccountname());
                                                                ps.setString(2,project.getPidentify());
                                                                ps.setString(3,project.getPaccounttel());
                                                                ps.setString(4,project.getPloc());
                                                                ps.setString(5,project.getPimage());
                                                                ps.setString(6,project.getPname());
                                                                ps.setString(7,project.getPcontent());
                                                                ps.setString(8,project.getPdate());
                                                                ps.setInt(9,project.getPtargetmoney());
                                                                ps.setInt(10,project.getPraisedays());
                                                                ps.setInt(11,project.getPpresentmoney());
                                                                ps.setString(12,project.getPtags());
                                                                ps.setString(13,project.getPdescriptitle());
                                                                ps.setString(14,project.getPdescripcont());
                                                                ps.setString(15,project.getPrepaytype());
                                                                ps.setString(16,project.getPrepaytitle());
                                                                ps.setString(17,project.getPrepaycont());
                                                                ps.setInt(18,project.getPcid());
                                                                ps.setInt(19,project.getUid());
                                            }
        });
    }
    @Override
    public boolean delete(String pid){
        String sql = "delete from project  where pid=?";
        Object[] params = new Object[]{pid};
        int rowNum = jdbcTemplate.update(sql, params);
        return rowNum > 0 ? true : false;
    }
}

class ProjectRowMapper implements RowMapper<Project> {

    @Override
    public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
        Project project =new Project();
                project.setPid(rs.getString("pid"));
                project.setPaccountname(rs.getString("paccountname"));
                project.setPidentify(rs.getString("pidentify"));
                project.setPaccounttel(rs.getString("paccounttel"));
                project.setPloc(rs.getString("ploc"));
                project.setPimage(rs.getString("pimage"));
                project.setPname(rs.getString("pname"));
                project.setPcontent(rs.getString("pcontent"));
                project.setPdate(rs.getString("pdate"));
                project.setPtargetmoney(rs.getInt("ptargetmoney"));
                project.setPraisedays(rs.getInt("praisedays"));
                project.setPpresentmoney(rs.getInt("ppresentmoney"));
                project.setPtags(rs.getString("ptags"));
                project.setPdescriptitle(rs.getString("pdescriptitle"));
                project.setPdescripcont(rs.getString("pdescripcont"));
                project.setPrepaytype(rs.getString("prepaytype"));
                project.setPrepaytitle(rs.getString("prepaytitle"));
                project.setPrepaycont(rs.getString("prepaycont"));
                project.setPcid(rs.getInt("pcid"));
                project.setUid(rs.getInt("uid"));
                return project;
    }

}