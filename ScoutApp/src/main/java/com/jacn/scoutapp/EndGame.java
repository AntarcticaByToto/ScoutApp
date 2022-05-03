/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jacn.scoutapp;

/**
 *
 * @author bjtex
 */
public class EndGame {
    
    private int endGameScore;
    
    private int climbBehavior;

    public EndGame(int climbBehavior) {
        this.climbBehavior = climbBehavior;
        
        switch (climbBehavior) {
            case 4:
                this.endGameScore = 0;//temp
                break;
            case 3:
                this.endGameScore = 0;//temp
                break;
            case 2:
                this.endGameScore = 0;//temp
                break;
            default:
                this.endGameScore = 0;
                break;
        }
        
    }

    public int getScore() {
        return endGameScore;
    }

    public int getClimbBehavior() {
        return climbBehavior;
    }
}
