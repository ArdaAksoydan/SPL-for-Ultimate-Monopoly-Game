import domain.Logger;
import domain.MonopolyBot;
import domain.MonopolyGameController;
import ui.MonopolyFrame;

public class MonopolyGame {

	public static void main(String[] args) {

		MonopolyGameController handler = new MonopolyGameController();
		MonopolyFrame frame = new MonopolyFrame(handler);
		handler.addObserver(frame);
		Logger.getInstance().addObserver(frame);
		new Thread(new MonopolyBot()).start();

	}

}
