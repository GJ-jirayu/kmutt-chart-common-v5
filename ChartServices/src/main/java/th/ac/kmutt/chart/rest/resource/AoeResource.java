package th.ac.kmutt.chart.rest.resource;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.apache.log4j.Logger;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.Variant;
import org.restlet.resource.ResourceException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import th.ac.kmutt.chart.constant.ServiceConstant;
import th.ac.kmutt.chart.domain.ChartEntity;
import th.ac.kmutt.chart.domain.FilterEntity;
import th.ac.kmutt.chart.domain.FilterInstanceEntity;
import th.ac.kmutt.chart.model.FilterInstanceM;
import th.ac.kmutt.chart.service.ChartService;
import th.ac.kmutt.chart.xstream.common.Paging;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AoeResource extends BaseResource {
    //private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
    @Autowired
    @Qualifier("chartServiceJpaImpl")
    private ChartService chartService;

    @Autowired
    private com.thoughtworks.xstream.XStream xstream;
    @Autowired
    private com.thoughtworks.xstream.XStream jsonXstream;
    public AoeResource() {
        super();
        logger.debug("into constructor TitleResource");
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
            xstream.processAnnotations(th.ac.kmutt.chart.model.ChartM.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
            th.ac.kmutt.chart.model.ChartM xsource = new th.ac.kmutt.chart.model.ChartM();
            Object xtarget = xstream.fromXML(in);
            if (xtarget != null) {
                xsource = (th.ac.kmutt.chart.model.ChartM) xtarget;
                if (xsource != null) {
                    ChartEntity domain = new ChartEntity();
                    BeanUtils.copyProperties(xsource, domain);
                    if (xsource.getServiceName() != null
                            && xsource.getServiceName().length() != 0) {
                        String serviceName = xsource.getServiceName();

                            Paging page = xsource.getPaging();
                            @SuppressWarnings("rawtypes")
                            java.util.ArrayList<ChartEntity> domains = (java.util.ArrayList<ChartEntity>)chartService.listChart();
                        List<th.ac.kmutt.chart.model.ChartM> models = new ArrayList<th.ac.kmutt.chart.model.ChartM>();
                        models = getChartModels(domains);


                        JsonRepresentation representation_aoe = null;


                        jsonXstream
                                .processAnnotations(th.ac.kmutt.chart.model.ChartM.class);// or
                        // xstream.autodetectAnnotations(true);
                        // (Auto-detect
                        // Annotations)
                        jsonXstream.autodetectAnnotations(true);
                         jsonXstream.setMode(XStream.NO_REFERENCES);
                       // jsonXstream.addImplicitCollection(models.getClass(), "TitleM");;
                        Gson gson = new Gson();
                        String jsonStr= gson.toJson(models);
                        logger.info("json->" + jsonStr);
                        String json = jsonXstream.toXML(models);
                        logger.info("json->"+json);

                            representation_aoe = new JsonRepresentation(jsonStr);

                        return representation_aoe;
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

    private List<th.ac.kmutt.chart.model.ChartM> getChartModels(
            java.util.ArrayList<ChartEntity> domains) {
        List<th.ac.kmutt.chart.model.ChartM> models = new ArrayList<th.ac.kmutt.chart.model.ChartM>(
                domains.size());
        for (ChartEntity domain : domains) {
            th.ac.kmutt.chart.model.ChartM model = new th.ac.kmutt.chart.model.ChartM();
            BeanUtils.copyProperties(domain, model);
            model.setPaging(null);
            models.add(model);
        }
        return models;
    }
    @Override
    protected Representation get(Variant variant) throws ResourceException {
        // TODO Auto-generated method stub

        FilterInstanceM domain =new FilterInstanceM();
        domain.setFilterId(1);
        domain.setInstanceId("xxx");
        FilterInstanceEntity entity =new FilterInstanceEntity();
        //FilterEntity filterEntity =new FilterEntity();
        //filterEntity.setFilterId(1);
        //entity.setFilterByFilterId(filterEntity);
        BeanUtils.copyProperties(domain, entity);

       // chartService.saveFilterInstanceEntity(entity);

        FilterInstanceEntity entity_return =chartService.findFilterInstanceEntityById("xxx");
        logger.info(entity_return.getFilterByFilterId().getFilterId()+","
        +entity_return.getFilterByFilterId().getFilterName());
        logger.info(entity_return.getId().getFilterId());
        return null;
    }
/*
    public com.thoughtworks.xstream.XStream getXstream() {
        return xstream;
    }

    public void setXstream(com.thoughtworks.xstream.XStream xstream) {
        this.xstream = xstream;
    }

    public XStream getJsonXstream() {
        return jsonXstream;
    }

    public void setJsonXstream(XStream jsonXstream) {
        this.jsonXstream = jsonXstream;
    }
    */
}
