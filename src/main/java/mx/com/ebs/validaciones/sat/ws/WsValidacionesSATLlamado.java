/**
 * 
 */
package mx.com.ebs.validaciones.sat.ws;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import utils.IsUtils;



/**
 * @author amirandaEBS
 *
 */
public class WsValidacionesSATLlamado {

	
	public String invocaWsValidacionesSAT(byte[] xml, boolean validaVigencia) throws Exception{
		String respuesta="";
		try{
			WsValidacionesSATServiceLocator context;
			WsValidacionesSAT cliente;
			context = new WsValidacionesSATServiceLocator();
			cliente = context.getWsValidacionesSAT();
			respuesta=cliente.validaCFDI(xml, validaVigencia);
		}catch(Exception e){
			throw new Exception("Error al invoca al servicio de validacion: "+e);
		}
		return respuesta;
	}
	/**
	 * @param args
	 * @throws Exception 
	 * @throws java.io.IOException
	 * @throws java.io.FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, Exception {
		WsValidacionesSATLlamado wsValidacionesSATLlamado = new WsValidacionesSATLlamado();
		
		File xml = new File("D:\\COATS\\6DC27CEF-FF0B-4AAD-8032-2B9BBAC41A70.xml");
		System.out.println("respuesta ws: "+wsValidacionesSATLlamado.invocaWsValidacionesSAT(IsUtils.toByteArrayUsingJava(new FileInputStream(xml)), true));
		
	}

}
