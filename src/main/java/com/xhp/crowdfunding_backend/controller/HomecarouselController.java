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

import com.xhp.crowdfunding_backend.entity.Homecarousel;
import com.xhp.crowdfunding_backend.service.HomecarouselService;


/**
 * 
 *
 * @author yuchu
 * @email 
 * @date 2018-05-03 10:00:35
 */
@RestController
@RequestMapping("homecarousel")
@Api("homecarousel")
public class HomecarouselController {
    @Autowired
    private HomecarouselService homecarouselService;

    @ApiOperation(value = "获取所有homecarousel")
    @GetMapping
    public ServerResponse<List<Homecarousel>> getAll() {
        try {
            return ServerResponse.createBySuccess(homecarouselService.getAll());
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "分页获取homecarousel")
    @GetMapping("/page")
    public ServerResponse<Pagination<Homecarousel>> getPage(@RequestParam(name = "pageNum") Integer pageNum, @RequestParam(name = "pageSize") Integer pageSize) {
        try {
            return ServerResponse.createBySuccess(homecarouselService.getPage(pageNum, pageSize));
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "更新homecarousel")
    @PutMapping
    public ServerResponse updateHomecarousel(@RequestBody Homecarousel homecarousel) {
        try {
            homecarouselService.updateHomecarousel(homecarousel);
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "添加homecarousel")
    @PostMapping
    public ServerResponse<Homecarousel> addHomecarousel(@RequestBody Homecarousel homecarousel) {
        try {
            return ServerResponse.createBySuccess(homecarouselService.addHomecarousel(homecarousel));
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "根据hcid获取homecarousel")
    @GetMapping("/{hcid}")
    public ServerResponse<Homecarousel> getHomecarousel(@PathVariable String hcid) {
        try {
            return ServerResponse.createBySuccess(homecarouselService.getById(hcid));
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "根据hcid删除homecarousel")
    @DeleteMapping
    public ServerResponse deleteHomecarousel(@RequestParam(name = "hcid") String hcid) {
        try {
            homecarouselService.deleteHomecarousel(hcid);
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

}
