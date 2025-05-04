# server.py (cell 1) 
import xmlrpc.server 
import threading 
 
# Function to calculate factorial 
def factorial(n): 
    if n < 0: 
        raise ValueError("Factorial is not defined for negative numbers.") 
    if n > 100:  # Limit the maximum number to 100 
        raise ValueError("Input is too large! Try a smaller number.") 
     
    if n == 0 or n == 1: 
        return 1 
    else: 
        return n * factorial(n - 1) 
 
# Function to start the server 
def start_server(): 
    with xmlrpc.server.SimpleXMLRPCServer(('localhost', 8000)) as server: 
        print("Server is running...") 
        server.register_function(factorial, 'factorial') 
        server.serve_forever() 
 
# Running the server in a separate thread 
server_thread = threading.Thread(target=start_server) 
server_thread.daemon = True 
server_thread.start() 
 
# client.py (cell 2) 
import xmlrpc.client 
 
# Create a connection to the server 
server = xmlrpc.client.ServerProxy('http://localhost:8000') 
 
# Request input from the user (for Jupyter, use input()) 
number = int(input("Enter an integer to calculate its factorial: ")) 
 
# Call the remote procedure (factorial) and print the result 
result = server.factorial(number) 
print(f"The factorial of {number} is {result}")
