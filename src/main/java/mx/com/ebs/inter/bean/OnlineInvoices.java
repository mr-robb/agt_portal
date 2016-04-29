package mx.com.ebs.inter.bean;

import mx.com.ebs.inter.data.bo.RecInvoiceSearchBo;
import mx.com.ebs.inter.data.model.facbanco.Invoice;
import mx.com.ebs.inter.data.model.facbanco.MCfd;
import mx.com.ebs.inter.service.InvoiceService;
import mx.com.ebs.inter.service.MCfdService;
import mx.com.ebs.inter.util.FacesMessageUtil;
import mx.com.ebs.inter.util.PropertiesCleaner;
import mx.com.ebs.inter.util.UnicodeCommonWords;
import mx.com.ebs.inter.util.Validator;
import org.apache.log4j.Logger;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by robb on 11/08/2015.
 */
//@Component
public class OnlineInvoices extends AbstractBean implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(OnlineInvoices.class);

    // CFD
    @Autowired
    private InvoiceService invoiceService;
    private List<Invoice> invoiceList;

    //CFDI
    @Autowired
    private MCfdService mCfdService;
    private List<MCfd> mCfdList;
    private RecInvoiceSearchBo recInvoiceSearchBo;

    private String searchType;

    private String cfd="CFD", cfdi="CFDI";

    @PostConstruct
    public void init(){
        invoiceList = new ArrayList<Invoice>();
        recInvoiceSearchBo = new RecInvoiceSearchBo();
        mCfdList = new ArrayList<MCfd>();
    }

    public void executeSearch(){
        LOGGER.debug("Ejecutando busqueda de " + searchType );
        try {
            PropertiesCleaner.cleanObjectUsingCapitalizedMetods(recInvoiceSearchBo);
        } catch (IllegalAccessException e) {
            LOGGER.error("IllegalAccessException while cleaning object recInvoiceAgtSearchBo");
        } catch (NoSuchMethodException e) {
            LOGGER.error("NoSuchMethodException while cleaning object recInvoiceAgtSearchBo");
        } catch (InvocationTargetException e) {
            LOGGER.error("InvocationTargetException while cleaning object recInvoiceAgtSearchBo");
        }

        if( Validator.isEmptyString(recInvoiceSearchBo.getNumPoliza()) &&
                Validator.isEmptyString(recInvoiceSearchBo.getRfc())){
            FacesMessageUtil.showFacesMessage("Datos inv"+ UnicodeCommonWords.AACUTE_LOWER+"lidos",
                    "Al menos debe introducir el RFC " + UnicodeCommonWords.OACUTE_LOWER +" el n"+UnicodeCommonWords.UACUTE_LOWER + "mero de p"+UnicodeCommonWords.OACUTE_LOWER+"liza",
                    "error");
        }else {
            if ( cfd.equals(searchType)) {
                mCfdList = new ArrayList<MCfd>();
                invoiceList = invoiceService.getListUsingFilter(recInvoiceSearchBo,0,100,null,null);
            } else {
                invoiceList = new ArrayList<Invoice>();
                mCfdList = mCfdService.getListUsingFilter(recInvoiceSearchBo, 0, 100, "FECHA", SortOrder.DESCENDING);
            }
        }
    }

    public void clearForm(){
        recInvoiceSearchBo = new RecInvoiceSearchBo();
        this.invoiceList.clear();
        this.mCfdList.clear();
    }

    public InvoiceService getInvoiceService() {
        return invoiceService;
    }

    public void setInvoiceService(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }

    public MCfdService getmCfdService() {
        return mCfdService;
    }

    public void setmCfdService(MCfdService mCfdService) {
        this.mCfdService = mCfdService;
    }

    public List<MCfd> getmCfdList() {
        return mCfdList;
    }

    public void setmCfdList(List<MCfd> mCfdList) {
        this.mCfdList = mCfdList;
    }

    public RecInvoiceSearchBo getRecInvoiceSearchBo() {
        return recInvoiceSearchBo;
    }

    public void setRecInvoiceSearchBo(RecInvoiceSearchBo recInvoiceSearchBo) {
        this.recInvoiceSearchBo = recInvoiceSearchBo;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getCfd() {
        return cfd;
    }

    public String getCfdi() {
        return cfdi;
    }






}
