<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.weibohot.server.entity.Category"%>
<%@page import="com.weibohot.server.util.ColorGenerator"%>
<%
    String base = request.getServletContext().getContextPath();
	List<Category> data = (List<Category>)request.getAttribute("data");
	boolean is24h = (boolean)request.getAttribute("24h");
%>
<!DOCTYPE html>
<html>
<head></head>
<body>

	<div id="pieChart"></div>

	<script src="<%=base%>/js/d3pie.min.js"></script>
	<script src="<%=base%>/js/d3.min.js"></script>
	<script>
		var pie = new d3pie("pieChart", {
			"header" : {
				"title" : {
					"text" : "<%=is24h? "24h":"全部"%>的热门话题分类",
					"fontSize" : 24,
					"font" : "open sans"
				},
				"subtitle" : {
					"text" : "热门话题的分类状况",
					"color" : "#999999",
					"fontSize" : 12,
					"font" : "open sans"
				},
				"titleSubtitlePadding" : 9
			},
			"footer" : {
				"color" : "#999999",
				"fontSize" : 10,
				"font" : "open sans",
				"location" : "bottom-left"
			},
			"size" : {
				"canvasWidth" : 800,
				"pieOuterRadius" : "90%"
			},
			"data" : {
				"sortOrder" : "value-desc",
				"content" : [ 
				<%for(int i=0;i<data.size();i++){%>{

					"label" : "<%=data.get(i).getCategoryName()%>",
					"value" : <%=data.get(i).getNewsCount()%>,
					"color" : "<%=ColorGenerator.generatorHtmlColor()%>"
				}
				<%if(i != data.size() -1){
				    out.print(",");
				}
				}%>]
			},
			"labels" : {
				"outer" : {
					"pieDistance" : 32
				},
				"inner" : {
					"format" : "value",
					"hideWhenLessThanPercentage" : 3
				},
				"mainLabel" : {
					"fontSize" : 11
				},
				"percentage" : {
					"color" : "#ffffff",
					"decimalPlaces" : 0
				},
				"value" : {
					"color" : "#adadad",
					"fontSize" : 11
				},
				"lines" : {
					"enabled" : true
				},
				"truncation" : {
					"enabled" : true
				}
			},
			"effects" : {
				"pullOutSegmentOnClick" : {
					"effect" : "linear",
					"speed" : 400,
					"size" : 8
				}
			},
			"misc" : {
				"gradient" : {
					"enabled" : true,
					"percentage" : 100
				}
			}
		});
	</script>

</body>
</html>