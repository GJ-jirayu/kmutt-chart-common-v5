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
import th.ac.kmutt.chart.domain.ServiceEntity;
import th.ac.kmutt.chart.model.*;
import th.ac.kmutt.chart.service.ChartService;
import th.ac.kmutt.chart.xstream.common.ImakeResultMessage;
import th.ac.kmutt.chart.xstream.common.Paging;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ServiceResource  extends BaseResource {
   // private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
    @Autowired
    @Qualifier("chartServiceJpaImpl")
    private ChartService chartService;

    @Autowired
    private com.thoughtworks.xstream.XStream xstream;
    @Autowired
    private com.thoughtworks.xstream.XStream jsonXstream;

    public ServiceResource() {
        super();
        logger.debug("into constructor ServiceEntity");
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
            xstream.processAnnotations(ServiceM.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
            ServiceM xsource = new ServiceM();
            Object xtarget = xstream.fromXML(in);
            if (xtarget != null) {
                xsource = (ServiceM) xtarget;

                if (xsource != null) {
                    ServiceEntity domain = new ServiceEntity();
                    BeanUtils.copyProperties(xsource, domain);
                    domain.setServiceName(xsource.getDatasourceName());
                    /*
                    ServiceEntityPK pk = new ServiceEntityPK();
                    if (xsource.getResearcherId() != null)
                        pk.setResearcherId(xsource.getResearcherId());
                    if (xsource.getResearchGroupId() != null)
                        pk.setResearchGroupId(xsource.getResearchGroupId());
                    domain.setId(pk);
                    if (xsource.getResearchGroup() != null && xsource.getResearchGroup().getResearchGroupId() != null) {
                        ResearchGroup researchGroup = new ResearchGroup();
                        BeanUtils.copyProperties(xsource.getResearchGroup(), researchGroup);
                        domain.setResearchGroup(researchGroup);
                    }
                    */


                    if (xsource.getServiceName() != null
                            && xsource.getServiceName().length() != 0) {
                        String serviceName = xsource.getServiceName();
                        if (serviceName.equals(ServiceConstant.SERVICE_FIND_BY_ID)) {
                            domain = chartService.findServiceEntityById(xsource.getServiceId());
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            if (domain != null) {
                                List<ServiceM> models = new ArrayList<ServiceM>(1);

                                java.util.ArrayList<ServiceEntity> domains =new ArrayList<ServiceEntity>(1);
                                domains.add(domain);
                                // get Model List
                                models=getServiceModels(domains);

                                imakeMessage.setResultListObj(models);
                            }
                            return getRepresentation(entity, imakeMessage, xstream);
                        } else if (serviceName.equals(ServiceConstant.SERVICE_SAVE)) {
                            //java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
                            // domain.setCreatedDate(now);
                            //domain.setUpdatedDate(now);
                            int updateRecord = chartService.saveServiceEntity(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.SERVICE_UPDATE)) {
                            //java.sql.Timestamp updatedDate = new java.sql.Timestamp(new Date().getTime());
                            //domain.setUpdatedDate(updatedDate);
                            int updateRecord = chartService.updateServiceEntity(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.SERVICE_ITEMS_DELETE)) {

                        } else if (serviceName.equals(ServiceConstant.SERVICE_DELETE)) {
                            int updateRecord = 0;
                            try {
                                chartService.deleteServiceEntity(domain);
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
                        } else if (serviceName.equals(ServiceConstant.SERVICE_SEARCH)) {

                            Paging page = xsource.getPaging();
                             @SuppressWarnings("rawtypes")
                          //  List result = (List) chartService.listServiceEntity( xsource);
                           // if (result != null && result.size() == 2) {
                            java.util.ArrayList<ServiceEntity> domains = (java.util.ArrayList<ServiceEntity>)chartService.listServiceEntity(xsource);
                            List<ServiceM> models = new ArrayList<ServiceM>(domains.size());
                            models = getServiceModels(domains);
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            imakeMessage.setResultListObj(models);
                            return getRepresentation(entity, imakeMessage, xstream);
                          //  }
                        } else if (serviceName.equals(ServiceConstant.SERVICE_SEARCH_BY_CHART)) {
                            Paging page = xsource.getPaging();
                            java.util.ArrayList<ServiceEntity> domains = (java.util.ArrayList<ServiceEntity>) chartService.listServiceByChartId(xsource);
                            List<ServiceM> models = new ArrayList<ServiceM>(domains.size());
                            models = getServiceModels(domains);
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            imakeMessage.setResultListObj(models);
                            return getRepresentation(entity, imakeMessage, xstream);
         
                        }else if(serviceName.equals(ServiceConstant.CHART_DS_DETAIL)){
                        	 List<ServiceM> dss = new ArrayList<ServiceM>();
                        	try{
	                        	 ServiceM ds = chartService.findServiceByServiceId(domain.getServiceId(),xsource.getUserid());

	                        	 ds.setChartList( chartService.findChartByServiceId(domain.getServiceId()) );
	                        	 ds.setUserList(  chartService.findUserByServiceId(domain.getServiceId()) );
	                        	 dss.add(ds);
	                        	 logger.info(" chart datasource detail success sid="+ds.getServiceId());
                        	}catch(Exception e){
                        		e.printStackTrace();
                        		logger.error(" chart datasource detail error reason"+e);
                        		dss = new ArrayList<ServiceM>();
                        		
                        	}
                       	 	ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            imakeMessage.setResultListObj(dss);
                        	 return getRepresentation(entity, imakeMessage, xstream);
                        }else if( serviceName.equals(ServiceConstant.CHART_DS_SAVE)){
                        	int updateRecord  = 0;
                        	 if(xsource.getServiceId()==null ){
                        		 updateRecord = chartService.saveService(xsource);
                        	 }
                        	 else{
                        		 updateRecord = chartService.updateService(xsource);
                        	 }
                              return returnUpdateRecord(entity, xsource, updateRecord);
                        }else if( serviceName.equals(ServiceConstant.CHART_DS_DELETE)){
                        		int deleteRecord  = 0;
	                       		deleteRecord = chartService.deleteServiceEntity(domain);
	                             return returnUpdateRecord(entity, xsource, deleteRecord);
	                     }else if( serviceName.equals(ServiceConstant.CHART_DS_ALL_USER)){
	                    	  List<ServiceM> models = new ArrayList<ServiceM>();
	                    	   try{
	                    		   ServiceM s = new ServiceM();
		                		   List<UserM> users = chartService.listUser();
		                   	       s.setUserList(users);
		                   	        models.add(s);
	                    	   }catch(Exception e){
	                    	   }
	                		   ImakeResultMessage imakeMessage = new ImakeResultMessage();
	                            imakeMessage.setResultListObj(models);
	                            return getRepresentation(entity, imakeMessage, xstream);
		                    
		                    }else if( serviceName.equals(ServiceConstant.CHART_DS_USER_BY_DATASOURCE)){
		                  	  List<ServiceM> models = new ArrayList<ServiceM>();
		                  	   ServiceM s = new ServiceM();
		                  	   try{
			                		   List<UserM> users = chartService.findUserByServiceId(xsource.getServiceId());
			                   	       s.setUserList(users);
			                   	        models.add(s);
		                  	   }catch(Exception e){
		                  		   models.add(null);
		                  	   }
		              		   ImakeResultMessage imakeMessage = new ImakeResultMessage();
		                          imakeMessage.setResultListObj(models);
		                          return getRepresentation(entity, imakeMessage, xstream);
		                   }
                    } // end chekc null serviceName
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

    private List<ServiceM> getServiceModels(
            java.util.ArrayList<ServiceEntity> domains) {
        List<ServiceM> models = new ArrayList<ServiceM>(
                domains.size());
        for (ServiceEntity domain : domains) {
            ServiceM model = new ServiceM();
            BeanUtils.copyProperties(domain, model);//
            model.setDatasourceName(domain.getServiceName());
            model.setPaging(null);
            models.add(model);
        }
        return models;
    }

    private Representation returnUpdateRecord(Representation entity, ServiceM model, int updateRecord) {
        ImakeResultMessage imakeMessage = new ImakeResultMessage();
        List<ServiceM> xsources = new ArrayList<ServiceM>(1);
        model.setUpdateRecord(updateRecord);
        xsources.add(model);
        imakeMessage.setReturnId(String.valueOf(updateRecord));
        imakeMessage.setResultListObj(xsources);
        return getRepresentation(entity, imakeMessage, xstream);
    }

    @Override
    protected Representation get(Variant variant) throws ResourceException {
        // TODO Auto-generated method stub
        return null;
    }

}
