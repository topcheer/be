/**
 * AutoLogin.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package com.brightedu.server.soap.zzu;

public class AutoLogin  implements java.io.Serializable {
    private java.lang.String userid;

    private java.lang.String password;

    private java.lang.String stationno;

    private java.lang.String gid;

    private java.lang.String msg;

    private java.lang.String stationname;

    public AutoLogin() {
    }

    public AutoLogin(
           java.lang.String userid,
           java.lang.String password,
           java.lang.String stationno,
           java.lang.String gid,
           java.lang.String msg,
           java.lang.String stationname) {
           this.userid = userid;
           this.password = password;
           this.stationno = stationno;
           this.gid = gid;
           this.msg = msg;
           this.stationname = stationname;
    }


    /**
     * Gets the userid value for this AutoLogin.
     * 
     * @return userid
     */
    public java.lang.String getUserid() {
        return userid;
    }


    /**
     * Sets the userid value for this AutoLogin.
     * 
     * @param userid
     */
    public void setUserid(java.lang.String userid) {
        this.userid = userid;
    }


    /**
     * Gets the password value for this AutoLogin.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this AutoLogin.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the stationno value for this AutoLogin.
     * 
     * @return stationno
     */
    public java.lang.String getStationno() {
        return stationno;
    }


    /**
     * Sets the stationno value for this AutoLogin.
     * 
     * @param stationno
     */
    public void setStationno(java.lang.String stationno) {
        this.stationno = stationno;
    }


    /**
     * Gets the gid value for this AutoLogin.
     * 
     * @return gid
     */
    public java.lang.String getGid() {
        return gid;
    }


    /**
     * Sets the gid value for this AutoLogin.
     * 
     * @param gid
     */
    public void setGid(java.lang.String gid) {
        this.gid = gid;
    }


    /**
     * Gets the msg value for this AutoLogin.
     * 
     * @return msg
     */
    public java.lang.String getMsg() {
        return msg;
    }


    /**
     * Sets the msg value for this AutoLogin.
     * 
     * @param msg
     */
    public void setMsg(java.lang.String msg) {
        this.msg = msg;
    }


    /**
     * Gets the stationname value for this AutoLogin.
     * 
     * @return stationname
     */
    public java.lang.String getStationname() {
        return stationname;
    }


    /**
     * Sets the stationname value for this AutoLogin.
     * 
     * @param stationname
     */
    public void setStationname(java.lang.String stationname) {
        this.stationname = stationname;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AutoLogin)) return false;
        AutoLogin other = (AutoLogin) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.userid==null && other.getUserid()==null) || 
             (this.userid!=null &&
              this.userid.equals(other.getUserid()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.stationno==null && other.getStationno()==null) || 
             (this.stationno!=null &&
              this.stationno.equals(other.getStationno()))) &&
            ((this.gid==null && other.getGid()==null) || 
             (this.gid!=null &&
              this.gid.equals(other.getGid()))) &&
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
        if (getUserid() != null) {
            _hashCode += getUserid().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getStationno() != null) {
            _hashCode += getStationno().hashCode();
        }
        if (getGid() != null) {
            _hashCode += getGid().hashCode();
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
        new org.apache.axis.description.TypeDesc(AutoLogin.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">AutoLogin"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "userid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "password"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "gid"));
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
