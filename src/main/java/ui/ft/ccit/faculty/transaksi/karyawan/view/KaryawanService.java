package ui.ft.ccit.faculty.transaksi.karyawan.view;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ui.ft.ccit.faculty.transaksi.DataAlreadyExistsException;
import ui.ft.ccit.faculty.transaksi.DataNotFoundException;
import ui.ft.ccit.faculty.transaksi.karyawan.model.Karyawan;
import ui.ft.ccit.faculty.transaksi.karyawan.model.KaryawanRepository;

import java.util.List;

@Service
@Transactional
public class KaryawanService {

    private final KaryawanRepository repository;

    public KaryawanService(KaryawanRepository repository) {
        this.repository = repository;
    }

    public List<Karyawan> getAll() {
        return repository.findAll();
    }

    public List<Karyawan> getAllWithPagination(int page, int size) {
        return repository.findAll(PageRequest.of(page, size)).getContent();
    }

    public Karyawan getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Karyawan", id));
    }

    public List<Karyawan> searchByNama(String keyword) {
        return repository.findByNamaContainingIgnoreCase(keyword);
    }

    // CREATE SINGLE
    public Karyawan save(Karyawan k) {
        if (k.getIdKaryawan() == null || k.getIdKaryawan().isBlank()) {
            throw new IllegalArgumentException("ID Karyawan wajib diisi");
        }
        if (repository.existsById(k.getIdKaryawan())) {
            throw new DataAlreadyExistsException("Karyawan", k.getIdKaryawan());
        }
        return repository.save(k);
    }

    // CREATE BULK (Fitur Baru)
    public List<Karyawan> saveBulk(List<Karyawan> listKaryawan) {
        for (Karyawan k : listKaryawan) {
            if (k.getIdKaryawan() == null || k.getIdKaryawan().isBlank()) {
                throw new IllegalArgumentException("ID Karyawan wajib diisi");
            }
            if (repository.existsById(k.getIdKaryawan())) {
                throw new DataAlreadyExistsException("Karyawan", k.getIdKaryawan());
            }
        }
        return repository.saveAll(listKaryawan);
    }

    // UPDATE
    public Karyawan update(String id, Karyawan updated) {
        Karyawan existing = getById(id);
        existing.setNama(updated.getNama());
        existing.setJenisKelamin(updated.getJenisKelamin());
        existing.setAlamat(updated.getAlamat());
        existing.setTelepon(updated.getTelepon());
        existing.setTglLahir(updated.getTglLahir());
        existing.setGaji(updated.getGaji());
        return repository.save(existing);
    }

    // DELETE SINGLE
    public void delete(String id) {
        if (!repository.existsById(id)) {
            throw new DataNotFoundException("Karyawan", id);
        }
        repository.deleteById(id);
    }

    // DELETE BULK (Fitur Baru)
    public void deleteBulk(List<String> ids) {
        List<Karyawan> existing = repository.findAllById(ids);
        if (existing.size() != ids.size()) {
            throw new DataNotFoundException("Sebagian Karyawan", "bulk");
        }
        repository.deleteAllById(ids);
    }
}