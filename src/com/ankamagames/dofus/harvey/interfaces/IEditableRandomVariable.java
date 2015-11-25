/**
 *
 */
package com.ankamagames.dofus.harvey.interfaces;

import com.ankamagames.dofus.harvey.engine.inetrfaces.IIEditableRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEditableRandomVariable<Data>
	extends IRandomVariable<Data>, IIEditableRandomVariable<Data>
{}