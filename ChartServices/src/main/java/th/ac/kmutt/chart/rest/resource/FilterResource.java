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
import th.ac.kmutt.chart.domain.ServiceFilterMappingEntity;
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
public class FilterResource  extends BaseResource {
    //private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
    @Autowired
    @Qualifier("chartServiceJpaImpl")
    private ChartService chartService;

    @Autowired
    private com.thoughtworks.xstream.XStream xstream;
    @Autowired
    private com.thoughtworks.xstream.XStream jsonXstream;

    public FilterResource() {
        super();
        logger.debug("into constructor FilterEntity");
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
            xstream.processAnnotations(FilterM.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
            FilterM xsource = new FilterM();
            Object xtarget = xstream.fromXML(in);
            if (xtarget != null) {
                xsource = (FilterM) xtarget;
                if (xsource != null) {
                    FilterEntity domain = new FilterEntity();
                    BeanUtils.copyProperties(xsource, domain);
                    /*
                    FilterEntityPK pk = new FilterEntityPK();
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
                        if (serviceName.equals(ServiceConstant.FILTER_FIND_BY_ID)) {
                            domain = chartService.findFilterEntityById(xsource.getFilterId());
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            if (domain != null) {
                                List<FilterM> models = new ArrayList<FilterM>(1);

                                java.util.ArrayList<FilterEntity> domains =new ArrayList<FilterEntity>(1);
                                domains.add(domain);
                                // get Model List
                                models=getFilterModels(domains);

                                imakeMessage.setResultListObj(models);
                            }
                            return getRepresentation(entity, imakeMessage, xstream);
                        } else    if (serviceName.equals(ServiceConstant.FILTER_GET_ITEMS)) {
                        	//single Filter return
                            domain = chartService.getFilterValueList(xsource.getFilterId());
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            if (domain != null) {
                                List<FilterM> models = new ArrayList<FilterM>(1);

                                java.util.ArrayList<FilterEntity> domains =new ArrayList<FilterEntity>(1);
                                domains.add(domain);
                                // get Model List
                                models=getFilterModels(domains);

                                imakeMessage.setResultListObj(models);
                            }
                            return getRepresentation(entity, imakeMessage, xstream);
                        }   else if (serviceName.equals(ServiceConstant.FILTER_SAVE)) {
                        	try{
	                        	chartService.saveFilter(xsource);
	                            return returnUpdateRecord(entity, xsource, 1);
                        	}catch(Exception e){
                        		return returnUpdateRecord(entity,null,0);
                        	}
                        } else if (serviceName.equals(ServiceConstant.FILTER_UPDATE)) {
//                            int updateRecord = chartService.updateFilterEntity(domain);
//                            return returnUpdateRecord(entity, xsource, updateRecord);
                        	try{

	                        	chartService.updateFilter(xsource);
	                            return returnUpdateRecord(entity, xsource, 1);
                        	}catch(Exception e){
                        		return returnUpdateRecord(entity,null,0);
                        	}
                        } else if (serviceName.equals(ServiceConstant.FILTER_ITEMS_DELETE)) {

                        } else if (serviceName.equals(ServiceConstant.FILTER_DELETE)) {
                            int updateRecord = 0;
                            try {
                                chartService.deleteFilterEntity(domain);
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
                        } else if (serviceName.equals(ServiceConstant.FILTER_SEARCH)) {
                            @SuppressWarnings("unchecked")
							java.util.ArrayList<FilterEntity> domains = (java.util.ArrayList<FilterEntity>)chartService.listFilterEntity(xsource);
                            List<FilterM> models = new ArrayList<FilterM>(domains.size());
                            models = getFilterModels(domains);
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            imakeMessage.setResultListObj(models);
                            return getRepresentation(entity, imakeMessage, xstream);
                            /*
                            Paging page = xsource.getPaging();
                             @SuppressWarnings("rawtypes")
                            List result = (List) chartService.searchFilterEntity(domain, page, xsource.getKeySearch());
                            if (result != null && result.size() == 2) {
                                java.util.ArrayList<FilterEntity> domains = (java.util.ArrayList<FilterEntity>) result
                                        .get(0);
                                String domains_size = (String) result.get(1);
                                ImakeResultMessage imakeMessage = new ImakeResultMessage();

                                List<FilterM> models = new ArrayList<FilterM>();
                                if (domains_size != null && domains_size.length() != 0)
                                    imakeMessage.setMaxRow(domains_size);
                                if (domains != null && domains.size() > 0) {
                                    models = getFilterModels(domains);
                                }
                                imakeMessage.setResultListObj(models);
                                return getRepresentation(entity, imakeMessage, xstream);
                            }
                            */
                        }
                        else if( serviceName.equals(ServiceConstant.FILTER_GET_GLOBAL_FILTER)){
                            List<FilterM> globalFilters = chartService.getGlobalFilter();
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            imakeMessage.setResultListObj(globalFilters);
                            return getRepresentation(entity, imakeMessage, xstream);
                        }
                        else if( serviceName.equals(ServiceConstant.FILTER_GET_FILTER_SERVICE)){
                        	 List<FilterM> filters = null;
                        	//try{
                        	 filters = chartService.getFilterOfService(xsource.getServiceId(),xsource.getUserid());
                        //	}catch(Exception e){
                        		//filters = new ArrayList<FilterM>();
                        	//	System.out.println(" Exception FilterResource sname FILTER_GET_FILTER_SERVICE  reason= " +e.getMessage());
                        //	}
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            imakeMessage.setResultListObj(filters);
                            return getRepresentation(entity, imakeMessage, xstream);
                        }
                        else if( serviceName.equals(ServiceConstant.PARAM_DETAIL   ) ){
                        	FilterM filter = chartService.getFilterWithParam(xsource.getFilterId());
                        	   List<FilterM> filters = new ArrayList<FilterM>();
                        	   filters.add(filter);
                        	  ImakeResultMessage imakeMessage = new ImakeResultMessage();
                              imakeMessage.setResultListObj(filters);
                              return getRepresentation(entity, imakeMessage, xstream);
                        }
                    } else { // else serviceName null
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

    private List<FilterM> getFilterModels(
            java.util.ArrayList<FilterEntity> domains) {
        List<FilterM> models = new ArrayList<FilterM>(
                domains.size());
        for (FilterEntity domain : domains) {
            FilterM model = new FilterM();
            BeanUtils.copyProperties(domain, model);

            model.setPaging(null);
            models.add(model);
        }
        return models;
    }

    private Representation returnUpdateRecord(Representation entity, FilterM model, int updateRecord) {
        ImakeResultMessage imakeMessage = new ImakeResultMessage();
        List<FilterM> xsources = new ArrayList<FilterM>(1);
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
