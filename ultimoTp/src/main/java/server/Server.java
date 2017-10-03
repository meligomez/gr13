package server;

import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	public static void main(String[] args) {
		Spark.port(7006);
		DebugScreen.enableDebugScreen();
		Router.configure();
	}
}
