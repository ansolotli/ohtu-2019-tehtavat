
package ohtu;

public class Player implements Comparable<Player>{
    private String name;
    private String nationality;
    private String team;
    private int goals;
    private int assists;
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
    
    public void setNationality(String nat) {
        this.nationality = nat;
    }
    
    public String getNationality() {
        return this.nationality;
    }
    
    public void setTeam(String team) {
        this.team = team;
    }
    
    public String getTeam() {
        return this.team;
    }
    
    private void setGoals(int goals) {
        this.goals = goals;
    }
    
    private int getGoals() {
        return this.goals;
    }
    
    private void setAssists(int assists) {
        this.assists = assists;
    }
    
    private int getAssists() {
        return this.assists;
    }
    
    private int getPoints(){
        return this.goals + this.assists;
    }

    @Override
    public String toString() {
        return name + " from " + team + ": " + goals + " + " + assists + " = " + getPoints();
    }

    @Override
    public int compareTo(Player p) {
        return p.getPoints() - this.getPoints();
    }
}
