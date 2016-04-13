/**
 *
 */
package com.ankamagames.dofus.harvey.generic.interfaces;

import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IEditableRandomVariable;
import com.ankamagames.dofus.harvey.engine.generic.inetrfaces.IIEditableGenericRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEditableGenericRandomVariable<Data>
	extends IGenericRandomVariable<Data>, IEditableRandomVariable, IIEditableGenericRandomVariable<Data>
{}