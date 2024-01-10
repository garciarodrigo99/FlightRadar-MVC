import json
from FlightRadar24.api import FlightRadar24API  # Asegúrate de importar adecuadamente tu módulo FlightRadar24
from find_flight_module import find_flight, ArgumentError

selected_flight = find_flight(2,"BA2703")