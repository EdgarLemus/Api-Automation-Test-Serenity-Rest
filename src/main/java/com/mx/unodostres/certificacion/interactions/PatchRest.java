package com.mx.unodostres.certificacion.interactions;

import com.mx.unodostres.certificacion.utils.Metodos;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.rest.interactions.Patch;

public class PatchRest implements Interaction{

	private String endPoint;
	
	public PatchRest(String endPoint) {
		this.endPoint = endPoint;
	}

	@Override
	public <T extends Actor> void performAs(T actor) {
		actor.attemptsTo(Patch.to(endPoint)
				.with(request -> request.header("Content-Type", "application/json")
						.header("Accept","*/*")
						.body(Metodos.leerArchivoJson())));
	}

	public static Performable on(String endPoint) {
		return Instrumented.instanceOf(PatchRest.class).withProperties(endPoint);
	}
}
