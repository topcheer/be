/**
 * GetExamineeList.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package com.brightedu.server.soap.zzu;

public class GetExamineeList  implements java.io.Serializable {
    private java.lang.String zkzh;

    private java.lang.String xm;

    private java.lang.String stationno;

    private java.lang.String zyh;

    private java.lang.String gid;

    public GetExamineeList() {
    }

    public GetExamineeList(
           java.lang.String zkzh,
           java.lang.String xm,
           java.lang.String stationno,
           java.lang.String zyh,
           java.lang.String gid) {
           this.zkzh = zkzh;
           this.xm = xm;
           this.stationno = stationno;
           this.zyh = zyh;
           this.gid = gid;
    }


    /**
     * Gets the zkzh value for this GetExamineeList.
     * 
     * @return zkzh
     */
    public java.lang.String getZkzh() {
        return zkzh;
    }


    /**
     * Sets the zkzh value for this GetExamineeList.
     * 
     * @param zkzh
     */
    public void setZkzh(java.lang.String zkzh) {
        this.zkzh = zkzh;
    }


    /**
     * Gets the xm value for this GetExamineeList.
     * 
     * @return xm
     */
    public java.lang.String getXm() {
        return xm;
    }


    /**
     * Sets the xm value for this GetExamineeList.
     * 
     * @param xm
     */
    public void setXm(java.lang.String xm) {
        this.xm = xm;
    }


    /**
     * Gets the stationno value for this GetExamineeList.
     * 
     * @return stationno
     */
    public java.lang.String getStationno() {
        return stationno;
    }


    /**
     * Sets the stationno value for this GetExamineeList.
     * 
     * @param stationno
     */
    public void setStationno(java.lang.String stationno) {
        this.stationno = stationno;
    }


    /**
     * Gets the zyh value for this GetExamineeList.
     * 
     * @return zyh
     */
    public java.lang.String getZyh() {
        return zyh;
    }


    /**
     * Sets the zyh value for this GetExamineeList.
     * 
     * @param zyh
     */
    public void setZyh(java.lang.String zyh) {
        this.zyh = zyh;
    }


    /**
     * Gets the gid value for this GetExamineeList.
     * 
     * @return gid
     */
    public java.lang.String getGid() {
        return gid;
    }


    /**
     * Sets the gid value for this GetExamineeList.
     * 
     * @param gid
     */
    public void setGid(java.lang.String gid) {
        this.gid = gid;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetExamineeList)) return false;
        GetExamineeList other = (GetExamineeList) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.zkzh==null && other.getZkzh()==null) || 
             (this.zkzh!=null &&
              this.zkzh.equals(other.getZkzh()))) &&
            ((this.xm==null && other.getXm()==null) || 
             (this.xm!=null &&
              this.xm.equals(other.getXm()))) &&
            ((this.stationno==null && other.getStationno()==null) || 
             (this.stationno!=null &&
              this.stationno.equals(other.getStationno()))) &&
            ((this.zyh==null && other.getZyh()==null) || 
             (this.zyh!=null &&
              this.zyh.equals(other.getZyh()))) &&
            ((this.gid==null && other.getGid()==null) || 
             (this.gid!=null &&
              this.gid.equals(other.getGid())));
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
        if (getZkzh() != null) {
            _hashCode += getZkzh().hashCode();
        }
        if (getXm() != null) {
            _hashCode += getXm().hashCode();
        }
        if (getStationno() != null) {
            _hashCode += getStationno().hashCode();
        }
        if (getZyh() != null) {
            _hashCode += getZyh().hashCode();
        }
        if (getGid() != null) {
            _hashCode += getGid().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetExamineeList.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">GetExamineeList"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("zkzh");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "zkzh"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("xm");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "xm"));
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
        elemField.setFieldName("zyh");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "zyh"));
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
