package server;

import Controller.CuentaController;
import Controller.InicioController;
import Controller.LoginController;
/*import controllers.InicioController;
import controllers.VentasController;*/
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.BooleanHelper;
import spark.utils.HandlebarsTemplateEngineBuilder;

public class Router {
	public static void configure(){
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder
				.create()
				.withDefaultHelpers()
				.withHelper("isTrue", BooleanHelper.isTrue)
				.build();

		Spark.staticFiles.location("/public");
		
		LoginController loginController = new LoginController();
		CuentaController cuentaController = new CuentaController();
		
		Spark.get("/", loginController::inicio, engine);
		Spark.post("/Home", loginController::verificarUsuario,engine);
		Spark.get("/cuentas", cuentaController::inicioCuenta, engine);
		Spark.get("/consultaCuenta/:empresa/:periodoDesde/:periodoHasta", cuentaController::consultaCuenta,engine);
		/*Spark.post("/ventaDetalle", ventasController::verdetalleVenta, engine);
		*/
	}
}
