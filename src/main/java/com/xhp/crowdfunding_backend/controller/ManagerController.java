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

import com.xhp.crowdfunding_backend.entity.Manager;
import com.xhp.crowdfunding_backend.service.ManagerService;


/**
 * 
 *
 * @author yuchu
 * @email 
 * @date 2018-05-03 10:00:35
 */
@RestController
@RequestMapping("manager")
@Api("manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @ApiOperation(value = "获取所有manager")
    @GetMapping
    public ServerResponse<List<Manager>> getAll() {
        try {
            return ServerResponse.createBySuccess(managerService.getAll());
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "分页获取manager")
    @GetMapping("/page")
    public ServerResponse<Pagination<Manager>> getPage(@RequestParam(name = "pageNum") Integer pageNum, @RequestParam(name = "pageSize") Integer pageSize) {
        try {
            return ServerResponse.createBySuccess(managerService.getPage(pageNum, pageSize));
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "更新manager")
    @PutMapping
    public ServerResponse updateManager(@RequestBody Manager manager) {
        try {
            managerService.updateManager(manager);
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "添加manager")
    @PostMapping
    public ServerResponse<Manager> addManager(@RequestBody Manager manager) {
        try {
            return ServerResponse.createBySuccess(managerService.addManager(manager));
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "根据mid获取manager")
    @GetMapping("/{mid}")
    public ServerResponse<Manager> getManager(@PathVariable Integer mid) {
        try {
            return ServerResponse.createBySuccess(managerService.getById(mid));
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "根据mid删除manager")
    @DeleteMapping
    public ServerResponse deleteManager(@RequestParam(name = "mid") Integer mid) {
        try {
            managerService.deleteManager(mid);
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

}
