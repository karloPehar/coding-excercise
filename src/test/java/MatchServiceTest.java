import org.junit.Test;
import org.junit.platform.engine.support.discovery.SelectorResolver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatchServiceTest {

    @Test
   public void addNewMatchTest() {

        Match match = matchService.addNewMatch("team1","team2");

        assertEquals(0, match.getHomeTeamScore());
        assertEquals(0, match.getAwayTeamScore());


    }
}
