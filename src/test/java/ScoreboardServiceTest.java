import model.Match;
import org.junit.BeforeClass;
import org.junit.Test;
import service.ScoreBoardService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreboardServiceTest {

    private static ScoreBoardService scoreBoardService;

    @BeforeClass
    public static void initializeData()
    {
        scoreBoardService = new ScoreBoardService();
    }

    @Test
   public void addNewMatchTest() {

        Match match = scoreBoardService.addNewMatch("team1","team2");

        assertEquals(0, match.getHomeTeamScore());
        assertEquals(0, match.getAwayTeamScore());


    }
}
