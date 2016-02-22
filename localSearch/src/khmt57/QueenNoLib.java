package khmt57;

/**
 * 
 */

/**
 * @author damvantai
 *
 */
// package khmt57;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class QueenNoLib {
	int n;	// declare number queen 
	int[] x;// x[i] is the row of queen on column i
	Random R = new Random();	// init object R is random
	public QueenNoLib(int n){
		this.n = n;
		x = new int[n];
	}
	
	// function count violation of n queen
	public int violations(){
		int v = 0;
		for(int i = 0; i < n-1; i++)
			for(int j = i+1; j < n; j++){
				if(x[i] == x[j])
					v++;
				if(x[i] + i == x[j] + j)
					v++;
				if(x[i] - i == x[j] -j)
					v++;
			}
		return v;
	}

	// function count violation of queen on column c
	public int violations(int c){
		int v = 0;
		for(int ci = 0;  ci < n; ci++)
			if(c != ci) // case another column
			{
				if(x[c] == x[ci]) v++;
				if(x[c] + c == x[ci] + ci) v++;
				if(x[c] - c == x[ci] - ci) v++;
			}
		return v;
	}

	// 
	public int selectMostViolatingQueen(){
		ArrayList<Integer> S = new ArrayList<Integer>();
		int sel_c = -1;
		int maxV = -1000000;
		for(int c = 0; c < n; c++){
			int v = violations(c);
			if(v > maxV){
				maxV = v;
				S.clear();
				S.add(c);
			}else if(v == maxV)
				S.add(c);
		}
		sel_c = S.get(R.nextInt(S.size()));
		return sel_c;
	}
	public int violations(int c, int r){
		int r1 = x[c];
		x[c] = r;
		int v = violations();
		x[c] = r1;
		return v;
	}
	public int selectMostPromissingRow(int c){
		int sel_r = -1;
		int minViolation = 10000000;
		ArrayList<Integer> S = new ArrayList<Integer>();
		for(int r = 0; r < n; r++){
			int r1 = x[c];
			x[c] = r;
			int v = violations();
			if(v < minViolation){
				minViolation = v;	
				S.clear();
				S.add(r);
			}else if(v == minViolation){
				S.add(r);
			}
//			x[c] = r1;	
		}
		sel_r = S.get(R.nextInt(S.size()));
		return sel_r;
	}
	public void search(int maxIter){
		int it = 0;
		
		java.util.Random R = new java.util.Random();
		for(int c = 0; c < n; c++)
			x[c] = R.nextInt(n);
		
		ArrayList<Integer> N = new ArrayList<Integer>();
		
		while(it < maxIter){
			int c = selectMostViolatingQueen();
			int r = selectMostPromissingRow(c);
			x[c] = r;// local move
			
			System.out.println("Step " + it + ", N.sz = " + N.size() + ", c = " + c + ", r = " + r + ", violations = " + violations());
			it++;
			if(violations() == 0) break;
		}
	}
	public void printHTML(String fn){
		try{
			PrintWriter out = new PrintWriter(fn);
			out.println("<table>");
			for(int i = 0; i < n; i++){
				out.println("<tr>");
				for(int j = 0; j < n; j++){
					if(x[j] == i)
						out.print("<td width=30 height=30 bgcolor='blue'>");	
					else
						out.print("<td width=30 height=30 bgcolor='red'>");	
					
					out.print("</td>");
				}
				out.println("</tr>\n");
			}
			out.println("</table>");
			out.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QueenNoLib q = new QueenNoLib(1000);
		q.search(10000);
		q.printHTML("khmt57-queen.html");
	}

}

