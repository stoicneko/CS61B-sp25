public class Dessert {
    public static int numDesserts = 0;
    public int flavor;
    public int price;
    public Dessert(int f, int p) {
        flavor = f;
        price = p;
        numDesserts++; // 应该放在构造函数里面

    }
    public void printDessert() {
        System.out.print(flavor + " " + price + " " + numDesserts);
        System.out.println();
//        numDesserts++;
    }
    public static void main(String[] args) {
        System.out.println("I love dessert!");
    }
}
