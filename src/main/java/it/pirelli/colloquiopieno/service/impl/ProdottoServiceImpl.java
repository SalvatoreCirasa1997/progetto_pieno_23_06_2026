package it.pirelli.colloquiopieno.service.impl;

import it.pirelli.colloquiopieno.dto.ProdottoRequestDTO;
import it.pirelli.colloquiopieno.dto.ProdottoResponseDTO;
import it.pirelli.colloquiopieno.entity.Prodotto;
import it.pirelli.colloquiopieno.exception.DuplicateResourceException;
import it.pirelli.colloquiopieno.exception.ResourceNotFoundException;
import it.pirelli.colloquiopieno.mapper.ProdottoMapper;
import it.pirelli.colloquiopieno.repository.ProdottoRepository;
import it.pirelli.colloquiopieno.service.ProdottoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProdottoServiceImpl implements ProdottoService {

    private final ProdottoRepository prodottoRepository;
    private final ProdottoMapper prodottoMapper;

    @Override
    public List<ProdottoResponseDTO> getAll() {

        log.info("Avvio chiamata findAll() al repository");
        List<Prodotto> list = prodottoRepository.findAll();
        log.info("Fine chiamata findAll() al repository");

        return prodottoMapper.toDtoList(list);
    }

    @Override
    public ProdottoResponseDTO getById(Long id) {
        Prodotto prodotto = prodottoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("prodotto", id));

        return prodottoMapper.toDto(prodotto);
    }

    @Override
    @Transactional
    public ProdottoResponseDTO insert(ProdottoRequestDTO prodottoRequestDTO) {

        if(prodottoRepository.existsByNome(prodottoRequestDTO.getNome())) {
            throw new DuplicateResourceException("prodotto", "nome", prodottoRequestDTO.getNome());
        }

        log.info("Avvio mappatura da ProdottoRequestDTO a Prodotto");
        Prodotto prodotto = prodottoMapper.toEntity(prodottoRequestDTO);
        log.info("Fine mappatura da ProdottoRequestDTO a Prodotto");

        log.info("Avvio chiamata save() al repository");
        prodotto = prodottoRepository.save(prodotto);
        log.info("Fine chiamata save() al repository");

        log.info("Avvio mappatura da Prodotto a ProdottoResponseDTO");
        return prodottoMapper.toDto(prodotto);
    }

    @Override
    public ProdottoResponseDTO update(Long id, ProdottoRequestDTO prodottoRequestDTO) {

        Prodotto existingProdotto = prodottoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("prodotto", id));

        if(!existingProdotto.getNome().equals(prodottoRequestDTO.getNome())){
            if(prodottoRepository.existsByNomeAndIdNot(prodottoRequestDTO.getNome(), id)){
                throw new DuplicateResourceException("prodotto", "nome", prodottoRequestDTO.getNome());
            }
        }

        prodottoMapper.updateEntity(prodottoRequestDTO, existingProdotto);
        existingProdotto = prodottoRepository.save(existingProdotto);

        return prodottoMapper.toDto(existingProdotto);
    }

    @Override
    public void delete(Long id) {

        Prodotto existingProdotto = prodottoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("prodotto", id));

        prodottoRepository.delete(existingProdotto);
    }
}
