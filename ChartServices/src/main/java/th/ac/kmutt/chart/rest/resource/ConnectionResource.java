package th.ac.kmutt.chart.rest.resource;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.restlet.representation.Representation;
import org.restlet.representation.Variant;
import org.restlet.resource.ResourceException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import th.ac.kmutt.chart.constant.ServiceConstant;
import th.ac.kmutt.chart.domain.ChartInstanceEntity;
import th.ac.kmutt.chart.domain.DatasourceConnectionEntity;
import th.ac.kmutt.chart.model.*;
import th.ac.kmutt.chart.service.ChartService;
import th.ac.kmutt.chart.xstream.common.ImakeResultMessage;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
@Transactional
public class ConnectionResource  extends BaseResource {
    //private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
    @Autowired
    @Qualifier("chartServiceJpaImpl")
    private ChartService chartService;

    @Autowired
    private com.thoughtworks.xstream.XStream xstream;
    @Autowired
    private com.thoughtworks.xstream.XStream jsonXstream;

    public ConnectionResource() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void doInit() throws ResourceException {
        // TODO Auto-generated method stub
        super.doInit();
    }

    @Override
    protected Representation post(Representation entity, Variant variant)
            throws ResourceException {
        InputStream in = null;
        try {
            in = entity.getStream();
            xstream.processAnnotations(ConnectionM.class);// or xstream.autodetectAnnotations(true); (Auto-detect  Annotations)
            ConnectionM xsource = new ConnectionM();
            Object xtarget = xstream.fromXML(in);
            if (xtarget != null) {
                xsource = (ConnectionM) xtarget;
                if (xsource != null) {
                	// prepare return object
                	ImakeResultMessage resMes = new ImakeResultMessage();
                	ConnectionM fchart = new ConnectionM();
                	BeanUtils.copyProperties(xsource, fchart);
                	// case service
                    if (xsource.getServiceName() != null  && xsource.getServiceName().length() != 0) {
                        String serviceName = xsource.getServiceName();
                        if (serviceName.equals(ServiceConstant.CONNECTION_LIST)) {
                        	try{
                        		List<DatasourceConnectionEntity> dsAll = chartService.listConnection();
                        		resMes.setResultListObj(transform(dsAll));
                        	}catch(Exception e){
                        		resMes.setResultListObj( new ArrayList<ConnectionM>());
                        	}
                            return getRepresentation(entity, resMes, xstream);	
                        }else if (serviceName.equals(ServiceConstant.CONNECTION_NEW)) {
                        	try{
                        		resMes.setReturnId( 	String.valueOf( chartService.newConnection(packEntity(xsource)) ) );
                        	}catch(Exception e){
                        		resMes.setReturnId(null);
                        		System.out.println(e.getMessage());
                        	}
                            return getRepresentation(entity, resMes, xstream);
                        }else if (serviceName.equals(ServiceConstant.CONNECTION_UPDATE)) {
                        	try{
                        		resMes.setReturnId( 	String.valueOf( chartService.updateConnection(packEntity(xsource)) ) );
                        	}catch(Exception e){
                        		System.out.println(e.getMessage());
                        		resMes.setReturnId(null);
                        	}
                            return getRepresentation(entity, resMes, xstream);
                        }else if (serviceName.equals(ServiceConstant.CONNECTION_DELETE)) {
                        	try{
                        		resMes.setReturnId( 	String.valueOf( chartService.deleteConnection(xsource.getConnId()) ) );
                        	}catch(Exception e){
                        		resMes.setReturnId(null);
                        	}
                            return getRepresentation(entity, resMes, xstream);
                        }
                        else if (serviceName.equals(ServiceConstant.CONNECTION_FIND_BY_ID)) {
                        	try{
                        		List<ConnectionM> cm = new ArrayList<ConnectionM>();
                        		ConnectionM model = packModel( chartService.findConnectionById(xsource.getConnId()) );
                        		cm.add(model);
                        		resMes.setResultListObj(cm);
                        	}catch(Exception e){
                        		resMes.setResultListObj(null);
                        	}
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
    
    private DatasourceConnectionEntity packEntity(ConnectionM model){
    	DatasourceConnectionEntity ent = new DatasourceConnectionEntity();
    	BeanUtils.copyProperties(model,ent);
    	return ent;
    }
    private ConnectionM packModel(DatasourceConnectionEntity ent){
    	ConnectionM model = new ConnectionM();
    	BeanUtils.copyProperties(ent,model);
    	return model;
    }
    private List<ConnectionM> transform(List<DatasourceConnectionEntity> enList){
    	 List<ConnectionM> mlist = new ArrayList<ConnectionM>();
    	for(DatasourceConnectionEntity en : enList){
    		ConnectionM m = new ConnectionM();
    		BeanUtils.copyProperties(en,m);
    		mlist.add(m);
    	}
		return mlist;
    }

}
