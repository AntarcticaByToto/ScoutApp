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
public class Auto {
    
    private int autoScore;
    
    private int autoUpperShotsMade;
    private int autoUpperShotsAttempted;
    
    private int autoLowerShotsMade;
    private int autoLowerShotsAttempted;
    
    private int autoBehavior;   //1 - DNM   //2 - attempted score  //3 -attempted to picup cargo

    private boolean taxi;
    
    public Auto(int autoUpperShotsMade, int autoUpperShotsAttempted, int autoLowerShotsMade, int autoLowerShotsAttempted, int autoBehavior, boolean taxi) {
        this.autoUpperShotsMade = autoUpperShotsMade;
        this.autoUpperShotsAttempted = autoUpperShotsAttempted;
        this.autoLowerShotsMade = autoLowerShotsMade;
        this.autoLowerShotsAttempted = autoLowerShotsAttempted;
        this.autoBehavior = autoBehavior;
        this.taxi = taxi;
       
        if (taxi) {
            this.autoScore = autoUpperShotsMade * 4 + autoLowerShotsMade * 2 + 0; //taxi pts need adding
        }
        else {
            this.autoScore = autoUpperShotsMade * 4 + autoLowerShotsMade * 2;
        }
    }

    public int getScore() {
        return autoScore;
    }

    public int getUpperShotsMade() {
        return autoUpperShotsMade;
    }

    public int getUpperShotsAttempted() {
        return autoUpperShotsAttempted;
    }

    public int getLowerShotsMade() {
        return autoLowerShotsMade;
    }

    public int getLowerShotsAttempted() {
        return autoLowerShotsAttempted;
    }

    public int getBehavior() {
        return autoBehavior;
    }
    
    public boolean getTaxi() {
        return taxi;
    }
    
    
}
