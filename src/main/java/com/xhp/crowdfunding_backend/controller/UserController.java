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

import com.xhp.crowdfunding_backend.entity.User;
import com.xhp.crowdfunding_backend.service.UserService;


/**
 * 
 *
 * @author yuchu
 * @email 
 * @date 2018-05-03 10:00:35
 */
@RestController
@RequestMapping("user")
@Api("user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取所有user")
    @GetMapping
    public ServerResponse<List<User>> getAll() {
        try {
            return ServerResponse.createBySuccess(userService.getAll());
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "分页获取user")
    @GetMapping("/page")
    public ServerResponse<Pagination<User>> getPage(@RequestParam(name = "pageNum") Integer pageNum, @RequestParam(name = "pageSize") Integer pageSize) {
        try {
            return ServerResponse.createBySuccess(userService.getPage(pageNum, pageSize));
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "更新user")
    @PutMapping
    public ServerResponse updateUser(@RequestBody User user) {
        try {
            userService.updateUser(user);
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "添加user")
    @PostMapping
    public ServerResponse<User> addUser(@RequestBody User user) {
        try {
            return ServerResponse.createBySuccess(userService.addUser(user));
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "根据uid获取user")
    @GetMapping("/{uid}")
    public ServerResponse<User> getUser(@PathVariable String uid) {
        try {
            return ServerResponse.createBySuccess(userService.getById(uid));
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "根据uid删除user")
    @DeleteMapping
    public ServerResponse deleteUser(@RequestParam(name = "uid") String uid) {
        try {
            userService.deleteUser(uid);
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

}
