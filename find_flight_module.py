from FlightRadar24 import FlightRadar24API
from FlightRadar24 import Flight

class ArgumentError(Exception):
    """
    Excepción personalizada para manejar errores relacionados con argumentos.
    """
    pass

def _compare_registration(flight: Flight, reg: str):
    """
    Compara el número de registro de un vuelo con un valor dado.

    Parámetros:
    - flight (Flight): Objeto Flight que representa un vuelo.
    - reg (str): Número de registro a comparar.

    Retorna:
    True si el número de registro del vuelo coincide con el valor dado, False en caso contrario.
    """
    return reg == flight.registration

def _compare_number(flight: Flight, number: str):
    """
    Compara el número de vuelo de un vuelo con un valor dado.

    Parámetros:
    - flight (Flight): Objeto Flight que representa un vuelo.
    - number (str): Número de vuelo a comparar.

    Retorna:
    True si el número de vuelo del vuelo coincide con el valor dado, False en caso contrario.
    """
    return number == flight.number

def _compare_id(flight: Flight, id: str):
    """
    Compara el identificador de un vuelo con un valor dado.

    Parámetros:
    - flight (Flight): Objeto Flight que representa un vuelo.
    - id (str): Identificador a comparar.

    Retorna:
    True si el identificador del vuelo coincide con el valor dado, False en caso contrario.
    """
    return id == flight.id

def find_flight(opcion: int, str: str) -> Flight:
    """
    Encuentra un vuelo según la opción y el valor proporcionados.

    Parámetros:
    - opcion (int): Opción que determina cómo comparar el valor (1 para registro, 2 para número, 3 para identificador).
    - str (str): Valor a comparar.

    Retorna:
    Objeto Flight que representa el vuelo encontrado.

    Lanza:
    ArgumentError: Si la opción no es válida.
    """
    choosen_function = None
    opcion = int(opcion)
    if opcion == 1:
        choosen_function = _compare_registration
    elif opcion == 2:
        choosen_function = _compare_number
    else: 
        choosen_function = _compare_id

    fr_api = FlightRadar24API()

    for key in fr_api.get_zones():
        #print(key)
        zone = fr_api.get_zones()[key]
        zone_bounds = fr_api.get_bounds(zone)

        selected_flights = fr_api.get_flights(
            bounds = zone_bounds
        )
        for i in selected_flights:
            if choosen_function(i,str):
                return i