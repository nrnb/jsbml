/*
 * $Id$
 * $URL$
 * ----------------------------------------------------------------------------
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML>
 * for the latest version of JSBML and more information about SBML.
 *
 * Copyright (C) 2009-2011 jointly by the following organizations:
 * 1. The University of Tuebingen, Germany
 * 2. EMBL European Bioinformatics Institute (EBML-EBI), Hinxton, UK
 * 3. The California Institute of Technology, Pasadena, CA, USA
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation. A copy of the license agreement is provided
 * in the file named "LICENSE.txt" included with this software distribution
 * and also available online as <http://sbml.org/Software/JSBML/License>.
 * ----------------------------------------------------------------------------
 */
package org.sbml.jsbml.xml.parsers;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.tree.TreeNode;

import org.apache.log4j.Logger;
import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.Model;
import org.sbml.jsbml.SBMLDocument;
import org.sbml.jsbml.SBase;
import org.sbml.jsbml.ext.qual.FunctionTerm;
import org.sbml.jsbml.ext.qual.Input;
import org.sbml.jsbml.ext.qual.Output;
import org.sbml.jsbml.ext.qual.QualConstant;
import org.sbml.jsbml.ext.qual.QualList;
import org.sbml.jsbml.ext.qual.QualitativeModel;
import org.sbml.jsbml.ext.qual.QualitativeSpecies;
import org.sbml.jsbml.ext.qual.SymbolicValue;
import org.sbml.jsbml.ext.qual.TemporisationMath;
import org.sbml.jsbml.ext.qual.Transition;
import org.sbml.jsbml.xml.stax.SBMLObjectForXML;

/**
 * This class is used to parse the qual extension package elements and
 * attributes. The namespaceURI URI of this parser is
 * "http://www.sbml.org/sbml/level3/version1/qual/version1". This parser is
 * able to read and write elements of the qual package (implements
 * ReadingParser and WritingParser).
 * 
 * @author Nicolas Rodriguez
 * @since 1.0
 * @version $Rev$
 */
public class QualParser extends AbstractReaderWriter {

	
	/**
	 * The QualList enum which represents the name of the list this parser is
	 * currently reading.
	 * 
	 */
	private QualList groupList = QualList.none;
	
	private Logger logger = Logger.getLogger(QualParser.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sbml.jsbml.xml.WritingParser#getListOfSBMLElementsToWrite(Object
	 * sbase)
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Object> getListOfSBMLElementsToWrite(Object sbase) {

		logger.debug("getListOfSBMLElementsToWrite : " + sbase.getClass().getCanonicalName());

		ArrayList<Object> listOfElementsToWrite = new ArrayList<Object>();

		if (sbase instanceof SBMLDocument) {
			// nothing to do
			// TODO : the 'required' attribute is written even if there is no plugin class for the SBMLDocument, so I am not totally sure how this is done.
		} 
		else if (sbase instanceof Model) {
			QualitativeModel modelGE = (QualitativeModel) ((Model) sbase).getExtension(QualConstant.namespaceURI);
			
			Enumeration<TreeNode> children = modelGE.children();
			
			while (children.hasMoreElements()) {
				listOfElementsToWrite.add(children.nextElement());
			}						
		} 
		else if (sbase instanceof TreeNode) {
			Enumeration<TreeNode> children = ((TreeNode) sbase).children();
			
			while (children.hasMoreElements()) {
				listOfElementsToWrite.add(children.nextElement());
			}
		}
		
		if (listOfElementsToWrite.isEmpty()) {
			listOfElementsToWrite = null;
		} else {
			logger.debug("getListOfSBMLElementsToWrite size = " + listOfElementsToWrite.size());
		}

		return listOfElementsToWrite;
	}


	/**
	 * 
	 * @see org.sbml.jsbml.xml.parsers.ReadingParser#processEndElement(String
	 *      elementName, String prefix, boolean isNested, Object contextObject)
	 */
	public boolean processEndElement(String elementName, String prefix,
			boolean isNested, Object contextObject) 
	{

		if (elementName.equals("listOfQualitativeSpecies")
				|| elementName.equals("listOfTransitions"))
		{
			this.groupList = QualList.none;
		}

		if (elementName.equals("listOfInputs")
				|| elementName.equals("listOfOutputs")
				|| elementName.equals("listOfFunctionTerms"))
		{
			this.groupList = QualList.listOfTransitions;
		}

		if (elementName.equals("listOfSymbolicValues")) 
		{
			this.groupList = QualList.listOfQualitativeSpecies;
		}

		return true;
	}

	/**
	 * 
	 * @see org.sbml.jsbml.xml.parsers.ReadingParser#processStartElement(String
	 *      elementName, String prefix, boolean hasAttributes, boolean
	 *      hasNamespaces, Object contextObject)
	 */
	// Create the proper object and link it to his parent.
	@SuppressWarnings("unchecked")
	public Object processStartElement(String elementName, String prefix,
			boolean hasAttributes, boolean hasNamespaces, Object contextObject) 
	{
		
		// logger.debug("processStartElement : elementName = " + elementName + " (" + contextObject.getClass().getSimpleName() + ")");
		
		if (contextObject instanceof Model) {
			Model model = (Model) contextObject;
			QualitativeModel qualModel = null;
			
			if (model.getExtension(QualConstant.namespaceURI) != null) {
				qualModel = (QualitativeModel) model.getExtension(QualConstant.namespaceURI);
			} else {
				qualModel = new QualitativeModel(model);
				model.addExtension(QualConstant.namespaceURI, qualModel);
			}

			if (elementName.equals("listOfQualitativeSpecies")) {
					
				ListOf<QualitativeSpecies> listOfQualitativeSpecies = qualModel.getListOfQualitativeSpecies();
				this.groupList = QualList.listOfQualitativeSpecies;
				return listOfQualitativeSpecies;
			} 
			else if (elementName.equals("listOfTransitions")) {

				ListOf<Transition> listOfObjectives = qualModel.getListOfTransitions();
				this.groupList = QualList.listOfTransitions;
				return listOfObjectives;
			}
		} else if (contextObject instanceof Transition) {
			Transition transition = (Transition) contextObject;
			
			if (elementName.equals("listOfInputs")) {				
				ListOf<Input> listOfInputs = transition.getListOfInputs();
				this.groupList = QualList.listOfInputs;
				return listOfInputs;
			} else if (elementName.equals("listOfOutputs")) {
				ListOf<Output> listOfOutputs = transition.getListOfOutputs();
				this.groupList = QualList.listOfOutputs;
				return listOfOutputs;
			} else if (elementName.equals("listOfFunctionTerms")) {
				ListOf<FunctionTerm> listOfFunctionTerms = transition.getListOfFunctionTerms();
				this.groupList = QualList.listOfFunctionTerms;
				return listOfFunctionTerms;
			}
		} else if (contextObject instanceof QualitativeSpecies) {
			QualitativeSpecies qualSpecies = (QualitativeSpecies) contextObject;
		
			if (elementName.equals("listOfSymbolicValues")) {				
				ListOf<SymbolicValue> listOfSymbolicValues = qualSpecies.getListOfSymbolicValues();
				this.groupList = QualList.listOfSymbolicValues;
				return listOfSymbolicValues;
			}
		} else if (contextObject instanceof FunctionTerm) {
			
			if (elementName.equals("temporisationMath")) {

				FunctionTerm qualSpecies = (FunctionTerm) contextObject;

				TemporisationMath temporisationMath = new TemporisationMath();
				qualSpecies.setTemporisationMath(temporisationMath);
				return temporisationMath;
			}
		}
		else if (contextObject instanceof ListOf<?>) 
		{
			ListOf<SBase> listOf = (ListOf<SBase>) contextObject;

			if (elementName.equals("transition") && this.groupList.equals(QualList.listOfTransitions)) { 
				//  && this.groupList.equals(QualList.listOfTransitions) // the ListOf inside the transition elements made this test wrong
				// if we want to have this kind of test, we would have to maintain a list of listOf type !
				Model model = (Model) listOf.getParentSBMLObject();
				QualitativeModel extendeModel = (QualitativeModel) model.getExtension(QualConstant.namespaceURI); 
				
				Transition transition = new Transition();				
				extendeModel.addTransition(transition);
				return transition;
				
			} else if ((elementName.equals("qualitativeSpecies")) && this.groupList.equals(QualList.listOfQualitativeSpecies)) {
				Model model = (Model) listOf.getParentSBMLObject();
				QualitativeModel extendeModel = (QualitativeModel) model.getExtension(QualConstant.namespaceURI); 
				
				QualitativeSpecies qualSpecies = new QualitativeSpecies();
				extendeModel.addQualitativeSpecies(qualSpecies);
				return qualSpecies;
				
			} else if (elementName.equals("input") && this.groupList.equals(QualList.listOfInputs)) {
				Transition transition = (Transition) listOf.getParentSBMLObject();
				
				Input input = new Input();
				transition.addInput(input);
				return input;
				
			} else if (elementName.equals("output") && this.groupList.equals(QualList.listOfOutputs)) {
				Transition transition = (Transition) listOf.getParentSBMLObject();
				
				Output output = new Output();
				transition.addOutput(output);
				return output;
				
			} else if (elementName.equals("functionTerm") && this.groupList.equals(QualList.listOfFunctionTerms)) {
				Transition transition = (Transition) listOf.getParentSBMLObject();
				
				FunctionTerm functionTerm = new FunctionTerm();
				transition.addFunctionTerm(functionTerm);
				return functionTerm;
				
			} else if (elementName.equals("defaultTerm") && this.groupList.equals(QualList.listOfFunctionTerms)) {
				Transition transition = (Transition) listOf.getParentSBMLObject();
				
				FunctionTerm functionTerm = new FunctionTerm();
				functionTerm.setDefaultTerm(true);
				transition.addFunctionTerm(functionTerm);
				return functionTerm;
				
			} else if (elementName.equals("symbolicValue") && this.groupList.equals(QualList.listOfSymbolicValues)) {
				QualitativeSpecies qualSpecies = (QualitativeSpecies) listOf.getParentSBMLObject();
				
				SymbolicValue symbolicValue = new SymbolicValue();
				qualSpecies.addSymbolicValue(symbolicValue);
				return symbolicValue;
				
			}
		}
		return contextObject;
	}


	/**
	 * 
	 * @see org.sbml.jsbml.xml.parsers.WritingParser#writeElement(SBMLObjectForXML
	 *      xmlObject, Object sbmlElementToWrite)
	 */
	public void writeElement(SBMLObjectForXML xmlObject,
			Object sbmlElementToWrite) 
	{
		super.writeElement(xmlObject, sbmlElementToWrite);
		
		logger.debug("writeElement : " + sbmlElementToWrite.getClass().getSimpleName());

		if (sbmlElementToWrite instanceof FunctionTerm &&
				((FunctionTerm) sbmlElementToWrite).isDefaultTerm())
		{
			xmlObject.setName("defaultTerm");
		}

	}


	@Override
	public String getShortLabel() {
		return QualConstant.shortLabel;
	}

	@Override
	public String getNamespaceURI() {
		return QualConstant.namespaceURI;
	}

}