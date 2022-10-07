package io.apicurio.umg.pipe;

import lombok.Getter;

/**
 * Base class for all pipeline stages.
 */
public abstract class AbstractStage implements Stage {
	
	@Getter
	private GenState state;

	@Override
	public void process(GenState state) {
		this.state = state;
		this.doProcess();
	}
	
	/**
	 * Perform the logic of the stage.
	 */
	protected abstract void doProcess();

}
