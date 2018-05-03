package com.xhp.crowdfunding_backend.dao;
import com.xhp.crowdfunding_backend.common.Pagination;
import com.xhp.crowdfunding_backend.common.PageRequest;
import com.xhp.crowdfunding_backend.entity.User;
import java.util.List;
/**
 * 
 * 
 * @author yuchu
 * @email 
 * @date 2018-05-03 10:00:35
 */

public interface UserDao{

    User findOne(String uid);

    List<User> findAll();

    Pagination<User> getPage(PageRequest pageRequest);

    User create(User user);

    void update(User user);

    boolean delete(String uid);
}
