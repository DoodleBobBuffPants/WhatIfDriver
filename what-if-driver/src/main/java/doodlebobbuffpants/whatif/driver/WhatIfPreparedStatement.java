package doodlebobbuffpants.whatif.driver;

import doodlebobbuffpants.whatif.driver.exception.WhatIfParameterReadingException;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.sql.*;
import java.util.*;

public class WhatIfPreparedStatement extends WhatIfStatement implements PreparedStatement {
    private final String sql;
    private final Map<Integer, String> parameters;

    public WhatIfPreparedStatement(WhatIfWriter writer, WhatIfConnection connection, String sql) throws SQLException {
        super(writer, connection);
        this.sql = sql;
        this.parameters = new HashMap<>();
    }

    @Override
    public ResultSet executeQuery() {
        return executeQuery(getPreparedSql(sql, parameters));
    }

    @Override
    public int executeUpdate() {
        return executeUpdate(getPreparedSql(sql, parameters));
    }

    @Override
    public void setNull(int parameterIndex, int sqlType) {
        parameters.put(parameterIndex, String.valueOf((String) null));
    }

    @Override
    public void setBoolean(int parameterIndex, boolean x) {
        parameters.put(parameterIndex, String.valueOf(x));
    }

    @Override
    public void setByte(int parameterIndex, byte x) {
        parameters.put(parameterIndex, String.valueOf(x));
    }

    @Override
    public void setShort(int parameterIndex, short x) {
        parameters.put(parameterIndex, String.valueOf(x));
    }

    @Override
    public void setInt(int parameterIndex, int x) {
        parameters.put(parameterIndex, String.valueOf(x));
    }

    @Override
    public void setLong(int parameterIndex, long x) {
        parameters.put(parameterIndex, String.valueOf(x));
    }

    @Override
    public void setFloat(int parameterIndex, float x) {
        parameters.put(parameterIndex, String.valueOf(x));
    }

    @Override
    public void setDouble(int parameterIndex, double x) {
        parameters.put(parameterIndex, String.valueOf(x));
    }

    @Override
    public void setBigDecimal(int parameterIndex, BigDecimal x) {
        setObject(parameterIndex, x);
    }

    @Override
    public void setString(int parameterIndex, String x) {
        setObject(parameterIndex, x);
    }

    @Override
    public void setBytes(int parameterIndex, byte[] x) {
        setObject(parameterIndex, Arrays.toString(x));
    }

    @Override
    public void setDate(int parameterIndex, Date x) {
        setObject(parameterIndex, x);
    }

    @Override
    public void setTime(int parameterIndex, Time x) {
        setObject(parameterIndex, x);
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x) {
        setObject(parameterIndex, x);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, int length) {
        try {
            setString(parameterIndex, new String(x.readNBytes(length)));
        } catch (IOException e) {
            throw new WhatIfParameterReadingException(e);
        }
    }

    @Override
    public void setUnicodeStream(int parameterIndex, InputStream x, int length) {
        try {
            setString(parameterIndex, new String(x.readNBytes(length), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new WhatIfParameterReadingException(e);
        }
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, int length) {
        try {
            setString(parameterIndex, new String(x.readNBytes(length)));
        } catch (IOException e) {
            throw new WhatIfParameterReadingException(e);
        }
    }

    @Override
    public void clearParameters() {
        parameters.clear();
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType) {
        setObject(parameterIndex, x);
    }

    @Override
    public void setObject(int parameterIndex, Object x) {
        parameters.put(parameterIndex, "\"" + x + "\"");
    }

    @Override
    public boolean execute() {
        executeQuery();
        return false;
    }

    @Override
    public void addBatch() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, int length) {
        char[] chars = new char[length];
        int total = 0;
        int read;
        try {
            while ((read = reader.read(chars, total, length)) > -1 && total < length) {
                total += read;
            }
        } catch (IOException e) {
            throw new WhatIfParameterReadingException(e);
        }
        setObject(parameterIndex, Arrays.toString(chars));
    }

    @Override
    public void setRef(int parameterIndex, Ref x) {
        setObject(parameterIndex, x);
    }

    @Override
    public void setBlob(int parameterIndex, Blob x) {
        setObject(parameterIndex, x);
    }

    @Override
    public void setClob(int parameterIndex, Clob x) {
        setObject(parameterIndex, x);
    }

    @Override
    public void setArray(int parameterIndex, Array x) {
        setObject(parameterIndex, x);
    }

    @Override
    public ResultSetMetaData getMetaData() {
        return null;
    }

    @Override
    public void setDate(int parameterIndex, Date x, Calendar cal) {
        setDate(parameterIndex, x);
    }

    @Override
    public void setTime(int parameterIndex, Time x, Calendar cal) {
        setTime(parameterIndex, x);
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) {
        setTimestamp(parameterIndex, x);
    }

    @Override
    public void setNull(int parameterIndex, int sqlType, String typeName) {
        setNull(parameterIndex, sqlType);
    }

    @Override
    public void setURL(int parameterIndex, URL x) {
        setObject(parameterIndex, x);
    }

    @Override
    public ParameterMetaData getParameterMetaData() {
        return null;
    }

    @Override
    public void setRowId(int parameterIndex, RowId x) {
        setObject(parameterIndex, x);
    }

    @Override
    public void setNString(int parameterIndex, String value) {
        setString(parameterIndex, value);
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value, long length) {
        setCharacterStream(parameterIndex, value, (int) length);
    }

    @Override
    public void setNClob(int parameterIndex, NClob value) {
        setObject(parameterIndex, value);
    }

    @Override
    public void setClob(int parameterIndex, Reader reader, long length) {
        setNCharacterStream(parameterIndex, reader, length);
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream, long length) {
        setBinaryStream(parameterIndex, inputStream, (int) length);
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader, long length) {
        setNCharacterStream(parameterIndex, reader, length);
    }

    @Override
    public void setSQLXML(int parameterIndex, SQLXML xmlObject) {
        setObject(parameterIndex, xmlObject);
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) {
        setObject(parameterIndex, x);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, long length) {
        setAsciiStream(parameterIndex, x, (int) length);
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, long length) {
        setBinaryStream(parameterIndex, x, (int) length);
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, long length) {
        setCharacterStream(parameterIndex, reader, (int) length);
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x) {
        try {
            setBytes(parameterIndex, x.readAllBytes());
        } catch (IOException e) {
            throw new WhatIfParameterReadingException(e);
        }
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x) {
        try {
            setBytes(parameterIndex, x.readAllBytes());
        } catch (IOException e) {
            throw new WhatIfParameterReadingException(e);
        }
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader) {
        setCharacterStream(parameterIndex, reader, Integer.MAX_VALUE);
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value) {
        setNCharacterStream(parameterIndex, value, Long.MAX_VALUE);
    }

    @Override
    public void setClob(int parameterIndex, Reader reader) {
        setClob(parameterIndex, reader, Long.MAX_VALUE);
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream) {
        setBlob(parameterIndex, inputStream, Long.MAX_VALUE);
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader) {
        setNClob(parameterIndex, reader, Long.MAX_VALUE);
    }

    private static String getPreparedSql(String sql, Map<Integer, String> parameters) {
        sql += '$';
        String[] sqlParts = sql.split("\\?");
        StringBuilder result = new StringBuilder(sqlParts[0]);
        for (int i = 1; i <= sqlParts.length - 1; i++) {
            result.append(parameters.getOrDefault(i, "?")).append(sqlParts[i]);
        }
        return result.deleteCharAt(result.length() - 1).toString();
    }
}
