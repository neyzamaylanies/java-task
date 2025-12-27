package ui.ft.ccit.faculty.transaksi.karyawan.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ui.ft.ccit.faculty.transaksi.karyawan.model.Karyawan;
import ui.ft.ccit.faculty.transaksi.karyawan.view.KaryawanService;

import java.util.List;

@RestController
@RequestMapping("/api/karyawan")
public class KaryawanController {

    private final KaryawanService service;

    public KaryawanController(KaryawanService service) {
        this.service = service;
    }

    @GetMapping
    public List<Karyawan> list(@RequestParam(required = false) Integer page,
                               @RequestParam(required = false) Integer size) {
        if (page == null && size == null) return service.getAll();
        int p = (page != null && page >= 0) ? page : 0;
        int s = (size != null && size > 0) ? size : 5;
        return service.getAllWithPagination(p, s);
    }

    @GetMapping("/{id}")
    public Karyawan get(@PathVariable String id) {
        return service.getById(id);
    }

    @GetMapping("/search")
    public List<Karyawan> search(@RequestParam String q) {
        return service.searchByNama(q);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Karyawan create(@RequestBody Karyawan k) {
        return service.save(k);
    }

    // POST BULK
    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Karyawan> createBulk(@RequestBody List<Karyawan> list) {
        return service.saveBulk(list);
    }

    @PutMapping("/{id}")
    public Karyawan update(@PathVariable String id, @RequestBody Karyawan k) {
        return service.update(id, k);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

    // DELETE BULK
    @DeleteMapping("/bulk")
    public void deleteBulk(@RequestBody List<String> ids) {
        service.deleteBulk(ids);
    }
}