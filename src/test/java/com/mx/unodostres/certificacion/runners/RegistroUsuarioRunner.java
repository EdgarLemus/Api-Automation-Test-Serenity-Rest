package com.mx.unodostres.certificacion.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
		features = "src/test/resources/features/RegistroUsuario.feature",
		glue = "com.mx.unodostres.certificacion.stepsdefinitions",
		snippets = SnippetType.CAMELCASE)
public class RegistroUsuarioRunner {

}
