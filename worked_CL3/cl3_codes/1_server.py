from xmlrpc.server import SimpleXMLRPCServer
import math
import base64

def calculate_factorial(n):
    if n < 0:
        return "Invalid input. Factorial is not defined for negative numbers."
    
    # Calculate factorial and encode in base64
    result = str(math.factorial(n)).encode('utf-8')
    encoded_result = base64.b64encode(result).decode('utf-8')
    return encoded_result

server = SimpleXMLRPCServer(("localhost", 8000))
print("Server is running on port 8000...")
server.register_function(calculate_factorial, "factorial")
server.serve_forever()

