import model.Match;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import service.ScoreBoardService;
import util.SequenceGenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;





public class ScoreboardServiceTest {

    private static ScoreBoardService scoreBoardService;
    private static SequenceGenerator sequenceGenerator;

    @BeforeAll
    public static void initializeData()
    {
        scoreBoardService = new ScoreBoardService();
        sequenceGenerator = new SequenceGenerator();
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

    @Test
    public void sequenceGeneratorIncreasesValueBy1EachTimeItIsCalledTest()
    {
        for(int i = 0; i < 9; i++)
        {
            sequenceGenerator.generate();
        }
        assertEquals(10, sequenceGenerator.generate());
    }

    @Test
    public void finishExistingMatchTest()
    {
        Match match1 = scoreBoardService.addNewMatch("team1","team2");
        Match match2 = scoreBoardService.addNewMatch("team3","team4");

        scoreBoardService.finishMatch(1);
    }

    @Test
    public void finishMatchWithNonExistingIdTest()
    {
        Match match1 = scoreBoardService.addNewMatch("team1","team2");
        Match match2 = scoreBoardService.addNewMatch("team3","team4");

        assertThrows(IllegalArgumentException.class, () -> {
            scoreBoardService.finishMatch(6);
        });

    }

    @Test
    public void updateScoreForMatchWithExistingIdTest()
    {
        Match match = scoreBoardService.addNewMatch("team1","team2");
        match = scoreBoardService.updateScore(1,1,1);
    }

    @Test
    public void matchThatdoesNotExistInScoreboardThrowsExceptionOnUpdate()
    {
        assertThrows(IllegalArgumentException.class, () -> {
            scoreBoardService.updateScore(115,1,1);
        });
    }

    @Test
    public void negativeScoreThrowsExceptionOnUpdate()
    {
        Match match = scoreBoardService.addNewMatch("team1","team2");

        assertThrows(IllegalArgumentException.class, () -> {
            scoreBoardService.updateScore(1,-1,1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            scoreBoardService.updateScore(1,1,-1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            scoreBoardService.updateScore(1,-1,-1);
        });

    }
}
