package net.trpcp.silosy.services;

import lombok.extern.slf4j.Slf4j;
import net.trpcp.silosy.model.Silo;
import net.trpcp.silosy.repositories.SiloRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class SiloServiceImpl implements SiloService{

    SiloRepository siloRepository;

    public SiloServiceImpl(SiloRepository siloRepository) {
        this.siloRepository = siloRepository;
    }

    public Silo findByName(String s) {
        return siloRepository.findByName(s);
    }

    public Set<Silo> findByCapacity(Integer i) {
        return siloRepository.findByCapacity(i);
    }

    public <S extends Silo> S save(S s) {
        return siloRepository.save(s);
    }

    public Set<Silo> saveAll(Set<Silo> silos) {
        return (Set<Silo>) siloRepository.saveAll(silos);
    }

    public Optional<Silo> findById(Long aLong) {
        return siloRepository.findById(aLong);
    }

    public boolean existsById(Long aLong) {
        return siloRepository.existsById(aLong);
    }

    public Set<Silo> findAll() {
        return (Set<Silo>) siloRepository.findAll();
    }

    public Set<Silo> findAllById(Iterable<Long> iterable) {
        return (Set<Silo>) siloRepository.findAllById(iterable);
    }

    public long count() {
        return siloRepository.count();
    }

    public void deleteById(Long aLong) {
        siloRepository.deleteById(aLong);
    }

    public void delete(Silo silo) {
        siloRepository.delete(silo);
    }

    public void deleteAllById(Iterable<? extends Long> iterable) {
        siloRepository.deleteAllById(iterable);
    }

    public void deleteAll(Iterable<? extends Silo> iterable) {
        siloRepository.deleteAll(iterable);
    }

    public void deleteAll() {
        siloRepository.deleteAll();
    }

    public Set<Silo> findByNameLike(String s) {
        return siloRepository.findByNameLike(s);
    }
}
