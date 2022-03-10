package view;

import domain.card.Card;
import domain.card.Cards;
import domain.game.BlackJackGame;
import domain.game.GameResult;
import domain.game.MatchResult;
import domain.participant.Dealer;
import domain.participant.Participant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String JOIN_DELIMITER = ", ";
    private static final String ERROR_MESSAGE = "[ERROR] ";

    private OutputView() {

    }

    public static void printInitialCards(Participant dealer, List<Participant> players) {
        System.out.printf("%n%s와 %s에게 2장의 카드를 나누었습니다.%n", dealer.getName(), getPlayerNames(players));
        System.out.printf("%s: %s%n", dealer.getName(), getCardName(dealer.getCards().getValue().get(0)));
        for (Participant player : players) {
            printCards(player);
        }
        System.out.println();
    }

    private static String getPlayerNames(List<Participant> players) {
        return players.stream()
                .map(Participant::getName)
                .collect(Collectors.joining(JOIN_DELIMITER));
    }

    private static String getCardNames(Cards cards) {
        return cards.getValue().stream()
                .map(OutputView::getCardName)
                .collect(Collectors.joining(JOIN_DELIMITER));
    }

    private static String getCardName(Card card) {
        return card.getDenomination().getName() + card.getSuit().getName();
    }

    public static void printCards(Participant player) {
        System.out.printf("%s카드: %s%n", player.getName(), getCardNames(player.getCards()));
    }

    public static void printDealerDrawInfo() {
        System.out.printf("%n딜러는 %d이하라 한장의 카드를 더 받았습니다.%n", Dealer.DRAW_STANDARD);
    }

    public static void printCardsResult(Participant dealer, List<Participant> players) {
        System.out.println();
        printCardResult(dealer);
        for (Participant player : players) {
            printCardResult(player);
        }
    }

    private static void printCardResult(Participant participant) {
        System.out.printf("%s 카드: %s - 결과: %d%n",
                participant.getName(), getCardNames(participant.getCards()), participant.getCards().sum());
    }

    public static void printGameResult(GameResult gameResult) {
        Map<Participant, MatchResult> map = gameResult.getGameResult();

        System.out.printf("%n%s: %d승 %d무 %d패%n"
                , BlackJackGame.DEALER_NAME
                , gameResult.calculateDealerMatchResultCount(MatchResult.WIN)
                , gameResult.calculateDealerMatchResultCount(MatchResult.PUSH)
                , gameResult.calculateDealerMatchResultCount(MatchResult.LOSE)
        );

        for (Participant player : map.keySet()) {
            System.out.printf("%s: %s%n", player.getName(), gameResult.getMatchResult(player).getValue());
        }
    }

    public static void printException(Exception e) {
        System.out.println(ERROR_MESSAGE + e.getMessage());
    }
}
