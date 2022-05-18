/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scoutapp;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.TreeMap;
import javax.swing.JPanel;

/**
 *
 * @author bjtex
 */
public class GraphicsPanelScore extends JPanel {
    
    private TreeMap<Integer, Match> matches;
    private int teamNumber;
    private DataType dataType;
    private ScoreType scoreType;
    private Match.AllianceColor allianceColor;
    
    public enum DataType{
        ALLIANCE,
        TEAM,
        ALL;
    };
    
    public enum ScoreType{
        ALL_POINTS,
        END_GAME,
        AUTO,
        TELEOP
    }

    public void paintData(TreeMap<Integer, Match> map, String team, DataType dt, ScoreType st, Match.AllianceColor ac) {
        
        this.matches = map;
        this.scoreType = st;
        this.dataType = dt;
        try {
            this.teamNumber = Integer.parseInt(team);
        }
        catch (NumberFormatException e) {
            this.teamNumber = 0;
        }
        
        if (dt == DataType.ALL || dt == DataType.TEAM) {
            this.allianceColor = Match.AllianceColor.ALL;
        }
        else {
            this.allianceColor = ac;
        }
        
        // Call the paint90, paintComponent() int order to update the panel conetent
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (matches == null) {
            return;
        }
        
        TreeMap<Integer, Robot> robots = new TreeMap<Integer, Robot>();
        
        if (dataType == DataType.TEAM) {
            for (int key : matches.keySet()) {
                if (matches.get(key).getBlue1() != null && matches.get(key).getBlue1().getTeamNumber() == teamNumber) {
                    robots.put(key, matches.get(key).getBlue1());
                }
                else if (matches.get(key).getBlue2() != null && matches.get(key).getBlue2().getTeamNumber() == teamNumber) {
                    robots.put(key, matches.get(key).getBlue2());
                }
                else if (matches.get(key).getBlue3() != null && matches.get(key).getBlue3().getTeamNumber() == teamNumber) {
                    robots.put(key, matches.get(key).getBlue3());
                }
                else if (matches.get(key).getRed1() != null && matches.get(key).getRed1().getTeamNumber() == teamNumber) {
                    robots.put(key, matches.get(key).getRed1());
                }
                else if (matches.get(key).getRed2() != null && matches.get(key).getRed2().getTeamNumber() == teamNumber) {
                    robots.put(key, matches.get(key).getRed2());
                }
                else if (matches.get(key).getRed3() != null && matches.get(key).getRed3().getTeamNumber() == teamNumber) {
                    robots.put(key, matches.get(key).getRed3());
                }
            }
        }
        
        int height = g.getClipBounds().height;
        int width = g.getClipBounds().width;
        
        int borderHeight = 50;
        int borderWidth = 50;
        
        int usableHeight = height - (2 * borderHeight);
        int usableWidth = width - (2 * borderWidth);
         
        int yAxisWidth = borderWidth;
        int xAxisHeight = height - borderHeight;

        // Draw X and Y axis
        
        g.drawLine(yAxisWidth, xAxisHeight, yAxisWidth, borderHeight);        // Y axis
        g.drawLine(yAxisWidth, xAxisHeight, width - borderWidth, xAxisHeight);      // X Axis
        
        int highestMatchScore = 0;
        if (dataType == DataType.ALL || dataType == DataType.ALLIANCE) {
            for (int key : matches.keySet()) {
                if (scoreType == ScoreType.ALL_POINTS) {
                        highestMatchScore = Integer.max(highestMatchScore, matches.get(key).getMatchScore(allianceColor));
                }
                else if (scoreType == ScoreType.AUTO) {
                    highestMatchScore = Integer.max(highestMatchScore, matches.get(key).getMatchAutoScore(allianceColor));
                }
                else if (scoreType == ScoreType.TELEOP) {
                    highestMatchScore = Integer.max(highestMatchScore, matches.get(key).getMatchTeleOpScore(allianceColor));
                }
                else {
                    highestMatchScore = Integer.max(highestMatchScore, matches.get(key).getMatchEndGameScore(allianceColor));
                }
            }
        }
        else {
            for (int key : robots.keySet()) {
                if (scoreType == ScoreType.ALL_POINTS) {
                        highestMatchScore = Integer.max(highestMatchScore, robots.get(key).getScore());
                }
                else if (scoreType == ScoreType.AUTO) {
                    highestMatchScore = Integer.max(highestMatchScore, robots.get(key).getAuto().getScore());
                }
                else if (scoreType == ScoreType.TELEOP) {
                    highestMatchScore = Integer.max(highestMatchScore, robots.get(key).getTeleop().getScore());
                }
                else {
                    highestMatchScore = Integer.max(highestMatchScore, robots.get(key).getEndgame().getScore());
                }
            }
        }
        
        if (highestMatchScore == 0) {
            return;
        }
 
        //graph rectangles
        int matchRangeSize;
        if (dataType == DataType.ALL || dataType == DataType.ALLIANCE) {
            matchRangeSize = matches.keySet().size();
        } 
        else {
            matchRangeSize = robots.keySet().size();
        }
        
        int rectangleWidth = (int)((double)usableWidth / matchRangeSize);
        int rectangleHeight = (int)((double)usableHeight / highestMatchScore);

        int widthLoss = -1 * ((rectangleWidth * matchRangeSize) - usableWidth);
        int heightLoss = -1 * ((rectangleHeight * matchRangeSize) - usableHeight);

        int currentMatch = 0;
        int previousX = yAxisWidth;
        int previousY = xAxisHeight;
        for (int i=0; i < matchRangeSize; i++) {
            int score;
            if (dataType == DataType.ALL || dataType == DataType.ALLIANCE) {
               if (scoreType == ScoreType.ALL_POINTS) {
                    score = matches.get(matches.keySet().toArray()[i]).getMatchScore(allianceColor);
                }
                else if (scoreType == ScoreType.AUTO) {
                    score = matches.get(matches.keySet().toArray()[i]).getMatchAutoScore(allianceColor);
                }
                else if (scoreType == ScoreType.TELEOP) {
                    score = matches.get(matches.keySet().toArray()[i]).getMatchTeleOpScore(allianceColor);
                }
                else {
                    score = matches.get(matches.keySet().toArray()[i]).getMatchEndGameScore(allianceColor);
                } 
            }
            else {
                if (scoreType == ScoreType.ALL_POINTS) {
                    score = robots.get(robots.keySet().toArray()[i]).getScore();
                }
                else if (scoreType == ScoreType.AUTO) {
                    score = robots.get(robots.keySet().toArray()[i]).getAuto().getScore();
                }
                else if (scoreType == ScoreType.TELEOP) {
                    score = robots.get(robots.keySet().toArray()[i]).getTeleop().getScore();;
                }
                else {
                    score = robots.get(robots.keySet().toArray()[i]).getEndgame().getScore();
                }
            }
            

            int colorSteps = 255 / matchRangeSize;
            Color currentColor;
            if (allianceColor == Match.AllianceColor.ALL){
               currentColor = new Color(colorSteps * i, 0, colorSteps * i); 
            }
            else if (allianceColor == Match.AllianceColor.RED) {
                currentColor = new Color(colorSteps * i, 0, 0);
            }
            else {
                currentColor = new Color(0, 0, colorSteps * i);
            }   
            g.setColor(currentColor);

            int x;
            if (dataType == DataType.ALL || dataType == DataType.ALLIANCE) {
                x = yAxisWidth + (int)(((double)i / matches.keySet().size()) * usableWidth);
            }
            else {
                x = yAxisWidth + (int)(((double)i / robots.keySet().size()) * usableWidth);
            }
            int y = xAxisHeight - (int)(((double)score / highestMatchScore) * usableHeight);
            int inWidth = rectangleWidth;
            int inHeight = (int)(((double)score / highestMatchScore) * usableHeight);
            g.fillRect(x, y, inWidth, inHeight);

            if (allianceColor == Match.AllianceColor.ALL){
               g.setColor(Color.RED); 
            }
            else if (allianceColor == Match.AllianceColor.RED) {
                g.setColor(Color.BLUE);
            }
            else {
                g.setColor(Color.RED);
            }
            
            int divider;
            if (dataType == DataType.ALL || dataType == DataType.ALLIANCE) {
                divider = 3;
            }
            else {
                divider = 1;
            }
            
            if (i % divider == 0) {
                int inX1 = previousX;
                int inY1 = previousY;
                int inX2 = x;
                int inY2 = y + ((previousY - y) / 2);
                g.drawLine(inX1, inY1, inX2, inY2);
                g.drawLine(inX1, inY1 - 1, inX2, inY2 - 1);
                g.drawLine(inX1, inY1 - 2, inX2, inY2 - 2);
                g.drawLine(inX1, inY1 - 3, inX2, inY2 - 3);
                previousX = inX2;
                previousY = inY2;
            }

           // add axis titles
           Color black = new Color(0, 0, 0);
           g.setColor(black);

           int xAxisTitleWidth = g.getFontMetrics().stringWidth("Match");
           int titleHeight = g.getFontMetrics().getHeight();

           //xAxis
           g.drawString("Match", ((usableWidth - xAxisTitleWidth) / 2) + yAxisWidth, xAxisHeight + ((borderHeight - titleHeight) / 2 ));

           String startMatch = Integer.toString(matches.firstKey());
           g.drawString(startMatch, yAxisWidth - (g.getFontMetrics().stringWidth(startMatch) / 2), xAxisHeight + titleHeight);

           String endMatch = Integer.toString(matches.lastKey());
           g.drawString(endMatch, yAxisWidth + usableWidth - (g.getFontMetrics().stringWidth(endMatch) / 2), xAxisHeight + titleHeight);

           g.drawString(Integer.toString(highestMatchScore), yAxisWidth - g.getFontMetrics().stringWidth(Integer.toString(highestMatchScore)) - 4, borderHeight + (titleHeight / 2));

        }
        
             //graph rectangles
//        int size = cases.keySet().size(); 
//        int dateRangeSize = caseDays.get(caseDays.lastKey()) - caseDays.get(caseDays.firstKey());
//        int rectangleWidth = useableWidth / dateRangeSize;
//        int rectangleHeight = useableHeight / caseDays.keySet().size();
//        
//        int widthLoss = -1 * ((rectangleWidth * dateRangeSize) - useableWidth);
//        int heightLoss = -1 * ((rectangleHeight * dateRangeSize) - useableHeight);
//        
//
//        int currentDay = 0;
//        int previousX = yAxisWidth + (widthLoss / 2);
//        int previousY = (xAxisHeight - (heightLoss / 2)) - ( 7 * rectangleHeight);;
//        for (int i=0; i < dateRangeSize; i++) {
//            int count = 0;
//            currentDay = caseDays.get(caseDays.firstKey()) + i;
//            for (Integer key : caseDays.keySet()) {
//                if (caseDays.get(key) <= currentDay) {
//                    count++;
//                }
//            }
//            
//            int colorSteps = 255 / dateRangeSize;
//            Color currentColor = new Color(colorSteps * i, 0, 0);
//            g.setColor(currentColor);
//            
//            int x = yAxisWidth + (widthLoss / 2) + (i * rectangleWidth);
//            int y = (xAxisHeight - (heightLoss / 2)) - ((count + 7) * rectangleHeight);
//            int inWidth = rectangleWidth;
//            int inHeight = rectangleHeight * count;
//            g.fillRect(x, y, inWidth, inHeight);
//            
//            g.setColor(Color.BLUE);
//            if (i % 3 == 0) {
//                int inX1 = previousX;
//                int inY1 = previousY;
//                int inX2 = x;
//                int inY2 = y + ((previousY - y) / 2);
//                g.drawLine(inX1, inY1, inX2, inY2);
//                g.drawLine(inX1, inY1 - 1, inX2, inY2 - 1);
//                g.drawLine(inX1, inY1 - 2, inX2, inY2 - 2);
//                g.drawLine(inX1, inY1 - 3, inX2, inY2 - 3);
//                previousX = inX2;
//                previousY = inY2;
//            }
//            
//        }
//        
//         // add axis titles
//        Color black = new Color(0, 0, 0);
//        g.setColor(black);
//         
//        int xAxisTitleWidth = g.getFontMetrics().stringWidth("Total Cases Curve");
//        int titleHeight = g.getFontMetrics().getHeight();
//        
//        //xAxis
//        g.drawString("Total Cases Curve", ((useableWidth - xAxisTitleWidth) / 2) + yAxisWidth, xAxisHeight + ((borderHeight - titleHeight) / 2 ));
//        
//        String startDate = "" + (cases.get(cases.firstKey()).getReportDate().get(Calendar.MONTH) + 1) + "/" + cases.get(cases.firstKey()).getReportDate().get(Calendar.DAY_OF_MONTH) + "/"  + cases.get(cases.firstKey()).getReportDate().get(Calendar.YEAR);
//        g.drawString(startDate, yAxisWidth - (g.getFontMetrics().stringWidth(startDate) / 2) + (widthLoss / 2), xAxisHeight + titleHeight);
//        
//        String endDate = "" + (cases.get(cases.lastKey()).getReportDate().get(Calendar.MONTH) + 1) + "/" + cases.get(cases.lastKey()).getReportDate().get(Calendar.DAY_OF_MONTH) + "/"  + cases.get(cases.lastKey()).getReportDate().get(Calendar.YEAR);
//        g.drawString(endDate, yAxisWidth + useableWidth - (g.getFontMetrics().stringWidth(endDate) / 2) - (widthLoss / 2), xAxisHeight + titleHeight);
//        
//        g.drawString(Integer.toString(size), yAxisWidth - g.getFontMetrics().stringWidth(Integer.toString(size)) - 4, borderHeight + (titleHeight / 2));
    }
}
