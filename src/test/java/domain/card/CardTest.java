package domain.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardTest {
    @Test
    void 정적팩토리테스트() {
        assertThat(Card.of(Rank.ACE, Suit.HEART)).isInstanceOf(Card.class);
    }
}
