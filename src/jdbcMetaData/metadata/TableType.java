package jdbcMetaData.metadata;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NonNull;

@Data
public class TableType {

    @NonNull
    private String tableType;

    public static List<TableType> of(ResultSet rows) throws SQLException {
        List<TableType> tableTypes = new ArrayList<>();
        while (rows.next()) {
            final String tableTypeColumn = rows.getString("TABLE_TYPE");
            TableType tableType = new TableType(tableTypeColumn);
            tableTypes.add(tableType);
        }
        return tableTypes;
    }

}
