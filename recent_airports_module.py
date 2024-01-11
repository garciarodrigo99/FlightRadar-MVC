from FlightRadar24 import FlightRadar24API
from FlightRadar24.api import FlightRadar24API
import find_flight_module
import json

def obtener_rutas_aereas(identificador):
    """
    Obtiene el historial de rutas aéreas para un vuelo específico.

    Parámetros:
    - identificador (str): El identificador del vuelo.

    Retorna:
    Una lista de rutas aéreas en formato "origen-destino".
    """
    fr_api = FlightRadar24API()
    
    my_flight_number = find_flight_module.find_flight(3, identificador)
    my_flight_details = fr_api.get_flight_details(my_flight_number)
    
    history = my_flight_details.get("flightHistory")
    
    # Acceder a cada aeropuerto
    rutas = []
    for flight in history["aircraft"]:
        airport_info = flight["airport"]
        airport_orig_iata = airport_info["origin"]["code"]["iata"]
        airport_dest_iata = airport_info["destination"]["code"]["iata"]
        rutas.append(airport_orig_iata + '-' + airport_dest_iata)
    
    return rutas