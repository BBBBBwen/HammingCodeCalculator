import javax.swing.JOptionPane;

public class Calculator {
	private static int p;

	public static void main(String[] args) {
		String input = JOptionPane.showInputDialog("Enter a number");
		JOptionPane.showMessageDialog(null, "Bit conversion:" + BinaryGenerate(input));
	}

	public static String BinaryGenerate(String i) {
		String Binary = "";
		int[] bry = new int[i.length()];
		for (int a = 0; a < i.length(); a++) {
			bry[a] = i.charAt(a);
			if (bry[a] % 2 == 0) {
				bry[a] = 0;
			} else {
				bry[a] = 1;
			}
			Binary = Binary + bry[a] + " ";
		}
		return Binary;
	}

	public static String HammingCodeGenerate(String i) {
		int m = i.length();
		String HMCode = "";
		for (int j = 0;; j++) {
			if (Math.pow(2, j) >= m + j + 1) {
				p = j;
				break;
			}
		}
		int[] bry = new int[m + p];
		int b = 0;
		for (int a = 0; a < m + p; a++) {
			if (parity(a)) {
				bry[a] = i.charAt(b);
				b++;
			}
		}
		for (int k = 0; k < p; k++) {
			bry[(int) Math.pow(2, k) - 1] = EvenParity(k, bry);
		}
		for (int g = 0; g < m + p; g++) {
			HMCode = HMCode + bry[g] + " ";
		}
		return HMCode;
	}

	public static boolean parity(int i) {
		for (int j = 0; j < p; j++) {
			if ((i + 1) == Math.pow(2, j)) {
				return false;
			}
		}
		return true;
	}

	public static int EvenParity(int py, int[] bry) {
		int pt = 0;
		for (int c = (int) (Math.pow(2, py) - 1); c < bry.length; c = +2 * (py + 1)) {
			for (int j = 0; j < Math.pow(2, py); j++) {
				if (bry[c] == 1) {
					pt++;
				}
			}
		}
		if (pt % 2 == 0) {
			return 0;
		} else {
			return 1;
		}
	}
}