import javax.swing.JOptionPane;

public class Calculator {
	private static int p;

	public static void main(String[] args) {
		String input = JOptionPane.showInputDialog("Enter a number");
		int[] inBry = BinaryGenerate(input);
		//int[] HMcode = HammingCodeGenerate(inBry);
		JOptionPane.showMessageDialog(null, printArray("Bit Convertion: ", inBry));
		//JOptionPane.showMessageDialog(null, printArray("HammingCode: ", HMcode));

	}

	static public String printArray(String s, int[] a) {
		String str = (s + " ");
		int i = 0;
		while (i < a.length) {
			str = str + a[i] + " ";
			i++;
		}
		return str;
	}

	public static int[] BinaryGenerate(String i) {
		int[] bry = new int[i.length()];
		for (int a = 0; a < i.length(); a++) {
			bry[a] = i.charAt(a);
			if (bry[a] % 2 == 0) {
				bry[a] = 0;
			} else {
				bry[a] = 1;
			}
		}
		return bry;
	}

	public static int[] HammingCodeGenerate(int[] i) {
		int m = i.length;
		for (int j = 0;; j++) {
			if (Math.pow(2, j) >= m + j + 1) {
				p = j;
				break;
			}
		}
		int[] bry = new int[m + p];
		int b = 0;
		for (int a = 0; a < bry.length; a++) {
			if (parity(a)) {
				bry[a] = i[b];
				b++;
			}
		}
		for (int k = 0; k < p; k++) {
			bry[(int) Math.pow(2, k) - 1] = EvenParity(k, bry);
		}
		
		return bry;
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