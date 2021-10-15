

package org.datasphere.mdm.core.dao;

import java.lang.reflect.Field;
import java.util.Properties;

import org.springframework.util.ReflectionUtils;

/**
 * todo: JavaDoc
 *
 * @author maria.chistyakova
 * @since 30.05.2019
 */
public class DaoSqlQueryMapper {

    private DaoSqlQueryMapper() {
    }

    public static void fill(Class<?> clazz,
                            Object fillerObject,
                            Properties sql) {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.getAnnotationsByType(SqlQuery.class).length != 0) {
                SqlQuery[] annotationsByType = field.getAnnotationsByType(SqlQuery.class);

                String query = sql.getProperty(annotationsByType[0].value());

                ReflectionUtils.makeAccessible(field);
                ReflectionUtils.setField(field, fillerObject, query);
                field.setAccessible(false);
            }
        }
    }
}
