package ohtu;

import java.util.HashMap;
import java.util.Map;

public class TennisGame {
    
    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;
    
    private Map<Integer,String> scoreMap = new HashMap<>();

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        scoreMap.put(0, "Love");
        scoreMap.put(1, "Fifteen");
        scoreMap.put(2, "Thirty");
        scoreMap.put(3, "Forty");
    }

    public void wonPoint(String playerName) {
        if ("player1".equals(playerName))
            m_score1 += 1;
        else
            m_score2 += 1;
    }

    public String getScore() {
        if (m_score1==m_score2)
        {
            return tied();
        }
        else if (m_score1>=4 || m_score2>=4)
        {
            return overFourPoints();
        }
        else
        {
            return loveToForty();
        }
    }

    private String loveToForty() {
        return getPointsToText(m_score1) + "-" + getPointsToText(m_score2);
    }

    private String overFourPoints() {       
        int pointDifference = Math.abs(m_score1-m_score2);
        if (pointDifference < 2){
            return determineAdvantage();
        } else {
            return determineWinner();
        }
    }
    
    private String determineAdvantage(){
        if(m_score1 > m_score2){
            return "Advantage player1";
        } else {
            return "Advantage player2";            
        }
    }
    
    private String determineWinner(){
        if(m_score1 > m_score2){
            return "Win for player1";
        } else {
            return "Win for player2";            
        } 
    }

    private String tied() {
        if(m_score1 < 4){
            return getPointsToText(m_score1) + "-All";
        } else {
            return "Deuce";
        }
    }
    
    private String getPointsToText(int points){
        return scoreMap.get(points);
    } 
}