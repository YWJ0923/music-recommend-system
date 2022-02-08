package com.ywj.music_recommend_system.back.util;

import java.util.List;

/**
 * @author ywj
 * @date 2021/11/09
 */
public class PageResult {
    private int currPage;
    private int totalPage;
    private int totalCount;
    private List<?> list;

    public PageResult(int currPage, int totalCount, List<?> list) {
        this.currPage = currPage;
        this.totalPage = (int)Math.ceil(totalCount * 1.0 / currPage);
        this.totalCount = totalCount;
        this.list = list;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
