package com.mx.unodostres.certificacion.stepsdefinitions;

import java.io.IOException;

import com.mx.unodostres.certificacion.interactions.PatchRest;
import com.mx.unodostres.certificacion.utils.Metodos;

import cucumber.api.java.Before;
import cucumber.api.java.es.Cuando;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

public class ActualizarParcheUsuarioStepDefinitions {

	@Before
	public void before() throws IOException {
		OnStage.setTheStage(new OnlineCast());
		Metodos.jsonBodyRequestPath = "ActualizacionUsuario";
	}
	
	@Cuando("^realice la solicitud para actualizar el parche de un usuario en (.*)$")
	public void realiceLaSolicitudParaActualizarElParcheDeUnUsuarioEnApiUsers(String endPoint) {
		OnStage.theActorInTheSpotlight().attemptsTo(PatchRest.on(endPoint));
	}
}
