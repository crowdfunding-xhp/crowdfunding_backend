package com.xhp.crowdfunding_backend.controller;
import com.xhp.crowdfunding_backend.common.Pagination;
import com.xhp.crowdfunding_backend.common.Pagination;
import com.xhp.crowdfunding_backend.common.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

import com.xhp.crowdfunding_backend.entity.Comment;
import com.xhp.crowdfunding_backend.service.CommentService;


/**
 * 
 *
 * @author yuchu
 * @email 
 * @date 2018-05-03 10:00:35
 */
@RestController
@RequestMapping("comment")
@Api("comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "获取所有comment")
    @GetMapping
    public ServerResponse<List<Comment>> getAll() {
        try {
            return ServerResponse.createBySuccess(commentService.getAll());
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "分页获取comment")
    @GetMapping("/page")
    public ServerResponse<Pagination<Comment>> getPage(@RequestParam(name = "pageNum") Integer pageNum, @RequestParam(name = "pageSize") Integer pageSize) {
        try {
            return ServerResponse.createBySuccess(commentService.getPage(pageNum, pageSize));
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "更新comment")
    @PutMapping
    public ServerResponse updateComment(@RequestBody Comment comment) {
        try {
            commentService.updateComment(comment);
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "添加comment")
    @PostMapping
    public ServerResponse<Comment> addComment(@RequestBody Comment comment) {
        try {
            return ServerResponse.createBySuccess(commentService.addComment(comment));
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "根据cid获取comment")
    @GetMapping("/{cid}")
    public ServerResponse<Comment> getComment(@PathVariable String cid) {
        try {
            return ServerResponse.createBySuccess(commentService.getById(cid));
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "根据cid删除comment")
    @DeleteMapping
    public ServerResponse deleteComment(@RequestParam(name = "cid") String cid) {
        try {
            commentService.deleteComment(cid);
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

}
