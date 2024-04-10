package ch.hearc.votingservice.remote;


import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;


//@Configuration
public class AdminServiceConfiguration {

    Logger logger = LoggerFactory.getLogger(AdminServiceConfiguration.class);

    @Autowired
    private EurekaClient eurekaClient;

    @Value("${admin.service.url}")
    String adminServiceUrl;

    /**
     * Retourne une instance d'un client web permettant d'interaggir avec l'api du service admin.service
     * @return un instance de @WebClient configuré pour communiquer ave le service admin-service
     */
    @Bean
    public RestClient adminServiceClient(){
        String adminUrl = getAdminServiceUrl();
        return RestClient.create(adminUrl);
    }

    private String getAdminServiceUrl(){
        logger.info("Resolving admin service location");

        InstanceInfo adminService = eurekaClient
                .getApplication("admin-service")
                .getInstances()
                .get(0);

        String hostName = adminService.getHostName();
        int port = adminService.getPort();
        logger.info("Admin service, host: " + hostName + ", port: " + port);
        return "http://" + hostName + ":" + port;
    }
}
