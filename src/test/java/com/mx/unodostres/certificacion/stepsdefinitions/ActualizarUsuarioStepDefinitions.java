package com.mx.unodostres.certificacion.stepsdefinitions;

import java.io.IOException;

import com.mx.unodostres.certificacion.interactions.PutRest;
import com.mx.unodostres.certificacion.utils.Metodos;

import cucumber.api.java.Before;
import cucumber.api.java.es.Cuando;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

public class ActualizarUsuarioStepDefinitions {
	
	@Before
	public void before() throws IOException {
		OnStage.setTheStage(new OnlineCast());
		Metodos.jsonBodyRequestPath = "ActualizacionUsuario";
	}
	
	@Cuando("^realice la solicitud para actualizar un usuario en (.*)$")
	public void realiceLaSolicitudParaActualizarUnUsuarioEnApiUsers(String endPoint) {
	    OnStage.theActorInTheSpotlight().attemptsTo(PutRest.on(endPoint));
	}
	
}
