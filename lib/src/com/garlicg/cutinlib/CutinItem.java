package com.garlicg.cutinlib;


public class CutinItem {
	public Class<? extends CutinService> serviceClass;
	public String cutinName;
	public int cutinId = -1;

	/**
	 * Official CUT-IN app uses the serviceClass as identifying for service
	 * intent.
	 * 
	 * @param serviceClass
	 *            The class extends CutinService need to definite an action on
	 *            Manifest.
	 * @param cutinName
	 */
	public CutinItem(Class<? extends CutinService> serviceClass,
			String cutinName) {
		this.serviceClass = serviceClass;
		this.cutinName = cutinName;
	}

	@Override
	public String toString() {
		return cutinName;
	}
}
