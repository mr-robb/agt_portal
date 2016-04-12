package mx.com.ebs.inter.service;

/**
 * Created by robb on 26/06/2015.
 */
public interface XmlValidatorService {

    String validate(byte[] fileContent) throws Exception;
    boolean isError();
}
