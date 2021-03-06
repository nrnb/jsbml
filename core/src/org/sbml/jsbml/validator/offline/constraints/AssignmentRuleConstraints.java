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
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.sbml.jsbml.ASTNode;
import org.sbml.jsbml.AssignmentRule;
import org.sbml.jsbml.ExplicitRule;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.Rule;
import org.sbml.jsbml.Variable;
import org.sbml.jsbml.validator.SBMLValidator.CHECK_CATEGORY;
import org.sbml.jsbml.validator.offline.ValidationContext;
import org.sbml.jsbml.validator.offline.constraints.helper.UnknownAttributeValidationFunction;
import org.sbml.jsbml.validator.offline.constraints.helper.ValidationTools;

/**
 * @author Roman
 * @since 1.2
 * @date 05.08.2016
 */
public class AssignmentRuleConstraints extends AbstractConstraintDeclaration {

  @Override
  public void addErrorCodesForCheck(Set<Integer> set, int level, int version,
    CHECK_CATEGORY category) {
    switch (category) {
    case GENERAL_CONSISTENCY:
      set.add(CORE_20901);

      if (level == 1) {
        set.add(CORE_99106);
        set.add(CORE_99129);
      } else if (level == 2) {
        set.add(CORE_20903);
        if (version == 1) {
          set.add(CORE_99106);
        }
      }
      else if (level == 3)
      {
        set.add(CORE_20903);
        set.add(CORE_20908);
      }

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
  public void addErrorCodesForAttribute(Set<Integer> set, int level,
    int version, String attributeName) {
    // TODO Auto-generated method stub

  }


  @Override
  public ValidationFunction<?> getValidationFunction(int errorCode) {
    ValidationFunction<AssignmentRule> func = null;

    switch (errorCode) {
    case CORE_20901:
      func = new ValidationFunction<AssignmentRule>() {

        @Override
        public boolean check(ValidationContext ctx, AssignmentRule r) {

          if (r.isSetVariable()) {

            Variable var = r.getVariableInstance();
            
            return ValidationTools.isValidVariable(var, ctx.getLevel());
            
          }

          return true;
        }
      };
      break;
      
    case CORE_20903:
      func = new ValidationFunction<AssignmentRule>() {

        @Override
        public boolean check(ValidationContext ctx, AssignmentRule r) {

          Variable var = r.getVariableInstance();

          if (var != null) {
            return !var.getConstant();
          }

          return true;
        }
      };
      break;

    case CORE_20908:
      func = new UnknownAttributeValidationFunction<AssignmentRule>() {
        
        @Override
        public boolean check(ValidationContext ctx, AssignmentRule rule) {
          // variable is a mandatory attribute
          if (!rule.isSetVariable()) {
            return false;
          }
          return super.check(ctx, rule);
        }
      };
      break;
      
    case CORE_99106:
      func = new ValidationFunction<AssignmentRule>() {

        @Override
        public boolean check(ValidationContext ctx, AssignmentRule r) {

          Model m = r.getModel();

          if (r.isSetMath() && m != null) {

            boolean later = false;
            Set<String> defienedLater = new HashSet<String>();

            // Collect all rules which were defined AFTER this one
            // Adds also the name of this rules, because it'S also permitted to
            // reference the rule in itself.
            for (Rule rule : m.getListOfRules()) {
              if (rule.isAssignment()) {

                ExplicitRule eRule = (ExplicitRule) rule;
                if (eRule.getVariable() == r.getVariable()) {
                  later = true;
                }

                if (later) {
                  defienedLater.add(eRule.getVariable());
                }
              }
            }

            Queue<ASTNode> toCheck = new LinkedList<ASTNode>();
            toCheck.offer(r.getMath());

            while (!toCheck.isEmpty()) {
              ASTNode node = toCheck.poll();

              if (node.isName()) {
                if (defienedLater.contains(node.getName())) {
                  return false;
                }
              }

              toCheck.addAll(node.getListOfNodes());
            }
          }

          return true;
        }

      };
      break;

    case CORE_99129:
      func = new ValidationFunction<AssignmentRule>() {

        public boolean check(ValidationContext ctx, AssignmentRule ar) {
          return ValidationTools.containsMathOnlyPredefinedFunctions(
            ar.getMath());
        }
      };
      break;
    }

    return func;
  }

}
