//
//
//import java.sql.*;
//import java.util.HashMap;
//import java.util.ArrayList;
//import java.util.Map;
//import java.net.URI;
//import java.net.URISyntaxException;
//
//import static spark.Spark.*;
//
//import main.java.Manager;
//import spark.template.freemarker.FreeMarkerEngine;
//import spark.ModelAndView;
//
//import static spark.Spark.get;
//
//import spark.Filter;
//import spark.Filter;
//import spark.Request;
//import spark.Response;
//import spark.Spark;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.heroku.sdk.jdbc.DatabaseUrl;
//
//public class Main {
//    private static final HashMap<String, String> corsHeaders = new HashMap<String, String>();
//    private static final Manager manager = new Manager();
//
//    static {
//        corsHeaders.put("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
//        corsHeaders.put("Access-Control-Allow-Origin", "*");
//        corsHeaders.put("Access-Control-Allow-Headers", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin,");
//        corsHeaders.put("Access-Control-Allow-Credentials", "true");
//    }
//
//    public final static void apply() {
//        Filter filter = new Filter() {
//            @Override
//            public void handle(Request request, Response response) throws Exception {
//                corsHeaders.forEach((key, value) -> {
//                    response.header(key, value);
//                });
//            }
//        };
//        Spark.after(filter);
//    }
//
//    public static void main(String[] args) {
//        port(Integer.valueOf(System.getenv("PORT")));
//        staticFileLocation("/public");
//
//        apply();
//
//        get("/findParties", (req, res) -> {
//
//            return manager.findParties(req,res);
//        });
//
//        post("/auth", (req, res) -> {
//
//            return manager.auth(req,res);
//        });
//
//        get("/auth", (req, res) -> {
//
//            return "ololo";
//        });
//
//        get("/", (request, response) -> {
//            Map<String, Object> attributes = new HashMap<>();
//            attributes.put("message", "Hello World!");
//
//            return new ModelAndView(attributes, "index.ftl");
//        }, new FreeMarkerEngine());
//
//        get("/db", (req, res) -> {
//            Connection connection = null;
//            Map<String, Object> attributes = new HashMap<>();
//            try {
//                connection = DatabaseUrl.extract().getConnection();
//
//                Statement stmt = connection.createStatement();
//                //stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
//                //stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
//                ResultSet rs = stmt.executeQuery("SELECT * FROM USERS");
//
//                ArrayList<String> output = new ArrayList<String>();
//                while (rs.next()) {
//                    output.add("Read from DB: " + rs.getString("NAME")+  rs.getString("LOGIN"));
//
//                }
//
//                attributes.put("results", output);
//                return new ModelAndView(attributes, "db.ftl");
//            } catch (Exception e) {
//                attributes.put("message", "There was an error: " + e);
//                return new ModelAndView(attributes, "error.ftl");
//            } finally {
//                if (connection != null) try {
//                    connection.close();
//                } catch (SQLException e) {
//                }
//            }
//        }, new FreeMarkerEngine());
//
//    }
//
//}
