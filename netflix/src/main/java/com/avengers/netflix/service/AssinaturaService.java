package com.avengers.netflix.service;

import com.avengers.netflix.model.Assinatura;
import com.avengers.netflix.model.dto.AssinaturaDTO;
import com.avengers.netflix.repository.AssinaturaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssinaturaService {
    private final AssinaturaRepository assinaturaRepository;

    public AssinaturaService(AssinaturaRepository assinaturaRepository) {
        this.assinaturaRepository = assinaturaRepository;
    }

    public Assinatura cadastrar(AssinaturaDTO assinaturaDTO){
        Assinatura assinatura = new Assinatura();
        assinatura.setNome(assinaturaDTO.nome());
        assinatura.setValor(assinaturaDTO.valor());
        assinatura.setDescricao(assinaturaDTO.descricao());

        return assinaturaRepository.save(assinatura);

    }

    public List<AssinaturaDTO> listarAssinaturas() {
        List<Assinatura> assinaturas = assinaturaRepository.findAll();
        return assinaturas.stream()
                .map(assinatura -> new AssinaturaDTO(
                        assinatura.getNome(),
                        assinatura.getValor(),
                        assinatura.getDescricao()
                ))
                .toList();
    }

    public AssinaturaDTO obterAssinaturaPorId(Long id) {
        Assinatura assinatura = assinaturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assinatura não encontrada"));
        return new AssinaturaDTO(
                assinatura.getNome(),
                assinatura.getValor(),
                assinatura.getDescricao()
        );
    }

    public Assinatura findByNome(String assinatura) {
        return assinaturaRepository.findByNome(assinatura);
    }
}
