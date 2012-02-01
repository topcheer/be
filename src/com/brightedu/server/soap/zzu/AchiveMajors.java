/**
 * AchiveMajors.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package com.brightedu.server.soap.zzu;

public class AchiveMajors  implements java.io.Serializable {
    private java.lang.String gid;

    private java.lang.String stationno;

    public AchiveMajors() {
    }

    public AchiveMajors(
           java.lang.String gid,
           java.lang.String stationno) {
           this.gid = gid;
           this.stationno = stationno;
    }


    /**
     * Gets the gid value for this AchiveMajors.
     * 
     * @return gid
     */
    public java.lang.String getGid() {
        return gid;
    }


    /**
     * Sets the gid value for this AchiveMajors.
     * 
     * @param gid
     */
    public void setGid(java.lang.String gid) {
        this.gid = gid;
    }


    /**
     * Gets the stationno value for this AchiveMajors.
     * 
     * @return stationno
     */
    public java.lang.String getStationno() {
        return stationno;
    }


    /**
     * Sets the stationno value for this AchiveMajors.
     * 
     * @param stationno
     */
    public void setStationno(java.lang.String stationno) {
        this.stationno = stationno;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AchiveMajors)) return false;
        AchiveMajors other = (AchiveMajors) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.gid==null && other.getGid()==null) || 
             (this.gid!=null &&
              this.gid.equals(other.getGid()))) &&
            ((this.stationno==null && other.getStationno()==null) || 
             (this.stationno!=null &&
              this.stationno.equals(other.getStationno())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getGid() != null) {
            _hashCode += getGid().hashCode();
        }
        if (getStationno() != null) {
            _hashCode += getStationno().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AchiveMajors.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">AchiveMajors"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "gid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stationno");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "stationno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
