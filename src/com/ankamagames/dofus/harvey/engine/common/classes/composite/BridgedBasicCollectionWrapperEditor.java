/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.classes.composite;

import com.ankamagames.dofus.harvey.engine.common.interfaces.IEditableBasicCollection;
import com.ankamagames.dofus.harvey.engine.common.interfaces.IIEditableBasicCollection;
import com.ankamagames.dofus.harvey.engine.probabilitystrategies.IEditableProbabilityStrategy;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public class BridgedBasicCollectionWrapperEditor
<
	Bridged extends BasicCollectionWrapper<? extends IEditableBasicCollection, ?, ? extends IEditableProbabilityStrategy>&IEditableBasicCollection
>
implements IIEditableBasicCollection
{
	private Bridged _bridged;

	public BridgedBasicCollectionWrapperEditor(final Bridged bridged)
	{
		_bridged = bridged;
	}

	/* (non-Javadoc)
	 * @see com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable#clear()
	 */
	@Override
	public void clear()
	{
		getBridged().getElement().clear();
	}

	/**
	 * @return the _bridged
	 */
	public Bridged getBridged()
	{
		return _bridged;
	}

	/**
	 * @param _bridged the _bridged to set
	 */
	public void setBridged(final Bridged bridged)
	{
		_bridged = bridged;
	}
}