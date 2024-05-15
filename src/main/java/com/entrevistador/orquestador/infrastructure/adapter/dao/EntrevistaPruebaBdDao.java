package com.entrevistador.orquestador.infrastructure.adapter.dao;

import com.entrevistador.orquestador.dominio.model.dto.IdEntrevistaDto;
import com.entrevistador.orquestador.dominio.model.dto.SoloPerfilDto;
import com.entrevistador.orquestador.dominio.model.dto.SoloPerfilImp;
import com.entrevistador.orquestador.dominio.port.EntrevistaPruebaDao;
import com.entrevistador.orquestador.infrastructure.adapter.repository.EntrevistaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class EntrevistaPruebaBdDao implements EntrevistaPruebaDao {

    private final EntrevistaRepository entrevistaRepository;

    @Override
    public Flux<SoloPerfilImp> getPerfil(int limit) {
        return this.entrevistaRepository.obtenerPerfilEmpresa(limit);
    }

    @Override
    public Mono<IdEntrevistaDto> getIdEntrevistaByPerfil(String perfil) {
        return this.entrevistaRepository.obtenerIdEntrevistaPorPerfil(perfil);
    }
}