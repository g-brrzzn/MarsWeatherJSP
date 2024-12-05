    package com.mars.weather;

    import com.google.gson.Gson;
    import jakarta.servlet.ServletException;
    import jakarta.servlet.annotation.WebServlet;
    import jakarta.servlet.http.HttpServlet;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import java.io.BufferedReader;
    import java.io.PrintStream;
    import java.util.Map;
    import java.io.InputStreamReader;
    import java.io.IOException;
    import java.net.HttpURLConnection;
    import java.net.URL;

@WebServlet("/mars-weather")
public class MarsWeatherServlet extends HttpServlet {
    private static final String API_URL = "https://api.nasa.gov/insight_weather/?api_key=RAFMXe3wKMcAD8pVJ054y2NJXbOXVJna6edYJo66&feedtype=json&ver=1.0";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(API_URL).openConnection();
        connection.setRequestMethod("GET");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }

            System.setOut(new PrintStream(System.out));
            System.out.println("Debugging output to console.");
            System.out.println("JSON Response: " + json); // Log da resposta JSON

            Map<String, Object> weatherData = parseJson(json.toString());
            System.out.println("Parsed Weather Data: " + weatherData); // Log dos dados parseados

            request.setAttribute("weatherData", weatherData);
        } catch (Exception e) {
            e.printStackTrace(); // Log de erro para verificar problemas
            System.out.println("Erro ao acessar a API: " + e.getMessage());
        }

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }


    private Map parseJson(String json) {
        // Utilize uma biblioteca como GSON ou Jackson para parsear o JSON
        return new Gson().fromJson(json, Map.class);
    }
}
