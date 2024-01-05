import sys
import find_flight_module
from FlightRadar24 import Flight

myFligth = find_flight_module.find_flight(sys.argv[1],sys.argv[2])
if myFligth == None:
    print(1)
else:
    print(myFligth.id)