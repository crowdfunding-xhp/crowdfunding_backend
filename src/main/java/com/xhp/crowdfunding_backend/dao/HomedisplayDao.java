package com.xhp.crowdfunding_backend.dao;
import com.xhp.crowdfunding_backend.common.Pagination;
import com.xhp.crowdfunding_backend.common.PageRequest;
import com.xhp.crowdfunding_backend.entity.Homedisplay;
import java.util.List;
/**
 * 
 * 
 * @author yuchu
 * @email 
 * @date 2018-05-03 10:00:35
 */

public interface HomedisplayDao{

    Homedisplay findOne(String pid);

    List<Homedisplay> findAll();

    Pagination<Homedisplay> getPage(PageRequest pageRequest);

    Homedisplay create(Homedisplay homedisplay);

    void update(Homedisplay homedisplay);

    boolean delete(String pid);
}
