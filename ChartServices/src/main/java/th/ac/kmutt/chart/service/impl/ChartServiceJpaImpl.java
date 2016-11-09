package th.ac.kmutt.chart.service.impl;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import th.ac.kmutt.chart.builder.*;
import th.ac.kmutt.chart.constant.ServiceConstant;
import th.ac.kmutt.chart.domain.*;
import th.ac.kmutt.chart.model.*;
import th.ac.kmutt.chart.repository.ChartRepository;
import th.ac.kmutt.chart.repository.DatasourceRepository;
import th.ac.kmutt.chart.service.ChartService;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Service("chartServiceJpaImpl")
public class ChartServiceJpaImpl implements ChartService {

    @Autowired
    @Qualifier("chartRepository")
    private ChartRepository chartRepository;

    @Autowired
    @Qualifier("datasourceRepository")
    private DatasourceRepository datasourceRepository;

    
    private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
    
    
    public List aew()throws DataAccessException{
        List<ChartEntity> list = chartRepository.aew();
        return list;
    }
    public List listChart() throws DataAccessException {
        List<ChartEntity> list = chartRepository.listChart();
        return list;
    }

    public Integer saveChartEntity(ChartEntity transientInstance) throws DataAccessException {
        return chartRepository.saveChartEntity(transientInstance);
    }

    public Integer updateChartEntity(ChartEntity transientInstance) throws DataAccessException {
        return chartRepository.updateChartEntity(transientInstance);
    }

    public Integer deleteChartEntity(ChartEntity persistentInstance) throws DataAccessException {
        return chartRepository.deleteChartEntity(persistentInstance);
    }

    public ChartEntity findChartEntityById(Integer chartId) throws DataAccessException {
        return chartRepository.findChartEntityById(chartId);
    }

    @Override
    public List listChartEntity(ChartM param) throws DataAccessException {
        return chartRepository.listChartEntity(param);
    }

    public Integer saveChartFeatureEntity(ChartFeatureEntity transientInstance) throws DataAccessException {
        return chartRepository.saveChartFeatureEntity(transientInstance);
    }

    public Integer updateChartFeatureEntity(ChartFeatureEntity transientInstance) throws DataAccessException {
        return chartRepository.updateChartFeatureEntity(transientInstance);
    }

    public Integer deleteChartFeatureEntity(ChartFeatureEntity persistentInstance) throws DataAccessException {
        return chartRepository.deleteChartFeatureEntity(persistentInstance);
    }

    public ChartFeatureEntity findChartFeatureEntityById(Integer chartId) throws DataAccessException {
        return chartRepository.findChartFeatureEntityById(chartId);
    }

    public Integer saveChartFeatureInstanceEntity(ChartFeatureInstanceEntity transientInstance) throws DataAccessException {
        return chartRepository.saveChartFeatureInstanceEntity(transientInstance);
    }

    public Integer updateChartFeatureInstanceEntity(ChartFeatureInstanceEntity transientInstance) throws DataAccessException {
        return chartRepository.updateChartFeatureInstanceEntity(transientInstance);
    }

    public Integer deleteChartFeatureInstanceEntity(ChartFeatureInstanceEntity persistentInstance) throws DataAccessException {
        return chartRepository.deleteChartFeatureInstanceEntity(persistentInstance);
    }

    public ChartFeatureInstanceEntity findChartFeatureInstanceEntityById(String instanceId) throws DataAccessException {
        return chartRepository.findChartFeatureInstanceEntityById(instanceId);
    }

    public Integer saveChartFeatureMappingEntity(ChartFeatureMappingEntity transientInstance) throws DataAccessException {
        return chartRepository.saveChartFeatureMappingEntity(transientInstance);
    }

    public Integer updateChartFeatureMappingEntity(ChartFeatureMappingEntity transientInstance) throws DataAccessException {
        return chartRepository.updateChartFeatureMappingEntity(transientInstance);
    }

    public Integer deleteChartFeatureMappingEntity(ChartFeatureMappingEntity persistentInstance) throws DataAccessException {
        return chartRepository.deleteChartFeatureMappingEntity(persistentInstance);
    }

    public ChartFeatureMappingEntity findChartFeatureMappingEntityById(ChartFeatureMappingEntityPK id) throws DataAccessException {
        return chartRepository.findChartFeatureMappingEntityById(id);
    }

    public Integer saveChartFilterInstanceEntity(ChartFilterInstanceEntity transientInstance) throws DataAccessException {
        return chartRepository.saveChartFilterInstanceEntity(transientInstance);
    }

    public Integer updateChartFilterInstanceEntity(ChartFilterInstanceEntity transientInstance) throws DataAccessException {
        return chartRepository.updateChartFilterInstanceEntity(transientInstance);
    }

    public Integer deleteChartFilterInstanceEntity(ChartFilterInstanceEntity persistentInstance) throws DataAccessException {
        return chartRepository.deleteChartFilterInstanceEntity(persistentInstance);
    }

    public ChartFilterInstanceEntity findChartFilterInstanceEntityById(String instanceId) throws DataAccessException {
        return chartRepository.findChartFilterInstanceEntityById(instanceId);
    }

    public Integer saveChartInstanceEntity(ChartInstanceEntity transientInstance) throws DataAccessException {
        return chartRepository.saveChartInstanceEntity(transientInstance);
    }

    public Integer updateChartInstanceEntity(ChartInstanceEntity transientInstance) throws DataAccessException {
        return chartRepository.updateChartInstanceEntity(transientInstance);
    }

    public Integer deleteChartInstanceEntity(ChartInstanceEntity persistentInstance) throws DataAccessException {
        return chartRepository.deleteChartInstanceEntity(persistentInstance);
    }

    public ChartInstanceEntity findChartInstanceEntityById(String instanceId) throws DataAccessException {
        return chartRepository.findChartInstanceEntityById(instanceId);
    }

    @Override
    public List listChartFilterInstanceEntity(ChartFilterInstanceM param) throws DataAccessException {
        return chartRepository.listChartFilterInstanceEntity(param);
    }

    public Integer saveCommentEntity(CommentEntity transientInstance) throws DataAccessException {
        return chartRepository.saveCommentEntity(transientInstance);
    }

    public Integer updateCommentEntity(CommentEntity transientInstance) throws DataAccessException {
        return chartRepository.updateCommentEntity(transientInstance);
    }

    public Integer deleteCommentEntity(CommentEntity persistentInstance) throws DataAccessException {
        return chartRepository.deleteCommentEntity(persistentInstance);
    }

    public CommentEntity findCommentEntityById(String instanceId) throws DataAccessException {
        return chartRepository.findCommentEntityById(instanceId);
    }

    public Integer saveFeatureEntity(FeatureEntity transientInstance) throws DataAccessException {
        return chartRepository.saveFeatureEntity(transientInstance);
    }

    public Integer updateFeatureEntity(FeatureEntity transientInstance) throws DataAccessException {
        return chartRepository.updateFeatureEntity(transientInstance);
    }

    public Integer deleteFeatureEntity(FeatureEntity persistentInstance) throws DataAccessException {
        return chartRepository.deleteFeatureEntity(persistentInstance);
    }

    public FeatureEntity findFeatureEntityById(Integer featureId) throws DataAccessException {
        return chartRepository.findFeatureEntityById(featureId);
    }
    
    // filter section
    @Override
    public void saveFilter(FilterM filterModel) throws Exception {
    	FilterEntity entity = new FilterEntity();
    	BeanUtils.copyProperties(filterModel, entity);
    	Integer saveId  = saveFilterEntity(entity);
    	if(saveId == null){
    		throw new Exception(" Save filter exception ");
    	}
    	List<FilterM> paramFilter =   filterModel.getFilterList();
    	if(paramFilter!=null && paramFilter.size()>0){
        	for(FilterM filter : paramFilter){
        		FilterMappingEntity map = new FilterMappingEntity();
        		map.setFilterId(saveId);
        		map.setParamFilterId(filter.getFilterId());
        		chartRepository.saveFilterMapping(map);
        	}
    	}
    }
    @Override
    public void updateFilter(FilterM filterModel) throws Exception {
    	FilterEntity entity = new FilterEntity();
    	BeanUtils.copyProperties(filterModel, entity);
    	chartRepository.updateFilterEntity(entity);
    	chartRepository.deleteFilterMappingByFilterId(filterModel.getFilterId());
    	List<FilterM> paramFilter =   filterModel.getFilterList();
    	if(paramFilter!=null && paramFilter.size()>0){
        	for(FilterM filter : paramFilter){
        		FilterMappingEntity map = new FilterMappingEntity();
        		map.setFilterId(entity.getFilterId());
        		map.setParamFilterId(filter.getFilterId());
        		chartRepository.saveFilterMapping(map);
        	}
    	}
    }
    @Override
    public void deleteFilter(FilterM filterModel) throws Exception {
    	FilterEntity en = new FilterEntity();
    	en.setFilterId(filterModel.getFilterId());
    	chartRepository.deleteFilterEntity(en);
    	chartRepository.deleteFilterMappingByFilterId(filterModel.getFilterId());
    }
    public Integer saveFilterEntity(FilterEntity transientInstance) throws DataAccessException { 	
        return chartRepository.saveFilterEntity(transientInstance);
    }

    public Integer updateFilterEntity(FilterEntity transientInstance) throws DataAccessException {
        return chartRepository.updateFilterEntity(transientInstance);
    }

    public Integer deleteFilterEntity(FilterEntity persistentInstance) throws DataAccessException {
        return chartRepository.deleteFilterEntity(persistentInstance);
    }

    public FilterEntity findFilterEntityById(Integer filterId) throws DataAccessException {
        return chartRepository.findFilterEntityById(filterId);
    }
    
    public FilterEntity getFilterValueList(Integer filterId) throws DataAccessException {
        return chartRepository.getFilterValueList(filterId);
    }

    public Integer saveFilterInstanceEntity(FilterInstanceEntity transientInstance) throws DataAccessException {
        return chartRepository.saveFilterInstanceEntity(transientInstance);
    }

    public Integer updateFilterInstanceEntity(FilterInstanceEntity transientInstance) throws DataAccessException {
        return chartRepository.updateFilterInstanceEntity(transientInstance);
    }

    public Integer deleteFilterInstanceEntity(FilterInstanceEntity persistentInstance) throws DataAccessException {
        return chartRepository.deleteFilterInstanceEntity(persistentInstance);
    }

    public FilterInstanceEntity findFilterInstanceEntityById(String instanceId) throws DataAccessException {
        return chartRepository.findFilterInstanceEntityById(instanceId);
    }

    @Override
    public List listFilterInstanceEntity(FilterInstanceM param) throws DataAccessException {
        return chartRepository.listFilterInstanceEntity(param);
    }

    public Integer saveServiceChartMappingEntity(ServiceChartMappingEntity transientInstance) throws DataAccessException {
        return chartRepository.saveServiceChartMappingEntity(transientInstance);
    }

    public Integer updateServiceChartMappingEntity(ServiceChartMappingEntity transientInstance) throws DataAccessException {
        return chartRepository.updateServiceChartMappingEntity(transientInstance);
    }

    public Integer deleteServiceChartMappingEntity(ServiceChartMappingEntity persistentInstance) throws DataAccessException {
        return chartRepository.deleteServiceChartMappingEntity(persistentInstance);
    }

    public ServiceChartMappingEntity findServiceChartMappingEntityById(ServiceChartMappingEntityPK id) throws DataAccessException {
        return chartRepository.findServiceChartMappingEntityById(id);
    }

    public ServiceM findServiceByServiceId(Integer serviceId,String userId) throws Exception{
    	ServiceM ds = new ServiceM();
    	ServiceEntity se = chartRepository.findServiceEntityById(serviceId);
    	BeanUtils.copyProperties(se, ds);
    	ds.setDatasourceName(se.getServiceName());
    	List<FilterM> filters = getFilterOfService(serviceId,userId);
    	ds.setFilterList(filters);
    	return ds;
    }
    public Integer saveService(ServiceM service){
    	ServiceEntity se = new ServiceEntity();
    	BeanUtils.copyProperties(service, se);
    	se.setServiceName(service.getDatasourceName());
    	se.setServiceId(null);
    	Integer serviceId = chartRepository.saveServiceEntity(se);
    	if(serviceId>0){
    		//service
    		for(FilterM f : service.getFilterList()){
    			ServiceFilterMappingEntityPK pk = new ServiceFilterMappingEntityPK();
    			pk.setFilterId(f.getFilterId());
    			pk.setServiceId(serviceId);
    			ServiceFilterMappingEntity map = new ServiceFilterMappingEntity();
    			map.setId(pk);
    			 chartRepository.saveServiceFilterMappingEntity(map);
    		}
    		//chart
    		for(ChartM c : service.getChartList()){
    			ServiceChartMappingEntityPK pk = new ServiceChartMappingEntityPK();
    			pk.setChartId(c.getChartId());
    			pk.setServiceId(serviceId);
    			ServiceChartMappingEntity map = new ServiceChartMappingEntity();
    			map.setId(pk);
    			 chartRepository.saveServiceChartMappingEntity(map);
    		}
    		
    		//user
    		for(UserM u : service.getUserList()){
    			ServiceUserMappingEntityPK pk = new ServiceUserMappingEntityPK();
    			pk.setUserId(u.getUserId());
    			pk.setServiceId(serviceId);
    			ServiceUserMappingEntity map = new ServiceUserMappingEntity();
    			map.setId(pk);
    			 chartRepository.saveServiceUserMappingEntity(map);
    		}
    	}
		return 1;
    }
    public Integer updateService(ServiceM service){
    	try{
	       	ServiceEntity se = new ServiceEntity();
	    	BeanUtils.copyProperties(service, se);
	    	se.setServiceName(service.getDatasourceName());
	    	Integer serviceId = chartRepository.updateServiceEntity(se);
	    	if(serviceId>0){
	    		//clear and insert
	    		chartRepository.deleteServiceFilterByService(serviceId);
	    		for(FilterM f : service.getFilterList()){
	    			ServiceFilterMappingEntityPK pk = new ServiceFilterMappingEntityPK();
	    			pk.setFilterId(f.getFilterId());
	    			pk.setServiceId(serviceId);
	    			ServiceFilterMappingEntity map = new ServiceFilterMappingEntity();
	    			map.setId(pk);
	    			 chartRepository.saveServiceFilterMappingEntity(map);
	    		}
	    		
	    		// chart
	    		chartRepository.deleteServiceChartByService(serviceId);
	    		for(ChartM c : service.getChartList()){
	    			ServiceChartMappingEntityPK pk = new ServiceChartMappingEntityPK();
	    			pk.setChartId(c.getChartId());
	    			pk.setServiceId(serviceId);
	    			ServiceChartMappingEntity map = new ServiceChartMappingEntity();
	    			map.setId(pk);
	    			 chartRepository.saveServiceChartMappingEntity(map);
	    		}
	    		
	    		//user
	    		chartRepository.deleteServiceUserByService(serviceId);
	    		for(UserM u : service.getUserList()){
	    			ServiceUserMappingEntityPK pk = new ServiceUserMappingEntityPK();
	    			pk.setUserId(u.getUserId());
	    			pk.setServiceId(serviceId);
	    			ServiceUserMappingEntity map = new ServiceUserMappingEntity();
	    			map.setId(pk);
	    			 chartRepository.saveServiceUserMappingEntity(map);
	    		}
	    	}
	    	return 1;
    	}catch(Exception e){
    		logger.info("Exception: updateService of "+service.getServiceId()+":"+service.getServiceName());
    		return 0;
    	}
    }
    public Integer saveServiceEntity(ServiceEntity transientInstance) throws DataAccessException {
        return chartRepository.saveServiceEntity(transientInstance);
    }

    public Integer updateServiceEntity(ServiceEntity transientInstance) throws DataAccessException {
        return chartRepository.updateServiceEntity(transientInstance);
    }

    public Integer deleteServiceEntity(ServiceEntity persistentInstance) throws DataAccessException {
        return chartRepository.deleteServiceEntity(persistentInstance);
    }

    public ServiceEntity findServiceEntityById(Integer serviceId) throws DataAccessException {
        return chartRepository.findServiceEntityById(serviceId);
    }

    @Override
    public List listServiceEntity(ServiceM param) throws DataAccessException {
        return chartRepository.listServiceEntity(param);
    }
    
    @Override
    public List listServiceByChartId(ServiceM service) throws DataAccessException {
        return chartRepository.listServiceByChartId(service);
    }
    
    public Integer saveServiceFilterMappingEntity(ServiceFilterMappingEntity transientInstance) throws DataAccessException {
        return chartRepository.saveServiceFilterMappingEntity(transientInstance);
    }

    public Integer updateServiceFilterMappingEntity(ServiceFilterMappingEntity transientInstance) throws DataAccessException {
        return chartRepository.updateServiceFilterMappingEntity(transientInstance);
    }

    public Integer deleteServiceFilterMappingEntity(ServiceFilterMappingEntity persistentInstance) throws DataAccessException {
        return chartRepository.deleteServiceFilterMappingEntity(persistentInstance);
    }

    public ServiceFilterMappingEntity findServiceFilterMappingEntityById(ServiceFilterMappingEntityPK id) throws DataAccessException {
        return chartRepository.findServiceFilterMappingEntityById(id);
    }

    @Override
    public List listServiceFilterMappingEntity(ServiceFilterMappingM param) throws DataAccessException {
        return chartRepository.listServiceFilterMappingEntity(param);
    }

    @Override
    public List listFilterEntity(FilterM param) throws DataAccessException {
        return chartRepository.listFilterEntity(param);
    }
    
    
    /*INBOUND_OUTBOUND_STUDENT

    public List InternationalCompareAllStudent(InBoundOutBoundServiceM persistentInstance){
    	return chartRepository.InternationalCompareAllStudent(persistentInstance);
    }
    
    
    public List EmpInternationalCompareAllEmp(InBoundOutBoundServiceM persistentInstance){
    	return chartRepository.EmpInternationalCompareAllEmp(persistentInstance);
    }
    
    public List ProgramInternationalCompareAllProgram(InBoundOutBoundServiceM persistentInstance){
    	return chartRepository.ProgramInternationalCompareAllProgram(persistentInstance);
    }
    
    public List InternationalCompareAllStudentProgramInter(InBoundOutBoundServiceM persistentInstance){
    	return chartRepository.InternationalCompareAllStudentProgramInter(persistentInstance);
    }
    
    public List InternationalCompareAllStudentByFaculty(InBoundOutBoundServiceM persistentInstance){
    	return chartRepository.InternationalCompareAllStudentByFaculty(persistentInstance);
    }
    
    public List InternationalCompareAllEmpByFaculty(InBoundOutBoundServiceM persistentInstance){
    	return chartRepository.InternationalCompareAllEmpByFaculty(persistentInstance);
    }*/
    
    public List<FilterM> getGlobalFilter(){
		List<FilterM> filters = new ArrayList<FilterM>();
    	List<FilterEntity> feList = chartRepository.fetchGlobalFilter();
		for(FilterEntity fe : feList){
			FilterM fm = new FilterM();
			BeanUtils.copyProperties(fe, fm); // source.target
			//System.out.println("global value=>"+fm.getValueType());
			//filterValue
			List<FilterM> paramFilter = chartRepository.findParamFilterMapping(fm.getFilterId());
			//DatasourceConnectionEntity dc = null;
			List<FilterValueM> fvs = datasourceRepository.fetchFilterValueCascade( fe,paramFilter);
			fm.setFilterValues(fvs);
			//into list
			filters.add(fm);
		}
		return filters;
    }
    @SuppressWarnings("unchecked")
	public FusionChartM buildChartObject(FusionChartM source){
    	ChartInstanceEntity chartInsEnt = chartRepository.findChartInstanceEntityById(source.getInstanceId());
    	if(chartInsEnt!=null){
	    	source.setChartType(chartInsEnt.getChartType());
	    	source.setServiceId(chartInsEnt.getServiceId());
	    	try {
	    		//find filter v2  portlet dont send filter ***
		    	/*FilterInstanceM fim = chartRepository.fetchFilterInstance(chartInsEnt.getInstanceId(),source.getFilters());
		    	List<FilterM> allFilters = new ArrayList<FilterM>(fim.getFilterList());
		    	if(source.getFilters()==null | source.getFilters().size()==0  ){ // v1 portlet only send global filters 
		    		List<FilterM> globalFilters = chartRepository.fetchGlobalFilter();
		    		allFilters.addAll(globalFilters);
		    	}else{
		    		List<FilterM> globalFilters = source.getFilters();
		    		allFilters.addAll(globalFilters);
		    	}*/
		    	//source.setFilters(allFilters);  // set Filters for generate Title in  portlet app side
		    	List<FilterM> allFilters = source.getFilters();  // v3 portlet send filter(internal & global)
	    		//autoSetFilterValue(allFilters); // such as role filter
	    		
		    	// retrive data in chart
		    	List<Object[]> results = new ArrayList<Object[]>();
		    	if(chartInsEnt.getDataSourceType().equals("1")){
			    	//fetch  result
		    		ServiceEntity se = chartRepository.findServiceEntityById(chartInsEnt.getServiceId());
		    		DatasourceConnectionEntity de = chartRepository.findDatasourceConnectionById(se.getConnId());
		    		results = datasourceRepository.fetchChartResultSet(de,se, allFilters);
		    	}else if(chartInsEnt.getDataSourceType().equals("2")){
		    		results = convertDataJsonToList(chartInsEnt.getDataAdhoc());
		    	}
		    	String chartJson = "";
		    	//System.out.println("build chart of type=>"+chartInsEnt.getChartType().toLowerCase());
		    	if(chartInsEnt.getChartType().toLowerCase().equals("bar2d")){
		    		Bar bar = new Bar();
		    		bar.setTemplate(chartInsEnt.getChartJson());
		    		bar.setData(results);
		    		chartJson = bar.build();
		    	}else if(chartInsEnt.getChartType().toLowerCase().equals("angulargauge")){
		    		RealTimeAngular rta = new RealTimeAngular();
		    		rta.setTemplate(chartInsEnt.getChartJson());
		    		System.out.println("exception =>"+results.size());
		    		rta.setData(results);
		    		chartJson = rta.build();
		    	} else if(chartInsEnt.getChartType().toLowerCase().equals("mscolumn2d")) {
		    		MultiSeriesColumn2D multiSeriesColumn2D = new MultiSeriesColumn2D();
		    		multiSeriesColumn2D.setTemplate(chartInsEnt.getChartJson());
		    		multiSeriesColumn2D.setData(results);
		    		chartJson = multiSeriesColumn2D.build();
		    	} else if(chartInsEnt.getChartType().toLowerCase().equals("table")) {
		    		Table table = new Table();
		    		table.setTemplate(chartInsEnt.getChartJson());
		    		table.setData(results);
		    		chartJson = table.build();	
		    	} 
		    	/*Multi serial line 2d*/
		    	else if(chartInsEnt.getChartType().toLowerCase().equals("msline")){		    		
		    		MultiSeriesLine2D multiSeriesLine2D = new MultiSeriesLine2D();
		    		multiSeriesLine2D.setTemplate(chartInsEnt.getChartJson());
		    		multiSeriesLine2D.setData(results);
		    		chartJson = multiSeriesLine2D.build();
		    	} 
		    	/*Multi serial Stack Column 2d*/
		    	else if(chartInsEnt.getChartType().toLowerCase().equals("msstackedcolumn2d")){		    		
		    		MultiSeriesStackColumn2D multiSeriesStackColumn2D = new MultiSeriesStackColumn2D();
		    		multiSeriesStackColumn2D.setTemplate(chartInsEnt.getChartJson());
		    		multiSeriesStackColumn2D.setData(results);
		    		chartJson = multiSeriesStackColumn2D.build();
		    	} 
		    	/*Multi Pie 3D*/
		    	else if(chartInsEnt.getChartType().toLowerCase().equals("pie3d")){		    		
		    		Pie3D pie3d = new Pie3D();
		    		pie3d.setTemplate(chartInsEnt.getChartJson());
		    		pie3d.setData(results);
		    		chartJson = pie3d.build();
		    	} 
		    	/*Vertical LED*/
		    	else if(chartInsEnt.getChartType().toLowerCase().equals("vled")){		    		
		    		VerticalLed verticalLed = new VerticalLed();
		    		verticalLed.setTemplate(chartInsEnt.getChartJson());
		    		verticalLed.setData(results);
		    		chartJson = verticalLed.build();
		    	}
		    	/*Column 2D*/
		    	else if(chartInsEnt.getChartType().toLowerCase().equals("column2d")){		    		
		    		Column2D column2d = new Column2D();
		    		column2d.setTemplate(chartInsEnt.getChartJson());
		    		column2d.setData(results);
		    		chartJson = column2d.build();
		    	}
		    	/*Radar*/
		    	else if(chartInsEnt.getChartType().toLowerCase().equals("radar")){		    		
		    		Radar radar = new Radar();
		    		radar.setTemplate(chartInsEnt.getChartJson());
		    		radar.setData(results);
		    		chartJson = radar.build();
		    	}
		    	/*Horizontal Linear Gauge*/
		    	else if(chartInsEnt.getChartType().toLowerCase().equals("hlineargauge")){		    		
		    		HorizontalLinearGauge hLinearGauge = new HorizontalLinearGauge();
		    		hLinearGauge.setTemplate(chartInsEnt.getChartJson());
		    		hLinearGauge.setData(results);
		    		chartJson = hLinearGauge.build();
		    	}
		    	/*Horizontal LED*/
		    	else if(chartInsEnt.getChartType().toLowerCase().equals("hled")){		    		
		    		HorizontalLED horizontalLED = new HorizontalLED();
		    		horizontalLED.setTemplate(chartInsEnt.getChartJson());
		    		horizontalLED.setData(results);
		    		chartJson = horizontalLED.build();
		    	}
		    	/*Stacked Column 2D chart*/
		    	else if(chartInsEnt.getChartType().toLowerCase().equals("stackedcolumn2d")){		    		
		    		StackColumn2D stackColumn2D = new StackColumn2D();
		    		stackColumn2D.setTemplate(chartInsEnt.getChartJson());
		    		stackColumn2D.setData(results);
		    		chartJson = stackColumn2D.build();
		    	}
		    	/*Horizontal Bullet graph*/
		    	else if(chartInsEnt.getChartType().toLowerCase().equals("hbullet")){		    		
		    		HorizontalBullet horizontalBullet = new HorizontalBullet();
		    		horizontalBullet.setTemplate(chartInsEnt.getChartJson());
		    		horizontalBullet.setData(results);
		    		chartJson = horizontalBullet.build();
		    	}
		    	/*Box and Whisker 2D*/
		    	else if(chartInsEnt.getChartType().toLowerCase().equals("boxandwhisker2d")){
		    		BoxAndWhisker2DTemporary boxAndWhisker2D = new BoxAndWhisker2DTemporary();
		    		boxAndWhisker2D.setTemplate(chartInsEnt.getChartJson());
		    		boxAndWhisker2D.setData(results);
		    		chartJson = boxAndWhisker2D.build();
		    	}
		    	/*Multi-Series 2D DualY Combination*/
		    	else if(chartInsEnt.getChartType().toLowerCase().equals("mscombidy2d")){
		    		MultiSeries2DDualYCombination multiSeries2DDualYCombination = new MultiSeries2DDualYCombination();
		    		multiSeries2DDualYCombination.setTemplate(chartInsEnt.getChartJson());
		    		multiSeries2DDualYCombination.setData(results);
		    		chartJson = multiSeries2DDualYCombination.build();
		    	}
		    	/*Stacked Area 2D*/
		    	else if(chartInsEnt.getChartType().toLowerCase().equals("stackedarea2d")){
		    		StackArea2D stackArea2D = new StackArea2D();
		    		stackArea2D.setTemplate(chartInsEnt.getChartJson());
		    		stackArea2D.setData(results);
		    		chartJson = stackArea2D.build();
		    	}
		    	/*Doughnut 3D chart*/
		    	else if(chartInsEnt.getChartType().toLowerCase().equals("doughnut3d")){
		    		Doughnut3D doughnut3d = new Doughnut3D();
		    		doughnut3d.setTemplate(chartInsEnt.getChartJson());
		    		doughnut3d.setData(results);
		    		chartJson = doughnut3d.build();
		    	}
		    	/*Multi-series Column 3D + Line - Dual Y Axis*/
		    	else if(chartInsEnt.getChartType().toLowerCase().equals("mscolumn3dlinedy")){
		    		MultiSeriesColumn3DLineDualY multiSeriesColumn3DLineDualY = new MultiSeriesColumn3DLineDualY();
		    		multiSeriesColumn3DLineDualY.setTemplate(chartInsEnt.getChartJson());
		    		multiSeriesColumn3DLineDualY.setData(results);
		    		chartJson = multiSeriesColumn3DLineDualY.build();
		    	}
		    	/*Stacked Column 3D + Line Single Y Axis*/
		    	else if(chartInsEnt.getChartType().equals("stackedColumn3DLine")){
		    		StackedColumn3DLineSingleY stackedColumn3DLineSingleY = new StackedColumn3DLineSingleY();
		    		stackedColumn3DLineSingleY.setTemplate(chartInsEnt.getChartJson());
		    		stackedColumn3DLineSingleY.setData(results);
		    		chartJson = stackedColumn3DLineSingleY.build();
		    	}
		    	/*Multi-series Bar 2D*/
		    	else if(chartInsEnt.getChartType().toLowerCase().equals("msbar2d")){
		    		MultiSeriesBar2D multiSeriesBar2D = new MultiSeriesBar2D();
		    		multiSeriesBar2D.setTemplate(chartInsEnt.getChartJson());
		    		multiSeriesBar2D.setData(results);
		    		chartJson = multiSeriesBar2D.build();
		    	}
		    	/*Stacked Bar 2D chart*/
		    	else if(chartInsEnt.getChartType().toLowerCase().equals("stackedbar2d")){
		    		StackBar2D stackBar2D = new StackBar2D();
		    		stackBar2D.setTemplate(chartInsEnt.getChartJson());
		    		stackBar2D.setData(results);
		    		chartJson = stackBar2D.build();
		    	}
		    	/*Pie 2D chart*/
		    	else if(chartInsEnt.getChartType().toLowerCase().equals("pie2d")){
		    		Pie2D pie2d = new Pie2D();
		    		pie2d.setTemplate(chartInsEnt.getChartJson());
		    		pie2d.setData(results);
		    		chartJson = pie2d.build();
		    	}
		    	/*Stacked Column 3D + Line Dual Y axis chart*/
		    	else if(chartInsEnt.getChartType().toLowerCase().equals("stackedcolumn3dlinedy")){
		    		StackedColumn3DLineDuaY stackedColumn3DLineDuaY = new StackedColumn3DLineDuaY();
		    		stackedColumn3DLineDuaY.setTemplate(chartInsEnt.getChartJson());
		    		stackedColumn3DLineDuaY.setData(results);
		    		chartJson = stackedColumn3DLineDuaY.build();
		    	}
		    	
		    	// set title
		    	if( !chartInsEnt.getChartType().equals("table") ){
			    	JSONObject chartJsonObj = new JSONObject(chartJson);
			    	JSONObject chartObj = (JSONObject) chartJsonObj.get("chart");
			    	chartObj.put("caption", chartInsEnt.getChartTitle());
			    	chartObj.put("subCaption", chartInsEnt.getChartSubTitle() );
			    	chartJson = chartJsonObj.toString();
		    	}else{
		    		JSONObject chartJsonObj = new JSONObject(chartJson);
			    	JSONObject chartObj = (JSONObject) chartJsonObj.get("table");
			    	chartObj.put("caption", chartInsEnt.getChartTitle());
			    	chartObj.put("subCaption", chartInsEnt.getChartSubTitle() );
			    	chartJson = chartJsonObj.toString();
		    	}
		    	// end set title
		    	// add json
		    	source.setChartJson(chartJson);
		    	logger.info("object of "+source.getInstanceId()+" => "+chartJson);
			} catch (Exception e) {
				logger.info("exception of "+source.getInstanceId()+" => "+e);
				e.printStackTrace();
			}
    	}//check exist
    	return source;
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public List convertDataJsonToList(String jsonString){
    	List  dataList = new ArrayList<Object[]>();
    	try {
    		JSONObject json = new JSONObject(jsonString);
    		JSONArray rows = json.getJSONArray("data");
			 for (int i = 0; i < rows.length(); i++) {
				 JSONObject row = rows.getJSONObject(i);
				 JSONArray rw = row.getJSONArray("row");
				 if(rw.length()>1){
					 String[] a = new String[rw.length()];
					 for(int as = 0 ;as<rw.length();as++){
						 a[as] = rw.get(as).toString();
					 }
					 dataList.add(a);
				 }else{
					 dataList.add(rw.getString(0));
				 }
			}
			
		} catch (JSONException e) {
			dataList = new ArrayList<Object[]>();
		}
    	return dataList;
    }
    public List<FilterInstanceM> getFilterInstanceWithItem(String instanceId){
    	List<FilterInstanceM> fins = new ArrayList<FilterInstanceM>();
     	List<Object[]> resultSet = chartRepository.fetchFilterInstanceWithItem(instanceId); 	
     	for( Object[] result :  resultSet){
			FilterInstanceM fin = new FilterInstanceM();
			fin.setInstanceId(instanceId);
			fin.setFilterId( (Integer)result[0]);
			FilterM f = new FilterM();
			f.setFilterId( (Integer)result[0] );
			f.setFilterName( (String) result[1]);
			f.setTitle( (String) result[2]);
			f.setValueType((String)result[3]);
			f.setSqlQuery((String) result[4]);
			f.setSelectedValue((String)result[5]);
			f.setConnId((Integer)result[6]);
			List<FilterM> paramFilter = chartRepository.findParamFilterMapping(f.getFilterId());
			
			FilterEntity fe = new FilterEntity();
			BeanUtils.copyProperties(f, fe);
			List<FilterValueM> fvs = datasourceRepository.fetchFilterValueCascade( fe,paramFilter);
			f.setFilterValues(fvs);
			fin.setFilterM(f);	//set fin Filter
			fins.add(fin);
		}
		return fins;
   
    }
    public List<FilterM> getFilterOfService(Integer serviceId,String userId) {
    	List<FilterM> globalFilter = getGlobalFilter();
    	List<FilterM> filters = new ArrayList<FilterM>();
	    	List<Object[]> results = chartRepository.fetchFilterOfService(serviceId,globalFilter,userId);
	    	for(Object[] result : results){

				FilterM filter = new FilterM();
				filter.setFilterId( (Integer)result[0]); 
				filter.setFilterName((String)result[1]);
				filter.setTitle((String)result[2]);
				filter.setSelectedValue((String)result[3]);
				filter.setSqlQuery((String)result[4]);
				filter.setAutoFill((String)result[5]);
				filter.setConnId((Integer)result[6]);
				//filterValue
				List<FilterM> paramFilter = new ArrayList<FilterM>();
				paramFilter.addAll(globalFilter);
				paramFilter.addAll(chartRepository.findParamFilterMapping(filter.getFilterId()));
				//replace sys filter
				String IdentifiyFilterAuthen = chartRepository.getConstant("authen");
				for( FilterM lookup : paramFilter ){
					if(IdentifiyFilterAuthen.equalsIgnoreCase(lookup.getFilterName())){
						lookup.setSelectedValue(userId);
					}
					//System.out.println("#:lookup.getFilterName():  " +lookup.getFilterName()+"  " + lookup.getSelectedValue() + " d:"+lookup.getDefaultValue() );
				}
				FilterEntity fe = new FilterEntity();
				BeanUtils.copyProperties(filter, fe);
				List<FilterValueM> fvs = datasourceRepository.fetchFilterValueCascade(fe,paramFilter);
				filter.setFilterValues(fvs);
				
				if( filter.getAutoFill().equals("1") && fvs.size()>0  ){
						filter.setSelectedValue(fvs.get(0).getValueMapping());
				}
				// add filters
				filters.add(filter);
			}
			return filters;
    }
    public List<ChartFilterInstanceM> getChartFilterInstance(ChartFilterInstanceM chartFilterInstance){
    	List<ChartFilterInstanceM> cfi = new ArrayList<ChartFilterInstanceM>();
    	
    	List<Object[]> results = chartRepository.fetchChartFilterInstance(chartFilterInstance);
    		
 		for(Object[] result : results){
 			ChartFilterInstanceM im = new ChartFilterInstanceM();
 			im.setServiceId((Integer)result[0]);
 			im.setFilterId((Integer)result[1]);
 			FilterM f = new FilterM();
 			f.setFilterId((Integer)result[1]);
 			f.setFilterName((String)result[2]);
 			f.setTitle((String)result[3]);
 			f.setActiveFlag( (String)result[4]);
 			f.setSqlQuery((String)result[5]);
 			f.setSelectedValue( (String)result[6] );
 			f.setConnId((Integer)result[7]);
 			// find filter Item
 			List<FilterValueM> fvs = new ArrayList<FilterValueM>();
 			try{
 				//fvs = fetchFilterValue( f.getSqlQuery());
 				List<FilterM> paramFilter = chartRepository.findParamFilterMapping(f.getFilterId());
 				FilterEntity fe = new FilterEntity();
 				BeanUtils.copyProperties(f,fe);
 				 fvs = datasourceRepository.fetchFilterValueCascade(fe,paramFilter);
 			}catch(Exception ex){
 				 fvs = new ArrayList<FilterValueM>();
 			}
 			f.setFilterValues(fvs);
 			im.setFilterM(f);
 			cfi.add(im);
 		}
 		return cfi;

    }
    @Override 
    public FilterInstanceM getFilterInstance(FilterInstanceM fim){
		 List<FilterM> filters = new ArrayList<FilterM>();  
    	List<Object[]> resultSet = chartRepository.fetchFilterInstance(fim.getInstanceId());
		for( Object[] result :  resultSet){
			FilterM f = new FilterM();
			f.setFilterId((Integer)result[0] );
			f.setFilterName( (String)result[1]);
			f.setTitle((String)result[2]);
			f.setValueType((String)result[3]);
			f.setSelectedValue( (String)result[4] );
			f.setSqlQuery((String)result[5]);
			f.setConnId((Integer)result[6]);
			//String sqlItem = (String)result[5];
			// filter value
			List<FilterM> initParamFilter = chartRepository.findParamFilterMapping(f.getFilterId());
			//override
			if(fim.getFilterList()!=null ) 	initParamFilter.addAll(fim.getFilterList()); // add global ?
		//	filterList.addAll(paramFilter);

			FilterEntity fe = new FilterEntity();
			BeanUtils.copyProperties(f, fe);
			List<FilterValueM> fvs = datasourceRepository.fetchFilterValueCascade(fe,initParamFilter);
			f.setFilterValues(fvs);
			filters.add(f);
		}
		fim.setFilterList(filters);
		return fim;
    }
	@Override
	public FilterInstanceM saveFilterInstance(FilterInstanceM fim) {
		return chartRepository.saveFilterInstance(fim);
	}
	public Integer deleteFilterInstance(String instanceId){
		return chartRepository.deleteFilterInstance(instanceId);
	}
	@Override
	public Integer updateFilterInstance(FilterInstanceM fim) {
		return chartRepository.updateFilterInstance(fim);
	}
	@Override
	public FilterInstanceM cascadeFilter(FilterInstanceM fin) {
		//add column name
		for(FilterM filter : fin.getFilterList() ){
			FilterEntity fe = chartRepository.findFilterEntityById(filter.getFilterId());
			filter.setFilterName(fe.getFilterName());
			filter.setValueType(fe.getValueType());
		}
		
		//find cause Filter 
		FilterM causeFilter = new FilterM();
		for(FilterM filter : fin.getFilterList()){
			if(filter.getActiveFlag().equals("0")){
				causeFilter  = filter;
			//	fin.getFilterList().remove(filter);
			}
		}
		List<FilterM> cascades = chartRepository.findFilterByParamMapping(causeFilter.getFilterId());
		
		for(FilterM cascade : cascades){
			List<FilterM> paramFilterDefault =  chartRepository.findParamFilterMapping(cascade.getFilterId());
			List<FilterM> paramFilter = new ArrayList<FilterM>(paramFilterDefault);
			paramFilter.addAll(fin.getFilterList());

			FilterEntity fe = new FilterEntity();
			BeanUtils.copyProperties(cascade, fe);
			List<FilterValueM> fv  = datasourceRepository.fetchFilterValueCascade(fe,paramFilter);
			cascade.setFilterValues(fv);
		}
		
		fin.setFilterList(cascades);
		return fin;
	}
	@Override
	public List<DatasourceConnectionEntity> listConnection() throws Exception {
		return chartRepository.listConnection();
	}
	@Override
	public FilterM getFilterWithParam(Integer filterId){
		try{
			FilterM filter = new FilterM();
			FilterEntity filterEntity = chartRepository.findFilterEntityById(filterId);
			BeanUtils.copyProperties(filterEntity, filter);
			List<FilterM> listParamFilter = chartRepository.findParamFilterMapping(filterId) ;
			filter.setFilterList(  listParamFilter );
		   return filter;
		}catch(Exception e){
			return new FilterM();
		}
	}
	@Override
	public List<ChartM> findChartByServiceId(Integer serviceId) throws Exception {
		List<ChartM> cmList = new ArrayList<ChartM>();
		for( ChartEntity en :  chartRepository.findChartByServiceId(serviceId) ){
			ChartM cm = new ChartM();
			BeanUtils.copyProperties(en, cm);
			cmList.add(cm);
		}
		return cmList;
	}
	@Override
	public List<ServiceM> findServiceByUser(String userId) throws Exception {
		List<ServiceM> smList = new ArrayList<ServiceM>();
		for( ServiceEntity en :  chartRepository.findServiceByUser(userId) ){
			ServiceM sm = new ServiceM();
			BeanUtils.copyProperties(en, sm);
			smList.add(sm);
		}
		return smList;
	}
	@Override
	public List<UserM> listUser() throws Exception {
		return chartRepository.listUser();
	}
	
	@Override
	public List<UserM> findUserByServiceId(Integer serviceId) throws Exception {	
		return chartRepository.findUserByService(serviceId);
	}
	@Override
	public Integer newConnection(DatasourceConnectionEntity e) throws Exception {
		return chartRepository.newConnection(e);
	}
	@Override
	public Integer updateConnection(DatasourceConnectionEntity e) throws Exception {
		return chartRepository.updateConnection(e);
	}
	@Override
	public Integer deleteConnection(Integer connId) throws Exception {
		return chartRepository.deleteConnection(connId);
	}
	@Override
	public DatasourceConnectionEntity  findConnectionById(Integer connId) throws Exception {
		return chartRepository.findConnectionById(connId);
	}
}
