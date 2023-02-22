package com.bolsadeideas.springboot.datajpa.app.controllers;

import com.bolsadeideas.springboot.datajpa.app.models.entity.Cliente;
import com.bolsadeideas.springboot.datajpa.app.models.services.IClienteService;
import com.bolsadeideas.springboot.datajpa.app.models.services.IUploadFileService;
import com.bolsadeideas.springboot.datajpa.app.paginator.PageRender;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

  private static final String UPLOADS_FOLDER = "uploads";
  private final Logger logger = LoggerFactory.getLogger(ClienteController.class);
  @Autowired private IClienteService clienteService;
  @Autowired private IUploadFileService iUploadFileService;

  @GetMapping(value = "/uploads/{filename:.+}")
  public ResponseEntity<Resource> verFoto(@PathVariable String filename)
      throws MalformedURLException {

    Resource recurso = iUploadFileService.load(filename);

    return ResponseEntity.ok()
        .header(
            HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"" + recurso.getFilename() + "\"")
        .body(recurso);
  }

  @GetMapping(value = "/ver/{id}")
  public String ver(
      @PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

    Cliente cliente = clienteService.findOne(id);
    if (cliente == null) {
      flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
      return "redirect:/listar";
    }

    model.put("cliente", cliente);
    model.put("titulo", "Detalle cliente: " + cliente.getNombre());

    return "ver";
  }

  @RequestMapping(value = {"/listar", "/"}, method = RequestMethod.GET)
  public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {

    Pageable pageableRequest = PageRequest.of(page, 4);
    Page<Cliente> clientes = clienteService.findAll(pageableRequest);

    PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes);

    model.addAttribute("titulo", "Listado de clientes");
    // model.addAttribute("clientes", clienteService.findAll());
    model.addAttribute("clientes", clientes);
    model.addAttribute("page", pageRender);
    return "listar";
  }

  @GetMapping(value = "/form")
  public String crear(Map<String, Object> model) {
    Cliente cliente = new Cliente();
    model.put("titulo", "Formulario de Cliente");
    model.put("cliente", cliente);
    return "form";
  }

  @PostMapping(value = "/form")
  public String guardar(
      @Valid Cliente cliente,
      BindingResult result,
      Model model,
      @RequestParam("file") MultipartFile foto,
      RedirectAttributes flash,
      SessionStatus status)
      throws IOException {

    if (result.hasErrors()) {
      model.addAttribute("titulo", "Formulario de Cliente");
      return "form";
    }

    if (!foto.isEmpty()) {
      // linea de codigo utilizado para poner la ruta en una ruta externa del proyecto
      // String rootPath = "/Users/carlosjcasoc/Pictures/uploads/";
      if (cliente.getId() != null
          && cliente.getId() > 0
          && cliente.getPhoto() != null
          && cliente.getPhoto().length() > 0) {
        iUploadFileService.delete(cliente.getPhoto());
      }

      /*String uniqueFilename = UUID.randomUUID() + foto.getOriginalFilename();
      Path rootPath = Paths.get(UPLOADS_FOLDER).resolve(uniqueFilename);
      Path rootAbsolutePath = rootPath.toAbsolutePath();

      logger.info("rootPath: " + rootPath);
      logger.info("rootAbsolutePath: " + rootAbsolutePath);

      try {

        byte[] bytes = foto.getBytes();
        Path rutaCompleta = Paths.get(rootPath + "//" + foto.getOriginalFilename());
        Files.write(rutaCompleta, bytes);

        // Otra forma de gurdar la foto
        Files.copy(foto.getInputStream(), rootAbsolutePath);
        flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + " '");
        cliente.setPhoto(uniqueFilename);
      } catch (IOException e) {
        e.printStackTrace();
      }*/

      String uniqueFilename = iUploadFileService.copy(foto);
      cliente.setPhoto(uniqueFilename);
    }

    String mensajeFLash =
        (cliente.getId() != null ? "Cliente editado con exito" : "Cliente creado con exito");

    clienteService.save(cliente);
    status.setComplete();
    flash.addFlashAttribute("success", mensajeFLash);
    return "redirect:listar";
  }

  @GetMapping(value = "/form/{id}")
  public String editar(
      @PathVariable(name = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
    Cliente cliente = null;
    if (id > 0) {
      cliente = clienteService.findOne(id);
      if (cliente == null) {
        flash.addFlashAttribute("error", "El id del cliente no no existe en la BD");
      }
    } else {
      flash.addFlashAttribute("error", "El id del cliente no pude ser cero");
      return "redirect:/listar";
    }
    model.put("cliente", cliente);
    model.put("titulo", "Editar Cliente");
    return "form";
  }

  @RequestMapping(value = "/eliminar/{id}")
  public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
    if (id > 0) {
      Cliente cliente = clienteService.findOne(id);

      clienteService.delete(id);
      flash.addFlashAttribute("success", "Cliente eliminado con exito");

      if (iUploadFileService.delete(cliente.getPhoto())) {
        flash.addFlashAttribute("info", "Foto " + cliente.getPhoto() + " eliminada con exito!!!");
      }
    }
    return "redirect:/listar";
  }
}
