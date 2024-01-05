from FlightRadar24 import FlightRadar24API
from FlightRadar24 import Flight
from FlightRadar24.api import FlightRadar24API
import find_flight_module

fr_api = FlightRadar24API()
myFlightNumber = find_flight_module.find_flight(1,"9H-QCY")
myFlightDetails = fr_api.get_flight_details(myFlightNumber)
indices = list(myFlightDetails.keys())
print(indices)
history = myFlightDetails.get("flightHistory")
availability = myFlightDetails.get("availability")
print(availability)

# Obtener índices y subíndices
# for i, item in enumerate(history['aircraft']):
#     identification = item.get('identification', {})
#     id_value = identification.get('id', '')
#     number = identification.get('number', {}).get('default', '')
    
#     print(f"Índice {i}:")
#     print(f"  - ID: {id_value}")
#     print(f"  - Número: {number}")

# Acceder a cada aeropuerto
for flight in history["aircraft"]:
    airport_info = flight["airport"]
    airport_orig_iata = airport_info["origin"]["code"]["iata"]
    print(f"Aeropuerto ID: {airport_orig_iata}")
    airport_dest_iata = airport_info["destination"]["code"]["iata"]
    print(f"Aeropuerto ID: {airport_dest_iata}")
