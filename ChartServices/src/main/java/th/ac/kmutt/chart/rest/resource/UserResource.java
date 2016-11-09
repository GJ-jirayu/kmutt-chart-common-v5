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

public class UserResource  extends BaseResource {
    //private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
    @Autowired
    @Qualifier("chartServiceJpaImpl")
    private ChartService chartService;

    @Autowired
    private com.thoughtworks.xstream.XStream xstream;
    @Autowired
    private com.thoughtworks.xstream.XStream jsonXstream;

    public UserResource() {
        super();
        logger.debug("into constructor UserM service");
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
        logger.debug("into Post UserM ");
        InputStream in = null;
        try {
            in = entity.getStream();
            xstream.processAnnotations(UserM.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
            UserM xsource = new UserM();
            Object xtarget = xstream.fromXML(in);
            if (xtarget != null) {
                xsource = (UserM) xtarget;
                if (xsource != null) {
                	// prepare return object
                	ImakeResultMessage resMes = new ImakeResultMessage();
                	List<UserM> resultObject = new ArrayList<UserM>();
                	UserM fchart = new UserM();
                	BeanUtils.copyProperties(xsource, fchart);
                	// case service
                    if (xsource.getServiceName() != null  && xsource.getServiceName().length() != 0) {
                        String serviceName = xsource.getServiceName();
                        if (serviceName.equals(ServiceConstant.USER_LIST)) {
                        //	fchart = chartService.buildChartObject(xsource);
                   //     	resultObject.add(fchart);
                        	resMes.setResultListObj(resultObject);
                            return getRepresentation(entity, resMes, xstream);	
                        } 
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
