package cat.itacademy.barcelonactiva.salgadosalichs.josep.s05.t01.n01;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info=@Info(
				title="S05T01N01E01", version = "1.0.1",
				contact = @Contact(
						name = "Josep Salgado", email = "example@example.com", url = "https://example.cat")
		),
		tags={@Tag(name="CHANGE PAGE", description="All EP to change page."),
				@Tag(name="CRUD: ADD", description="All ADDs of Sucursal controller."),
				@Tag(name = "CRUD: DELETE", description = "All DELETEs of Sucursal controller."),
				@Tag(name="CRUD: GET", description="All GETs of Sucursal controller."),
				@Tag(name="CRUD: PUT", description="All PUTs of Sucursal controller.")}
)
public class S05T01N01SalgadoSalichsJosepApplication {

	public static void main(String[] args) {
		SpringApplication.run(S05T01N01SalgadoSalichsJosepApplication.class, args);
	}

}
