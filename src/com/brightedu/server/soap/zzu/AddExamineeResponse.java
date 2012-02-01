/**
 * AddExamineeResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package com.brightedu.server.soap.zzu;

public class AddExamineeResponse  implements java.io.Serializable {
    private boolean addExamineeResult;

    private com.brightedu.server.soap.zzu.Clientexaminee e;

    private java.lang.String msg;

    public AddExamineeResponse() {
    }

    public AddExamineeResponse(
           boolean addExamineeResult,
           com.brightedu.server.soap.zzu.Clientexaminee e,
           java.lang.String msg) {
           this.addExamineeResult = addExamineeResult;
           this.e = e;
           this.msg = msg;
    }


    /**
     * Gets the addExamineeResult value for this AddExamineeResponse.
     * 
     * @return addExamineeResult
     */
    public boolean isAddExamineeResult() {
        return addExamineeResult;
    }


    /**
     * Sets the addExamineeResult value for this AddExamineeResponse.
     * 
     * @param addExamineeResult
     */
    public void setAddExamineeResult(boolean addExamineeResult) {
        this.addExamineeResult = addExamineeResult;
    }


    /**
     * Gets the e value for this AddExamineeResponse.
     * 
     * @return e
     */
    public com.brightedu.server.soap.zzu.Clientexaminee getE() {
        return e;
    }


    /**
     * Sets the e value for this AddExamineeResponse.
     * 
     * @param e
     */
    public void setE(com.brightedu.server.soap.zzu.Clientexaminee e) {
        this.e = e;
    }


    /**
     * Gets the msg value for this AddExamineeResponse.
     * 
     * @return msg
     */
    public java.lang.String getMsg() {
        return msg;
    }


    /**
     * Sets the msg value for this AddExamineeResponse.
     * 
     * @param msg
     */
    public void setMsg(java.lang.String msg) {
        this.msg = msg;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AddExamineeResponse)) return false;
        AddExamineeResponse other = (AddExamineeResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.addExamineeResult == other.isAddExamineeResult() &&
            ((this.e==null && other.getE()==null) || 
             (this.e!=null &&
              this.e.equals(other.getE()))) &&
            ((this.msg==null && other.getMsg()==null) || 
             (this.msg!=null &&
              this.msg.equals(other.getMsg())));
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
        _hashCode += (isAddExamineeResult() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getE() != null) {
            _hashCode += getE().hashCode();
        }
        if (getMsg() != null) {
            _hashCode += getMsg().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddExamineeResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">AddExamineeResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addExamineeResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "AddExamineeResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("e");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "e"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "clientexaminee"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("msg");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "msg"));
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
