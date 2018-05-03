package com.xhp.crowdfunding_backend.dao;
import com.xhp.crowdfunding_backend.common.Pagination;
import com.xhp.crowdfunding_backend.common.PageRequest;
import com.xhp.crowdfunding_backend.entity.Approval;
import java.util.List;
/**
 * 
 * 
 * @author yuchu
 * @email 
 * @date 2018-05-03 10:00:35
 */

public interface ApprovalDao{

    Approval findOne(String aid);

    List<Approval> findAll();

    Pagination<Approval> getPage(PageRequest pageRequest);

    Approval create(Approval approval);

    void update(Approval approval);

    boolean delete(String aid);
}
