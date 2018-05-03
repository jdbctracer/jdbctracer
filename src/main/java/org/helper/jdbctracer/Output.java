package org.helper.jdbctracer;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

final class Output {
    final private String id;
    final private Logger logger;
    final private int level;

    Output(String idStr, Logger log, Level l) {
        id = idStr;
        logger = log;
        level = l.intValue();
    }

    interface MeasuredFunction<R> {
        R call() throws SQLException;
    }

    interface MeasuredFunctionV {
        void call() throws SQLException;
    }

    private static long millis() { return System.currentTimeMillis(); }

    boolean enabled(Level lvl) {
        return lvl.intValue() >= level;
    }

    private void log(Level lvl, long ms, String msg, Object result) {
        logger.log(lvl, "%dms %s %s -> %s", new Object[]{ms, id, msg, extract(result)});
    }

    <R> R act(Level lvl, String msg, MeasuredFunction<R> fun) throws SQLException {
        long start = millis();
        try {
            R result = fun.call();
            if ((enabled(lvl))) log(lvl, (millis()-start), msg, result);
            return result;
        } catch (SQLException e) {
            long end = millis();
            log(Level.SEVERE, (end-start), msg, extract(e));
            throw e;
        }
    }

    <T> void act(Level lvl, String msg, MeasuredFunctionV fun, T o) throws SQLException {
        long start = millis();
        try {
            fun.call();
            if ((enabled(lvl))) log(lvl, (millis()-start), msg, o == null ? "" : o);
        } catch (SQLException e) {
            long end = millis();
            log(Level.SEVERE, (end-start), msg, extract(e));
            throw e;
        }
    }

    private static String extract(Set<Throwable> seen, Throwable e) {
        if (seen.add(e)) {
            String m = e.getMessage();
            Throwable t = e.getCause();
            if (t != null) return m + " " + extract(seen, t);
            return m;
        } else return "";
    }

    private static String extract(Object o) {
        if (o == null) return "";
        if (o instanceof CallableStatement) return "callable";
        if (o instanceof PreparedStatement) return "prepared";
        if (o instanceof ResultSet) return "resultset";
        if (o instanceof Exception) return extract(new HashSet<>(), (Exception)o);
        return o.toString();
    }

    static String idStr(Object obj) {
       return "@"+ Integer.toHexString(obj.hashCode());
    }
}
