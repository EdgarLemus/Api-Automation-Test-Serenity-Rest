package com.mx.unodostres.certificacion.interactions;

import com.mx.unodostres.certificacion.utils.Metodos;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.rest.interactions.Post;

public class PostRest implements Interaction{

	private String endPoint;

	public PostRest(String endPoint) {
		this.endPoint = endPoint;
	}

	@Override
	public <T extends Actor> void performAs(T actor) {
		
		actor.attemptsTo(Post.to(endPoint)
				.with(request -> request
						.header("Content-Type","application/json")
						.header("Accept","*/*")
				.body(Metodos.leerArchivoJson())));
	}
	
	public static Performable on(String endPoint) {
		return Instrumented.instanceOf(PostRest.class).withProperties(endPoint);
	}
}
