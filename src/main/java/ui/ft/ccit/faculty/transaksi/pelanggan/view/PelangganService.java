package ui.ft.ccit.faculty.transaksi.pelanggan.view;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ui.ft.ccit.faculty.transaksi.DataAlreadyExistsException;
import ui.ft.ccit.faculty.transaksi.DataNotFoundException;
import ui.ft.ccit.faculty.transaksi.pelanggan.model.Pelanggan;
import ui.ft.ccit.faculty.transaksi.pelanggan.model.PelangganRepository;

import java.util.List;

@Service
@Transactional
public class PelangganService {

    private final PelangganRepository repository;

    public PelangganService(PelangganRepository repository) {
        this.repository = repository;
    }

    // 1. GET ALL
    public List<Pelanggan> getAll() {
        return repository.findAll();
    }

    // 2. GET PAGINATION
    public List<Pelanggan> getAllWithPagination(int page, int size) {
        return repository.findAll(PageRequest.of(page, size))
        .getContent();
    }

    // 3. GET BY ID
    public Pelanggan getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Pelanggan", id));
    }

    // 4. SEARCH
    public List<Pelanggan> searchByNama(String keyword) {
        return repository.findByNamaContainingIgnoreCase(keyword);
    }

    // 5. CREATE
    public Pelanggan save(Pelanggan pelanggan) {
        if (pelanggan.getIdPelanggan() == null || pelanggan.getIdPelanggan().isBlank()) {
            throw new IllegalArgumentException("ID Pelanggan wajib diisi");
        }
        if (repository.existsById(pelanggan.getIdPelanggan())) {
            throw new DataAlreadyExistsException("Pelanggan", pelanggan.getIdPelanggan());
        }
        return repository.save(pelanggan);
    }

    // 5. CREATE BULK
    public List<Pelanggan> saveBulk(List<Pelanggan> pelanggans) {
        for (Pelanggan pelanggan : pelanggans) {
            if (pelanggan.getIdPelanggan() == null || pelanggan.getIdPelanggan().isBlank()) {
                throw new IllegalArgumentException("ID Pelanggan wajib diisi untuk setiap pelanggan");
            }
            if (repository.existsById(pelanggan.getIdPelanggan())) {
                throw new DataAlreadyExistsException("Pelanggan", pelanggan.getIdPelanggan());
            }
        }
        return repository.saveAll(pelanggans);
    }

    // 6. UPDATE
    public Pelanggan update(String id, Pelanggan updated) {
        Pelanggan existing = getById(id);
        
        // Update field satu per satu
        existing.setNama(updated.getNama());
        existing.setJenisKelamin(updated.getJenisKelamin());
        existing.setAlamat(updated.getAlamat());
        existing.setTelepon(updated.getTelepon());
        existing.setTglLahir(updated.getTglLahir());
        existing.setJenisPelanggan(updated.getJenisPelanggan());

        return repository.save(existing);
    }

    // 8. DELETE BULK
    public void deleteBulk(List<String> ids) {

        if (ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("List ID tidak boleh kosong");
        }

        if (ids.size() > 100) {
            throw new IllegalArgumentException("Maksimal 100 data per bulk delete");
        }

        long existingCount = repository.countByIdPelangganIn(ids);
        if (existingCount != ids.size()) {
            throw new IllegalArgumentException("Beberapa ID pelanggan tidak ditemukan");
        }

        repository.deleteAllById(ids);
    }
    
    public void delete(String id){
        if(!repository.existsById(id)){
            throw new DataNotFoundException("Pelanggan", id);
        }
        repository.deleteById(id);
    }
    }