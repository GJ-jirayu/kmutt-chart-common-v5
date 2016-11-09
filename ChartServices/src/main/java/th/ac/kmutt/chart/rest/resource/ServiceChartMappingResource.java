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
import th.ac.kmutt.chart.domain.ChartFeatureInstanceEntity;
import th.ac.kmutt.chart.domain.ServiceChartMappingEntity;
import th.ac.kmutt.chart.domain.ServiceChartMappingEntityPK;
import th.ac.kmutt.chart.model.*;
import th.ac.kmutt.chart.service.ChartService;
import th.ac.kmutt.chart.xstream.common.ImakeResultMessage;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by imake on 20/10/2015.
 */
public class ServiceChartMappingResource  extends BaseResource {
  //  private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
    @Autowired
    @Qualifier("chartServiceJpaImpl")
    private ChartService chartService;

    @Autowired
    private com.thoughtworks.xstream.XStream xstream;
    @Autowired
    private com.thoughtworks.xstream.XStream jsonXstream;

    public ServiceChartMappingResource() {
        super();
        logger.debug("into constructor ServiceChartMappingEntity");
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
            xstream.processAnnotations(ServiceChartMappingM.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
            ServiceChartMappingM xsource = new ServiceChartMappingM();
            Object xtarget = xstream.fromXML(in);
            if (xtarget != null) {
                xsource = (ServiceChartMappingM) xtarget;
                if (xsource != null) {
                    ServiceChartMappingEntity domain = new ServiceChartMappingEntity();
                    BeanUtils.copyProperties(xsource, domain);

                    ServiceChartMappingEntityPK pk = new ServiceChartMappingEntityPK();
                    if (xsource.getServiceId() != null)
                        pk.setServiceId(xsource.getServiceId());
                    if (xsource.getChartId() != null)
                        pk.setChartId(xsource.getChartId());
                    domain.setId(pk);




                    if (xsource.getServiceName() != null
                            && xsource.getServiceName().length() != 0) {
                        String serviceName = xsource.getServiceName();
                        if (serviceName.equals(ServiceConstant.SERVICE_CHART_MAPPING_FIND_BY_ID)) {
                            domain = chartService.findServiceChartMappingEntityById(pk);
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            if (domain != null) {
                                List<ServiceChartMappingM> models = new ArrayList<ServiceChartMappingM>(1);

                                java.util.ArrayList<ServiceChartMappingEntity> domains =new ArrayList<ServiceChartMappingEntity>(1);
                                domains.add(domain);
                                // get Model List
                             //   models=getServiceChartMappingModels(domains);

                                imakeMessage.setResultListObj(models);
                            }
                            return getRepresentation(entity, imakeMessage, xstream);
                        } else if (serviceName.equals(ServiceConstant.SERVICE_CHART_MAPPING_SAVE)) {
                            //java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
                            // domain.setCreatedDate(now);
                            //domain.setUpdatedDate(now);
                            int updateRecord = chartService.saveServiceChartMappingEntity(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.SERVICE_CHART_MAPPING_UPDATE)) {
                            //java.sql.Timestamp updatedDate = new java.sql.Timestamp(new Date().getTime());
                            //domain.setUpdatedDate(updatedDate);
                            int updateRecord = chartService.updateServiceChartMappingEntity(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.SERVICE_CHART_MAPPING_ITEMS_DELETE)) {

                        } else if (serviceName.equals(ServiceConstant.SERVICE_CHART_MAPPING_DELETE)) {
                            int updateRecord = 0;
                            try {
                                chartService.deleteServiceChartMappingEntity(domain);
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
                        } else if (serviceName.equals(ServiceConstant.SERVICE_CHART_MAPPING_SEARCH)) {
                            /*
                            Paging page = xsource.getPaging();
                             @SuppressWarnings("rawtypes")
                            List result = (List) chartService.searchServiceChartMappingEntity(domain, page, xsource.getKeySearch());
                            if (result != null && result.size() == 2) {
                                java.util.ArrayList<ServiceChartMappingEntity> domains = (java.util.ArrayList<ServiceChartMappingEntity>) result
                                        .get(0);
                                String domains_size = (String) result.get(1);
                                ImakeResultMessage imakeMessage = new ImakeResultMessage();

                                List<ServiceChartMappingM> models = new ArrayList<ServiceChartMappingM>();
                                if (domains_size != null && domains_size.length() != 0)
                                    imakeMessage.setMaxRow(domains_size);
                                if (domains != null && domains.size() > 0) {
                                    models = getServiceChartMappingModels(domains);
                                }
                                imakeMessage.setResultListObj(models);
                                return getRepresentation(entity, imakeMessage, xstream);
                            }
                            */
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
/*
    private List<ServiceChartMappingM> getServiceChartMappingModels(
    		
            java.util.ArrayList<ServiceChartMappingEntity> domains) {
        List<ServiceChartMappingM> models = new ArrayList<ServiceChartMappingM>(
                domains.size());
        for (ServiceChartMappingEntity domain : domains) {
            ServiceChartMappingM model = new ServiceChartMappingM();
            BeanUtils.copyProperties(domain, model);
            // set ChartM
            if (domain.getChartByChartId() != null) {
                ChartM chartM = new ChartM();
                BeanUtils.copyProperties(domain.getChartByChartId(), chartM);
                if (domain.getChartByChartId().getChartFeatureByChartId() != null) {
                    ChartFeatureM chartFeatureM = new ChartFeatureM();
                    BeanUtils.copyProperties(domain.getChartByChartId().getChartFeatureByChartId(), chartFeatureM);

                    chartM.setChartFeature(chartFeatureM);
                    chartM.setPaging(null);
                }
                model.setChart(chartM);
            }
            // set ServiceM
            if (domain.getServiceByServiceId() != null) {
                ServiceM serviceM = new ServiceM();
                BeanUtils.copyProperties(domain.getServiceByServiceId(), serviceM);
                model.setService(serviceM);
            }
            model.setPaging(null);
            models.add(model);
        }
        return models;
    }*/

    private Representation returnUpdateRecord(Representation entity, ServiceChartMappingM model, int updateRecord) {
        ImakeResultMessage imakeMessage = new ImakeResultMessage();
        List<ServiceChartMappingM> xsources = new ArrayList<ServiceChartMappingM>(1);
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
