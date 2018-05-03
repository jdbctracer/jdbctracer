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
     * @param level The trace level to use (NONE disables tracing, null uses runtime-configurable JDBC log level)
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
        Level level = extract(url);
        if (level == Level.OFF) return null;
        String theUrl = urlOf(url);
        if (level.intValue() > Level.SEVERE.intValue()) return DriverManager.getConnection(theUrl, info);

        Output o = new Output("#" + nextId(), logger, level);
        return o.act(Level.INFO, "OPEN "+theUrl, () -> new TraceConnection(DriverManager.getConnection(theUrl, info), o));
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

    private Level extract(String url) {
        if (acceptsURL(url)) {
            String level = url.substring(prefix.length(), url.indexOf(")jdbc:"));
            if ("".equalsIgnoreCase(level) || "default".equalsIgnoreCase(level)) {
                return logger.getLevel();
            }
            return Level.parse(level);
        }
        return Level.OFF;
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
