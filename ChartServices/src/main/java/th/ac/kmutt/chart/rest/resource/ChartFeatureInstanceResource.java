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
import th.ac.kmutt.chart.domain.ChartEntity;
import th.ac.kmutt.chart.domain.ChartFeatureInstanceEntity;
import th.ac.kmutt.chart.domain.FeatureEntity;
import th.ac.kmutt.chart.model.ChartFeatureInstanceM;
import th.ac.kmutt.chart.model.ChartFeatureM;
import th.ac.kmutt.chart.model.ChartM;
import th.ac.kmutt.chart.model.FeatureM;
import th.ac.kmutt.chart.service.ChartService;
import th.ac.kmutt.chart.xstream.common.ImakeResultMessage;
import th.ac.kmutt.chart.xstream.common.Paging;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by imake on 20/10/2015.
 */
public class ChartFeatureInstanceResource  extends BaseResource {
   // private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
    @Autowired
    @Qualifier("chartServiceJpaImpl")
    private ChartService chartService;

    @Autowired
    private com.thoughtworks.xstream.XStream xstream;
    @Autowired
    private com.thoughtworks.xstream.XStream jsonXstream;

    public ChartFeatureInstanceResource() {
        super();
        logger.debug("into constructor ChartFeatureInstanceEntity");
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
            xstream.processAnnotations(ChartFeatureInstanceM.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
            ChartFeatureInstanceM xsource = new ChartFeatureInstanceM();
            Object xtarget = xstream.fromXML(in);
            if (xtarget != null) {
                xsource = (ChartFeatureInstanceM) xtarget;
                if (xsource != null) {
                    ChartFeatureInstanceEntity domain = new ChartFeatureInstanceEntity();
                    BeanUtils.copyProperties(xsource, domain);
                    /*
                    ChartFeatureInstanceEntityPK pk = new ChartFeatureInstanceEntityPK();
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
                        if (serviceName.equals(ServiceConstant.CHART_FEATURE_INSTANCE_FIND_BY_ID)) {
                            domain = chartService.findChartFeatureInstanceEntityById(xsource.getInstanceId());
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            if (domain != null) {
                                List<ChartFeatureInstanceM> models = new ArrayList<ChartFeatureInstanceM>(1);

                                java.util.ArrayList<ChartFeatureInstanceEntity> domains =new ArrayList<ChartFeatureInstanceEntity>(1);
                                domains.add(domain);
                                // get Model List
                                models=getChartFeatureInstanceModels(domains);

                                imakeMessage.setResultListObj(models);
                            }
                            return getRepresentation(entity, imakeMessage, xstream);
                        } else if (serviceName.equals(ServiceConstant.CHART_FEATURE_INSTANCE_SAVE)) {
                            //java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
                            // domain.setCreatedDate(now);
                            //domain.setUpdatedDate(now);
                            int updateRecord = chartService.saveChartFeatureInstanceEntity(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.CHART_FEATURE_INSTANCE_UPDATE)) {
                            //java.sql.Timestamp updatedDate = new java.sql.Timestamp(new Date().getTime());
                            //domain.setUpdatedDate(updatedDate);
                            int updateRecord = chartService.updateChartFeatureInstanceEntity(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.CHART_FEATURE_INSTANCE_ITEMS_DELETE)) {

                        } else if (serviceName.equals(ServiceConstant.CHART_FEATURE_INSTANCE_DELETE)) {
                            int updateRecord = 0;
                            try {
                                chartService.deleteChartFeatureInstanceEntity(domain);
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
                        } else if (serviceName.equals(ServiceConstant.CHART_FEATURE_INSTANCE_SEARCH)) {
                            /*
                            Paging page = xsource.getPaging();
                             @SuppressWarnings("rawtypes")
                            List result = (List) chartService.searchChartFeatureInstanceEntity(domain, page, xsource.getKeySearch());
                            if (result != null && result.size() == 2) {
                                java.util.ArrayList<ChartFeatureInstanceEntity> domains = (java.util.ArrayList<ChartFeatureInstanceEntity>) result
                                        .get(0);
                                String domains_size = (String) result.get(1);
                                ImakeResultMessage imakeMessage = new ImakeResultMessage();

                                List<ChartFeatureInstanceM> models = new ArrayList<ChartFeatureInstanceM>();
                                if (domains_size != null && domains_size.length() != 0)
                                    imakeMessage.setMaxRow(domains_size);
                                if (domains != null && domains.size() > 0) {
                                    models = getChartFeatureInstanceModels(domains);
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

    private List<ChartFeatureInstanceM> getChartFeatureInstanceModels(
            java.util.ArrayList<ChartFeatureInstanceEntity> domains) {
        List<ChartFeatureInstanceM> models = new ArrayList<ChartFeatureInstanceM>(
                domains.size());
        for (ChartFeatureInstanceEntity domain : domains) {
            ChartFeatureInstanceM model = new ChartFeatureInstanceM();
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
            // set FeatureM
            if (domain.getFeatureByFeatureId() != null) {
                FeatureM featureM = new FeatureM();
                BeanUtils.copyProperties(domain.getFeatureByFeatureId(), featureM);
                model.setFeature(featureM);
            }
            model.setPaging(null);
            models.add(model);
        }
        return models;
    }

    private Representation returnUpdateRecord(Representation entity, ChartFeatureInstanceM model, int updateRecord) {
        ImakeResultMessage imakeMessage = new ImakeResultMessage();
        List<ChartFeatureInstanceM> xsources = new ArrayList<ChartFeatureInstanceM>(1);
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
