/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.longs.interfaces;

import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IEditableRandomVariable;
import com.ankamagames.dofus.harvey.engine.numeric.longs.inetrfaces.IIEditableLongRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEditableLongRandomVariable
extends ILongRandomVariable, IEditableRandomVariable, IIEditableLongRandomVariable
{}