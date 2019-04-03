package poc.amitk.springboot.cxf.fiql.config;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.ext.search.SearchContextProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import poc.amitk.springboot.cxf.fiql.resource.EmployeeResource;

import java.util.Arrays;

/**
 * @author amitkapps
 */
@Configuration
public class CxfConfig {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Bus bus;

    @Autowired
    EmployeeResource employeeResource;

    @Bean
    public Server rsServer() {
        logger.info("Setting up CXF Server with employee resource: {}", employeeResource);
        JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
        endpoint.setProvider(new JacksonJsonProvider());
        endpoint.setProvider(new SearchContextProvider());

//        endpoint.getInInterceptors().add(new LoggingInInterceptor());
//        endpoint.getOutInterceptors().add(new LoggingOutInterceptor());

        endpoint.setBus(bus);
        endpoint.setAddress("/");
        // Register 2 JAX-RS root resources supporting "/sayHello/{id}" and "/sayHello2/{id}" relative paths
        endpoint.setServiceBeans(Arrays.<Object>asList(employeeResource));
//        endpoint.setFeatures(Arrays.asList(new Swagger2Feature()));
        return endpoint.create();
    }
}
