// javac -cp "./src/;./tests/" -d bin src/*.java tests/*.java
// java -cp bin Main 16 32
public class Main {
	public static void main(String [] args) {
		Tests t = new Tests();
		t.test(args);
	}
}
