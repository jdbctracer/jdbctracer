package org.helper.jdbctracer;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class TraceDriver implements Driver {
    private static int cnt;
    private final static Logger logger;
    private static final String prefix = "jdbctrace(";

    /**
     * Setup a JDBC connection url for tracing
     * @param url The URL to use (starting with jdbc: )
     * @param level The trace level to use (NONE disables tracing, NULL uses runtime-configurable JDBC log level)
     * @return JDBC URL with/without tracing
     */
    public static String setup(String url, String level) {
        if (url != null && !url.startsWith(prefix) && !"NONE".equalsIgnoreCase(level)) {
            return level == null ? prefix + ")"+url : prefix+level+")"+url;
        }
        return url;
    }

    public TraceDriver() {
    }

    static {
        logger = Logger.getLogger("JDBC");
        try { DriverManager.registerDriver(new TraceDriver()); } catch (SQLException e) { }
    }

    public Connection connect(String url, Properties info) throws SQLException {
        int level = extract(url);
        if (level == 0) return null;
        String theUrl = urlOf(url);
        if (level <= 1) return DriverManager.getConnection(theUrl, info);

        Output o = new Output("#" + nextId(), logger, level);
        return o.act(2, "OPEN "+theUrl, () -> new TraceConnection(DriverManager.getConnection(theUrl, info), o));
    }

    public boolean acceptsURL(String url) {
        return url != null && url.startsWith(prefix) && url.contains(")jdbc:");
    }

    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) {
        return new DriverPropertyInfo[0];
    }

    public int getMajorVersion() {
        return 1;
    }

    public int getMinorVersion() {
        return 0;
    }

    public boolean jdbcCompliant() {
        return true;
    }

    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new SQLFeatureNotSupportedException();
    }

    private int extract(String url) {
        if (acceptsURL(url)) {
            String level = url.substring(prefix.length(), url.indexOf(")jdbc:"));
            String def = logger.isLoggable(Level.ALL) ? "ALL" : logger.isLoggable(Level.INFO) ? "INFO" : "NONE";

            if ("".equalsIgnoreCase(level) || "default".equalsIgnoreCase(level)) level = def;
            switch (level.toUpperCase()) {
                // Open/Statement/Parameter/All
                case "NONE": return 1;
                case "INFO": return 2;
                case "DEBUG": return 3;
                case "ALL": return 4;
                default: return 2;
            }
        }
        return 0;
    }

    private static String urlOf(String u) {
        return u.substring(u.indexOf("jdbc:"));
    }

    private int nextId() {
        synchronized (logger) {
            return ++cnt;
        }
    }
}
