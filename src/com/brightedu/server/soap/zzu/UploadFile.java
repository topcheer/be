/**
 * UploadFile.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package com.brightedu.server.soap.zzu;

public class UploadFile  implements java.io.Serializable {
    private byte[] fs;

    private java.lang.String filename;

    private java.lang.String gid;

    private java.lang.String msg;

    private java.lang.String stationno;

    private java.lang.String file_type;

    public UploadFile() {
    }

    public UploadFile(
           byte[] fs,
           java.lang.String filename,
           java.lang.String gid,
           java.lang.String msg,
           java.lang.String stationno,
           java.lang.String file_type) {
           this.fs = fs;
           this.filename = filename;
           this.gid = gid;
           this.msg = msg;
           this.stationno = stationno;
           this.file_type = file_type;
    }


    /**
     * Gets the fs value for this UploadFile.
     * 
     * @return fs
     */
    public byte[] getFs() {
        return fs;
    }


    /**
     * Sets the fs value for this UploadFile.
     * 
     * @param fs
     */
    public void setFs(byte[] fs) {
        this.fs = fs;
    }


    /**
     * Gets the filename value for this UploadFile.
     * 
     * @return filename
     */
    public java.lang.String getFilename() {
        return filename;
    }


    /**
     * Sets the filename value for this UploadFile.
     * 
     * @param filename
     */
    public void setFilename(java.lang.String filename) {
        this.filename = filename;
    }


    /**
     * Gets the gid value for this UploadFile.
     * 
     * @return gid
     */
    public java.lang.String getGid() {
        return gid;
    }


    /**
     * Sets the gid value for this UploadFile.
     * 
     * @param gid
     */
    public void setGid(java.lang.String gid) {
        this.gid = gid;
    }


    /**
     * Gets the msg value for this UploadFile.
     * 
     * @return msg
     */
    public java.lang.String getMsg() {
        return msg;
    }


    /**
     * Sets the msg value for this UploadFile.
     * 
     * @param msg
     */
    public void setMsg(java.lang.String msg) {
        this.msg = msg;
    }


    /**
     * Gets the stationno value for this UploadFile.
     * 
     * @return stationno
     */
    public java.lang.String getStationno() {
        return stationno;
    }


    /**
     * Sets the stationno value for this UploadFile.
     * 
     * @param stationno
     */
    public void setStationno(java.lang.String stationno) {
        this.stationno = stationno;
    }


    /**
     * Gets the file_type value for this UploadFile.
     * 
     * @return file_type
     */
    public java.lang.String getFile_type() {
        return file_type;
    }


    /**
     * Sets the file_type value for this UploadFile.
     * 
     * @param file_type
     */
    public void setFile_type(java.lang.String file_type) {
        this.file_type = file_type;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UploadFile)) return false;
        UploadFile other = (UploadFile) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fs==null && other.getFs()==null) || 
             (this.fs!=null &&
              java.util.Arrays.equals(this.fs, other.getFs()))) &&
            ((this.filename==null && other.getFilename()==null) || 
             (this.filename!=null &&
              this.filename.equals(other.getFilename()))) &&
            ((this.gid==null && other.getGid()==null) || 
             (this.gid!=null &&
              this.gid.equals(other.getGid()))) &&
            ((this.msg==null && other.getMsg()==null) || 
             (this.msg!=null &&
              this.msg.equals(other.getMsg()))) &&
            ((this.stationno==null && other.getStationno()==null) || 
             (this.stationno!=null &&
              this.stationno.equals(other.getStationno()))) &&
            ((this.file_type==null && other.getFile_type()==null) || 
             (this.file_type!=null &&
              this.file_type.equals(other.getFile_type())));
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
        if (getFs() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFs());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getFs(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getFilename() != null) {
            _hashCode += getFilename().hashCode();
        }
        if (getGid() != null) {
            _hashCode += getGid().hashCode();
        }
        if (getMsg() != null) {
            _hashCode += getMsg().hashCode();
        }
        if (getStationno() != null) {
            _hashCode += getStationno().hashCode();
        }
        if (getFile_type() != null) {
            _hashCode += getFile_type().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UploadFile.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", ">UploadFile"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "fs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("filename");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "filename"));
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
        elemField.setFieldName("stationno");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "stationno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("file_type");
        elemField.setXmlName(new javax.xml.namespace.QName("http://tempuri.org/", "file_type"));
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
