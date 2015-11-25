/**
 *
 */
package com.ankamagames.dofus.harvey.interfaces.singlevalues;

import com.ankamagames.dofus.harvey.engine.inetrfaces.singlevalues.IISingleValueRandomVariable;
import com.ankamagames.dofus.harvey.interfaces.IRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface ISingleValueRandomVariable<Data>
	extends IRandomVariable<Data>, IISingleValueRandomVariable<Data>
{}