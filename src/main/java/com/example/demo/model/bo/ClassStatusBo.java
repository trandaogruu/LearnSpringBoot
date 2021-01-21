package com.example.demo.model.bo;

public class ClassStatusBo {

    private int idClass;
    private boolean status;
    private int page;
    private int limit;

    public ClassStatusBo(int idClass, boolean status, int page, int limit) {
        this.idClass = idClass;
        this.status = status;
        this.page = page;
        this.limit = limit;
    }

    public int getIdClass() {
        return idClass;
    }

    public void setIdClass(int idClass) {
        this.idClass = idClass;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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
