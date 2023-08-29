package interfaces;

public class HyundaiMaker implements CarMaker {

	@Override
	public Car sell(Money money) {
		System.out.println("현대차(입금) : " + money.getAmount());
		
		Car car = new Car("쏘나타");
		
		return car;
	}
}
