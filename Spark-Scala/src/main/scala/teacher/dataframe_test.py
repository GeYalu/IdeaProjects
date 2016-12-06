import sys
from pyspark.sql import SparkSession
from pyspark.sql import Row
from pyspark.sql.types import StructField,StructType,StringType
from pyspark.sql.functions import *

if __name__ == "__main__":
    spark = SparkSession.builder.appName("Spark Example").config("spark.sql.warehouse.dir","file:///").getOrCreate()
    sc = spark.sparkContext
    people_rdd = sc.textFile("C:\Users\Administrator\IdeaProjects\scala-tu\sample_age_data.txt")
    people_df = people_rdd.map(lambda people : people.split(" ")).map(lambda people:Row(name=people[0], age=int(people[1]))).toDF()
    #people_df.show()
    people_df.printSchema()
    people_df.createTempView("people")
    spark.sql("select * from people").show
    #---------------------------------------
    schema_string = "id,age"
    fields = [StructField(field, StringType(), True) for field in schema_string.split(",")]
    schema = StructType(fields)
    person_df = spark.createDataFrame(people_rdd.map(lambda p: (p.split(" ")[0], p.split(" ")[1])), schema)
    person_df.sort(person_df.id.desc()).show()
    #--------------------------------------------
    person_df.orderBy(["age","id"], ascending=[0,1]).show()
    spark.stop()