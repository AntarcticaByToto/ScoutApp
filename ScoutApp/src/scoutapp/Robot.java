/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scoutapp;

import java.io.Serializable;

/**
 *
 * @author bjtex
 */
public class Robot implements Serializable{
    private int score;
    
    private int matchNumber;
    
    private int fieldPlacement;
    
    private int teamNumber;
    
    private String allianceColor;
    
    private String scoutName;
    
    private String comments;
    
    private Auto auto;
    private TeleOp teleop;
    private EndGame endgame;

    public Robot(int matchNumber, String allianceColor, int fieldPlacement, int teamNumber, String scoutName, String comments, Auto auto, TeleOp teleop, EndGame endgame) {
        this.matchNumber = matchNumber;
        this.fieldPlacement = fieldPlacement;
        this.teamNumber = teamNumber;
        this.scoutName = scoutName;
        this.comments = comments;
        this.auto = auto;
        this.teleop = teleop;
        this.endgame = endgame;
        this.allianceColor = allianceColor;
        
        this.score = auto.getScore() + teleop.getScore() + endgame.getScore();
    }

    public int getScore() {
        return score;
    }

    public int getMatchNumber() {
        return matchNumber;
    }

    public int getFieldPlacement() {
        return fieldPlacement;
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public String getScoutName() {
        return scoutName;
    }

    public String getComments() {
        return comments;
    }
    
    public String getAllianceColor() {
        return allianceColor;
    }

    public Auto getAuto() {
        return auto;
    }

    public TeleOp getTeleop() {
        return teleop;
    }

    public EndGame getEndgame() {
        return endgame;
    }
    
}
