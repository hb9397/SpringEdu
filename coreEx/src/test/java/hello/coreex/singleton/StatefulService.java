package hello.coreex.singleton;

public class StatefulService {
	// 실무에서 싱글톤을 적용 했을 때 하기 쉬운 실수

	// 상태를 유지하는 필드
	private int price;

	public void order(String name, int price){
		System.out.println("name = " + name + ", price = " + price);

		// 해당 코드가 문제를 발생
		this.price = price;
	}

	public int getPrice(){
		return price;
	}

}
