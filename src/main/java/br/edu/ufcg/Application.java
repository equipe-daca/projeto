package br.edu.ufcg;

import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;

@EnableSwagger2
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @Bean
    ServletRegistrationBean h2servletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }

    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                    .apis(basePackage("br.edu.ufcg"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Projeto DACA")
                .description("Projeto da disciplina Desenvolvimento de Aplicações Corporativas Avançadas," +
                        "período 2016.1, Universidade Federal de Campina Grande.\nClone do backend do sistema " +
                        "Dirlididi.")
                .contact(new Contact("Diego Augusto", "", "diego.moura.oliveira@ccc.ufcg.edu.br"))
                .contact(new Contact("Gervásio Júnior", "", "gervasio.junior@ccc.ufcg.edu.br"))
                .license("MIT")
                .licenseUrl("https://github.com/equipe-daca/projeto/blob/master/LICENSE")
                .version("1.0")
                .build();
    }
}
