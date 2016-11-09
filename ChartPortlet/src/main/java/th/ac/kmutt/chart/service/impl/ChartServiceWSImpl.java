package th.ac.kmutt.chart.service.impl;

import org.springframework.stereotype.Service;

import th.ac.kmutt.chart.constant.ServiceConstant;
import th.ac.kmutt.chart.model.*;
import th.ac.kmutt.chart.service.ChartService;
import th.ac.kmutt.chart.xstream.common.ImakeResultMessage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service("chartServiceWSImpl")
public class ChartServiceWSImpl extends PostCommon implements ChartService {

    @Override
    public List listService(ServiceM param) {
        param.setServiceName(ServiceConstant.SERVICE_SEARCH);
        ImakeResultMessage imakeMessage = postMessage(param, param.getClass().getName(), "service", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return  imakeMessage.getResultListObj();
        else
            return null;
    }

	@Override
	public List listServiceByChart(ServiceM param) {
		 param.setServiceName(ServiceConstant.SERVICE_SEARCH_BY_CHART);
	        ImakeResultMessage imakeMessage = postMessage(param, param.getClass().getName(), "service", true);
	        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
	            return  imakeMessage.getResultListObj();
	        else
	            return null;
	}
	
    private static SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

/*
    @Override
    public List listCopyrightService(CopyrightServiceM param) {
        param.setServiceName(ServiceConstant.COPYRIGHT_SERVICE_SEARCH);
        ImakeResultMessage imakeMessage = postMessage(param, param.getClass().getName(), "copyright", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return  imakeMessage.getResultListObj();
        else
            return null;
    }

    @Override
    public List listJournalPapersService(JournalPapersServiceM param) {
        param.setServiceName(ServiceConstant.JOURNAL_SERVICE_SEARCH);
        ImakeResultMessage imakeMessage = postMessage(param, param.getClass().getName(), "journalPapers", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return  imakeMessage.getResultListObj();
        else
            return null;
    }

    @Override
    public List listFundingResourceService(FundingResourceServiceM param) {
        param.setServiceName(ServiceConstant.FUNDING_SERVICE_SEARCH);
        ImakeResultMessage imakeMessage = postMessage(param, param.getClass().getName(), "funding", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return  imakeMessage.getResultListObj();
        else
            return null;
    }*/

    @Override
    public Integer saveChart(ChartM model){
        model.setServiceName(ServiceConstant.CHART_SAVE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "chart", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ChartM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }
    @Override
    public Integer updateChart(ChartM model){
        model.setServiceName(ServiceConstant.CHART_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "chart", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ChartM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteChart(ChartM model){
        // TODO Auto-generated method stub
        model.setServiceName(ServiceConstant.CHART_DELETE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "chart", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ChartM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public ChartM findChartById(Integer chartId) {
        // TODO Auto-generated method stub
        ChartM model = new ChartM();
        model.setChartId(chartId);
        model.setServiceName(ServiceConstant.CHART_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "chart", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (ChartM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }
    @Override
    public List listChart(ChartM param) {
        param.setServiceName(ServiceConstant.CHART_SEARCH);
        ImakeResultMessage imakeMessage = postMessage(param, param.getClass().getName(), "chart", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return  imakeMessage.getResultListObj();
        else
            return null;
    }

    // chart feature
    @Override
    public Integer saveChartFeature(ChartFeatureM model) {
        model.setServiceName(ServiceConstant.CHART_FEATURE_SAVE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "chartfeature",true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ChartFeatureM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateChartFeature(ChartFeatureM model) {
        model.setServiceName(ServiceConstant.CHART_FEATURE_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "chartfeature", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ChartFeatureM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteChartFeature(ChartFeatureM model) {
        model.setServiceName(ServiceConstant.CHART_FEATURE_DELETE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "chartfeature", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ChartFeatureM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public ChartFeatureM findChartFeatureById(Integer chartId) {
        ChartFeatureM model = new ChartFeatureM();
        model.setChartId(chartId);
        model.setServiceName(ServiceConstant.CHART_FEATURE_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "chartfeature", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (ChartFeatureM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }

    // Chart Feature Instance
    @Override
    public Integer saveChartFeatureInstance(ChartFeatureInstanceM model) {
        model.setServiceName(ServiceConstant.CHART_FEATURE_INSTANCE_SAVE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "chartfeatureinstance", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ChartFeatureInstanceM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateChartFeatureInstance(ChartFeatureInstanceM model) {
        model.setServiceName(ServiceConstant.CHART_FEATURE_INSTANCE_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "chartfeatureinstance", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ChartFeatureInstanceM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteChartFeatureInstance(ChartFeatureInstanceM model) {
        model.setServiceName(ServiceConstant.CHART_FEATURE_INSTANCE_DELETE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "chartfeatureinstance", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ChartFeatureInstanceM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public ChartFeatureInstanceM findChartFeatureInstanceById(String instanceId) {
        ChartFeatureInstanceM model = new ChartFeatureInstanceM();
        model.setInstanceId(instanceId);
        model.setServiceName(ServiceConstant.CHART_FEATURE_INSTANCE_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "chartfeatureinstance", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (ChartFeatureInstanceM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }

    // Chart Feature Mapping
    @Override
    public Integer saveChartFeatureMapping(ChartFeatureMappingM model) {
        model.setServiceName(ServiceConstant.CHART_FEATURE_MAPPING_SAVE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "chartfeaturemapping", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ChartFeatureMappingM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateChartFeatureMapping(ChartFeatureMappingM model) {
        model.setServiceName(ServiceConstant.CHART_FEATURE_MAPPING_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "chartfeaturemapping", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ChartFeatureMappingM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteChartFeatureMapping(ChartFeatureMappingM model) {
        model.setServiceName(ServiceConstant.CHART_FEATURE_MAPPING_DELETE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "chartfeaturemapping", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ChartFeatureMappingM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public ChartFeatureMappingM findChartFeatureMappingById(Integer featureId, Integer chartId) {
        ChartFeatureMappingM model = new ChartFeatureMappingM();
        model.setChartId(chartId);
        model.setFeatureId(featureId);
        model.setServiceName(ServiceConstant.CHART_FEATURE_MAPPING_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "chartfeaturemapping", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (ChartFeatureMappingM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }

    // Chart Filter Instance
    @Override
    public Integer saveChartFilterInstance(ChartFilterInstanceM model) {
        model.setServiceName(ServiceConstant.CHART_FILTER_INSTANCE_SAVE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "chartfilterinstance", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ChartFilterInstanceM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateChartFilterInstance(ChartFilterInstanceM model) {
        model.setServiceName(ServiceConstant.CHART_FILTER_INSTANCE_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "chartfilterinstance", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ChartFilterInstanceM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteChartFilterInstance(ChartFilterInstanceM model) {
        model.setServiceName(ServiceConstant.CHART_FILTER_INSTANCE_DELETE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "chartfilterinstance", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ChartFilterInstanceM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public ChartFilterInstanceM findChartFilterInstanceById(String instanceId) {
        ChartFilterInstanceM model = new ChartFilterInstanceM();
        model.setInstanceId(instanceId);
        model.setServiceName(ServiceConstant.CHART_FILTER_INSTANCE_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "chartfilterinstance", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (ChartFilterInstanceM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }
    @Override
    public List listChartFilterInstance(ChartFilterInstanceM param) {
        param.setServiceName(ServiceConstant.CHART_FILTER_INSTANCE_SEARCH);
        ImakeResultMessage imakeMessage = postMessage(param, param.getClass().getName(), "chartfilterinstance", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return  imakeMessage.getResultListObj();
        else
            return null;
    }

    // Chart Instance
    @Override
    public Integer saveChartInstance(ChartInstanceM model) {
        model.setServiceName(ServiceConstant.CHART_INSTANCE_SAVE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "chartinstance", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ChartInstanceM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateChartInstance(ChartInstanceM model) {
        model.setServiceName(ServiceConstant.CHART_INSTANCE_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "chartinstance", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ChartInstanceM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteChartInstance(ChartInstanceM model) {
        model.setServiceName(ServiceConstant.CHART_INSTANCE_DELETE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "chartinstance", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ChartInstanceM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public ChartInstanceM findChartInstanceById(String instanceId) {
        ChartInstanceM model = new ChartInstanceM();
        model.setInstanceId(instanceId);
        model.setServiceName(ServiceConstant.CHART_INSTANCE_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "chartinstance", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (ChartInstanceM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }

    // Comment
    @Override
    public Integer saveComment(CommentM model) {
        model.setServiceName(ServiceConstant.COMMENT_SAVE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "comment", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((CommentM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateComment(CommentM model) {
        model.setServiceName(ServiceConstant.COMMENT_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "comment", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((CommentM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteComment(CommentM model) {
        model.setServiceName(ServiceConstant.COMMENT_DELETE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "comment", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((CommentM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public CommentM findCommentById(String instanceId) {
        CommentM model = new CommentM();
        model.setInstanceId(instanceId);
        model.setServiceName(ServiceConstant.COMMENT_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "comment", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (CommentM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }

    // Feature
    @Override
    public Integer saveFeature(FeatureM model) {
        model.setServiceName(ServiceConstant.FEATURE_SAVE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "feature", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((FeatureM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateFeature(FeatureM model) {
        model.setServiceName(ServiceConstant.FEATURE_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "feature", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((FeatureM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteFeature(FeatureM model) {
        model.setServiceName(ServiceConstant.FEATURE_DELETE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "feature", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((FeatureM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public FeatureM findFeatureById(Integer featureId) {
        FeatureM model = new FeatureM();
        model.setFeatureId(featureId);
        model.setServiceName(ServiceConstant.FEATURE_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "feature", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (FeatureM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }

    // Filter
    @Override
    public Integer saveFilter(FilterM model) {
        model.setServiceName(ServiceConstant.FILTER_SAVE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "filter", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((FilterM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateFilter(FilterM model) {
        model.setServiceName(ServiceConstant.FILTER_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "filter", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((FilterM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteFilter(FilterM model) {
        model.setServiceName(ServiceConstant.FILTER_DELETE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "filter", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((FilterM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public FilterM findFilterById(Integer filterId) {
        FilterM model = new FilterM();
        model.setFilterId(filterId);
        model.setServiceName(ServiceConstant.FILTER_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "filter", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (FilterM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }

    @Override
    public List listFilter(FilterM param) {
        param.setServiceName(ServiceConstant.FILTER_SEARCH);
        ImakeResultMessage imakeMessage = postMessage(param, param.getClass().getName(), "filter", true);
        if (imakeMessage!=null &&imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return  imakeMessage.getResultListObj();
        else
            return null;
    }

    // Filter Instance
    @Override
    public Integer saveFilterInstance(FilterInstanceM model) {
        model.setServiceName(ServiceConstant.FILTER_INSTANCE_SAVE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "filterInstance", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((FilterInstanceM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateFilterInstance(FilterInstanceM model) {
        model.setServiceName(ServiceConstant.FILTER_INSTANCE_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "filterInstance", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((FilterInstanceM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteFilterInstance(FilterInstanceM model) {
        model.setServiceName(ServiceConstant.FILTER_INSTANCE_DELETE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "filterInstance", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((FilterInstanceM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public FilterInstanceM findFilterInstanceById(String instanceId) {
        FilterInstanceM model = new FilterInstanceM();
        model.setInstanceId(instanceId);
        model.setServiceName(ServiceConstant.FILTER_INSTANCE_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "filterInstance", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (FilterInstanceM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }

    @Override
    public List listFilterInstance(FilterInstanceM param) {
        param.setServiceName(ServiceConstant.FILTER_INSTANCE_SEARCH);
        ImakeResultMessage imakeMessage = postMessage(param, param.getClass().getName(), "filterInstance", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return  imakeMessage.getResultListObj();
        else
            return null;
    }
/*
    // Funding
    @Override
    public Integer saveFundingResourceService(FundingResourceServiceM model) {
        model.setServiceName(ServiceConstant.FUNDING_SERVICE_SAVE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "funding", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((FundingResourceServiceM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateFundingResourceService(FundingResourceServiceM model) {
        model.setServiceName(ServiceConstant.FUNDING_SERVICE_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "funding", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((FundingResourceServiceM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteFundingResourceService(FundingResourceServiceM model) {
        model.setServiceName(ServiceConstant.FUNDING_SERVICE_DELETE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "funding", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((FundingResourceServiceM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public FundingResourceServiceM findFundingResourceServiceById(Integer type, Integer year) {
        FundingResourceServiceM model = new FundingResourceServiceM();
        model.setType(type);
        model.setYear(year);
        model.setServiceName(ServiceConstant.FUNDING_SERVICE_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "funding", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (FundingResourceServiceM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }

    // Journal
    @Override
    public Integer saveJournalPapersService(JournalPapersServiceM model) {
        model.setServiceName(ServiceConstant.JOURNAL_SERVICE_SAVE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "journalPapers", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((JournalPapersServiceM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateJournalPapersService(JournalPapersServiceM model) {
        model.setServiceName(ServiceConstant.JOURNAL_SERVICE_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "journalPapers", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((JournalPapersServiceM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteJournalPapersService(JournalPapersServiceM model) {
        model.setServiceName(ServiceConstant.JOURNAL_SERVICE_DELETE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "journalPapers", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((JournalPapersServiceM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public JournalPapersServiceM findJournalPapersServiceById(Integer type, Integer year) {
        JournalPapersServiceM model = new JournalPapersServiceM();
        model.setType(type);
        model.setYear(year);
        model.setServiceName(ServiceConstant.JOURNAL_SERVICE_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "journalPapers", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (JournalPapersServiceM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }
    // Copyright Service
    @Override
    public Integer saveCopyrightService(CopyrightServiceM model) {
        model.setServiceName(ServiceConstant.COPYRIGHT_SERVICE_SAVE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "copyright", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((CopyrightServiceM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }
    @Override
    public Integer updateCopyrightService(CopyrightServiceM model) {
        model.setServiceName(ServiceConstant.COPYRIGHT_SERVICE_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "copyright", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((CopyrightServiceM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }
    @Override
    public Integer deleteCopyrightService(CopyrightServiceM model) {
        model.setServiceName(ServiceConstant.COPYRIGHT_SERVICE_DELETE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "copyright", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((CopyrightServiceM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }
    @Override
    public CopyrightServiceM findCopyrightServiceById(Integer type, Integer year, Integer month) {
        CopyrightServiceM model = new CopyrightServiceM();
        model.setType(type);
        model.setYear(year);
        model.setMonth(month);
        model.setServiceName(ServiceConstant.COPYRIGHT_SERVICE_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "copyright", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (CopyrightServiceM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }*/

    // Service Chart Mapping
    @Override
    public Integer saveServiceChartMapping(ServiceChartMappingM model) {
        model.setServiceName(ServiceConstant.SERVICE_CHART_MAPPING_SAVE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "servicechartmapping", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ServiceChartMappingM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateServiceChartMapping(ServiceChartMappingM model) {
        model.setServiceName(ServiceConstant.SERVICE_CHART_MAPPING_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "servicechartmapping", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ServiceChartMappingM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteServiceChartMapping(ServiceChartMappingM model) {
        model.setServiceName(ServiceConstant.SERVICE_CHART_MAPPING_DELETE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "servicechartmapping", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ServiceChartMappingM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public ServiceChartMappingM findServiceChartMappingById(Integer serviceId, Integer chartId) {
        ServiceChartMappingM model = new ServiceChartMappingM();
        model.setChartId(chartId);
        model.setServiceName(ServiceConstant.SERVICE_CHART_MAPPING_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "servicechartmapping", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (ServiceChartMappingM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }

    // Service
    @Override
    public Integer saveService(ServiceM model) {
        model.setServiceName(ServiceConstant.SERVICE_SAVE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "service", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ServiceM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateService(ServiceM model) {
        model.setServiceName(ServiceConstant.SERVICE_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "service", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ServiceM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteService(ServiceM model) {
        model.setServiceName(ServiceConstant.CHART_DS_DELETE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "service", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ServiceM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }
    
    //*  with chartDatasource xxx
    @Override
    public List<UserM> listUser() {
    	ServiceM model = new ServiceM();
        model.setServiceName(ServiceConstant.CHART_DS_ALL_USER);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "service", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0){
            ServiceM s =  (ServiceM) imakeMessage.getResultListObj().get(0);
        	return s.getUserList();
        }
        else
            return null;
    }
    @Override
    public List<UserM> listUserByService(ServiceM s){
    	ServiceM model = new ServiceM();
        model.setServiceName(ServiceConstant.CHART_DS_USER_BY_DATASOURCE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "service", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return imakeMessage.getResultListObj();
        else
            return null;
    }

    @Override
    public ServiceM findServiceById(Integer serviceId) {
        ServiceM model = new ServiceM();
        model.setServiceId(serviceId);
        model.setServiceName(ServiceConstant.SERVICE_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "service", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (ServiceM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }

    // Service Filter Mapping
    @Override
    public Integer saveServiceFilterMapping(ServiceFilterMappingM model) {
        model.setServiceName(ServiceConstant.SERVICE_FILTER_MAPPING_SAVE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "servicefiltermapping", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ServiceFilterMappingM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer updateServiceFilterMapping(ServiceFilterMappingM model) {
        model.setServiceName(ServiceConstant.SERVICE_FILTER_MAPPING_UPDATE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "servicefiltermapping", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ServiceFilterMappingM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public Integer deleteServiceFilterMapping(ServiceFilterMappingM model) {
        model.setServiceName(ServiceConstant.SERVICE_FILTER_MAPPING_DELETE);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "servicefiltermapping", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return ((ServiceFilterMappingM) imakeMessage.getResultListObj().get(0)).getUpdateRecord();
        else
            return null;
    }

    @Override
    public ServiceFilterMappingM findServiceFilterMappingById(Integer serviceId, Integer filterId) {
        ServiceFilterMappingM model = new ServiceFilterMappingM();
        model.setServiceId(serviceId);
        model.setFilterId(filterId);
        model.setServiceName(ServiceConstant.SERVICE_FILTER_MAPPING_FIND_BY_ID);
        ImakeResultMessage imakeMessage = postMessage(model, model.getClass().getName(), "servicefiltermapping", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return (ServiceFilterMappingM) imakeMessage.getResultListObj().get(0);
        else
            return null;
    }

    @Override
    public List listServiceFilterMapping(ServiceFilterMappingM param) {
        param.setServiceName(ServiceConstant.SERVICE_FILTER_MAPPING_SEARCH);
        ImakeResultMessage imakeMessage = postMessage(param, param.getClass().getName(), "servicefiltermapping", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return  imakeMessage.getResultListObj();
        else
            return null;
    }

	@Override
	public List listInboundOutbound(InBoundOutBoundServiceM param) {
		param.setServiceName("N");
        ImakeResultMessage imakeMessage = postMessage(param, param.getClass().getName(), "aew", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
            return  imakeMessage.getResultListObj();
        else
            return null;
	}

	@Override
	public FusionChartM getFusionChart(FusionChartM obj) {
		obj.setServiceName(ServiceConstant.FUSION_CHART_OBJECT);
			ImakeResultMessage imakeMessage = postMessage(obj, obj.getClass().getName(), "fusionchart", true);
	        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
	        {   List<FusionChartM> returnObj =    imakeMessage.getResultListObj();
	        	obj = returnObj.get(0);
	        }
        return obj;
	}
	@Override
	public FilterInstanceM getFilterInstance(FilterInstanceM obj){
		obj.setServiceName(ServiceConstant.FILTER_INSTANCE_GET);
		FilterInstanceM fin = new FilterInstanceM();
		ImakeResultMessage imakeMessage = postMessage(obj, obj.getClass().getName(), "filterInstance", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
        {   fin =    (FilterInstanceM) imakeMessage.getResultListObj().get(0);
        }
		return fin;
	}
	@Override
	public List<FilterInstanceM> getFilterInstanceWithItem(FilterInstanceM obj){
		obj.setServiceName(ServiceConstant.FILTER_INSTANCE_WITH_ITEM);
		List<FilterInstanceM> fins = new ArrayList<FilterInstanceM>();
		ImakeResultMessage imakeMessage = postMessage(obj, obj.getClass().getName(), "filterInstance", true);
        if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0)
        {   fins =    imakeMessage.getResultListObj();
        }
		return fins;
	}

	@Override
	public List<FilterM> getGlobalFilter() {
		List<FilterM> filters = new ArrayList<FilterM>();
		FilterM filter = new FilterM();
		filter.setServiceName(ServiceConstant.FILTER_GET_GLOBAL_FILTER);
		ImakeResultMessage imakeMessage = postMessage(filter, filter.getClass().getName(), "filter", true);
		 if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0){
			 filters =    imakeMessage.getResultListObj();
		 }
		return filters;
	}

	@Override
	public List<FilterM> getFilterService(FilterM obj) {
		List<FilterM> filters = new ArrayList<FilterM>();
		obj.setServiceName(ServiceConstant.FILTER_GET_FILTER_SERVICE);
		ImakeResultMessage imakeMessage = postMessage(obj, obj.getClass().getName(), "filter", true);
		 if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0){
			 filters =    imakeMessage.getResultListObj();
		 }
		return filters;
	}

	@Override
	public List<ChartFilterInstanceM> getChartFilterInstance(ChartFilterInstanceM obj) {
		List<ChartFilterInstanceM> filters = new ArrayList<ChartFilterInstanceM>();
		obj.setServiceName(ServiceConstant.CHART_FILTER_INSTANCE_GET_ALL_FILTER);
		ImakeResultMessage imakeMessage = postMessage(obj, obj.getClass().getName(), "chartfilterinstance", true);
		 if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0){
			 filters =    imakeMessage.getResultListObj();
		 }
		return filters;
	}
	//cascade

	@Override
	public List<FilterM> cascadeFilterItems(FilterInstanceM fin) {
		List<FilterM> filters = new ArrayList<FilterM>();
		
		fin.setServiceName(ServiceConstant.CASCADE_FILTER);
		ImakeResultMessage imakeMessage = postMessage(fin, fin.getClass().getName(), "filterInstance", true);
		 if (imakeMessage.getResultListObj() != null && imakeMessage.getResultListObj().size() > 0){
			 @SuppressWarnings("unchecked")
			List<FilterInstanceM> fins  =    imakeMessage.getResultListObj();
			 filters = fins.get(0).getFilterList();
		 }
		return filters;
	}

	@Override
	public List<ServiceM> listChartDatasource() {     // "===  sevice"
		try{
		ServiceM sm = new ServiceM();
		sm.setServiceName( ServiceConstant.SERVICE_SEARCH );  
		ImakeResultMessage imakeMessage = postMessage(sm, sm.getClass().getName(), "service", true);
		@SuppressWarnings("unchecked")
		List<ServiceM> sms = imakeMessage.getResultListObj();
		return sms;
		}catch(Exception e){
			return new ArrayList<ServiceM>();	
		}
	}

	@Override
	public ServiceM detailChartDatasource(ServiceM s) {
		try{
		s.setServiceName( ServiceConstant.CHART_DS_DETAIL );  
		ImakeResultMessage imakeMessage = postMessage(s, s.getClass().getName(), "service", true);
		s = (ServiceM)imakeMessage.getResultListObj().get(0);
		return s;
		}catch(Exception e){
			return null;
		}
	}
	@Override
	public Integer saveChartDatasource(ServiceM s) {
		try{
			s.setServiceName( ServiceConstant.CHART_DS_SAVE );  
			ImakeResultMessage imakeMessage = postMessage(s, s.getClass().getName(), "service", true);
			if(imakeMessage.getReturnId()==null) throw new Exception();
			return  1;
		}catch(Exception e){
			return 0;
		}
	}
	@Override
	public Integer saveDatasourceXFilter(ServiceM s) {
		try{
			s.setServiceName( ServiceConstant.CHART_DS_BIND_FILTER );  
			ImakeResultMessage imakeMessage = postMessage(s, s.getClass().getName(), "service", true);
			if(imakeMessage.getReturnId()==null) throw new Exception();
			return  1;
		}catch(Exception e){
			return 0;
		}
	}
	@Override
	public Integer deleteDatasourceXFilter(ServiceM s) {
		try{
			s.setServiceName( ServiceConstant.CHART_DS_DEL_FILTER );  
			ImakeResultMessage imakeMessage = postMessage(s, s.getClass().getName(), "service", true);
			if(imakeMessage.getReturnId()==null) throw new Exception();
			return  1;
		}catch(Exception e){
			return 0;
		}
	}

	@Override
	public List<ConnectionM> listConnection() {
		ConnectionM conn = new ConnectionM();
		try{
			conn.setServiceName( ServiceConstant.CONNECTION_LIST );  
			ImakeResultMessage imakeMessage = postMessage(conn,conn.getClass().getName(), "connection", true);
			if(imakeMessage.getResultListObj()==null) throw new Exception();
			return  imakeMessage.getResultListObj();
		}catch(Exception e){
			return  new ArrayList<ConnectionM>();
		}
	}

	@Override
	public FilterM detailFilter(FilterM filter) {
		try{
			filter.setServiceName( ServiceConstant.PARAM_DETAIL );  
			ImakeResultMessage imakeMessage = postMessage(filter,filter.getClass().getName(), "filter", true);
			if(imakeMessage.getResultListObj()==null) throw new Exception();
			return  (FilterM) imakeMessage.getResultListObj().get(0);
		}catch(Exception e){
			return  new FilterM();
		}
	}
	@Override
	public void newConnection(ConnectionM conn) throws Exception{
			conn.setServiceName( ServiceConstant.CONNECTION_NEW );  
			ImakeResultMessage imakeMessage = postMessage(conn,conn.getClass().getName(), "connection", true);
			if(imakeMessage.getReturnId()==null)
				throw new Exception("new connection fail");
	}
	@Override
	public void updateConnection(ConnectionM conn) throws Exception{
		conn.setServiceName( ServiceConstant.CONNECTION_UPDATE );  
		ImakeResultMessage imakeMessage = postMessage(conn,conn.getClass().getName(), "connection", true);
		if(imakeMessage.getReturnId()==null)
			throw new Exception("update connection fail");
	}
	@Override
	public void deleteConnection(ConnectionM conn) throws Exception{
		conn.setServiceName( ServiceConstant.CONNECTION_DELETE );  
		ImakeResultMessage imakeMessage = postMessage(conn,conn.getClass().getName(), "connection", true);
		if(imakeMessage.getReturnId()==null)
			throw new Exception("delete connection fail");
	}
	@Override
	public ConnectionM findConnectionById(Integer id) throws Exception{
		ConnectionM conn = new ConnectionM();
		conn.setConnId(id);
		conn.setServiceName( ServiceConstant.CONNECTION_FIND_BY_ID );  
		ImakeResultMessage imakeMessage = postMessage(conn,conn.getClass().getName(), "connection", true);
		return (ConnectionM) imakeMessage.getResultListObj().get(0);
	}
	
} 
