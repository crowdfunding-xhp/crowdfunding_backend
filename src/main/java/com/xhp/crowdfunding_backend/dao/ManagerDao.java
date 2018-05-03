package com.xhp.crowdfunding_backend.dao;
import com.xhp.crowdfunding_backend.common.Pagination;
import com.xhp.crowdfunding_backend.common.PageRequest;
import com.xhp.crowdfunding_backend.entity.Manager;
import java.util.List;
/**
 * 
 * 
 * @author yuchu
 * @email 
 * @date 2018-05-03 10:00:35
 */

public interface ManagerDao{

    Manager findOne(Integer mid);

    List<Manager> findAll();

    Pagination<Manager> getPage(PageRequest pageRequest);

    Manager create(Manager manager);

    void update(Manager manager);

    boolean delete(Integer mid);
}
