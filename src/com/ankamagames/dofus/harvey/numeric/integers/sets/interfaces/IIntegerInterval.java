/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.integers.sets.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IInterval;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IIntegerInterval
extends
IInterval<IIntegerBound, IIntegerSet, ISimpleIntegerSet, IElementaryIntegerSet, IIntegerInterval>,
IIntegerSet,
IElementaryIntegerSet
{
	@Override
	IIntegerInterval getSimpleSet();
}