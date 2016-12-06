package Hive;

/**
 * UDF  Upper结果
 */

import org.apache.hadoop.hive.ql.exec.UDF;

public class UDFUpper extends UDF {
    public static String evaluate(String str) {
        return str.toUpperCase();
    }
}
