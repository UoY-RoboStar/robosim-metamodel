package circus.robocalc.robosim.impl;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;

import circus.robocalc.robochart.Event;
import circus.robocalc.robochart.Interface;
import circus.robocalc.robochart.RCPackage;
import circus.robocalc.robochart.RoboChartFactory;
import circus.robocalc.robosim.RoboSimFactory;

public class ExecTriggerImplCustom extends ExecTriggerImpl {
	
	static protected Event exec = RoboChartFactory.eINSTANCE.createEvent();
	
	protected ExecTriggerImplCustom()
	{
		super();
		this.setEvent(null);
	}
	
	@Override
	public Event getEvent() {
		Resource rs = this.eResource();
		if (this.event == null && rs != null) { 
			this.setEvent(null);
		}
		return super.getEvent();
	}
	
	@Override
	public void setEvent(Event newEvent) {
		// An ExecTrigger should have only an 'exec' event.
		// So here we can see if the ResourceSet is not null,
		// and if so, lookup the correct Event instance in
		// score.rst.
		//
		// When Xtext parses this the first time around,
		// however, it so happens that there is no eResource/ResourceSet.
		// So for that case, we override the method 'getEvent' above
		// and attempt to set the event if it is still null at that point.
		//
		// This seems to work fine with Xtext/Eclipse, but I'm unsure
		// as to how well this works using the standalone setup, and
		// when used from Epsilon. I think it will be key to guarantee
		// that the 'score.rst' is in scope, but only further testing
		// can guarantee this.
		Resource rs = this.eResource();
		
		if (rs != null) {
			ResourceSet rss = rs.getResourceSet();
			if (rss != null) {
				for (Resource r : rss.getResources()) {
					
					EList<EObject> contents = r.getContents();
					
					if (contents.size() > 0 && contents.get(0) instanceof RCPackage) {
						RCPackage pkg = (RCPackage) contents.get(0);
					
					if (pkg.getName() != null && pkg.getName().equals("robosim__core")
						) {
							for (Interface i : pkg.getInterfaces()) {
								for (Event e : i.getEvents()) {
									if (e.getName().equals("exec"))
										super.setEvent(e);
										//EcoreUtil.)resolveAll(this);
								}
							}
						}
					}
				}
			}
		}
	}
	
}
