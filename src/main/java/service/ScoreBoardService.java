package service;

import model.Match;
import org.apache.commons.lang3.StringUtils;
import util.SequenceGenerator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreBoardService {


    private final List<Match> scoreboard;
    private final SequenceGenerator sequenceGenerator;

    public ScoreBoardService() {
        this.scoreboard = new ArrayList<>();
        sequenceGenerator = new SequenceGenerator();
    }

    public Match addNewMatch(String homeTeam, String awayTeam)
    {
        if (StringUtils.isBlank(homeTeam) || StringUtils.isBlank(awayTeam))
        {
            throw new IllegalArgumentException("team names cannot be empty");
        }

        Match newMatch = new Match(sequenceGenerator.generate(), homeTeam,awayTeam);

        scoreboard.add(newMatch);
        return newMatch;
    }

    public void finishMatch(int matchId)
    {
        if(!scoreboard.removeIf(x-> x.getId() == matchId))
        {
            throw new IllegalArgumentException("Could not complete action, match does not exist in the scoreboard");
        }
    }

    public Match updateScore(int matchId, int homeTeamScore, int awayTeamScore)
    {
        if(homeTeamScore < 0 || awayTeamScore < 0)
            throw new IllegalArgumentException("Team score cannot be updated to negative value");

        for(Match match : scoreboard)
        {
            if (match.getId() == matchId)
            {
                match.updateScore(homeTeamScore,awayTeamScore);
                return match;
            }
        }
        throw new IllegalArgumentException("Could not complete action, match does not exist in the scoreboard");
    }

    public List<String> generateMatchSummary() {

        return scoreboard.stream()
                .sorted(Comparator.comparing(Match::getTotalScore,Comparator.reverseOrder())
                        .thenComparing(Match::getId,Comparator.reverseOrder()))
                .map(Match::generateMatchSummary).collect(Collectors.toList());

    }
}
