package circus.robocalc.robosim.impl;

import circus.robocalc.robosim.ExecTrigger;

public class RoboSimFactoryImplCustom extends RoboSimFactoryImpl {
	
	@Override
	public ExecTrigger createExecTrigger() {
		ExecTrigger o = new ExecTriggerImplCustom();
		return o;
	}
	
	

}
