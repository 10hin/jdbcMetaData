package jdbcMetaData.metadata;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NonNull;

@Data
public class Column {

    private String tableCat;
    private String tableSchem;
    @NonNull
    private String tableName;
    @NonNull
    private String columnName;
    private int dataType;
    @NonNull
    private String typeName;
    private int columnSize;
    private int decimalDigits;
    private int numPrecRadix;
    private int nullable;
    private String remarks;
    private String columnDef;
    private int charOctetLength;
    private int ordinalPosition;
    @NonNull
    private String isNullable;
    private String scopeCatalog;
    private String scopeSchema;
    private String scopeTable;
    private short sourceDataType;
    @NonNull
    private String isAutoincrement;
    @NonNull
    private String isGeneratedcolumn;

    public static List<Column> of(ResultSet rows) throws SQLException {
        List<Column> columns = new ArrayList<>();
        while (rows.next()) {
            final String tableName = rows.getString("TABLE_NAME");
            final String columnName = rows.getString("COLUMN_NAME");
            final String typeName = rows.getString("TYPE_NAME");
            final String isNullable = rows.getString("IS_NULLABLE");
            final String isAutoincrement = rows.getString("IS_AUTOINCREMENT");
            final String isGeneratedcolumn = rows.getString("IS_GENERATEDCOLUMN");
            Column column = new Column(tableName, columnName, typeName, isNullable, isAutoincrement, isGeneratedcolumn);
            column.setTableCat(rows.getString("TABLE_CAT"));
            column.setTableSchem(rows.getString("TABLE_SCHEM"));
            column.setDataType(rows.getInt("DATA_TYPE"));
            column.setColumnSize(rows.getInt("COLUMN_SIZE"));
            column.setDecimalDigits(rows.getInt("DECIMAL_DIGITS"));
            column.setNumPrecRadix(rows.getInt("NUM_PREC_RADIX"));
            column.setNullable(rows.getInt("NULLABLE"));
            column.setRemarks(rows.getString("REMARKS"));
            column.setColumnDef(rows.getString("COLUMN_DEF"));
            column.setCharOctetLength(rows.getInt("CHAR_OCTET_LENGTH"));
            column.setOrdinalPosition(rows.getInt("ORDINAL_POSITION"));
            column.setScopeCatalog(rows.getString("SCOPE_CATALOG"));
            column.setScopeSchema(rows.getString("SCOPE_SCHEMA"));
            column.setSourceDataType(rows.getShort("SOURCE_DATA_TYPE"));
            columns.add(column);
        }
        return columns;
    }

}
