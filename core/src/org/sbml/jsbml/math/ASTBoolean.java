/*
 * $Id$
 * $URL$
 * ---------------------------------------------------------------------------- 
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML> 
 * for the latest version of JSBML and more information about SBML. 
 * 
 * Copyright (C) 2009-2014  jointly by the following organizations: 
 * 1. The University of Tuebingen, Germany 
 * 2. EMBL European Bioinformatics Institute (EBML-EBI), Hinxton, UK 
 * 3. The California Institute of Technology, Pasadena, CA, USA 
 * 4. The University of California, San Diego, La Jolla, CA, USA
 * 5. The Babraham Institute, Cambridge, UK
 * 6. The University of Toronto, Toronto, ON, Canada
 * 
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation. A copy of the license agreement is provided 
 * in the file named "LICENSE.txt" included with this software distribution 
 * and also available online as <http://sbml.org/Software/JSBML/License>. 
 * ---------------------------------------------------------------------------- 
 */
package org.sbml.jsbml.math;

import javax.swing.tree.TreeNode;

import org.sbml.jsbml.MathContainer;
import org.sbml.jsbml.ASTNode.Type;
import org.sbml.jsbml.math.compiler.ASTNode2Compiler;
import org.sbml.jsbml.util.compilers.ASTNodeValue;


/**
 * An Abstract Syntax Tree (AST) node representing a boolean
 * 
 * @author Victor Kofia
 * @version $Rev$
 * @since 1.0
 * @date Jul 18, 2014
 */
public class ASTBoolean extends AbstractASTNode {
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * Copy constructor; Creates a deep copy of the given {@link ASTBoolean}.
   * 
   * @param node
   *            the {@link ASTBoolean} to be copied.
   */
  public ASTBoolean(ASTBoolean bool) {
    super(bool);
  }
  
  /**
   * Creates a new {@link ASTBoolean}.
   */
  public ASTBoolean(Type type) {
    super();
    setType(type);
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.AbstractTreeNode#clone()
   */
  @Override
  public TreeNode clone() {
    return new ASTBoolean(this);
  }
  
  /* (non-Javadoc)
   * @see org.sbml.jsbml.math.ASTNode2#compile(org.sbml.jsbml.util.compilers.ASTNode2Compiler)
   */
  @Override
  public ASTNodeValue compile(ASTNode2Compiler compiler) {
    ASTNodeValue value = null;
    switch(getType()) {
    case CONSTANT_TRUE:
      value = compiler.getConstantTrue();
      break;
    case CONSTANT_FALSE:
      value = compiler.getConstantFalse();
      break;
    default:
      value = compiler.unknownValue();
    }
    value.setType(getType());
    MathContainer parent = getParentSBMLObject();
    if (parent != null) {
      value.setLevel(parent.getLevel());
      value.setVersion(parent.getVersion());
    }
    return value;
  }


  /* (non-Javadoc)
   * @see javax.swing.tree.TreeNode#getAllowsChildren()
   */
  @Override
  public boolean getAllowsChildren() {
    return false;
  }

  /* (non-Javadoc)
   * @see javax.swing.tree.TreeNode#getChildAt(int)
   */
  @Override
  public ASTNumber getChildAt(int childIndex) {
    throw new IndexOutOfBoundsException();
  }

  /* (non-Javadoc)
   * @see javax.swing.tree.TreeNode#getChildCount()
   */
  @Override
  public int getChildCount() {
    return 0;
  }
  
}
