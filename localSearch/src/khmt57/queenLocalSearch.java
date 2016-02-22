package khmt57;

import localsearch.constraints.alldifferent.AllDifferent;
import localsearch.functions.basic.FuncPlus;
import localsearch.model.ConstraintSystem;
import localsearch.model.IFunction;
import localsearch.model.LocalSearchManager;
import localsearch.model.VarIntLS;
import localsearch.selectors.MinMaxSelector;

public class queenLocalSearch {
	 /**
     * @param args the command line arguments
     */
    
    public void solve(int n)
    {
        LocalSearchManager ls = new LocalSearchManager();	// declare init variable manager local search
        VarIntLS[] x = new VarIntLS[n];						// declare varialbe x have update allow propagate value of x[i] is value of column i
        for (int i = 0; i < n; i++) {
            x[i] = new VarIntLS(ls, 0, n-1);	// init value of x max is n-1 and min is 0
        }
        
        ConstraintSystem S = new ConstraintSystem(ls);	// init constraint system
        S.post(new AllDifferent(x));					// post alldifferent (x) in S
        
        IFunction[] f1 = new IFunction[n];				// init function constraint
        for (int i = 0; i < n; i++) {
            f1[i] = new FuncPlus(x[i],i);				// declare function constraint
        }
        S.post(new AllDifferent(f1));					// post constraint to S
        
        IFunction[] f2 = new IFunction[n];
        for (int i = 0; i < n; i++) {
            f2[i] = new FuncPlus(x[i],-i);
        }
        S.post(new AllDifferent(f2));
        
        ls.close();
        
        MinMaxSelector mms = new MinMaxSelector(S);			// init min max selector
        int it = 0;
        while (it < 1000000 && S.violations() > 0) {            
            VarIntLS sel_x = mms.selectMostViolatingVariable();			// mms select variable have most violate
            int sel_v = mms.selectMostPromissingValue(sel_x);			// mms select varialbe have most promiss
            sel_x.setValuePropagate(sel_v);	
            System.out.println("Step " + it + ", violations = " + S.violations());
            							// 
            it++;
            
        }
    }
    
    // main function
    public static void main(String[] args) {
        // TODO code application logic here
        queenLocalSearch Q = new queenLocalSearch();
        Q.solve(1000);
    }
    
}

