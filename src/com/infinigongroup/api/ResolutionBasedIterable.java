/**
 * 
 */
package com.infinigongroup.api;

import java.net.Proxy;

/**
 * @author thvassil
 *
 */
public abstract class ResolutionBasedIterable extends ResultIterable {

	/**
	 * 
	 */
	
	public final String resolution;
	public int iteration_limit = 0;
	
	/**
	 * @param proxy
	 */
	public ResolutionBasedIterable(String resolution, Proxy proxy) {
		super(proxy);
		this.resolution = resolution;
		switch (resolution) {
			case "M":
				this.iteration_limit = 1440;
				break;
			case "H":
				this.iteration_limit = 720;
			case "d":
				this.iteration_limit = 730;
		}
	}
	


	/* (non-Javadoc)
	 * @see com.infinigongroup.api.ResultIterable#getUrl()
	 */

	@Override
	Object getResults() {
		return responseGet("results");
	}


}
