package com.xhp.crowdfunding_backend.dao;
import com.xhp.crowdfunding_backend.common.Pagination;
import com.xhp.crowdfunding_backend.common.PageRequest;
import com.xhp.crowdfunding_backend.entity.Sponsor;
import java.util.List;
/**
 * 
 * 
 * @author yuchu
 * @email 
 * @date 2018-05-03 10:00:35
 */

public interface SponsorDao{

    Sponsor findOne(String sid);

    List<Sponsor> findAll();

    Pagination<Sponsor> getPage(PageRequest pageRequest);

    Sponsor create(Sponsor sponsor);

    void update(Sponsor sponsor);

    boolean delete(String sid);
}
