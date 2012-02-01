/**
 * CapService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package com.brightedu.server.soap.zzu;

public interface CapService extends javax.xml.rpc.Service {
    public java.lang.String getCapServiceSoapAddress();

    public com.brightedu.server.soap.zzu.CapServiceSoap_PortType getCapServiceSoap() throws javax.xml.rpc.ServiceException;

    public com.brightedu.server.soap.zzu.CapServiceSoap_PortType getCapServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getCapServiceSoap12Address();

    public com.brightedu.server.soap.zzu.CapServiceSoap_PortType getCapServiceSoap12() throws javax.xml.rpc.ServiceException;

    public com.brightedu.server.soap.zzu.CapServiceSoap_PortType getCapServiceSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
