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
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Duank
 */
@RestController
@CrossOrigin
public class tryController {

    Barang mydata = new Barang();
    BarangJpaController ctrl = new BarangJpaController();

    @RequestMapping(value = "/GET/{id}", method = RequestMethod.GET)
    public String getNameById(@PathVariable("id") int id) {
        try {
            mydata = ctrl.findBarang(id);
        } catch (Exception e) {
        }
        return mydata.getNamabarang();
    }

    @RequestMapping(value = "/GET")
    public List<Barang> getAll() {
        List<Barang> dummy = new ArrayList<>();
        try {
            dummy = ctrl.findBarangEntities();
        } catch (Exception e) {
            dummy = List.of();
        }
        return dummy;
    }

    @RequestMapping(value = "/POST", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
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

    @RequestMapping(value = "/PUT", method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
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

    @RequestMapping(value = "/DELETE", method = RequestMethod.DELETE, consumes = APPLICATION_JSON_VALUE)
    public String deleteData(HttpEntity<String> kiriman) {
        String message = "no action";
        try {
            String json_receive = kiriman.getBody();
            ObjectMapper mapper = new ObjectMapper();

            Barang newdatas = new Barang();
            String id = newdatas.getId().toString();

            newdatas = mapper.readValue(json_receive, Barang.class);
            ctrl.destroy(newdatas.getId());

            message = id + " has been Updated";
        } catch (Exception e) {
        }
        return message;
    }

}
