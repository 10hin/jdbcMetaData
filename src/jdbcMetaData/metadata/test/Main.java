package jdbcMetaData.metadata.test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.util.List;
import java.util.Objects;

import jdbcMetaData.metadata.Catalog;
import jdbcMetaData.metadata.Column;
import jdbcMetaData.metadata.CrossReference;
import jdbcMetaData.metadata.ExportedKey;
import jdbcMetaData.metadata.ImportedKey;
import jdbcMetaData.metadata.IndexInfo;
import jdbcMetaData.metadata.PrimaryKey;
import jdbcMetaData.metadata.Schema;
import jdbcMetaData.metadata.Table;
import jdbcMetaData.metadata.TableType;

public class Main {

    public static void main(String[] args) {
        try {
            printMetadatas("jdbc:mysql://localhost:3306/metadata_test", "com.mysql.jdbc.Driver", "root", "johann");
        } catch (Exception e) {
            throw new InternalError(e);
        }
        try {
            printMetadatas("jdbc:postgresql://localhost:5432/metadata_test", "org.postgresql.Driver", "postgres",
                    "postgres");
        } catch (Exception e) {
            throw new InternalError(e);
        }
    }

    public static void printMetadatas(String url, String driverClassName, String username, String password)
            throws Exception {
        Class.forName(driverClassName);
        Connection con = DriverManager.getConnection(url, username, password);
        DatabaseMetaData metaData = con.getMetaData();
        List<Catalog> catalogs = Catalog.of(metaData.getCatalogs());
        System.out.println(catalogs);
        List<Schema> schemas = Schema.of(metaData.getSchemas());
        System.out.println(schemas);
        List<TableType> tableTypes = TableType.of(metaData.getTableTypes());
        System.out.println(tableTypes);
        List<Table> tables = Table.of(metaData.getTables(null, "%", "%", tableTypes.stream().map(TableType::getTableType).toArray((n) -> new String[n])));
        System.out.println(tables);
        for (Table t : tables) {
            List<Column> columns = Column
                    .of(metaData.getColumns(t.getTableCat(), t.getTableSchem(), t.getTableName(), "%"));
            System.out.println(columns);
            List<PrimaryKey> primaryKeys = PrimaryKey
                    .of(metaData.getPrimaryKeys(t.getTableCat(), t.getTableSchem(), t.getTableName()));
            System.out.println(primaryKeys);
            List<ImportedKey> importedKeys = ImportedKey
                    .of(metaData.getImportedKeys(t.getTableCat(), t.getTableSchem(), t.getTableName()));
            System.out.println(importedKeys);
            List<ExportedKey> exportedKeys = ExportedKey
                    .of(metaData.getExportedKeys(t.getTableCat(), t.getTableSchem(), t.getTableName()));
            System.out.println(exportedKeys);
            List<IndexInfo> indexInfos = IndexInfo
                    .of(metaData.getIndexInfo(t.getTableCat(), t.getTableSchem(), t.getTableName(), false, true));
            System.out.println(indexInfos);
        }
        for (Table t1 : tables) {
            for (Table t2 : tables) {
                if (Objects.equals(t1, t2)) {
                    continue;
                }
                List<CrossReference> crossReferences = CrossReference
                        .of(metaData.getCrossReference(t1.getTableCat(), t1.getTableSchem(), t1.getTableName(), t2.getTableCat(), t2.getTableSchem(), t2.getTableName()));
                System.out.println(crossReferences);
            }
        }
    }

}
