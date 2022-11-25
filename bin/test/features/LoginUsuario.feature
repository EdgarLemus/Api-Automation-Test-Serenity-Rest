#language: es
#Author: edgar_duvan_l_r@hotmail.com

Característica: Login de usuarios

	Esquema del escenario: <Escenario>
		Dado que me encuentro en la url <Url> de reqres
		Cuando realice la solicitud para loguear un usuario en <EndPoint>
		Entonces podre observar el estado <Estado> de la solicitud
		
		Ejemplos:
		|Escenario|Url|EndPoint|Estado|
		|Login de usuario - Successful|https://reqres.in/|api/login|200|
		|Login de usuario - Unsuccessful|https://reqres.in/|api/login|400|
