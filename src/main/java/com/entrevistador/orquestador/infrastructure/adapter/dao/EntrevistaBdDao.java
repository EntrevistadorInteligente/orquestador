package com.entrevistador.orquestador.infrastructure.adapter.dao;

import com.entrevistador.orquestador.dominio.excepciones.IdNoEncontradoException;
import com.entrevistador.orquestador.dominio.model.Entrevista;
import com.entrevistador.orquestador.dominio.model.dto.EstadoEntrevistaDto;
import com.entrevistador.orquestador.dominio.model.dto.FormularioDto;
import com.entrevistador.orquestador.dominio.model.dto.RagsIdsDto;
import com.entrevistador.orquestador.dominio.model.enums.EstadoEntrevistaEnum;
import com.entrevistador.orquestador.dominio.port.EntrevistaDao;
import com.entrevistador.orquestador.infrastructure.adapter.entity.EntrevistaEntity;
import com.entrevistador.orquestador.infrastructure.adapter.repository.EntrevistaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class EntrevistaBdDao implements EntrevistaDao {
    public static final String ID_DE_ESTADO_NO_ENCONTRADO_ID = "Id de estado no encontrado. ID: %s";
    private final EntrevistaRepository entrevistaRepository;

    @Override
    public Mono<String> crearEntrevistaBase(String idHojaDeVidaRag, String username, FormularioDto formulario) {
        return this.entrevistaRepository.save(EntrevistaEntity.builder()
                        .idHojaDeVidaRag(idHojaDeVidaRag)
                        .username(username)
                        .empresa(formulario.getEmpresa())
                        .pais(formulario.getPais())
                        .seniorityEmpresa(formulario.getSeniority())
                        .perfilEmpresa(formulario.getPerfil())
                        .descripcionVacante(formulario.getDescripcionVacante())
                        .estadoEntrevista(EstadoEntrevistaEnum.IC.name())
                        .build())
                .map(entrevista ->
                        entrevista.getUuid());
    }

    @Override
    public Mono<Void> actualizarEntrevista(Entrevista entrevista) {
        return this.entrevistaRepository.findById(entrevista.getUuid())
                .switchIfEmpty(Mono.error(new IdNoEncontradoException(String.format(ID_DE_ESTADO_NO_ENCONTRADO_ID, entrevista.getUuid()))))
                .flatMap(entrevistaEntity ->
                        entrevistaRepository.save(EntrevistaEntity.builder()
                                            .uuid(entrevistaEntity.getUuid())
                                            .idHojaDeVidaRag(entrevistaEntity.getIdHojaDeVidaRag())
                                            .idInformacionEmpresaRag(entrevista.getInformacionEmpresaDto().getIdInformacionEmpresaRag())
                                            .empresa(entrevista.getInformacionEmpresaDto().getEmpresa())
                                            .perfilEmpresa(entrevista.getInformacionEmpresaDto().getPerfil())
                                            .seniorityEmpresa(entrevista.getInformacionEmpresaDto().getSeniority())
                                            .pais(entrevista.getInformacionEmpresaDto().getPais())
                                            .hojaDeVidaValida(entrevistaEntity.isHojaDeVidaValida())
                                            .estadoEntrevista(entrevistaEntity.getEstadoEntrevista())
                                            .username(entrevistaEntity.getUsername())
                                            .build()).then());

    }

    @Override
    public Mono<RagsIdsDto> consultarRagsId(String idEntrevista) {
        return this.entrevistaRepository.obtenerRagsYEstadoEntrevistaPorId(idEntrevista);
    }

    @Override
    public Mono<Void> actualizarEstadoHojaDeVida(String idEntrevista, boolean esEntrevistaValida) {
        return this.entrevistaRepository.findById(idEntrevista)
                .flatMap(entrevista -> {
                    entrevista.setHojaDeVidaValida(esEntrevistaValida);
                    return entrevistaRepository.save(entrevista);
                })
                .then();
    }


    @Override
    public Mono<Void> actualizarIdInformacionEmpresaRag(String idEntrevista, String idInformacionEmpresaRag) {
        return this.entrevistaRepository.findById(idEntrevista)
                .flatMap(entrevista -> {
                    entrevista.setIdInformacionEmpresaRag(idInformacionEmpresaRag);
                    return entrevistaRepository.save(entrevista);
                })
                .then();
    }

    @Override
    public Mono<EstadoEntrevistaDto> obtenerEstadoEntrevistaPorUsuario(String username) {
        return this.entrevistaRepository.
                obtenerEntrevistaEnProcesoPorUsuario(username).map( entrevista -> new EstadoEntrevistaDto(entrevista.getUuid(),
                entrevista.getEstadoEntrevista()));
    }

    @Override
    public Mono<EstadoEntrevistaDto> obtenerEstadoEntrevistaPorId(String id) {
       return this.entrevistaRepository.findById(id)
               .map( entrevista -> new EstadoEntrevistaDto(entrevista.getUuid(),
                       entrevista.getEstadoEntrevista()));
    }

    @Override
    public Mono<Void> actualizarEstadoEntrevista(String idEntrevista, EstadoEntrevistaEnum estadoEntrevistaEnum) {
        return this.entrevistaRepository.actualizarEstadoEntrevistaPorId(idEntrevista, estadoEntrevistaEnum.name());
    }

}
