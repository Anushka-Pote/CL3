https://justpaste.it/gxvu5

for copypaste links


Perfect! Assuming you already have:

* `Hadoop` installed and working locally (pseudo-distributed mode is fine),
* Your `WeatherAnalysis.java` code is saved and ready,
* Your input file is `weather_data.csv` in your local filesystem,

Here’s a **step-by-step list of exact commands to run in CMD/Terminal**:

---

## ✅ Step-by-Step Hadoop Job Execution (CMD Commands)

### **1. Start Hadoop Daemons**

```bash
start-dfs.sh
start-yarn.sh
```

Verify services are running (optional):

```bash
jps
```

You should see things like: `NameNode`, `DataNode`, `ResourceManager`, etc.

---

### **2. Create Input Directory in HDFS**

```bash
hadoop fs -mkdir /input
```

---

### **3. Copy Your Input File to HDFS**

Make sure you're in the folder where `weather_data.csv` exists:

```bash
hadoop fs -put weather_data.csv /input
```

To verify:

```bash
hadoop fs -ls /input
```

---

### **4. Compile Your Java Code**

Save your Java code as `WeatherAnalysis.java`.

Then run:

```bash
javac -classpath `hadoop classpath` -d WeatherClasses WeatherAnalysis.java
```

Now package it into a JAR:

```bash
jar -cvf WeatherAnalysis.jar -C WeatherClasses/ .
```

---

### **5. Run the MapReduce Job**

```bash
hadoop jar WeatherAnalysis.jar WeatherAnalysis /input /output
```

> ⚠️ If `/output` already exists:

```bash
hadoop fs -rm -r /output
```

---

### **6. View the Output**

```bash
hadoop fs -cat /output/part-r-00000
```

This will show lines like:

```
Coolest in 2010    -5
Hottest in 2010    30
...
```

---

To run your Hadoop **MapReduce Java program** locally or on a Hadoop setup, follow these **complete steps** to **compile, package, and execute** the code using Hadoop commands (not just Java commands).

---

## ✅ Prerequisites

* Java JDK installed (`java -version`)
* Hadoop installed and configured (`hadoop version`)
* Environment variables set: `JAVA_HOME`, `HADOOP_HOME`, `PATH`
* Input file ready (`input.txt`)
* Your MapReduce code is saved in a `.java` file

---

## ✅ Step-by-Step: Run Hadoop MapReduce Java Program

### 📝 Step 1: Save Your Code

Save your MapReduce code (e.g., the character count version) in a file named:

```
CharacterCountMR.java
```

---

### 🧑‍💻 Step 2: Compile the Code Using Hadoop Compiler

Open terminal (or command prompt) and run:

```bash
hadoop com.sun.tools.javac.Main CharacterCountMR.java
```

This compiles the `.java` file and generates `.class` files.

---

### 📦 Step 3: Create a JAR File

Create a JAR file named `charcount.jar`:

```bash
jar cf charcount.jar CharacterCountMR*.class
```

> This packages all generated `.class` files into a JAR.

---

### 📂 Step 4: Prepare Input File in HDFS

Assume you have a text file `input.txt`.

```bash
hdfs dfs -mkdir -p /user/yourname/input
hdfs dfs -put input.txt /user/yourname/input/
```

> Replace `yourname` with your actual Hadoop user.

---

### 🚀 Step 5: Run the MapReduce Job

Run the job using:

```bash
hadoop jar charcount.jar CharacterCountMR /user/yourname/input /user/yourname/output
```

* `/user/yourname/input` is the HDFS input directory
* `/user/yourname/output` is the HDFS output directory (must not already exist)

---

### 📄 Step 6: View the Output

```bash
hdfs dfs -cat /user/yourname/output/part-r-00000
```

This will show character frequencies like:

```
H	1
a	2
d	2
...
```

---


