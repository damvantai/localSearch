package khmt57;

import localsearch.model.IFunction;
import localsearch.model.LocalSearchManager;
import localsearch.model.VarIntLS;
import localsearch.model.VarIntLS;

import java.util.HashMap;

import localsearch.model.*;

public class MySum extends AbstractInvariant implements IFunction {

	private VarIntLS[] x;
	private LocalSearchManager mgr;
	private int value;
	private int minValue;
	private int maxValue;
	private HashMap<VarIntLS, Integer> map;
	
	public MySum(VarIntLS[] x) {
		// TODO Auto-generated constructor stub
		this.x = x;
		mgr = x[0].getLocalSearchManager();
		map = new HashMap<VarIntLS, Integer>();
		for (int i = 0; i < x.length; i++)
			map.put(x[i], i);
		minValue = 0;
		maxValue = 0;
		for (int i = 0; i < x.length; i++) {
			minValue += x[i].getMinValue();
			maxValue += x[i].getMaxValue();
		}
		
		mgr.post(this);
	}

	@Override
	public LocalSearchManager getLocalSearchManager() {
		// TODO Auto-generated method stub
		return mgr;
	}

	@Override
	public VarIntLS[] getVariables() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public void initPropagate() {
		// TODO Auto-generated method stub
		value = 0;
		for (int i = 0; i < x.length; i++)
			value += x[i].getValue();
	}

	@Override
	public void propagateInt(VarIntLS arg0, int arg1) {
		// TODO Auto-generated method stub
		if (map.get(arg0) == null) return;
		value = value - arg0.getOldValue() + arg0.getValue();
	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getAssignDelta(VarIntLS arg0, int arg1) {
		// TODO Auto-generated method stub
		if (map.get(arg0) == null) return 0;
		int newV = value - arg0.getValue() + arg1;
		return newV - value;
	}

	@Override
	public int getMaxValue() {
		// TODO Auto-generated method stub
		return maxValue;
	}

	@Override
	public int getMinValue() {
		// TODO Auto-generated method stub
		return minValue;
	}

	@Override
	public int getSwapDelta(VarIntLS arg0, VarIntLS arg1) {
		// TODO Auto-generated method stub
		if (map.get(arg0) == null && map.get(arg1) == null) return 0;
		if (map.get(arg0) != null && map.get(arg1) != null) return 0;
		if (map.get(arg0) == null && map.get(arg1) != null)
			return getAssignDelta(arg1, arg0.getValue());
		return getAssignDelta(arg0, arg1.getValue());
	}

	@Override
	public int getValue() {
		// TODO Auto-generated method stub
		return value;
	}
	
	public static void main(String[] args) {
		LocalSearchManager mgr = new LocalSearchManager();
		VarIntLS x[] = new VarIntLS[10];
		for (int i = 0; i < 10; i++) {
			x[i] = new VarIntLS(mgr, 1, 100);
			x[i].setValue(i +1);
		}
		MySum a = new MySum(x);
		mgr.close();
		
		for (int i = 0; i < 10; i++)
			System.out.println(x[i].getValue());
		System.out.println("Sum =" + a.getValue());
		System.out.println("Sum = " + x[3].getValue());
		x[3].setValuePropagate(15);
		
		System.out.println("Sum = " + a.getValue());
	}
}
