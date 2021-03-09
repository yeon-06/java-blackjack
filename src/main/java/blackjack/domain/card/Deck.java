package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Deck {
    private static final int FIRST_INDEX = 0;

    private final List<Card> shuffledCards;

    private Deck(List<Card> shuffledCards) {
        this.shuffledCards = new ArrayList<>(shuffledCards);
    }

    public static Deck generateByRandomCard() {
        return new Deck(Card.getShuffledCards());
    }

    public List<Card> makeTwoCards() {
        return IntStream.range(0, 2)
                .mapToObj(count -> shuffledCards.remove(FIRST_INDEX))
                .collect(Collectors.toList());
    }

    public Card makeOneCard() {
        return shuffledCards.remove(FIRST_INDEX);
    }
}