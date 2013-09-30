package com.garlicg.cutinlib;


public class CutinItem {
	public Class<? extends CutinService> serviceClass;
	public String cutinName;
	public long cutinId = -1;

	/**
	 * Official CUT-IN app uses the serviceClass as identifying for service
	 * intent.
	 * 
	 * @param serviceClass:
	 *            The class extends CutinService need to definite an action on
	 *            Manifest.
	 * @param cutinName:
	 *            Print name of cutIn.
	 */
	public CutinItem(Class<? extends CutinService> serviceClass,
			String cutinName) {
		this.serviceClass = serviceClass;
		this.cutinName = cutinName;
	}
	
	/**
	 * Official CUT-IN app uses the serviceClass as identifying for service
	 * intent.
	 * 
	 * @param serviceClass:
	 *            The class extends CutinService need to definite an action on
	 *            Manifest.
	 * @param cutinName:
	 *            Print name as a cutIn.
	 * @param cutinId: optional cutIn id. default is -1.
	 */
	public CutinItem(Class<? extends CutinService> serviceClass,
			String cutinName, long cutinId) {
		this.serviceClass = serviceClass;
		this.cutinName = cutinName;
		this.cutinId = cutinId;
	}

	@Override
	public String toString() {
		return cutinName;
	}
}
