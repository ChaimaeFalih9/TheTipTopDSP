package com.jeux.tiptop_app.controller;

import com.jeux.tiptop_app.entity.Gagne;
import com.jeux.tiptop_app.entity.GameScore;
import com.jeux.tiptop_app.entity.User;
import com.jeux.tiptop_app.service.GagneService;
import com.jeux.tiptop_app.service.GameService;
import com.jeux.tiptop_app.service.UserService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
public class ClientController {

    @Autowired
    private GagneService gagneService;
    @Autowired
    private UserService userService;

    @GetMapping("/recherche_client")
    public ModelAndView recherche_client(HttpServletRequest request,@RequestParam(value = "user") String user) {


        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        User userByName = userService.findByUsername(user);

        List<Gagne> clients = gagneService.getAllGagne();

        ModelAndView modelAndView = new ModelAndView("recherche_client");
        modelAndView.addObject("user", "connect");
        modelAndView.addObject("name", user);
        modelAndView.addObject("role", userByName.getRole());
        modelAndView.addObject("clients", clients);

        return modelAndView;
    }

    @GetMapping("/etatGagne")
    public String etatGagne(@RequestParam(value = "user") String user,@RequestParam(value = "client") Long client) {

        gagneService.etatGagne(client);

        return "redirect:/recherche_client?user="+user;

    }

    @GetMapping("/searchMail")
    public ModelAndView searchMail(@RequestParam(value = "user") String user,@RequestParam(value = "email") String email) {

        User userByName = userService.findByUsername(user);
        List<Gagne> clients = gagneService.getByEmail(email);

        ModelAndView modelAndView = new ModelAndView("recherche_client");
        modelAndView.addObject("user", "connect");
        modelAndView.addObject("name", user);
        modelAndView.addObject("role", userByName.getRole());
        modelAndView.addObject("clients", clients);

        return modelAndView;

    }

    @GetMapping("/export-excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        // Create a workbook and sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Clients Data");

        // Create header row
        Row headerRow = sheet.createRow(0);
        List<String> headers = Arrays.asList("ID", "Name", "Email");
        for (int i = 0; i < headers.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers.get(i));
        }

        // Sample data
        List<User> users =  userService.getAllUsers();

        // Populate data rows
        int rowNum = 1;
        for (User users1 : users) {
            Row dataRow = sheet.createRow(rowNum++);
            dataRow.createCell(0).setCellValue(users1.getId());
            dataRow.createCell(1).setCellValue(users1.getName());
            dataRow.createCell(2).setCellValue(users1.getEmail());
        }

        // Set the response headers
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=employees.xlsx");

        // Write the workbook content to the response stream
        workbook.write(response.getOutputStream());

        // Close the workbook
        workbook.close();
    }


}
