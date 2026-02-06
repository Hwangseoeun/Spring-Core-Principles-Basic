package springCorePrinciples.basic.singleton;

public class StatefulService {

    // 상태를 유지하는 필드
    private int price;

    public void order(final String name, final int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
