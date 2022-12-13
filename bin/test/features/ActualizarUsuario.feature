#language: es
#Author: edgar_duvan_l_r@hotmail.com

Característica: Actualizacion de usuario

	Esquema del escenario: Actualizar usuario
		Dado que me encuentro en la url <Url> de reqres
		Cuando realice la solicitud para actualizar un usuario en <EndPoint>
		Entonces podre observar el estado <Estado> de la solicitud
		
		Ejemplos:
		|Url|EndPoint|Estado|
		|https://reqres.in/|api/users/2|200|