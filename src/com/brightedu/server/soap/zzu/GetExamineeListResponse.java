/**
 * GetExamineeListResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package com.brightedu.server.soap.zzu;

public class GetExamineeListResponse  implements java.io.Serializable {
    private com.brightedu.server.soap.zzu.ArrayOfAnyType getExamineeListResult;

    public GetExamineeListResponse() {
    }

    public GetExamineeListResponse(
           com.brightedu.server.soap.zzu.ArrayOfAnyType getExamineeListResult) {
           this.getExamineeListResult = getExamineeListResult;
    }


    /**
     * Gets the getExamineeListResult value for this GetExamineeListResponse.
     * 
     * @return getExamineeListResult
     */
    public com.brightedu.server.soap.zzu.ArrayOfAnyType getGetExamineeListResult() {
        return getExamineeListResult;
    }


    /**
     * Sets the getExamineeListResult value for this GetExamineeListResponse.
     * 
     * @param getExamineeListResult
     */
    public void setGetExamineeListResult(com.brightedu.server.soap.zzu.ArrayOfAnyType getExamineeListResult) {
        this.getExamineeListResult = getExamineeListResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetExamineeListResponse)) return false;
        GetExamineeListResponse other = (GetExamineeListResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getExamineeListResult==null && other.getGetExamineeListResult()==null) || 
             (this.getExamineeListResult!=null &&
              this.getExamineeListResult.equals(other.getGetExamineeListResult())));
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
        if (getGetExamineeListResult() != null) {
            _hashCode += getGetExamineeListResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetExamineeListResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">GetExamineeListResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getExamineeListResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "GetExamineeListResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "ArrayOfAnyType"));
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
