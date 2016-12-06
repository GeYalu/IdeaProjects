import sys
from pyspark.sql import SparkSession
from pyspark.sql import Row
from pyspark.sql.functions import *

if __name__ == "__main__":
    spark = SparkSession.builder\
        .appName("Spark Location Exploring")\
        .config("spark.sql.warehouse.dir", "file:///")\
        .getOrCreate()
    sc = spark.sparkContext
    sc.setLogLevel("WARN")
    people_rdd = sc.textFile(sys.argv[1])
    location_rdd = sc.textFile(sys.argv[2])
    people_df = people_rdd.map(lambda p:p.split(" "))\
        .map(lambda p:Row(id=p[0],gender=p[1],height=int(p[2]), weight=int(p[3]), age=int(p[3]))).toDF()
    location_df = location_rdd.map(lambda l:l.split(" "))\
        .map(lambda l:Row(id=l[0],lat=float(l[1]), lon=float(l[2]))).toDF()
    people_df.createOrReplaceTempView("people")
    location_df.createOrReplaceTempView("location")
    person_df = spark.read\
        .csv(sys.argv[3],header=True,sep=" ",inferSchema=True)
    person_df.printSchema()

    spark.stop