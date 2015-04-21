/*
 * $Id: AdjacentDomains.java 2109 2015-01-05 04:50:45Z andreas-draeger $
 * $URL: svn://svn.code.sf.net/p/jsbml/code/trunk/extensions/spatial/src/org/sbml/jsbml/ext/spatial/AdjacentDomains.java $
 * ----------------------------------------------------------------------------
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML>
 * for the latest version of JSBML and more information about SBML.
 *
 * Copyright (C) 2009-2015 jointly by the following organizations:
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
package org.sbml.jsbml.ext.spatial;

import java.text.MessageFormat;
import java.util.Map;
import java.util.ResourceBundle;

import org.sbml.jsbml.ListOf;
import org.sbml.jsbml.PropertyUndefinedError;
import org.sbml.jsbml.util.ResourceManager;

/**
 * @author Alex Thomas
 * @author Andreas Dr&auml;ger
 * @since 1.0
 * @version $Rev: 2109 $
 */
public class AdjacentDomains extends AbstractSpatialNamedSBase {

  /**
   * 
   */
  private String domain1;
  /**
   * 
   */
  private String domain2;
  /**
   * 
   */
  private static final ResourceBundle bundle = ResourceManager.getBundle("org.sbml.jsbml.ext.spatial.Messages");


  /**
   * Generated serial version identifier.
   */
  private static final long serialVersionUID = 1600690320824551145L;

  /**
   * Creates a {@link AdjacentDomains} instance.
   */
  public AdjacentDomains() {
    super();
  }

  /**
   * Creates a {@link AdjacentDomains} instance with a level and version.
   * 
   * @param level
   * @param version
   */
  public AdjacentDomains(int level, int version) {
    super(level, version);
  }

  /**
   * @param id
   * @param level
   * @param version
   */
  public AdjacentDomains(String id, int level, int version) {
    super(id,level,version);
  }

  /**
   * Clone constructor
   * 
   * @param sb
   */
  public AdjacentDomains(AdjacentDomains sb) {
    super(sb);

    if (sb.isSetDomain1()) {
      setDomain1(sb.getDomain1());
    }
    if (sb.isSetDomain2()) {
      setDomain2(sb.getDomain2());
    }
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.AbstractSBase#clone()
   */
  @Override
  public AdjacentDomains clone() {
    return new AdjacentDomains(this);
  }

  @Override
  public boolean equals(Object object) {
    boolean equal = super.equals(object);
    if (equal) {
      AdjacentDomains dms = (AdjacentDomains) object;
      equal &= dms.isSetDomain1() == isSetDomain1();
      if (equal && isSetDomain1()) {
        equal &= dms.getDomain1().equals(getDomain1());
      }
      equal &= dms.isSetDomain2() == isSetDomain2();
      if (equal && isSetDomain2()) {
        equal &= dms.getDomain2().equals(getDomain2());
      }
    }
    return equal;
  }


  /**
   * Returns the value of domain1
   *
   * @return the value of domain1
   */
  public String getDomain1() {
    if (isSetDomain1()) {
      return domain1;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(SpatialConstants.domain1, this);
  }


  /**
   * Returns whether domain1 is set
   *
   * @return whether domain1 is set
   */
  public boolean isSetDomain1() {
    return domain1 != null;
  }


  /**
   * Sets the value of domain1
   */
  /**
   * @param domain1
   */
  public void setDomain1(String domain1) {
    String oldDomain1 = this.domain1;
    this.domain1 = domain1;
    firePropertyChange(SpatialConstants.domain1, oldDomain1, this.domain1);
  }


  /**
   * Unsets the variable domain1
   *
   * @return {@code true}, if domain1 was set before,
   *         otherwise {@code false}
   */
  public boolean unsetDomain1() {
    if (isSetDomain1()) {
      String oldDomain1 = domain1;
      domain1 = null;
      firePropertyChange(SpatialConstants.domain1, oldDomain1, domain1);
      return true;
    }
    return false;
  }


  /**
   * Returns the value of domain2
   *
   * @return the value of domain2
   */
  public String getDomain2() {
    if (isSetDomain2()) {
      return domain2;
    }
    // This is necessary if we cannot return null here.
    throw new PropertyUndefinedError(SpatialConstants.domain2, this);
  }

  /**
   * Returns whether domain2 is set
   *
   * @return whether domain2 is set
   */
  public boolean isSetDomain2() {
    return domain2 != null;
  }

  /**
   * Sets the value of domain2
   * @param domain2
   */
  public void setDomain2(String domain2) {
    String oldDomain2 = this.domain2;
    this.domain2 = domain2;
    firePropertyChange(SpatialConstants.domain2, oldDomain2, this.domain2);
  }

  /**
   * Unsets the variable domain2
   *
   * @return {@code true}, if domain2 was set before,
   *         otherwise {@code false}
   */
  public boolean unsetDomain2() {
    if (isSetDomain2()) {
      String oldDomain2 = domain2;
      domain2 = null;
      firePropertyChange(SpatialConstants.domain2, oldDomain2, domain2);
      return true;
    }
    return false;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.spatial.AbstractSpatialNamedSBase#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 293;
    int hashCode = super.hashCode();
    if (isSetDomain1()) {
      hashCode += prime * getDomain1().hashCode();
    }
    if (isSetDomain2()) {
      hashCode += prime * getDomain2().hashCode();
    }
    return hashCode;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.spatial.AbstractSpatialNamedSBase#writeXMLAttributes()
   */
  @Override
  public Map<String, String> writeXMLAttributes() {
    Map<String, String> attributes = super.writeXMLAttributes();
    if (isSetDomain1()) {
      attributes.remove("domain1");
      attributes.put(SpatialConstants.shortLabel + ":domain1", getDomain1());
    }
    if (isSetDomain2()) {
      attributes.remove("domain2");
      attributes.put(SpatialConstants.shortLabel + ":domain2", getDomain2());
    }
    return attributes;
  }

  /* (non-Javadoc)
   * @see org.sbml.jsbml.ext.spatial.AbstractSpatialNamedSBase#readAttribute(java.lang.String, java.lang.String, java.lang.String)
   */
  @Override
  public boolean readAttribute(String attributeName, String prefix, String value) {
    boolean isAttributeRead = (super.readAttribute(attributeName, prefix, value))
        && (SpatialConstants.shortLabel == prefix);
    if (!isAttributeRead) {
      isAttributeRead = true;
      if (attributeName.equals(SpatialConstants.domain1)) {
        try {
          setDomain1(value);
        } catch (Exception e) {
          MessageFormat.format(bundle.getString("COULD_NOT_READ"), value,
            SpatialConstants.domain1);
        }
      }
      else if (attributeName.equals(SpatialConstants.domain2)) {
        try {
          setDomain2(value);
        } catch (Exception e) {
          MessageFormat.format(bundle.getString("COULD_NOT_READ"), value,
            SpatialConstants.domain2);
        }
      }
      else {
        isAttributeRead = false;
      }
    }
    return isAttributeRead;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("AdjacentDomains [domain1=");
    builder.append(domain1);
    builder.append(", domain2=");
    builder.append(domain2);
    builder.append("]");
    return builder.toString();
  }

}
