package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp() {
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }

    @Test
    public void returnPlayerIfTheyAreOnTheList() {
        assertEquals("Yzerman", stats.search("Yzerman").getName());
    }

    @Test
    public void returnNullIfPlayerIsNotOnTheList() {
        assertNull(stats.search("Korhonen"));
    }

    @Test
    public void returnNumberOfPlayersInTeam() {
        List<Player> team = stats.team("EDM");
        
        assertEquals(3, team.size());
    }
    
    @Test
    public void returnCorrectTopScorer() {
        assertEquals("Gretzky", stats.topScorers(1).get(0).getName());
    }
}
