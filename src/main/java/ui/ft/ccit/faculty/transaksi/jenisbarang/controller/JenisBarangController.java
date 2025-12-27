package ui.ft.ccit.faculty.transaksi.jenisbarang.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ui.ft.ccit.faculty.transaksi.jenisbarang.model.JenisBarang;
import ui.ft.ccit.faculty.transaksi.jenisbarang.view.JenisBarangService;

import java.util.List;

@RestController
@RequestMapping("/api/jenisbarang")
public class JenisBarangController {

    private final JenisBarangService service;

    public JenisBarangController(JenisBarangService service) { this.service = service; }

    @GetMapping
    public List<JenisBarang> list(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {
        if (page == null && size == null) return service.getAll();
        int p = (page != null && page >= 0) ? page : 0;
        int s = (size != null && size > 0) ? size : 5;
        return service.getAllWithPagination(p, s);
    }

    @GetMapping("/{id}")
    public JenisBarang get(@PathVariable Byte id) { return service.getById(id); }

    @GetMapping("/search")
    public List<JenisBarang> search(@RequestParam String q) { return service.searchByNama(q); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JenisBarang create(@RequestBody JenisBarang j) { return service.save(j); }

    // POST BULK
    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public List<JenisBarang> createBulk(@RequestBody List<JenisBarang> list) { return service.saveBulk(list); }

    @PutMapping("/{id}")
    public JenisBarang update(@PathVariable Byte id, @RequestBody JenisBarang j) { return service.update(id, j); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Byte id) { service.delete(id); }

    // DELETE BULK
    @DeleteMapping("/bulk")
    public void deleteBulk(@RequestBody List<Byte> ids) { service.deleteBulk(ids); }
}