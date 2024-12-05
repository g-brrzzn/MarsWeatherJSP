<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <title>Mars Weather</title>
</head>
<body>
    <h1>Mars Weather Data</h1>
    <%
        Map<String, Object> weatherData = (Map<String, Object>) request.getAttribute("weatherData");
        if (weatherData != null) {
            for (String sol : (List<String>) weatherData.get("sol_keys")) {
                Map<String, Object> solData = (Map<String, Object>) weatherData.get(sol);
    %>
                <h2>Sol <%= sol %></h2>
                <p>Temperature: Average = <%= solData.get("AT.av") %>°C,
                   Min = <%= solData.get("AT.mn") %>°C,
                   Max = <%= solData.get("AT.mx") %>°C</p>
    <%
            }
        } else {
    %>
        <p>No weather data available.</p>
    <%
        }
    %>
</body>
</html>
