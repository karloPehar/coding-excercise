import model.Match;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import service.ScoreBoardService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;





public class ScoreboardServiceTest {

    private static ScoreBoardService scoreBoardService;

    @BeforeAll
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

    @Test
    public void EmptyTeamNamesPassedOnMatchCreationCauseExceptionTest()
    {
        assertThrows(IllegalArgumentException.class, () -> {
            scoreBoardService.addNewMatch("","");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            scoreBoardService.addNewMatch("string","");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            scoreBoardService.addNewMatch("  ","string");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            scoreBoardService.addNewMatch(null,"string");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            scoreBoardService.addNewMatch("",null);
        });


    }

}
