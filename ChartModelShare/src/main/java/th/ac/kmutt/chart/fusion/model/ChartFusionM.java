package th.ac.kmutt.chart.fusion.model;

import com.google.gson.annotations.SerializedName;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;
import java.util.List;

/**
 * Created by imake on 07/09/2015.
 */
@XStreamAlias("chart")
public class ChartFusionM implements Serializable {





    private String chartName;
    private String chartValue;

    @SerializedName("caption")
    @XStreamAlias("caption")
    private String caption;

    @SerializedName("subCaption")
    @XStreamAlias("subCaptionn")
    private String subCaptionn;

    @SerializedName("captionFontSize")
    @XStreamAlias("captionFontSize")
    private String captionFontSize;

    @SerializedName("subcaptionFontSize")
    @XStreamAlias("subcaptionFontSize")
    private String subcaptionFontSize;

    @SerializedName("subcaptionFontBold")
    @XStreamAlias("subcaptionFontBold")
    private String subcaptionFontBold;

    @SerializedName("paletteColors")
    @XStreamAlias("paletteColors")
    private String paletteColors; // "#0075c2,#1aaf5d"

    @SerializedName("bgcolor")
    @XStreamAlias("bgcolor")
    private String bgcolor;

    @SerializedName("showBorder")
    @XStreamAlias("showBorder")
    private String showBorder;

    @SerializedName("showShadow")
    @XStreamAlias("showShadow")
    private String showShadow;

    @SerializedName("showCanvasBorder")
    @XStreamAlias("showCanvasBorder")
    private String showCanvasBorder;

    @SerializedName("usePlotGradientColor")
    @XStreamAlias("usePlotGradientColor")
    private String usePlotGradientColorr;

    @SerializedName("legendBorderAlpha")
    @XStreamAlias("legendBorderAlpha")
    private String legendBorderAlpha;

    @SerializedName("legendShadow")
    @XStreamAlias("legendShadow")
    private String legendShadow;

    @SerializedName("showAxisLines")
    @XStreamAlias("showAxisLines")
    private String showAxisLinesr;

    @SerializedName("showAlternateHGridColor")
    @XStreamAlias("showAlternateHGridColor")
    private String showAlternateHGridColor;

    @SerializedName("divlineThickness")
    @XStreamAlias("divlineThickness")
    private String divlineThickness;

    @SerializedName("divLineDashed")
    @XStreamAlias("divLineDashed")
    private String divLineDashed;

    @SerializedName("divLineDashLen")
    @XStreamAlias("divLineDashLen")
    private String divLineDashLen;

    @SerializedName("divLineGapLen")
    @XStreamAlias("divLineGapLen")
    private String divLineGapLen;

    @SerializedName("xAxisName")
    @XStreamAlias("xAxisName")
    private String xAxisName;

    @SerializedName("showValues")
    @XStreamAlias("showValues")
    private String showValues;

    // for MSArea
    @SerializedName("yAxisName")
    @XStreamAlias("yAxisName")
    private String yAxisName;

    @SerializedName("drawAnchors")
    @XStreamAlias("drawAnchors")
    private String drawAnchors;

    @SerializedName("bgColor")
    @XStreamAlias("bgColor")
    private String bgColor;

    @SerializedName("plotBorderAlpha")
    @XStreamAlias("plotBorderAlpha")
    private String plotBorderAlpha;

    @SerializedName("plotFillAlpha")
    @XStreamAlias("plotFillAlpha")
    private String plotFillAlpha;

    @SerializedName("showXAxisLine")
    @XStreamAlias("showXAxisLine")
    private String showXAxisLine;

    @SerializedName("axisLineAlpha")
    @XStreamAlias("axisLineAlpha")
    private String axisLineAlpha;

    @SerializedName("divlineColor")
    @XStreamAlias("divlineColor")
    private String divlineColor;

    @SerializedName("toolTipColor")
    @XStreamAlias("toolTipColor")
    private String toolTipColor;

    @SerializedName("toolTipBorderThickness")
    @XStreamAlias("toolTipBorderThickness")
    private String toolTipBorderThickness;

    @SerializedName("toolTipBgColor")
    @XStreamAlias("toolTipBgColor")
    private String toolTipBgColo;

    @SerializedName("toolTipBgAlpha")
    @XStreamAlias("toolTipBgAlpha")
    private String toolTipBgAlphas;


    @SerializedName("toolTipBorderRadiu")
    @XStreamAlias("toolTipBorderRadiu")
    private String toolTipBorderRadiu;


    @SerializedName("toolTipPadding")
    @XStreamAlias("toolTipPadding")
    private String toolTipPadding;

    public String getChartName() {
        return chartName;
    }

    public void setChartName(String chartName) {
        this.chartName = chartName;
    }

    public String getChartValue() {
        return chartValue;
    }

    public void setChartValue(String chartValue) {
        this.chartValue = chartValue;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getSubCaptionn() {
        return subCaptionn;
    }

    public void setSubCaptionn(String subCaptionn) {
        this.subCaptionn = subCaptionn;
    }

    public String getCaptionFontSize() {
        return captionFontSize;
    }

    public void setCaptionFontSize(String captionFontSize) {
        this.captionFontSize = captionFontSize;
    }

    public String getSubcaptionFontSize() {
        return subcaptionFontSize;
    }

    public void setSubcaptionFontSize(String subcaptionFontSize) {
        this.subcaptionFontSize = subcaptionFontSize;
    }

    public String getSubcaptionFontBold() {
        return subcaptionFontBold;
    }

    public void setSubcaptionFontBold(String subcaptionFontBold) {
        this.subcaptionFontBold = subcaptionFontBold;
    }

    public String getPaletteColors() {
        return paletteColors;
    }

    public void setPaletteColors(String paletteColors) {
        this.paletteColors = paletteColors;
    }

    public String getBgcolor() {
        return bgcolor;
    }

    public void setBgcolor(String bgcolor) {
        this.bgcolor = bgcolor;
    }

    public String getShowBorder() {
        return showBorder;
    }

    public void setShowBorder(String showBorder) {
        this.showBorder = showBorder;
    }

    public String getShowShadow() {
        return showShadow;
    }

    public void setShowShadow(String showShadow) {
        this.showShadow = showShadow;
    }

    public String getShowCanvasBorder() {
        return showCanvasBorder;
    }

    public void setShowCanvasBorder(String showCanvasBorder) {
        this.showCanvasBorder = showCanvasBorder;
    }

    public String getUsePlotGradientColorr() {
        return usePlotGradientColorr;
    }

    public void setUsePlotGradientColorr(String usePlotGradientColorr) {
        this.usePlotGradientColorr = usePlotGradientColorr;
    }

    public String getLegendBorderAlpha() {
        return legendBorderAlpha;
    }

    public void setLegendBorderAlpha(String legendBorderAlpha) {
        this.legendBorderAlpha = legendBorderAlpha;
    }

    public String getLegendShadow() {
        return legendShadow;
    }

    public void setLegendShadow(String legendShadow) {
        this.legendShadow = legendShadow;
    }

    public String getShowAxisLinesr() {
        return showAxisLinesr;
    }

    public void setShowAxisLinesr(String showAxisLinesr) {
        this.showAxisLinesr = showAxisLinesr;
    }

    public String getShowAlternateHGridColor() {
        return showAlternateHGridColor;
    }

    public void setShowAlternateHGridColor(String showAlternateHGridColor) {
        this.showAlternateHGridColor = showAlternateHGridColor;
    }

    public String getDivlineThickness() {
        return divlineThickness;
    }

    public void setDivlineThickness(String divlineThickness) {
        this.divlineThickness = divlineThickness;
    }

    public String getDivLineDashed() {
        return divLineDashed;
    }

    public void setDivLineDashed(String divLineDashed) {
        this.divLineDashed = divLineDashed;
    }

    public String getDivLineDashLen() {
        return divLineDashLen;
    }

    public void setDivLineDashLen(String divLineDashLen) {
        this.divLineDashLen = divLineDashLen;
    }

    public String getDivLineGapLen() {
        return divLineGapLen;
    }

    public void setDivLineGapLen(String divLineGapLen) {
        this.divLineGapLen = divLineGapLen;
    }

    public String getxAxisName() {
        return xAxisName;
    }

    public void setxAxisName(String xAxisName) {
        this.xAxisName = xAxisName;
    }

    public String getShowValues() {
        return showValues;
    }

    public void setShowValues(String showValues) {
        this.showValues = showValues;
    }

    public String getyAxisName() {
        return yAxisName;
    }

    public void setyAxisName(String yAxisName) {
        this.yAxisName = yAxisName;
    }

    public String getDrawAnchors() {
        return drawAnchors;
    }

    public void setDrawAnchors(String drawAnchors) {
        this.drawAnchors = drawAnchors;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public String getPlotBorderAlpha() {
        return plotBorderAlpha;
    }

    public void setPlotBorderAlpha(String plotBorderAlpha) {
        this.plotBorderAlpha = plotBorderAlpha;
    }

    public String getPlotFillAlpha() {
        return plotFillAlpha;
    }

    public void setPlotFillAlpha(String plotFillAlpha) {
        this.plotFillAlpha = plotFillAlpha;
    }

    public String getShowXAxisLine() {
        return showXAxisLine;
    }

    public void setShowXAxisLine(String showXAxisLine) {
        this.showXAxisLine = showXAxisLine;
    }

    public String getAxisLineAlpha() {
        return axisLineAlpha;
    }

    public void setAxisLineAlpha(String axisLineAlpha) {
        this.axisLineAlpha = axisLineAlpha;
    }

    public String getDivlineColor() {
        return divlineColor;
    }

    public void setDivlineColor(String divlineColor) {
        this.divlineColor = divlineColor;
    }

    public String getToolTipColor() {
        return toolTipColor;
    }

    public void setToolTipColor(String toolTipColor) {
        this.toolTipColor = toolTipColor;
    }

    public String getToolTipBorderThickness() {
        return toolTipBorderThickness;
    }

    public void setToolTipBorderThickness(String toolTipBorderThickness) {
        this.toolTipBorderThickness = toolTipBorderThickness;
    }

    public String getToolTipBgColo() {
        return toolTipBgColo;
    }

    public void setToolTipBgColo(String toolTipBgColo) {
        this.toolTipBgColo = toolTipBgColo;
    }

    public String getToolTipBgAlphas() {
        return toolTipBgAlphas;
    }

    public void setToolTipBgAlphas(String toolTipBgAlphas) {
        this.toolTipBgAlphas = toolTipBgAlphas;
    }

    public String getToolTipBorderRadiu() {
        return toolTipBorderRadiu;
    }

    public void setToolTipBorderRadiu(String toolTipBorderRadiu) {
        this.toolTipBorderRadiu = toolTipBorderRadiu;
    }

    public String getToolTipPadding() {
        return toolTipPadding;
    }

    public void setToolTipPadding(String toolTipPadding) {
        this.toolTipPadding = toolTipPadding;
    }
}

