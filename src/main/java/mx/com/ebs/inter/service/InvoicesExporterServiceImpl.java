package mx.com.ebs.inter.service;

import mx.com.ebs.inter.data.bo.ExporterSearchBo;
import mx.com.ebs.inter.data.dao.agt.InvoiceMapper;
import mx.com.ebs.inter.data.dao.agt.RecInvoiceAgtMapper;
import mx.com.ebs.inter.data.dao.facbanco.MCfdMapper;
import mx.com.ebs.inter.data.dao.proc.ProcRecInvoiceMapper;
import mx.com.ebs.inter.data.model.ProcRecInvoiceExample;
import mx.com.ebs.inter.data.model.RecInvoiceAgtExample;
import mx.com.ebs.inter.data.model.facbanco.InvoiceExample;
import mx.com.ebs.inter.data.model.facbanco.MCfdExample;
import mx.com.ebs.inter.exception.ApplicationException;
import mx.com.ebs.inter.util.Validator;
import mx.com.ebs.inter.util.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by robb on 07/12/2015.
 */
@Service
public class InvoicesExporterServiceImpl implements InvoicesExporterService {

    @Autowired
    private MCfdMapper mCfdMapper;

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private RecInvoiceAgtMapper recInvoiceAgtMapper;

    @Autowired
    private ProcRecInvoiceMapper procRecInvoiceMapper;

    @Override
    public int getDocumentsCounter(ExporterSearchBo exporterSearchBo) {
        if( exporterSearchBo == null ){
            return 0;
        }
        MCfdExample mCfdExample = new MCfdExample();
        MCfdExample.Criteria mcfdExampleCriteria = mCfdExample.createCriteria();

        InvoiceExample invoiceExample = new InvoiceExample();
        InvoiceExample.Criteria invoiceExampleCriteria = invoiceExample.createCriteria();


        if(!Validator.isEmptyString(exporterSearchBo.getNumAgente()) ){
            mcfdExampleCriteria.andNUM_AGENTE_EQUALS(exporterSearchBo.getNumAgente());
            invoiceExampleCriteria.andNUMERO_CLIENTEEqualTo(exporterSearchBo.getNumAgente());
        }
        if( !Validator.isEmptyString(exporterSearchBo.getRfc()) ){
            mcfdExampleCriteria.andRFCEqualTo(exporterSearchBo.getRfc());
            invoiceExampleCriteria.andNUMERO_CLIENTEEqualTo( exporterSearchBo.getRfc() );
        }
        if( exporterSearchBo.getFechaDesde() != null && exporterSearchBo.getFechaHasta()!=null ){
            mcfdExampleCriteria.andFECHABetween(exporterSearchBo.getFechaDesde(),exporterSearchBo.getFechaHasta());
            invoiceExampleCriteria.andFECHA_EMISIONNotBetween(exporterSearchBo.getFechaDesde(),exporterSearchBo.getFechaHasta());
        }
        int cfdiCounter = countCFDI(mCfdExample);
        int invoiceCounter = counInvoice(invoiceExample);
        int counter =0;
        return counter;
    }

    @Transactional(value = Variables.TXM_PORTAL, readOnly = true)
    public int counInvoice(InvoiceExample invoiceExample) {
        return invoiceMapper.countByExample(invoiceExample);
    }

    @Transactional(value = Variables.TXM_FACBANCO, readOnly = true)
    public int countCFDI(MCfdExample mCfdExample){
        return mCfdMapper.countByExample(mCfdExample);
    }

    @Transactional(value = Variables.TXM_PORTAL, readOnly = true)
    public int countInvoiceAgt( RecInvoiceAgtExample recInvoiceAgtExample){
        return recInvoiceAgtMapper.countByExample(recInvoiceAgtExample);
    }

    //@Transactional(value = )
    public int countProcRecInvoice(ProcRecInvoiceExample procRecInvoiceExample){
        return 0;
    }


    @Override
    public void exportDocuments(ExporterSearchBo exporterSearchBo) throws ApplicationException {

    }

    public MCfdMapper getmCfdMapper() {
        return mCfdMapper;
    }

    public void setmCfdMapper(MCfdMapper mCfdMapper) {
        this.mCfdMapper = mCfdMapper;
    }

    public InvoiceMapper getInvoiceMapper() {
        return invoiceMapper;
    }

    public void setInvoiceMapper(InvoiceMapper invoiceMapper) {
        this.invoiceMapper = invoiceMapper;
    }

    public RecInvoiceAgtMapper getRecInvoiceAgtMapper() {
        return recInvoiceAgtMapper;
    }

    public void setRecInvoiceAgtMapper(RecInvoiceAgtMapper recInvoiceAgtMapper) {
        this.recInvoiceAgtMapper = recInvoiceAgtMapper;
    }

    public ProcRecInvoiceMapper getProcRecInvoiceMapper() {
        return procRecInvoiceMapper;
    }

    public void setProcRecInvoiceMapper(ProcRecInvoiceMapper procRecInvoiceMapper) {
        this.procRecInvoiceMapper = procRecInvoiceMapper;
    }
}
