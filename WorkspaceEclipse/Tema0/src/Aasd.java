import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Aasd {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner (new File("file.txt"));
		
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			System.out.println(line);
		}
	}

}
