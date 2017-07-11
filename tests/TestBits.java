import java.util.*;

public class TestBits {
	public static void p(String f, Object ...a) {
		System.out.printf(f, a);
	}
	public void test() {
		t00();
	}
	public void t00() {
		SBits sbits = new SBits();
		Integer v;
		String s = "10101011";
		v = sbits.bin2int(s);
		assert (v != null) : "t00 null err 1";
		p("bin2int		%s -> %d\n", s, v);
		s = sbits.int2bin(v);
		assert (s != null) : "t00 null err 2";
		p("int2bin		%d -> %s\n", v, s);
		v = 123456;
		s = sbits.int2string(v);
		assert (s != null) : "t00 null err 3";
		p("int2string	%d -> %s\n", v, s);
	}
}
