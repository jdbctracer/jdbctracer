package org.helper.jdbctracer;

import java.io.File;
import java.sql.*;
import java.util.Enumeration;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length == 0 || args[0].equals("-help")) {
            System.out.println("VERSION: " + GitRepositoryState.getGitRepositoryState().toString());
            System.out.println("USAGE: url [-prop:name=value]* [-us:char] [-rs:char] [-gs:char] [-header|-headerX] [-drivers] [sql]*");
            System.out.println("  -prop:name=value Property passed to JDBC driver");
            System.out.println("  -us:char         Character to use to separate units (default:comma)");
            System.out.println("  -rs:char         Character to use to separate records (default:newline)");
            System.out.println("  -gs:char         Character to use to separate groups (default:GS)");
            System.out.println("  -header          Write header line with field names");
            System.out.println("  -headerX         Write header line with field names/types");
            System.out.println("  -drivers         Print installed drivers");
            System.out.println("  sql              SQL statements to execute");
            System.exit(2);
        }
        if (!new TraceDriver().acceptsURL(TraceDriver.getPrefix()+"level)jdbc:")) {
            System.exit(3);
        }
        String unitSep = ",";
        String recordSep = System.lineSeparator();
        String groupSep = Character.toString((char)29);
        String fileSep = Character.toString((char)28);
        int header = 0;
        boolean drivers = false;

        Properties props = new Properties();
        int pos = 1;
        while (pos < args.length) {
            if (args[pos].startsWith("-")) {
                if (args[pos].startsWith("-p:") && args[pos].indexOf('=') > 0) {
                    String[] kv = args[pos].substring(3).split("=", 2);
                    props.setProperty(kv[0], kv[1]);
                } else if (args[pos].startsWith("-us:")) {
                    unitSep = args[pos].substring(4);
                } else if (args[pos].startsWith("-rs:")) {
                    recordSep = args[pos].substring(4);
                } else if (args[pos].startsWith("-gs:")) {
                    groupSep = args[pos].substring(4);
                } else if (args[pos].startsWith("-fs:")) {
                    fileSep = args[pos].substring(4);
                } else if (args[pos].equals("-header")) {
                    header = 1;
                } else if (args[pos].equals("-headerX")) {
                    header = 3;
                } else if (args[pos].equals("-drivers")) {
                    drivers = true;
                }
                pos++;
            } else break;
        }

        if (drivers) {
            Enumeration<Driver> drvrs = DriverManager.getDrivers();
            while (drvrs.hasMoreElements()) {
                Driver d = drvrs.nextElement();
                System.out.println(d.getClass().getName()+","+getJar(d));
            }
        }

        try (Connection con = DriverManager.getConnection(args[0], props)) {
            if (drivers) {
                DatabaseMetaData md = con.getMetaData();
                System.err.println("JAR "+ getJar(md));
                System.err.println("DRIVERNAME "+ md.getDriverName());
                System.err.println("DRIVERVERSION "+ md.getDriverVersion());
                System.err.println("DATABASEPRODUCTNAME "+ md.getDatabaseProductName());
                System.err.println("DATABASEPRODUCTVERSION "+ md.getDatabaseProductVersion());
            }

            String fs = "";
            while (pos < args.length) {
                System.out.print(fs); fs = fileSep;

                try (Statement stmt = con.createStatement()) {
                    if (stmt.execute(args[pos])) {
                        String gs = "";
                        do {
                            System.out.print(gs); gs = groupSep;
                            try (ResultSet rs = stmt.getResultSet()) {
                                String us = "";
                                ResultSetMetaData rsmd = rs.getMetaData();
                                int len = rsmd.getColumnCount();

                                if (header > 0) {
                                    for (int i = 1; i <= len; i++) {
                                        System.out.print(us); us = unitSep;
                                        System.out.print(rsmd.getColumnName(i));
                                        if (header == 3)
                                            System.out.print("/"+rsmd.getColumnTypeName(i));
                                    }
                                    System.out.print(recordSep);
                                }

                                while (rs.next()) {
                                    us = "";
                                    for (int i = 1; i <= len; i++) {
                                        System.out.print(us); us = unitSep;
                                        System.out.print(rs.getObject(i));
                                    }
                                    System.out.print(recordSep);
                                }
                            }
                        } while (stmt.getMoreResults());
                    } else {
                        System.out.println(stmt.getUpdateCount());
                    }
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
