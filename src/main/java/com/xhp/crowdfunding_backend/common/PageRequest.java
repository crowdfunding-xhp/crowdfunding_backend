package com.xhp.crowdfunding_backend.common;
import lombok.Data;

/**
 * 分页请求参数类
 *
 * @author yuchu
 * @email 
 * @date 2018-05-03 10:00:35
 */
@Data
public class PageRequest{

    private static final int NUMBERS_PER_PAGE = 10;

    private int pageNum;

    private int pageSize;

    public PageRequest() {
        this.pageNum = 1;
        this.pageSize = NUMBERS_PER_PAGE;
    }

    public PageRequest(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
}

