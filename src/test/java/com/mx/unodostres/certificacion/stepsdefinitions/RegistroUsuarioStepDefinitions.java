package com.mx.unodostres.certificacion.stepsdefinitions;

import java.io.IOException;

import com.mx.unodostres.certificacion.interactions.PostRest;
import com.mx.unodostres.certificacion.utils.Metodos;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.es.Cuando;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

public class RegistroUsuarioStepDefinitions {

	@Before
	public void before(Scenario scenario) throws IOException {
		OnStage.setTheStage(new OnlineCast());
		Metodos.jsonBodyRequestPath = "Registro"+scenario.getName().split(" ")[4];
	}
	
	@Cuando("^realice la solicitud para registrar un usuario en (.*)$")
	public void realiceLaSolicitudParaRegistrarUnUsuarioEnApiRegister(String endPoint) {
		OnStage.theActorInTheSpotlight().attemptsTo(PostRest.on(endPoint));	    
	}
}
