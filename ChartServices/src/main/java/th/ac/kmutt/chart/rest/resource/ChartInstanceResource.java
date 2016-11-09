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
public class ChartInstanceResource  extends BaseResource {
    //private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
    @Autowired
    @Qualifier("chartServiceJpaImpl")
    private ChartService chartService;

    @Autowired
    private com.thoughtworks.xstream.XStream xstream;
    @Autowired
    private com.thoughtworks.xstream.XStream jsonXstream;

    public ChartInstanceResource() {
        super();
        logger.debug("into constructor ChartInstanceEntity");
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
            xstream.processAnnotations(ChartInstanceM.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
            ChartInstanceM xsource = new ChartInstanceM();
            Object xtarget = xstream.fromXML(in);
            if (xtarget != null) {
                xsource = (ChartInstanceM) xtarget;
                if (xsource != null) {
                    ChartInstanceEntity domain = new ChartInstanceEntity();
                    BeanUtils.copyProperties(xsource, domain);
                    CommentM commentM= xsource.getComment();
                    ServiceM serviceM= xsource.getService();
                    if(commentM!=null){
                        CommentEntity commentEntity=new CommentEntity();
                        BeanUtils.copyProperties(commentM, commentEntity);
                  //      domain.setCommentByInstanceId(commentEntity);
                    }
                    if(serviceM!=null){
                        ServiceEntity serviceEntity=new ServiceEntity();
                        BeanUtils.copyProperties(serviceM, serviceEntity);
                    //    domain.setServiceByServiceId(serviceEntity);
                    }

                    if (xsource.getServiceName() != null
                            && xsource.getServiceName().length() != 0) {
                        String serviceName = xsource.getServiceName();
                        if (serviceName.equals(ServiceConstant.CHART_INSTANCE_FIND_BY_ID)) {
                        	domain = chartService.findChartInstanceEntityById(xsource.getInstanceId());
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();

                            if (domain != null) {
                                List<ChartInstanceM> models = new ArrayList<ChartInstanceM>(1);
                                java.util.ArrayList<ChartInstanceEntity> domains =new ArrayList<ChartInstanceEntity>(1);
                                domains.add(domain);
                                // get Model List
                                models=getChartInstanceModels(domains);
                                imakeMessage.setResultListObj(models);
                            }
                            return getRepresentation(entity, imakeMessage, xstream);
                        } else if (serviceName.equals(ServiceConstant.CHART_INSTANCE_SAVE)) {
                            //java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
                            // domain.setCreatedDate(now);
                            //domain.setUpdatedDate(now);
                            int updateRecord = chartService.saveChartInstanceEntity(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.CHART_INSTANCE_UPDATE)) {
                            //java.sql.Timestamp updatedDate = new java.sql.Timestamp(new Date().getTime());
                            //domain.setUpdatedDate(updatedDate);
                            int updateRecord = chartService.updateChartInstanceEntity(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.CHART_INSTANCE_ITEMS_DELETE)) {

                        } else if (serviceName.equals(ServiceConstant.CHART_INSTANCE_DELETE)) {
                            int updateRecord = 0;
                            try {
                                chartService.deleteChartInstanceEntity(domain);
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
                        } else if (serviceName.equals(ServiceConstant.CHART_INSTANCE_SEARCH)) {
                            /*
                            Paging page = xsource.getPaging();
                             @SuppressWarnings("rawtypes")
                            List result = (List) chartService.searchChartInstanceEntity(domain, page, xsource.getKeySearch());
                            if (result != null && result.size() == 2) {
                                java.util.ArrayList<ChartInstanceEntity> domains = (java.util.ArrayList<ChartInstanceEntity>) result
                                        .get(0);
                                String domains_size = (String) result.get(1);
                                ImakeResultMessage imakeMessage = new ImakeResultMessage();

                                List<ChartInstanceM> models = new ArrayList<ChartInstanceM>();
                                if (domains_size != null && domains_size.length() != 0)
                                    imakeMessage.setMaxRow(domains_size);
                                if (domains != null && domains.size() > 0) {
                                    models = getChartInstanceModels(domains);
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

    private List<ChartInstanceM> getChartInstanceModels(
            java.util.ArrayList<ChartInstanceEntity> domains) {
        List<ChartInstanceM> models = new ArrayList<ChartInstanceM>(
                domains.size());
        for (ChartInstanceEntity domain : domains) {
            ChartInstanceM model = new ChartInstanceM();
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
    /*        if (domain.getServiceByServiceId() != null) {
                ServiceM serviceM = new ServiceM();
                BeanUtils.copyProperties(domain.getServiceByServiceId(), serviceM);
                serviceM.setPaging(null);
                model.setService(serviceM);
            }*/
            /* set CommentM
            if (domain.getCommentByInstanceId() != null) {
                CommentM cmmentM = new CommentM();
                BeanUtils.copyProperties(domain.getCommentByInstanceId(), cmmentM);
                cmmentM.setPaging(null);
                model.setComment(cmmentM);
            }*/
            model.setPaging(null);
            models.add(model);
        }
        return models;
    }

    private Representation returnUpdateRecord(Representation entity, ChartInstanceM model, int updateRecord) {
        ImakeResultMessage imakeMessage = new ImakeResultMessage();
        List<ChartInstanceM> xsources = new ArrayList<ChartInstanceM>(1);
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
