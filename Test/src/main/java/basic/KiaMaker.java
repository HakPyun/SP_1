package basic;

public class KiaMaker {

	public Car sell(Money money) {
		System.out.println("기아차(입금) : " + money.getAmount());
		
		Car car = new Car("K5");
		
		return car;
	}
}
