package th.ac.kmutt.chart.service;

import th.ac.kmutt.chart.model.*;

import java.util.List;

//import th.ac.kmutt.research.bean.McDegree;

public interface ChartService {

	//
	/*
	public Integer saveResearchGroupM(ResearchGroupM researchGroupM) ;
	public Integer updateResearchGroupM(ResearchGroupM researchGroupM) ;
	public Integer deleteResearchGroupM(ResearchGroupM researchGroupM);
	public Integer deleteItemsResearchGroupM(String[] researchGroupId);
	public ResearchGroupM findResearchGroupById(Integer researchGroupId);
	//public List<ResearchGroupM> searchResearchGroupM(ResearchGroupM researchGroupM) ;
	public ImakeResultMessage searchResearchGroupM(ResearchGroupM researchGroupM) ;
	public List<ResearchGroupM> listResearchGroupM();
*/

	//public List listCopyrightService(th.ac.kmutt.chart.model.CopyrightServiceM param);
	//public List listJournalPapersService(th.ac.kmutt.chart.model.JournalPapersServiceM param);
	//public List listFundingResourceService(th.ac.kmutt.chart.model.FundingResourceServiceM param);


	//CHART
	public Integer saveChart(ChartM model) ;
	public Integer updateChart(ChartM model) ;
	public Integer deleteChart(ChartM model) ;
	public ChartM findChartById(Integer chartId) ;
	public List listChart(ChartM param);
	
	//CHART_FEATURE
	public Integer saveChartFeature(ChartFeatureM model) ;
	public Integer updateChartFeature(ChartFeatureM model) ;
	public Integer deleteChartFeature(ChartFeatureM model) ;
	public ChartFeatureM findChartFeatureById(Integer chartId) ;

	//CHART_FEATURE_INSTANCE
	public Integer saveChartFeatureInstance(ChartFeatureInstanceM model) ;
	public Integer updateChartFeatureInstance(ChartFeatureInstanceM model) ;
	public Integer deleteChartFeatureInstance(ChartFeatureInstanceM model) ;
	public ChartFeatureInstanceM findChartFeatureInstanceById(String instanceId) ;

	//CHART_FEATURE_MAPPIG
	public Integer saveChartFeatureMapping(ChartFeatureMappingM model) ;
	public Integer updateChartFeatureMapping(ChartFeatureMappingM model) ;
	public Integer deleteChartFeatureMapping(ChartFeatureMappingM model) ;
	public ChartFeatureMappingM findChartFeatureMappingById(Integer featureId, Integer chartId) ;

	//CHART_FEATURE_MAPPIG
	public Integer saveChartFilterInstance(ChartFilterInstanceM model) ;
	public Integer updateChartFilterInstance(ChartFilterInstanceM model) ;
	public Integer deleteChartFilterInstance(ChartFilterInstanceM model) ;
	public ChartFilterInstanceM findChartFilterInstanceById(String instanceId) ;
	public List listChartFilterInstance(th.ac.kmutt.chart.model.ChartFilterInstanceM param);

	//CHART_INSTANCE
	public Integer saveChartInstance(ChartInstanceM model) ;
	public Integer updateChartInstance(ChartInstanceM model) ;
	public Integer deleteChartInstance(ChartInstanceM model) ;
	public ChartInstanceM findChartInstanceById(String instanceId) ;

	//COMMENT
	public Integer saveComment(CommentM model) ;
	public Integer updateComment(CommentM model) ;
	public Integer deleteComment(CommentM model) ;
	public CommentM findCommentById(String instanceId) ;

	//FEATURE
	public Integer saveFeature(FeatureM model) ;
	public Integer updateFeature(FeatureM model) ;
	public Integer deleteFeature(FeatureM model) ;
	public FeatureM findFeatureById(Integer featureId) ;

	//FILTER
	public Integer saveFilter(FilterM model) ;
	public Integer updateFilter(FilterM model) ;
	public Integer deleteFilter(FilterM model) ;
	public FilterM findFilterById(Integer filterId) ;
	public List listFilter(th.ac.kmutt.chart.model.FilterM param);

	//FILTER_INSTANCE
	public Integer saveFilterInstance(FilterInstanceM model) ;
	public Integer updateFilterInstance(FilterInstanceM model) ;
	public Integer deleteFilterInstance(FilterInstanceM model) ;
	public FilterInstanceM findFilterInstanceById(String instanceId) ;
	public List listFilterInstance(th.ac.kmutt.chart.model.FilterInstanceM param);

	/*
	//FUNDING_RESOURCE_SERVICE
	public Integer saveFundingResourceService(FundingResourceServiceM model) ;
	public Integer updateFundingResourceService(FundingResourceServiceM model) ;
	public Integer deleteFundingResourceService(FundingResourceServiceM model) ;
	public FundingResourceServiceM findFundingResourceServiceById(Integer type,Integer year) ;

	//JOURNAL_PAPERS_SERVICE
	public Integer saveJournalPapersService(JournalPapersServiceM model) ;
	public Integer updateJournalPapersService(JournalPapersServiceM model) ;
	public Integer deleteJournalPapersService(JournalPapersServiceM model) ;
	public JournalPapersServiceM findJournalPapersServiceById(Integer type,Integer year) ;

	//COPYRIGHT_SERVICE
		public Integer saveCopyrightService(CopyrightServiceM model) ;
		public Integer updateCopyrightService(CopyrightServiceM model) ;
		public Integer deleteCopyrightService(CopyrightServiceM model) ;
		public CopyrightServiceM findCopyrightServiceById(Integer type,Integer year,Integer month) ;
*/
	
	//SERVICE_CHART_MAPPING
	public Integer saveServiceChartMapping(ServiceChartMappingM model) ;
	public Integer updateServiceChartMapping(ServiceChartMappingM model) ;
	public Integer deleteServiceChartMapping(ServiceChartMappingM model) ;
	public ServiceChartMappingM findServiceChartMappingById(Integer serviceId,Integer chartId) ;

	//SERVICE
	public Integer saveService(ServiceM model) ;
	public Integer updateService(ServiceM model) ;
	public Integer deleteService(ServiceM model) ;
	public ServiceM findServiceById(Integer serviceId) ;
	public List listService(ServiceM param);
	public List listServiceByChart(ServiceM param);
	public List listInboundOutbound(InBoundOutBoundServiceM param);
	
	//SERVICE
	public Integer saveServiceFilterMapping(ServiceFilterMappingM model) ;
	public Integer updateServiceFilterMapping(ServiceFilterMappingM model) ;
	public Integer deleteServiceFilterMapping(ServiceFilterMappingM model) ;
	public ServiceFilterMappingM findServiceFilterMappingById(Integer serviceId,Integer filterId) ;
	public List listServiceFilterMapping(ServiceFilterMappingM param);
	
	//new service by pk
	public List<FilterM> getGlobalFilter();
	public FusionChartM getFusionChart(FusionChartM obj);
	public FilterInstanceM getFilterInstance(FilterInstanceM obj); // get Filter instance
	public List<FilterInstanceM> getFilterInstanceWithItem(FilterInstanceM obj); // get filter instance with item
	public List<FilterM> getFilterService(FilterM obj);
	public List<ChartFilterInstanceM> getChartFilterInstance(ChartFilterInstanceM obj);

	//cascade
	public List<FilterM> cascadeFilterItems(FilterInstanceM fin); 
	
	
	// chart datasource
	public List<ServiceM> listChartDatasource();
	public ServiceM detailChartDatasource(ServiceM s);
	public Integer saveChartDatasource(ServiceM s);
	public Integer saveDatasourceXFilter(ServiceM s);
	public Integer deleteDatasourceXFilter(ServiceM s);

	// filter 
	public FilterM detailFilter(FilterM filter);
	
	public List<UserM> listUser();
	public List<UserM> listUserByService(ServiceM s);
	
	//connection
	public List<ConnectionM> listConnection();
	public void newConnection(ConnectionM con) throws Exception;
	public void updateConnection(ConnectionM con)  throws Exception;
	public void deleteConnection(ConnectionM con)  throws Exception;
	public ConnectionM findConnectionById(Integer id) throws Exception;
	
}
