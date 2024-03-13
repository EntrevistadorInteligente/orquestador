package com.entrevistador.orquestador.infrastructure.beanconfiguration;


<<<<<<< HEAD
import com.entrevistador.orquestador.dominio.service.ActualizarEstadoProcesoEntrevistaService;
import com.entrevistador.orquestador.dominio.service.ActualizarInformacionEntrevistaService;
import com.entrevistador.orquestador.dominio.service.CrearEntrevistaAlternativaService;
import com.entrevistador.orquestador.dominio.service.CrearEntrevistaService;
import com.entrevistador.orquestador.dominio.service.NotificarFrontEntrevistaFallidaService;
import com.entrevistador.orquestador.dominio.service.SolicitudPreparacionEntrevistaService;
import com.entrevistador.orquestador.dominio.service.ValidadorEventosSimultaneosService;
import com.entrevistador.orquestador.dominio.service.ValidadorPdfService;
=======
import com.entrevistador.orquestador.dominio.port.EntrevistaDao;
import com.entrevistador.orquestador.dominio.service.*;
>>>>>>> 2f0718390945761988812f01e5b66f4d0fc71e9e
import com.entrevistador.orquestador.infrastructure.adapter.repository.ProcesoEntrevistaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesBeanConfiguration {

    @Bean
    public ActualizarEstadoProcesoEntrevistaService actualizarEstadoProvesoEntrevistaService(ProcesoEntrevistaRepository procesoEntrevistaRepository) {
        return new ActualizarEstadoProcesoEntrevistaService(procesoEntrevistaRepository);
    }

    @Bean
    public ActualizarInformacionEntrevistaService actualizarInformacionEntrevistaService() {
        return new ActualizarInformacionEntrevistaService();
    }

    @Bean
    public CrearEntrevistaAlternativaService crearEntrevistaAlternativaService(NotificarFrontEntrevistaFallidaService notificarFrontEntrevistaFallidaService,
                                                                               ProcesoEntrevistaRepository procesoEntrevistaRepository) {
        return new CrearEntrevistaAlternativaService(notificarFrontEntrevistaFallidaService, procesoEntrevistaRepository);
    }

    @Bean
    public CrearEntrevistaService crearEntrevistaService(EntrevistaDao entrevistaDao) {
        return new CrearEntrevistaService(entrevistaDao);
    }

    @Bean
    public NotificarFrontEntrevistaFallidaService notificarFrontEntrevistaFallidaService() {
        return new NotificarFrontEntrevistaFallidaService();
    }

    @Bean
    public SolicitudPreparacionEntrevistaService solicitudPreparacionEntrevistaService() {
        return new SolicitudPreparacionEntrevistaService();
    }

    @Bean
    public ValidadorEventosSimultaneosService validadorEventosSimultaneosService() {
        return new ValidadorEventosSimultaneosService();
    }

    @Bean
    public ValidadorPdfService validadorPdfService() {
        return new ValidadorPdfService();
    }


}
