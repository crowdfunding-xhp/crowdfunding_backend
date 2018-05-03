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

import com.xhp.crowdfunding_backend.entity.Projectclassification;
import com.xhp.crowdfunding_backend.service.ProjectclassificationService;


/**
 * 
 *
 * @author yuchu
 * @email 
 * @date 2018-05-03 10:00:35
 */
@RestController
@RequestMapping("projectclassification")
@Api("projectclassification")
public class ProjectclassificationController {
    @Autowired
    private ProjectclassificationService projectclassificationService;

    @ApiOperation(value = "获取所有projectclassification")
    @GetMapping
    public ServerResponse<List<Projectclassification>> getAll() {
        try {
            return ServerResponse.createBySuccess(projectclassificationService.getAll());
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "分页获取projectclassification")
    @GetMapping("/page")
    public ServerResponse<Pagination<Projectclassification>> getPage(@RequestParam(name = "pageNum") Integer pageNum, @RequestParam(name = "pageSize") Integer pageSize) {
        try {
            return ServerResponse.createBySuccess(projectclassificationService.getPage(pageNum, pageSize));
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "更新projectclassification")
    @PutMapping
    public ServerResponse updateProjectclassification(@RequestBody Projectclassification projectclassification) {
        try {
            projectclassificationService.updateProjectclassification(projectclassification);
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "添加projectclassification")
    @PostMapping
    public ServerResponse<Projectclassification> addProjectclassification(@RequestBody Projectclassification projectclassification) {
        try {
            return ServerResponse.createBySuccess(projectclassificationService.addProjectclassification(projectclassification));
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "根据pcid获取projectclassification")
    @GetMapping("/{pcid}")
    public ServerResponse<Projectclassification> getProjectclassification(@PathVariable String pcid) {
        try {
            return ServerResponse.createBySuccess(projectclassificationService.getById(pcid));
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "根据pcid删除projectclassification")
    @DeleteMapping
    public ServerResponse deleteProjectclassification(@RequestParam(name = "pcid") String pcid) {
        try {
            projectclassificationService.deleteProjectclassification(pcid);
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

}
