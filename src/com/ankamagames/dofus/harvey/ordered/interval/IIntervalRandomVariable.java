package com.ankamagames.dofus.harvey.ordered.interval;

import com.ankamagames.dofus.harvey.interfaces.IEditableCompositeRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IOrderedRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IIntervalRandomVariable<Data, ParentType extends IEditableCompositeRandomVariable<Data, ?, ?>>
	extends IOrderedRandomVariable<Data, ParentType>
{
	Data getLowerBound();
	Data getUpperBound();
}
