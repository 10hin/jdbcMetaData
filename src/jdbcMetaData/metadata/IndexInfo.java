package jdbcMetaData.metadata;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NonNull;

@Data
public class IndexInfo {

    private String tableCat;
    private String tableSchem;
    @NonNull
    private String tableName;
    private boolean nonUnique;
    private String indexQualifier;
    private String indexName;
    private short type;
    private short ordinalPosition;
    private String columnName;
    private String ascOrDesc;
    private long cardinality;
    private long pages;
    private String filterCondition;

    public static List<IndexInfo> of(ResultSet rows) throws SQLException {
        List<IndexInfo> indexInfos = new ArrayList<>();
        while (rows.next()) {
            final String tableName = rows.getString("TABLE_NAME");
            final IndexInfo indexInfo = new IndexInfo(tableName);
            indexInfo.setTableCat(rows.getString("TABLE_CAT"));
            indexInfo.setTableSchem(rows.getString("TABLE_SCHEM"));
            indexInfo.setNonUnique(rows.getBoolean("NON_UNIQUE"));
            indexInfo.setIndexQualifier(rows.getString("INDEX_QUALIFIER"));
            indexInfo.setIndexName(rows.getString("INDEX_NAME"));
            indexInfo.setType(rows.getShort("TYPE"));
            indexInfo.setOrdinalPosition(rows.getShort("ORDINAL_POSITION"));
            indexInfo.setColumnName(rows.getString("COLUMN_NAME"));
            indexInfo.setAscOrDesc(rows.getString("ASC_OR_DESC"));
            indexInfo.setCardinality(rows.getLong("CARDINALITY"));
            indexInfo.setPages(rows.getLong("PAGES"));
            indexInfo.setFilterCondition(rows.getString("FILTER_CONDITION"));
            indexInfos.add(indexInfo);
        }
        return indexInfos;
    }

}
