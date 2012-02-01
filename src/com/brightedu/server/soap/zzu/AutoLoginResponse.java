/**
 * AutoLoginResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package com.brightedu.server.soap.zzu;

public class AutoLoginResponse  implements java.io.Serializable {
    private boolean autoLoginResult;

    private java.lang.String stationno;

    private java.lang.String msg;

    private java.lang.String stationname;

    public AutoLoginResponse() {
    }

    public AutoLoginResponse(
           boolean autoLoginResult,
           java.lang.String stationno,
           java.lang.String msg,
           java.lang.String stationname) {
           this.autoLoginResult = autoLoginResult;
           this.stationno = stationno;
           this.msg = msg;
           this.stationname = stationname;
    }


    /**
     * Gets the autoLoginResult value for this AutoLoginResponse.
     * 
     * @return autoLoginResult
     */
    public boolean isAutoLoginResult() {
        return autoLoginResult;
    }


    /**
     * Sets the autoLoginResult value for this AutoLoginResponse.
     * 
     * @param autoLoginResult
     */
    public void setAutoLoginResult(boolean autoLoginResult) {
        this.autoLoginResult = autoLoginResult;
    }


    /**
     * Gets the stationno value for this AutoLoginResponse.
     * 
     * @return stationno
     */
    public java.lang.String getStationno() {
        return stationno;
    }


    /**
     * Sets the stationno value for this AutoLoginResponse.
     * 
     * @param stationno
     */
    public void setStationno(java.lang.String stationno) {
        this.stationno = stationno;
    }


    /**
     * Gets the msg value for this AutoLoginResponse.
     * 
     * @return msg
     */
    public java.lang.String getMsg() {
        return msg;
    }


    /**
     * Sets the msg value for this AutoLoginResponse.
     * 
     * @param msg
     */
    public void setMsg(java.lang.String msg) {
        this.msg = msg;
    }


    /**
     * Gets the stationname value for this AutoLoginResponse.
     * 
     * @return stationname
     */
    public java.lang.String getStationname() {
        return stationname;
    }


    /**
     * Sets the stationname value for this AutoLoginResponse.
     * 
     * @param stationname
     */
    public void setStationname(java.lang.String stationname) {
        this.stationname = stationname;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AutoLoginResponse)) return false;
        AutoLoginResponse other = (AutoLoginResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.autoLoginResult == other.isAutoLoginResult() &&
            ((this.stationno==null && other.getStationno()==null) || 
             (this.stationno!=null &&
              this.stationno.equals(other.getStationno()))) &&
            ((this.msg==null && other.getMsg()==null) || 
             (this.msg!=null &&
              this.msg.equals(other.getMsg()))) &&
            ((this.stationname==null && other.getStationname()==null) || 
             (this.stationname!=null &&
              this.stationname.equals(other.getStationname())));
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
        _hashCode += (isAutoLoginResult() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getStationno() != null) {
            _hashCode += getStationno().hashCode();
        }
        if (getMsg() != null) {
            _hashCode += getMsg().hashCode();
        }
        if (getStationname() != null) {
            _hashCode += getStationname().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AutoLoginResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">AutoLoginResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("autoLoginResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "AutoLoginResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stationno");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "stationno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stationname");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "stationname"));
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
