package mx.com.ebs.inter.service;

import mx.com.ebs.inter.data.bo.ExporterSearchBo;
import mx.com.ebs.inter.exception.ApplicationException;

/**
 * Created by robb on 03/12/2015.
 */
public interface InvoicesExporterService {

    int getDocumentsCounter(ExporterSearchBo exporterSearchBo);
    void exportDocuments(ExporterSearchBo exporterSearchBo) throws ApplicationException;
}
