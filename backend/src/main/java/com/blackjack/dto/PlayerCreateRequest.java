package com.blackjack.dto;

public class PlayerCreateRequest {
    private String name;
    private double money;
    private String playerType;

    public String getName() {
        return name;
    }

    public double getMoney() {
        return money;
    }

    public String getPlayerType() {
        return playerType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setPlayerType(String playerType) {
        this.playerType = playerType;
    }
}
