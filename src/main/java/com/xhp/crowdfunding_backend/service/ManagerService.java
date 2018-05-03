package com.xhp.crowdfunding_backend.service;
import com.xhp.crowdfunding_backend.common.Pagination;
import com.xhp.crowdfunding_backend.common.PageRequest;
import com.xhp.crowdfunding_backend.entity.Manager;
import com.xhp.crowdfunding_backend.dao.ManagerDao;
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
public class ManagerService{
    @Autowired
    private ManagerDao managerDao;

    public Manager addManager(Manager manager){
        return managerDao.create(manager);
    }

    public void updateManager(Manager manager){
            managerDao.update(manager);
    }

    public void deleteManager(Integer mid){
        managerDao.delete(mid);
    }

    public Manager getById( Integer mid){
       return managerDao.findOne(mid);
    }

    public List<Manager> getAll(){
       return managerDao.findAll();
    }

    public Pagination<Manager> getPage(Integer pageNum,Integer pageSize){
        PageRequest pageRequest = new PageRequest(pageNum,pageSize);
        return managerDao.getPage(pageRequest);
    }
}

