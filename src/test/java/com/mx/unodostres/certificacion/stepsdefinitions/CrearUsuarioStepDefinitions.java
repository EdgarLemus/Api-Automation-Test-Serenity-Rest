package com.mx.unodostres.certificacion.stepsdefinitions;

import java.io.IOException;

import com.mx.unodostres.certificacion.interactions.PostRest;
import com.mx.unodostres.certificacion.utils.Metodos;

import cucumber.api.java.Before;
import cucumber.api.java.es.Cuando;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

public class CrearUsuarioStepDefinitions {	
	
	@Before
	public void before() throws IOException {
		OnStage.setTheStage(new OnlineCast());
		Metodos.jsonBodyRequestPath = "CreacionUsuario";
	}
	
	@Cuando("^realice la solicitud para crear un usuario en (.*)$")
	public void realiceLaSolicitudParaCrearUnUsuarioEnApiUsers(String endPoint) {
		OnStage.theActorInTheSpotlight().attemptsTo(PostRest.on(endPoint));
	}

}
