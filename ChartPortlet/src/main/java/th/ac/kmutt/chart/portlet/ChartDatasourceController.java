package th.ac.kmutt.chart.portlet;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.util.PortalUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.portlet.bind.PortletRequestDataBinder;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import th.ac.kmutt.chart.form.ChartDatasourceForm;
import th.ac.kmutt.chart.model.ChartM;
import th.ac.kmutt.chart.model.ConnectionM;
import th.ac.kmutt.chart.model.FilterM;
import th.ac.kmutt.chart.model.ServiceM;
import th.ac.kmutt.chart.model.UserM;
import th.ac.kmutt.chart.service.ChartService;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.*;

@Controller("chartDatasourceController")
@RequestMapping("VIEW")
public class ChartDatasourceController {

	private static final Logger logger = Logger
			.getLogger(ChartDatasourceController.class);

	@Autowired
	@Qualifier("chartServiceWSImpl")
	private ChartService chartService;

	@InitBinder
	public void initBinder(PortletRequestDataBinder binder,
			PortletPreferences preferences) {
		logger.debug("initBinder");
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
		// String a[] = new String[]{"ntcfaq.nfaqName"};
		final String[] ALLOWED_FIELDS = { "" };
		binder.setAllowedFields(ALLOWED_FIELDS);
	}

	@RequestMapping("VIEW")
	public String iniPage(PortletRequest request, Model model) {

		ChartDatasourceForm dsf = null;
		if (!model.containsAttribute("chartDatasourceForm")) {
			dsf = new ChartDatasourceForm();
			dsf.setDatasourceId(0); // set initial value = 0
			dsf.setSqlString(null);
			List<FilterM> usedList = new ArrayList<FilterM>(); // init null
			dsf.setFilterUsedList(new HashSet<FilterM>(usedList));
		} else {
			dsf = (ChartDatasourceForm) model.asMap()
					.get("chartDatasourceForm");
		}

		List<ServiceM> ds = chartService.listChartDatasource();
		dsf.setDatasources(ds);

		FilterM f = new FilterM();
		@SuppressWarnings("unchecked")
		List<FilterM> filters = chartService.listFilter(f);
		Set<FilterM> initialFilterNone = new HashSet<FilterM>();
		dsf.setInitialFilterList(initialFilterNone);
		Set<FilterM> filterList = new HashSet<FilterM>(filters);
		dsf.setFilterList(filterList);

		// x chart
		ChartM chartParam = new ChartM();
		List<ChartM> charts = chartService.listChart(chartParam);
		Set<ChartM> chartInit = new HashSet<ChartM>();
		dsf.setChartInitList(chartInit);
		Set<ChartM> chartList = new HashSet<ChartM>(charts);
		dsf.setChartList(chartList);

		List<ConnectionM> cons = chartService.listConnection();
		dsf.setConnections(new HashSet<ConnectionM>(cons));

		// x User
		List<UserM> users = chartService.listUser();
		Set<UserM> usersinit = new HashSet<UserM>();
		dsf.setUserInitList(usersinit);
		Set<UserM> userList = new HashSet<UserM>();
		if (users != null) {
			userList = new HashSet<UserM>(users);
		}
		dsf.setUserList(userList);

		/*
		 * x user List<String> users = chartService.listFilter(f); Set<String>
		 * usersInit = new HashSet<String>(); dsf.setUserInitialList(usersInit);
		 * Set<String> userList = new HashSet<String>(users);
		 * dsf.setUserList(userList);
		 */

		model.addAttribute("chartDatasourceForm", dsf);
		return "chart/chartDatasource";
	}

	@RequestMapping(params = "action=doSubmit")
	// action phase
	public void doSubmit(javax.portlet.ActionRequest request,
			javax.portlet.ActionResponse response,
			@ModelAttribute("chartDatasourceForm") ChartDatasourceForm chartds,
			BindingResult result, Model model) {

		// try{
		String[] selectedFilter = request.getParameterValues("filterUsedList");
		List<String> usedFilterId = selectedFilter.length > 0 ? Arrays
				.asList(selectedFilter) : new ArrayList<String>();

		logger.info("chartds select param size: " + usedFilterId.size());
		ServiceM s = new ServiceM();
		s.setServiceId(chartds.getDatasourceId());
		s.setServiceName(chartds.getDatasourceName());
		s.setSqlString(chartds.getSqlString());

		// Integer resultCode = chartService.saveChartDatasource(s);
		// if(resultCode==0) throw new Exception("error");
		chartds.setMessage("size:" + chartds.getFilterUsedList().size());
		// }catch(Exception e){
		// chartds.setMessage("error");
		// logger.info("chartds: save action exception=>"+e.getMessage());

		// }
		// model.addAttribute("chartDatasourceForm", chartds);
	}

	@RequestMapping(params = "action=doNew")
	// action phase
	public void doNew(javax.portlet.ActionRequest request,
			javax.portlet.ActionResponse response,
			@ModelAttribute("chartDatasourceForm") ChartDatasourceForm chartds,
			BindingResult result, Model model) {
		// bug form not auto binding ??? why ???
		Integer datasourceId = request.getParameter("datasourceId") != null ? Integer
				.valueOf(request.getParameter("datasourceId")) : null;
		String datasourceName = request.getParameter("datasourceName");
		String textSqlString = request.getParameter("sqlString");
		Integer connection = request.getParameter("selectedConnId") != null  ? Integer.parseInt(request.getParameter("selectedConnId")) : null;
		String[] selectedFilter = request.getParameterValues("filterUsedList") != null ? request.getParameterValues("filterUsedList") : new String[0];
		List<String> usedFilterId = selectedFilter.length > 0 ? Arrays.asList(selectedFilter) : new ArrayList<String>();

		String[] chartUsed = request.getParameterValues("chartUsedList") != null ? request.getParameterValues("chartUsedList") : new String[0];
		List<String> chartUsedId = selectedFilter.length > 0 ? Arrays.asList(chartUsed) : new ArrayList<String>();

		String[] useUsed = request.getParameterValues("userUsedList") != null ? request.getParameterValues("userUsedList") : new String[0];
		List<String> useUsedId = selectedFilter.length > 0 ? Arrays.asList(useUsed) : new ArrayList<String>();

		
				
		try {
			ServiceM s = new ServiceM();
			s.setServiceId(null); // new must set id = null
			s.setDatasourceName(datasourceName);
			s.setSqlString(textSqlString);
			s.setConnId(connection);
			
			List<FilterM> fms = new ArrayList<FilterM>();
			for (String filterId : usedFilterId) {
				FilterM fm = new FilterM();
				fm.setFilterId(Integer.valueOf(filterId));
				fms.add(fm);
			}
			s.setFilterList(fms);
			
			List<ChartM> chartList = new ArrayList<ChartM>();
			for (String chartId : chartUsedId) {
				ChartM chart = new ChartM();
				chart.setChartId(Integer.valueOf(chartId));
				chartList.add(chart);
			}
			s.setChartList(chartList);
			
			List<UserM> userList = new ArrayList<UserM>();
			for (String userId : useUsedId) {
				UserM user = new UserM();
				user.setUserId(userId);
				userList.add(user);
			}
			s.setUserList(userList);
			
			Integer resultCode = chartService.saveChartDatasource(s);
			if (resultCode > 0) {
				chartds.setMessage("Save as service success");
			} else {
				chartds.setMessage("Save as service fail");
			}
		} catch (Exception e) {
			chartds.setMessage("Save as service fail");
			logger.info("chartds: save action exception=>" + e.getMessage());
		}
		chartds.setDatasourceId(datasourceId);
		model.addAttribute("chartDatasourceForm", chartds);
	}

	@RequestMapping(params = "action=doUpdate")
	// action phase
	public void doUpdate(javax.portlet.ActionRequest request,
			javax.portlet.ActionResponse response,
			@ModelAttribute("chartDatasourceForm") ChartDatasourceForm chartds,
			BindingResult result, Model model) {

		// bug ,not auto binding ??? why ???
		Integer datasourceId = request.getParameter("datasourceId") != null ? Integer.valueOf(request.getParameter("datasourceId")) : null;
		String datasourceName = request.getParameter("datasourceName");
		String textSqlString = request.getParameter("sqlString");
		Integer connection = request.getParameter("selectedConnId") != null ? Integer.parseInt(request.getParameter("selectedConnId")) : null;
		String[] selectedFilter = request.getParameterValues("filterUsedList") != null ? request.getParameterValues("filterUsedList") : new String[0];
		List<String> usedFilterId = selectedFilter.length > 0 ? Arrays.asList(selectedFilter) : new ArrayList<String>();
		
		String[] chartUsed = request.getParameterValues("chartUsedList") != null ? request.getParameterValues("chartUsedList") : new String[0];
		List<String> chartUsedId = chartUsed.length > 0 ? Arrays.asList(chartUsed) : new ArrayList<String>();

		String[] useUsed = request.getParameterValues("userUsedList") != null ? request.getParameterValues("userUsedList") : new String[0];
		List<String> useUsedId = useUsed.length > 0 ? Arrays.asList(useUsed) : new ArrayList<String>();

		//set form
		Set<FilterM> usedFilterSet = new HashSet<FilterM>();
		Set<FilterM> usedChartSet = new HashSet<FilterM>();
		Set<FilterM> usedUserSet = new HashSet<FilterM>();
		
		
		try {
			ServiceM s = new ServiceM();
			s.setServiceId(datasourceId);
			s.setDatasourceName(datasourceName);
			s.setSqlString(textSqlString);
			s.setConnId(connection);
			
			List<FilterM> fms = new ArrayList<FilterM>();
			for (String filterId : usedFilterId) {
				FilterM fm = new FilterM();
				fm.setFilterId(Integer.valueOf(filterId));
				fms.add(fm);
				//FilterM f = chartService.findFilterById(Integer.valueOf(filterId));
				//usedFilterSet.add(f);
			}
			s.setFilterList(fms);
			
			logger.info(" chart ds chartList "+chartUsed+"//"+useUsedId.size());
			List<ChartM> chartList = new ArrayList<ChartM>();
			for (String chartId : chartUsedId) {
				ChartM chart = new ChartM();
				chart.setChartId(Integer.valueOf(chartId));
				chartList.add(chart);
			}
			s.setChartList(chartList);
			
			List<UserM> userList = new ArrayList<UserM>();
			for (String userId : useUsedId) {
				UserM user = new UserM();
				user.setUserId(userId);
				userList.add(user);
			}
			s.setUserList(userList);
			
			
			Integer resultCode = chartService.saveChartDatasource(s);
			if (resultCode > 0) {
				chartds.setMessage("Update service success");
			} else {
				chartds.setMessage("Update service fail");
			}
		} catch (Exception e) {
			chartds.setMessage("Update service fail " + e.getMessage());
			logger.info("chartds: save action exception=>" + e.getMessage());
		}
		
		// set back
	//	chartds.setDatasourceId(datasourceId);
	//	chartds.setDatasourceName(datasourceName);
	//	chartds.setSqlString(textSqlString);
	//	chartds.setSelectedConnId(connection);
	//	chartds.setFilterUsedList(usedFilterSet); // list lost
		model.addAttribute("chartDatasourceForm", chartds);
	}

	@RequestMapping(params = "action=doDelete")
	// action phase
	public void doDetele(javax.portlet.ActionRequest request,
			javax.portlet.ActionResponse response,
			@ModelAttribute("chartDatasourceForm") ChartDatasourceForm chartds,
			BindingResult result, Model model) {
		Integer datasourceId = request.getParameter("datasourceId") != null ? Integer
				.valueOf(request.getParameter("datasourceId")) : null;
		String datasourceName = request.getParameter("datasourceName");
		String textSqlString = request.getParameter("sqlString");

		if (datasourceId != null) {
			ServiceM ds = new ServiceM();
			ds.setServiceId(datasourceId);
			Integer totalDelete = chartService.deleteService(ds);
			if (totalDelete != null) {
				chartds.setMessage("Delete service sucess");
			} else {
				chartds.setMessage("Delete service fail");
			}
		}
		chartds.setDatasourceId(datasourceId);
		chartds.setDatasourceName(datasourceName);
		chartds.setSqlString(textSqlString);
		model.addAttribute("chartDatasourceForm", chartds);
	}

	@ResourceMapping(value = "selectedDatasource")
	@ResponseBody
	public void chartDatasourceDetail(ResourceRequest request,
			ResourceResponse response) throws IOException {
		JSONObject json = JSONFactoryUtil.createJSONObject();
		JSONObject head = JSONFactoryUtil.createJSONObject();
		JSONObject content = JSONFactoryUtil.createJSONObject();
		JSONObject dsObj = JSONFactoryUtil.createJSONObject();
		JSONObject filterObj = JSONFactoryUtil.createJSONObject();
		JSONObject chartObj = JSONFactoryUtil.createJSONObject();
		JSONObject userObj = JSONFactoryUtil.createJSONObject();

		HttpServletRequest httpReq = PortalUtil.getHttpServletRequest(request);
		HttpServletRequest normalRequest = PortalUtil
				.getOriginalServletRequest(httpReq);

		try {
			ServiceM s = new ServiceM();
			s.setServiceId(Integer.parseInt(normalRequest
					.getParameter("datasourceId")));

			s = chartService.detailChartDatasource(s);

			dsObj.put("sid", s.getServiceId());
			dsObj.put("sname", s.getDatasourceName());
			dsObj.put("sql", s.getSqlString());
			dsObj.put("conn", s.getConnId());
			content.put("datasource", dsObj);

			// filter object section
			JSONArray fs = JSONFactoryUtil.createJSONArray();
			for (FilterM usedFilter : s.getFilterList()) {
				JSONObject usedJson = JSONFactoryUtil.createJSONObject();
				usedJson.put("fid", usedFilter.getFilterId());
				usedJson.put("fname", usedFilter.getFilterName());
				usedJson.put("flabel", usedFilter.getTitle());
				fs.put(usedJson);
			}
			filterObj.put("fusedlist", fs);
			content.put("filter", filterObj);

			// chart object section
			JSONArray fc = JSONFactoryUtil.createJSONArray();
			if (s.getChartList() != null) {
				for (ChartM useChart : s.getChartList()) {
					JSONObject useChartJson = JSONFactoryUtil
							.createJSONObject();
					useChartJson.put("cid", useChart.getChartId());
					useChartJson.put("cname", useChart.getChartName());
					fc.put(useChartJson);
				}
			}
			chartObj.put("cusedlist", fc);
			content.put("chart", chartObj);

			// user
			JSONArray fu = JSONFactoryUtil.createJSONArray();
			if (s.getUserList() != null) {
				for (UserM userUsed : s.getUserList()) {
					JSONObject useChartJson = JSONFactoryUtil.createJSONObject();
					useChartJson.put("uid", userUsed.getUserId());
					useChartJson.put("uname", userUsed.getUsername());
					fu.put(useChartJson);
				}
			}
			userObj.put("userUsedlist", fu);
			content.put("user", userObj);

			head.put("success", "1");
			head.put("msg", "success");
			json.put("content", content);
			json.put("header", head);
			response.getWriter().write(json.toString());
		} catch (Exception e) {
			head.put("success", "0");
			head.put("msg", e.getStackTrace().toString());
			logger.error(" chartDatasource retrive detail Exception reason="+ e);
			throw e;
		}
	}
}
