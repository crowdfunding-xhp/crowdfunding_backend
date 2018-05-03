package com.xhp.crowdfunding_backend.service;
import com.xhp.crowdfunding_backend.common.Pagination;
import com.xhp.crowdfunding_backend.common.PageRequest;
import com.xhp.crowdfunding_backend.entity.Approval;
import com.xhp.crowdfunding_backend.dao.ApprovalDao;
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
public class ApprovalService{
    @Autowired
    private ApprovalDao approvalDao;

    public Approval addApproval(Approval approval){
        return approvalDao.create(approval);
    }

    public void updateApproval(Approval approval){
            approvalDao.update(approval);
    }

    public void deleteApproval(String aid){
        approvalDao.delete(aid);
    }

    public Approval getById( String aid){
       return approvalDao.findOne(aid);
    }

    public List<Approval> getAll(){
       return approvalDao.findAll();
    }

    public Pagination<Approval> getPage(Integer pageNum,Integer pageSize){
        PageRequest pageRequest = new PageRequest(pageNum,pageSize);
        return approvalDao.getPage(pageRequest);
    }
}

