/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author blefevre
 *
 */
@NonNullByDefault
public interface ICompositeRandomVariable
<
	Set extends ISet<Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSet<Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySet<Set, SimpleSet, ElementarySet>,
	RandomVariable extends ICompositeRandomVariable<Set, SimpleSet, ElementarySet, RandomVariable, IterableType, ElementsType>,
	IterableType extends IRandomVariable<Set, SimpleSet, ElementarySet, RandomVariable, IterableType, ElementsType>,
	ElementsType extends ISet<Set, SimpleSet, ElementarySet>
>
extends IRandomVariable<Set, SimpleSet, ElementarySet, RandomVariable, IterableType, ElementsType>
{

}
