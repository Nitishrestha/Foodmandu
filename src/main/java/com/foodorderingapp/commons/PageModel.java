package com.foodorderingapp.commons;

/**
 * Created by TOPSHI KREATS on 1/29/2018.
 */

public class PageModel {
    private long count;
    private int firstResult;
    private int maxResult;

    public PageModel(){}

    public PageModel(long count, int firstResult, int maxResult) {
        this.count = count;
        this.firstResult = firstResult;
        this.maxResult = maxResult;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public int getFirstResult() {
        return firstResult;
    }

    public void setFirstResult(int firstResult) {
        this.firstResult = firstResult;
    }

    public int getMaxResult() {
        return maxResult;
    }

    public void setMaxResult(int maxResult) {
        this.maxResult = maxResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PageModel pageModel = (PageModel) o;
        if (count != pageModel.count) return false;
        if (firstResult != pageModel.firstResult) return false;
        return maxResult == pageModel.maxResult;
    }

    @Override
    public int hashCode() {
        int result = (int) (count ^ (count >>> 32));
        result = 31 * result + firstResult;
        result = 31 * result + maxResult;
        return result;
    }
}
