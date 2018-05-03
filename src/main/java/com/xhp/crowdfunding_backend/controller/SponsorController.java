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

import com.xhp.crowdfunding_backend.entity.Sponsor;
import com.xhp.crowdfunding_backend.service.SponsorService;


/**
 * 
 *
 * @author yuchu
 * @email 
 * @date 2018-05-03 10:00:35
 */
@RestController
@RequestMapping("sponsor")
@Api("sponsor")
public class SponsorController {
    @Autowired
    private SponsorService sponsorService;

    @ApiOperation(value = "获取所有sponsor")
    @GetMapping
    public ServerResponse<List<Sponsor>> getAll() {
        try {
            return ServerResponse.createBySuccess(sponsorService.getAll());
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "分页获取sponsor")
    @GetMapping("/page")
    public ServerResponse<Pagination<Sponsor>> getPage(@RequestParam(name = "pageNum") Integer pageNum, @RequestParam(name = "pageSize") Integer pageSize) {
        try {
            return ServerResponse.createBySuccess(sponsorService.getPage(pageNum, pageSize));
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "更新sponsor")
    @PutMapping
    public ServerResponse updateSponsor(@RequestBody Sponsor sponsor) {
        try {
            sponsorService.updateSponsor(sponsor);
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "添加sponsor")
    @PostMapping
    public ServerResponse<Sponsor> addSponsor(@RequestBody Sponsor sponsor) {
        try {
            return ServerResponse.createBySuccess(sponsorService.addSponsor(sponsor));
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "根据sid获取sponsor")
    @GetMapping("/{sid}")
    public ServerResponse<Sponsor> getSponsor(@PathVariable String sid) {
        try {
            return ServerResponse.createBySuccess(sponsorService.getById(sid));
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

    @ApiOperation(value = "根据sid删除sponsor")
    @DeleteMapping
    public ServerResponse deleteSponsor(@RequestParam(name = "sid") String sid) {
        try {
            sponsorService.deleteSponsor(sid);
            return ServerResponse.createBySuccess();
        } catch (Exception e) {
            return ServerResponse.createByErrorMessage("未知错误");
        }
    }

}
