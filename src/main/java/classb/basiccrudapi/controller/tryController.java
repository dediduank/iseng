/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classb.basiccrudapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import model.entity.Barang;
import model.jpacontroller.BarangJpaController;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Duank
 */
@RestController
@CrossOrigin
@RequestMapping("/data")
public class tryController {

    Barang mydata = new Barang();
    BarangJpaController ctrl = new BarangJpaController();

    @GetMapping("/{id}")
    public List<Barang> getNameById(@PathVariable("id") int id) {
        List<Barang> dummy = new ArrayList<>(); // Declare new LIST
        try {
            mydata = ctrl.findBarang(id); // get data from db
            dummy.add(mydata); // fill data from db to list
        } catch (Exception e) {
            dummy = List.of(); // data not found
        }
        return dummy;
    }

    @GetMapping
    public List<Barang> getAll() {
        List<Barang> dummy = new ArrayList<>();
        try {
            dummy = ctrl.findBarangEntities();
        } catch (Exception e) {
            dummy = List.of();
        }
        return dummy;
    }

    @PostMapping()
    public String createData(HttpEntity<String> paket) {
        String message = "";

        try {
            String json_receive = paket.getBody();

            ObjectMapper map = new ObjectMapper();

            Barang data = new Barang();

            data = map.readValue(json_receive, Barang.class);

            ctrl.create(data);
            message = data.getNamabarang() + " Data Saved";

        } catch (Exception e) {
            message = "Failed";
        }

        return message;
    }

    @PutMapping()
    public String editData(HttpEntity<String> kiriman) {
        String message = "no action";
        try {
            String json_receive = kiriman.getBody();
            ObjectMapper mapper = new ObjectMapper();

            Barang newdatas = new Barang();

            newdatas = mapper.readValue(json_receive, Barang.class);
            ctrl.edit(newdatas);
            message = newdatas.getNamabarang() + " has been Updated";
        } catch (Exception e) {
        }
        return message;
    }

    @DeleteMapping()
    public String deleteData(HttpEntity<String> kiriman) {
        String message = "no action";
        try {
            String json_receive = kiriman.getBody();
            ObjectMapper mapper = new ObjectMapper();

            Barang newdatas = new Barang();

            newdatas = mapper.readValue(json_receive, Barang.class);
            ctrl.destroy(newdatas.getId());

            message = "Data has been Deleted";
        } catch (Exception e) {
        }
        return message;
    }

}
