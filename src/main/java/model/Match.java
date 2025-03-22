package model;

public class Match {

    int id;
    String homeTeam;
    String awayTeam;
    int homeTeamScore;
    int awayTeamScore;



    public Match(int id, String homeTeam, String awayTeam) {

        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamScore = 0;
        this.awayTeamScore = 0;
    }

    public int getId() { return id;}

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void updateScore(int homeTeamScore, int awayTeamScore) {
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
    }
}
