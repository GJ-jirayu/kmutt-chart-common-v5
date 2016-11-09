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
import th.ac.kmutt.chart.domain.FilterEntity;
import th.ac.kmutt.chart.domain.ServiceEntity;
import th.ac.kmutt.chart.domain.ServiceFilterMappingEntity;
import th.ac.kmutt.chart.domain.ServiceFilterMappingEntityPK;
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
public class ServiceFilterMappingResource  extends BaseResource {
   // private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
    @Autowired
    @Qualifier("chartServiceJpaImpl")
    private ChartService chartService;

    @Autowired
    private com.thoughtworks.xstream.XStream xstream;
    @Autowired
    private com.thoughtworks.xstream.XStream jsonXstream;

    public ServiceFilterMappingResource() {
        super();
        logger.debug("into constructor ServiceFilterMappingEntity");
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
            xstream.processAnnotations(ServiceFilterMappingM.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
            ServiceFilterMappingM xsource = new ServiceFilterMappingM();
            Object xtarget = xstream.fromXML(in);
            if (xtarget != null) {
                xsource = (ServiceFilterMappingM) xtarget;
                if (xsource != null) {
                    ServiceFilterMappingEntity domain = new ServiceFilterMappingEntity();
                    BeanUtils.copyProperties(xsource, domain);

                    ServiceFilterMappingEntityPK pk = new ServiceFilterMappingEntityPK();
                    if (xsource.getServiceId() != null)
                        pk.setServiceId(xsource.getServiceId());
                    if (xsource.getFilterId() != null)
                        pk.setFilterId(xsource.getFilterId());
                    domain.setId(pk);



                    if (xsource.getServiceName() != null
                            && xsource.getServiceName().length() != 0) {
                        String serviceName = xsource.getServiceName();
                        if (serviceName.equals(ServiceConstant.SERVICE_FILTER_MAPPING_FIND_BY_ID)) {
                            domain = chartService.findServiceFilterMappingEntityById(pk);
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            if (domain != null) {
                                List<ServiceFilterMappingM> models = new ArrayList<ServiceFilterMappingM>(1);

                                java.util.ArrayList<ServiceFilterMappingEntity> domains =new ArrayList<ServiceFilterMappingEntity>(1);
                                domains.add(domain);
                                // get Model List
                                models=getServiceFilterMappingModels(domains);

                                imakeMessage.setResultListObj(models);
                            }
                            return getRepresentation(entity, imakeMessage, xstream);
                        } else if (serviceName.equals(ServiceConstant.SERVICE_FILTER_MAPPING_SAVE)) {
                            //java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
                            // domain.setCreatedDate(now);
                            //domain.setUpdatedDate(now);
                            int updateRecord = chartService.saveServiceFilterMappingEntity(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.SERVICE_FILTER_MAPPING_UPDATE)) {
                            //java.sql.Timestamp updatedDate = new java.sql.Timestamp(new Date().getTime());
                            //domain.setUpdatedDate(updatedDate);
                            int updateRecord = chartService.updateServiceFilterMappingEntity(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.SERVICE_FILTER_MAPPING_ITEMS_DELETE)) {

                        } else if (serviceName.equals(ServiceConstant.SERVICE_FILTER_MAPPING_DELETE)) {
                            int updateRecord = 0;
                            try {
                                chartService.deleteServiceFilterMappingEntity(domain);
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
                        } else if (serviceName.equals(ServiceConstant.SERVICE_FILTER_MAPPING_SEARCH)) {
                            java.util.ArrayList<ServiceFilterMappingEntity> mapEntitys = (java.util.ArrayList<ServiceFilterMappingEntity>)
                                    chartService.listServiceFilterMappingEntity(xsource);
                            
                            List<ServiceFilterMappingM> models = new ArrayList<ServiceFilterMappingM>(mapEntitys.size());
                     
                            //makeModel
                            for (ServiceFilterMappingEntity mapEntity : mapEntitys) {
                                ServiceFilterMappingM model = new ServiceFilterMappingM();
                                model.setFilterId(mapEntity.getId().getFilterId());
                                model.setServiceId(mapEntity.getId().getServiceId());
                                FilterEntity fe = chartService.findFilterEntityById(mapEntity.getId().getFilterId());
                                FilterM f = new FilterM();
                                System.out.println("test sf:"+fe.getValueType());
                                BeanUtils.copyProperties(fe,f );
                                model.setFilterM(f);
                                models.add(model);
                            } // end make model
                            
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            imakeMessage.setResultListObj(models);
                            return getRepresentation(entity, imakeMessage, xstream);
                            /*
                            Paging page = xsource.getPaging();
                             @SuppressWarnings("rawtypes")
                            List result = (List) chartService.searchServiceFilterMappingEntity(domain, page, xsource.getKeySearch());
                            if (result != null && result.size() == 2) {
                                java.util.ArrayList<ServiceFilterMappingEntity> domains = (java.util.ArrayList<ServiceFilterMappingEntity>) result
                                        .get(0);
                                String domains_size = (String) result.get(1);
                                ImakeResultMessage imakeMessage = new ImakeResultMessage();

                                List<ServiceFilterMappingM> models = new ArrayList<ServiceFilterMappingM>();
                                if (domains_size != null && domains_size.length() != 0)
                                    imakeMessage.setMaxRow(domains_size);
                                if (domains != null && domains.size() > 0) {
                                    models = getServiceFilterMappingModels(domains);
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

    private List<ServiceFilterMappingM> getServiceFilterMappingModels(
            java.util.ArrayList<ServiceFilterMappingEntity> domains) {
        List<ServiceFilterMappingM> models = new ArrayList<ServiceFilterMappingM>(
                domains.size());
        for (ServiceFilterMappingEntity domain : domains) {
            ServiceFilterMappingM model = new ServiceFilterMappingM();
            model.setFilterId(domain.getId().getFilterId());
            model.setServiceId(domain.getId().getServiceId());
            /* set FilterM
            if (domain.getFilterByFilterId() != null) {
                FilterM filterM = new FilterM();
                BeanUtils.copyProperties(domain.getFilterByFilterId(), filterM);
                filterM.setPaging(null);
                model.setFilterM(filterM);
            }
            // set ServiceM
            if (domain.getServiceByServiceId() != null) {
                ServiceM serviceM = new ServiceM();
                BeanUtils.copyProperties(domain.getServiceByServiceId(), serviceM);
                serviceM.setPaging(null);
                model.setService(serviceM);
            }*/
            model.setPaging(null);
            models.add(model);
        }
        return models;
    }

    private Representation returnUpdateRecord(Representation entity, ServiceFilterMappingM model, int updateRecord) {
        ImakeResultMessage imakeMessage = new ImakeResultMessage();
        List<ServiceFilterMappingM> xsources = new ArrayList<ServiceFilterMappingM>(1);
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
