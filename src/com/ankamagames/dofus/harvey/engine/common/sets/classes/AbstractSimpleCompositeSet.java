/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.classes;

import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ICompositeSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.IElementarySet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleCompositeSet;
import com.ankamagames.dofus.harvey.engine.common.sets.interfaces.ISimpleSet;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public abstract class AbstractSimpleCompositeSet
<
	Set extends ISet<Set, SimpleSet, ElementarySet>,
	SimpleSet extends ISimpleSet<Set, SimpleSet, ElementarySet>,
	ElementarySet extends IElementarySet<Set, SimpleSet, ElementarySet>,
	CompositeSet extends ICompositeSet<Set, SimpleSet, ElementarySet, CompositeSet, ?>,
	SimpleCompositeSet extends ISimpleCompositeSet<Set, SimpleSet, ElementarySet, CompositeSet, ElementarySet> 
>
extends AbstractCompositeSet<Set, SimpleSet, ElementarySet, CompositeSet, ElementarySet>
implements ISimpleCompositeSet<Set, SimpleSet, ElementarySet, CompositeSet, ElementarySet>
{		
	protected abstract AbstractSimpleCompositeSetBridge<Set, SimpleSet, ElementarySet, CompositeSet, ? extends AbstractSimpleCompositeSet<Set, SimpleSet, ElementarySet, CompositeSet, SimpleCompositeSet>, ?>  getBridge();

}