package controller;

import domain.game.BlackJackGame;
import domain.game.GameResult;
import domain.participant.Command;
import domain.participant.Name;
import domain.participant.Participant;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    public void run() {
        BlackJackGame blackJackGame = createBlackJackGame();

        Participant dealer = blackJackGame.findDealer();
        List<Participant> players = blackJackGame.findPlayers();

        OutputView.printInitialCards(dealer, players);

        playPlayersTurn(blackJackGame, players);
        playDealerTurn(blackJackGame, dealer);

        showGameResult(blackJackGame, dealer, players);
    }

    private BlackJackGame createBlackJackGame() {
        try {
            List<Name> playerNames = InputView.inputPlayerNames();
            return new BlackJackGame(playerNames);
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return createBlackJackGame();
        }
    }

    private void playPlayersTurn(BlackJackGame blackJackGame, List<Participant> players) {
        for (Participant player : players) {
            playPlayerTurn(blackJackGame, player);
        }
    }

    private void playPlayerTurn(BlackJackGame blackJackGame, Participant player) {
        Command command = Command.HIT;

        while (command != Command.STAY && !player.isFinished()) {
            command = inputCommand(player);
            blackJackGame.drawCardByCommand(player, command);
            OutputView.printCards(player);
        }
    }

    private Command inputCommand(Participant player) {
        try {
            return InputView.inputWantDraw(player.getName());

        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return inputCommand(player);
        }
    }

    private void playDealerTurn(BlackJackGame blackJackGame, Participant dealer) {
        while (!dealer.isFinished()) {
            OutputView.printDealerDrawInfo();
            blackJackGame.drawCard(dealer);
        }
    }

    private void showGameResult(BlackJackGame blackJackGame, Participant dealer, List<Participant> players) {
        GameResult gameResult = blackJackGame.createGameResult();
        OutputView.printCardsResult(dealer, players);
        OutputView.printGameResult(gameResult);
    }

}