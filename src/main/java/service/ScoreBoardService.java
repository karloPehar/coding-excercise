package service;

import model.Match;
import org.apache.commons.lang3.StringUtils;
import util.SequenceGenerator;

public class ScoreBoardService {


    private final SequenceGenerator sequenceGenerator;

    public ScoreBoardService() {
        sequenceGenerator = new SequenceGenerator();
    }

    public Match addNewMatch(String homeTeam, String awayTeam)
    {
        if (StringUtils.isBlank(homeTeam) || StringUtils.isBlank(awayTeam))
        {
            throw new IllegalArgumentException("team names cannot be empty");
        }

        Match newMatch = new Match(sequenceGenerator.generate(), homeTeam,awayTeam);
        return newMatch;
    }

}
