package th.ac.kmutt.chart.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import th.ac.kmutt.chart.domain.ChartEntity;
import th.ac.kmutt.chart.domain.ChartFeatureEntity;
import th.ac.kmutt.chart.domain.ChartFeatureInstanceEntity;
import th.ac.kmutt.chart.domain.ChartFeatureMappingEntity;
import th.ac.kmutt.chart.domain.ChartFeatureMappingEntityPK;
import th.ac.kmutt.chart.domain.ChartFilterInstanceEntity;
import th.ac.kmutt.chart.domain.ChartInstanceEntity;
import th.ac.kmutt.chart.domain.CommentEntity;
import th.ac.kmutt.chart.domain.DatasourceConnectionEntity;
import th.ac.kmutt.chart.domain.FeatureEntity;
import th.ac.kmutt.chart.domain.FilterEntity;
import th.ac.kmutt.chart.domain.FilterInstanceEntity;
import th.ac.kmutt.chart.domain.ServiceChartMappingEntity;
import th.ac.kmutt.chart.domain.ServiceChartMappingEntityPK;
import th.ac.kmutt.chart.domain.ServiceEntity;
import th.ac.kmutt.chart.domain.ServiceFilterMappingEntity;
import th.ac.kmutt.chart.domain.ServiceFilterMappingEntityPK;
import th.ac.kmutt.chart.model.ChartFilterInstanceM;
import th.ac.kmutt.chart.model.ChartM;
import th.ac.kmutt.chart.model.ConnectionM;
import th.ac.kmutt.chart.model.CopyrightServiceM;
import th.ac.kmutt.chart.model.FilterInstanceM;
import th.ac.kmutt.chart.model.FilterM;
import th.ac.kmutt.chart.model.FusionChartM;
import th.ac.kmutt.chart.model.InBoundOutBoundServiceM;
import th.ac.kmutt.chart.model.ServiceM;
import th.ac.kmutt.chart.model.UserM;

public interface ChartService {
    public List listChart() throws DataAccessException;

    //CHART
    public Integer saveChartEntity(ChartEntity transientInstance) throws DataAccessException;
    public Integer updateChartEntity(ChartEntity transientInstance) throws DataAccessException;
    public Integer deleteChartEntity(ChartEntity persistentInstance) throws DataAccessException;
    public ChartEntity findChartEntityById(Integer chartId) throws DataAccessException;
    public List listChartEntity(ChartM param)throws DataAccessException;
    //CHART_FEATURE
    public Integer saveChartFeatureEntity(ChartFeatureEntity transientInstance) throws DataAccessException;
    public Integer updateChartFeatureEntity(ChartFeatureEntity transientInstance) throws DataAccessException;
    public Integer deleteChartFeatureEntity(ChartFeatureEntity persistentInstance) throws DataAccessException;
    public ChartFeatureEntity findChartFeatureEntityById(Integer chartId) throws DataAccessException;

    //CHART_FEATURE_INSTANCE
    public Integer saveChartFeatureInstanceEntity(ChartFeatureInstanceEntity transientInstance) throws DataAccessException;
    public Integer updateChartFeatureInstanceEntity(ChartFeatureInstanceEntity transientInstance) throws DataAccessException;
    public Integer deleteChartFeatureInstanceEntity(ChartFeatureInstanceEntity persistentInstance) throws DataAccessException;
    public ChartFeatureInstanceEntity findChartFeatureInstanceEntityById(String instanceId) throws DataAccessException;

    //CHART_FEATURE_MAPPIG
    public Integer saveChartFeatureMappingEntity(ChartFeatureMappingEntity transientInstance) throws DataAccessException;
    public Integer updateChartFeatureMappingEntity(ChartFeatureMappingEntity transientInstance) throws DataAccessException;
    public Integer deleteChartFeatureMappingEntity(ChartFeatureMappingEntity persistentInstance) throws DataAccessException;
    public ChartFeatureMappingEntity findChartFeatureMappingEntityById(ChartFeatureMappingEntityPK id) throws DataAccessException;

    //CHART_FILTER_INSTANCE
    public Integer saveChartFilterInstanceEntity(ChartFilterInstanceEntity transientInstance) throws DataAccessException;
    public Integer updateChartFilterInstanceEntity(ChartFilterInstanceEntity transientInstance) throws DataAccessException;
    public Integer deleteChartFilterInstanceEntity(ChartFilterInstanceEntity persistentInstance) throws DataAccessException;
    public ChartFilterInstanceEntity findChartFilterInstanceEntityById(String instanceId) throws DataAccessException;
    public List listChartFilterInstanceEntity(th.ac.kmutt.chart.model.ChartFilterInstanceM param)throws DataAccessException;

    //CHART_INSTANCE
    public Integer saveChartInstanceEntity(ChartInstanceEntity transientInstance) throws DataAccessException;
    public Integer updateChartInstanceEntity(ChartInstanceEntity transientInstance) throws DataAccessException;
    public Integer deleteChartInstanceEntity(ChartInstanceEntity persistentInstance) throws DataAccessException;
    public ChartInstanceEntity findChartInstanceEntityById(String instanceId) throws DataAccessException;



    //COMMENT
    public Integer saveCommentEntity(CommentEntity transientInstance) throws DataAccessException;
    public Integer updateCommentEntity(CommentEntity transientInstance) throws DataAccessException;
    public Integer deleteCommentEntity(CommentEntity persistentInstance) throws DataAccessException;
    public CommentEntity findCommentEntityById(String instanceId) throws DataAccessException;

    
    //FEATURE
    public Integer saveFeatureEntity(FeatureEntity transientInstance) throws DataAccessException;
    public Integer updateFeatureEntity(FeatureEntity transientInstance) throws DataAccessException;
    public Integer deleteFeatureEntity(FeatureEntity persistentInstance) throws DataAccessException;
    public FeatureEntity findFeatureEntityById(Integer featureId) throws DataAccessException;

    //FILTER
    public Integer saveFilterEntity(FilterEntity transientInstance) throws DataAccessException;
    public Integer updateFilterEntity(FilterEntity transientInstance) throws DataAccessException;
    public Integer deleteFilterEntity(FilterEntity persistentInstance) throws DataAccessException;
    public FilterEntity findFilterEntityById(Integer filterId) throws DataAccessException;
    public List listFilterEntity(th.ac.kmutt.chart.model.FilterM param)throws DataAccessException;
    public FilterEntity getFilterValueList(Integer filterId) throws DataAccessException;
    public void saveFilter(FilterM filterModel) throws Exception;
    public void deleteFilter(FilterM filterModel) throws Exception; 
    public void updateFilter(FilterM filterModel) throws Exception;

    //FILTER_INSTANCE
    public Integer saveFilterInstanceEntity(FilterInstanceEntity transientInstance) throws DataAccessException;
    public Integer updateFilterInstanceEntity(FilterInstanceEntity transientInstance) throws DataAccessException;
    public Integer deleteFilterInstanceEntity(FilterInstanceEntity persistentInstance) throws DataAccessException;
    public FilterInstanceEntity findFilterInstanceEntityById(String instanceId) throws DataAccessException;
    public List listFilterInstanceEntity(th.ac.kmutt.chart.model.FilterInstanceM param)throws DataAccessException;

    //SERVICE_CHART_MAPPING
    public Integer saveServiceChartMappingEntity(ServiceChartMappingEntity transientInstance) throws DataAccessException;
    public Integer updateServiceChartMappingEntity(ServiceChartMappingEntity transientInstance) throws DataAccessException;
    public Integer deleteServiceChartMappingEntity(ServiceChartMappingEntity persistentInstance) throws DataAccessException;
    public ServiceChartMappingEntity findServiceChartMappingEntityById(ServiceChartMappingEntityPK id) throws DataAccessException;

    //SERVICE
    public ServiceM findServiceByServiceId(Integer serviceId,String userId) throws Exception;
    public Integer saveService(ServiceM server);
	public Integer updateService(ServiceM xsource);
    public Integer saveServiceEntity(ServiceEntity transientInstance) throws DataAccessException;
    public Integer updateServiceEntity(ServiceEntity transientInstance) throws DataAccessException;
    public Integer deleteServiceEntity(ServiceEntity persistentInstance) throws DataAccessException;
    public ServiceEntity findServiceEntityById(Integer serviceId) throws DataAccessException;
    public List listServiceEntity(th.ac.kmutt.chart.model.ServiceM param)throws DataAccessException;
    public List listServiceByChartId(ServiceM sm) throws DataAccessException;
    //SERVICE
    public Integer saveServiceFilterMappingEntity(ServiceFilterMappingEntity transientInstance) throws DataAccessException;
    public Integer updateServiceFilterMappingEntity(ServiceFilterMappingEntity transientInstance) throws DataAccessException;
    public Integer deleteServiceFilterMappingEntity(ServiceFilterMappingEntity persistentInstance) throws DataAccessException;
    public ServiceFilterMappingEntity findServiceFilterMappingEntityById(ServiceFilterMappingEntityPK id) throws DataAccessException;

    public List listServiceFilterMappingEntity(th.ac.kmutt.chart.model.ServiceFilterMappingM param)throws DataAccessException;

    
    /*INBOUND_OUTBOUND_STUDENT
    public List InternationalCompareAllStudent(InBoundOutBoundServiceM transientInstance);
    public List EmpInternationalCompareAllEmp(InBoundOutBoundServiceM transientInstance);
    public List InternationalCompareAllStudentProgramInter(InBoundOutBoundServiceM transientInstance);
    public List ProgramInternationalCompareAllProgram(InBoundOutBoundServiceM transientInstance);
    public List InternationalCompareAllStudentByFaculty(InBoundOutBoundServiceM transientInstance);
    public List InternationalCompareAllEmpByFaculty(InBoundOutBoundServiceM transientInstance);
	*/
	public FusionChartM buildChartObject(FusionChartM xsource);
	public FilterInstanceM getFilterInstance(FilterInstanceM fim);
	public List<FilterInstanceM> getFilterInstanceWithItem(String instanceId); 
	public List<FilterM> getGlobalFilter();
	public List<FilterM> getFilterOfService(Integer serviceId,String userId) ;
	public List<ChartFilterInstanceM> getChartFilterInstance(ChartFilterInstanceM chartFilterInstance);
	public FilterInstanceM saveFilterInstance(FilterInstanceM fim);
	public Integer deleteFilterInstance(String instanceId);
	public Integer updateFilterInstance(FilterInstanceM fim);
	
	public FilterInstanceM cascadeFilter(FilterInstanceM fin);
	
	public FilterM getFilterWithParam(Integer filterId);
	

	// service X chart
	public List<ChartM> findChartByServiceId(Integer serviceId) throws Exception;
	
	public List<ServiceM> findServiceByUser(String userId) throws Exception;
	//connection
	public List<DatasourceConnectionEntity> listConnection() throws Exception;
	public Integer newConnection(DatasourceConnectionEntity e) throws Exception;
	public Integer updateConnection(DatasourceConnectionEntity e) throws Exception;
	public Integer deleteConnection(Integer connId) throws Exception;
	public DatasourceConnectionEntity findConnectionById(Integer connId) throws Exception;

	//user
	public List<UserM> listUser() throws Exception;
	public List<UserM> findUserByServiceId(Integer serviceId) throws Exception;

	
}
