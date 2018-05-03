package org.helper.jdbctracer;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.logging.Level;

final class TraceConnection implements Connection {
    private final Connection wrapped;
    final Output output;

    TraceConnection(Connection toWrap, Output o) {
        wrapped = toWrap;
        output = o;
    }

    public Statement createStatement() throws SQLException {
        return new TraceStatement(wrapped.createStatement(), this);
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return output.act(Level.INFO, sql,
                () -> new TracePreparedStatement(wrapped.prepareStatement(sql), this, sql));
    }

    public CallableStatement prepareCall(String sql) throws SQLException {
        return output.act(Level.INFO, sql,
                () -> new TraceCallableStatement(wrapped.prepareCall(sql), this, sql));
    }

    public String nativeSQL(String sql) throws SQLException {
        return output.act(Level.INFO, sql, () -> wrapped.nativeSQL(sql));
    }

    public void setAutoCommit(boolean autoCommit) throws SQLException {
        output.act(Level.INFO, "SET AUTOCOMMIT", () -> wrapped.setAutoCommit(autoCommit), autoCommit);
    }

    public boolean getAutoCommit() throws SQLException {
        return output.act(Level.FINE, "GET AUTOCOMMIT", wrapped::getAutoCommit);
    }

    public void commit() throws SQLException {
        output.act(Level.INFO, "COMMIT", wrapped::commit, null);
    }

    public void rollback() throws SQLException {
        output.act(Level.INFO, "ROLLBACK", wrapped::rollback, null);
    }

    public void close() throws SQLException {
        output.act(Level.INFO, "CLOSE", wrapped::close, null);
    }

    public boolean isClosed() throws SQLException {
        return wrapped.isClosed();
    }

    public DatabaseMetaData getMetaData() throws SQLException {
        return wrapped.getMetaData();
    }

    public void setReadOnly(boolean readOnly) throws SQLException {
        output.act(Level.INFO, "SET READONLY", () -> wrapped.setReadOnly(readOnly), readOnly);
    }

    public boolean isReadOnly() throws SQLException {
        return output.act(Level.FINE, "IS READONLY", wrapped::isReadOnly);
    }

    public void setCatalog(String catalog) throws SQLException {
        output.act(Level.INFO, "SET CATALOG", () -> wrapped.setCatalog(catalog), catalog);
    }

    public String getCatalog() throws SQLException {
        return output.act(Level.FINE, "GET CATALOG", wrapped::getCatalog);
    }

    public void setTransactionIsolation(int level) throws SQLException {
        output.act(Level.INFO, "SET ISO_LEVEL", () -> wrapped.setTransactionIsolation(level), level);
    }

    public int getTransactionIsolation() throws SQLException {
        return output.act(Level.FINE, "GET ISO_LEVEL", wrapped::getTransactionIsolation);
    }

    public SQLWarning getWarnings() throws SQLException {
        return wrapped.getWarnings();
    }

    public void clearWarnings() throws SQLException {
        wrapped.clearWarnings();
    }

    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        return new TraceStatement(wrapped.createStatement(resultSetType, resultSetConcurrency), this);
    }

    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return output.act(Level.INFO, sql, () -> new TracePreparedStatement(wrapped.prepareStatement(sql, resultSetType, resultSetConcurrency), this, sql));
    }

    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return output.act(Level.INFO, sql, () -> new TraceCallableStatement(wrapped.prepareCall(sql, resultSetType, resultSetConcurrency), this, sql));
    }

    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return wrapped.getTypeMap();
    }

    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        wrapped.setTypeMap(map);
    }

    public void setHoldability(int holdability) throws SQLException {
        output.act(Level.FINE, "SET HOLDABILITY", () -> wrapped.setHoldability(holdability), holdability);
    }

    public int getHoldability() throws SQLException {
        return wrapped.getHoldability();
    }

    public Savepoint setSavepoint() throws SQLException {
        return output.act(Level.INFO, "SAVEPOINT", wrapped::setSavepoint);
    }

    public Savepoint setSavepoint(String name) throws SQLException {
        return output.act(Level.INFO, "SAVEPOINT "+name, () -> wrapped.setSavepoint(name));
    }

    public void rollback(Savepoint savepoint) throws SQLException {
        output.act(Level.INFO, "ROLLBACK SAVEPOINT", () -> wrapped.rollback(savepoint), savepoint);
    }

    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        output.act(Level.INFO, "RELEASE SAVEPOINT", () -> wrapped.releaseSavepoint(savepoint), savepoint);
    }

    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return new TraceStatement(wrapped.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability), this);
    }

    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return output.act(Level.INFO, sql, () -> new TracePreparedStatement(wrapped.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability), this, sql));
    }

    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return output.act(Level.INFO, sql, () -> new TraceCallableStatement(wrapped.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability), this, sql));
    }

    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        return output.act(Level.INFO, sql, () -> new TracePreparedStatement(wrapped.prepareStatement(sql, autoGeneratedKeys), this, sql));
    }

    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        return output.act(Level.INFO, sql, () -> new TracePreparedStatement(wrapped.prepareStatement(sql, columnIndexes), this, sql));
    }

    public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        return output.act(Level.INFO, sql, () -> new TracePreparedStatement(wrapped.prepareStatement(sql, columnNames), this, sql));
    }

    public Clob createClob() throws SQLException {
        return wrapped.createClob();
    }

    public Blob createBlob() throws SQLException {
        return wrapped.createBlob();
    }

    public NClob createNClob() throws SQLException {
        return wrapped.createNClob();
    }

    public SQLXML createSQLXML() throws SQLException {
        return wrapped.createSQLXML();
    }

    public boolean isValid(int timeout) throws SQLException {
        return output.act(Level.FINE, "IS VALID "+timeout, () -> wrapped.isValid(timeout));
    }

    public void setClientInfo(String name, String value) throws SQLClientInfoException {
        wrapped.setClientInfo(name, value);
    }

    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        wrapped.setClientInfo(properties);
    }

    public String getClientInfo(String name) throws SQLException {
        return wrapped.getClientInfo(name);
    }

    public Properties getClientInfo() throws SQLException {
        return wrapped.getClientInfo();
    }

    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        return wrapped.createArrayOf(typeName, elements);
    }

    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        return wrapped.createStruct(typeName, attributes);
    }

    public void setSchema(String schema) throws SQLException {
        output.act(Level.INFO, "SET SCHEMA", () -> wrapped.setSchema(schema), schema);
    }

    public String getSchema() throws SQLException {
        return output.act(Level.FINE, "GET SCHEMA", wrapped::getSchema);
    }

    public void abort(Executor executor) throws SQLException {
        output.act(Level.INFO, "ABORT EXECUTOR", () -> wrapped.abort(executor), executor);
    }

    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
        output.act(Level.FINE, "SET NETWORKTIMEOUT "+milliseconds, () -> wrapped.setNetworkTimeout(executor, milliseconds), executor);
    }

    public int getNetworkTimeout() throws SQLException {
        return output.act(Level.FINER, "GET NETWORKTIMOUT", wrapped::getNetworkTimeout);
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        return wrapped.unwrap(iface);
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return wrapped.isWrapperFor(iface);
    }

    @Override
    public String toString() {
        return Output.idStr(this);
    }
}
