package csw;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class Boot
{

   public static void main(String[] args)
   {
      SpringApplication.run(Boot.class, args);
   }
   
	
   /**
    * method to create ModelMapper Bean over the API.
    * @return
    */
   @Bean
   public ModelMapper modelMapper() {
	   return new ModelMapper();
   }
   
   /**
    * Bean to use Logger over the API.
    * @return
    */
   @Bean
	public Logger configureLogger() {
		return LoggerFactory.getLogger(Boot.class);
	}
   
//   @Bean
//   public WebMvcConfigurer corsConfigurer() {
//       return new WebMvcConfigurerAdapter() {
//           @Override
//           public void addCorsMappings(CorsRegistry registry) {
//               registry.addMapping("/**")
//                       .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
//           }
//       };
//   }

}
