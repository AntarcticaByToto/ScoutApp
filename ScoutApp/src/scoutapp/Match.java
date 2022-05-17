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
public class Match implements Serializable{
    private Robot red1;
    private Robot red2;
    private Robot red3;
    
    private Robot blue1;
    private Robot blue2;
    private Robot blue3;
    
    private int matchNumber;

    public Match(int matchNumber) {
        this.matchNumber = matchNumber;
    }

    public Robot getRed1() {
        return red1;
    }

    public Robot getRed2() {
        return red2;
    }

    public Robot getRed3() {
        return red3;
    }

    public Robot getBlue1() {
        return blue1;
    }

    public Robot getBlue2() {
        return blue2;
    }

    public Robot getBlue3() {
        return blue3;
    }

    public void setRobot(Robot rob) {
        if (rob.getAllianceColor().equals("Blue")) {
            switch(rob.getFieldPlacement()) {
                case 1:
                    blue1 = rob;
                    break;
                case 2:
                    blue2 = rob;
                    break;
                case 3:
                    blue3 = rob;
                    break;
            }
        }
        else {
            switch(rob.getFieldPlacement()) {
                case 1:
                    red1 = rob;
                    break;
                case 2:
                    red2 = rob;
                    break;
                case 3:
                    red3 = rob;
                    break;
            }
        }
        
    }
    
    public int getMatchNumber() {
        return matchNumber;
    }
    
    public int getMatchScore() {
        int score = 0;
        if (red1 != null) {
            score += red1.getScore();
        }
        if (red2 != null) {
            score += red2.getScore();
        }
        if (red3 != null) {
            score += red3.getScore();
        }
        if (blue1 != null) {
            score += blue1.getScore();
        }
        if (blue2 != null) {
            score += blue2.getScore();
        }
        if (blue3 != null) {
            score += blue3.getScore();
        }
        return score; 
    }
}
