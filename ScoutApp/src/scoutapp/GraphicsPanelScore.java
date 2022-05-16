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
import javax.swing.JTextField;

/**
 *
 * @author bjtex
 */
public class GraphicsPanelScore extends JPanel {
    
    private TreeMap<Integer, Match> matches;
    private JTextField field;
    private ScoreType scoreType;
    private DataType dataType;
    
    public enum ScoreType{
        ALLIANCE,
        TEAM,
        ALL;
    };
    
    public enum DataType{
        ALL_POINTS,
        END_GAME,
        AUTO,
        TELEOP
    }

    public void paintData(TreeMap<Integer, Match> map, JTextField textField, ScoreType sc, DataType dt) {
        
        this.matches = map;
        this.scoreType = sc;
        this.dataType = dt;
        
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
    }
}
