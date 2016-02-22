package khmt57;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import localsearch.constraints.alldifferent.AllDifferent;
import localsearch.functions.basic.FuncPlus;
import localsearch.model.*;
import localsearch.selectors.MinMaxSelector;
public class Queen {
	
	int n;
	LocalSearchManager ls;
	VarIntLS[] x;
	ConstraintSystem S;
	public Queen(int n){
		this.n=n;
	}
	
	public void stateModel(){
		ls=new LocalSearchManager();
		x=new VarIntLS[n];
		for(int i=0 ; i<n;i++)
			x[i]=new VarIntLS(ls, 0, n-1);
		S=new ConstraintSystem(ls);
		S.post(new AllDifferent(x));
		IFunction[] f1=new IFunction[n] ;
			
		for ( int i = 0 ; i < n ; i++)
		f1 [ i ] = new FuncPlus ( x [ i ] , i ) ;
		S.post ( new AllDifferent ( f1 ) ) ;
		IFunction [ ] f2 = new IFunction [ n ] ;
		for ( int i = 0 ; i < n ; i++)
		f2 [ i ] = new FuncPlus ( x [ i ] , -i ) ;
		S . post ( new AllDifferent ( f2 ) ) ;
		ls . close ( );
			
	}
	
	public void printHTML(String fn){
		try{
			PrintWriter out=new PrintWriter(fn);
			out.println("<html>");
			out.println("<head>");
			out.println("<title>queen</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<table width='400' height='400' style='border:1px solid black'>");
			for(int i=0;i<n;i++){
				out.println("<tr>");
				for(int j=0;j<n;j++){
					out.print("<td");
					//out.print("1");
					if(x[j].getValue()==i){
						out.print(" style='background-color:red' >");
					}
					else{
						out.print(" style='background-color:green' >");
					}
					
					out.println("</td>");
				}
				out.println("</tr>");
			}
			out.println("</table>");
			out.println("</body>");
			out.println("</html>");
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void search(){
		MinMaxSelector mms = new MinMaxSelector (S) ;
		int it = 0 ;
		while (  S.violations( ) > 0) {
			VarIntLS selx = mms.selectMostViolatingVariable();
			int selv = mms . selectMostPromissingValue ( selx ) ;
			selx . setValuePropagate ( selv ) ;
			it++;
			System . out . println ("loop "+it+" : "+S.violations ( ) );
		}
		System . out . println (S.violations ( ) );
	}
	
	public void search1(){
		ArrayList<Variable> arraylist=new ArrayList<Variable>();
		int it=0;
		int minDelta=100000000;
		while(S.violations()>0){
			minDelta=100000000;
			for(int i=0;i<n;i++)
				for(int j=0;j<n;j++){
					int delta=S.getAssignDelta(x[i], j);
					if(delta < minDelta){
						minDelta=delta;
						arraylist.clear();
						arraylist.add(new Variable(i, j));
					}
					else if(delta==minDelta){
						arraylist.add(new Variable(i, j));
					}
				}
			Variable var=arraylist.get(new Random().nextInt(arraylist.size()));
			x[var.i].setValuePropagate(var.v);
			it++;
			System . out . println ("loop "+it+" : "+S.violations ( ) );
		}
	}
	
	public static void main(String[] args) {
		Queen q=new Queen(2000);
		q.stateModel();
		q.search();
		q.printHTML("/home/damvantai/workspace/localSearch/queenHTML.html");
		
	}
}