package th.ac.kmutt.chart.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import th.ac.kmutt.chart.constant.DefaultConstant;
import th.ac.kmutt.chart.constant.ServiceConstant;
import th.ac.kmutt.chart.domain.DatasourceConnectionEntity;
import th.ac.kmutt.chart.domain.FilterEntity;
import th.ac.kmutt.chart.domain.ServiceEntity;
import th.ac.kmutt.chart.model.FilterM;
import th.ac.kmutt.chart.model.FilterValueM;
import th.ac.kmutt.chart.rest.application.SystemSetting;


@Repository("datasourceRepository")
public class DatasourceRepository {
    private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
    
    @Autowired
    @PersistenceContext(unitName = "HibernatePersistenceUnit")
    private EntityManager entityManager;
    
    public EntityManager connectDS(Integer dsid){
   // 	try{
    		if(!SystemSetting.existConnects()){
    			logger.info("loading connection. . .");	
    			System.out.println("loading connection. . .");
    			loadEm();
    		}
    		EntityManager em = SystemSetting.getConnects(dsid.toString());
    		return em;
 //   	}catch(Exception e){
  //  		logger.error(" exception datasourceRepo.connectDS  not found dsConfig at [" +dsid+ "] reason="+e.getCause());
   // 		return null;
   // 	}
    }
    public void loadEm(){
        	try {
    			List<DatasourceConnectionEntity> dsConfigList = listConnection();
    			if(dsConfigList!=null){
    				for(DatasourceConnectionEntity dsConfig : dsConfigList){
    					try{
    						createEm(dsConfig);
    						logger.info(" success loaded  "+dsConfig.getConnId());
    					}catch(Exception e){
    						logger.error(" fail to load dataSource at con:"+dsConfig.getConnId());
    					}
    				}
    			}
    		} catch (Exception e) {
    			logger.error(" exception at load entity manager dwh reason="+e.getStackTrace());
    		}// end
    }

	public List<DatasourceConnectionEntity> listConnection() throws Exception{
		String sql = "select s from DatasourceConnectionEntity s";
		Query q = entityManager.createQuery(sql, DatasourceConnectionEntity.class);
		return q.getResultList();
	}
    public void createEm(DatasourceConnectionEntity dsConfig){
    	String conName = dsConfig.getConnId().toString();
    	Map<String, String> properties = new HashMap<String, String>();
    	properties.put("hibernate.connection.driver_class",dsConfig.getDriverClass());
    	properties.put("hibernate.connection.url",dsConfig.getConnString());
    	properties.put("hibernate.connection.username", dsConfig.getUsername());
    	properties.put("hibernate.connection.password", dsConfig.getPassword());
    	properties.put("hibernate.dialect", dsConfig.getDialect());
    	properties.put("hibernate.show-sql", "false");
    	
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("HibernatePersistenceUnitDwh",properties);
    	EntityManager em = emf.createEntityManager();
    	System.out.println(" create persistence manager success at con:"+conName);
    	logger.info(" create persistence manager success at con:"+conName);
    	SystemSetting.addConnects(conName, em);
    	System.out.println(" store persistence manager success at con:"+conName);
    	logger.info(" store persistence manager success at con:"+conName);
    }
    
    @SuppressWarnings("unchecked")
	public List<Object[]> fetchChartResultSet(DatasourceConnectionEntity dsCon,ServiceEntity seSetting,List<FilterM> filters) throws Exception{
		
		List<Object[]>  results  = new ArrayList<Object[]>();
		try{
			String sql = seSetting.getSqlString();
			EntityManager em = connectDS(seSetting.getConnId());
			Query query = em.createNativeQuery(sql);
			for( FilterM filter : filters ){
				Object value = transformSelectedValueToObject(filter.getValueType(),filter.getSelectedValue());
			//	System.out.println("fetch Chart Param:"+filter.getFilterName()+":"+filter.getSelectedValue());
				if(  sql.contains(":"+filter.getFilterName()+" ") || sql.contains(":"+filter.getFilterName()+"\r\n") || sql.contains(":"+filter.getFilterName()+")")   ){ // check param syntax in sqlQuery		
					query.setParameter(filter.getFilterName(),value);
				}
			}
			results = query.getResultList();
		}catch(Exception ex){
			logger.info("Exception => Chart Query ResultSet Exception at  Service["+seSetting.getServiceId()+":"+ seSetting.getServiceName()+"] reason="+ex.getMessage()  );
			ex.printStackTrace();
		}
		return results;
	}
	private Object transformSelectedValueToObject(String type,String valueString){
		if(valueString!=null && type!=null){
		
			if( type.equals(DefaultConstant.filterTypeList.get(0).toString() ) ){
				//Manual Input
				return valueString;
			}else if( type.equals(DefaultConstant.filterTypeList.get(1).toString()) ){
				// select
				return valueString;
			}else if ( type.equals(DefaultConstant.filterTypeList.get(2).toString()) ){
				// multi select
					String[] vs = valueString.split(",");
					List<String>  arrayList = Arrays.asList(vs);
					return arrayList;
			}else{
				return valueString;
			}
		}else{
			return valueString;
		}
	}
	public String transformSelectedValue(String type,String valueString){
		if(valueString!=null && type!=null){
		
			if( type.equals(DefaultConstant.filterTypeList.get(0).toString() ) ){
				//Manual Input
			}else if( type.equals(DefaultConstant.filterTypeList.get(1).toString()) ){
				// select
			}else if ( type.equals(DefaultConstant.filterTypeList.get(2).toString()) ){
				// multi select
					String[] vs = valueString.split(",");
					valueString = StringUtils.join(vs,",");
			}
		
		}else{
		}
		return valueString;
	}
	//public List<FilterValueM> fetchFilterValueCascade(Integer filterId,List<FilterM> filters){
	public List<FilterValueM> fetchFilterValueCascade(FilterEntity fe,List<FilterM> filters) {
	//		try{
			// normal filter
		 	EntityManager em = connectDS(fe.getConnId()); 
			Query query = em.createNativeQuery(fe.getSqlQuery());
			for( FilterM filter : filters ){
				
				Object value = transformSelectedValueToObject(filter.getValueType(),filter.getSelectedValue());	
				if(  fe.getSqlQuery().contains(":"+filter.getFilterName()) && !filter.getFilterId().equals(fe.getFilterId()) ){ 
					query.setParameter(filter.getFilterName(),value);
				}
			}
			List<Object[]> results = query.getResultList();
			List<FilterValueM> fvs = new ArrayList<FilterValueM>();
			
			for(Object[] result : results){
				fvs.add(buildFilterItems(result));
			}
			return fvs;
	//	}catch(Exception ex){
	//		System.out.println("exception: filterValue cascade error cause of filter id="+fe.getFilterId()+ "  cause="+ex);
	//		return  new ArrayList<FilterValueM>();
	//	}
	}

	public FilterValueM buildFilterItems(Object[] rf){
		FilterValueM fv = new FilterValueM();
		if(rf.length==1){
			// Can't be. Always return List<Object[]>
			//fv.setKeyMapping((String)rf[0]);
			//fv.setValueMapping((String)rf[0]);
		}else if(rf.length==2){
			fv.setKeyMapping((String)rf[0].toString());   // edit by mike 22/03/2559
			fv.setValueMapping((String)rf[1].toString());
		}else{
		}
		return fv;
	}
}