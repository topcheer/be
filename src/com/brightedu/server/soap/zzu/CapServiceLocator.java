/**
 * CapServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package com.brightedu.server.soap.zzu;

public class CapServiceLocator extends org.apache.axis.client.Service implements com.brightedu.server.soap.zzu.CapService {

    public CapServiceLocator() {
    }


    public CapServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CapServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CapServiceSoap
    private java.lang.String CapServiceSoap_address = "http://202.196.64.19:8088/aspx/SignSys/CapService.asmx";

    public java.lang.String getCapServiceSoapAddress() {
        return CapServiceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CapServiceSoapWSDDServiceName = "CapServiceSoap";

    public java.lang.String getCapServiceSoapWSDDServiceName() {
        return CapServiceSoapWSDDServiceName;
    }

    public void setCapServiceSoapWSDDServiceName(java.lang.String name) {
        CapServiceSoapWSDDServiceName = name;
    }

    public com.brightedu.server.soap.zzu.CapServiceSoap_PortType getCapServiceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CapServiceSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCapServiceSoap(endpoint);
    }

    public com.brightedu.server.soap.zzu.CapServiceSoap_PortType getCapServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.brightedu.server.soap.zzu.CapServiceSoap_BindingStub _stub = new com.brightedu.server.soap.zzu.CapServiceSoap_BindingStub(portAddress, this);
            _stub.setPortName(getCapServiceSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCapServiceSoapEndpointAddress(java.lang.String address) {
        CapServiceSoap_address = address;
    }


    // Use to get a proxy class for CapServiceSoap12
    private java.lang.String CapServiceSoap12_address = "http://202.196.64.19:8088/aspx/SignSys/CapService.asmx";

    public java.lang.String getCapServiceSoap12Address() {
        return CapServiceSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CapServiceSoap12WSDDServiceName = "CapServiceSoap12";

    public java.lang.String getCapServiceSoap12WSDDServiceName() {
        return CapServiceSoap12WSDDServiceName;
    }

    public void setCapServiceSoap12WSDDServiceName(java.lang.String name) {
        CapServiceSoap12WSDDServiceName = name;
    }

    public com.brightedu.server.soap.zzu.CapServiceSoap_PortType getCapServiceSoap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CapServiceSoap12_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCapServiceSoap12(endpoint);
    }

    public com.brightedu.server.soap.zzu.CapServiceSoap_PortType getCapServiceSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.brightedu.server.soap.zzu.CapServiceSoap12Stub _stub = new com.brightedu.server.soap.zzu.CapServiceSoap12Stub(portAddress, this);
            _stub.setPortName(getCapServiceSoap12WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCapServiceSoap12EndpointAddress(java.lang.String address) {
        CapServiceSoap12_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     * This service has multiple ports for a given interface;
     * the proxy implementation returned may be indeterminate.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.brightedu.server.soap.zzu.CapServiceSoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.brightedu.server.soap.zzu.CapServiceSoap_BindingStub _stub = new com.brightedu.server.soap.zzu.CapServiceSoap_BindingStub(new java.net.URL(CapServiceSoap_address), this);
                _stub.setPortName(getCapServiceSoapWSDDServiceName());
                return _stub;
            }
            if (com.brightedu.server.soap.zzu.CapServiceSoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.brightedu.server.soap.zzu.CapServiceSoap12Stub _stub = new com.brightedu.server.soap.zzu.CapServiceSoap12Stub(new java.net.URL(CapServiceSoap12_address), this);
                _stub.setPortName(getCapServiceSoap12WSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("CapServiceSoap".equals(inputPortName)) {
            return getCapServiceSoap();
        }
        else if ("CapServiceSoap12".equals(inputPortName)) {
            return getCapServiceSoap12();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "CapService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "CapServiceSoap"));
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "CapServiceSoap12"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CapServiceSoap".equals(portName)) {
            setCapServiceSoapEndpointAddress(address);
        }
        else 
if ("CapServiceSoap12".equals(portName)) {
            setCapServiceSoap12EndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
