package ui.ft.ccit.faculty.transaksi.pelanggan.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ui.ft.ccit.faculty.transaksi.pelanggan.model.Pelanggan;
import ui.ft.ccit.faculty.transaksi.pelanggan.view.PelangganService;

@RestController
@RequestMapping("/api/pelanggan")
public class PelangganController {

    private final PelangganService service;

    public PelangganController(PelangganService service) {
        this.service = service;
    }

    // Endpoint 1: Create
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pelanggan create(@RequestBody Pelanggan pelanggan) {
        return service.save(pelanggan);
    }

    // Endpoint 2: Get One Detail
    @GetMapping("/{id}")
    public Pelanggan getDetail(@PathVariable String id) {
        return service.getById(id);
    }

    // Endpoint 3: Delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

    // Endpoint 4: Get All / Search (GABUNGAN)
    // Tidak ada lagi '/search' atau '/all' terpisah. Semua masuk ke root '/api/pelanggan'
    @GetMapping
    public Page<Pelanggan> getAllOrSearch(
            @RequestParam(required = false) String q,             // Keyword (Opsional)
            @RequestParam(defaultValue = "0") int page,           // Halaman (Default 0)
            @RequestParam(defaultValue = "10") int size,          // Ukuran (Default 10)
            @RequestParam(defaultValue = "idPelanggan") String sort, // Sortir by kolom apa (Default ID)
            @RequestParam(defaultValue = "asc") String dir        // Arah (Default A-Z)
    ) {
        return service.search(q, page, size, sort, dir);
    }
}