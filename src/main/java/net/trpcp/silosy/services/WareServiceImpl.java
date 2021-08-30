package net.trpcp.silosy.services;

import net.trpcp.silosy.model.Ware;
import net.trpcp.silosy.repositories.WareRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class WareServiceImpl implements net.trpcp.silosy.services.WareService {

    WareRepository wareRepository;

    public WareServiceImpl(WareRepository wareRepository) {
        this.wareRepository = wareRepository;
    }

    @Override
    public Ware findById(Long l) {
        return wareRepository.findById(l).orElse(null);
    }

    @Override
    public Ware findByName(String s) {
        return wareRepository.findByName(s);
    }

    @Override
    public Set<Ware> findAll() {
        return (Set<Ware>) wareRepository.findAll();
    }

    @Override
    public Set<Ware> findByNameLike(String s) {
        return wareRepository.findByNameLike("%" + s + "%");
    }

    @Override
    public Ware save(Ware w) {
        return wareRepository.save(w);
    }

    @Override
    public Set<Ware> saveAll(Set<Ware> set) {
        return (Set<Ware>) wareRepository.saveAll(set);
    }

    @Override
    public void delete(Ware w) {
        wareRepository.delete(w);
    }

    @Override
    public void deleteById(Long l) {
        wareRepository.deleteById(l);
    }
}
