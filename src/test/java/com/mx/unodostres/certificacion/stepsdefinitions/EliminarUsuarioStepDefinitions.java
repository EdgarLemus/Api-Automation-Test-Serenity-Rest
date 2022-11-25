package com.mx.unodostres.certificacion.stepsdefinitions;

import java.io.IOException;

import com.mx.unodostres.certificacion.interactions.DeleteRest;
import com.mx.unodostres.certificacion.utils.Metodos;

import cucumber.api.java.Before;
import cucumber.api.java.es.Cuando;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

public class EliminarUsuarioStepDefinitions {

	@Before
	public void before() throws IOException {
		OnStage.setTheStage(new OnlineCast());
	}
	
	@Cuando("^realice la solicitud para eliminar un usuario en (.*)$")
	public void realiceLaSolicitudParaEliminarUnUsuarioEnApiUsers(String endPoint) {
	    OnStage.theActorInTheSpotlight().attemptsTo(DeleteRest.on(endPoint));
	}
}
