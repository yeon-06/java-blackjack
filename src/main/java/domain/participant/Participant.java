package domain.participant;

import domain.card.Card;
import domain.card.Cards;
import java.util.Objects;

public abstract class Participant {

    protected final Name name;
    protected final Cards cards;

    public Participant(Name name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public abstract boolean isFinished();

    public void drawCard(Card card) {
        cards.add(card);
    }

    public String getName() {
        return name.getValue();
    }

    public Cards getCards() {
        return cards;
    }

    public <T> boolean isSameClass(Class<T> classType) {
        return getClass() == classType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Participant that = (Participant) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
