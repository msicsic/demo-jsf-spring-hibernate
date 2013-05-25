package com.tentelemed.jsftest1.business;

import org.hibernate.cfg.ImprovedNamingStrategy;

public class EntityNamingStrategy extends ImprovedNamingStrategy {

    @Override
    public String classToTableName(String className) {
        return "T_" + super.tableName(className).toUpperCase();
    }

    @Override
    public String tableName(String tableName) {
        return classToTableName(tableName);
    }

    @Override
    public String propertyToColumnName(String propertyName) {
        return "C_" + super.columnName(propertyName).toUpperCase();
    }

    @Override
    public String columnName(String columnName) {
        return propertyToColumnName(columnName);
    }

    @Override
    public String foreignKeyColumnName(String propertyName, String propertyEntityName, String propertyTableName, String referencedColumnName) {
        return "F_" + super.foreignKeyColumnName(propertyName, propertyEntityName, propertyTableName, referencedColumnName).toUpperCase();
    }

    @Override
    public String joinKeyColumnName(String joinedColumn, String joinedTable) {
        return "J_" + super.joinKeyColumnName(joinedColumn, joinedTable).toUpperCase();
    }

    @Override
    public String collectionTableName(String ownerEntity, String ownerEntityTable, String associatedEntity, String associatedEntityTable, String propertyName) {
        return "T_" + super.collectionTableName(ownerEntity, ownerEntityTable, associatedEntity, associatedEntityTable, propertyName).toUpperCase();
    }
}
