package khmt57;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
//import java.lang.*;

public class QueenNoOpenCBLS {

	/**
	 * 
	 */
	
	int n; // declare number queen
	int[] x;	// x[i] is the row of queen on column i
	Random R = new Random();	// init object R is random
	
	public QueenNoOpenCBLS(int n) {
		// TODO Auto-generated constructor stub
		this.n = n;
		x = new int[n];
	}
	
	// function count violation of n queen
	public int violations()
	{
		int v = 0;
		for (int i = 0; i < n; i++)
			for (int j = i + 1; j < n; j++)
			{
				if (x[i] == x[j])
					v++;
				if (x[i] + i == x[j] + j)
					v++;
				if (x[i] - j == x[j] -j)
					v++;
			}
		return v;
	}
	
	// function count violation of queen on column c
	public int violation(int c)
	{
		int v = 0;
		for (int ci = 0; ci < n; ci++)
			if (c != ci)
			{
				if (x[c] == x[ci]) v++;
				if (x[c] + c == x[ci] + ci) v++;
				if (x[c] -c == x[ci] - ci) v++;
			}
		return v;		
	}
	
	// function select queen have most violation
	public int selectMostViolationQueen()
	{
		ArrayList<Integer> S = new ArrayList<Integer>();
		int sel_c = -1;
		int maxV = -10000000;
		for (int c = 0; c < n; c++)
		{
			int v = violation(c);
			if (v > maxV)
			{
				maxV = v;
				S.clear();
				S.add(c);
			}
			else if (v == maxV)
				S.add(c);
		}
		sel_c = S.get(R.nextInt(S.size()));		// select random size of queen
		return sel_c;
	}
	
	// function count violation in queen row r and col c
//	public int violations(int c, int r)
//	{
//		int r1 = x[c];
//		x[c] = r;
//		int v = violations();
//		x[c] = r1;
//		return v;
//	}
	
	// function select function have most promissing in row thử tất cả các trường hợp của 
	// trong một cột c, thử lần lượt từng hàng một và nếu hàng nào có violation nhơ hơn thì cập nhật sau lại xet tiếp
	// các các hàng khác
	public int selectMostPromissingRow(int c)
	{
		int sel_r = -1;
		int minViolation = 10000000;
		ArrayList<Integer> S = new ArrayList<Integer>();
		for (int r = 0; r < n; r++)
		{
			int r1 = x[c];
			x[c] = r;
			int v = violations();
			if (v < minViolation) 
			{
				minViolation = v;
				S.clear();
				S.add(r);
			}
			else if (v == minViolation)
			{
				S.add(r);
			}
			x[c] = r1;
		}
		sel_r = S.get(R.nextInt(S.size()));
		return sel_r;
	}
	
	// function search
	public void search(int maxIter){
		int it = 0;
		Random R = new Random();
		for (int c = 0; c < n; c++)
			x[c] = R.nextInt(n);
		
		ArrayList<Integer> N = new ArrayList<Integer>();
		while (it < maxIter) {
			int c = selectMostViolationQueen();
			int r = selectMostPromissingRow(c);
			x[c] = r;
			System.out.println("Step " + it + ", N.size = " + N.size() + ", c = " + c + ", r = " + r + ", violations = " + violations());
			it ++;
			if (violations() == 0) break;
		}
	}
	
	// printhtml
	public void printHTML(String fn) {
		try {
			PrintWriter out = new PrintWriter(fn);
			out.println("<table>");
			for (int i = 0; i < n; i++) {
				out.println("<tr>");
				for (int j = 0; j < n; j++) {
					if (x[j] == i)
						out.print("<td width = 30 height = 30 bgcolor = 'blue'>");
					else 
						out.print("<td width = 30 height = 30 bgcolor = 'red'>");
					
					out.print("</td>");
				}
				out.println("</tr>\n");
			}
			out.println("</table>");
			out.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// Todo auto 
		QueenNoOpenCBLS q = new QueenNoOpenCBLS(10);
		q.search(10000);
		q.printHTML("/home/damvantai/workspace/QueenNoOpenCBLS/queen.html");
	}
}
