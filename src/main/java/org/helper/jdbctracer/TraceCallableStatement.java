package org.helper.jdbctracer;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.Map;

final class TraceCallableStatement<T extends CallableStatement> extends TracePreparedStatement<T> implements CallableStatement {
    TraceCallableStatement(T toWrap, TraceConnection c, String s) {
        super(toWrap, c, s);
    }

    public void registerOutParameter(int parameterIndex, int sqlType) throws SQLException {
        wrapped.registerOutParameter(parameterIndex, sqlType);
    }

    public void registerOutParameter(int parameterIndex, int sqlType, int scale) throws SQLException {
        wrapped.registerOutParameter(parameterIndex, sqlType, scale);
    }

    public boolean wasNull() throws SQLException {
        return wrapped.wasNull();
    }

    public String getString(int parameterIndex) throws SQLException {
        return wrapped.getString(parameterIndex);
    }

    public boolean getBoolean(int parameterIndex) throws SQLException {
        return wrapped.getBoolean(parameterIndex);
    }

    public byte getByte(int parameterIndex) throws SQLException {
        return wrapped.getByte(parameterIndex);
    }

    public short getShort(int parameterIndex) throws SQLException {
        return wrapped.getShort(parameterIndex);
    }

    public int getInt(int parameterIndex) throws SQLException {
        return wrapped.getInt(parameterIndex);
    }

    public long getLong(int parameterIndex) throws SQLException {
        return wrapped.getLong(parameterIndex);
    }

    public float getFloat(int parameterIndex) throws SQLException {
        return wrapped.getFloat(parameterIndex);
    }

    public double getDouble(int parameterIndex) throws SQLException {
        return wrapped.getDouble(parameterIndex);
    }

    @SuppressWarnings("deprecation")
    public BigDecimal getBigDecimal(int parameterIndex, int scale) throws SQLException {
        return wrapped.getBigDecimal(parameterIndex, scale);
    }

    public byte[] getBytes(int parameterIndex) throws SQLException {
        return wrapped.getBytes(parameterIndex);
    }

    public Date getDate(int parameterIndex) throws SQLException {
        return wrapped.getDate(parameterIndex);
    }

    public Time getTime(int parameterIndex) throws SQLException {
        return wrapped.getTime(parameterIndex);
    }

    public Timestamp getTimestamp(int parameterIndex) throws SQLException {
        return wrapped.getTimestamp(parameterIndex);
    }

    public Object getObject(int parameterIndex) throws SQLException {
        return wrapped.getObject(parameterIndex);
    }

    public BigDecimal getBigDecimal(int parameterIndex) throws SQLException {
        return wrapped.getBigDecimal(parameterIndex);
    }

    public Object getObject(int parameterIndex, Map<String, Class<?>> map) throws SQLException {
        return wrapped.getObject(parameterIndex, map);
    }

    public Ref getRef(int parameterIndex) throws SQLException {
        return wrapped.getRef(parameterIndex);
    }

    public Blob getBlob(int parameterIndex) throws SQLException {
        return wrapped.getBlob(parameterIndex);
    }

    public Clob getClob(int parameterIndex) throws SQLException {
        return wrapped.getClob(parameterIndex);
    }

    public Array getArray(int parameterIndex) throws SQLException {
        return wrapped.getArray(parameterIndex);
    }

    public Date getDate(int parameterIndex, Calendar cal) throws SQLException {
        return wrapped.getDate(parameterIndex, cal);
    }

    public Time getTime(int parameterIndex, Calendar cal) throws SQLException {
        return wrapped.getTime(parameterIndex, cal);
    }

    public Timestamp getTimestamp(int parameterIndex, Calendar cal) throws SQLException {
        return wrapped.getTimestamp(parameterIndex, cal);
    }

    public void registerOutParameter(int parameterIndex, int sqlType, String typeName) throws SQLException {
        wrapped.registerOutParameter(parameterIndex, sqlType, typeName);
    }

    public void registerOutParameter(String parameterName, int sqlType) throws SQLException {
        wrapped.registerOutParameter(parameterName, sqlType);
    }

    public void registerOutParameter(String parameterName, int sqlType, int scale) throws SQLException {
        wrapped.registerOutParameter(parameterName, sqlType, scale);
    }

    public void registerOutParameter(String parameterName, int sqlType, String typeName) throws SQLException {
        wrapped.registerOutParameter(parameterName, sqlType, typeName);
    }

    public URL getURL(int parameterIndex) throws SQLException {
        return wrapped.getURL(parameterIndex);
    }

    public void setURL(String parameterName, URL val) throws SQLException {
        wrapped.setURL(parameterName, val);
    }

    public void setNull(String parameterName, int sqlType) throws SQLException {
        wrapped.setNull(parameterName, sqlType);
    }

    public void setBoolean(String parameterName, boolean x) throws SQLException {
        wrapped.setBoolean(parameterName, x);
    }

    public void setByte(String parameterName, byte x) throws SQLException {
        wrapped.setByte(parameterName, x);
    }

    public void setShort(String parameterName, short x) throws SQLException {
        wrapped.setShort(parameterName, x);
    }

    public void setInt(String parameterName, int x) throws SQLException {
        wrapped.setInt(parameterName, x);
    }

    public void setLong(String parameterName, long x) throws SQLException {
        wrapped.setLong(parameterName, x);
    }

    public void setFloat(String parameterName, float x) throws SQLException {
        wrapped.setFloat(parameterName, x);
    }

    public void setDouble(String parameterName, double x) throws SQLException {
        wrapped.setDouble(parameterName, x);
    }

    public void setBigDecimal(String parameterName, BigDecimal x) throws SQLException {
        wrapped.setBigDecimal(parameterName, x);
    }

    public void setString(String parameterName, String x) throws SQLException {
        wrapped.setString(parameterName, x);
    }

    public void setBytes(String parameterName, byte[] x) throws SQLException {
        wrapped.setBytes(parameterName, x);
    }

    public void setDate(String parameterName, Date x) throws SQLException {
        wrapped.setDate(parameterName, x);
    }

    public void setTime(String parameterName, Time x) throws SQLException {
        wrapped.setTime(parameterName, x);
    }

    public void setTimestamp(String parameterName, Timestamp x) throws SQLException {
        wrapped.setTimestamp(parameterName, x);
    }

    public void setAsciiStream(String parameterName, InputStream x, int length) throws SQLException {
        wrapped.setAsciiStream(parameterName, x, length);
    }

    public void setBinaryStream(String parameterName, InputStream x, int length) throws SQLException {
        wrapped.setBinaryStream(parameterName, x, length);
    }

    public void setObject(String parameterName, Object x, int targetSqlType, int scale) throws SQLException {
        wrapped.setObject(parameterName, x, targetSqlType, scale);
    }

    public void setObject(String parameterName, Object x, int targetSqlType) throws SQLException {
        wrapped.setObject(parameterName, x, targetSqlType);
    }

    public void setObject(String parameterName, Object x) throws SQLException {
        wrapped.setObject(parameterName, x);
    }

    public void setCharacterStream(String parameterName, Reader reader, int length) throws SQLException {
        wrapped.setCharacterStream(parameterName, reader, length);
    }

    public void setDate(String parameterName, Date x, Calendar cal) throws SQLException {
        wrapped.setDate(parameterName, x, cal);
    }

    public void setTime(String parameterName, Time x, Calendar cal) throws SQLException {
        wrapped.setTime(parameterName, x, cal);
    }

    public void setTimestamp(String parameterName, Timestamp x, Calendar cal) throws SQLException {
        wrapped.setTimestamp(parameterName, x, cal);
    }

    public void setNull(String parameterName, int sqlType, String typeName) throws SQLException {
        wrapped.setNull(parameterName, sqlType, typeName);
    }

    public String getString(String parameterName) throws SQLException {
        return wrapped.getString(parameterName);
    }

    public boolean getBoolean(String parameterName) throws SQLException {
        return wrapped.getBoolean(parameterName);
    }

    public byte getByte(String parameterName) throws SQLException {
        return wrapped.getByte(parameterName);
    }

    public short getShort(String parameterName) throws SQLException {
        return wrapped.getShort(parameterName);
    }

    public int getInt(String parameterName) throws SQLException {
        return wrapped.getInt(parameterName);
    }

    public long getLong(String parameterName) throws SQLException {
        return wrapped.getLong(parameterName);
    }

    public float getFloat(String parameterName) throws SQLException {
        return wrapped.getFloat(parameterName);
    }

    public double getDouble(String parameterName) throws SQLException {
        return wrapped.getDouble(parameterName);
    }

    public byte[] getBytes(String parameterName) throws SQLException {
        return wrapped.getBytes(parameterName);
    }

    public Date getDate(String parameterName) throws SQLException {
        return wrapped.getDate(parameterName);
    }

    public Time getTime(String parameterName) throws SQLException {
        return wrapped.getTime(parameterName);
    }

    public Timestamp getTimestamp(String parameterName) throws SQLException {
        return wrapped.getTimestamp(parameterName);
    }

    public Object getObject(String parameterName) throws SQLException {
        return wrapped.getObject(parameterName);
    }

    public BigDecimal getBigDecimal(String parameterName) throws SQLException {
        return wrapped.getBigDecimal(parameterName);
    }

    public Object getObject(String parameterName, Map<String, Class<?>> map) throws SQLException {
        return wrapped.getObject(parameterName, map);
    }

    public Ref getRef(String parameterName) throws SQLException {
        return wrapped.getRef(parameterName);
    }

    public Blob getBlob(String parameterName) throws SQLException {
        return wrapped.getBlob(parameterName);
    }

    public Clob getClob(String parameterName) throws SQLException {
        return wrapped.getClob(parameterName);
    }

    public Array getArray(String parameterName) throws SQLException {
        return wrapped.getArray(parameterName);
    }

    public Date getDate(String parameterName, Calendar cal) throws SQLException {
        return wrapped.getDate(parameterName, cal);
    }

    public Time getTime(String parameterName, Calendar cal) throws SQLException {
        return wrapped.getTime(parameterName, cal);
    }

    public Timestamp getTimestamp(String parameterName, Calendar cal) throws SQLException {
        return wrapped.getTimestamp(parameterName, cal);
    }

    public URL getURL(String parameterName) throws SQLException {
        return wrapped.getURL(parameterName);
    }

    public RowId getRowId(int parameterIndex) throws SQLException {
        return wrapped.getRowId(parameterIndex);
    }

    public RowId getRowId(String parameterName) throws SQLException {
        return wrapped.getRowId(parameterName);
    }

    public void setRowId(String parameterName, RowId x) throws SQLException {
        wrapped.setRowId(parameterName, x);
    }

    public void setNString(String parameterName, String value) throws SQLException {
        wrapped.setNString(parameterName, value);
    }

    public void setNCharacterStream(String parameterName, Reader value, long length) throws SQLException {
        wrapped.setNCharacterStream(parameterName, value, length);
    }

    public void setNClob(String parameterName, NClob value) throws SQLException {
        wrapped.setNClob(parameterName, value);
    }

    public void setClob(String parameterName, Reader reader, long length) throws SQLException {
        wrapped.setClob(parameterName, reader, length);
    }

    public void setBlob(String parameterName, InputStream inputStream, long length) throws SQLException {
        wrapped.setBlob(parameterName, inputStream, length);
    }

    public void setNClob(String parameterName, Reader reader, long length) throws SQLException {
        wrapped.setNClob(parameterName, reader, length);
    }

    public NClob getNClob(int parameterIndex) throws SQLException {
        return wrapped.getNClob(parameterIndex);
    }

    public NClob getNClob(String parameterName) throws SQLException {
        return wrapped.getNClob(parameterName);
    }

    public void setSQLXML(String parameterName, SQLXML xmlObject) throws SQLException {
        wrapped.setSQLXML(parameterName, xmlObject);
    }

    public SQLXML getSQLXML(int parameterIndex) throws SQLException {
        return wrapped.getSQLXML(parameterIndex);
    }

    public SQLXML getSQLXML(String parameterName) throws SQLException {
        return wrapped.getSQLXML(parameterName);
    }

    public String getNString(int parameterIndex) throws SQLException {
        return wrapped.getNString(parameterIndex);
    }

    public String getNString(String parameterName) throws SQLException {
        return wrapped.getNString(parameterName);
    }

    public Reader getNCharacterStream(int parameterIndex) throws SQLException {
        return wrapped.getNCharacterStream(parameterIndex);
    }

    public Reader getNCharacterStream(String parameterName) throws SQLException {
        return wrapped.getNCharacterStream(parameterName);
    }

    public Reader getCharacterStream(int parameterIndex) throws SQLException {
        return wrapped.getCharacterStream(parameterIndex);
    }

    public Reader getCharacterStream(String parameterName) throws SQLException {
        return wrapped.getCharacterStream(parameterName);
    }

    public void setBlob(String parameterName, Blob x) throws SQLException {
        wrapped.setBlob(parameterName, x);
    }

    public void setClob(String parameterName, Clob x) throws SQLException {
        wrapped.setClob(parameterName, x);
    }

    public void setAsciiStream(String parameterName, InputStream x, long length) throws SQLException {
        wrapped.setAsciiStream(parameterName, x, length);
    }

    public void setBinaryStream(String parameterName, InputStream x, long length) throws SQLException {
        wrapped.setBinaryStream(parameterName, x, length);
    }

    public void setCharacterStream(String parameterName, Reader reader, long length) throws SQLException {
        wrapped.setCharacterStream(parameterName, reader, length);
    }

    public void setAsciiStream(String parameterName, InputStream x) throws SQLException {
        wrapped.setAsciiStream(parameterName, x);
    }

    public void setBinaryStream(String parameterName, InputStream x) throws SQLException {
        wrapped.setBinaryStream(parameterName, x);
    }

    public void setCharacterStream(String parameterName, Reader reader) throws SQLException {
        wrapped.setCharacterStream(parameterName, reader);
    }

    public void setNCharacterStream(String parameterName, Reader value) throws SQLException {
        wrapped.setNCharacterStream(parameterName, value);
    }

    public void setClob(String parameterName, Reader reader) throws SQLException {
        wrapped.setClob(parameterName, reader);
    }

    public void setBlob(String parameterName, InputStream inputStream) throws SQLException {
        wrapped.setBlob(parameterName, inputStream);
    }

    public void setNClob(String parameterName, Reader reader) throws SQLException {
        wrapped.setNClob(parameterName, reader);
    }

    public <K> K getObject(int parameterIndex, Class<K> type) throws SQLException {
        return wrapped.getObject(parameterIndex, type);
    }

    public <K> K getObject(String parameterName, Class<K> type) throws SQLException {
        return wrapped.getObject(parameterName, type);
    }
}
