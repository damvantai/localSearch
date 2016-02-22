package khmt57;

import localsearch.model.AbstractInvariant;
import localsearch.model.IConstraint;
import localsearch.model.LocalSearchManager;
import localsearch.model.VarIntLS;

public class AllMyDiff extends AbstractInvariant implements IConstraint {

	public AllMyDiff() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public LocalSearchManager getLocalSearchManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VarIntLS[] getVariables() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initPropagate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void propagateInt(VarIntLS arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean verify() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getAssignDelta(VarIntLS arg0, int arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSwapDelta(VarIntLS arg0, VarIntLS arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int violations() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int violations(VarIntLS arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
