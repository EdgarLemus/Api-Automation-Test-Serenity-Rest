#language: es
#Author: edgar_duvan_l_r@hotmail.com

Característica: Eliminacion de usuarios

	Esquema del escenario: Eliminar usuario
		Dado que me encuentro en la url <Url> de reqres
		Cuando realice la solicitud para eliminar un usuario en <EndPoint>
		Entonces podre observar el estado <Estado> de la solicitud
		
		Ejemplos:
		|Url|EndPoint|Estado|
		|https://reqres.in/|api/users/2|204|
