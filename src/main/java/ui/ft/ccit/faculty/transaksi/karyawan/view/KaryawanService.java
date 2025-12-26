package ui.ft.ccit.faculty.transaksi.karyawan.view;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ui.ft.ccit.faculty.transaksi.DataNotFoundException;
import ui.ft.ccit.faculty.transaksi.karyawan.model.Karyawan;
import ui.ft.ccit.faculty.transaksi.karyawan.model.KaryawanRepository;

@Service
@Transactional
public class KaryawanService {

    private final KaryawanRepository repository;

    public KaryawanService(KaryawanRepository repository) {
        this.repository = repository;
    }

    public Karyawan save(Karyawan k) { 
        return repository.save(k); 
    }
    
    public Karyawan getById(String id) {
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException("Karyawan", id));
    }
    
    public void delete(String id) {
        if (!repository.existsById(id)) throw new DataNotFoundException("Karyawan", id);
        repository.deleteById(id);
    }

    // METHOD SAKTI
    public Page<Karyawan> search(String keyword, int page, int size, String sortBy, String direction) {
        Sort.Direction dir = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(dir, sortBy));

        if (keyword == null || keyword.isBlank()) {
            return repository.findAll(pageable);
        } else {
            return repository.findByNamaContainingIgnoreCase(keyword, pageable);
        }
    }
}