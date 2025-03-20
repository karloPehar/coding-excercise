package service;

import model.Match;
//import org.junit.platform.commons.util.StringUtils;
//import org.junit.platform.commons.util.StringUtils;
import org.apache.commons.lang3.StringUtils;
public class ScoreBoardService {



    public Match addNewMatch(String homeTeam, String awayTeam)
    {
        if (StringUtils.isBlank(homeTeam) || StringUtils.isBlank(awayTeam))
        {
            throw new IllegalArgumentException("team names cannot be empty");
        }

        Match newMatch = new Match(1,homeTeam,awayTeam);

        return newMatch;
    }

}
