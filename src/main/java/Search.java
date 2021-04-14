import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Search {

    public static void findStreet(String name, Double lat, Double lon) throws SQLException {
        ConnectionJDBC connection = new ConnectionJDBC();
        Connection conn = connection.openConnection();

        PreparedStatement st = conn.prepareStatement("select  name, ST_Y(ST_Centroid(ST_GeomFromEWKT(ST_AsText(geom)))), ST_X(ST_Centroid(ST_GeomFromEWKT(ST_AsText(geom)))) from roads where name = '"+name+"' limit 1");
        ResultSet rs = st.executeQuery();

        while (rs.next())
        {
            String street = rs.getString("name");
            Double y = Double.parseDouble(rs.getString("ST_Y"));
            Double x = Double.parseDouble(rs.getString("ST_X"));

            Double dist = calcDistance(conn, x, y, lat, lon);

            System.out.println(street);
            System.out.println("Coordenadas geocodificadas (LonLat): "+x+", "+y);
            System.out.println("Coordenadas reais (LonLat): "+lon+", "+lat);
            System.out.println("Distancia: "+ dist);

        }
    }

    public static Double calcDistance(Connection conn, Double x, Double y, Double lat, Double lon) throws SQLException {
        PreparedStatement st = conn.prepareStatement("SELECT ST_Distance('SRID=4326;POINT("+x+" "+y+")'::geometry, 'SRID=4326;POINT("+lon+" "+lat+")'::geometry) as distance;");
        ResultSet r = st.executeQuery();

        while (r.next())
        {
            Double dist = Double.parseDouble(r.getString("distance"));
            return dist;
        }
        return null;
    }
}
