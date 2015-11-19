/**
 *
 */
package com.ankamagames.dofus.harvey.engine.behaviours.composite;

import java.util.Set;

import com.ankamagames.dofus.harvey.engine.behaviours.IRandomVariableBehaviour;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ICompositeBehaviour<Data, ChildType extends IRandomVariable<Data, ?>>
	extends IRandomVariableBehaviour<Data>
{
	Set<ChildType> getSubVariables();
}
