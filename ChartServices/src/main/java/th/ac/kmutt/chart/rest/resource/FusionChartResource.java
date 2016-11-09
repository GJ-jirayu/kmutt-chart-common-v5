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
import th.ac.kmutt.chart.domain.ChartInstanceEntity;
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
public class FusionChartResource  extends BaseResource {
    //private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
    @Autowired
    @Qualifier("chartServiceJpaImpl")
    private ChartService chartService;

    @Autowired
    private com.thoughtworks.xstream.XStream xstream;
    @Autowired
    private com.thoughtworks.xstream.XStream jsonXstream;

    public FusionChartResource() {
        super();
        logger.debug("into constructor FusionChart service");
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
        logger.debug("into Post FusionChart ");
        InputStream in = null;
        try {
            in = entity.getStream();
            xstream.processAnnotations(FusionChartM.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
            FusionChartM xsource = new FusionChartM();
            Object xtarget = xstream.fromXML(in);
            if (xtarget != null) {
                xsource = (FusionChartM) xtarget;
                if (xsource != null) {
                	// prepare return object
                	ImakeResultMessage resMes = new ImakeResultMessage();
                	List<FusionChartM> resultObject = new ArrayList<FusionChartM>();
                	FusionChartM fchart = new FusionChartM();
                	BeanUtils.copyProperties(xsource, fchart);
                	// case service
                    if (xsource.getServiceName() != null  && xsource.getServiceName().length() != 0) {
                        String serviceName = xsource.getServiceName();
                        if (serviceName.equals(ServiceConstant.FUSION_CHART_OBJECT)) {
                        	fchart = chartService.buildChartObject(xsource);
                        	resultObject.add(fchart);
                        	resMes.setResultListObj(resultObject);
                            return getRepresentation(entity, resMes, xstream);	
                        } // end service FUSION_CHART.FUSION_CHART_OBJECT
                    } else { // serviceName = null
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

    @Override
    protected Representation get(Variant variant) throws ResourceException {
        // TODO Auto-generated method stub
        return null;
    }

}
