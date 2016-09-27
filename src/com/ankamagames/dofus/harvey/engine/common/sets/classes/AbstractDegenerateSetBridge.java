/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.IEqualityToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes.ISetCreationToolbox;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IDegenerateSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractDegenerateSetBridge
<
	Set extends ISet<Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSet<Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySet<Set, SimpleSet, ElementarySet>,
	DegenerateSet extends IDegenerateSet<Set, SimpleSet, ElementarySet, DegenerateSet>,
	BridgedSet extends IDegenerateSet<Set, SimpleSet, ElementarySet, DegenerateSet>
>
extends AbstractSetBridge<Set, SimpleSet, ElementarySet, BridgedSet>
{
	public AbstractDegenerateSetBridge(final BridgedSet bridged)
	{
		super(bridged);
	}

	protected abstract IEqualityToolbox<Set, SimpleSet, ElementarySet, BridgedSet> getEqualityToolbox();

	protected abstract ISetCreationToolbox<Set, SimpleSet, ElementarySet, ?, BridgedSet> getSetCreationToolbox();

	public boolean isEqual(@Nullable final Object obj)
	{
		if(!(obj instanceof ISet))
			return getEqualityToolbox().equalsValue(obj);
		final ISet<?, ?, ?> set = (ISet<?, ?, ?>)obj;
		if(set.isEmpty())
			return false;
		if(!set.isDegenerate())
			return false;
		try
		{
			@SuppressWarnings("unchecked")
			final Set sameTypeSet = (Set)set;
			return getEqualityToolbox().equalsDegenerateSet(sameTypeSet);
		}
		catch(final ClassCastException e)
		{
			return false;
		}
	}

	public boolean isEqual(final Set set)
	{
		if(!set.isDegenerate())
			return false;

		return getEqualityToolbox().equalsDegenerateSet(set);
	}

	public boolean contains(final Set set)
	{
		if(set.isEmpty())
			return true;
		if(!set.isDegenerate())
			return false;
		return getEqualityToolbox().equalsDegenerateSet(set);
	}

	public boolean isContainedBy(final Set set)
	{
		return set.contains(_bridged.getAsSet());
	}

	public Set unite(final Set set)
	{
		if(set.isEmpty())
			return _bridged.getAsSet();
		if(set.isDegenerate())
			if(getEqualityToolbox().equalsDegenerateSet(set))
				return _bridged.getAsSet();
			else if(set.isElementary())
				return getSetCreationToolbox().makeComposite(set).getAsSet();
			else
				return set.unite(_bridged.getAsSet()).getAsSet();
		if(isContainedBy(set))
			return set;
		return set.unite(_bridged.getAsSet()).getAsSet();
	}

	public Set intersect(final Set set)
	{
		if(set.isEmpty())
			return set;
		if(set.isDegenerate())
			if(getEqualityToolbox().equalsDegenerateSet(set))
				return _bridged.getAsSet();
			else
				return getSetCreationToolbox().makeEmptySet().getAsSet();
		if(_bridged.isContainedBy(set))
			return _bridged.getAsSet();
		return getSetCreationToolbox().makeEmptySet().getAsSet();
	}

	public Set subtract(final Set set)
	{
		if(isContainedBy(set))
			return getSetCreationToolbox().makeEmptySet().getAsSet();
		return _bridged.getAsSet();
	}

	public Set symmetricSubtract(final Set set)
	{
		if(isContainedBy(set))
			return set.subtract(_bridged.getAsSet()).getAsSet();
		return unite(set);
	}
}