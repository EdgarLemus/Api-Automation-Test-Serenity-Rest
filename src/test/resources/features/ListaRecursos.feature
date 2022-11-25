#language: es
#Author: edgar_duvan_l_r@hotmail.com

Característica: Obtener lista de recursos

	Esquema del escenario: Obtener lista de recursos
		Dado que me encuentro en la url <Url> de reqres
		Cuando realice la solicitud para obtener la lista de recursos en <EndPoint>
		Entonces podre observar el estado <Estado> de la solicitud
		
		Ejemplos:
		|Url|EndPoint|Estado|
		|https://reqres.in/|api/unknown|200|
