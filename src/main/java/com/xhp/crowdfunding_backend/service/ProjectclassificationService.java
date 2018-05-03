package com.xhp.crowdfunding_backend.service;
import com.xhp.crowdfunding_backend.common.Pagination;
import com.xhp.crowdfunding_backend.common.PageRequest;
import com.xhp.crowdfunding_backend.entity.Projectclassification;
import com.xhp.crowdfunding_backend.dao.ProjectclassificationDao;
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
public class ProjectclassificationService{
    @Autowired
    private ProjectclassificationDao projectclassificationDao;

    public Projectclassification addProjectclassification(Projectclassification projectclassification){
        return projectclassificationDao.create(projectclassification);
    }

    public void updateProjectclassification(Projectclassification projectclassification){
            projectclassificationDao.update(projectclassification);
    }

    public void deleteProjectclassification(String pcid){
        projectclassificationDao.delete(pcid);
    }

    public Projectclassification getById( String pcid){
       return projectclassificationDao.findOne(pcid);
    }

    public List<Projectclassification> getAll(){
       return projectclassificationDao.findAll();
    }

    public Pagination<Projectclassification> getPage(Integer pageNum,Integer pageSize){
        PageRequest pageRequest = new PageRequest(pageNum,pageSize);
        return projectclassificationDao.getPage(pageRequest);
    }
}

