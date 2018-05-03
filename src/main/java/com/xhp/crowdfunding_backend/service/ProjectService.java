package com.xhp.crowdfunding_backend.service;
import com.xhp.crowdfunding_backend.common.Pagination;
import com.xhp.crowdfunding_backend.common.PageRequest;
import com.xhp.crowdfunding_backend.entity.Project;
import com.xhp.crowdfunding_backend.dao.ProjectDao;
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
public class ProjectService{
    @Autowired
    private ProjectDao projectDao;

    public Project addProject(Project project){
        return projectDao.create(project);
    }

    public void updateProject(Project project){
            projectDao.update(project);
    }

    public void deleteProject(String pid){
        projectDao.delete(pid);
    }

    public Project getById( String pid){
       return projectDao.findOne(pid);
    }

    public List<Project> getAll(){
       return projectDao.findAll();
    }

    public Pagination<Project> getPage(Integer pageNum,Integer pageSize){
        PageRequest pageRequest = new PageRequest(pageNum,pageSize);
        return projectDao.getPage(pageRequest);
    }
}

