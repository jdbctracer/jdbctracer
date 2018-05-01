package org.helper.jdbctracer;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.Map;

final class TraceResultSet implements ResultSet {
    private final ResultSet wrapped;
    private final TraceStatement statement;
    private int cnt;

    TraceResultSet(ResultSet toWrap, TraceStatement s) {
        wrapped = toWrap;
        statement = s;
    }

    public void close() throws SQLException {
        statement.output.act(4, "CLOSE RS", wrapped::close, cnt);
    }

    public boolean next() throws SQLException {
        boolean n = wrapped.next();
        if (n) cnt++;
        return n;
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

    public InputStream getAsciiStream(int columnIndex) throws SQLException {
        return wrapped.getAsciiStream(columnIndex);
    }

    @SuppressWarnings("deprecation")
    public InputStream getUnicodeStream(int columnIndex) throws SQLException {
        return wrapped.getUnicodeStream(columnIndex);
    }

    public InputStream getBinaryStream(int columnIndex) throws SQLException {
        return wrapped.getBinaryStream(columnIndex);
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

    public URL getURL(int parameterIndex) throws SQLException {
        return wrapped.getURL(parameterIndex);
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

    @SuppressWarnings("deprecation")
    public BigDecimal getBigDecimal(String columnLabel, int scale) throws SQLException {
        return wrapped.getBigDecimal(columnLabel, scale);
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

    @Override
    public InputStream getAsciiStream(String columnLabel) throws SQLException {
        return wrapped.getAsciiStream(columnLabel);
    }

    @SuppressWarnings("deprecation")
    @Override
    public InputStream getUnicodeStream(String columnLabel) throws SQLException {
        return wrapped.getUnicodeStream(columnLabel);
    }

    @Override
    public InputStream getBinaryStream(String columnLabel) throws SQLException {
        return wrapped.getBinaryStream(columnLabel);
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return wrapped.getWarnings();
    }

    @Override
    public void clearWarnings() throws SQLException {
        wrapped.clearWarnings();
    }

    @Override
    public String getCursorName() throws SQLException {
        return wrapped.getCursorName();
    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return wrapped.getMetaData();
    }

    public Object getObject(String parameterName) throws SQLException {
        return wrapped.getObject(parameterName);
    }

    @Override
    public int findColumn(String columnLabel) throws SQLException {
        return wrapped.findColumn(columnLabel);
    }

    public BigDecimal getBigDecimal(String parameterName) throws SQLException {
        return wrapped.getBigDecimal(parameterName);
    }

    @Override
    public boolean isBeforeFirst() throws SQLException {
        return wrapped.isBeforeFirst();
    }

    @Override
    public boolean isAfterLast() throws SQLException {
        return wrapped.isAfterLast();
    }

    @Override
    public boolean isFirst() throws SQLException {
        return wrapped.isFirst();
    }

    @Override
    public boolean isLast() throws SQLException {
        return wrapped.isLast();
    }

    @Override
    public void beforeFirst() throws SQLException {
        wrapped.beforeFirst();
    }

    @Override
    public void afterLast() throws SQLException {
        wrapped.afterLast();
    }

    @Override
    public boolean first() throws SQLException {
        return wrapped.first();
    }

    @Override
    public boolean last() throws SQLException {
        return wrapped.last();
    }

    @Override
    public int getRow() throws SQLException {
        return wrapped.getRow();
    }

    @Override
    public boolean absolute(int row) throws SQLException {
        return wrapped.absolute(row);
    }

    @Override
    public boolean relative(int rows) throws SQLException {
        return wrapped.relative(rows);
    }

    @Override
    public boolean previous() throws SQLException {
        return wrapped.previous();
    }

    @Override
    public void setFetchDirection(int direction) throws SQLException {
        wrapped.setFetchDirection(direction);
    }

    @Override
    public int getFetchDirection() throws SQLException {
        return wrapped.getFetchDirection();
    }

    @Override
    public void setFetchSize(int rows) throws SQLException {
        wrapped.setFetchDirection(rows);
    }

    @Override
    public int getFetchSize() throws SQLException {
        return wrapped.getFetchSize();
    }

    @Override
    public int getType() throws SQLException {
        return wrapped.getType();
    }

    @Override
    public int getConcurrency() throws SQLException {
        return wrapped.getConcurrency();
    }

    @Override
    public boolean rowUpdated() throws SQLException {
        return wrapped.rowUpdated();
    }

    @Override
    public boolean rowInserted() throws SQLException {
        return wrapped.rowInserted();
    }

    @Override
    public boolean rowDeleted() throws SQLException {
        return wrapped.rowDeleted();
    }

    @Override
    public void updateNull(int columnIndex) throws SQLException {
        wrapped.updateNull(columnIndex);
    }

    @Override
    public void updateBoolean(int columnIndex, boolean x) throws SQLException {
        wrapped.updateBoolean(columnIndex, x);
    }

    @Override
    public void updateByte(int columnIndex, byte x) throws SQLException {
        wrapped.updateByte(columnIndex, x);
    }

    @Override
    public void updateShort(int columnIndex, short x) throws SQLException {
        wrapped.updateShort(columnIndex, x);
    }

    @Override
    public void updateInt(int columnIndex, int x) throws SQLException {
        wrapped.updateInt(columnIndex, x);
    }

    @Override
    public void updateLong(int columnIndex, long x) throws SQLException {
        wrapped.updateLong(columnIndex, x);
    }

    @Override
    public void updateFloat(int columnIndex, float x) throws SQLException {
        wrapped.updateFloat(columnIndex, x);
    }

    @Override
    public void updateDouble(int columnIndex, double x) throws SQLException {
        wrapped.updateDouble(columnIndex, x);
    }

    @Override
    public void updateBigDecimal(int columnIndex, BigDecimal x) throws SQLException {
        wrapped.updateBigDecimal(columnIndex, x);
    }

    @Override
    public void updateString(int columnIndex, String x) throws SQLException {
        wrapped.updateString(columnIndex, x);
    }

    @Override
    public void updateBytes(int columnIndex, byte[] x) throws SQLException {
        wrapped.updateBytes(columnIndex, x);
    }

    @Override
    public void updateDate(int columnIndex, Date x) throws SQLException {
        wrapped.updateDate(columnIndex, x);
    }

    @Override
    public void updateTime(int columnIndex, Time x) throws SQLException {
        wrapped.updateTime(columnIndex, x);
    }

    @Override
    public void updateTimestamp(int columnIndex, Timestamp x) throws SQLException {
        wrapped.updateTimestamp(columnIndex, x);
    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x, int length) throws SQLException {
        wrapped.updateAsciiStream(columnIndex, x, length);
    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x, int length) throws SQLException {
        wrapped.updateBinaryStream(columnIndex, x, length);
    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x, int length) throws SQLException {
        wrapped.updateCharacterStream(columnIndex, x, length);
    }

    @Override
    public void updateObject(int columnIndex, Object x, int scaleOrLength) throws SQLException {
        wrapped.updateObject(columnIndex, x, scaleOrLength);
    }

    @Override
    public void updateObject(int columnIndex, Object x) throws SQLException {
        wrapped.updateObject(columnIndex, x);
    }

    @Override
    public void updateNull(String columnLabel) throws SQLException {
        wrapped.updateNull(columnLabel);
    }

    @Override
    public void updateBoolean(String columnLabel, boolean x) throws SQLException {
        wrapped.updateBoolean(columnLabel, x);
    }

    @Override
    public void updateByte(String columnLabel, byte x) throws SQLException {
        wrapped.updateByte(columnLabel, x);
    }

    @Override
    public void updateShort(String columnLabel, short x) throws SQLException {
        wrapped.updateShort(columnLabel, x);
    }

    @Override
    public void updateInt(String columnLabel, int x) throws SQLException {
        wrapped.updateInt(columnLabel, x);
    }

    @Override
    public void updateLong(String columnLabel, long x) throws SQLException {
        wrapped.updateLong(columnLabel, x);
    }

    @Override
    public void updateFloat(String columnLabel, float x) throws SQLException {
        wrapped.updateFloat(columnLabel, x);
    }

    @Override
    public void updateDouble(String columnLabel, double x) throws SQLException {
        wrapped.updateDouble(columnLabel, x);
    }

    @Override
    public void updateBigDecimal(String columnLabel, BigDecimal x) throws SQLException {
        wrapped.updateBigDecimal(columnLabel, x);
    }

    @Override
    public void updateString(String columnLabel, String x) throws SQLException {
        wrapped.updateString(columnLabel, x);
    }

    @Override
    public void updateBytes(String columnLabel, byte[] x) throws SQLException {
        wrapped.updateBytes(columnLabel, x);
    }

    @Override
    public void updateDate(String columnLabel, Date x) throws SQLException {
        wrapped.updateDate(columnLabel, x);
    }

    @Override
    public void updateTime(String columnLabel, Time x) throws SQLException {
        wrapped.updateTime(columnLabel, x);
    }

    @Override
    public void updateTimestamp(String columnLabel, Timestamp x) throws SQLException {
        wrapped.updateTimestamp(columnLabel, x);
    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x, int length) throws SQLException {
        wrapped.updateAsciiStream(columnLabel, x, length);
    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x, int length) throws SQLException {
        wrapped.updateBinaryStream(columnLabel, x, length);
    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader, int length) throws SQLException {
        wrapped.updateCharacterStream(columnLabel, reader, length);
    }

    @Override
    public void updateObject(String columnLabel, Object x, int scaleOrLength) throws SQLException {
        wrapped.updateObject(columnLabel, x, scaleOrLength);
    }

    @Override
    public void updateObject(String columnLabel, Object x) throws SQLException {
        wrapped.updateObject(columnLabel, x);
    }

    @Override
    public void insertRow() throws SQLException {
        wrapped.insertRow();
    }

    @Override
    public void updateRow() throws SQLException {
        wrapped.updateRow();
    }

    @Override
    public void deleteRow() throws SQLException {
        wrapped.deleteRow();
    }

    @Override
    public void refreshRow() throws SQLException {
        wrapped.refreshRow();
    }

    @Override
    public void cancelRowUpdates() throws SQLException {
        wrapped.cancelRowUpdates();
    }

    @Override
    public void moveToInsertRow() throws SQLException {
        wrapped.moveToInsertRow();
    }

    @Override
    public void moveToCurrentRow() throws SQLException {
        wrapped.moveToCurrentRow();
    }

    @Override
    public Statement getStatement() {
        return statement;
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

    @Override
    public void updateRef(int columnIndex, Ref x) throws SQLException {
        wrapped.updateRef(columnIndex, x);
    }

    @Override
    public void updateRef(String columnLabel, Ref x) throws SQLException {
        wrapped.updateRef(columnLabel, x);
    }

    @Override
    public void updateBlob(int columnIndex, Blob x) throws SQLException {
        wrapped.updateBlob(columnIndex, x);
    }

    @Override
    public void updateBlob(String columnLabel, Blob x) throws SQLException {
        wrapped.updateBlob(columnLabel, x);
    }

    @Override
    public void updateClob(int columnIndex, Clob x) throws SQLException {
        wrapped.updateClob(columnIndex, x);
    }

    @Override
    public void updateClob(String columnLabel, Clob x) throws SQLException {
        wrapped.updateClob(columnLabel, x);
    }

    @Override
    public void updateArray(int columnIndex, Array x) throws SQLException {
        wrapped.updateArray(columnIndex, x);
    }

    @Override
    public void updateArray(String columnLabel, Array x) throws SQLException {
        wrapped.updateArray(columnLabel, x);
    }

    public RowId getRowId(int parameterIndex) throws SQLException {
        return wrapped.getRowId(parameterIndex);
    }

    public RowId getRowId(String parameterName) throws SQLException {
        return wrapped.getRowId(parameterName);
    }

    @Override
    public void updateRowId(int columnIndex, RowId x) throws SQLException {
        wrapped.updateRowId(columnIndex, x);
    }

    @Override
    public void updateRowId(String columnLabel, RowId x) throws SQLException {
        wrapped.updateRowId(columnLabel, x);
    }

    @Override
    public int getHoldability() throws SQLException {
        return wrapped.getHoldability();
    }

    @Override
    public boolean isClosed() throws SQLException {
        return wrapped.isClosed();
    }

    @Override
    public void updateNString(int columnIndex, String nString) throws SQLException {
        wrapped.updateNString(columnIndex, nString);
    }

    @Override
    public void updateNString(String columnLabel, String nString) throws SQLException {
        wrapped.updateNString(columnLabel, nString);
    }

    @Override
    public void updateNClob(int columnIndex, NClob nClob) throws SQLException {
        wrapped.updateNClob(columnIndex, nClob);
    }

    @Override
    public void updateNClob(String columnLabel, NClob nClob) throws SQLException {
        wrapped.updateNClob(columnLabel, nClob);
    }

    public NClob getNClob(int parameterIndex) throws SQLException {
        return wrapped.getNClob(parameterIndex);
    }

    public NClob getNClob(String parameterName) throws SQLException {
        return wrapped.getNClob(parameterName);
    }

    public SQLXML getSQLXML(int parameterIndex) throws SQLException {
        return wrapped.getSQLXML(parameterIndex);
    }

    public SQLXML getSQLXML(String parameterName) throws SQLException {
        return wrapped.getSQLXML(parameterName);
    }

    @Override
    public void updateSQLXML(int columnIndex, SQLXML xmlObject) throws SQLException {
        wrapped.updateSQLXML(columnIndex, xmlObject);
    }

    @Override
    public void updateSQLXML(String columnLabel, SQLXML xmlObject) throws SQLException {
        wrapped.updateSQLXML(columnLabel, xmlObject);
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

    @Override
    public void updateNCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
        wrapped.updateNCharacterStream(columnIndex, x, length);
    }

    @Override
    public void updateNCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
        wrapped.updateNCharacterStream(columnLabel, reader, length);
    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x, long length) throws SQLException {
        wrapped.updateAsciiStream(columnIndex, x, length);
    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x, long length) throws SQLException {
        wrapped.updateBinaryStream(columnIndex, x, length);
    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
        wrapped.updateCharacterStream(columnIndex, x, length);
    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x, long length) throws SQLException {
        wrapped.updateAsciiStream(columnLabel, x, length);
    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x, long length) throws SQLException {
        wrapped.updateBinaryStream(columnLabel, x, length);
    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
        wrapped.updateCharacterStream(columnLabel, reader, length);
    }

    @Override
    public void updateBlob(int columnIndex, InputStream inputStream, long length) throws SQLException {
        wrapped.updateBlob(columnIndex, inputStream, length);
    }

    @Override
    public void updateBlob(String columnLabel, InputStream inputStream, long length) throws SQLException {
        wrapped.updateBlob(columnLabel, inputStream, length);
    }

    @Override
    public void updateClob(int columnIndex, Reader reader, long length) throws SQLException {
        wrapped.updateClob(columnIndex, reader, length);
    }

    @Override
    public void updateClob(String columnLabel, Reader reader, long length) throws SQLException {
        wrapped.updateClob(columnLabel, reader, length);
    }

    @Override
    public void updateNClob(int columnIndex, Reader reader, long length) throws SQLException {
        wrapped.updateNClob(columnIndex, reader, length);
    }

    @Override
    public void updateNClob(String columnLabel, Reader reader, long length) throws SQLException {
        wrapped.updateNClob(columnLabel, reader, length);
    }

    @Override
    public void updateNCharacterStream(int columnIndex, Reader x) throws SQLException {
        wrapped.updateNCharacterStream(columnIndex, x);
    }

    @Override
    public void updateNCharacterStream(String columnLabel, Reader reader) throws SQLException {
        wrapped.updateNCharacterStream(columnLabel, reader);
    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x) throws SQLException {
        wrapped.updateAsciiStream(columnIndex, x);
    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x) throws SQLException {
        wrapped.updateBinaryStream(columnIndex, x);
    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x) throws SQLException {
        wrapped.updateCharacterStream(columnIndex, x);
    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x) throws SQLException {
        wrapped.updateAsciiStream(columnLabel, x);
    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x) throws SQLException {
        wrapped.updateBinaryStream(columnLabel, x);
    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader) throws SQLException {
        wrapped.updateCharacterStream(columnLabel, reader);
    }

    @Override
    public void updateBlob(int columnIndex, InputStream inputStream) throws SQLException {
        wrapped.updateBlob(columnIndex, inputStream);
    }

    @Override
    public void updateBlob(String columnLabel, InputStream inputStream) throws SQLException {
        wrapped.updateBlob(columnLabel, inputStream);
    }

    @Override
    public void updateClob(int columnIndex, Reader reader) throws SQLException {
        wrapped.updateClob(columnIndex, reader);
    }

    @Override
    public void updateClob(String columnLabel, Reader reader) throws SQLException {
        wrapped.updateClob(columnLabel, reader);
    }

    @Override
    public void updateNClob(int columnIndex, Reader reader) throws SQLException {
        wrapped.updateNClob(columnIndex, reader);
    }

    @Override
    public void updateNClob(String columnLabel, Reader reader) throws SQLException {
        wrapped.updateNClob(columnLabel, reader);
    }

    public Reader getCharacterStream(int parameterIndex) throws SQLException {
        return wrapped.getCharacterStream(parameterIndex);
    }

    public Reader getCharacterStream(String parameterName) throws SQLException {
        return wrapped.getCharacterStream(parameterName);
    }

    public <K> K getObject(int parameterIndex, Class<K> type) throws SQLException {
        return wrapped.getObject(parameterIndex, type);
    }

    public <K> K getObject(String parameterName, Class<K> type) throws SQLException {
        return wrapped.getObject(parameterName, type);
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        return wrapped.unwrap(iface);
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return wrapped.isWrapperFor(iface);
    }
}
