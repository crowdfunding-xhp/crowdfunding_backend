package com.xhp.crowdfunding_backend.service;
import com.xhp.crowdfunding_backend.common.Pagination;
import com.xhp.crowdfunding_backend.common.PageRequest;
import com.xhp.crowdfunding_backend.entity.Homecarousel;
import com.xhp.crowdfunding_backend.dao.HomecarouselDao;
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
public class HomecarouselService{
    @Autowired
    private HomecarouselDao homecarouselDao;

    public Homecarousel addHomecarousel(Homecarousel homecarousel){
        return homecarouselDao.create(homecarousel);
    }

    public void updateHomecarousel(Homecarousel homecarousel){
            homecarouselDao.update(homecarousel);
    }

    public void deleteHomecarousel(String hcid){
        homecarouselDao.delete(hcid);
    }

    public Homecarousel getById( String hcid){
       return homecarouselDao.findOne(hcid);
    }

    public List<Homecarousel> getAll(){
       return homecarouselDao.findAll();
    }

    public Pagination<Homecarousel> getPage(Integer pageNum,Integer pageSize){
        PageRequest pageRequest = new PageRequest(pageNum,pageSize);
        return homecarouselDao.getPage(pageRequest);
    }
}

