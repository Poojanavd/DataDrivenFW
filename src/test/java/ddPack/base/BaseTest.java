package ddPack.base;

import java.io.FileInputStream;
import java.util.Properties;

public class BaseTest {

	public static Properties prop = null;
	public static FileInputStream fis;

	public static void initialize() {
		if (prop == null) {
			prop = new Properties();
			try {
				fis = new FileInputStream("src\\test\\resources\\config.properties");
				prop.load(fis);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
