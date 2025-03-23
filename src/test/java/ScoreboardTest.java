import impl.Match;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import impl.ScoreBoard;
import util.SequenceGenerator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;





public class ScoreboardTest {

    private static ScoreBoard scoreBoard;
    private static SequenceGenerator sequenceGenerator;

    @BeforeEach
    public void initializeData()
    {
        scoreBoard = new ScoreBoard();
        sequenceGenerator = new SequenceGenerator();
    }

    @Test
   public void addNewMatchTest() {

        Match match = scoreBoard.addNewMatch("team1","team2");

        assertEquals(0, match.getHomeTeamScore());
        assertEquals(0, match.getAwayTeamScore());

    }

    @Test
    public void EmptyTeamNamesPassedOnMatchCreationCauseExceptionTest()
    {
        assertThrows(IllegalArgumentException.class, () -> {
            scoreBoard.addNewMatch("","");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            scoreBoard.addNewMatch("string","");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            scoreBoard.addNewMatch("  ","string");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            scoreBoard.addNewMatch(null,"string");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            scoreBoard.addNewMatch("",null);
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
        Match match1 = scoreBoard.addNewMatch("team1","team2");
        Match match2 = scoreBoard.addNewMatch("team3","team4");

        scoreBoard.finishMatch(1);
    }

    @Test
    public void finishMatchWithNonExistingIdTest()
    {
        Match match1 = scoreBoard.addNewMatch("team1","team2");
        Match match2 = scoreBoard.addNewMatch("team3","team4");

        assertThrows(IllegalArgumentException.class, () -> {
            scoreBoard.finishMatch(6);
        });

    }

    @Test
    public void updateScoreForMatchWithExistingIdTest()
    {
        Match match = scoreBoard.addNewMatch("team1","team2");
        match = scoreBoard.updateScore(1,1,1);
    }

    @Test
    public void matchThatdoesNotExistInScoreboardThrowsExceptionOnUpdate()
    {
        assertThrows(IllegalArgumentException.class, () -> {
            scoreBoard.updateScore(115,1,1);
        });
    }

    @Test
    public void negativeScoreThrowsExceptionOnUpdate()
    {
        Match match = scoreBoard.addNewMatch("team1","team2");

        assertThrows(IllegalArgumentException.class, () -> {
            scoreBoard.updateScore(1,-1,1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            scoreBoard.updateScore(1,1,-1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            scoreBoard.updateScore(1,-1,-1);
        });

    }


    @Test
    public void matchSummaryTest()
    {

        matchSummaryTestData();

        List<String> matchSummaries = scoreBoard.generateMatchSummary();

        assertEquals( "Uruguay 6 - Italy 6", matchSummaries.get(0));
        assertEquals( "Spain 10 - Brazil 2", matchSummaries.get(1));
        assertEquals( "Mexico 0 - Canada 5", matchSummaries.get(2));
        assertEquals( "Argentina 3 - Australia 1", matchSummaries.get(3));
        assertEquals( "Germany 2 - France 2", matchSummaries.get(4));
    }

    @Test
    public void retrievingMatchSummaryForEmptyScoreboardDoesNotThrowExceptionTest()
    {
        List<String> matchSummaries = scoreBoard.generateMatchSummary();
        assertEquals( 0, matchSummaries.size());
    }


    void matchSummaryTestData()
    {
        scoreBoard.addNewMatch("Mexico","Canada");
        scoreBoard.updateScore(1,0,5);

        scoreBoard.addNewMatch("Spain","Brazil");
        scoreBoard.updateScore(2,10,2);

        scoreBoard.addNewMatch("Germany","France");
        scoreBoard.updateScore(3,2,2);

        scoreBoard.addNewMatch("Uruguay","Italy");
        scoreBoard.updateScore(4,6,6);

        scoreBoard.addNewMatch("Argentina","Australia");
        scoreBoard.updateScore(5,3,1);

    }

}
