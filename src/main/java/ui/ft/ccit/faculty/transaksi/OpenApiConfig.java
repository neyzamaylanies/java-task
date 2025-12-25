package ui.ft.ccit.faculty.transaksi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

        /*
         * ======================================================
         * GLOBAL METADATA
         * ======================================================
         */
        @Bean
        public OpenAPI openAPI() {
                return new OpenAPI()
                                .info(new Info()
                                                .title("API Sistem Transaksi")
                                                .version("1.0.0")
                                                .description("""
                                                                Dokumentasi API internal Sistem Transaksi.

                                                                Catatan desain:
                                                                - API berbasis REST
                                                                - Pagination opsional
                                                                - Bulk operation bersifat transactional
                                                                - Swagger hanya sebagai dokumentasi manusia
                                                                """)
                                                .contact(new Contact()
                                                                .name("Muhammad Azka Ramadhan")
                                                                .email("m.azka@eng.ui.ac.id")));
        }

        /*
         * ======================================================
         * GROUP: BARANG
         * ======================================================
         */
        @Bean
        public GroupedOpenApi barangApi() {
                return GroupedOpenApi.builder()
                                .group("Barang")
                                .pathsToMatch("/api/barang/**")
                                .build();
        }

        /*
         * ======================================================
         * GROUP: PELANGGAN
         * ======================================================
         */
        @Bean
        public GroupedOpenApi pelangganApi() {
                return GroupedOpenApi.builder()
                                .group("Pelanggan")
                                .pathsToMatch("/api/pelanggan/**")
                                .build();
        }

}
