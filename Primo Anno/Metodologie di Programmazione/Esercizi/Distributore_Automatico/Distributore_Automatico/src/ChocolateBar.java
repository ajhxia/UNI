public class ChocolateBar extends Product{
    private static int kBarre;
    public ChocolateBar() {
        super(2.50, "003");
        kBarre++;
		if(kBarre==3) {
			System.out.println("GOLDEN TICKET!");
			kBarre=0;//oppure potevamo usare il modulo
		}
    }
}
