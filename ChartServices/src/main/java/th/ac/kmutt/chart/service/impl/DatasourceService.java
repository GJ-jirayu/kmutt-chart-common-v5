package th.ac.kmutt.chart.service.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import th.ac.kmutt.chart.constant.ServiceConstant;
import th.ac.kmutt.chart.domain.DatasourceConnectionEntity;
import th.ac.kmutt.chart.repository.ChartRepository;
import th.ac.kmutt.chart.repository.DatasourceRepository;
import th.ac.kmutt.chart.rest.application.SystemSetting;


@Service("datasourceService")
public class DatasourceService {

    @Autowired
    @Qualifier("chartRepository")
    private ChartRepository chartRepository;
	
    @Autowired
    @Qualifier("datasourceRepository")
    private DatasourceRepository datasourceRepository;

    /*
     * Service Provider 
     * limit only local access
     */
    private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);

    public void createEm(){
    }
    
    public void deleteEm(String name){
    	try{
	    	EntityManager em = SystemSetting.getConnects(name);
	    	if(em!=null){
	    		em.clear();
	    		em.close();
	    		SystemSetting.deleteConnects(name);
	    	}//  end check exist
    	}catch(Exception e){
    		// null point ignore
    	}
    }
    public String statEm(){
    	return SystemSetting.statConnects();
    }
    public void loadEm(){
    	try {
			List<DatasourceConnectionEntity> dsConfigList = chartRepository.listConnection();
			if(dsConfigList!=null){
				for(DatasourceConnectionEntity dsConfig : dsConfigList){
					try{
						datasourceRepository.createEm(dsConfig);
						logger.error("loaded "+dsConfig.getConnId());
					}catch(Exception e){
						logger.error(" fail to load dataSource at con:"+dsConfig.getConnId());
					}
				}
			}
		} catch (Exception e) {
			logger.error(" exception at load entity manager dwh reason="+e.getStackTrace());
		}// end
    }
}
