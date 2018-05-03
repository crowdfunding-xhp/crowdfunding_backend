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

import com.xhp.crowdfunding_backend.entity.Project;
import com.xhp.crowdfunding_backend.service.ProjectService;


/**
 * 
 *
 * @author yuchu
 * @email 
 * @date 2018-05-03 10:00:35
 */
@RestController
@RequestMapping("project")
@Api("project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @ApiOperation(value = "获取所有project")
    @GetMapping
    public ServerResponse<List<Project>> getAll() {
        try {
            return ServerResponse.createBySuccess(projectService.getAll());
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "分页获取project")
    @GetMapping("/page")
    public ServerResponse<Pagination<Project>> getPage(@RequestParam(name = "pageNum") Integer pageNum, @RequestParam(name = "pageSize") Integer pageSize) {
        try {
            return ServerResponse.createBySuccess(projectService.getPage(pageNum, pageSize));
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "更新project")
    @PutMapping
    public ServerResponse updateProject(@RequestBody Project project) {
        try {
            projectService.updateProject(project);
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "添加project")
    @PostMapping
    public ServerResponse<Project> addProject(@RequestBody Project project) {
        try {
            return ServerResponse.createBySuccess(projectService.addProject(project));
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "根据pid获取project")
    @GetMapping("/{pid}")
    public ServerResponse<Project> getProject(@PathVariable String pid) {
        try {
            return ServerResponse.createBySuccess(projectService.getById(pid));
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "根据pid删除project")
    @DeleteMapping
    public ServerResponse deleteProject(@RequestParam(name = "pid") String pid) {
        try {
            projectService.deleteProject(pid);
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

}
