#language: es
#Author: edgar_duvan_l_r@hotmail.com

Característica: Actualizacion de parche de usuario

	Esquema del escenario: Actualizar parche de usuario
		Dado que me encuentro en la url <Url> de reqres
		Cuando realice la solicitud para actualizar el parche de un usuario en <EndPoint>
		Entonces podre observar el estado <Estado> de la solicitud
		
		Ejemplos:
		|Url|EndPoint|Estado|
		|https://reqres.in/|api/users/2|200|
