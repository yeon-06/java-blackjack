package domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    private static final int ACE_ADDITIONAL_VALUE = 10;
    private static final int BLACKJACK_COUNT = 21;

    private final List<Card> value;

    public Cards(List<Card> cards) {
        this.value = new ArrayList<>(cards);
    }

    public void add(Card card) {
        this.value.add(card);
    }

    public boolean isBust() {
        return sum() > BLACKJACK_COUNT;
    }

    public boolean isBlackJack() {
        return sum() == BLACKJACK_COUNT;
    }

    public int sum() {
        int sum = value.stream()
                .mapToInt(Card::toInt)
                .sum();

        if (hasAce() && !exceedBust(sum)) {
            sum += ACE_ADDITIONAL_VALUE;
        }

        return sum;
    }

    private boolean exceedBust(int sum) {
        return sum + ACE_ADDITIONAL_VALUE > BLACKJACK_COUNT;
    }

    private boolean hasAce() {
        return value.stream()
                .anyMatch(Card::isAce);
    }

    public List<Card> getValue() {
        return value;
    }
}
