package th.ac.kmutt.chart.rest.resource;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.restlet.data.Method;
import org.restlet.data.Status;
import org.restlet.engine.resource.MethodAnnotationInfo;
import org.restlet.representation.Representation;
import org.restlet.representation.Variant;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import th.ac.kmutt.chart.model.SystemM;
import th.ac.kmutt.chart.rest.application.SystemSetting;
import th.ac.kmutt.chart.service.impl.DatasourceService;
import th.ac.kmutt.chart.xstream.common.ImakeResultMessage;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

public class SystemResource  extends BaseResource {
    //private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
    @Autowired
    @Qualifier("datasourceService")
    private DatasourceService dataService;

    @Autowired
    private com.thoughtworks.xstream.XStream xstream;
    @Autowired
    private com.thoughtworks.xstream.XStream jsonXstream;

    public SystemResource() {
        super();
        logger.debug("SystemResource constructor");
    }

    @Override
    protected void doInit() throws ResourceException {
        super.doInit();
        logger.debug("SystemResource  doInit");
    }

    @Override
    protected Representation post(Representation entity, Variant variant)
            throws ResourceException {
        logger.debug("SystemResource  Post  ");
        InputStream in = null;
        try {
            in = entity.getStream();
            ImakeResultMessage resMes = new ImakeResultMessage();
        	
            xstream.processAnnotations(SystemM.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
          //  xstream.autodetectAnnotations(true);
            Object xtarget = xstream.fromXML(in);
        	SystemM xsource = null;
            if (xtarget != null ) {
                xsource = (SystemM) xtarget;
                if(xsource.getServiceName().equals(SystemSetting.serviceNewConns)){
                	dataService.createEm();
                	return getRepresentation(entity,resMes,xstream);
                }
                else if(xsource.getServiceName().equals(SystemSetting.serviceStatConns)){
                	String msg = dataService.statEm();
                	System.out.println(" stat: "+msg);
                	return getRepresentation(entity,resMes,xstream);
                }
                else if(xsource.getServiceName().equals(SystemSetting.serviceDeleteConns)){
                	dataService.deleteEm(xsource.getConName());
                	System.out.println(" del success");
                	return getRepresentation(entity,resMes,xstream);
                }
                else if(xsource.getServiceName().equals(SystemSetting.serviceGetConns)){
                	
                	// implement here
                	
                	return getRepresentation(entity,resMes,xstream);
                }
                else if(xsource.getServiceName().equals(SystemSetting.serviceLoadConns)){
                	dataService.loadEm();
                	// implement here
                	return getRepresentation(entity,resMes,xstream);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }

        return null;
    }
}
