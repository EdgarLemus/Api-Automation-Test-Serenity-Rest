package com.mx.unodostres.certificacion.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class CodigoEstado implements Question<Boolean>{

	private int codigoExpectativa;
	
	public CodigoEstado(int codigoExpectativa) {
		this.codigoExpectativa = codigoExpectativa;
	}	
	
	public static CodigoEstado delServicio(int codigoExpectativa) {
		return new CodigoEstado(codigoExpectativa);
	}
	
	@Override
	public Boolean answeredBy(Actor actor) {
		return String.valueOf(SerenityRest.lastResponse().statusCode()).equals(String.valueOf(codigoExpectativa));
	}
}
