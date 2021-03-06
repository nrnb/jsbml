/*
 * $IdLocalParametersConstraints.java 22:56:03 roman $
 * $URLLocalParametersConstraints.java $
 * ---------------------------------------------------------------------------- 
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML> 
 * for the latest version of JSBML and more information about SBML. 
 * 
 * Copyright (C) 2009-2016  jointly by the following organizations: 
 * 1. The University of Tuebingen, Germany 
 * 2. EMBL European Bioinformatics Institute (EBML-EBI), Hinxton, UK 
 * 3. The California Institute of Technology, Pasadena, CA, USA 
 * 4. The University of California, San Diego, La Jolla, CA, USA
 * 5. The Babraham Institute, Cambridge, UK
 * 
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation. A copy of the license agreement is provided 
 * in the file named "LICENSE.txt" included with this software distribution 
 * and also available online as <http://sbml.org/Software/JSBML/License>. 
 * ---------------------------------------------------------------------------- 
 */
package org.sbml.jsbml.validator.offline.constraints;

import java.util.Set;

import org.sbml.jsbml.JSBML;
import org.sbml.jsbml.LocalParameter;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.validator.SBMLValidator.CHECK_CATEGORY;
import org.sbml.jsbml.validator.offline.ValidationContext;
import org.sbml.jsbml.validator.offline.constraints.helper.UnknownAttributeValidationFunction;
import org.sbml.jsbml.xml.XMLNode;


/**
 * @author Roman
 * @since 1.2
 * @date 07.08.2016
 */
public class LocalParameterConstraints extends AbstractConstraintDeclaration {

  /* (non-Javadoc)
   * @see org.sbml.jsbml.validator.offline.constraints.ConstraintDeclaration#addErrorCodesForCheck(java.util.Set, int, int, org.sbml.jsbml.validator.SBMLValidator.CHECK_CATEGORY)
   */
  @Override
  public void addErrorCodesForCheck(Set<Integer> set, int level, int version,
    CHECK_CATEGORY category) {
    // TODO Auto-generated method stub
    switch (category) {
    case GENERAL_CONSISTENCY:
      set.add(CORE_21124);
      
      if (level > 2) {
        set.add(CORE_21172);
      }
      break;
    case IDENTIFIER_CONSISTENCY:
      break;
    case MATHML_CONSISTENCY:
      break;
    case MODELING_PRACTICE:
      set.add(CORE_80701);
      set.add(CORE_81121);
      break;
    case OVERDETERMINED_MODEL:
      break;
    case SBO_CONSISTENCY:
      break;
    case UNITS_CONSISTENCY:
      break;
    }
  }


  /* (non-Javadoc)
   * @see org.sbml.jsbml.validator.offline.constraints.ConstraintDeclaration#addErrorCodesForAttribute(java.util.Set, int, int, java.lang.String)
   */
  @Override
  public void addErrorCodesForAttribute(Set<Integer> set, int level,
    int version, String attributeName) {
    // TODO Auto-generated method stub

  }


  /* (non-Javadoc)
   * @see org.sbml.jsbml.validator.offline.constraints.AbstractConstraintDeclaration#getValidationFunction(int)
   */
  @Override
  public ValidationFunction<?> getValidationFunction(int errorCode) {
    ValidationFunction<LocalParameter> func = null;
    
    switch (errorCode) {
      
    case CORE_21124:
      func = new ValidationFunction<LocalParameter>() {
        
        @Override
        public boolean check(ValidationContext ctx, LocalParameter lp) 
        {
          if (lp.isSetUserObjects() && lp.getUserObject(JSBML.UNKNOWN_XML) != null)
          {
            XMLNode unknownNode = (XMLNode) lp.getUserObject(JSBML.UNKNOWN_XML);

            // System.out.println("UnknownAttributeValidationFunction - attributes.length = " + unknownNode.getAttributesLength());

            if (unknownNode.getAttributesLength() > 0 && unknownNode.getAttrIndex("constant") != -1) {
              String constant = unknownNode.getAttrValue("constant");
              
              if (! "true".equals(constant)) {
                return false;
              }
            }
          }
          
          return true;
        }
      };
      break;
      
    case CORE_21172:
      func = new UnknownAttributeValidationFunction<LocalParameter>() {
        
        @Override
        public boolean check(ValidationContext ctx, LocalParameter c) {
          // id is a mandatory attribute
          if (!c.isSetId()) {
            return false;
          }
          return super.check(ctx, c);
        }
      };
      break;
      
    case CORE_81121:
      func = new ValidationFunction<LocalParameter>() {
        
        
        @Override
        public boolean check(ValidationContext ctx, LocalParameter lp) {
          
          Model m = lp.getModel();
          
          if (m != null)
          {
            String id = lp.getId();
            
            // ID should't be used by anything else
            return m.getFunctionDefinition(id) == null &&
                m.getCompartment(id) == null &&
                m.getSpecies(id) == null &&
                m.getParameter(id) == null &&
                m.getReaction(id) == null;
          }
          
          return true;
        }
      };
      break;
      
    case CORE_80701:
      func = new ValidationFunction<LocalParameter>() {

        @Override
        public boolean check(ValidationContext ctx, LocalParameter p) {

          // System.out.println("Unit " + p.getUnits());
          return p.isSetUnits();
        }
      };
      break;
    }
    
    return func;
  }

}
