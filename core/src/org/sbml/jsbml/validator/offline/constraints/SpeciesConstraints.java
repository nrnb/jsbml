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

import java.util.Set;

import org.sbml.jsbml.Compartment;
import org.sbml.jsbml.ExplicitRule;
import org.sbml.jsbml.JSBML;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.Parameter;
import org.sbml.jsbml.Reaction;
import org.sbml.jsbml.Rule;
import org.sbml.jsbml.Species;
import org.sbml.jsbml.SpeciesReference;
import org.sbml.jsbml.UnitDefinition;
import org.sbml.jsbml.validator.SBMLValidator.CHECK_CATEGORY;
import org.sbml.jsbml.validator.offline.ValidationContext;
import org.sbml.jsbml.validator.offline.constraints.helper.SBOValidationConstraints;
import org.sbml.jsbml.validator.offline.constraints.helper.UnknownAttributeValidationFunction;
import org.sbml.jsbml.validator.offline.constraints.helper.ValidationTools;
import org.sbml.jsbml.xml.XMLNode;

/**
 * 
 * @author Roman
 * @since 1.2
 * @date 04.08.2016
 */
@SuppressWarnings("deprecation")
public class SpeciesConstraints extends AbstractConstraintDeclaration{

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
      set.add(CORE_20601);
      set.add(CORE_20608);
      set.add(CORE_20610);
      set.add(CORE_20614);

      if (level == 2)
      {
        set.add(CORE_20604);
        set.add(CORE_20609);
        set.add(CORE_20611);

        if (version < 3)
        {
          set.add(CORE_20602);
          set.add(CORE_20603);
        }

        if (version > 1)
        {
          set.add(CORE_20612);
          set.add(CORE_20613);
        }

        if (version > 2)
        {
          
          set.add(CORE_20615);
        }
      }
      else if (level == 3)
      {
        set.add(CORE_20609);
        set.add(CORE_20616);
        set.add(CORE_20617);
        set.add(CORE_20623);
        set.add(CORE_20705);
      }

      break;

    case MODELING_PRACTICE:
      if (level > 1)
      {
        set.add(CORE_80601);
      }
      break;
    case SBO_CONSISTENCY:
      if ((level == 2 && version > 2) || level > 2)
      {
        set.add(CORE_10713);
      }
      break;
    case IDENTIFIER_CONSISTENCY:
      break;
    case MATHML_CONSISTENCY:
      break;
    case OVERDETERMINED_MODEL:
      break;
    case UNITS_CONSISTENCY:
      
      set.add(CORE_20608);
      
      if (level == 2 && version < 3)
      {
        addRangeToSet(set, CORE_20605, CORE_20607);
      }
      
      if (level > 2)
      {
        set.add(CORE_99508);
      }
    
      break;
    }
  }



  @Override
  public ValidationFunction<?> getValidationFunction(int errorCode) {
    ValidationFunction<Species> func = null;

    switch (errorCode) {
    case CORE_10713:
      return SBOValidationConstraints.isMaterialEntity;
      
    case CORE_20601:
      func = new ValidationFunction<Species>() {
        @Override
        public boolean check(ValidationContext ctx, Species s) {
          /*
           * Invalid value found for Species 'compartment' attribute
           */
          if (s.isSetCompartment() && s.getModel() != null) {
            return s.getCompartmentInstance() != null;
          }

          return true;
        }
      };
      break;
    case CORE_20602:
      func = new ValidationFunction<Species>() {


        @Override
        public boolean check(ValidationContext ctx, Species s) {
          /*
           * Invalid value found for Species 'compartment' attribute
           */
          if (s.hasOnlySubstanceUnits()) {
            return !s.isSetSpatialSizeUnits();
          }

          return true;
        }
      };
      break;

    case CORE_20603:
      func = new ValidationFunction<Species>() {


        @Override
        public boolean check(ValidationContext ctx, Species s) {

          if (s.getModel() != null)
          {
            Compartment c = s.getCompartmentInstance();

            if (c != null && c.getSpatialDimensions() == 0) {
              return !s.isSetSpatialSizeUnits();
            }
          }

          return true;
        }
      };
      break;

    case CORE_20604:
      func = new ValidationFunction<Species>() {
        @Override
        public boolean check(ValidationContext ctx, Species s) {

          Compartment c = s.getCompartmentInstance();

          if (c != null && c.getSpatialDimensions() == 0) {
            return !s.isSetInitialConcentration();
          }

          return true;
        }
      };
      break;

    case CORE_20605:
      func = new ValidationFunction<Species>() {
        @Override
        public boolean check(ValidationContext ctx, Species s) {

          Compartment c = s.getCompartmentInstance();

          if (c != null && c.getSpatialDimensions() == 1 && s.isSetSpatialSizeUnits()) {
            UnitDefinition def = s.getSpatialSizeUnitsInstance();

            if (def == null)
            {
              return false;
            }
     
            boolean isLength = def.isVariantOfLength();

            if (ctx.getLevel() == 2 && ctx.getLevel() == 1) {
              return isLength;
            }

            if (ctx.getLevel() >= 2) {
              return def.isVariantOfDimensionless() || isLength;
            }
          }

          return true;
        }
      };
      break;

    case CORE_20606:
      func = new ValidationFunction<Species>() {
        @Override
        public boolean check(ValidationContext ctx, Species s) {

          Compartment c = s.getCompartmentInstance();

          if (c != null && c.getSpatialDimensions() == 2 && s.isSetSpatialSizeUnits()) {
    
            UnitDefinition def = s.getSpatialSizeUnitsInstance();
            
            if (def == null)
            {
              return false;
            }
            
            boolean isArea = def.isVariantOfArea();

            if (ctx.getLevel() == 2 && ctx.getLevel() == 1) {
              return isArea;
            }

            if (ctx.getLevel() >= 2) {

              return def.isVariantOfDimensionless() || isArea;
            }
          }

          return true;
        }
      };
      break;

    case CORE_20607:
      func = new ValidationFunction<Species>() {

        @Override
        public boolean check(ValidationContext ctx, Species s) {

          Compartment c = s.getCompartmentInstance();

          if (c != null && c.getSpatialDimensions() == 3 && s.isSetSpatialSizeUnits()) {
        
            UnitDefinition def = s.getSpatialSizeUnitsInstance();

            if (def == null)
            {
              return false;
            }
            
            boolean isVolume = def.isVariantOfVolume();

            if (ctx.getLevel() == 2 && ctx.getLevel() == 1) {
              return isVolume;
            }

            if (ctx.getLevel() >= 2) {

              return def.isVariantOfDimensionless() || isVolume;
            }
          }

          return true;
        }
      };
      break;
    case CORE_20608:
      func = new ValidationFunction<Species>() {
        
        
        @Override
        public boolean check(ValidationContext ctx, Species s) {

          if (s.isSetSubstanceUnits())
          {
            
            UnitDefinition ud = s.getSubstanceUnitsInstance();
            
            if (ud == null)
            {
              return false;
            }

            if (ctx.getLevel() == 1 || (ctx.getLevel() == 2 && ctx.getVersion() == 1))
            {
              return ud.isVariantOfSubstance();
            }
            else
            {
              return ud.isVariantOfSubstance() || ud.isVariantOfDimensionless();
            }
          }
          
          return true;
        }
      };
      break;
      
    case CORE_20609:
      func = new ValidationFunction<Species>() {
        
        
        @Override
        public boolean check(ValidationContext ctx, Species s) {

          if (s.isSetUserObjects() && s.getUserObject(JSBML.UNKNOWN_XML) != null)
          {
            XMLNode unknownNode = (XMLNode) s.getUserObject(JSBML.UNKNOWN_XML);

            if (unknownNode.getAttributesLength() > 0) {
              return (unknownNode.getAttrIndex("initialConcentration") == -1) && (unknownNode.getAttrIndex("initialAmount") == -1);  
            }
          }
          
          return true;
        }
      };
      break;
      
    case CORE_20610:
      func = new ValidationFunction<Species>() {

        @Override
        public boolean check(ValidationContext ctx, Species s) {
          Model m = s.getModel();

          if (!s.isBoundaryCondition() && 
              !s.isConstant() && 
              m != null)
          {

            boolean found = false;

            for (Rule r: m.getListOfRules())
            {
              if (r.isAssignment() || r.isRate())
              {
                ExplicitRule er = (ExplicitRule) r;
                if (er.getVariable().equals(s.getId()))
                {
                  found = true;
                  break;
                }
              }
            }

            // If the Species is not assigned by a rule, there couldn't be a collision
            if (!found)
            {
              return true;
            }

            // This species can't be part of a Reaction
            for (Reaction r:m.getListOfReactions())
            {
              for (SpeciesReference sr: r.getListOfProducts())
              {
                if (sr.getSpecies().equals(s.getId()))
                {
                  return false;
                }
              }

              for (SpeciesReference sr: r.getListOfReactants())
              {
                if (sr.getSpecies().equals(s.getId()))
                {
                  return false;
                }
              }
            }
          }

          return true;
        }
      };
      break;

    case CORE_20612:
      func = new ValidationFunction<Species>() {

        @Override
        public boolean check(ValidationContext ctx, Species s) {
          if (s.isSetSpeciesType()) {

            return s.getSpeciesTypeInstance() != null;
          }
          return true;
        }
      };
      break;
      
    case CORE_20613:
      func = new ValidationFunction<Species>() {

        @Override
        public boolean check(ValidationContext ctx, Species s) {
          
          Model m = s.getModel();
          if (s.isSetSpeciesType() && m != null)
          {
            String st = s.getSpeciesType();
            
            for (Species spec: m.getListOfSpecies())
            {
              // Are species in same compartment but not the same?
              if (spec.isSetSpeciesType() &&
                  spec.getCompartment().equals(s.getCompartment()) &&
                  !spec.getId().equals(s.getId()))
              {
                // Must have different Types
                if (spec.getSpeciesType().equals(st))
                {
                  return false;
                }
              }
            }
          }
          
          return true;
        }
      };
      break;

    case CORE_20614:
      func = new ValidationFunction<Species>() {
        @Override
        public boolean check(ValidationContext ctx, Species s) {
          
          return s.isSetCompartment();
        }
      };
      break;

    case CORE_20615:
      func = new ValidationFunction<Species>() {
        @Override
        public boolean check(ValidationContext ctx, Species s) {
          return !s.isSetSpatialSizeUnits();
        }
      };
      break;

    case CORE_20616:
      func = new ValidationFunction<Species>() {
        @Override
        public boolean check(ValidationContext ctx, Species s) {
          return s.isSetSubstanceUnits() || s.getModel().isSetSubstanceUnits();
        }
      };
      break;
      
    case CORE_20617:
      func = new ValidationFunction<Species>() {
        @Override
        public boolean check(ValidationContext ctx, Species s) {
          if (s.isSetConversionFactor()) {
            return s.getConversionFactorInstance() != null;
          }

          return true;
        }
      };
      break;
      
    case CORE_20623:
      func = new UnknownAttributeValidationFunction<Species>() {
        
        @Override
        public boolean check(ValidationContext ctx, Species c) {
          // id 'compartment', 'hasOnlySubstanceUnits', 'boundaryCondition'and constant are mandatory attributes
          if (!c.isSetId() || !c.isSetConstant() || !c.isSetHasOnlySubstanceUnits()
              || !c.isSetBoundaryCondition() || !c.isSetCompartment())
          {
            return false;
          }
          return super.check(ctx, c);
        }
      };
      break;
      
    case CORE_20705:
      func = new ValidationFunction<Species>() {
        @Override
        public boolean check(ValidationContext ctx, Species s) {
          if (s.isSetConversionFactor())
          {
            Parameter fac = s.getConversionFactorInstance();
            return fac != null && fac.isConstant();
          }
          
          return true;
        }
      };
      break;
      
    case CORE_80601:
      func = new ValidationFunction<Species>() {
        @Override
        public boolean check(ValidationContext ctx, Species s) {
          
          Model m = s.getModel();
          
          if (m != null && !s.isSetInitialAmount() && !s.isSetInitialConcentration())
          {
            boolean setByAssignment = false;
            
            if (s.isSetId())
            {
              setByAssignment = m.getInitialAssignment(s.getId()) != null;
              
              if (!setByAssignment)
              {
                Rule r = m.getRule(m.getId());
                setByAssignment = r != null && r.isAssignment();
              }
            }
            
            return setByAssignment;
          }
          
          return true;
        }
      };
      break;
      
    case CORE_99508:
      return ValidationTools.checkDerivedUnit;
    }

    return func;
  }
}
