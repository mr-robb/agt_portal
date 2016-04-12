/**
 * WsValidacionesSATServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package mx.com.ebs.validaciones.sat.ws;

import mx.com.ebs.inter.util.PropertiesLoader;
import mx.com.ebs.inter.util.Variables;

public class WsValidacionesSATServiceLocator extends org.apache.axis.client.Service implements WsValidacionesSATService {

    public WsValidacionesSATServiceLocator() {
    }


    public WsValidacionesSATServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WsValidacionesSATServiceLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WsValidacionesSAT
    private String WsValidacionesSAT_address = PropertiesLoader.getProperty(Variables.WS_VALIDA_SAT_URL);

    public String getWsValidacionesSATAddress() {
        return WsValidacionesSAT_address;
    }

    // The WSDD service name defaults to the port name.
    private String WsValidacionesSATWSDDServiceName = "WsValidacionesSAT";

    public String getWsValidacionesSATWSDDServiceName() {
        return WsValidacionesSATWSDDServiceName;
    }

    public void setWsValidacionesSATWSDDServiceName(String name) {
        WsValidacionesSATWSDDServiceName = name;
    }

    public WsValidacionesSAT getWsValidacionesSAT() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WsValidacionesSAT_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWsValidacionesSAT(endpoint);
    }

    public WsValidacionesSAT getWsValidacionesSAT(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            WsValidacionesSATSoapBindingStub _stub = new WsValidacionesSATSoapBindingStub(portAddress, this);
            _stub.setPortName(getWsValidacionesSATWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWsValidacionesSATEndpointAddress(String address) {
        WsValidacionesSAT_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (WsValidacionesSAT.class.isAssignableFrom(serviceEndpointInterface)) {
                WsValidacionesSATSoapBindingStub _stub = new WsValidacionesSATSoapBindingStub(new java.net.URL(WsValidacionesSAT_address), this);
                _stub.setPortName(getWsValidacionesSATWSDDServiceName());
                return _stub;
            }
        }
        catch (Throwable t) {
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
        String inputPortName = portName.getLocalPart();
        if ("WsValidacionesSAT".equals(inputPortName)) {
            return getWsValidacionesSAT();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ws.sat.validaciones.ebs.com.mx", "WsValidacionesSATService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://ws.sat.validaciones.ebs.com.mx", "WsValidacionesSAT"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {
        
if ("WsValidacionesSAT".equals(portName)) {
            setWsValidacionesSATEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
