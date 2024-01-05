from flask import Flask, jsonify
import threading
import time
import json
from FlightRadar24.api import FlightRadar24API  # Asegúrate de importar adecuadamente tu módulo FlightRadar24
import logging
import sys
from find_flight_module import find_flight, ArgumentError
# url: http://127.0.0.1:<port_number>/<function_name>
#http://127.0.0.1:5000/obtener_json

def mostrar_error(mensaje):
    print(f"Error: {mensaje}", file=sys.stderr)

if (sys.argv[2] == "-r" or
    sys.argv[2] == "--registration"):
    opcion = 1
elif (sys.argv[2] == "-n" or
    sys.argv[2] == "--fligh_number"):
    opcion = 2
elif (sys.argv[2] == "-i" or
    sys.argv[2] == "--identificator"):
    opcion = 3
else:
    mostrar_error("Opción no válida")
    sys.exit(1)

puerto_elegido = int(sys.argv[1])
selected_flight = None
# Comprobar primero en el caso de que se quiera seguir a un avión y la matricula 
# no se encuentre en el registro, lanzar una excpeción para acabar con el programa
print("Ejecutando: ",opcion,sys.argv[3])
try:
    selected_flight = find_flight(opcion,sys.argv[3])
except ArgumentError as e:
    print(f"Error: {e}")

f_id = selected_flight.id
print(f_id)
while selected_flight == None:
    selected_flight = find_flight(3,f_id)
app = Flask(__name__)
ruta_personalizada = '/'+f_id

# if len(sys.argv) > 1:
#     matricula = sys.argv[2]
#     ruta_personalizada = "/tracking_airplane_" + matricula  # Toma el primer argumento de la línea de comandos
#     #print(ruta_personalizada)
# else:
#     print("ERROR: Debes proporcionar una matricula para el seguimiento del vuelo.")
#     sys.exit(1)  # Salir con código de error 1

#logging.basicConfig(filename=ruta_personalizada[1:]+'.log', level=logging.DEBUG)
logging.basicConfig(filename=ruta_personalizada[1:]+'.log', level=logging.DEBUG)

# Datos iniciales
datos_json = {}
lock = threading.Lock()

@app.route(ruta_personalizada+'/status', methods=['GET'])
def obtener_json():
    with lock:
        return jsonify(datos_json)
    
@app.route(ruta_personalizada + '/info', methods=['GET'])
def obtener_info():
    fr_api = FlightRadar24API()
    return jsonify(selected_flight.__dict__)

def actualizar_datos_json():
    global datos_json
    fr_api = FlightRadar24API()

    while True:
        selected_flight_details = fr_api.get_flight_details(selected_flight)
        print(selected_flight_details.get("time")["real"]["arrival"],end="\t")
        print(selected_flight)
        with lock:
            datos_json = selected_flight_details.get("time")
        
        time.sleep(10)

if __name__ == '__main__':
    hilo_flask = threading.Thread(target=app.run, kwargs={'debug': True, 'port': puerto_elegido, 'use_reloader': False})
    hilo_flask.start()

    hilo_datos = threading.Thread(target=actualizar_datos_json)
    hilo_datos.start()