import java.util.Scanner;

import javax.swing.JOptionPane;

public class Calculator {
	private static int p;

	public static void main(String[] args) {
<<<<<<< HEAD
		Scanner sc = new Scanner(System.in);
		//String input = sc.nextLine();
		//System.out.print("Enter a number");
		//int[] inBry = BinaryGenerate(input);
		//int[] HMcode = HammingCodeGenerate(inBry);
		//System.out.print(printArray("Bit Convertion: ", inBry) + "\n" + printArray("HammingCode: ", HMcode));
		String Errin = sc.nextLine();
		System.out.print("Enter error message");
=======
		String input = JOptionPane.showInputDialog("Enter a number");
		int[] inBry = BinaryGenerate(input);
		int[] HMcode = HammingCodeGenerate(inBry);
		JOptionPane.showMessageDialog(null,
		printArray("Bit Convertion: ", inBry) + "\n" + printArray("HammingCode: ",
		HMcode));
		String Errin = JOptionPane.showInputDialog("Enter error message");
>>>>>>> 8f2b72c262c242c35d1e06e5a90f0b4877a5e5fc
		validate(Errin);
		sc.close();
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
		int[] ham = new int[m + p];
		int[] hamming = new int[m + p];
		int[] PCount = new int[p];
		int count = 0;
		for (int a = 0; a < ham.length; a++) {
			if (parity(a,ham)) {
				PCount[count] = a;
				count++;
			} else {
				ham[a] = i[a - count];
			}
		}
		for (int k = 0; k < p; k++) {
			ham[PCount[k]] = EvenParity(PCount[k], ham);
		}
		int s = m + p - 1;
		for (int h = 0; h < m + p; h++) {
			hamming[h] = ham[s];
			s--;
		}
		return hamming;
	}

	public static void validate(String str) {
		int[] ham = new int[str.length()];
		int err = -1;
		int j = ham.length - 1;
		for (int i = 0; i < ham.length; i++) {
			ham[j] = str.charAt(i)-48;
			j--;
			//System.out.print(printArray("",ham));
		}
		System.out.println(printArray("",ham));
		for (int i = 0; i < ham.length; i++) {
			if (parity(i,ham)) {
				if (EvenParity(i, ham) == 1) {
					err = i;
					break;
				}
			}
		}
		if (err == -1) {
			System.out.print("Received codes are valid");
		} else {
			System.out.print("Received codes are invalid. Found error at " + (err + 1));
			ham[err] = 1 - ham[err];
			System.out.print(printArray("the Correct cide are ",ham));
		}
	}

	public static boolean parity(int i,int[] str) {
		int t;
		for (int j = 0;; j++) {
			if (Math.pow(2, j) >= str.length) {
				t = j;
				break;
			}
		}
		for (int j = 0; j < t; j++) {
			if (i == Math.pow(2, j) - 1) {
				return true;
			}
		}
		return false;
	}

	public static int EvenParity(int py, int[] bry) {
		int pt = 0;
		for (int c = py; c < bry.length; c += 2 * (py + 1)) {
			for (int j = c; j < c + py + 1; j++) {
				if (bry[j] == 1) {
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