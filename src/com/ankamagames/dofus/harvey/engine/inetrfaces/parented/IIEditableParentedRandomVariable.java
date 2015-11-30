package com.ankamagames.dofus.harvey.engine.inetrfaces.parented;

import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public interface IIEditableParentedRandomVariable
<
	Data,
	ParentType extends IRandomVariable<Data>
>
{
	void setParent(@Nullable ParentType parent);
}