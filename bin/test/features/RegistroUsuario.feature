#language: es
#Author: edgar_duvan_l_r@hotmail.com

Característica: Registro de usuarios

	Esquema del escenario: <Escenario>
		Dado que me encuentro en la url <Url> de reqres
		Cuando realice la solicitud para registrar un usuario en <EndPoint>
		Entonces podre observar el estado <Estado> de la solicitud
		
		Ejemplos:
		|Escenario|Url|EndPoint|Estado|
		|Registro de usuario - Successful|https://reqres.in/|api/register|200|
		|Registro de usuario - Unsuccessful|https://reqres.in/|api/register|400|
