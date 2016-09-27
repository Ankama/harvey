/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes.toolboxes;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSet;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEqualityToolbox
<
	Set extends ISet<Set, SimpleSet, ElementarySet>, 
	SimpleSet extends ISimpleSet<Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySet<Set, SimpleSet, ElementarySet>,
	Bridged extends ISet<Set, SimpleSet, ElementarySet>
>
{
	boolean equalsValue(@Nullable Object obj);

	boolean equalsDegenerateSet(Set set);
}