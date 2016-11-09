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
import th.ac.kmutt.chart.domain.FilterInstanceEntityPK;
import th.ac.kmutt.chart.domain.FilterMappingEntity;
import th.ac.kmutt.chart.domain.ServiceChartMappingEntity;
import th.ac.kmutt.chart.domain.ServiceChartMappingEntityPK;
import th.ac.kmutt.chart.domain.ServiceEntity;
import th.ac.kmutt.chart.domain.ServiceFilterMappingEntity;
import th.ac.kmutt.chart.domain.ServiceFilterMappingEntityPK;
import th.ac.kmutt.chart.domain.ServiceUserMappingEntity;
import th.ac.kmutt.chart.model.ChartFilterInstanceM;
import th.ac.kmutt.chart.model.ConnectionM;
import th.ac.kmutt.chart.model.FilterInstanceM;
import th.ac.kmutt.chart.model.FilterM;
import th.ac.kmutt.chart.model.FilterValueM;
import th.ac.kmutt.chart.model.ServiceM;
import th.ac.kmutt.chart.model.UserM;

@Repository("chartRepository")
@Transactional
public class ChartRepository {
    private static final Logger logger = Logger.getLogger(ServiceConstant.LOG_APPENDER);
    
    @Autowired
    @PersistenceContext(unitName = "HibernatePersistenceUnit")
    private EntityManager entityManager;

    @Autowired
    @PersistenceContext(unitName = "HibernatePersistenceLiferayUnit")
    private EntityManager portalEntityManager;
    /*
    @Autowired
    @PersistenceContext(unitName = "HibernatePersistenceUnitDwh")
    private EntityManager entityManagerDwh;
 */
	public List<DatasourceConnectionEntity> listConnection() throws Exception{
		String sql = "select s from DatasourceConnectionEntity s";
		Query q = entityManager.createQuery(sql, DatasourceConnectionEntity.class);
		return q.getResultList();
	}
    public  DatasourceConnectionEntity findDatasourceConnectionById(Integer id) throws DataAccessException{
        return entityManager.find(DatasourceConnectionEntity.class, id);
    }
    
    /* public EntityManager connectDS(Integer connection)throws Exception{
    	if(connection==null){
    		throw new Exception("No connection");
    	}
    	try{
    	entityManagerDwh.flush();
    	}catch(Exception e){
    		//TransactionRequiredException
    	}
    	DatasourceConnectionEntity dsConn = findDatasourceConnectionById(connection);
    	
    	Map<String, String> properties = new HashMap<String, String>();
    	properties.put("hibernate.connection.driver_class",dsConn.getDriverClass());
    	properties.put("hibernate.connection.url", dsConn.getConnString());
    	properties.put("hibernate.connection.username", dsConn.getUsername());
    	properties.put("hibernate.connection.password", dsConn.getPassword());
    	properties.put("hibernate.dialect", dsConn.getDialect());
    	properties.put("hibernate.show-sql", "true");
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("HibernatePersistenceUnitDwh",properties);
    	EntityManager enM = (EntityManager) emf.createEntityManager();
    	return	 enM; 
    	
    	//return entityManagerDwh;
    }*/
    public List listServiceEntity(th.ac.kmutt.chart.model.ServiceM param) throws DataAccessException {
        StringBuffer sb = new StringBuffer(" select p from ServiceEntity p  where 1=1 ");
        Query query = entityManager.createQuery(sb.toString(), ServiceEntity.class);
        return query.getResultList();
    }
    public List listServiceByChartId(ServiceM sm) throws DataAccessException{
    	StringBuffer sb = new StringBuffer();
    	sb.append("select s.*  from SERVICE_CHART_MAPPING sm , SERVICE s  , CHART c , SERVICE_USER_MAPPING su  "
    			+ " where s.service_id = sm.service_id and sm.chart_id = c.chart_id and su.SERVICE_ID = s.SERVICE_ID "
    			+ " and c.chart_type = :chartType and su.user_id = :username ");
    	Query query = entityManager.createNativeQuery(sb.toString(),ServiceEntity.class);
    	query.setParameter("chartType",sm.getChartType());   
    	query.setParameter("username",sm.getUserId() );
    	return query.getResultList();
    }
    public List listFilterEntity(th.ac.kmutt.chart.model.FilterM param)throws DataAccessException{
        StringBuffer sb = new StringBuffer(" select p from FilterEntity p  where 1=1 ");
        
        if (param.getGlobalFlag()!=null ) {
            sb.append(" and p.globalFlag='" + param.getGlobalFlag() + "' ");
        }

        Query query = entityManager.createQuery(sb.toString(), FilterEntity.class);
        return query.getResultList();
    }
    public List listFilterInstanceEntity(th.ac.kmutt.chart.model.FilterInstanceM param)throws DataAccessException{
        StringBuffer sb = new StringBuffer(" select p from FilterInstanceEntity p  where 1=1 ");
        String instanceId= param.getInstanceId();
        if (instanceId != null ) {
            sb.append(" and p.id.instanceId='" + instanceId + "' ");
        }
        Query query = entityManager.createQuery(sb.toString(), FilterInstanceEntity.class);
        return query.getResultList();
    }

    public List listServiceFilterMappingEntity(th.ac.kmutt.chart.model.ServiceFilterMappingM param)throws DataAccessException{
        StringBuffer sb = new StringBuffer(" select p from ServiceFilterMappingEntity p  where 1=1 ");
        Integer serviceId = param.getServiceId();
        Integer filterId = param.getFilterId();

        if (serviceId != null ) {
            sb.append(" and p.id.serviceId=" + serviceId + " ");
        }
        if (filterId != null ) {
            sb.append(" and p.id.filterId=" + filterId + " ");
        }
        Query query = entityManager.createQuery(sb.toString(), ServiceFilterMappingEntity.class);
        return query.getResultList();
    }
    public List<ChartEntity> listChart() {
        // TODO Auto-generated method stub
        Query query=null;
        StringBuffer sb = new StringBuffer(" select p from  ChartEntity p ");
         query = entityManager.createQuery(sb.toString(), ChartEntity.class);
        query.setFirstResult(0);
        query.setMaxResults(10);
        return query.getResultList();
    }
    public List<ChartEntity> aew() {
        // TODO Auto-generated method stub
        Query query=null;
        StringBuffer sb = new StringBuffer(" select p from  xxx p  ");
        query = entityManager.createQuery(sb.toString(), ChartEntity.class);
        query.setFirstResult(0);
        query.setMaxResults(10);
        return query.getResultList();
    }

    //CHART
    public Integer saveChartEntity(ChartEntity transientInstance) throws DataAccessException{
        entityManager.persist(transientInstance);
        return transientInstance.getChartId();
    }
    public Integer updateChartEntity(ChartEntity transientInstance) throws DataAccessException{
        entityManager.merge(transientInstance);
        return transientInstance.getChartId();
    }
    public Integer deleteChartEntity(ChartEntity persistentInstance) throws DataAccessException{
        int deletedCount = 0;
        deletedCount = entityManager.createQuery(
                String.format("delete from  ChartEntity where chartId=%d", persistentInstance.getChartId()))
                .executeUpdate();
        return deletedCount;
    }
    public ChartEntity findChartEntityById(Integer chartId) throws DataAccessException{
        return entityManager.find(ChartEntity.class, chartId);
    }

    public List listChartEntity(th.ac.kmutt.chart.model.ChartM param)throws DataAccessException{
        StringBuffer sb = new StringBuffer(" select p from ChartEntity p  where 1=1 ");

        String activeFlag = param.getActiveFlag();
        String chartType = param.getChartType();

        if (activeFlag != null ) {
            sb.append(" and p.activeFlag='" + activeFlag + "' ");
        }
        if (chartType != null ) {
            sb.append(" and p.chartType='" + chartType + "' ");
        }
        sb.append(" order by p.chartName ");
        Query query = entityManager.createQuery(sb.toString(), ChartEntity.class);
        return query.getResultList();
    }

    //CHART_FEATURE
    public Integer saveChartFeatureEntity(ChartFeatureEntity transientInstance) throws DataAccessException{
        entityManager.persist(transientInstance);
        return transientInstance.getChartId();
    }
    public Integer updateChartFeatureEntity(ChartFeatureEntity transientInstance) throws DataAccessException{
        entityManager.merge(transientInstance);
        return transientInstance.getChartId();
    }
    public Integer deleteChartFeatureEntity(ChartFeatureEntity persistentInstance) throws DataAccessException{
        int deletedCount = 0;
        deletedCount = entityManager.createQuery(
                new StringBuilder().
                        append("delete from ChartFeatureEntity where researchGroupId=").append(persistentInstance.getChartId()).toString())
                .executeUpdate();
        return deletedCount;
    }
    public ChartFeatureEntity findChartFeatureEntityById(Integer chartId) throws DataAccessException{
        return entityManager.find(ChartFeatureEntity.class, chartId);
    }

    //CHART_FEATURE_INSTANCE
    public Integer saveChartFeatureInstanceEntity(ChartFeatureInstanceEntity transientInstance) throws DataAccessException{
        entityManager.persist(transientInstance);
        return transientInstance.getChartId();
    }
    public Integer updateChartFeatureInstanceEntity(ChartFeatureInstanceEntity transientInstance) throws DataAccessException{
        entityManager.merge(transientInstance);
        return transientInstance.getChartId();
    }
    public Integer deleteChartFeatureInstanceEntity(ChartFeatureInstanceEntity persistentInstance) throws DataAccessException{
        int deletedCount = 0;
        deletedCount = entityManager.createQuery(
                new StringBuilder().append("delete from ChartFeatureInstanceEntity where instanceId=").
                        append(persistentInstance.getInstanceId()).toString())
                .executeUpdate();
        return deletedCount;
    }
    public ChartFeatureInstanceEntity findChartFeatureInstanceEntityById(String instanceId) throws DataAccessException{
        return entityManager.find(ChartFeatureInstanceEntity.class, instanceId);
    }

    //CHART_FEATURE_MAPPIG
    public Integer saveChartFeatureMappingEntity(ChartFeatureMappingEntity transientInstance) throws DataAccessException{
        entityManager.persist(transientInstance);
        return transientInstance.getId().getChartId();
    }
    public Integer updateChartFeatureMappingEntity(ChartFeatureMappingEntity transientInstance) throws DataAccessException{
        entityManager.merge(transientInstance);
        return transientInstance.getId().getChartId();
    }
    public Integer deleteChartFeatureMappingEntity(ChartFeatureMappingEntity persistentInstance) throws DataAccessException{
        int deletedCount = 0;
        deletedCount = entityManager.createQuery(
                new StringBuilder().append("delete from ChartFeatureMappingEntity where id=:id").toString())
                .setParameter("id", persistentInstance.getId())
                .executeUpdate();
        return deletedCount;
    }
    public ChartFeatureMappingEntity findChartFeatureMappingEntityById(ChartFeatureMappingEntityPK id) throws DataAccessException{
        return entityManager.find(ChartFeatureMappingEntity.class, id);
    }

    //CHART_FILTER_INSTANCE
    public Integer saveChartFilterInstanceEntity(ChartFilterInstanceEntity transientInstance) throws DataAccessException {
        entityManager.persist(transientInstance);
        return transientInstance.getId().getFilterId();
    }
    public Integer updateChartFilterInstanceEntity(ChartFilterInstanceEntity transientInstance) throws DataAccessException{
        entityManager.merge(transientInstance);
        return transientInstance.getId().getFilterId();
    }
    public Integer deleteChartFilterInstanceEntity(ChartFilterInstanceEntity persistentInstance) throws DataAccessException{
        int deletedCount = 0;
        deletedCount = entityManager.createQuery(
                new StringBuilder().append("delete from ChartFilterInstanceEntity where id.instanceId=:instanceId ").toString())
                .setParameter("instanceId",persistentInstance.getId().getInstanceId())
                .executeUpdate();
        return deletedCount;

    }
    public ChartFilterInstanceEntity findChartFilterInstanceEntityById(String instanceId) throws DataAccessException{
        return entityManager.find(ChartFilterInstanceEntity.class, instanceId);
    }

    //CHART_INSTANCE
    public Integer saveChartInstanceEntity(ChartInstanceEntity transientInstance) throws DataAccessException{
        /*if(transientInstance.getCommentByInstanceId()!=null) {
            logger.info("transientInstance getCommentByInstanceId id->" + transientInstance.getCommentByInstanceId().getInstanceId()+"x");
            logger.info("transientInstance getCommentByInstanceId comment->" + transientInstance.getCommentByInstanceId().getComment()+"x");
        }*/
        entityManager.persist(transientInstance);
    //    if(transientInstance.getCommentByInstanceId()!=null)
    //        entityManager.persist(transientInstance.getCommentByInstanceId());
      return 1;
    }
    public Integer updateChartInstanceEntity(ChartInstanceEntity transientInstance) throws DataAccessException{
    	entityManager.merge(transientInstance);
    //    if(transientInstance.getCommentByInstanceId()!=null && transientInstance.getCommentByInstanceId().getInstanceId()!=null
     //           && transientInstance.getCommentByInstanceId().getInstanceId().trim().length()>0)
      //      entityManager.merge(transientInstance.getCommentByInstanceId());
        return 1;
    }
    public Integer deleteChartInstanceEntity(ChartInstanceEntity persistentInstance) throws DataAccessException{
        int deletedCount = 0;
        deletedCount = entityManager.createQuery(
                new StringBuilder().append("delete from ChartInstanceEntity where instanceId=:instanceId").toString())
                .setParameter("instanceId",persistentInstance.getInstanceId())
                .executeUpdate();
        return deletedCount;
    }
    public ChartInstanceEntity findChartInstanceEntityById(String instanceId) throws DataAccessException{
        Query query=null;
        StringBuffer sb=new StringBuffer("select p from ChartInstanceEntity p where p.instanceId=:instanceId ");

        query = entityManager.createQuery(sb.toString(), ChartInstanceEntity.class);
        query.setParameter("instanceId",instanceId);
        ChartInstanceEntity result_return=null;
        List resultList=query.getResultList();
        if(resultList!=null && resultList.size()>0)
            result_return=(ChartInstanceEntity)resultList.get(0);
        return result_return;
      //  return entityManager.find(ChartInstanceEntity.class, instanceId);
    }
    public List listChartFilterInstanceEntity(th.ac.kmutt.chart.model.ChartFilterInstanceM param)throws DataAccessException {
        StringBuffer sb = new StringBuffer(" select p from ChartFilterInstanceEntity p  where 1=1 ");
        Integer serviceId = param.getServiceId();
        Integer filterId = param.getFilterId();
        String instanceId= param.getInstanceId();
        if (instanceId != null) {
            sb.append(" and p.id.instanceId='" + instanceId + "' ");
        }
        if (serviceId != null) {
            sb.append(" and p.serviceId=" + serviceId + " ");
        }
        if (filterId != null) {
            sb.append(" and p.id.filterId=" + filterId + " ");
        }
        Query query = entityManager.createQuery(sb.toString(), ChartFilterInstanceEntity.class);
        return query.getResultList();
    }

    //COMMENT
    public Integer saveCommentEntity(CommentEntity transientInstance) throws DataAccessException{
        entityManager.persist(transientInstance);
        return 1;
    }
    public Integer updateCommentEntity(CommentEntity transientInstance) throws DataAccessException{
        entityManager.merge(transientInstance);
        return 1;
    }
    public Integer deleteCommentEntity(CommentEntity persistentInstance) throws DataAccessException{
        int deletedCount = 0;
        deletedCount = entityManager.createQuery(
                new StringBuilder().append("delete from CommentEntity where instanceId=:instanceId").toString())
                .setParameter("instanceId",persistentInstance.getInstanceId())
                .executeUpdate();
        return deletedCount;
    }
    public CommentEntity findCommentEntityById(String instanceId) throws DataAccessException{
        return entityManager.find(CommentEntity.class, instanceId);
    }

    //FEATURE
    public Integer saveFeatureEntity(FeatureEntity transientInstance) throws DataAccessException {
        entityManager.persist(transientInstance);
        return transientInstance.getFeatureId();
    }
    public Integer updateFeatureEntity(FeatureEntity transientInstance) throws DataAccessException{
        entityManager.merge(transientInstance);
        return transientInstance.getFeatureId();
    }
    public Integer deleteFeatureEntity(FeatureEntity persistentInstance) throws DataAccessException{
        int deletedCount = 0;
        deletedCount = entityManager.createQuery(
                new StringBuilder().append("delete from FeatureEntity where featureId=:featureId").toString())
                .setParameter("featureId",persistentInstance.getFeatureId())
                .executeUpdate();
        return deletedCount;
    }
    public FeatureEntity findFeatureEntityById(Integer featureId) throws DataAccessException{
        return entityManager.find(FeatureEntity.class, featureId);
    }

    //FILTER
    public Integer saveFilterEntity(FilterEntity transientInstance) throws DataAccessException {
    	entityManager.persist(transientInstance);
        return transientInstance.getFilterId();
    }
    public Integer updateFilterEntity(FilterEntity transientInstance) throws DataAccessException{
        entityManager.merge(transientInstance);
        return transientInstance.getFilterId();
    }
    public Integer deleteFilterEntity(FilterEntity persistentInstance) throws DataAccessException{
        int deletedCount = 0;
        deletedCount = entityManager.createQuery(
                new StringBuilder().append("delete from FilterEntity where filterId=:filterId").toString())
                .setParameter("filterId",persistentInstance.getFilterId())
                .executeUpdate();
        return deletedCount;
    }
    public FilterEntity findFilterEntityById(Integer filterId) throws DataAccessException{
        return entityManager.find(FilterEntity.class, filterId);
    }
    public FilterEntity getFilterValueList(Integer filterId) throws DataAccessException{
    	FilterEntity ret = new FilterEntity();
    	try{
    		ret  = entityManager.find(FilterEntity.class, filterId);
   // 		ret  = entityManager.find(FilterValueEntity.class, filterId);
    	}catch(Exception ex){
    		ret = new FilterEntity();
    	}
    	return ret;
    }
    public Integer findFilterIdByFilterName(String FilterName){
    	String sql = "select filter_id from FILTER where filter_name = '"+FilterName+"'";
    	Query query = 		entityManager.createNativeQuery(sql);
    	Object result = query.getSingleResult();
    	return (Integer) result;
    }
    // FILTER MAPPING 
    public void saveFilterMapping(FilterMappingEntity entity ) throws Exception {
    	entityManager.persist(entity);
    	entityManager.flush();
    }
    public void deleteFilterMappingByFilterId(Integer filterId){
    	 StringBuffer sb = new StringBuffer();
    	 sb.append(" delete from FilterMappingEntity where filterId = :filterId");
    	 Query query = entityManager.createQuery(sb.toString());
    	 query.setParameter("filterId", filterId);
    	 query.executeUpdate();
    	 entityManager.flush();
    }
    //FILTER_INSTANCE
    public Integer saveFilterInstanceEntity(FilterInstanceEntity transientInstance) throws DataAccessException {
        entityManager.persist(transientInstance);
        return transientInstance.getId().getFilterId();
    }
    public Integer updateFilterInstanceEntity(FilterInstanceEntity transientInstance) throws DataAccessException{
        entityManager.merge(transientInstance);
        return transientInstance.getId().getFilterId();
    }
    public Integer deleteFilterInstanceEntity(FilterInstanceEntity persistentInstance) throws DataAccessException{
        int deletedCount = 0;
        deletedCount = entityManager.createQuery(
                new StringBuilder().append("delete from FilterInstanceEntity where id.instanceId=:instanceId").toString())
                .setParameter("instanceId",persistentInstance.getId().getInstanceId())
                .executeUpdate();
        return deletedCount;
    }
    public FilterInstanceEntity findFilterInstanceEntityById(String instanceId) throws DataAccessException{
        return entityManager.find(FilterInstanceEntity.class, instanceId);
    }

    //SERVICE_CHART_MAPPING
    public Integer saveServiceChartMappingEntity(ServiceChartMappingEntity transientInstance) throws DataAccessException{
        entityManager.persist(transientInstance);
        return transientInstance.getId().getChartId();
    }
    public Integer updateServiceChartMappingEntity(ServiceChartMappingEntity transientInstance) throws DataAccessException{
        entityManager.merge(transientInstance);
        return transientInstance.getId().getChartId();
    }
    public Integer deleteServiceChartMappingEntity(ServiceChartMappingEntity persistentInstance) throws DataAccessException{
        int deletedCount = 0;
        deletedCount = entityManager.createQuery(
                new StringBuilder().append("delete from ServiceChartMappingEntity where id=:id").toString())
                .setParameter("id",persistentInstance.getId())
                .executeUpdate();
        return deletedCount;
    }
    public ServiceChartMappingEntity findServiceChartMappingEntityById(ServiceChartMappingEntityPK id) throws DataAccessException{
        return entityManager.find(ServiceChartMappingEntity.class, id);
    }

    //SERVICE
    public Integer saveServiceEntity(ServiceEntity transientInstance) throws DataAccessException {
    	 entityManager.persist(transientInstance);
        return transientInstance.getServiceId();
    }
    public Integer updateServiceEntity(ServiceEntity transientInstance) throws DataAccessException{
    	ServiceEntity object = entityManager.merge(transientInstance);
        return object.getServiceId();
    }
    public Integer deleteServiceEntity(ServiceEntity persistentInstance) throws DataAccessException{
        int deletedCount = 0;
        deletedCount = entityManager.createQuery(
                new StringBuilder().append("delete from ServiceEntity where serviceId=:serviceId").toString())
                .setParameter("serviceId",persistentInstance.getServiceId())
                .executeUpdate();
        return deletedCount;
    }
    public ServiceEntity findServiceEntityById(Integer serviceId) throws DataAccessException{
    	ServiceEntity s = entityManager.find(ServiceEntity.class, serviceId);
    	  return s;
    }

    //SERVICE
    public Integer saveServiceFilterMappingEntity(ServiceFilterMappingEntity transientInstance) throws DataAccessException {
        entityManager.persist(transientInstance);
        return transientInstance.getId().getFilterId();
    }
    public Integer updateServiceFilterMappingEntity(ServiceFilterMappingEntity transientInstance) throws DataAccessException{
        entityManager.merge(transientInstance);
        return transientInstance.getId().getFilterId();
    }
    public Integer deleteServiceFilterMappingEntity(ServiceFilterMappingEntity persistentInstance) throws DataAccessException{
        int deletedCount = 0;
        deletedCount = entityManager.createQuery(
                new StringBuilder().append("delete from ServiceFilterMappingEntity where id=:id").toString())
                .setParameter("id",persistentInstance.getId())
                .executeUpdate();
        return deletedCount;
    }
    public Integer deleteServiceFilterByService(Integer serviceId){
    	StringBuffer sb = new StringBuffer();
    	sb.append("delete from SERVICE_FILTER_MAPPING where service_id = :serviceId ");
    	 Query query  = entityManager.createNativeQuery(sb.toString());
    	 query.setParameter("serviceId", serviceId);
    	Integer deletedCount =  query.executeUpdate();
    	return deletedCount;
    }
    public ServiceFilterMappingEntity findServiceFilterMappingEntityById(ServiceFilterMappingEntityPK id) throws DataAccessException{
        return entityManager.find(ServiceFilterMappingEntity.class, id);
    }

    public Integer deleteServiceChartByService(Integer serviceId){
    	StringBuffer sb = new StringBuffer();
    	sb.append("delete from SERVICE_CHART_MAPPING where service_id = :serviceId ");
    	 Query query  = entityManager.createNativeQuery(sb.toString());
    	 query.setParameter("serviceId", serviceId);
    	Integer deletedCount =  query.executeUpdate();
    	return deletedCount;
    }
    
    public Integer deleteServiceUserByService(Integer serviceId){
    	StringBuffer sb = new StringBuffer();
    	sb.append("delete from SERVICE_USER_MAPPING where service_id = :serviceId ");
    	 Query query  = entityManager.createNativeQuery(sb.toString());
    	 query.setParameter("serviceId", serviceId);
    	Integer deletedCount =  query.executeUpdate();
    	return deletedCount;
    }
    
    /*INBOUND_OUTBOUND_STUDENT
	public List InternationalCompareAllStudent(InBoundOutBoundServiceM param) throws DataAccessException {
		Query query = entityManagerDwh.createNativeQuery(		
	
				"SELECT ROUND(DECIMAL(DECIMAL(INB.NOOFSTUDENT)/AL.NO_OF_STUDENT *100,10,2),2) AS NoOf \n"
					+"FROM ( SELECT ACADEMIC_YEAR, SUM(FACT_INBOUND_OUTBOUND_STUDENT.NO_OF_STUDENT) AS NOOFSTUDENT\n"
							 +"FROM FACT_INBOUND_OUTBOUND_STUDENT\n"
							 +"INNER JOIN DIM_FIELD ON  FACT_INBOUND_OUTBOUND_STUDENT.FIELD_KEY = DIM_FIELD.FIELD_KEY\n"
							+"WHERE IN_OUT_TYPE_KEY = '2'\n"
							+"AND NATIONALITY <> 'Thai'\n"
							+"AND ACADEMIC_YEAR = :paramYear \n"
							+"AND( DIM_FIELD.FACULTY_CODE = :paramFaculty OR 'ALL' = :paramFaculty)\n"
							+"AND( DIM_FIELD.DEPARTMENT_CODE = :paramDepartment OR 'ALL' = :paramDepartment) \n"
							+"GROUP BY ACADEMIC_YEAR\n"
							+")INB\n"
					+"INNER JOIN (SELECT DS.ACADEMIC_YEAR,\n"
									+"SUM(NO_OF_STUDENT) AS NO_OF_STUDENT\n"
									+"FROM FACT_ALL_STUDENT FAS\n"
									+"INNER JOIN DIM_SEMESTER DS ON  FAS.SEMESTER_KEY = DS.SEMESTER_KEY\n"
									+"INNER JOIN DIM_FIELD DF ON FAS.FIELD_KEY =DF.FIELD_KEY\n"
									+"WHERE DS.ACADEMIC_YEAR = :paramYear \n"
									+"AND (DF.FACULTY_CODE = :paramFaculty OR 'ALL' =:paramFaculty)\n"
									+"AND (DF.DEPARTMENT_CODE = :paramDepartment OR 'ALL' = :paramDepartment)\n"
									+"AND LEFT(DS.SEMESTER,1) = '1'\n"
									+"GROUP BY ACADEMIC_YEAR )AL on INB.ACADEMIC_YEAR = AL.ACADEMIC_YEAR\n"
				);
		query.setParameter("paramYear", param.getAcademicYear());
		query.setParameter("paramFaculty", param.getFacultyCode());
		query.setParameter("paramDepartment",param.getDepartmentCode());
		List<BigDecimal> results = query.getResultList();
		 
		List<InboundOutboundStudent> inboundOutboundStudents = new ArrayList<InboundOutboundStudent>();
		for (BigDecimal result : results) {	
			InboundOutboundStudent inboundOutboundStudent = new InboundOutboundStudent();
			inboundOutboundStudent.setNoOf((BigDecimal) result);
	
			
			inboundOutboundStudents.add(inboundOutboundStudent);
			
		}
		 		 
		 return inboundOutboundStudents;
	}
	
	
	public List EmpInternationalCompareAllEmp(InBoundOutBoundServiceM param) throws DataAccessException {
		Query query = entityManagerDwh.createNativeQuery(		
	
				"SELECT ROUND(DECIMAL(DECIMAL(HR.NOOFEMPIN)/INB.NOOFEMP *100,10,1),1) AS NoOf\n"
				+"FROM ( SELECT DD.ACADAMIC_YEAR, count(HR_FACT_EMPLOYEE.emp_key) AS NOOFEMP\n"
									+"FROM HR_FACT_EMPLOYEE \n"
									+"INNER JOIN DIM_DATE DD ON  HR_FACT_EMPLOYEE.MONTH_KEY = DD.DATE_KEY\n"
									+"INNER JOIN HR_DIM_DEPARTMENT ON HR_FACT_EMPLOYEE.DEPARTMENT_KEY = HR_DIM_DEPARTMENT.DEPARTMENT_KEY\n"
									+"WHERE  HR_FACT_EMPLOYEE.MONTH_KEY = (SELECT Max (HFE.MONTH_KEY)\n"
																			+"FROM HR_FACT_EMPLOYEE HFE \n"
																			+"INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n"
																			+"WHERE DD.CALENDAR_YEAR = :paramYear\n"  
																			+")\n"
	                                +"AND (HR_DIM_DEPARTMENT.ORGENIZATION_CODE = :paramFaculty OR 'ALL' = :paramFaculty)\n"
	                                +"AND (HR_DIM_DEPARTMENT.DEPARTMENT_CODE = :paramDepartment OR 'ALL' = :paramDepartment)\n"
	                                +"group by DD.ACADAMIC_YEAR )INB\n"
					+"INNER JOIN (SELECT DD.ACADAMIC_YEAR, count(HR_FACT_EMPLOYEE.emp_key) AS NOOFEMPIN\n"
									+"FROM HR_FACT_EMPLOYEE \n"
									+"INNER JOIN DIM_DATE DD ON  HR_FACT_EMPLOYEE.MONTH_KEY = DD.DATE_KEY\n"
									+"INNER JOIN HR_DIM_DEPARTMENT ON HR_FACT_EMPLOYEE.DEPARTMENT_KEY = HR_DIM_DEPARTMENT.DEPARTMENT_KEY\n"
									+"WHERE  HR_FACT_EMPLOYEE.MONTH_KEY = (SELECT Max (HFE.MONTH_KEY)\n"
																			+"FROM HR_FACT_EMPLOYEE HFE \n"
																			+"INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n"
																			+"WHERE DD.CALENDAR_YEAR = :paramYear\n" 
																			+")\n"
									+"AND HR_FACT_EMPLOYEE.NATIONALITY_KEY <> 100\n"
	                                +"AND (HR_DIM_DEPARTMENT.ORGENIZATION_CODE = :paramFaculty OR 'ALL' = :paramFaculty)\n"
	                                +"AND (HR_DIM_DEPARTMENT.DEPARTMENT_CODE = :paramDepartment OR 'ALL' = :paramDepartment)\n"
									+"group by DD.ACADAMIC_YEAR )HR on INB.ACADAMIC_YEAR = HR.ACADAMIC_YEAR\n"
				);
		query.setParameter("paramYear", param.getAcademicYear());
		query.setParameter("paramFaculty", param.getFacultyCode());
		query.setParameter("paramDepartment",param.getDepartmentCode());
		List<BigDecimal> results = query.getResultList();
		 
		List<InboundOutboundStudent> inboundOutboundStudents = new ArrayList<InboundOutboundStudent>();
		for (BigDecimal result : results) {	
			InboundOutboundStudent inboundOutboundStudent = new InboundOutboundStudent();
			inboundOutboundStudent.setNoOf((BigDecimal) result);
			
			inboundOutboundStudents.add(inboundOutboundStudent);
			
		}
		 		 
		 return inboundOutboundStudents;
	}
	
	
	public List InternationalCompareAllStudentProgramInter(InBoundOutBoundServiceM param) throws DataAccessException {
		Query query = entityManagerDwh.createNativeQuery(		
	
				"SELECT ROUND(DECIMAL(DECIMAL(INB.NO_OF_INTER)/AL.NO_OF_ALL *100,10,2),2) AS NoOf \n"
					+"FROM ( SELECT ACADEMIC_YEAR, SUM(FACT_INBOUND_OUTBOUND_STUDENT.NO_OF_STUDENT) AS NO_OF_INTER\n"
							+" FROM FACT_INBOUND_OUTBOUND_STUDENT\n"
							 +"INNER JOIN DIM_FIELD ON  FACT_INBOUND_OUTBOUND_STUDENT.FIELD_KEY = DIM_FIELD.FIELD_KEY\n"
	                         +"WHERE IN_OUT_TYPE_KEY = '2'\n"
							 +"AND NATIONALITY <> 'Thai'\n"
							 +"AND ACADEMIC_YEAR = :paramYear \n"
							 +"AND( DIM_FIELD.FACULTY_CODE = :paramFaculty  OR 'ALL' = :paramFaculty )\n"
							 +"AND( DIM_FIELD.DEPARTMENT_CODE = :paramDepartment OR 'ALL' = :paramDepartment) \n"
							 +"GROUP BY ACADEMIC_YEAR\n"
							 +")INB\n"
					+"INNER JOIN (SELECT DS.ACADEMIC_YEAR,\n"
									+"SUM(FAS.NO_OF_STUDENT) AS NO_OF_ALL\n"
									+"FROM FACT_ALL_STUDENT FAS\n"
									+"INNER JOIN DIM_SEMESTER DS ON  FAS.SEMESTER_KEY = DS.SEMESTER_KEY\n"
									+"INNER JOIN DIM_FIELD DF ON FAS.FIELD_KEY =DF.FIELD_KEY\n"
	                                +"INNER JOIN DIM_PROGRAM DP ON FAS.PROGRAM_KEY = DP.PROGRAM_KEY\n"
									+"WHERE DS.ACADEMIC_YEAR = :paramYear \n"
									+"AND (DF.FACULTY_CODE = :paramFaculty  OR 'ALL' = :paramFaculty )\n"
									+"AND (DF.DEPARTMENT_CODE = :paramDepartment  OR 'ALL' = :paramDepartment)\n"
									+"AND LEFT(DS.SEMESTER,1) = '1'\n"
	                                +"AND DP.ARRANGE_TYPE = 'หลักสูตรนานาชาติ'\n"
									+"GROUP BY ACADEMIC_YEAR\n"
									+")AL on INB.ACADEMIC_YEAR = AL.ACADEMIC_YEAR\n"
									) ;
		query.setParameter("paramYear", param.getAcademicYear());
		query.setParameter("paramFaculty", param.getFacultyCode());
		query.setParameter("paramDepartment",param.getDepartmentCode());
		List<BigDecimal> results = query.getResultList();
		 
		List<InboundOutboundStudent> inboundOutboundStudents = new ArrayList<InboundOutboundStudent>();
		for (BigDecimal result : results) {	
			InboundOutboundStudent inboundOutboundStudent = new InboundOutboundStudent();
			inboundOutboundStudent.setNoOf((BigDecimal) result);
			
			inboundOutboundStudents.add(inboundOutboundStudent);
			
		}
		 		 
		 return inboundOutboundStudents;
	}
	
	
	public List ProgramInternationalCompareAllProgram(InBoundOutBoundServiceM param) throws DataAccessException {
		Query query = entityManagerDwh.createNativeQuery(		
	
				"SELECT ROUND(DECIMAL(DECIMAL(INB.NO_OF_INTER)/AL.NO_OF_ALL *100,10,2),2) AS NoOf\n"
						+"FROM (SELECT DS.ACADEMIC_YEAR,\n"
								+"COUNT(dISTINCT DP.PROGRAM_CODE) AS NO_OF_INTER\n"
								+"FROM FACT_ALL_STUDENT FAS\n"
								+"INNER JOIN DIM_SEMESTER DS ON  FAS.SEMESTER_KEY = DS.SEMESTER_KEY\n"
								+"INNER JOIN DIM_FIELD DF ON FAS.FIELD_KEY =DF.FIELD_KEY\n"
								+"INNER JOIN DIM_PROGRAM DP ON FAS.PROGRAM_KEY = DP.PROGRAM_KEY\n"
								+"WHERE DS.ACADEMIC_YEAR = :paramYear \n"
								+"AND (DF.FACULTY_CODE = :paramFaculty OR 'ALL' = :paramFaculty)\n"
								+"AND (DF.DEPARTMENT_CODE = :paramDepartment OR 'ALL' = :paramDepartment)\n"
								+"AND LEFT(DS.SEMESTER,1) = '1'\n"
								+"AND DP.ARRANGE_TYPE = 'หลักสูตรนานาชาติ'\n"
								+"GROUP BY ACADEMIC_YEAR \n"
								+")INB\n"
						+"INNER JOIN (SELECT DS.ACADEMIC_YEAR,\n"
									+"COUNT(dISTINCT DP.PROGRAM_CODE) AS NO_OF_ALL\n"
									+"FROM FACT_ALL_STUDENT FAS\n"
									+"INNER JOIN DIM_SEMESTER DS ON  FAS.SEMESTER_KEY = DS.SEMESTER_KEY\n"
									+"INNER JOIN DIM_FIELD DF ON FAS.FIELD_KEY =DF.FIELD_KEY\n"
									+"INNER JOIN DIM_PROGRAM DP ON FAS.PROGRAM_KEY = DP.PROGRAM_KEY\n"
									+"WHERE DS.ACADEMIC_YEAR = :paramYear \n"
									+"AND (DF.FACULTY_CODE = :paramFaculty OR 'ALL' = :paramFaculty)\n"
									+"AND (DF.DEPARTMENT_CODE = :paramDepartment OR 'ALL' = :paramDepartment)\n"
									+"AND LEFT(DS.SEMESTER,1) = '1'\n"
									+"GROUP BY ACADEMIC_YEAR )AL on INB.ACADEMIC_YEAR = AL.ACADEMIC_YEAR\n"
				);
		query.setParameter("paramYear", param.getAcademicYear());
		query.setParameter("paramFaculty", param.getFacultyCode());
		query.setParameter("paramDepartment",param.getDepartmentCode());
		List<BigDecimal> results = query.getResultList();
		 
		List<InboundOutboundStudent> inboundOutboundStudents = new ArrayList<InboundOutboundStudent>();
		for (BigDecimal result : results) {	
			InboundOutboundStudent inboundOutboundStudent = new InboundOutboundStudent();
			inboundOutboundStudent.setNoOf((BigDecimal) result);
			
			inboundOutboundStudents.add(inboundOutboundStudent);
			
		}
		 		 
		 return inboundOutboundStudents;
	}
	
	public List InternationalCompareAllStudentByFaculty(InBoundOutBoundServiceM param) throws DataAccessException {
		Query query = entityManagerDwh.createNativeQuery(		
	
				"SELECT INB.FACULTY_NAME_INITIAL,INB.NO_OF_INTER,AL.NO_OF_ALL\n"
				+"FROM ( SELECT ACADEMIC_YEAR,\n"
	                         +"DIM_FACULTY.FACULTY_NAME_INITIAL, \n"
	                         +"SUM(FACT_INBOUND_OUTBOUND_STUDENT.NO_OF_STUDENT) AS NO_OF_INTER\n"
	                         +"FROM FACT_INBOUND_OUTBOUND_STUDENT\n"
	                         +"INNER JOIN DIM_FACULTY ON  FACT_INBOUND_OUTBOUND_STUDENT.FACULTY_KEY = DIM_FACULTY.FACULTY_KEY\n"
	                         +"INNER JOIN DIM_FIELD ON  FACT_INBOUND_OUTBOUND_STUDENT.FIELD_KEY = DIM_FIELD.FIELD_KEY\n"
	                         +"WHERE IN_OUT_TYPE_KEY = '2'\n"
	                         +"AND NATIONALITY <> 'Thai'\n"
	                         +"AND ACADEMIC_YEAR = :paramYear \n"
	                         +"AND( DIM_FIELD.FACULTY_CODE = :paramFaculty  OR 'ALL' = :paramFaculty)\n"
	                         +"AND( DIM_FIELD.DEPARTMENT_CODE = :paramDepartment  OR 'ALL' = :paramDepartment) \n"
	                         +"GROUP BY ACADEMIC_YEAR, DIM_FACULTY.FACULTY_NAME_INITIAL \n"
	                         +")INB\n"
	          +"INNER JOIN (SELECT DS.ACADEMIC_YEAR,DC.FACULTY_NAME_INITIAL,\n"
	          				+"SUM(FAS.NO_OF_STUDENT) AS NO_OF_ALL\n"
	          				+"FROM FACT_ALL_STUDENT FAS\n"
	          				+"INNER JOIN DIM_SEMESTER DS ON  FAS.SEMESTER_KEY = DS.SEMESTER_KEY\n"
	          				+"INNER JOIN DIM_FIELD DF ON FAS.FIELD_KEY =DF.FIELD_KEY\n"
	          				+"INNER JOIN DIM_FACULTY DC ON DF.FACULTY_CODE = DC.FACULTY_CODE\n"
	          				+"WHERE DS.ACADEMIC_YEAR = :paramYear\n" 
	          				+"AND (DF.FACULTY_CODE = :paramFaculty  OR 'ALL' = :paramFaculty)\n"
	          				+"AND (DF.DEPARTMENT_CODE = :paramDepartment  OR 'ALL' = :paramDepartment)\n"
	          				+"AND LEFT(DS.SEMESTER,1) = '1'  \n"
	          				+"GROUP BY DS.ACADEMIC_YEAR,DC.FACULTY_NAME_INITIAL\n"
	          				+")AL on INB.ACADEMIC_YEAR = AL.ACADEMIC_YEAR AND AL.FACULTY_NAME_INITIAL = INB.FACULTY_NAME_INITIAL\n"
						) ;
		query.setParameter("paramYear", param.getAcademicYear());
		query.setParameter("paramFaculty", param.getFacultyCode());
		query.setParameter("paramDepartment",param.getDepartmentCode());
		List<Object[]> results = query.getResultList();
		 
		List<InBoundOutBoundServiceM> inboundOutboundStudents = new ArrayList<InBoundOutBoundServiceM>();
		for (Object[] result : results) {	
			InBoundOutBoundServiceM inBoundOutBoundServiceM = new InBoundOutBoundServiceM();
			inBoundOutBoundServiceM.setShortFaculty((String) result[0]);
			inBoundOutBoundServiceM.setnoOfInter((Integer) result[1]);
			inBoundOutBoundServiceM.setnoOfAll((Integer) result[2]);
			
			inBoundOutBoundServiceM.setPaging(null);
			inboundOutboundStudents.add(inBoundOutBoundServiceM);
			
		}
		 return inboundOutboundStudents;
	}
	
	
	public List InternationalCompareAllEmpByFaculty(InBoundOutBoundServiceM param) throws DataAccessException {
		Query query = entityManagerDwh.createNativeQuery(		
	
				"SELECT INB.FACULTY_NAME_INITIAL as ShortFaculty,INB.noOfInter,AL.noOfAll \n"
					+"FROM ( SELECT DD.ACADAMIC_YEAR, \n"
					+"CASE \n"
					+" WHEN DIM_FACULTY.FACULTY_NAME_INITIAL IS NULL \n"
					+"THEN 'KMUTT'\n"
					+" ELSE DIM_FACULTY.FACULTY_NAME_INITIAL END FACULTY_NAME_INITIAL ,\n"
					+" count(HR_FACT_EMPLOYEE.emp_key) AS noOfInter\n"
					+"FROM HR_FACT_EMPLOYEE \n"
					+"INNER JOIN DIM_DATE DD ON  HR_FACT_EMPLOYEE.MONTH_KEY = DD.DATE_KEY\n"
					+"INNER JOIN HR_DIM_DEPARTMENT ON HR_FACT_EMPLOYEE.DEPARTMENT_KEY = HR_DIM_DEPARTMENT.DEPARTMENT_KEY\n"
									+"LEFT JOIN DIM_FACULTY ON  HR_DIM_DEPARTMENT.ORGENIZATION_CODE = DIM_FACULTY.FACULTY_CODE\n"
									+"WHERE  HR_FACT_EMPLOYEE.MONTH_KEY = (SELECT Max (HFE.MONTH_KEY)\n"
																			+"FROM HR_FACT_EMPLOYEE HFE \n"
																			+"INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n"
																			+"WHERE DD.CALENDAR_YEAR = :paramYear \n"
																			+")\n"
									+"AND (HR_DIM_DEPARTMENT.ORGENIZATION_CODE =  :paramFaculty OR 'ALL' =  :paramFaculty)\n"
									+"AND (HR_DIM_DEPARTMENT.DEPARTMENT_CODE =  :paramDepartment OR 'ALL' =  :paramDepartment )\n"
									+"AND  HR_FACT_EMPLOYEE.NATIONALITY_KEY <> 100\n"
									+"group by DD.ACADAMIC_YEAR,DIM_FACULTY.FACULTY_NAME_INITIAL )INB\n"
					+"INNER JOIN (SELECT DD.ACADAMIC_YEAR, \n"
									+"CASE \n"
									+"WHEN DIM_FACULTY.FACULTY_NAME_INITIAL IS NULL\n" 
									+"THEN 'KMUTT'\n"
									+"ELSE DIM_FACULTY.FACULTY_NAME_INITIAL END AS FACULTY_NAME_INITIAL ,\n"
									+"count(HR_FACT_EMPLOYEE.emp_key) AS noOfAll\n"
									+"FROM HR_FACT_EMPLOYEE \n"
									+"INNER JOIN DIM_DATE DD ON  HR_FACT_EMPLOYEE.MONTH_KEY = DD.DATE_KEY\n"
									+"INNER JOIN HR_DIM_DEPARTMENT ON HR_FACT_EMPLOYEE.DEPARTMENT_KEY = HR_DIM_DEPARTMENT.DEPARTMENT_KEY\n"
									+"LEFT JOIN DIM_FACULTY ON  HR_DIM_DEPARTMENT.ORGENIZATION_CODE = DIM_FACULTY.FACULTY_CODE\n"
									+"WHERE  HR_FACT_EMPLOYEE.MONTH_KEY = (SELECT Max (HFE.MONTH_KEY)\n"
																			+"FROM HR_FACT_EMPLOYEE HFE \n"
																			+"INNER JOIN DIM_DATE DD ON  HFE.MONTH_KEY = DD.DATE_KEY\n"
																			+"WHERE DD.CALENDAR_YEAR = :paramYear \n"
																			+")\n"
									+"AND (HR_DIM_DEPARTMENT.ORGENIZATION_CODE =  :paramFaculty OR 'ALL' =  :paramFaculty )\n"
									+"AND (HR_DIM_DEPARTMENT.DEPARTMENT_CODE =  :paramDepartment OR 'ALL' =  :paramDepartment )\n"
									+"group by DD.ACADAMIC_YEAR,DIM_FACULTY.FACULTY_NAME_INITIAL )AL on INB.ACADAMIC_YEAR = AL.ACADAMIC_YEAR  AND  INB.FACULTY_NAME_INITIAL = AL.FACULTY_NAME_INITIAL \n"
						) ;
		query.setParameter("paramYear", param.getAcademicYear());
		query.setParameter("paramFaculty", param.getFacultyCode());
		query.setParameter("paramDepartment",param.getDepartmentCode());
		List<Object[]> results = query.getResultList();
		 
		List<InBoundOutBoundServiceM> inboundOutboundStudents = new ArrayList<InBoundOutBoundServiceM>();
		for (Object[] result : results) {	
			InBoundOutBoundServiceM inBoundOutBoundServiceM = new InBoundOutBoundServiceM();
			inBoundOutBoundServiceM.setShortFaculty((String) result[0]);
			inBoundOutBoundServiceM.setnoOfInter((Integer) result[1]);
			inBoundOutBoundServiceM.setnoOfAll((Integer) result[2]);
			
			inBoundOutBoundServiceM.setPaging(null);
			inboundOutboundStudents.add(inBoundOutBoundServiceM);
			
		}
		 return inboundOutboundStudents;
	}
	*/
	
	

	@SuppressWarnings("unchecked")
	public List<Object[]> fetchFilterInstance(String instanceId){
		 // [filter_name,FILTER_NAME,value]
		String sql = "SELECT f.FILTER_ID,f.FILTER_NAME,f.title,f.value_type, coalesce( fi.VALUE , f.SUBSTITUTE_DEFAULT) as filter_value,f.SQL_QUERY,f.CONNECTION_ID "
				+ "	FROM FILTER_INSTANCE fi inner join  FILTER f on fi.FILTER_ID = f.FILTER_ID "
				+ "   WHERE fi.instance_id = '"+instanceId+"'";

		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultSet = query.getResultList();
		return resultSet;
	}
	public List<Object[]> fetchFilterInstanceWithItem(String instanceId){
		//  no try exception // fetch with ValueItem
		String sql = "SELECT f.filter_id,f.filter_name,f.title,f.value_type,f.sql_query,cfi.VALUE,f.CONNECTION_ID "
				+ "	FROM FILTER_INSTANCE cfi inner join FILTER f on cfi.FILTER_ID  = f.FILTER_ID "
				+ " where INSTANCE_ID =  '"+instanceId+"'";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> resultSet = query.getResultList();
		return resultSet;
	}
	/*
	public List<FilterValueM> fetchFilterValueCascade(Integer filterId,List<FilterM> filters){
		try{
			// normal filter
			FilterEntity fe = findFilterEntityById(filterId);
			Query query = connectDS(fe.getConnId()).createNativeQuery(fe.getSqlQuery());
			for( FilterM filter : filters ){
				
				Object value = transformSelectedValueToObject(filter.getValueType(),filter.getSelectedValue());	
				if(  fe.getSqlQuery().contains(":"+filter.getFilterName()) && !filter.getFilterId().equals(filterId) ){ 
					query.setParameter(filter.getFilterName(),value);
				}
			}
			List<Object[]> results = query.getResultList();
			List<FilterValueM> fvs = new ArrayList<FilterValueM>();
			
			for(Object[] result : results){
				fvs.add(buildFilterItems(result));
			}
			return fvs;
		}catch(Exception ex){
			System.out.println("exception: filterValue cascade error cause of filter id="+filterId+ "  cause="+ex);
			return  new ArrayList<FilterValueM>();
		}
	}*/
	public  List<FilterEntity> fetchGlobalFilter(){
		String sql = "select * from FILTER where global_flag = '1'";
		Query q = entityManager.createNativeQuery(sql,FilterEntity.class);
		List<FilterEntity> feList = q.getResultList();
		return feList;
	}
	public List<Object[]> fetchFilterOfService(Integer serviceId,List<FilterM>globalFilter,String userId) {
			String sql = "SELECT f.filter_id,filter_name,title,SUBSTITUTE_DEFAULT,f.SQL_QUERY,f.auto_fill_flag,f.CONNECTION_ID FROM SERVICE_FILTER_MAPPING sfm "
					+ " left join FILTER f on sfm.filter_id = f.filter_id  "
					+ " where   sfm.service_id = "+serviceId;
			Query q =  entityManager.createNativeQuery(sql);
			@SuppressWarnings("unchecked")
			List<Object[]> results = q.getResultList();
			return results;
	}
	public List<Object[]> fetchChartFilterInstance(ChartFilterInstanceM chartFilterInstance){
		String sql = "select fs.service_id,fs.FILTER_ID,FILTER_NAME,TITLE,IF(fi.FILTER_ID is null,'0','1') actFlag ,fs.SQL_QUERY"
				+ " ,IF(fi.value is null,fs.SUBSTITUTE_DEFAULT,fi.value) as default_value,fs.CONNECTION_ID "
				+ " from "
				+ " (select sfm.service_id,sfm.FILTER_ID,f.FILTER_NAME,f.TITLE,f.SUBSTITUTE_DEFAULT,f.SQL_QUERY,f.CONNECTION_ID "
				+ " from SERVICE_FILTER_MAPPING sfm INNER JOIN FILTER f on sfm.filter_id = f.filter_id  "
				+ " where sfm.service_id = "+chartFilterInstance.getServiceId()+" ) fs left join "
				+ " (select FILTER_ID,VALUE from FILTER_INSTANCE fi where instance_id = '"+chartFilterInstance.getInstanceId()+"'"
				+ " ) fi on fs.filter_id = fi.filter_id ";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> results = query.getResultList();
		return results;
	}
    public FilterInstanceM saveFilterInstance(FilterInstanceM fim){
    	for(FilterM filter : fim.getFilterList()){
    		FilterInstanceEntity fie = new FilterInstanceEntity();
	    	FilterInstanceEntityPK id = new FilterInstanceEntityPK();
	    	id.setInstanceId(fim.getInstanceId());
	    	id.setFilterId(filter.getFilterId());
	    	fie.setId(id);
	    	fie.setValue(filter.getSelectedValue());
	    	entityManager.persist(fie);
    	}
    	return fim;
    }
    public Integer deleteFilterInstance(String instanceId){
    	  int deletedCount = 0;
    	  String sql = "delete from FILTER_INSTANCE where INSTANCE_ID = '"+instanceId+"'";
    	  deletedCount = entityManager.createNativeQuery(sql).executeUpdate();
          return deletedCount;
    }
	public Integer updateFilterInstance(FilterInstanceM fim) {
		Integer successNo  = 0;
//		try{
			for(FilterM filter : fim.getFilterList()){
				FilterInstanceEntity fie = new FilterInstanceEntity();
				FilterInstanceEntityPK fiePK = new FilterInstanceEntityPK();
				fiePK.setFilterId(filter.getFilterId());
				fiePK.setInstanceId(fim.getInstanceId());
				fie.setId(fiePK);
				fie.setValue(filter.getSelectedValue());
				entityManager.merge(fie);
				successNo++;
			}
		/*}catch(Exception ex){
			System.out.println("test:exception");
			logger.info("service updateFilterInstance have exception :"+ex.getMessage());
		}*/
		return successNo;
	}
	public List<FilterM> findParamFilterMapping(Integer filterId){
			try{
			String sql = "SELECT f.FILTER_ID,f.FILTER_NAME,f.VALUE_TYPE,f.SUBSTITUTE_DEFAULT FROM FILTER_MAPPING fm "
					+ " left join FILTER f on fm.param_filter_id = f.filter_id "
					+ " where fm.filter_id = "+filterId;
			Query query = entityManager.createNativeQuery(sql);
			List<Object[]> results = query.getResultList();
			List<FilterM> filters = new ArrayList<FilterM>();
			for(Object[] result : results){
				FilterM filter = new FilterM();
				filter.setFilterId( (Integer)result[0] );
				filter.setFilterName((String)result[1]);
				filter.setValueType((String)result[2] );
				filter.setSelectedValue((String)result[3] );
				filters.add(filter);
			}
			return filters;
		}catch(Exception e){
			return new ArrayList<FilterM>();
		}
	}
	public List<FilterM> findFilterByParamMapping(Integer filterId){
		String sql = "SELECT f.FILTER_ID,f.FILTER_NAME,f.SUBSTITUTE_DEFAULT "
				+ " ,f.SQL_QUERY,f.CONNECTION_ID "
				+ " FROM FILTER_MAPPING fm "
				+ " left join FILTER f on fm.filter_id = f.filter_id "
				+ " where fm.param_filter_id = "+filterId;
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> results = query.getResultList();
		List<FilterM> filters = new ArrayList<FilterM>();
		for(Object[] result : results){
			FilterM filter = new FilterM();
			filter.setFilterId( (Integer)result[0] );
			filter.setFilterName((String)result[1]);
			filter.setSelectedValue((String)result[2] );
			filters.add(filter);
		}
		return filters;
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
	
	public List<ChartEntity> findChartByServiceId(Integer serviceId) throws Exception{
		String sql = "select c.* from SERVICE_CHART_MAPPING m , SERVICE s , CHART c  "
				+ " where m.SERVICE_ID = s.SERVICE_ID and m.CHART_ID = c.CHART_ID "
				+ " and s.SERVICE_ID = "+serviceId;
		Query query = entityManager.createNativeQuery(sql,ChartEntity.class);
	//	List<ChartEntity> a = query.getResultList();
	//	System.out.println("test$$$$");
		return query.getResultList();
	}
	
	public List<ServiceEntity> findServiceByUser(String userId) throws Exception{
		String sql = "select s.service_id,s.service_name from SERVICE_USER_MAPPING m , SERVICE s   "
				+ " where m.SERVICE_ID = s.SERVICE_ID  and m.USER_ID = "+userId;
		Query query = entityManager.createNativeQuery(sql,ServiceEntity.class);
		return query.getResultList();
	}
	
	public List<UserM> listUser() throws Exception{
		try{
			List<UserM>  userList = new ArrayList<UserM>();
			String fname = getConstant("authen");
			Query query = entityManager.createNativeQuery(" select * from FILTER where filter_name = '"+fname+"'",FilterEntity.class);
			FilterEntity datasource = (FilterEntity) query.getResultList().get(0);
				
				query = portalEntityManager.createNativeQuery(datasource.getSqlQuery());
				List<Object[]> results = query.getResultList();
				for(Object[] row : results ){
					UserM user = new UserM();
					user.setUserId(String.valueOf(row[0]));
					user.setUsername(String.valueOf(row[1]));
					userList.add(user);
				}		
				return userList;
		}catch(Exception e){
				logger.error(" user retrive fail reason "+e);
				return new ArrayList<UserM>();
		}
	}
	
	public List<UserM> findUserByService(Integer serviceId) throws Exception{
		List<UserM>  returnUser = new ArrayList<UserM>();
		
		Query query = entityManager.createNativeQuery("select SERVICE_ID,USER_ID from SERVICE_USER_MAPPING where service_id="+serviceId);
		List<Object[]> results =  query.getResultList();
		
		List<UserM>  allUser = listUser();
		
		for(Object[] row : results){
			for(UserM user : allUser){
				if( String.valueOf(row[1]).equalsIgnoreCase(user.getUserId()) ){
					returnUser.add(user);
				}
			}
		}
		return returnUser;
	}
	
	public Integer newConnection(DatasourceConnectionEntity e) {

		System.out.println("#newcon "+e.getConnName());
		entityManager.persist(e);

		System.out.println("#newcon "+e.getConnId());
		return e.getConnId();
	}
	public Integer updateConnection(DatasourceConnectionEntity e) throws Exception{
		System.out.println("#upcon "+e.getConnName());
		
		entityManager.merge(e);

		System.out.println("#upcon "+e.getConnId());
		return e.getConnId();
	}
	public Integer deleteConnection(Integer connId) throws Exception{
		 int deletedCount = 0;
	        deletedCount = entityManager.createQuery(
	                String.format("delete from  DatasourceConnectionEntity where connId=%d", connId))
	                .executeUpdate();
	        return deletedCount;
	}
    public DatasourceConnectionEntity findConnectionById(Integer chartId) throws Exception{
        return entityManager.find(DatasourceConnectionEntity.class, chartId);
    }
    
    //user
  //SERVICE_USER_MAPPING
    public String saveServiceUserMappingEntity(ServiceUserMappingEntity transientInstance) throws DataAccessException{
        entityManager.persist(transientInstance);
        return transientInstance.getId().getUserId();
    }
    
    //constants
    public String getConstant(String name)  {
    	Query q = entityManager.createNativeQuery(" select constant_value from CONSTANTS where constant_id = '"+name+"'");
    	if(q.getSingleResult()!=null){
    		return String.valueOf(q.getSingleResult());
    	}else{
    		return null;
    	}
    }
    public FilterEntity findFilterByName(String filterName) throws Exception{
	    	Query q = entityManager.createQuery(" select d from FilterEntity d where d.filterName = :fname",FilterEntity.class);
	    	q.setParameter("fname",filterName);
	    	if(q.getResultList()!=null && q.getResultList().size()>0){
	    		List<FilterEntity> results = q.getResultList();
	    		return (FilterEntity)results.get(0);
	    	}
	    	else{
	    		return null;
	    	}
    }
}
