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
import th.ac.kmutt.chart.domain.ChartFeatureEntity;
import th.ac.kmutt.chart.domain.ChartFeatureInstanceEntity;
import th.ac.kmutt.chart.model.ChartFeatureM;
import th.ac.kmutt.chart.model.ChartFeatureM;
import th.ac.kmutt.chart.model.ChartM;
import th.ac.kmutt.chart.model.FeatureM;
import th.ac.kmutt.chart.service.ChartService;
import th.ac.kmutt.chart.xstream.common.ImakeResultMessage;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by imake on 20/10/2015.
 */
public class ChartFeatureResource  extends BaseResource {
  //  private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
    @Autowired
    @Qualifier("chartServiceJpaImpl")
    private ChartService chartService;

    @Autowired
    private com.thoughtworks.xstream.XStream xstream;
    @Autowired
    private com.thoughtworks.xstream.XStream jsonXstream;

    public ChartFeatureResource() {
        super();
        logger.debug("into constructor ChartFeatureEntity");
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
            xstream.processAnnotations(ChartFeatureM.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
            ChartFeatureM xsource = new ChartFeatureM();
            Object xtarget = xstream.fromXML(in);
            if (xtarget != null) {
                xsource = (ChartFeatureM) xtarget;
                if (xsource != null) {
                    ChartFeatureEntity domain = new ChartFeatureEntity();
                    BeanUtils.copyProperties(xsource, domain);
                    /*
                    ChartFeatureEntityPK pk = new ChartFeatureEntityPK();
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
                        if (serviceName.equals(ServiceConstant.CHART_FEATURE_FIND_BY_ID)) {
                            domain = chartService.findChartFeatureEntityById(xsource.getChartId());
                            ImakeResultMessage imakeMessage = new ImakeResultMessage();
                            if (domain != null) {
                                List<ChartFeatureM> models = new ArrayList<ChartFeatureM>(1);

                                java.util.ArrayList<ChartFeatureEntity> domains =new ArrayList<ChartFeatureEntity>(1);
                                domains.add(domain);
                                // get Model List
                                models=getChartFeatureModels(domains);

                                imakeMessage.setResultListObj(models);
                            }
                            return getRepresentation(entity, imakeMessage, xstream);
                        } else if (serviceName.equals(ServiceConstant.CHART_FEATURE_SAVE)) {
                            //java.sql.Timestamp now = new java.sql.Timestamp(new Date().getTime());
                            // domain.setCreatedDate(now);
                            //domain.setUpdatedDate(now);
                            int updateRecord = chartService.saveChartFeatureEntity(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.CHART_FEATURE_UPDATE)) {
                            //java.sql.Timestamp updatedDate = new java.sql.Timestamp(new Date().getTime());
                            //domain.setUpdatedDate(updatedDate);
                            int updateRecord = chartService.updateChartFeatureEntity(domain);
                            return returnUpdateRecord(entity, xsource, updateRecord);
                        } else if (serviceName.equals(ServiceConstant.CHART_FEATURE_ITEMS_DELETE)) {

                        } else if (serviceName.equals(ServiceConstant.CHART_FEATURE_DELETE)) {
                            int updateRecord = 0;
                            try {
                                chartService.deleteChartFeatureEntity(domain);
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
                        } else if (serviceName.equals(ServiceConstant.CHART_FEATURE_SEARCH)) {
                            /*
                            Paging page = xsource.getPaging();
                             @SuppressWarnings("rawtypes")
                            List result = (List) chartService.searchChartFeatureEntity(domain, page, xsource.getKeySearch());
                            if (result != null && result.size() == 2) {
                                java.util.ArrayList<ChartFeatureEntity> domains = (java.util.ArrayList<ChartFeatureEntity>) result
                                        .get(0);
                                String domains_size = (String) result.get(1);
                                ImakeResultMessage imakeMessage = new ImakeResultMessage();

                                List<ChartFeatureM> models = new ArrayList<ChartFeatureM>();
                                if (domains_size != null && domains_size.length() != 0)
                                    imakeMessage.setMaxRow(domains_size);
                                if (domains != null && domains.size() > 0) {
                                    models = getChartFeatureModels(domains);
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

    private List<ChartFeatureM> getChartFeatureModels(
            java.util.ArrayList<ChartFeatureEntity> domains) {
        List<ChartFeatureM> models = new ArrayList<ChartFeatureM>(
                domains.size());
        for (ChartFeatureEntity domain : domains) {
            ChartFeatureM model = new ChartFeatureM();
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

            model.setPaging(null);
            models.add(model);
        }
        return models;
    }

    private Representation returnUpdateRecord(Representation entity, ChartFeatureM model, int updateRecord) {
        ImakeResultMessage imakeMessage = new ImakeResultMessage();
        List<ChartFeatureM> xsources = new ArrayList<ChartFeatureM>(1);
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
