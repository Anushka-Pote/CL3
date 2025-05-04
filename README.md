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

## 🛠️ Optional: Clean Up

```bash
hadoop fs -rm -r /input /output
```

---

Let me know if you'd like a ready-made batch script to automate this whole process.
