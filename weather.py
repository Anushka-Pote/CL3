pip install pyspark
from pyspark.sql import SparkSession
from pyspark.sql.functions import col, year, max, min

# Create Spark session
spark = SparkSession.builder.appName("Weather Analysis").getOrCreate()

# Load the CSV file (make sure header=True and schema is inferred)
df = spark.read.csv("weather_data.csv", header=True, inferSchema=True)

# Extract year from the date column
df = df.withColumn("Year", year(col("Date")))

# Filter for TMAX (Hottest)
tmax_df = df.filter(col("Type") == "TMAX")
hottest_year = tmax_df.groupBy("Year").agg(max("Temp").alias("MaxTemp"))
hottest_year_ordered = hottest_year.orderBy(col("MaxTemp").desc())

# Filter for TMIN (Coolest)
tmin_df = df.filter(col("Type") == "TMIN")
coolest_year = tmin_df.groupBy("Year").agg(min("Temp").alias("MinTemp"))
coolest_year_ordered = coolest_year.orderBy(col("MinTemp").asc())

# Show results
print("🔥 Hottest Year(s):")
hottest_year_ordered.show(5)

print("❄️ Coolest Year(s):")
coolest_year_ordered.show(5)

# Stop Spark session
spark.stop()
