/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scoutapp;

import java.io.Serializable;
import java.util.TreeMap;

/**
 *
 * @author cjnel
 */
public class matchModel implements Serializable {
    private TreeMap<Integer, Match> matches;

    public matchModel(TreeMap<Integer, Match> matches) {
        this.matches = matches;
    }

    public TreeMap<Integer, Match> getMatches() {
        return matches;
    }
    
}
