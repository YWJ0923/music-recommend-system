package com.ywj.music_recommend_system.back.util;

import java.util.HashMap;

/**
 * @author ywj
 * @date 2021/11/09
 */
public class PageRequest extends HashMap<String, Object> {
    private int page;
    private int limit;
    private int offset;

    @Override
    public String toString() {
        return "PageRequest{" +
                "page=" + page +
                ", limit=" + limit +
                ", offset=" + offset +
                '}';
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public PageRequest(int page, int limit) {
        this.page = page;
        this.limit = limit;
        this.offset = (page - 1) * limit;
        this.put("page", page);
        this.put("limit", limit);
        this.put("offset", offset);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
