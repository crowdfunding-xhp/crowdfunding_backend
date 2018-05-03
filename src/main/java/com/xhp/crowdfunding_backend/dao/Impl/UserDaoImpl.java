package com.xhp.crowdfunding_backend.dao.Impl;
import com.xhp.crowdfunding_backend.entity.User;
import com.xhp.crowdfunding_backend.dao.UserDao;
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
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(readOnly = true)
    public User findOne(String uid) {
        try {
            return jdbcTemplate.queryForObject("select * from user where uid=?", new Object[]{uid}, new UserRowMapper());
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return jdbcTemplate.query("select * from user", new UserRowMapper());
    }
    @Override
    @Transactional(readOnly = true)
    public Pagination getPage(PageRequest pageRequest) {
        return new Pagination("select * from user",pageRequest,jdbcTemplate);
    }
    @Override
    public User create(User user) {
                    String uid = UUID.randomUUID().toString().replaceAll("-", "");
            user.setUid(uid);
            final String sql = "insert into user values( ?  ,  ?  ,  ?  ,  ?  ,  ?  ,  ?  ,  ?  ,  ?  ,  ?   )";
            jdbcTemplate.update(sql, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                                        ps.setString(1,user.getUid());
                                        ps.setString(2,user.getUnickname());
                                        ps.setString(3,user.getUpassword());
                                        ps.setString(4,user.getUname());
                                        ps.setString(5,user.getUsex());
                                        ps.setString(6,user.getUidnumber());
                                        ps.setString(7,user.getUphone());
                                        ps.setString(8,user.getUimage());
                                        ps.setString(9,user.getUemail());
                                    }
            });
            return user;
            }

    @Override
    public void update(User user){
        final String sql = "update user set  unickname=?,upassword=?,uname=?,usex=?,uidNumber=?,uphone=?,uimage=?,uemail=? where uid=?";
        jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                                                ps.setString(9,user.getUid());
                                                                ps.setString(1,user.getUnickname());
                                                                ps.setString(2,user.getUpassword());
                                                                ps.setString(3,user.getUname());
                                                                ps.setString(4,user.getUsex());
                                                                ps.setString(5,user.getUidnumber());
                                                                ps.setString(6,user.getUphone());
                                                                ps.setString(7,user.getUimage());
                                                                ps.setString(8,user.getUemail());
                                            }
        });
    }
    @Override
    public boolean delete(String uid){
        String sql = "delete from user  where uid=?";
        Object[] params = new Object[]{uid};
        int rowNum = jdbcTemplate.update(sql, params);
        return rowNum > 0 ? true : false;
    }
}

class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user =new User();
                user.setUid(rs.getString("uid"));
                user.setUnickname(rs.getString("unickname"));
                user.setUpassword(rs.getString("upassword"));
                user.setUname(rs.getString("uname"));
                user.setUsex(rs.getString("usex"));
                user.setUidnumber(rs.getString("uidnumber"));
                user.setUphone(rs.getString("uphone"));
                user.setUimage(rs.getString("uimage"));
                user.setUemail(rs.getString("uemail"));
                return user;
    }

}