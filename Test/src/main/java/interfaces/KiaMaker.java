package interfaces;

public class KiaMaker implements CarMaker {

	@Override
	public Car sell(Money money) {
		System.out.println("기아차(입금) : " + money.getAmount());
		
		Car car = new Car("K5");
		
		return car;
	}
}
