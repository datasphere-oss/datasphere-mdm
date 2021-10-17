
package org.datasphere.mdm.data.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.datasphere.mdm.system.configuration.ModuleConfiguration;
import org.datasphere.mdm.system.util.DataSourceUtils;

/**
 * @author Alexander Malyshev
 */
@Configuration
@EnableTransactionManagement
public class DataConfiguration extends ModuleConfiguration {
    /**
     * Id.
     */
    public static final ConfigurationId ID = () -> "DATA_CONFIGURATION";
    /**
     * Constructor.
     */
    public DataConfiguration() {
        super();
    }

    @Override
    public ConfigurationId getId() {
        return ID;
    }

    @Bean("data-sql")
    public PropertiesFactoryBean dataSql() {
        final PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/db/data-sql.xml"));
        return propertiesFactoryBean;
    }

    @Bean("records-sql")
    public PropertiesFactoryBean recordsSql() {
        final PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/db/records-sql.xml"));
        return propertiesFactoryBean;
    }

    @Bean("relations-sql")
    public PropertiesFactoryBean relationsSql() {
        final PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/db/relations-sql.xml"));
        return propertiesFactoryBean;
    }

    @Bean("job-support-sql")
    public PropertiesFactoryBean jobSupportSql() {
        final PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/db/job-support-sql.xml"));
        return propertiesFactoryBean;
    }

    @Bean("user-favorite-etalons-sql")
    public PropertiesFactoryBean userFavoriteEtalonsSql() {
        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        bean.setLocation(new ClassPathResource("/db/user-favorite-etalons-sql.xml"));
        return bean;
    }

    @Bean(name = "storageDataSource")
    public DataSource storageDataSource() {
    	Properties properties = getPropertiesByPrefix(DataConfigurationConstants.DATA_DATASOURCE_PROPERTIES_PREFIX, true);
    	return DataSourceUtils.newPoolingXADataSource(properties);
    }
}
