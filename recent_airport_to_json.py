import json

def crear_ruta(ruta):
    ruta = {
        "ruta": ruta
    }
    return ruta

# Ejemplo de información sobre varias rutas entre aeropuertos
rutas = [
    crear_ruta("AER1-AER2"),
    crear_ruta("AER2-AER3"),
    crear_ruta("AER3-AER1"),
    # Puedes agregar más rutas según sea necesario
]

# Convertir la lista de rutas a JSON
json_rutas = json.dumps(rutas, indent=2)

# Guardar el JSON en un archivo
nombre_archivo = "rutas_entre_aeropuertos.json"
with open(nombre_archivo, 'w') as archivo_json:
    archivo_json.write(json_rutas)

print(f"Se ha creado el archivo JSON '{nombre_archivo}' con la información de las rutas.")
