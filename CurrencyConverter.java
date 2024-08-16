import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class CurrencyConverter {

    public static void main(String[] args) {
        try {
            // Step 1: Fetch exchange rates from the API
            JSONObject exchangeRates = fetchExchangeRates();

            // Step 2: User interaction
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("--- Currency Converter ---");

            // Display available currencies
            System.out.println("Available currencies:");
            for (String currency : exchangeRates.keySet()) {
                System.out.print(currency + " ");
            }
            System.out.println();

            // Prompt user for base and target currencies
            System.out.print("Enter base currency (e.g., USD): ");
            String baseCurrency = reader.readLine().toUpperCase();

            System.out.print("Enter target currency (e.g., EUR): ");
            String targetCurrency = reader.readLine().toUpperCase();

            // Prompt user for amount to convert
            System.out.print("Enter amount to convert: ");
            double amount = Double.parseDouble(reader.readLine());

            // Step 3: Currency conversion
            double exchangeRate = ((JSONObject) exchangeRates).getDouble(targetCurrency) / exchangeRates.getDouble(baseCurrency);
            double convertedAmount = amount * exchangeRate;

            // Step 4: Display result
            System.out.printf("%.2f %s = %.2f %s%n", amount, baseCurrency, convertedAmount, targetCurrency);

        } catch (IOException e) {
            System.out.println("Error: Unable to fetch exchange rates. Please try again later.");
            e.printStackTrace();
        }
    }

    @SuppressWarnings("deprecation")
    private static JSONObject fetchExchangeRates() throws IOException {
        String apiUrl = "https://api.exchangeratesapi.io/latest?base=USD"; // Example API endpoint
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JSONObject jsonObject = new JSONObject(response.toString());
        return jsonObject.getJSONObject("rates");
    }
}