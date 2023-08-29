package basic;

public class OrderManager {

	private HyundaiMaker hyundaiMaker;
	//기아꺼 팔고 싶으면 싹다 기아로 바뀌어야함.
	

	public OrderManager() {
		super();
		this.hyundaiMaker = new HyundaiMaker();
	}

	public OrderManager(HyundaiMaker hyundaiMaker) {
		super();
		this.hyundaiMaker = new HyundaiMaker();
	}
	
	public void order(int m) {
		Money money = new Money(m);
		
		Car car = hyundaiMaker.sell(money);
		System.out.println("판매상(인수) : " + car.getName());
	}
}
