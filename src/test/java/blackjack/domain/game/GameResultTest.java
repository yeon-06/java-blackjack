package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.TestUtil.getCards;

import blackjack.domain.card.Number;
import blackjack.domain.participant.BettingAmount;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GameResultTest {
    @ParameterizedTest(name = "{0}")
    @MethodSource("provideParameters")
    @DisplayName("플레이어 승리")
    void test(String comment, Player player, Dealer dealer) {
        // given
        GameResult gameResult = new GameResult(List.of(player), dealer);

        // then
        assertThat(gameResult.getMatchResult(player)).isEqualTo(MatchResult.WIN);
    }

    private static Stream<Arguments> provideParameters() {
        Name playerName = new Name("yeonLog");
        Name dealerName = new Name("딜러");
        BettingAmount bettingAmount = new BettingAmount(1000L);

        return Stream.of(
                Arguments.arguments("플레이어가 버스트가 아니고 딜러가 버스트인 경우",
                        new Player(playerName, getCards(Number.ACE, Number.NINE), bettingAmount),
                        new Dealer(dealerName, getCards(Number.QUEEN, Number.KING, Number.JACK))),
                Arguments.arguments("둘 다 버스트가 아니고 딜러보다 숫자가 높은 경우",
                        new Player(playerName, getCards(Number.KING, Number.QUEEN), bettingAmount),
                        new Dealer(dealerName, getCards(Number.QUEEN, Number.NINE))),
                Arguments.arguments("플레이어만 블랙잭",
                        new Player(playerName, getCards(Number.KING, Number.ACE), bettingAmount),
                        new Dealer(dealerName, getCards(Number.QUEEN, Number.TEN, Number.ACE)))
        );
    }

    @ParameterizedTest(name = "{3} 개수 -> {4}회")
    @MethodSource("provideParameters2")
    @DisplayName("딜러가 승리한 횟수")
    void dealer_count_test(Player player1, Player player2, Dealer dealer, MatchResult result, int expect) {
        // given
        List<Player> players = Arrays.asList(player1, player2);
        GameResult gameResult = new GameResult(players, dealer);

        // then
        assertThat(gameResult.calculateDealerMatchResultCount(result)).isEqualTo(expect);
    }

    private static Stream<Arguments> provideParameters2() {
        Name playerName1 = new Name("yeonLog1");
        Name playerName2 = new Name("yeonLog2");
        Name dealerName = new Name("딜러");
        BettingAmount bettingAmount = new BettingAmount(1000L);

        return Stream.of(
                Arguments.arguments(
                        new Player(playerName1, getCards(Number.ACE, Number.NINE), bettingAmount),
                        new Player(playerName2, getCards(Number.EIGHT, Number.NINE), bettingAmount),
                        new Dealer(dealerName, getCards(Number.QUEEN, Number.NINE)),
                        MatchResult.WIN,
                        1
                ),
                Arguments.arguments(
                        new Player(playerName1, getCards(Number.ACE, Number.NINE), bettingAmount),
                        new Player(playerName2, getCards(Number.NINE, Number.NINE), bettingAmount),
                        new Dealer(dealerName, getCards(Number.EIGHT, Number.NINE)),
                        MatchResult.LOSE,
                        2
                )
        );
    }
}