package flights;

import static sbcc.Core.*;

import java.io.*;
import java.util.*;

import static java.lang.System.*;
import org.apache.commons.lang3.StringUtils;
import static org.apache.commons.lang3.StringUtils.*;

/**
 * 
 * @author Riley Peterlinz
 *
 */
public class Main {

	public static void main(String[] args) {
		try {
			var flights = loadFlights(readLine());
			Object[][] table = new String[flights.size() + 2][];

			table[0] = new String[] { "AIRLINE", "FROM", "TO", "PRICE" };
			table[1] = new String[] { "-------", "----", "--", "-----" };
			for (int i = 0; i < flights.size(); i++) {
				table[i + 2] = new String[] { flights.get(i).getAirline(), flights.get(i).getFrom(),
						flights.get(i).getTo(), flights.get(i).getPrice() };
			}

			for (var row : table) {
				printf("%-16s%-8s%-8s%-5s%n", row);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static ArrayList<Flight> loadFlights(String file) throws IOException {
		ArrayList<Flight> flights = new ArrayList<Flight>();
		var flightData = readFileAsLines(file);

		for (int i = 0; i < flightData.size(); i++) {
			var datum = flightData.get(i);
			var from = datum.substring(0, 3);
			var to = datum.substring(7, 10);
			var airline = StringUtils.substringBetween(datum, ", ", ", ");
			var price = datum.substring(datum.indexOf("$"));
			flights.add(new Flight(airline, from, to, price));
		}
		return flights;
	}

}
