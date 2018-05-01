package org.helper.jdbctracer;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

final class Output {
    final private String id;
    final private Logger logger;
    final int kind;
    final private static String[] l2s = new String[] { "ERROR", "WARN", "INFO", "DEBUG", "ALL" };

    Output(String idStr, Logger log, int k) {
        id = idStr;
        logger = log;
        kind = k;
    }

    interface MeasuredFunction<R> {
        R call() throws SQLException;
    }

    interface MeasuredFunctionV {
        void call() throws SQLException;
    }

    <R> R act(int level, String msg, MeasuredFunction<R> fun) throws SQLException {
        long start = System.currentTimeMillis();
        try {
            R result = fun.call();
            if (level <= kind) log(l2s[level], (System.currentTimeMillis()-start), msg, result);
            return result;
        } catch (SQLException e) {
            long end = System.currentTimeMillis();
            log("ERROR", (end-start), msg, extract(e));
            throw e;
        }
    }

    <T> void act(int level, String msg, MeasuredFunctionV fun, T o) throws SQLException {
        long start = System.currentTimeMillis();
        try {
            fun.call();
            if (level <= kind) log(l2s[level], (System.currentTimeMillis()-start), msg, o == null ? "" : o);
        } catch (SQLException e) {
            long end = System.currentTimeMillis();
            log("ERROR", (end-start), msg, extract(e));
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

    void log(String sev, long ms, String msg, Object result) {
        System.err.println(""+sev+" "+ms+" "+id+" "+msg+" -> "+extract(result));
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
