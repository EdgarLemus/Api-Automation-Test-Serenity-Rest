#language: es
#Author: edgar_duvan_l_r@hotmail.com

Característica: Creacion de usuarios

	Esquema del escenario: Crear usuario
		Dado que me encuentro en la url <Url> de reqres
		Cuando realice la solicitud para crear un usuario en <EndPoint>
		Entonces podre observar el estado <Estado> de la solicitud
		
		Ejemplos:
		|Url|EndPoint|Estado|
		|https://reqres.in/|api/users|201|
