package com.xhp.crowdfunding_backend.dao;
import com.xhp.crowdfunding_backend.common.Pagination;
import com.xhp.crowdfunding_backend.common.PageRequest;
import com.xhp.crowdfunding_backend.entity.Comment;
import java.util.List;
/**
 * 
 * 
 * @author yuchu
 * @email 
 * @date 2018-05-03 10:00:35
 */

public interface CommentDao{

    Comment findOne(String cid);

    List<Comment> findAll();

    Pagination<Comment> getPage(PageRequest pageRequest);

    Comment create(Comment comment);

    void update(Comment comment);

    boolean delete(String cid);
}
