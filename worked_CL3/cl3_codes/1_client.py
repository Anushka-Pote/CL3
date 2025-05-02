import xmlrpc.client
import base64

proxy = xmlrpc.client.ServerProxy("http://localhost:8000/")
n = int(input("Enter an integer: "))

# Get the base64 encoded result
encoded_result = proxy.factorial(n)

# Decode the base64 result
decoded_result = base64.b64decode(encoded_result).decode('utf-8')

print(f"Factorial of {n} is {decoded_result}")
