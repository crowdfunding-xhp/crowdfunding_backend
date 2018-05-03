package com.xhp.crowdfunding_backend.service;
import com.xhp.crowdfunding_backend.common.Pagination;
import com.xhp.crowdfunding_backend.common.PageRequest;
import com.xhp.crowdfunding_backend.entity.Sponsor;
import com.xhp.crowdfunding_backend.dao.SponsorDao;
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
public class SponsorService{
    @Autowired
    private SponsorDao sponsorDao;

    public Sponsor addSponsor(Sponsor sponsor){
        return sponsorDao.create(sponsor);
    }

    public void updateSponsor(Sponsor sponsor){
            sponsorDao.update(sponsor);
    }

    public void deleteSponsor(String sid){
        sponsorDao.delete(sid);
    }

    public Sponsor getById( String sid){
       return sponsorDao.findOne(sid);
    }

    public List<Sponsor> getAll(){
       return sponsorDao.findAll();
    }

    public Pagination<Sponsor> getPage(Integer pageNum,Integer pageSize){
        PageRequest pageRequest = new PageRequest(pageNum,pageSize);
        return sponsorDao.getPage(pageRequest);
    }
}

