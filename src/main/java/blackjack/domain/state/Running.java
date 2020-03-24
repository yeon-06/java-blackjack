package blackjack.domain.state;

public abstract class Running extends Started {
    public Running(final Cards cards) {
        super(cards);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double profit(final double money) {
        throw new UnsupportedOperationException();
    }
}