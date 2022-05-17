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
    private String teamNumber;
    private DataType dataType;
    private ScoreType scoreType;
    private AllianceColor allianceColor;
    
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
    
    public enum AllianceColor{
        RED,
        BLUE
    }

    public void paintData(TreeMap<Integer, Match> map, String team, DataType dt, ScoreType st, AllianceColor ac) {
        
        this.matches = map;
        this.scoreType = st;
        this.dataType = dt;
        this.teamNumber = team;
        this.allianceColor = ac;
        
        // Call the paint90, paintComponent() int order to update the panel conetent
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (matches == null) {
            return;
        }
        
        int height = g.getClipBounds().height;
        int width = g.getClipBounds().width;
        
        int borderHeight = 50;
        int borderWidth = 50;
        
        int useableHeight = height - (2 * borderHeight);
        int useableWidth = width - (2 * borderWidth);
         
        int yAxisWidth = borderWidth;
        int xAxisHeight = height - borderHeight;

        // Draw X and Y axis
        
        g.drawLine(yAxisWidth, xAxisHeight, yAxisWidth, borderHeight);        // Y axis
        g.drawLine(yAxisWidth, xAxisHeight, width - borderWidth, xAxisHeight);      // X Axis
        
        int highestMatchScore = 0;
        for (int key : matches.keySet()) {
            highestMatchScore = Integer.max(highestMatchScore, matches.get(key).getMatchScore());
        }
        if (highestMatchScore == 0) {
            return;
        }
        
        if (dataType == DataType.ALL) {
            //graph rectangles
            int matchRangeSize = matches.keySet().size(); 
            int rectangleWidth = useableWidth / matchRangeSize;
            int rectangleHeight = useableHeight / highestMatchScore;

            int widthLoss = -1 * ((rectangleWidth * matchRangeSize) - useableWidth);
            int heightLoss = -1 * ((rectangleHeight * matchRangeSize) - useableHeight);

            int currentMatch = 0;
            int previousX = yAxisWidth + (widthLoss / 2);
            int previousY = (xAxisHeight - (heightLoss / 2)) - ( 7 * rectangleHeight);;
            for (int i=0; i < matchRangeSize; i++) {
                int score = matches.get(matches.keySet().toArray()[i]).getMatchScore();

                int colorSteps = 255 / matchRangeSize;
                Color currentColor = new Color(colorSteps * i, 0, 0);
                g.setColor(currentColor);

                int x = yAxisWidth + (widthLoss / 2) + (i * rectangleWidth);
                int y = (xAxisHeight - (heightLoss / 2)) - ((score + 7) * rectangleHeight);
                int inWidth = rectangleWidth;
                int inHeight = rectangleHeight * score;
                g.fillRect(x, y, inWidth, inHeight);

                g.setColor(Color.BLUE);
                if (i % 3 == 0) {
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

            }
        }
        else if (dataType == DataType.ALLIANCE) {
            
        }
        else {
            
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
