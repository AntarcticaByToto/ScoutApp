/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scoutapp;

/**
 *
 * @author bjtex
 */
public class TeleOp {
    
    private int teleOpScore;

    private int teleOpUpperShotsMade;
    private int teleOpLowerShotsMade;

    private int teleOpBehavior;

    private double intakePerform;
    private double scoringPerform;
    private double defensivePerform;

    private int cargoBehaviour;

    public TeleOp(int teleOpUpperShotsMade, int teleOpLowerShotsMade, int teleOpBehavior, double intakePerform, double scoringPerform, double defensivePerform, int cargoBehaviour) {
        this.teleOpUpperShotsMade = teleOpUpperShotsMade;
        this.teleOpLowerShotsMade = teleOpLowerShotsMade;
        this.teleOpBehavior = teleOpBehavior;
        this.intakePerform = intakePerform;
        this.scoringPerform = scoringPerform;
        this.defensivePerform = defensivePerform;
        this.cargoBehaviour = cargoBehaviour;
        
        this.teleOpScore = teleOpUpperShotsMade * 2 + teleOpLowerShotsMade;
    }

    public int getScore() {
        return teleOpScore;
    }

    public int getUpperShotsMade() {
        return teleOpUpperShotsMade;
    }

    public int getLowerShotsMade() {
        return teleOpLowerShotsMade;
    }

    public int getBehavior() {
        return teleOpBehavior;
    }

    public double getIntakePerform() {
        return intakePerform;
    }

    public double getScoringPerform() {
        return scoringPerform;
    }

    public double getDefensivePerform() {
        return defensivePerform;
    }

    public int getCargoBehaviour() {
        return cargoBehaviour;
    }
    
    
}
