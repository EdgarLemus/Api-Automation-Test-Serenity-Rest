package com.mx.unodostres.certificacion.stepsdefinitions;

import java.io.IOException;

import com.mx.unodostres.certificacion.interactions.GetRest;

import cucumber.api.java.Before;
import cucumber.api.java.es.Cuando;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

public class ListaRecursosStepDefinitions {

	@Before
	public void before() throws IOException {
		OnStage.setTheStage(new OnlineCast());
	}
	
	@Cuando("^realice la solicitud para obtener la lista de recursos en (.*)$")
	public void realiceLaSolicitudParaObtenerLaListaDeRecursosEnApiUnknown(String endPoint) {
		OnStage.theActorInTheSpotlight().attemptsTo(GetRest.on(endPoint));
	}
}
