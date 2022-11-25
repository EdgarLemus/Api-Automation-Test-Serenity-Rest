package com.mx.unodostres.certificacion.stepsdefinitions;

import java.io.IOException;

import com.mx.unodostres.certificacion.interactions.PostRest;
import com.mx.unodostres.certificacion.utils.Metodos;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.es.Cuando;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

public class LoginUsuarioStepDefinitions {

	@Before
	public void before(Scenario scenario) throws IOException {
		OnStage.setTheStage(new OnlineCast());
		Metodos.jsonBodyRequestPath = "Login"+scenario.getName().split(" ")[4];
	}
	
	@Cuando("^realice la solicitud para loguear un usuario en (.*)$")
	public void realiceLaSolicitudParaLoguearUnUsuarioEnApiLogin(String endPoint) {
		OnStage.theActorInTheSpotlight().attemptsTo(PostRest.on(endPoint));
	}
}
