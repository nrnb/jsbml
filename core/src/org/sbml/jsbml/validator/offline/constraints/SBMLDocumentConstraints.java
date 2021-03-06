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

import java.util.Map;
import java.util.Set;

import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.validator.SBMLValidator.CHECK_CATEGORY;
import org.sbml.jsbml.validator.offline.ValidationContext;;

/**
 * 
 * @author Roman
 * @since 1.2
 * @date 04.08.2016
 */
public class SBMLDocumentConstraints extends AbstractConstraintDeclaration {

  @Override
  public void addErrorCodesForAttribute(Set<Integer> set, int level,
    int version, String attributeName) {
    // TODO Auto-generated method stub

  }


  @Override
  public void addErrorCodesForCheck(Set<Integer> set, int level, int version,
    CHECK_CATEGORY category) {

    switch (category) {
    case GENERAL_CONSISTENCY:
      set.add(CORE_20201);
      set.add(CORE_20108);
      break;
    case IDENTIFIER_CONSISTENCY:
      break;
    case MATHML_CONSISTENCY:
      break;
    case MODELING_PRACTICE:
      break;
    case OVERDETERMINED_MODEL:
      break;
    case SBO_CONSISTENCY:
      break;
    case UNITS_CONSISTENCY:
      break;
    }
  }


  @Override
  public ValidationFunction<?> getValidationFunction(int errorCode) {
    ValidationFunction<SBMLDocument> func = null;

    switch (errorCode) {
    case CORE_20201:
      func = new ValidationFunction<SBMLDocument>() {

        @Override
        public boolean check(ValidationContext ctx, SBMLDocument d) {

          return d.getModel() != null; // TODO - relaxed for SBML L3V2
        }
      };
      break;
    case CORE_20108:
      func = new ValidationFunction<SBMLDocument>() {

        @Override
        public boolean check(ValidationContext ctx, SBMLDocument d) {

          // go through the getSBMLDocumentAttributes() map
          Map<String, String> attributeMap = d.getSBMLDocumentAttributes();
          
          for (String attributeNameWithPrefix : attributeMap.keySet()) {
            
            if (attributeNameWithPrefix.contains("xmlns") || attributeNameWithPrefix.endsWith("required")) {
              continue;
            }
            
            // If we arrive here, we have an unknown attribute !
            return false;
          }
          
          return true;
        }
      };

    }

    return func;
  }
}
