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

import com.xhp.crowdfunding_backend.entity.Homedisplay;
import com.xhp.crowdfunding_backend.service.HomedisplayService;


/**
 * 
 *
 * @author yuchu
 * @email 
 * @date 2018-05-03 10:00:35
 */
@RestController
@RequestMapping("homedisplay")
@Api("homedisplay")
public class HomedisplayController {
    @Autowired
    private HomedisplayService homedisplayService;

    @ApiOperation(value = "获取所有homedisplay")
    @GetMapping
    public ServerResponse<List<Homedisplay>> getAll() {
        try {
            return ServerResponse.createBySuccess(homedisplayService.getAll());
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "分页获取homedisplay")
    @GetMapping("/page")
    public ServerResponse<Pagination<Homedisplay>> getPage(@RequestParam(name = "pageNum") Integer pageNum, @RequestParam(name = "pageSize") Integer pageSize) {
        try {
            return ServerResponse.createBySuccess(homedisplayService.getPage(pageNum, pageSize));
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "更新homedisplay")
    @PutMapping
    public ServerResponse updateHomedisplay(@RequestBody Homedisplay homedisplay) {
        try {
            homedisplayService.updateHomedisplay(homedisplay);
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "添加homedisplay")
    @PostMapping
    public ServerResponse<Homedisplay> addHomedisplay(@RequestBody Homedisplay homedisplay) {
        try {
            return ServerResponse.createBySuccess(homedisplayService.addHomedisplay(homedisplay));
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "根据pid获取homedisplay")
    @GetMapping("/{pid}")
    public ServerResponse<Homedisplay> getHomedisplay(@PathVariable String pid) {
        try {
            return ServerResponse.createBySuccess(homedisplayService.getById(pid));
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "根据pid删除homedisplay")
    @DeleteMapping
    public ServerResponse deleteHomedisplay(@RequestParam(name = "pid") String pid) {
        try {
            homedisplayService.deleteHomedisplay(pid);
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

}
