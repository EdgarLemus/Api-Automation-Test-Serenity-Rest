package com.mx.unodostres.certificacion.stepsdefinitions;

import java.io.IOException;

import com.mx.unodostres.certificacion.interactions.GetRest;
import com.mx.unodostres.certificacion.questions.CodigoEstado;

import cucumber.api.java.Before;
import cucumber.api.java.es.*;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;


public class ListaUsuarioStepDefinitions {

	@Before
	public void before() throws IOException {
		OnStage.setTheStage(new OnlineCast());
	}
	
	@Dado("^que me encuentro en la url (.*) de reqres$")
	public void queMeEncuentroEnLaUrlHttpsReqresInDeReqres(String url) {
		OnStage.theActorCalled("").whoCan(CallAnApi.at(url));
	}

	@Cuando("^realice la solicitud para obtener la lista de usuarios en (.*)$")
	public void realiceLaSolicitudParaObtenerLaListaDeUsuariosEnApiUsersPage(String endPoint) {
		OnStage.theActorInTheSpotlight().attemptsTo(GetRest.on(endPoint));
	}

	@Entonces("^podre observar el estado (\\d+) de la solicitud$")
	public void podreObservarElEstadoDeLaSolicitud(int estado) {
		OnStage.theActorInTheSpotlight().should(GivenWhenThen.seeThat(CodigoEstado.delServicio(estado)));
	}
}
