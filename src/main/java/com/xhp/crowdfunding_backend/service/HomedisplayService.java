package com.xhp.crowdfunding_backend.service;
import com.xhp.crowdfunding_backend.common.Pagination;
import com.xhp.crowdfunding_backend.common.PageRequest;
import com.xhp.crowdfunding_backend.entity.Homedisplay;
import com.xhp.crowdfunding_backend.dao.HomedisplayDao;
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
public class HomedisplayService{
    @Autowired
    private HomedisplayDao homedisplayDao;

    public Homedisplay addHomedisplay(Homedisplay homedisplay){
        return homedisplayDao.create(homedisplay);
    }

    public void updateHomedisplay(Homedisplay homedisplay){
            homedisplayDao.update(homedisplay);
    }

    public void deleteHomedisplay(String pid){
        homedisplayDao.delete(pid);
    }

    public Homedisplay getById( String pid){
       return homedisplayDao.findOne(pid);
    }

    public List<Homedisplay> getAll(){
       return homedisplayDao.findAll();
    }

    public Pagination<Homedisplay> getPage(Integer pageNum,Integer pageSize){
        PageRequest pageRequest = new PageRequest(pageNum,pageSize);
        return homedisplayDao.getPage(pageRequest);
    }
}

