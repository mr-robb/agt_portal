package mx.com.ebs.validaciones.sat.ws;

public class WsValidacionesSATProxy implements WsValidacionesSAT {
  private String _endpoint = null;
  private WsValidacionesSAT wsValidacionesSAT = null;
  
  public WsValidacionesSATProxy() {
    _initWsValidacionesSATProxy();
  }
  
  public WsValidacionesSATProxy(String endpoint) {
    _endpoint = endpoint;
    _initWsValidacionesSATProxy();
  }
  
  private void _initWsValidacionesSATProxy() {
    try {
      wsValidacionesSAT = (new WsValidacionesSATServiceLocator()).getWsValidacionesSAT();
      if (wsValidacionesSAT != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wsValidacionesSAT)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wsValidacionesSAT)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wsValidacionesSAT != null)
      ((javax.xml.rpc.Stub)wsValidacionesSAT)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public WsValidacionesSAT getWsValidacionesSAT() {
    if (wsValidacionesSAT == null)
      _initWsValidacionesSATProxy();
    return wsValidacionesSAT;
  }
  
  public String validaCFDI(byte[] xml, boolean validaVigencia) throws java.rmi.RemoteException{
    if (wsValidacionesSAT == null)
      _initWsValidacionesSATProxy();
    return wsValidacionesSAT.validaCFDI(xml, validaVigencia);
  }
  
  
}