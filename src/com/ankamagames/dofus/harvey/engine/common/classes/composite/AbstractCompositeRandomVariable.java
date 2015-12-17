/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.classes.composite;

import java.util.Collection;
import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.interfaces.IBasicCollection;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractCompositeRandomVariable
<
	ChildType extends BasicCollectionWrapper<?, ?, ?>
>
implements IBasicCollection
{
	abstract protected Collection<ChildType> getElements();

	protected Iterator<ChildType> iterator()
	{
		return getElements().iterator();
	}

	@Override
	public boolean isEmpty()
	{
		for(final ChildType element:getElements())
			if(!element.isEmpty())
				return false;
		return true;
	}
}