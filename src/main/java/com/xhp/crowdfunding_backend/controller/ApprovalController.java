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

import com.xhp.crowdfunding_backend.entity.Approval;
import com.xhp.crowdfunding_backend.service.ApprovalService;


/**
 * 
 *
 * @author yuchu
 * @email 
 * @date 2018-05-03 10:00:35
 */
@RestController
@RequestMapping("approval")
@Api("approval")
public class ApprovalController {
    @Autowired
    private ApprovalService approvalService;

    @ApiOperation(value = "获取所有approval")
    @GetMapping
    public ServerResponse<List<Approval>> getAll() {
        try {
            return ServerResponse.createBySuccess(approvalService.getAll());
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "分页获取approval")
    @GetMapping("/page")
    public ServerResponse<Pagination<Approval>> getPage(@RequestParam(name = "pageNum") Integer pageNum, @RequestParam(name = "pageSize") Integer pageSize) {
        try {
            return ServerResponse.createBySuccess(approvalService.getPage(pageNum, pageSize));
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "更新approval")
    @PutMapping
    public ServerResponse updateApproval(@RequestBody Approval approval) {
        try {
            approvalService.updateApproval(approval);
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "添加approval")
    @PostMapping
    public ServerResponse<Approval> addApproval(@RequestBody Approval approval) {
        try {
            return ServerResponse.createBySuccess(approvalService.addApproval(approval));
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "根据aid获取approval")
    @GetMapping("/{aid}")
    public ServerResponse<Approval> getApproval(@PathVariable String aid) {
        try {
            return ServerResponse.createBySuccess(approvalService.getById(aid));
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "根据aid删除approval")
    @DeleteMapping
    public ServerResponse deleteApproval(@RequestParam(name = "aid") String aid) {
        try {
            approvalService.deleteApproval(aid);
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

}
