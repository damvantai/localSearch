package khmt57;

import localsearch.functions.basic.FuncPlus;
import localsearch.model.IFunction;
import localsearch.model.LocalSearchManager;
import localsearch.model.VarIntLS;

public class demoInvariants {
	public void test() 
	{
		LocalSearchManager mgr = new LocalSearchManager();
		VarIntLS x = new VarIntLS(mgr, 1, 5);
		VarIntLS y = new VarIntLS(mgr, 1, 5);
		IFunction f = new FuncPlus(x,y);
		mgr.close();
		
		x.setValuePropagate(3);
		y.setValuePropagate(2);
		System.out.println("x = " + x.getValue()
							+", y = " + y.getValue()
							+" funcplus = " +f.getValue());
	}
	
	public static void main(String[] argv)
	{
		demoInvariants t = new demoInvariants();
		t.test();
	}
}
