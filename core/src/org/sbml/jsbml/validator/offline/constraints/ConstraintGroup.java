/*
 * $Id$
 * $URL$
 * ----------------------------------------------------------------------------
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML>
 * for the latest version of JSBML and more information about SBML.
 * Copyright (C) 2009-2016 jointly by the following organizations:
 * 1. The University of Tuebingen, Germany
 * 2. EMBL European Bioinformatics Institute (EBML-EBI), Hinxton, UK
 * 3. The California Institute of Technology, Pasadena, CA, USA
 * 4. The University of California, San Diego, La Jolla, CA, USA
 * 5. The Babraham Institute, Cambridge, UK
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation. A copy of the license agreement is provided
 * in the file named "LICENSE.txt" included with this software distribution
 * and also available online as <http://sbml.org/Software/JSBML/License>.
 * ----------------------------------------------------------------------------
 */

package org.sbml.jsbml.validator.offline.constraints;

import java.util.HashSet;
import java.util.Set;

import org.sbml.jsbml.validator.offline.ValidationContext;

public class ConstraintGroup<T> extends AbstractConstraint<T> {

  private Set<AnyConstraint<T>> constraints = new HashSet<AnyConstraint<T>>();


  public ConstraintGroup() {
    super(CoreSpecialErrorCodes.ID_GROUP);
  }


  @Override
  public boolean check(ValidationContext context, T object) {
    context.willValidate(this, object);

    boolean success = true;
    for (AnyConstraint<T> c : this.constraints) {
      if (c != null) {
        success = c.check(context, object) && success;
      }
    }
    context.didValidate(this, object, success);

    return success;
  }


  /**
   * Adds a constraint to the group. The constraints are collected in a
   * {@link Set}, so every constraint can only added onces.
   * 
   * @param c
   */
  public void add(AnyConstraint<T> c) {

    if (c != null) {
      this.constraints.add(c);
    }
  }


  /**
   * Removes the constraint from the group.
   * 
   * @param constraint
   * @return <code>true</code> if constraint was in group
   */
  public boolean remove(AnyConstraint<T> constraint) {
    return this.constraints.remove(constraint);
  }


  /**
   * Checks if the errorCode is in the group.
   * <p>
   * Notice that every {@link ConstraintGroup} uses the same error code
   * {@link CoreSpecialErrorCodes#ID_GROUP}. Use
   * {@link #contains(AnyConstraint)} instead.
   * 
   * @param errorCode
   * @return <code>true</code> if the error code is in the group
   * @see #contains(AnyConstraint)
   */
  public boolean contains(int errorCode) {
    for (AnyConstraint<T> con : this.constraints) {
      if (con.getErrorCode() == errorCode) {
        return true;
      }
    }
    return false;
  }


  /**
   * Checks if the constraint is in the group.
   * 
   * @param c
   * @return
   */
  public boolean contains(AnyConstraint<T> c) {
    return this.constraints.contains(c);
  }


  /**
   * Returns the members of the group.
   * 
   * @return
   */
  public AnyConstraint<T>[] getConstraints() {
    @SuppressWarnings("unchecked")
    AnyConstraint<T>[] constraints =
    this.constraints.toArray(new AnyConstraint[this.constraints.size()]);
    return constraints;
  }
  
  /**
   * Returns the number of constraints in this group
   * @return
   */
  public int getConstraintsCount()
  {
    return this.constraints.size();
  }
 
}
