package com.blackjack.dto;

public class SimulationRequest {
    private int rounds;
    private double speed; // seconds per hand

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
