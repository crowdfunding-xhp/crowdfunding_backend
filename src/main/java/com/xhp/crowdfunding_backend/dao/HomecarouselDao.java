package com.xhp.crowdfunding_backend.dao;
import com.xhp.crowdfunding_backend.common.Pagination;
import com.xhp.crowdfunding_backend.common.PageRequest;
import com.xhp.crowdfunding_backend.entity.Homecarousel;
import java.util.List;
/**
 * 
 * 
 * @author yuchu
 * @email 
 * @date 2018-05-03 10:00:35
 */

public interface HomecarouselDao{

    Homecarousel findOne(String hcid);

    List<Homecarousel> findAll();

    Pagination<Homecarousel> getPage(PageRequest pageRequest);

    Homecarousel create(Homecarousel homecarousel);

    void update(Homecarousel homecarousel);

    boolean delete(String hcid);
}
