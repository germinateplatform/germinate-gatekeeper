<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>

<%--
  ~ Copyright 2017 Information and Computational Sciences,
  ~ The James Hutton Institute.
  ~
  ~ Licensed under the Apache License, Version 2.0 (the "License");
  ~ you may not use this file except in compliance with the License.
  ~ You may obtain a copy of the License at
  ~
  ~  http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~ Unless required by applicable law or agreed to in writing, software
  ~ distributed under the License is distributed on an "AS IS" BASIS,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  ~ See the License for the specific language governing permissions and
  ~ limitations under the License.
  --%>

<%
	Calendar cal = Calendar.getInstance();
	cal.setTime(new Date(System.currentTimeMillis()));

	int year = cal.get(Calendar.YEAR);

	String yearString = "2013-" + year;

	String versionName = "3.6.0";
%>

<footer class="footer">
	<div class="container">
		<div class="float-left truncate seventy">Version <%= versionName %> &copy; Information & Computational Sciences, JHI <%= yearString %>
		</div>
		<div class="float-right truncate thirty">
			<a id="cookie-button" href="#cookie" data-toggle="modal" data-target="#cookie"></a>
		</div>
	</div>

</footer>


<!-- Modal -->
<div class="modal fade" id="cookie" tabindex="-1" role="dialog" aria-labelledby="cookie-policy-title">

</div>