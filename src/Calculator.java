import java.util.Scanner;

public class Calculator {
	private static int p;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		System.out.print("Enter a number");
		int[] inBry = BinaryGenerate(input);
		int[] HMcode = HammingCodeGenerate(inBry);
		printArray("Bit Convertion: ", inBry);
		printArray("HammingCode: ", HMcode);
		String Errin = sc.nextLine();
		System.out.print("Enter error message");
		validate(Errin);
		sc.close();
	}

	static public void printArray(String s, int[] a) {
		String str = (s + " ");
		int i = 0;
		while (i < a.length) {
			str = str + a[i] + " ";
			i++;
		}
		System.out.println(str);
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
		int s = m + p - 1;
		for (int h = 0; h < m + p; h++) {
			hamming[h] = i[s];
			s--;
		}
		for (int a = 0; a < ham.length; a++) {
			if (parity(a, ham)) {
				PCount[count] = a;
				count++;
			} else {
				ham[a] = hamming[a - count];
			}
		}
		for (int k = 0; k < p; k++) {
			ham[PCount[k]] = EvenParity(PCount[k], ham);
		}
		
		return ham;
	}

	public static void validate(String str) {
		int[] ham = new int[str.length()];
		int err = -1;
		int j = ham.length - 1;
		for (int i = 0;; i++) {
			if (Math.pow(2, j) >= ham.length) {
				p = j;
				break;
			}
		}
		for (int i = 0; i < ham.length; i++) {
			ham[j] = str.charAt(i) - 48;
			j--;
			// System.out.print(printArray("",ham));
		}
		//System.out.println(printArray("", ham));
		for (int i = 0; i < ham.length; i++) {
			if (parity(i, ham)) {
				if (EvenParity(i, ham) == 1) {
					err = err + i;
				}
			}
		}
		if (err == -1) {
			System.out.print("Received codes are valid");
		} else {
			System.out.print("Received codes are invalid. Found error at " + (err + 1));
			ham[err] = 1 - ham[err];
			printArray("\nthe Correct cide are ", ham);
		}
	}

	public static boolean parity(int i, int[] str) {

		for (int j = 0; j < p; j++) {
			if (i == Math.pow(2, j) - 1) {
				return true;
			}
		}
		return false;
	}

	public static int EvenParity(int py, int[] bry) {
		int pt = 0;
		for (int c = py; c < bry.length; c += 2 * (py + 1)) {
			for (int j = c; j < Math.min(c + py + 1,bry.length); j++) {
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