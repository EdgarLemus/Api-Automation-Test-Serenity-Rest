package com.mx.unodostres.certificacion.interactions;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class GetRest implements Interaction{
	
	private String endPoint;

	public GetRest(String endPoint) {
		this.endPoint = endPoint;
	}

	@Override
	public <T extends Actor> void performAs(T actor) {
		actor.attemptsTo(Get.resource(endPoint)
				.with(request -> request.header("Content-Type", "application/json")
						.header("Accept","*/*")));
	}

	public static Performable on(String endPoint) {
		return Instrumented.instanceOf(GetRest.class).withProperties(endPoint);
	}
}
