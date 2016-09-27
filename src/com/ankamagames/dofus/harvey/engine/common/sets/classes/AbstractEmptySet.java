/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import java.util.Iterator;

import com.ankamagames.dofus.harvey.engine.common.sets.classes.iterators.EmptyIterator;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IEmptySet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSet;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractEmptySet
<
	Set extends ISet<Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSet<Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySet<Set, SimpleSet, ElementarySet>,
	EmptySet extends IEmptySet<Set, SimpleSet, ElementarySet, EmptySet>
>
extends AbstractElementarySet<Set, SimpleSet, ElementarySet>
implements IEmptySet<Set, SimpleSet, ElementarySet, EmptySet>
{
	protected boolean _equals(final ISet<?, ?, ?> set)
	{
		return set.isEmpty();
	}

	@Override
	public boolean equals(@Nullable final Object obj)
	{
		if(!(obj instanceof ISet))
			return false;
		return _equals((ISet<?, ?, ?>)obj);
	}

	@Override
	public boolean equals(final Set set)
	{
		return _equals(set);
	}

	@Override
	public boolean isEmpty()
	{
		return true;
	}

	@Override
	public boolean isDegenerate()
	{
		return false;
	}

	@Override
	public boolean isElementary()
	{
		return true;
	}

	@Override
	public boolean isSimple()
	{
		return true;
	}

	@Override
	public double size()
	{
		return 0;
	}

	@Override
	public boolean contains(final Set set)
	{
		return set.isEmpty();
	}

	@Override
	public boolean isContainedBy(final Set set)
	{
		return true;
	}

	@Override
	public boolean isIntersecting(final Set set)
	{
		return false;
	}

	@Override
	public Iterator<? extends IEmptySet<Set, SimpleSet, ElementarySet, EmptySet>> iterator()
	{
		return EmptyIterator.getInstance();
	}

	@Override
	public Set unite(final Set set)
	{
		return set;
	}

	@Override
	public EmptySet intersect(final Set set)
	{
		return getAsEmptySet();
	}

	@Override
	public EmptySet subtract(final Set set)
	{
		return getAsEmptySet();
	}

	@Override
	public Set symmetricSubtract(final Set set)
	{
		return set;
	}

	@Override
	public String toString()
	{
		return "∅";
	}
}