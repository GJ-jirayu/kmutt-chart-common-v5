<%--
  Created by IntelliJ IDEA.
  User: imake
  Date: 07/09/2015
  Time: 19:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<c:set var="ns"><portlet:namespace/></c:set>
<html>
<head>
  <title></title>
  <script type="text/javascript" src="<c:url value="/resources/fusioncharts/js/fusioncharts.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/fusioncharts/js/themes/fusioncharts.theme.fint.js"/>"></script>
  <script type="text/javascript" src="<c:url value="/resources/js/jquery-1.11.2.min.js"/>"></script>
  <script type="text/javascript">
    <%-- pie3d column2d --%>
    FusionCharts.ready(function(){
      var dataSource;
      var chartype="";
      <c:if test="${dashboardForm.chartType=='column2d'}">
      chartype='column2d';
      dataSource={
        "chart": {
          "caption": "Top 5 Salespeople",
          "subcaption": "",
          "yaxisname": "Sales",
          // "numberprefix": "$",
          "plotgradientcolor": " ",
          "bgcolor": "FFFFFF",
          "showalternatehgridcolor": "0",
          "showplotborder": "0",
          "showvalues": "1",
          "labeldisplay": "WRAP",
          "divlinecolor": "CCCCCC",
          "canvasborderalpha": "0",
          "showcanvasborder": "0",
          "showborder": "0"
        },
        "data": [
          {
            "label": "Gerry",
            // "color": "ff9a00",
            "value": "11500"

          },
          {
            "label": "Harry",
            //"color": "ffcf00",
            "value": "11000"

          },
          {
            "label": "James",
            //"color": "9ccf31",
            "value": "9000"

          },
          {
            "label": "Rita",
            //"color": "cecf31",
            "value": "7000"

          },
          {
            "label": "Jenny",
            //"color": "5fd3d2",
            "value": "6000"

          }
        ],
        "trendlines": [
          {
            "line": [
              {
                "startvalue": "9900",
                "endvalue": "10500",
                "istrendzone": "1",
                "displayvalue": "Target{br}Range",
                "tooltext": "Target revenue range",
                "alpha": "20",
                "valueonright": "1"
              }
            ]
          }
        ]
      };
      </c:if>

      <c:if test="${dashboardForm.chartType=='pie3d'}">
      chartype='pie3d';
      dataSource={
        "chart": {
          "caption": "Age profile of website visitors",
          "subcaption": "Last Year",
          "startingangle": "120",
          "showlabels": "0",
          "showlegend": "1",
          "enablemultislicing": "0",
          "slicingdistance": "15",
          "showpercentvalues": "1",
          "showpercentintooltip": "0",
          "plottooltext": "Age group :",
          "theme": "fint"
        },
        "data": [
          {
            "label": "Teenage",
            "value": "1250400"
          },
          {
            "label": "Adult",
            "value": "1463300"
          },
          {
            "label": "Mid-age",
            "value": "1050700"
          },
          {
            "label": "Senior",
            "value": "491000"
          }
        ]
      };
      </c:if>
      <c:if test="${dashboardForm.chartType=='angulargauge'}">

      chartype='angulargauge';
      dataSource=
      {
        "chart": {
          "caption": "Customer Satisfaction Score",
          "lowerlimit": "0",
          "upperlimit": "100",
          "lowerlimitdisplay": "Bad",
          "upperlimitdisplay": "Good",
          "palette": "1",
          "numbersuffix": "%",
          "tickvaluedistance": "10",
          "showvalue": "0",
          "gaugeinnerradius": "0",
          "bgcolor": "FFFFFF",
          "pivotfillcolor": "333333",
          "pivotradius": "8",
          "pivotfillmix": "333333, 333333",
          "pivotfilltype": "radial",
          "pivotfillratio": "0,100",
          "showtickvalues": "1",
          "showborder": "0"
        },
        "colorrange": {
          "color": [
            {
              "minvalue": "0",
              "maxvalue": "45",
              "code": "e44a00"
            },
            {
              "minvalue": "45",
              "maxvalue": "75",
              "code": "f8bd19"
            },
            {
              "minvalue": "75",
              "maxvalue": "100",
              "code": "6baa01"
            }
          ]
        },
        "dials": {
          "dial": [
            {
              "value": "92",
              "rearextension": "15",
              "radius": "100",
              "bgcolor": "333333",
              "bordercolor": "333333",
              "basewidth": "8"
            }
          ]
        }
      }
      </c:if>
      <c:if test="${dashboardForm.chartType=='pyramid'}">

      chartype='pyramid';
      dataSource=
      {
        "chart": {
          "bgcolor": "FFFFFF",
          "caption": "Revenue distribution for 2013",
          "basefontcolor": "333333",
          "decimals": "0",
          "numbersuffix": "M",
          "numberprefix": "$",
          "pyramidyscale": "40",
          "chartbottommargin": "0",
          "captionpadding": "0",
          "showBorder": "0"
        },
        "data": [
          {
            "value": "17",
            "name": "Products",
            "color": "008ee4"
          },
          {
            "value": "21",
            "name": "Services",
            "color": "6baa01"
          },
          {
            "value": "20",
            "name": "Consultancy",
            "color": "f8bd19"
          },
          {
            "value": "5",
            "name": "Others",
            "color": "e44a00"
          }
        ]
      }
      </c:if>
      <c:if test="${dashboardForm.chartType=='mscolumn3dlinedy'}">

      chartype='mscolumn3dlinedy';
      dataSource=
      {
        "chart": {
          "caption": "ค่าสิ่งก่อสร้าง จำแนกตามแหล่งเงินรัฐและเงินรายได้",
          "subCaption": "(กุมภาพันธ์ 2558)",
          "xAxisname": "", // Quarter
          "pYAxisName": "ล้านบาท",
          "sYAxisName": "Percent %",
          //  "numberPrefix": "$",
          "sNumberSuffix": "%",
          "sYAxisMaxValue": "25",
          "paletteColors": "#0075c2,#1aaf5d,#f2c500,#99FF66",
          "bgColor": "#ffffff",
          "showBorder": "0",
          "showCanvasBorder": "0",
          "usePlotGradientColor": "0",
          "plotBorderAlpha": "10",
          "legendBorderAlpha": "0",
          "legendBgAlpha": "0",
          "legendShadow": "0",
          "showHoverEffect": "1",
          "valueFontColor": "#ffffff",
          "rotateValues": "1",//1,0
          "placeValuesInside": "1",
          "divlineColor": "#999999",
          "divLineDashed": "1",
          "divLineDashLen": "1",
          "divLineGapLen": "1",
          "canvasBgColor": "#ffffff",
          "captionFontSize": "14",
          "subcaptionFontSize": "14",
          "subcaptionFontBold": "0"
        },
        "categories": [
          {
            "category": [
              {
                "label": "เงินรัฐ"
              },
              {
                "label": "เงินรายได้"
              },
              {
                "label": "รวม"
              }
            ]
          }
        ],
        "dataset": [
          {
            "seriesname": "ปี 2558 แผน",
            "data": [
              {
                "value": "11000"
              },
              {
                "value": "14000"
              },
              {
                "value": "10500"
              }
            ]
          },
          {
            "seriesname": "ปี 2558 ผล",
            "data": [
              {
                "value": "14400"
              },
              {
                "value": "14800"
              },
              {
                "value": "8300"
              }
            ]
          },
          {
            "seriesname": "Profit %",
            "renderAs": "line", // line,column,area
            "parentYAxis": "S",
            "showValues": "1",

            "data": [
              {
                "value": "0"
              },
              {
                "value": "16"
              },
              {
                "value": "15"
              }
            ]
          }
        ]
      }
      </c:if>
      <c:if test="${dashboardForm.chartType=='mscombidy2d'}">

      chartype='mscombidy2d';
      dataSource=
      {
        "chart": {
          "caption": "ค่าสิ่งก่อสร้าง จำแนกตามแหล่งเงินรัฐและเงินรายได้",
          "subCaption": "(กุมภาพันธ์ 2558)",
          "xAxisname": "", // Quarter
          "pYAxisName": "ล้านบาท",
          "sYAxisName": "Percent %",
          // "numberPrefix": "$",
          "sNumberSuffix": "%",
          "sYAxisMaxValue": "50",
          "paletteColors": "#0075c2,#1aaf5d,#f2c500",
          "baseFontColor": "#333333",
          "baseFont": "Helvetica Neue,Arial",
          "captionFontSize": "14",
          "subcaptionFontSize": "14",
          "subcaptionFontBold": "0",
          "showBorder": "0",
          "bgColor": "#ffffff",
          "showShadow": "0",
          "canvasBgColor": "#ffffff",
          "canvasBorderAlpha": "0",
          "divlineAlpha": "100",
          "divlineColor": "#999999",
          "divlineThickness": "1",
          "divLineDashed": "1",
          "divLineDashLen": "1",
          "divLineGapLen": "1",
          "usePlotGradientColor": "0",
          "showplotborder": "0",
          "showXAxisLine": "1",
          "xAxisLineThickness": "1",
          "xAxisLineColor": "#999999",
          "showAlternateHGridColor": "0",
          "showAlternateVGridColor": "0",
          "legendBgAlpha": "0",
          "legendBorderAlpha": "0",
          "legendShadow": "0",
          "legendItemFontSize": "10",
          "legendItemFontColor": "#666666"
        },
        "categories": [
          {
            "category": [
              {
                "label": "เงินรัฐ"
              },
              {
                "label": "เงินรายได้"
              },
              {
                "label": "รวม"
              }
            ]
          }
        ],
        "dataset": [
          {
            "seriesname": "ปี 2558 แผน",
            "data": [
              {
                "value": "11000"
              },
              {
                "value": "14000"
              },
              {
                "value": "10500"
              }
            ]
          },
          {
            "seriesname": "ปี 2558 ผล",
            "data": [
              {
                "value": "14400"
              },
              {
                "value": "14800"
              },
              {
                "value": "8300"
              }
            ]
          },


          {
            "seriesName": "Percent %",
            "parentYAxis": "S",
            "renderAs": "Line",
            "showValues": "1",
            "data": [
              {
                "value": "25"
              },
              {
                "value": "25"
              },
              {
                "value": "16.66"
              }
            ]
          }
        ]
      }
      </c:if>

      <c:if test="${dashboardForm.chartType=='mscolumn2d'}">

      chartype='mscolumn2d';
      dataSource=
      {
        "chart": {
          "caption": "จำนวนพนักงานและข้าราชการสายวิชาการ\nจำแนกตามช่วงอายุและตำแหน่งวิชาการ",
          "xAxisname": "จำนวน",
          //"yAxisName": "Revenues (In USD)",
          "yAxisName": "",
          //"numberPrefix": "$",
          "plotFillAlpha": "80",
          "paletteColors": "#0075c2,#1aaf5d,#f2c500,#99CCFF",
          "baseFontColor": "#333333",
          "baseFont": "Helvetica Neue,Arial",
          "captionFontSize": "14",
          "subcaptionFontSize": "14",
          "subcaptionFontBold": "0",
          "showBorder": "0",
          "bgColor": "#ffffff",
          "showShadow": "0",
          "canvasBgColor": "#ffffff",
          "canvasBorderAlpha": "0",
          "divlineAlpha": "100",
          "divlineColor": "#999999",
          "divlineThickness": "1",
          "divLineDashed": "1",
          "divLineDashLen": "1",
          "divLineGapLen": "1",
          "usePlotGradientColor": "0",
          "showplotborder": "0",
          "valueFontColor": "#ffffff",
          "placeValuesInside": "1",
          "showHoverEffect": "1",
          //"rotateValues": "1",
          "showXAxisLine": "1",
          "xAxisLineThickness": "1",
          "xAxisLineColor": "#999999",
          "showAlternateHGridColor": "0",
          "legendBgAlpha": "0",
          "legendBorderAlpha": "0",
          "legendShadow": "0",
          "legendItemFontSize": "10",
          "legendItemFontColor": "#666666"
        },
        "categories": [
          {
            "category": [
              {
                "label": "26-30"
              },
              {
                "label": "31-35"
              },
              {
                "label": "36-40"
              },
              {
                "label": "41-45"
              }
            ]
          }
        ],
        "dataset": [
          {
            "seriesname": "อาจารย์และนักวิจัย",
            "data": [
              {
                "value": "21"
              },
              {
                "value": "107"
              },
              {
                "value": "109"
              },
              {
                "value": "67"
              }
            ]
          },
          {
            "seriesname": "ผู้ช่วยศาสตราจารย์",
            "data": [
              {
                "value": "0"
              },
              {
                "value": "8"
              },
              {
                "value": "55"
              },
              {
                "value": "61"
              }
            ]
          },
          {
            "seriesname": "รองศาสตราจารย์",
            "data": [
              {
                "value": "0"
              },
              {
                "value": "0"
              },
              {
                "value": "11"
              },
              {
                "value": "26"
              }
            ]
          },
          {
            "seriesname": "สตราจารย์",
            "data": [
              {
                "value": "0"
              },
              {
                "value": "0"
              },
              {
                "value": "0"
              },
              {
                "value": "2"
              }
            ]
          }
        ]
        /*,
         "trendlines": [
         {
         "line": [
         {
         "startvalue": "12250",
         "color": "#0075c2",
         "displayvalue": "Previous{br}Average",
         "valueOnRight": "1",
         "thickness": "1",
         "showBelow": "1",
         "tooltext": "Previous year quarterly target  : $13.5K"
         },
         {
         "startvalue": "25950",
         "color": "#1aaf5d",
         "displayvalue": "Current{br}Average",
         "valueOnRight": "1",
         "thickness": "1",
         "showBelow": "1",
         "tooltext": "Current year quarterly target  : $23K"
         }
         ]
         }
         ]
         */
      }
      </c:if>
      <c:if test="${dashboardForm.chartType=='mscombi2d'}">

      chartype='mscombi2d';
      dataSource={
        "chart": {

          "caption": "จำนวนพนักงานและข้าราชการสายวิชาการ",
          "subCaption": "จำแนกตามช่วงอายุและตำแหน่งวิชาการ",
          "xAxisname": "จำนวน",
          "yAxisName": "",
          //"numberPrefix": "$",
          "showBorder": "0",
          "showValues": "0",
          "paletteColors": "#0075c2,#1aaf5d,#f2c500,#99CCFF",
          "bgColor": "#ffffff",
          "showCanvasBorder": "0",
          "canvasBgColor": "#ffffff",
          "captionFontSize": "14",
          "subcaptionFontSize": "14",
          "subcaptionFontBold": "0",
          "divlineColor": "#999999",
          "divLineDashed": "1",
          "divLineDashLen": "1",
          "divLineGapLen": "1",
          "showAlternateHGridColor": "0",
          "usePlotGradientColor": "0",
          "toolTipColor": "#ffffff",
          "toolTipBorderThickness": "0",
          "toolTipBgColor": "#000000",
          "toolTipBgAlpha": "80",
          "toolTipBorderRadius": "2",
          "toolTipPadding": "5",
          "legendBgColor": "#ffffff",
          "legendBorderAlpha": "0",
          "legendShadow": "0",
          "legendItemFontSize": "10",
          "legendItemFontColor": "#666666"
        },
        "categories": [
          {
            "category": [
              {
                "label": "26-30"
              },
              {
                "label": "31-35"
              },
              {
                "label": "36-40"
              },
              {
                "label": "41-45"
              }
            ]
          }
        ],
        "dataset": [
          {
            "seriesname": "อาจารย์และนักวิจัย",
            "showValues": "1",
            "data": [
              {
                "value": "21"
              },
              {
                "value": "107"
              },
              {
                "value": "109"
              },
              {
                "value": "67"
              }
            ]
          },
          {
            "seriesname": "ผู้ช่วยศาสตราจารย์",
            "showValues": "1",
            "data": [
              {
                "value": "0"
              },
              {
                "value": "8"
              },
              {
                "value": "55"
              },
              {
                "value": "61"
              }
            ]
          },
          {
            "seriesname": "รองศาสตราจารย์",
            "showValues": "1",
            "data": [
              {
                "value": "0"
              },
              {
                "value": "0"
              },
              {
                "value": "11"
              },
              {
                "value": "26"
              }
            ]
          },
          {
            "seriesname": "สตราจารย์",
            "showValues": "1",
            "data": [
              {
                "value": "0"
              },
              {
                "value": "0"
              },
              {
                "value": "0"
              },
              {
                "value": "2"
              }
            ]
          }

        ]
      }

      </c:if>

      <c:if test="${dashboardForm.chartType=='pie2d'}">

      chartype='pie2d';
      dataSource={
        "chart": {
          "caption": "เงินสะสมปลอดภาระ ณ กุมภาพันธ์ 2558",
          "subCaption": "รวมทั้งสิ้น 3,000 ล้านบาท",
          //   "paletteColors": "#0075c2,#1aaf5d,#f2c500,#f45b00,#8e0000",
          "bgColor": "#ffffff",
          "showBorder": "0",
          "use3DLighting": "0",
          "showShadow": "0",
          "enableSmartLabels": "0",
          "startingAngle": "0",
          // "showPercentValues": "1",
          "showPercentInTooltip": "0",
          "decimals": "1",
          "captionFontSize": "14",
          "subcaptionFontSize": "14",
          "subcaptionFontBold": "0",
          "toolTipColor": "#ffffff",
          "toolTipBorderThickness": "0",
          "toolTipBgColor": "#000000",
          "toolTipBgAlpha": "80",
          "toolTipBorderRadius": "2",
          "toolTipPadding": "5",
          "showHoverEffect": "1",
          "showLegend": "1",
          "legendBgColor": "#ffffff",
          "legendBorderAlpha": "0",
          "legendShadow": "0",
          "legendItemFontSize": "10",
          "legendItemFontColor": "#666666",
          "useDataPlotColorForLabels": "1"
        },
        "data": [
          {
            "label": "LoLA",
            "value": "100"
          },
          {
            "label": "FoE",
            "value": "450"
          },
          {
            "label": "FSC",
            "value": "100"
          },
          {
            "label": "FIET",
            "value": "120"
          },
          {
            "label": "GMI",
            "value": "80"
          },
          {
            "label": "SIT",
            "value": "150"
          }
        ]
      }

      </c:if>

      <c:if test="${dashboardForm.chartType=='pie3d'}">

      chartype='pie3d';
      dataSource={
        "chart": {
          "caption": "เงินสะสมปลอดภาระ ณ กุมภาพันธ์ 2558",
          "subCaption": "รวมทั้งสิ้น 3,000 ล้านบาท",
          //  "paletteColors": "#0075c2,#1aaf5d,#f2c500,#f45b00,#8e0000",
          "bgColor": "#ffffff",
          "showBorder": "0",
          "use3DLighting": "0",
          "showShadow": "0",
          "enableSmartLabels": "0",
          "startingAngle": "0",
          //"showPercentValues": "1",
          "showPercentInTooltip": "0",
          "decimals": "1",
          "captionFontSize": "14",
          "subcaptionFontSize": "14",
          "subcaptionFontBold": "0",
          "toolTipColor": "#ffffff",
          "toolTipBorderThickness": "0",
          "toolTipBgColor": "#000000",
          "toolTipBgAlpha": "80",
          "toolTipBorderRadius": "2",
          "toolTipPadding": "5",
          "showHoverEffect": "1",
          "showLegend": "1",
          "legendBgColor": "#ffffff",
          "legendBorderAlpha": "0",
          "legendShadow": "0",
          "legendItemFontSize": "10",
          "legendItemFontColor": "#666666",
          "useDataPlotColorForLabels": "1"
        },
        "data": [
          {
            "label": "LoLA",
            "value": "100"
          },
          {
            "label": "FoE",
            "value": "450"
          },
          {
            "label": "FSC",
            "value": "100"
          },
          {
            "label": "FIET",
            "value": "120"
          },
          {
            "label": "GMI",
            "value": "80"
          },
          {
            "label": "SIT",
            "value": "150"
          }
        ]
      }

      </c:if>
      <c:if test="${dashboardForm.chartType=='multilevelpie'}">

      chartype='multilevelpie';
      dataSource={
        "chart": {
          "caption": "เงินสะสมปลอดภาระ ณ กุมภาพันธ์ 2558",
          "subCaption": "รวมทั้งสิ้น 3,000 ล้านบาท",
          "captionFontSize": "14",
          "subcaptionFontSize": "14",
          "baseFontColor": "#333333",
          "baseFont": "Helvetica Neue,Arial",
          "basefontsize": "9",
          "subcaptionFontBold": "0",
          "bgColor": "#ffffff",
          "canvasBgColor": "#ffffff",
          "showBorder": "0",
          "showShadow": "0",
          "showCanvasBorder": "0",
          "pieFillAlpha": "60",
          "pieBorderThickness": "2",
          "hoverFillColor": "#cccccc",
          "pieBorderColor": "#ffffff",
          "useHoverColor": "1",
          "showValuesInTooltip": "1",
          "showPercentInTooltip": "0",
          "showLegend": "1",
          "showValues":"1",
          "showLabels":"1"
          // "numberPrefix": "$",
          //  "plotTooltext": "$label, $$valueK, $percentValue"
        },
        "category": [
          {
            //   "label": "Sales by category",
            "color": "#ffffff",
            //  "value": "1000",
            "category": [
              {
                "label": "มหาวิทยาลัย",
                "color": "#f8bd19",
                "value": "770",
                "category": [
                  {
                    "label": "LoLA",
                    "color": "#f8bd19",
                    "value": "100"
                  },

                  {
                    "label": "FoE",
                    "color": "#f8bd19",
                    "value": "450"
                  },
                  {
                    "label": "FSC",
                    "color": "#f8bd19",
                    "value": "100"
                  },
                  {
                    "label": "FIET",
                    "color": "#f8bd19",
                    "value": "120"
                  }

                ]
              },
              {
                "label": "หน่วยงาน",
                "color": "#e44a00",
                "value": "230",
                "category": [
                  {
                    "label": "GMI",
                    "color": "#e44a00",
                    "value": "80"
                  },
                  {
                    "label": "SIT",
                    "color": "#e44a00",
                    "value": "150"
                  }
                ]
              }
            ]
          }
        ]
      }
      </c:if>

      <c:if test="${dashboardForm.chartType=='angulargauge'}">

      chartype='angulargauge';
      dataSource={
        "chart": {
          "caption": "Customer Satisfaction Score",
          "subcaption": "Los Angeles Topanga",
          "plotToolText": "Current Score: $value",
          "gaugeFillMix": "{dark-40},{light-40},{dark-20}",
          "theme": "fint",
          "gaugeOriginY": "230",
          "showValue": "1"
        },
        "colorRange": {
          "color": [
            {
              "minValue": "0",
              "maxValue": "4.5",
              "code": "#e44a00"
            },
            {
              "minValue": "4.5",
              "maxValue": "7.5",
              "code": "#f8bd19"
            },
            {
              "minValue": "7.5",
              "maxValue": "10",
              "code": "#6baa01"
            }
          ]
        },
        "dials": {
          "dial": [
            {
              "value": "8.9"
            }
          ]
        },
        "trendPoints": {
          "point": [
            {
              "startValue": "6.8",
              "color": "#0075c2",
              "dashed": "1"
            },
            {
              "startValue": "9.5",
              "color": "#0075c2",
              "dashed": "1"
            },
            {
              "startValue": "6.8",
              "endValue": "9.5",
              "color": "#0075c2",
              "radius": "185",
              "innerRadius": "80"
            }
          ]
        },
        "annotations": {
          "origw": "450",
          "origh": "300",
          "autoscale": "1",
          "showBelow": "0",
          "groups": [
            {
              "id": "arcs",
              "items": [
                {
                  "id": "national-cs-bg",
                  "type": "rectangle",
                  "x": "$chartCenterX+2",
                  "y": "$chartEndY - 45",
                  "tox": "$chartCenterX + 130",
                  "toy": "$chartEndY - 25",
                  "fillcolor": "#f8bd19"
                },
                {
                  "id": "national-cs-text",
                  "type": "Text",
                  "color": "#ffffff",
                  "label": "National Average : 7.2",
                  "fontSize": "12",
                  "align": "left",
                  "x": "$chartCenterX + 7",
                  "y": "$chartEndY - 35"
                },
                {
                  "id": "state-cs-bg",
                  "type": "rectangle",
                  "x": "$chartCenterX-2",
                  "y": "$chartEndY - 45",
                  "tox": "$chartCenterX - 103",
                  "toy": "$chartEndY - 25",
                  "fillcolor": "#6baa01"
                },
                {
                  "id": "state-cs-text",
                  "type": "Text",
                  "color": "#ffffff",
                  "label": "State Average : 8",
                  "fontSize": "12",
                  "align": "right",
                  "x": "$chartCenterX - 7",
                  "y": "$chartEndY - 35"
                },
                {
                  "id": "store-cs-bg",
                  "type": "rectangle",
                  "x": "$chartCenterX-130",
                  "y": "$chartEndY - 22",
                  "tox": "$chartCenterX + 150",
                  "toy": "$chartEndY - 2",
                  "fillcolor": "#0075c2"
                },
                {
                  "id": "state-cs-text",
                  "type": "Text",
                  "color": "#ffffff",
                  "label": "Store's Customer Satisfaction Range: 6.8 to 9.5",
                  "fontSize": "12",
                  "align": "center",
                  "x": "$chartCenterX + 10",
                  "y": "$chartEndY - 12"
                }
              ]
            }
          ]
        }
      }
      </c:if>

      <c:if test="${dashboardForm.chartType=='msline'}">

      chartype='msline';
      dataSource={
        "chart": {
          "caption": "Number of visitors last week",
          "subCaption": "Bakersfield Central vs Los Angeles Topanga",
          "captionFontSize": "14",
          "subcaptionFontSize": "14",
          "subcaptionFontBold": "0",
          "paletteColors": "#0075c2,#1aaf5d",
          "bgcolor": "#ffffff",
          "showBorder": "0",
          "showShadow": "0",
          "showCanvasBorder": "0",
          "usePlotGradientColor": "0",
          "legendBorderAlpha": "0",
          "legendShadow": "0",
          "showAxisLines": "0",
          "showAlternateHGridColor": "0",
          "divlineThickness": "1",
          "divLineDashed": "1",
          "divLineDashLen": "1",
          "divLineGapLen": "1",
          "xAxisName": "Day",
          "showValues": "0"
        },
        "categories": [
          {
            "category": [
              {
                "label": "Mon"
              },
              {
                "label": "Tue"
              },
              {
                "label": "Wed"
              },
              {
                /*
                 "vline": "true",

                 "lineposition": "0",
                 "color": "#6baa01",
                 "labelHAlign": "center",
                 "labelPosition": "0",
                 */
                "label": "National holiday"
                // "dashed": "1"
              },
              {
                "label": "Thu"
              },
              {
                "label": "Fri"
              },
              {
                "label": "Sat"
              },
              {
                "label": "Sun"
              }
            ]
          }
        ],
        "dataset": [
          {
            "seriesname": "Bakersfield Central",
            "data": [
              {
                "value": "15123"
              },
              {
                "value": "14233"
              },
              {
                "value": "25507"
              },
              {
                "value": "9110"
              },
              {
                "value": "15529"
              },
              {
                "value": "20803"
              },
              {
                "value": "19202"
              }
            ]
          },
          {
            "seriesname": "Los Angeles Topanga",
            "data": [
              {
                "value": "13400"
              },
              {
                "value": "12800"
              },
              {
                "value": "22800"
              },
              {
                "value": "12400"
              },
              {
                "value": "15800"
              },
              {
                "value": "19800"
              },
              {
                "value": "21800"
              }
            ]
          }
        ]
        /*,
         "trendlines": [
         {
         "line": [
         {
         "startvalue": "17022",
         "color": "#6baa01",
         "valueOnRight": "1",
         "displayvalue": "Average"
         }
         ]
         }
         ]
         */
      }
      </c:if>

      <c:if test="${dashboardForm.chartType=='MSArea'}">

      chartype='MSArea';
      dataSource={
        "chart": {
          "caption": "Sales of Liquor",
          "subCaption": "Previous week vs current week",
          "xAxisName": "Day",
          "yAxisName": "Sales (In USD)",
          "drawAnchors":"1",
          "paletteColors": "#0075c2,#1aaf5d",
          "bgColor": "#ffffff",
          "showBorder": "0",
          "showCanvasBorder": "0",
          "plotBorderAlpha": "10",
          "usePlotGradientColor": "0",
          "legendBorderAlpha": "0",
          "legendShadow": "1",
          "plotFillAlpha": "60",
          "showXAxisLine": "1",
          "axisLineAlpha": "25",
          "showValues": "0",
          "captionFontSize": "14",
          "subcaptionFontSize": "14",
          "subcaptionFontBold": "0",
          "divlineColor": "#999999",
          "divLineDashed": "1",
          "divLineDashLen": "1",
          "divLineGapLen": "1",
          "showAlternateHGridColor": "0",
          "toolTipColor": "#ffffff",
          "toolTipBorderThickness": "0",
          "toolTipBgColor": "#000000",
          "toolTipBgAlpha": "80",
          "toolTipBorderRadius": "2",
          "toolTipPadding": "5"
        },
        "categories": [
          {
            "category": [
              {
                "label": "Mon"
              },
              {
                "label": "Tue"
              },
              {
                "label": "Wed"
              },
              {
                "label": "Thu"
              },
              {
                "label": "Fri"
              },
              {
                "label": "Sat"
              },
              {
                "label": "Sun"
              }
            ]
          }
        ],
        "dataset": [
          {
            "seriesname": "Previous Week",
            "data": [
              {
                "value": "13000"
              },
              {
                "value": "14500"
              },
              {
                "value": "13500"
              },
              {
                "value": "15000"
              },
              {
                "value": "15500"
              },
              {
                "value": "17650"
              },
              {
                "value": "19500"
              }
            ]
          },
          {
            "seriesname": "Current Week",
            "data": [
              {
                "value": "8400"
              },
              {
                "value": "9800"
              },
              {
                "value": "11800"
              },
              {
                "value": "14400"
              },
              {
                "value": "18800"
              },
              {
                "value": "24800"
              },
              {
                "value": "30800"
              }
            ]
          }
        ]
      }
      </c:if>

      <c:if test="${dashboardForm.chartType=='hbullet'}">

      chartype='hbullet';
      dataSource={
        "chart": {
          "lowerLimit": "0",
          "upperLimit": "100",
          "caption": "Monthly Revenue",
          "subcaption": "Actual vs Target<br>Bakersfield Central",
          "numberPrefix": "$",
          "numberSuffix": "K",
          "plotFillColor": "#0075c2",
          "targetColor": "#8e0000",
          "showHoverEffect": "1",
          "showBorder": "0",
          "bgColor": "#ffffff",
          "showShadow": "0",
          "colorRangeFillMix": "{light+0}",
          "valuePadding": "7"
        },
        "colorRange": {
          "color": [
            {
              "minValue": "0",
              "maxValue": "50",
              "code": "#e44a00",
              "alpha": "70"
            },
            {
              "minValue": "50",
              "maxValue": "75",
              "code": "#f2c500",
              "alpha": "70"
            },
            {
              "minValue": "75",
              "maxValue": "120",
              "code": "#1aaf5d",
              "alpha": "70"
            }
          ]
        },
        "value": "82",
        "target": "90"
      }
      </c:if>

      <c:if test="${dashboardForm.chartType=='bar2d'}">

      chartype='bar2d';
      dataSource={
        "chart": {
          "caption": "Top 10 Most Popular Sports in the World",
          "subcaption": "Based on number of viewers",
          "yaxisname": "Number of Viewers",
          "plotgradientcolor": "",
          "bgcolor": "FFFFFF",
          "showplotborder": "0",
          "divlinecolor": "CCCCCC",
          "showvalues": "1",
          "showcanvasborder": "0",
          "canvasbordercolor": "CCCCCC",
          "canvasborderthickness": "1",
          "showyaxisvalues": "0",
          "showlegend": "1",
          "showshadow": "0",
          "labelsepchar": ": ",
          "basefontcolor": "000000",
          "labeldisplay": "AUTO",
          "numberscalevalue": "1000,1000,1000",
          "numberscaleunit": "K,M,B",
          "palettecolors": "#008ee4,#9b59b6,#6baa01,#e44a00,#f8bd19,#d35400,#bdc3c7,#95a5a6,#34495e,#1abc9c",
          "showborder": "0"
        },
        "data": [
          {
            "label": "Football",
            "value": "3500000000",
            "tooltext": "Popular in: {br}Europe{br}Africa{br}Asia{br}Americas"
          },
          {
            "label": "Cricket",
            "value": "3000000000",
            "tooltext": "Popular in: {br}India{br}UK{br}Pakistan{br}Australia"
          },
          {
            "label": "Field Hockey",
            "value": "2200000000",
            "tooltext": "Popular in: {br}Asia{br}Europe{br}Africa{br}Australia"
          },
          {
            "label": "Tennis",
            "value": "1000000000",
            "color": "e44a00",
            "tooltext": "Popular in: {br}Europe{br}Americas{br}Asia"
          },
          {
            "label": "Volleyball",
            "value": "900000000",
            "tooltext": "Popular in: {br}Asia{br}Europe{br}Americas{br}Australia"
          },
          {
            "label": "Table Tennis",
            "value": "900000000",
            "tooltext": "Popular in: {br}Asia{br}Europe{br}Africa{br}Americas"
          },
          {
            "label": "Baseball",
            "value": "500000000",
            "tooltext": "Popular in: {br}US{br}Japan{br}Cuba{br}Dominican Republic"
          },
          {
            "label": "Golf",
            "value": "400000000",
            "tooltext": "Popular in: {br}US{br}Canada{br}Europe"
          },
          {
            "label": "Basketball",
            "value": "400000000",
            "tooltext": "Popular in: {br}US{br}Canada"
          },
          {
            "label": "American football",
            "value": "390000000",
            "tooltext": "Popular in:{br}US"
          }
        ]
      }
      </c:if>

      <c:if test="${dashboardForm.chartType=='stackedcolumn2d'}">

      chartype='stackedcolumn2d';
      dataSource={
        "chart": {
          "caption": "Visits from search engines",
          "showvalues": "0",
          "plotgradientcolor": "",
          "formatnumberscale": "0",
          "showplotborder": "0",
          "palettecolors": "#EED17F,#97CBE7,#074868,#B0D67A,#2C560A,#DD9D82",
          "canvaspadding": "0",
          "bgcolor": "FFFFFF",
          "showalternatehgridcolor": "0",
          "divlinecolor": "CCCCCC",
          "showcanvasborder": "0",
          "legendborderalpha": "0",
          "legendshadow": "0",
          "interactivelegend": "0",
          "showpercentvalues": "1",
          "showsum": "1",
          "canvasborderalpha": "0",
          "showborder": "0"
        },
        "categories": [
          {
            "category": [
              {
                "label": "Jan"
              },
              {
                "label": "Feb"
              },
              {
                "label": "Mar"
              },
              {
                "label": "Apr"
              },
              {
                "label": "May"
              },
              {
                "label": "Jun"
              },
              {
                "label": "Jul"
              },
              {
                "label": "Aug"
              },
              {
                "label": "Sep"
              },
              {
                "label": "Oct"
              },
              {
                "label": "Nov"
              },
              {
                "label": "Dec"
              }
            ]
          }
        ],
        "dataset": [
          {
            "seriesname": "Google",
            "renderas": "Area",
            "data": [
              {
                "value": "5040"
              },
              {
                "value": "4794"
              },
              {
                "value": "5026"
              },
              {
                "value": "5341"
              },
              {
                "value": "4800"
              },
              {
                "value": "4507"
              },
              {
                "value": "5701"
              },
              {
                "value": "4671"
              },
              {
                "value": "4980"
              },
              {
                "value": "4041"
              },
              {
                "value": "3813"
              },
              {
                "value": "3691"
              }
            ]
          },
          {
            "seriesname": "Yahoo",
            "renderas": "Area",
            "data": [
              {
                "value": "1200"
              },
              {
                "value": "1124"
              },
              {
                "value": "1006"
              },
              {
                "value": "921"
              },
              {
                "value": "1500"
              },
              {
                "value": "1007"
              },
              {
                "value": "921"
              },
              {
                "value": "971"
              },
              {
                "value": "1080"
              },
              {
                "value": "1041"
              },
              {
                "value": "1113"
              },
              {
                "value": "1091"
              }
            ]
          },
          {
            "seriesname": "MSN",
            "renderas": "Area",
            "data": [
              {
                "value": "400"
              },
              {
                "value": "524"
              },
              {
                "value": "606"
              },
              {
                "value": "421"
              },
              {
                "value": "500"
              },
              {
                "value": "707"
              },
              {
                "value": "621"
              },
              {
                "value": "471"
              },
              {
                "value": "680"
              },
              {
                "value": "441"
              },
              {
                "value": "713"
              },
              {
                "value": "491"
              }
            ]
          },
          {
            "seriesname": "Others",
            "renderas": "Area",
            "data": [
              {
                "value": "765"
              },
              {
                "value": "571"
              },
              {
                "value": "532"
              },
              {
                "value": "411"
              },
              {
                "value": "500"
              },
              {
                "value": "407"
              },
              {
                "value": "421"
              },
              {
                "value": "551"
              },
              {
                "value": "600"
              },
              {
                "value": "541"
              },
              {
                "value": "613"
              },
              {
                "value": "591"
              }
            ]
          }
        ]
      }
      </c:if>
      <c:if test="${dashboardForm.chartType=='stackedcolumn2d_v2'}">

      chartype='stackedcolumn2d';
      dataSource={
        "chart": {
          "caption": "Product-wise quarterly revenue in current year",
          "subCaption": "Harry's SuperMart",
          "xAxisname": "Quarter",
          "yAxisName": "Revenue (In USD)",
          "numberPrefix": "$",
          "paletteColors": "#0075c2,#1aaf5d",
          "bgColor": "#ffffff",
          "borderAlpha": "20",
          "showCanvasBorder": "0",
          "usePlotGradientColor": "0",
          "plotBorderAlpha": "10",
          "legendBorderAlpha": "0",
          "legendShadow": "0",
          "valueFontColor": "#ffffff",
          "showXAxisLine": "1",
          "xAxisLineColor": "#999999",
          "divlineColor": "#999999",
          "divLineDashed": "1",
          "showAlternateHGridColor": "0",
          "subcaptionFontBold": "0",
          "subcaptionFontSize": "14",
          "showHoverEffect": "1"
        },
        "categories": [
          {
            "category": [
              {
                "label": "Q1"
              },
              {
                "label": "Q2"
              },
              {
                "label": "Q3"
              },
              {
                "label": "Q4"
              }
            ]
          }
        ],
        "dataset": [
          {
            "seriesname": "Food Products",
            "data": [
              {
                "value": "121000"
              },
              {
                "value": "135000"
              },
              {
                "value": "123500"
              },
              {
                "value": "145000"
              }
            ]
          },
          {
            "seriesname": "Non-Food Products",
            "data": [
              {
                "value": "131400"
              },
              {
                "value": "154800"
              },
              {
                "value": "98300"
              },
              {
                "value": "131800"
              }
            ]
          }
        ]
      }
      </c:if>
      <%--
       if(chartype.length>0){
       var revenueChart = new FusionCharts({
         "type": chartype,
         "renderAt": "${ns}chartContainer",
         "width": "500",
         "height": "300",
         "dataFormat": "json",
         "dataSource":dataSource

       });
       revenueChart.render();
       }
       --%>
      var chartHeight="300";
      <c:if test="${dashboardForm.chartType=='gantt'}">
      chartHeight="600";

      </c:if>
      <c:if test="${not empty dashboardForm.jsonStr}">
      var revenueChart = new FusionCharts({
        "type": "${dashboardForm.chartType}",
        "renderAt": "${ns}chartContainer",
        "width": "100%", // 500
        "height": chartHeight,
        "dataFormat": "json",
        "dataSource":${dashboardForm.jsonStr}

      });
      revenueChart.render();

      </c:if>
    })

  </script>
</head>
<body>
<div>${empinfo.chartName}</div>

<div id="${ns}chartContainer"> Please Config Chart!</div>
<%--
<span id="${ns}chartContainer" style="line-height: 100%; display:
inline-block; zoom: 1; width: 99%; height: 34.65px; background-color: rgb(255, 255, 255);">
Please Config Chart!
</span>
--%>
</body>
</html>
