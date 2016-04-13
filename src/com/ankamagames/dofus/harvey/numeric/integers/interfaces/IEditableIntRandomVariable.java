/**
 *
 */
package com.ankamagames.dofus.harvey.numeric.integers.interfaces;

import com.ankamagames.dofus.harvey.engine.common.randomvariables.interfaces.IEditableRandomVariable;
import com.ankamagames.dofus.harvey.engine.numeric.integers.inetrfaces.IIEditableIntRandomVariable;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * @author sgros
 *
 */
@NonNullByDefault
public interface IEditableIntRandomVariable
extends IIntRandomVariable, IEditableRandomVariable, IIEditableIntRandomVariable
{}