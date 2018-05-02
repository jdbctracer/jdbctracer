package org.helper.jdbctracer;

import java.io.File;
import java.sql.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length == 0 || args[0].equals("-help")) {
            System.out.println("USAGE: url [-prop:name=value]* [sql]*");
            System.out.println("VERSION: " + GitRepositoryState.getGitRepositoryState().toString());
            System.exit(2);
        }
        if (!new TraceDriver().acceptsURL("jdbctrace(level)jdbc:")) {
            System.exit(3);
        }
        String separator = ",";
        Properties props = new Properties();
        int pos = 1;
        while (pos < args.length) {
            if (args[pos].startsWith("-")) {
                if (args[pos].startsWith("-p:") && args[pos].indexOf('=') > 0) {
                    String[] kv = args[pos].substring(3).split("=", 2);
                    props.setProperty(kv[0], kv[1]);
                    pos++;
                } else if (args[pos].startsWith("-sep:")) {
                    separator = args[pos].substring(5);
                }
            } else break;
        }

        try (Connection con = DriverManager.getConnection(args[0], props)){

            DatabaseMetaData md = con.getMetaData();
            System.err.println("JAR "+ getJar(md));
            System.err.println("DRIVERNAME "+ md.getDriverName());
            System.err.println("DRIVERVERSION "+ md.getDriverVersion());
            System.err.println("DATABASEPRODUCTNAME "+ md.getDatabaseProductName());
            System.err.println("DATABASEPRODUCTVERSION "+ md.getDatabaseProductVersion());

            while (pos < args.length) {
                Statement stmt = con.createStatement();
                if (stmt.execute(args[pos])) {
                    try (ResultSet rs = stmt.getResultSet()) {
                        String sep = "";
                        ResultSetMetaData rsmd = rs.getMetaData();
                        int len = rsmd.getColumnCount();
                        for (int i = 1; i <= len; i++) {
                            System.out.print(sep); System.out.print(rsmd.getColumnName(i)); sep = separator;
                        }
                        System.out.println();
                        while (rs.next()) {
                            sep = "";
                            for (int i = 1; i <= len; i++) {
                                System.out.print(sep); System.out.print(rs.getObject(i)); sep = separator;
                            }
                            System.out.println();
                        }
                    }
                } else {
                    System.out.println(stmt.getUpdateCount());
                }
                pos++;
            }
            System.exit(0);
        } catch (SQLException e) {
            System.err.println(e.toString());
            System.exit(1);
        }
    }

    private static String getJar(Object o) {
        try {
            return new File(o.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getPath();
        } catch (Exception e) {
            return "unknown";
        }
    }
}
