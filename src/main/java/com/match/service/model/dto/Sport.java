package com.match.service.model.dto;

public enum Sport {

    FOOTBALL(1),
    BASKETBALL(2);

    private int num;

    Sport(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
