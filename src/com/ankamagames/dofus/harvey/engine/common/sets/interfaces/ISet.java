/**
 *
 */
package com.ankamagames.dofus.harvey.engine.common.sets.interfaces;

import org.eclipse.jdt.annotation.NonNullByDefault;


/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ISet<Set extends ISet<Set>>
{
	boolean isEmpty();
	
	/**
	 * The choice of a double as a return type has been made so that this value
	 * can hold the large values of a long as well as the "non integer" values of
	 * a float
	 * 
	 * @return
	 */
	double size();
	
	boolean isDegenerate();

	boolean contains(Set set);
	
	boolean isContainedBy(Set set);

	boolean intersects(Set set);
	
	ICompositeSet<Set, ?> getMergedSet();
}