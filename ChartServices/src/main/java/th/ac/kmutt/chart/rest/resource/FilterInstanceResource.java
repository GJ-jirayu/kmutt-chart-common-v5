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
import java.util.List;

/**
 * Created by imake on 20/10/2015.
 */
public class FilterInstanceResource  extends BaseResource {
//    private static final Logger logger = Logger.getLogger("MISSConsultLog");

    @Autowired
    @Qualifier("chartServiceJpaImpl")
    private ChartService chartService;

    @Autowired
    private com.thoughtworks.xstream.XStream xstream;
    @Autowired
    private com.thoughtworks.xstream.XStream jsonXstream;

    public FilterInstanceResource() {
        super();
        logger.debug("into constructor FilterInstanceEntity");
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
            xstream.processAnnotations(FilterInstanceM.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
            FilterInstanceM xsource = new FilterInstanceM();
            Object xtarget = xstream.fromXML(in);
            if (xtarget != null) {
                xsource = (FilterInstanceM) xtarget;
                if (xsource != null) {
                    FilterInstanceEntity domain = new FilterInstanceEntity();
                    BeanUtils.copyProperties(xsource, domain);

                    FilterInstanceEntityPK pk = new FilterInstanceEntityPK();
                    if (xsource.getFilterId() != null)
                        pk.setFilterId(xsource.getFilterId());
                    if (xsource.getInstanceId() != null)
                        pk.setInstanceId(xsource.getInstanceId());
                    domain.setId(pk);


                    if (xsource.getServiceName() != null
                            && xsource.getServiceName().length() != 0) {
                        String serviceName = xsource.getServiceName();
                        if (serviceName.equals(ServiceConstant.FILTER_INSTANCE_FIND_BY_ID)) {
                            domain = chartService.findFilterInstanceEntityById(xsource.getInstanceId());
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            if (domain != null) {
                                List<FilterInstanceM> models = new ArrayList<FilterInstanceM>(1);

                                java.util.ArrayList<FilterInstanceEntity> domains =new ArrayList<FilterInstanceEntity>(1);
                                domains.add(domain);
                                // get Model List
                                models=getFilterInstanceModels(domains);

                                imakeMessage.setResultListObj(models);
                            }
                            return getRepresentation(entity, imakeMessage, xstream);
                        }else if (serviceName.equals(ServiceConstant.FILTER_INSTANCE_SAVE)) {
                        	chartService.deleteFilterInstance(xsource.getInstanceId());
                        	FilterInstanceM fim = chartService.saveFilterInstance(xsource);
                        	int updateRecord = fim!=null? 1 : 0;
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.FILTER_INSTANCE_UPDATE)) {
                        	chartService.deleteFilterInstance(xsource.getInstanceId());
                        	Integer rec = chartService.updateFilterInstance(xsource);
                        	int updateRecord = rec!=null? rec : 0;
                        	return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.FILTER_INSTANCE_DELETE)) {
                            int updateRecord = 0;
                            try {
                                chartService.deleteFilterInstanceEntity(domain);
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
                        } else if (serviceName.equals(ServiceConstant.FILTER_INSTANCE_SEARCH)) {

                             @SuppressWarnings({ "rawtypes", "unchecked" })
                             List<FilterInstanceEntity> domains =  chartService.listFilterInstanceEntity(xsource);
                            List<FilterInstanceM> models = new ArrayList<FilterInstanceM>(domains.size());
                            models = getFilterInstanceModels(domains);
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                           // System.out.println("size:"+models.get(0).getFilterM().getFilterId());
                            imakeMessage.setResultListObj(models);
                            return getRepresentation(entity, imakeMessage, xstream);

                        }else if (serviceName.equals(ServiceConstant.FILTER_INSTANCE_GET)) {
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                        	FilterInstanceM fin = chartService.getFilterInstance(xsource);
                        	List<FilterInstanceM> fins = new ArrayList<FilterInstanceM>();
                        	fins.add(fin);
                        	  imakeMessage.setResultListObj(fins);
                            return getRepresentation(entity, imakeMessage, xstream); 
                    	}else if (serviceName.equals(ServiceConstant.FILTER_INSTANCE_WITH_ITEM)) {
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                        	List<FilterInstanceM> fins = chartService.getFilterInstanceWithItem(xsource.getInstanceId());
                            imakeMessage.setResultListObj(fins);
                            return getRepresentation(entity, imakeMessage, xstream);
                        }
                    	else if (serviceName.equals(ServiceConstant.CASCADE_FILTER)) {
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                        	FilterInstanceM fin = chartService.cascadeFilter(xsource);
                        	List<FilterInstanceM> fins = new ArrayList<FilterInstanceM>();
                        	fins.add(fin);
                            imakeMessage.setResultListObj(fins);
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

	private List<FilterInstanceM> getFilterInstanceModels(List<FilterInstanceEntity> domains) {
        List<FilterInstanceM> models = new ArrayList<FilterInstanceM>(
                domains.size());
        for (FilterInstanceEntity domain : domains) {
            FilterInstanceM model = new FilterInstanceM();
            BeanUtils.copyProperties(domain, model);
            FilterInstanceEntityPK pk =domain.getId();
            if(pk!=null){
                model.setFilterId(pk.getFilterId());
                model.setInstanceId(pk.getInstanceId());
            }
            // set FilterByFilterM
            if (domain.getFilterByFilterId() != null) {
                FilterM filterM= new FilterM();
                BeanUtils.copyProperties(domain.getFilterByFilterId(), filterM);
                filterM.setPaging(null);
                model.setFilterM(filterM);
            }
            model.setPaging(null);
            models.add(model);;
        }
        return models;
    }

    private Representation returnUpdateRecord(Representation entity, FilterInstanceM model, int updateRecord) {
        ImakeResultMessage imakeMessage = new ImakeResultMessage();
        List<FilterInstanceM> xsources = new ArrayList<FilterInstanceM>(1);
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
