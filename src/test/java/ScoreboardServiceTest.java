import model.Match;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import service.ScoreBoardService;
import util.SequenceGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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


    @Test
    public void matchSummaryTest()
    {

        matchSummaryTestData();

        List<String> matchSummaries = scoreBoardService.generateMatchSummary();

        assertEquals( "Uruguay 6 - Italy 6", matchSummaries.get(0));
        assertEquals( "Spain 10 - Brazil 2", matchSummaries.get(1));
        assertEquals( "Mexico 0 - Canada 5", matchSummaries.get(2));
        assertEquals( "Argentina 3 - Australia 1", matchSummaries.get(3));
        assertEquals( "Germany 2 - France 2", matchSummaries.get(4));


    }


    void matchSummaryTestData()
    {
        scoreBoardService.addNewMatch("Mexico","Canada");
        scoreBoardService.updateScore(1,0,5);

        scoreBoardService.addNewMatch("Spain","Brazil");
        scoreBoardService.updateScore(2,10,2);

        scoreBoardService.addNewMatch("Germany","France");
        scoreBoardService.updateScore(3,2,2);

        scoreBoardService.addNewMatch("Uruguay","Italy");
        scoreBoardService.updateScore(4,6,6);

        scoreBoardService.addNewMatch("Argentina","Australia");
        scoreBoardService.updateScore(6,3,1);

    }

}
