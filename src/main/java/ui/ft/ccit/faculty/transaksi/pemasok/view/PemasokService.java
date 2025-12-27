package ui.ft.ccit.faculty.transaksi.pemasok.view;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ui.ft.ccit.faculty.transaksi.DataAlreadyExistsException;
import ui.ft.ccit.faculty.transaksi.DataNotFoundException;
import ui.ft.ccit.faculty.transaksi.pemasok.model.Pemasok;
import ui.ft.ccit.faculty.transaksi.pemasok.model.PemasokRepository;

import java.util.List;

@Service
@Transactional
public class PemasokService {

    private final PemasokRepository repository;

    public PemasokService(PemasokRepository repository) {
        this.repository = repository;
    }

    public List<Pemasok> getAll() { return repository.findAll(); }

    public List<Pemasok> getAllWithPagination(int page, int size) {
        return repository.findAll(PageRequest.of(page, size)).getContent();
    }

    public Pemasok getById(String id) {
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException("Pemasok", id));
    }

    public List<Pemasok> searchByNama(String keyword) {
        return repository.findByNamaPemasokContainingIgnoreCase(keyword);
    }

    // CREATE SINGLE
    public Pemasok save(Pemasok p) {
        if (p.getIdPemasok() == null || p.getIdPemasok().isBlank()) {
            throw new IllegalArgumentException("ID Pemasok wajib diisi");
        }
        if (repository.existsById(p.getIdPemasok())) {
            throw new DataAlreadyExistsException("Pemasok", p.getIdPemasok());
        }
        return repository.save(p);
    }

    // CREATE BULK
    public List<Pemasok> saveBulk(List<Pemasok> listPemasok) {
        for (Pemasok p : listPemasok) {
            if (p.getIdPemasok() == null || p.getIdPemasok().isBlank()) {
                throw new IllegalArgumentException("ID Pemasok wajib diisi");
            }
            if (repository.existsById(p.getIdPemasok())) {
                throw new DataAlreadyExistsException("Pemasok", p.getIdPemasok());
            }
        }
        return repository.saveAll(listPemasok);
    }

    // UPDATE
    public Pemasok update(String id, Pemasok updated) {
        Pemasok existing = getById(id);
        existing.setNamaPemasok(updated.getNamaPemasok());
        existing.setAlamat(updated.getAlamat());
        existing.setTelepon(updated.getTelepon());
        existing.setEmail(updated.getEmail());
        return repository.save(existing);
    }

    // DELETE SINGLE
    public void delete(String id) {
        if (!repository.existsById(id)) throw new DataNotFoundException("Pemasok", id);
        repository.deleteById(id);
    }

    // DELETE BULK
    public void deleteBulk(List<String> ids) {
        List<Pemasok> existing = repository.findAllById(ids);
        if (existing.size() != ids.size()) {
            throw new DataNotFoundException("Sebagian Pemasok", "bulk");
        }
        repository.deleteAllById(ids);
    }
}