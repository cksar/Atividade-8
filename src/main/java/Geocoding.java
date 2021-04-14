import java.sql.SQLOutput;
import java.util.Scanner;

public class Geocoding {
    public static void main(String[] args) {
        try {
            String rua;
            Double lat;
            Double lon;
            Scanner scanner = new Scanner(System.in);

            System.out.print("Informe o endereço: ");
            rua = scanner.nextLine();
            System.out.print("Informe a longitude: ");
            lon = Double.parseDouble(scanner.nextLine());
            System.out.print("Informe a latitude: ");
            lat = Double.parseDouble(scanner.nextLine());

            Search.findStreet(rua, lat, lon);
        } catch (Exception e) {
            System.out.println("Ocorreu um erro: "+ e);
        }

    }
}
