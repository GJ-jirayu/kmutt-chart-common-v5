package th.ac.kmutt.chart.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import th.ac.kmutt.chart.xstream.common.ImakeXML;

import java.io.Serializable;

/**
 * Created by imake on 20/10/2015.
 */
@XStreamAlias("ChartFeatureMappingM")
public class ChartFeatureMappingM extends ImakeXML implements Serializable

    {
        private static final long serialVersionUID = 1L;
    private Integer featureId;
    private Integer chartId;
        private ChartM chart;
        private FeatureM feature;
        public Integer getChartId() {
            return chartId;
        }

        public void setChartId(Integer chartId) {
            this.chartId = chartId;
        }

        public Integer getFeatureId() {
            return featureId;
        }

        public ChartM getChart() {
            return chart;
        }

        public void setChart(ChartM chart) {
            this.chart = chart;
        }

        public FeatureM getFeature() {
            return feature;
        }

        public void setFeature(FeatureM feature) {
            this.feature = feature;
        }

        public void setFeatureId(Integer featureId) {
            this.featureId = featureId;
        }
    }
