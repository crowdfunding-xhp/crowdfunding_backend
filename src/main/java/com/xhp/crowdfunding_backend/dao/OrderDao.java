package com.xhp.crowdfunding_backend.dao;
import com.xhp.crowdfunding_backend.common.Pagination;
import com.xhp.crowdfunding_backend.common.PageRequest;
import com.xhp.crowdfunding_backend.entity.Order;
import java.util.List;
/**
 * 
 * 
 * @author yuchu
 * @email 
 * @date 2018-05-03 10:00:35
 */

public interface OrderDao{

    Order findOne(String oid);

    List<Order> findAll();

    Pagination<Order> getPage(PageRequest pageRequest);

    Order create(Order order);

    void update(Order order);

    boolean delete(String oid);
}
