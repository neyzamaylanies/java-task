package ui.ft.ccit.faculty.transaksi.jenisbarang.view;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ui.ft.ccit.faculty.transaksi.DataNotFoundException;
import ui.ft.ccit.faculty.transaksi.jenisbarang.model.JenisBarang;
import ui.ft.ccit.faculty.transaksi.jenisbarang.model.JenisBarangRepository;

import java.util.List;

@Service
@Transactional
public class JenisBarangService {

    private final JenisBarangRepository repository;

    public JenisBarangService(JenisBarangRepository repository) {
        this.repository = repository;
    }

    public List<JenisBarang> getAll() { return repository.findAll(); }

    public List<JenisBarang> getAllWithPagination(int page, int size) {
        return repository.findAll(PageRequest.of(page, size)).getContent();
    }

    public JenisBarang getById(Byte id) {
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException("JenisBarang", id.toString()));
    }

    public List<JenisBarang> searchByNama(String keyword) {
        return repository.findByNamaJenisContainingIgnoreCase(keyword);
    }

    // CREATE SINGLE (Auto Increment)
    public JenisBarang save(JenisBarang j) {
        return repository.save(j);
    }

    // CREATE BULK (Fitur Baru)
    public List<JenisBarang> saveBulk(List<JenisBarang> listJenis) {
        return repository.saveAll(listJenis);
    }

    // UPDATE
    public JenisBarang update(Byte id, JenisBarang updated) {
        JenisBarang existing = getById(id);
        existing.setNamaJenis(updated.getNamaJenis());
        return repository.save(existing);
    }

    // DELETE SINGLE
    public void delete(Byte id) {
        if (!repository.existsById(id)) throw new DataNotFoundException("JenisBarang", id.toString());
        repository.deleteById(id);
    }

    // DELETE BULK (Fitur Baru)
    public void deleteBulk(List<Byte> ids) {
        List<JenisBarang> existing = repository.findAllById(ids);
        if (existing.size() != ids.size()) {
            throw new DataNotFoundException("Sebagian JenisBarang", "bulk");
        }
        repository.deleteAllById(ids);
    }
}