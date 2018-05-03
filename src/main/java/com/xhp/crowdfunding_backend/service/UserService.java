package com.xhp.crowdfunding_backend.service;
import com.xhp.crowdfunding_backend.common.Pagination;
import com.xhp.crowdfunding_backend.common.PageRequest;
import com.xhp.crowdfunding_backend.entity.User;
import com.xhp.crowdfunding_backend.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 
 *
 * @author yuchu
 * @email 
 * @date 2018-05-03 10:00:35
 */
@Service
public class UserService{
    @Autowired
    private UserDao userDao;

    public User addUser(User user){
        return userDao.create(user);
    }

    public void updateUser(User user){
            userDao.update(user);
    }

    public void deleteUser(String uid){
        userDao.delete(uid);
    }

    public User getById( String uid){
       return userDao.findOne(uid);
    }

    public List<User> getAll(){
       return userDao.findAll();
    }

    public Pagination<User> getPage(Integer pageNum,Integer pageSize){
        PageRequest pageRequest = new PageRequest(pageNum,pageSize);
        return userDao.getPage(pageRequest);
    }
}

