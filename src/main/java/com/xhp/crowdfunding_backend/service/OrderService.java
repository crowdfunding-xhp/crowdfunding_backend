package com.xhp.crowdfunding_backend.service;
import com.xhp.crowdfunding_backend.common.Pagination;
import com.xhp.crowdfunding_backend.common.PageRequest;
import com.xhp.crowdfunding_backend.entity.Order;
import com.xhp.crowdfunding_backend.dao.OrderDao;
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
public class OrderService{
    @Autowired
    private OrderDao orderDao;

    public Order addOrder(Order order){
        return orderDao.create(order);
    }

    public void updateOrder(Order order){
            orderDao.update(order);
    }

    public void deleteOrder(String oid){
        orderDao.delete(oid);
    }

    public Order getById( String oid){
       return orderDao.findOne(oid);
    }

    public List<Order> getAll(){
       return orderDao.findAll();
    }

    public Pagination<Order> getPage(Integer pageNum,Integer pageSize){
        PageRequest pageRequest = new PageRequest(pageNum,pageSize);
        return orderDao.getPage(pageRequest);
    }
}

