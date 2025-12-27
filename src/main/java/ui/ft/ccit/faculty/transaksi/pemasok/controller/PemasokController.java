package ui.ft.ccit.faculty.transaksi.pemasok.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ui.ft.ccit.faculty.transaksi.pemasok.model.Pemasok;
import ui.ft.ccit.faculty.transaksi.pemasok.view.PemasokService;

import java.util.List;

@RestController
@RequestMapping("/api/pemasok")
public class PemasokController {

    private final PemasokService service;

    public PemasokController(PemasokService service) { this.service = service; }

    @GetMapping
    public List<Pemasok> list(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {
        if (page == null && size == null) return service.getAll();
        int p = (page != null && page >= 0) ? page : 0;
        int s = (size != null && size > 0) ? size : 5;
        return service.getAllWithPagination(p, s);
    }

    @GetMapping("/{id}")
    public Pemasok get(@PathVariable String id) { 
        return service.getById(id); 
    }

    @GetMapping("/search")
    public List<Pemasok> search(@RequestParam String q) { 
        return service.searchByNama(q); 
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pemasok create(@RequestBody Pemasok p) { 
        return service.save(p); 
    }

    // POST BULK
    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Pemasok> createBulk(@RequestBody List<Pemasok> list) { 
        return service.saveBulk(list); 
    }

    @PutMapping("/{id}")
    public Pemasok update(@PathVariable String id, @RequestBody Pemasok p) { 
        return service.update(id, p); 
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