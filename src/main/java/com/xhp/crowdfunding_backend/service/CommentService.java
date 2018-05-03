package com.xhp.crowdfunding_backend.service;
import com.xhp.crowdfunding_backend.common.Pagination;
import com.xhp.crowdfunding_backend.common.PageRequest;
import com.xhp.crowdfunding_backend.entity.Comment;
import com.xhp.crowdfunding_backend.dao.CommentDao;
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
public class CommentService{
    @Autowired
    private CommentDao commentDao;

    public Comment addComment(Comment comment){
        return commentDao.create(comment);
    }

    public void updateComment(Comment comment){
            commentDao.update(comment);
    }

    public void deleteComment(String cid){
        commentDao.delete(cid);
    }

    public Comment getById( String cid){
       return commentDao.findOne(cid);
    }

    public List<Comment> getAll(){
       return commentDao.findAll();
    }

    public Pagination<Comment> getPage(Integer pageNum,Integer pageSize){
        PageRequest pageRequest = new PageRequest(pageNum,pageSize);
        return commentDao.getPage(pageRequest);
    }
}

