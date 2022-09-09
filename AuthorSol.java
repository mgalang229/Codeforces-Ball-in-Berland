import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

/*

- there are k possible pairs
- choose 2 pairs from k pairs wherein the first and second are different from the other pair
- boy's number comes first, then the girl's number
- a[i] is matched with its corresponding b[i]

1
3 4 4
1 1 2 3
2 3 2 4

boys:
1 = 2
2 = 1
3 = 1

girl:
1 = 0
2 = 2
3 = 1
4 = 1

2 + 2 - 1 = 3

total edges that would be blocked if we use edge (a, b):
= deg(a) + deg(b) - 1

subtract the answer from k to get the available edges (for every edge)

non-blocked edges (per pair/edge):
= k - [deg(a) + deg(b) - 1]
= k - deg(a) - deg(b) + 1

 */

public class AuthorSol {
	
	public static void main(String[] args) {	
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int T = 1;
		T = fs.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int a = fs.nextInt(), b = fs.nextInt(), k = fs.nextInt();
			Edge[] edges = new Edge[k];
			for (int i = 0; i < k; i++) {
				edges[i] = new Edge(fs.nextInt(), 0);
			}
			for (int i = 0; i < k; i++) {
				edges[i] = new Edge(edges[i].from, fs.nextInt());
			}
			int[] boys = new int[a];
			int[] girls = new int[b];
			for (int i = 0; i < k; i++) {
				boys[edges[i].from-1]++;
				girls[edges[i].to-1]++;
			}
			long ans = 0;
			for (int i = 0; i < k; i++) {
				ans += k - boys[edges[i].from-1] - girls[edges[i].to-1] + 1;
			}
			System.out.println(ans / 2);
		}
		out.close();
	}
	
	static class Edge {
		int from, to;
		
		Edge(int from, int to) {
			this.from = from;
			this.to = to;
		}
	}
	
	static final Random rnd = new Random();
	
	static void shuffleSort(int[] a) { //change this
		int n = a.length;
		for (int i = 0; i < n; i++) {
			int newInd = rnd.nextInt(n);
			int temp = a[newInd]; //change this
			a[newInd] = a[i];
			a[i] = temp;
		}
		Arrays.sort(a);
	}
	
	static class FastScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
		String next() {
			while (!st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		
		int[] readArray(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
		}
		
		long[] readLongArray(int n) {
			long[] a = new long[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextLong();
			}
			return a;
		}
		
		long nextLong() {
			return Long.parseLong(next());
		}
		
		double nextDouble() {
			return Double.parseDouble(next());
		}
		
		String nextLine() {
			String str = "";
			try {
				if (st.hasMoreTokens()) {
					str = st.nextToken("\n");
				} else {
					str = br.readLine();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
