package interfaces;

public class ClientTest {

	public static void main(String[] args) {
		OrderManager manager = new OrderManager();
		manager.setMaker(new KiaMaker());
		manager.order(20000);

	}

}
