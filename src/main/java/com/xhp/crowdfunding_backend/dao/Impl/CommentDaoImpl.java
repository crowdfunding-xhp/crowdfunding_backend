package com.xhp.crowdfunding_backend.dao.Impl;
import com.xhp.crowdfunding_backend.entity.Comment;
import com.xhp.crowdfunding_backend.dao.CommentDao;
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
public class CommentDaoImpl implements CommentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(readOnly = true)
    public Comment findOne(String cid) {
        try {
            return jdbcTemplate.queryForObject("select * from comment where cid=?", new Object[]{cid}, new CommentRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findAll() {
        return jdbcTemplate.query("select * from comment", new CommentRowMapper());
    }
    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(PageRequest pageRequest) {
        return new Pagination("select * from comment",pageRequest,jdbcTemplate);
    }
    @Override
    public Comment create(Comment comment) {
                    String cid = UUID.randomUUID().toString().replaceAll("-", "");
            comment.setCid(cid);
            final String sql = "insert into comment values( ?  ,  ?  ,  ?  ,  ?  ,  ?   )";
            jdbcTemplate.update(sql, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                                        ps.setString(1,comment.getCid());
                                        ps.setString(2,comment.getCcontext());
                                        ps.setString(3,comment.getCreplyid());
                                        ps.setString(4,comment.getUid());
                                        ps.setString(5,comment.getPid());
                                    }
            });
            return comment;
            }

    @Override
    public void update(Comment comment){
        final String sql = "update comment set  Ccontext=?,CreplyId=?,Uid=?,Pid=? where Cid=?";
        jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                                                ps.setString(5,comment.getCid());
                                                                ps.setString(1,comment.getCcontext());
                                                                ps.setString(2,comment.getCreplyid());
                                                                ps.setString(3,comment.getUid());
                                                                ps.setString(4,comment.getPid());
                                            }
        });
    }
    @Override
    public boolean delete(String cid){
        String sql = "delete from comment  where Cid=?";
        Object[] params = new Object[]{cid};
        int rowNum = jdbcTemplate.update(sql, params);
        return rowNum > 0 ? true : false;
    }
}

class CommentRowMapper implements RowMapper<Comment> {

    @Override
    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Comment comment =new Comment();
                comment.setCid(rs.getString("cid"));
                comment.setCcontext(rs.getString("ccontext"));
                comment.setCreplyid(rs.getString("creplyid"));
                comment.setUid(rs.getString("uid"));
                comment.setPid(rs.getString("pid"));
                return comment;
    }

}