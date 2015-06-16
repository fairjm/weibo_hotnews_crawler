<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
    String base = request.getServletContext().getContextPath();
    String dataFile = (String) request.getAttribute("data");
    String path = base + "/generation/" + dataFile;
    String title = (String)request.getAttribute("title");
%>
<!DOCTYPE html>
<html>
<head>
</head>
<meta charset="utf-8">
<style>
body {
	font: 10px sans-serif;
}

.axis path, .axis line {
	fill: none;
	stroke: #000;
	shape-rendering: crispEdges;
}

.bar {
	fill: orange;
}

.bar:hover {
	fill: orangered;
}

.x.axis path {
	display: none;
}

.d3-tip {
	line-height: 1;
	font-weight: bold;
	padding: 12px;
	background: rgba(0, 0, 0, 0.8);
	color: #fff;
	border-radius: 2px;
}

.d3-tip:after {
	box-sizing: border-box;
	display: inline;
	font-size: 10px;
	width: 100%;
	line-height: 1;
	color: rgba(0, 0, 0, 0.8);
	content: "\25BC";
	position: absolute;
	text-align: center;
}

.d3-tip.n:after {
	margin: -1px 0 0 0;
	top: 100%;
	left: 0;
}
</style>
<body>
	<h1><%=title%></h1>
	<script src="<%=base%>/js/d3.v3.min.js"></script>
	<script src="<%=base%>/js/d3.tip.v0.6.3.js"></script>


	<script type="text/javascript">
var margin = {top: 40, right: 20, bottom: 80, left: 40},
    width = 960 - margin.left - margin.right,
    height = 600 - margin.top - margin.bottom;

var x = d3.scale.ordinal()
    .rangeRoundBands([0, width], .1);

var y = d3.scale.linear()
    .range([height, 0]);

var xAxis = d3.svg.axis()
    .scale(x)
    .orient("bottom");

var yAxis = d3.svg.axis()
    .scale(y)
    .orient("left");

var tip = d3.tip()
  .attr('class', 'd3-tip')
  .offset([-10, 0])
  .html(function(d) {
    return "<span style='color:red'>" + d.value + "</span>";
  })

var svg = d3.select("body").append("svg")
    .attr("width", width + margin.left + margin.right)
    .attr("height", height + margin.top + margin.bottom)
    .append("g")
    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

svg.call(tip);

d3.tsv("<%=path%>", type, function(error, data) {
			x.domain(data.map(function(d) {
				return d.key;
			}));
			y.domain([ 0, d3.max(data, function(d) {
				return d.value;
			}) ]);

			svg.append("g").attr("class", "x axis").attr("transform",
					"translate(0," + height + ")").call(xAxis).selectAll("text")
		            .style("text-anchor", "end")
		            .attr("dx", "-.8em")
		            .attr("dy", ".15em")
		            .attr("transform", "rotate(-35)");

			svg.append("g").attr("class", "y axis").call(yAxis).append("text")
					.attr("transform", "rotate(-90)").attr("y", 6).attr("dy",
							".71em").style("text-anchor", "end").text(
							"值");

			svg.selectAll(".bar").data(data).enter().append("rect").attr(
					"class", "bar").attr("x", function(d) {
				return x(d.key);
			}).attr("width", x.rangeBand()).attr("y", function(d) {
				return y(d.value);
			}).attr("height", function(d) {
				return height - y(d.value);
			}).on('mouseover', tip.show).on('mouseout', tip.hide)
		});

svg.selectAll(".axis g text")
.style("text-anchor", "end")
.attr("dx", "-.8em")
.attr("dy", ".15em")
.attr("transform", "rotate(-35)");

function type(d) {
	d.value = +d.value;
	return d;
}
	</script>
</body>
</html>