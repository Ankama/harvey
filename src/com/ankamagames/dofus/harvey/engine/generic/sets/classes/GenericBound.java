/**
 *
 */
package com.ankamagames.dofus.harvey.engine.generic.sets.classes;

import java.util.Comparator;

import com.ankamagames.dofus.harvey.engine.generic.comparators.DefaultComparableComparator;
import com.ankamagames.dofus.harvey.engine.generic.incrementors.SurroundingValuesProvider;
import com.ankamagames.dofus.harvey.generic.sets.interfaces.IGenericBound;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public class GenericBound<Data>
extends AbstractGenericBound<Data, IGenericBound<Data>>
implements IGenericBound<Data>
{
	protected Comparator<? super Data> _comparator;
	protected SurroundingValuesProvider<Data> _surroundingProvider;

	public static <Data extends Comparable<Data>> GenericBound<Data> makeBound(final @Nullable Data value, final SurroundingValuesProvider<Data> surroundingProvider)
	{
		final DefaultComparableComparator<Data> comparator = DefaultComparableComparator.getInstance();
		return new GenericBound<Data>(value, comparator, surroundingProvider);
	}

	public static <Data> GenericBound<Data> makeBound(final @Nullable Data value, final Comparator<? super Data> comparator, final SurroundingValuesProvider<Data> surroundingProvider)
	{
		return new GenericBound<Data>(value, comparator, surroundingProvider);
	}

	protected GenericBound(final @Nullable Data value, final Comparator<? super Data> comparator, final SurroundingValuesProvider<Data> surroundingProvider)
	{
		super(value);
		_comparator = comparator;
		_surroundingProvider = surroundingProvider;
	}

	@Override
	protected Comparator<? super Data> getComparator()
	{
		return _comparator;
	}

	@Override
	public boolean isPreceding(final IGenericBound<Data> bound)
	{
		final Data predecessor = _surroundingProvider.getPredecessor(bound.getValue());
		if (predecessor != null)
			return predecessor.equals(getValue());
		return getValue() == null;
	}

	@Override
	public boolean isSucceeding(final IGenericBound<Data> bound)
	{
		final Data succcessor = _surroundingProvider.getSuccessor(bound.getValue());
		if (succcessor != null)
			return succcessor.equals(getValue());
		return getValue() == null;
	}

	@Override
	public String toString()
	{
		if(_value != null)
			return _value.toString();
		return "null";
	}
}