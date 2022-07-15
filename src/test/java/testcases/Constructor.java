package testcases;

    public class Constructor {
	public Constructor() {
		this(2);
		System.out.println("i am in constructor ");
	}

	public Constructor(int a) {
		System.out.println("i argument int");
	}

	public static void main(String[] argsa) {
		Constructor c = new Constructor();
	}
}