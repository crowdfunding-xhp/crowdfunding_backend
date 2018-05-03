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

import com.xhp.crowdfunding_backend.entity.Order;
import com.xhp.crowdfunding_backend.service.OrderService;


/**
 * 
 *
 * @author yuchu
 * @email 
 * @date 2018-05-03 10:00:35
 */
@RestController
@RequestMapping("order")
@Api("order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "获取所有order")
    @GetMapping
    public ServerResponse<List<Order>> getAll() {
        try {
            return ServerResponse.createBySuccess(orderService.getAll());
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "分页获取order")
    @GetMapping("/page")
    public ServerResponse<Pagination<Order>> getPage(@RequestParam(name = "pageNum") Integer pageNum, @RequestParam(name = "pageSize") Integer pageSize) {
        try {
            return ServerResponse.createBySuccess(orderService.getPage(pageNum, pageSize));
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "更新order")
    @PutMapping
    public ServerResponse updateOrder(@RequestBody Order order) {
        try {
            orderService.updateOrder(order);
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "添加order")
    @PostMapping
    public ServerResponse<Order> addOrder(@RequestBody Order order) {
        try {
            return ServerResponse.createBySuccess(orderService.addOrder(order));
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "根据oid获取order")
    @GetMapping("/{oid}")
    public ServerResponse<Order> getOrder(@PathVariable String oid) {
        try {
            return ServerResponse.createBySuccess(orderService.getById(oid));
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "根据oid删除order")
    @DeleteMapping
    public ServerResponse deleteOrder(@RequestParam(name = "oid") String oid) {
        try {
            orderService.deleteOrder(oid);
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

}
