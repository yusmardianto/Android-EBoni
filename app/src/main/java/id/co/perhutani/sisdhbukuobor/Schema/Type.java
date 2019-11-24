package id.co.perhutani.sisdhbukuobor.Schema;


/**
 * Created by hamdan on 13/09/17.
 */

public class Type {
    public static final String VARCHAR_NULLABLE_100 = "VARCHAR(100) NULL";
    public static final String INTEGER_NULLABLE = "INTEGER NULL";
    public static final String VARCHAR_225 = "VARCHAR(225)";
    public static final String BIG_INT = "bigint";
    public static final String BOOLEAN = "BOOLEAN";
    public static final String CREATED_AT_CURRENT_DATE = "CREATED_AT DATETIME NULL DEFAULT (datetime('now','localtime'))";
    public static final String UPDATED_AT_CURRENT_DATE = "UPDATED_AT DATETIME NULL DEFAULT (datetime('now','localtime'))";
    public static final String DOUBLE = "DOUBLE";
    public static final String DECIMAL = "DECIMAL";
    public static final String INTEGER = "INTEGER";
    public static final String DATE = "DATE";

}
