package pl.lodz.p.zzpj.testing.assertj;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static pl.lodz.p.zzpj.testing.assertj.Fellow.Race.*;
import static pl.lodz.p.zzpj.testing.assertj.FellowTestFixture.*;

public class FellowshipAssertionTest {

    private Fellowship fellowship = formTheFellowshipOfTheRing();

    @Test
    public void frodoShouldBeIn() {
        assertThat(fellowship).contains(frodo());
    }

    @Test
    public void sauronShouldNotBeIn() {
        assertThat(fellowship).doesNotContain(sauron());
    }

    @Test
    public void shouldFrodoNameBeCorrectAndNotBeASauronAndBeInFellowship() {
        assertThat(fellowship).extracting("name").contains("Frodo").doesNotContain("Sauron");
    }

    @Test
    public void aragornShouldBeBeforeBoromir() {
        assertThat(fellowship).containsSubsequence(aragorn(), boromir());
    }

    @Test
    public void shouldNotContainDuplicatedFellows() {
        assertThat(fellowship).doesNotHaveDuplicates();
    }

    @Test
    public void shouldContainOnlyFellowsWithExpectedNamesInProperOrder() {
        assertThat(fellowship).extracting("name")
                .containsSequence("Frodo", "Sam", "Merry", "Pippin", "Gandalf", "Legolas", "Gimli", "Aragorn", "Boromir");
    }

    @Test
    public void shouldContainNineFellowsButNoneGiant() {
        assertThat(fellowship).hasSize(9).filteredOn("race", GIANT).isEmpty();
    }

    @Test
    public void shouldContainTupleForBoromirSamAndLegolas() {
        // Extracting multiple values at once (using tuple to group them)
        // Create tuples with name and race name
        assertThat(fellowship).extracting("name", "race")
                .contains(tuple("Boromir", MAN),
                          tuple("Sam", HOBBIT),
                          tuple("Legolas", ELF));
    }


    @Test
    public void shouldContainsFourHobbits() {
        assertThat(fellowship).filteredOn("race", HOBBIT).hasSize(4);
    }

    private Fellowship formTheFellowshipOfTheRing() {
        return new Fellowship(
                frodo(),
                FellowTestFixture.sam(),
                FellowTestFixture.merry(),
                FellowTestFixture.pippin(),
                FellowTestFixture.gandalf(),
                FellowTestFixture.legolas(),
                FellowTestFixture.gimli(),
                aragorn(),
                FellowTestFixture.boromir());
    }
}
