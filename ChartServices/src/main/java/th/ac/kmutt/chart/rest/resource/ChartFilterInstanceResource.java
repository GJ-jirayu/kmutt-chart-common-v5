package th.ac.kmutt.chart.rest.resource;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.restlet.representation.Representation;
import org.restlet.representation.Variant;
import org.restlet.resource.ResourceException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import th.ac.kmutt.chart.constant.ServiceConstant;
import th.ac.kmutt.chart.domain.*;
import th.ac.kmutt.chart.model.*;
import th.ac.kmutt.chart.service.ChartService;
import th.ac.kmutt.chart.xstream.common.ImakeResultMessage;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by imake on 20/10/2015.
 */
public class ChartFilterInstanceResource  extends BaseResource {
   // private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
    @Autowired
    @Qualifier("chartServiceJpaImpl")
    private ChartService chartService;

    @Autowired
    private com.thoughtworks.xstream.XStream xstream;
    @Autowired
    private com.thoughtworks.xstream.XStream jsonXstream;

    public ChartFilterInstanceResource() {
        super();
        logger.debug("into constructor ChartFilterInstanceEntity");
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void doInit() throws ResourceException {
        // TODO Auto-generated method stub
        super.doInit();
        logger.debug("into doInit");
    }

    @Override
    protected Representation post(Representation entity, Variant variant)
            throws ResourceException {
        // TODO Auto-generated method stub
        logger.debug("into Post ConsultantReportResource 2");
        InputStream in = null;
        try {
            in = entity.getStream();
            xstream.processAnnotations(ChartFilterInstanceM.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
            ChartFilterInstanceM xsource = new ChartFilterInstanceM();
            Object xtarget = xstream.fromXML(in);
            if (xtarget != null) {
                xsource = (ChartFilterInstanceM) xtarget;
                if (xsource != null) {
                    ChartFilterInstanceEntity domain = new ChartFilterInstanceEntity();
                    BeanUtils.copyProperties(xsource, domain);
                    if(xsource.getFilterM()!=null && xsource.getFilterM().getFilterId()!=null){
                        FilterEntity filterEntity=new FilterEntity();
                        filterEntity.setFilterId(xsource.getFilterM().getFilterId());
                        domain.setFilterByFilterId(filterEntity);
                    }
                    if(xsource.getService()!=null && xsource.getService().getServiceId()!=null){
                        ServiceEntity serviceEntity=new ServiceEntity();
                        serviceEntity.setServiceId(xsource.getService().getServiceId());
                        domain.setServiceByServiceId(serviceEntity);
                    }
                    ChartFilterInstanceEntityPK pk = new ChartFilterInstanceEntityPK();
                    if (xsource.getInstanceId() != null)
                        pk.setInstanceId(xsource.getInstanceId());
                    if (xsource.getFilterId() != null)
                        pk.setFilterId(xsource.getFilterId());
                    domain.setId(pk);

                    if (xsource.getServiceName() != null
                            && xsource.getServiceName().length() != 0) {
                        String serviceName = xsource.getServiceName();
                        if (serviceName.equals(ServiceConstant.CHART_FILTER_INSTANCE_FIND_BY_ID)) {
                            domain = chartService.findChartFilterInstanceEntityById(xsource.getInstanceId());
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            if (domain != null) {
                                List<ChartFilterInstanceM> models = new ArrayList<ChartFilterInstanceM>(1);

                                java.util.ArrayList<ChartFilterInstanceEntity> domains =new ArrayList<ChartFilterInstanceEntity>(1);
                                domains.add(domain);
                                // get Model List
                                models=getChartFilterInstanceModels(domains);

                                imakeMessage.setResultListObj(models);
                            }
                            return getRepresentation(entity, imakeMessage, xstream);
                        } else if (serviceName.equals(ServiceConstant.CHART_FILTER_INSTANCE_SAVE)) {
                            //java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
                            // domain.setCreatedDate(now);
                            //domain.setUpdatedDate(now);
                            int updateRecord = chartService.saveChartFilterInstanceEntity(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.CHART_FILTER_INSTANCE_UPDATE)) {
                            java.sql.Timestamp updatedDate = new java.sql.Timestamp(new Date().getTime());
                         //   domain.setUpdatedDate(updatedDate);
                             int updateRecord = chartService.updateChartFilterInstanceEntity(domain);
                            /*
                            chartService.deleteChartFilterInstanceEntity(domain);
                            String[] ids=xsource.getIds();
                            if(ids!=null)
                            for (int i=0;i<ids.length;i++){
                                String[] id_Array=ids[i].split("_");
                                if(!id_Array[0].equals("-1")){
                                    ChartFilterInstanceEntity domain_inner = new ChartFilterInstanceEntity();
                                    BeanUtils.copyProperties(xsource, domain_inner);

                                    ChartFilterInstanceEntityPK pk_inner = new ChartFilterInstanceEntityPK();
                                    if (xsource.getInstanceId() != null)
                                        pk_inner.setInstanceId(xsource.getInstanceId());
                                    //if (xsource.getFilterId() != null)
                                    pk_inner.setFilterId(Integer.valueOf(id_Array[0]));
                                    domain_inner.setId(pk_inner);
                                    domain_inner.setValue(id_Array[1]);
                                    updateRecord = chartService.saveChartFilterInstanceEntity(domain_inner);
                                }

                            }*/
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.CHART_FILTER_INSTANCE_ITEMS_DELETE)) {

                        } else if (serviceName.equals(ServiceConstant.CHART_FILTER_INSTANCE_DELETE)) {
                            int updateRecord = 0;
                            try {
                                chartService.deleteChartFilterInstanceEntity(domain);
                            } catch (Exception e) {
                                Throwable t = e.getCause();

                                while ((t != null) && !(t instanceof ConstraintViolationException)) {
                                    t = t.getCause();
                                }

                                if (t instanceof ConstraintViolationException) {
                                    updateRecord = -9;
                                }
                            }
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.CHART_FILTER_INSTANCE_SEARCH)) {
                            java.util.ArrayList<ChartFilterInstanceEntity> domains = (java.util.ArrayList<ChartFilterInstanceEntity>)
                                    chartService.listChartFilterInstanceEntity(xsource);
                            List<ChartFilterInstanceM> models = new ArrayList<ChartFilterInstanceM>(domains.size());
                            models = getChartFilterInstanceModels(domains);
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            imakeMessage.setResultListObj(models);
                            return getRepresentation(entity, imakeMessage, xstream);
                            /*
                            Paging page = xsource.getPaging();
                             @SuppressWarnings("rawtypes")
                            List result = (List) chartService.searchChartFilterInstanceEntity(domain, page, xsource.getKeySearch());
                            if (result != null && result.size() == 2) {
                                java.util.ArrayList<ChartFilterInstanceEntity> domains = (java.util.ArrayList<ChartFilterInstanceEntity>) result
                                        .get(0);
                                String domains_size = (String) result.get(1);
                                ImakeResultMessage imakeMessage = new ImakeResultMessage();

                                List<ChartFilterInstanceM> models = new ArrayList<ChartFilterInstanceM>();
                                if (domains_size != null && domains_size.length() != 0)
                                    imakeMessage.setMaxRow(domains_size);
                                if (domains != null && domains.size() > 0) {
                                    models = getChartFilterInstanceModels(domains);
                                }
                                imakeMessage.setResultListObj(models);
                                return getRepresentation(entity, imakeMessage, xstream);
                            }
                            */
                        }
                        else if( serviceName.equals(ServiceConstant.CHART_FILTER_INSTANCE_GET_ALL_FILTER)){
                            List<ChartFilterInstanceM> filters = chartService.getChartFilterInstance(xsource);
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            imakeMessage.setResultListObj(filters);
                            return getRepresentation(entity, imakeMessage, xstream);
                        }
                    } else {
                    }
                }

            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            logger.debug(" into Finally Call");
            try {
                if (in != null)
                    in.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;

    }

    private List<ChartFilterInstanceM> getChartFilterInstanceModels(
            java.util.ArrayList<ChartFilterInstanceEntity> domains) {
        List<ChartFilterInstanceM> models = new ArrayList<ChartFilterInstanceM>(
                domains.size());
        for (ChartFilterInstanceEntity domain : domains) {
            ChartFilterInstanceM model = new ChartFilterInstanceM();
            BeanUtils.copyProperties(domain, model);

            // set FilterM
            if (domain.getFilterByFilterId() != null) {
                FilterM filter = new FilterM();
                BeanUtils.copyProperties(domain.getFilterByFilterId(), filter);
                filter.setPaging(null);
                model.setFilterM(filter);
            }
            // set ServiceM
            if (domain.getServiceByServiceId() != null) {
                ServiceM serviceM= new ServiceM();
                BeanUtils.copyProperties(domain.getServiceByServiceId(), serviceM);
                serviceM.setPaging(null);
                model.setService(serviceM);
            }
            model.setPaging(null);
            models.add(model);
        }
        return models;
    }

    private Representation returnUpdateRecord(Representation entity, ChartFilterInstanceM model, int updateRecord) {
        ImakeResultMessage imakeMessage = new ImakeResultMessage();
        List<ChartFilterInstanceM> xsources = new ArrayList<ChartFilterInstanceM>(1);
        model.setUpdateRecord(updateRecord);
        xsources.add(model);
        imakeMessage.setResultListObj(xsources);
        return getRepresentation(entity, imakeMessage, xstream);
    }

    @Override
    protected Representation get(Variant variant) throws ResourceException {
        // TODO Auto-generated method stub
        return null;
    }

}
