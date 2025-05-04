#charecter count 

text = """Hello world!
This is a test text file for character counting.
Let's count the characters!"""


def char_mapper(text):
    for char in text:
        if char.strip(): 
            yield (char, 1)

# Reduce function
def char_reducer(mapped_results):
    char_count = {}
    for char, count in mapped_results:
        if char in char_count:
            char_count[char] += count
        else:
            char_count[char] = count
    return char_count

# Simulate MapReduce
mapped_results = list(char_mapper(text))
char_counts = char_reducer(mapped_results)

# Display results
print("Character Counts:")
for char, count in char_counts.items():
    print(f"'{char}': {count}")

 #word count
text = """Hello world!
This is a test text file for word counting.
Let's count the words!"""


def word_mapper(text):
    words = text.lower().split()
    for word in words:
        word = ''.join(char for char in word if char.isalnum())
        if word:
            yield (word, 1)

# Reduce function
def word_reducer(mapped_results):
    word_count = {}
    for word, count in mapped_results:
        if word in word_count:
            word_count[word] += count
        else:
            word_count[word] = count
    return word_count

# Simulate MapReduce
mapped_results = list(word_mapper(text))
word_counts = word_reducer(mapped_results)

# Display results
print("Word Counts:")
for word, count in word_counts.items():
    print(f"'{word}': {count}")
