package hello.coreex.singleton;

public class StatelessService {
	// 상태를 유지 하는 필드를 제거한다.

	public int order(String name, int price){
		System.out.println("name = " + name + ", price = " + price);
		return price;
	}
}
