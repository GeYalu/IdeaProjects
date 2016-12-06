package logAnalysis_hive;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.util.ArrayList;

/*
    通过Hive对数据进行查询，将查询处理的结果存入Mysql，使用DBUtil_Mysql类
 */


public class AnalysisLog_with_Hive {

    public static Statement conn() {
        //连接hive前，需要在hive/bin下启动 hiveserver2
        String driverName = "org.apache.hive.jdbc.HiveDriver";
        String user = "hadoop";
        String passwd = "123456789";
        try {
            Class.forName(driverName);
            Connection con = DriverManager.getConnection("jdbc:hive2://192.168.2.240:10000", user, passwd);
            Statement stmt = con.createStatement();
            return stmt;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int insertMysql(String date, String uv, String pv, String retention) {
        Connection conn = DBUtil_Mysql.getConnection();
        String sql = "INSERT INTO logany(date,uv,pv,retention) VALUES(?,?,?,?)";
        Object[] param = {date, uv, pv, retention};
        int Result = DBUtil_Mysql.excuteUpdate(sql, param);
        return Result;
    }

    public static int insertMysqlTop10(ArrayList list, String date) {

        String top1 = list.get(0).toString();
        String top2 = list.get(1).toString();
        String top3 = list.get(2).toString();
        String top4 = list.get(3).toString();
        String top5 = list.get(4).toString();

        Connection conn = DBUtil_Mysql.getConnection();

        String sql = "UPDATE logany SET top1=?,top2=?,top3=?,top4=?,top5=?  where date = ?";
        Object[] param = {top1, top2, top3, top4, top5, date};

        int Result = DBUtil_Mysql.excuteUpdate(sql, param);
        return Result;
    }

    public static String getUV(String date) throws SQLException {
        Statement stmt = conn();
        String uvsqldate = "'" + date + "%" + "'";
        String uvSql = " select count(distinct ip) from log where date1 like " + uvsqldate;
        System.out.println("Running: " + uvSql);
        ResultSet uvres = stmt.executeQuery(uvSql);
        if (uvres.next()) {
            System.out.println("UV " + uvres.getString(1));
        }
        return uvres.getString(1);
    }

    public static String getPV(String date) throws SQLException {
        Statement stmt = conn();
        String pvsqldate = "'" + date + "%" + "'";
        String pvSql = " select count(*) from log where date1 like " + pvsqldate;
        System.out.println("Running: " + pvSql);
        ResultSet pvres = stmt.executeQuery(pvSql);
        if (pvres.next()) {
            System.out.println("PV " + pvres.getString(1));
        }
        return pvres.getString(1);
    }

    public static String getRetention(String date) throws SQLException {
        Statement stmt = conn();
        String Retentiondate = "'" + date + "%" + "'";
        int date1 = Integer.parseInt(date) + 1;

        String Retentiondate1 = "'" + date1 + "%" + "'";
        String RetentionSql = " select count(distinct b.ip) from log b where b.ip in (select distinct a.ip from log a where date1 like" + Retentiondate + ") and b.date1 like" + Retentiondate1;
        System.out.println("Running: " + RetentionSql);
        ResultSet Retentionres = stmt.executeQuery(RetentionSql);
        if (Retentionres.next()) {
            System.out.println("PV" + Retentionres.getString(1));
        }

        return Retentionres.getString(1);
    }

    public static ArrayList getTOP10(String date) throws SQLException {

        ArrayList result = new ArrayList();
        Statement stmt = conn();
        String topsqldate = "'" + date + "%" + "'";
        String topSql = "select url,count(1) num from log where date1 like " + topsqldate + " and url like '/show/%' group by url order by num desc limit 10";
        System.out.println("Running: " + topSql);
        ResultSet topres = stmt.executeQuery(topSql);
        while (topres.next()) {
            System.out.println("URL " + topres.getString("url") + "Num " + topres.getString("num"));
            result.add(topres.getString("url"));
        }
        return result;
    }

    public static void main(String[] args) throws SQLException {

        String date = "20151002";

        String uv = getUV(date);

        System.out.println("UV"+uv);

        //String pv = getPV(date);
 /*       String retention = getRetention(date);
        System.out.println("UV " + uv + " PV " + pv + " Retention " + retention);

        int res = insertMysql(date, uv, pv, retention);
        System.out.println(res);

        int res1 = insertMysqlTop10(getTOP10(date), date);
        System.out.println(res1);
*/

//-------------------------------------------------------------------------------------------

/*
        Statement stmt = conn();
        String sql = "select * from logtest limit 10" ;
        ResultSet res = stmt.executeQuery(sql);
        while (res.next()) {
            System.out.println(String.valueOf(res.getString(1)) + "\t" + res.getString(2));
        }
*/

		/*stmt.execute("drop table if exists " + tableName);
        stmt.execute("create table " + tableName + " (key int, value string)");
		System.out.println("Create table success!");*/

        // show tables
/*        String sql = "show tables '" + tableName + "'";
        System.out.println("Running: " + sql);
        ResultSet res = stmt.executeQuery(sql);
        if (res.next()) {
            System.out.println(res.getString(1));
        }*/
/*
        */

/*        String pvsqldate = "'20151001%'";
        String pvSql = " select count(*) from log where date1 like "+pvsqldate;
        System.out.println("Running: " + pvSql);
        ResultSet pvres = stmt.executeQuery(pvSql);
        if (pvres.next()) {
            System.out.println("PV"+pvres.getString(1));
        }*/


/*		// describe table
        sql = "describe " + tableName;
		System.out.println("Running: " + sql);
		res = stmt.executeQuery(sql);
		while (res.next()) {
			System.out.println(res.getString(1) + "\t" + res.getString(2));
		}*/

/*		sql = "select * from " + tableName;
		res = stmt.executeQuery(sql);
		while (res.next()) {
			System.out.println(String.valueOf(res.getInt(1)) + "\t" + res.getString(2));
		}

		sql = "select count(1) from " + tableName;
		System.out.println("Running: " + sql);
		res = stmt.executeQuery(sql);
		while (res.next()) {
			System.out.println(res.getString(1));
		}*/
    }
}