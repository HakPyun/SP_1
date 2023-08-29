package xml;

public class OrderManager {

	private CarMaker maker;

	public OrderManager() {
		
	}

	public OrderManager(CarMaker maker) {
		super();
		this.maker = maker;
	}
	
	public void order(int m) {
		Money money = new Money(m);
		
		Car car = maker.sell(money);
		System.out.println("판매상(인수) : " + car.getName());
	}
	public void setMaker(CarMaker maker) {
		this.maker = maker;
	}
}
