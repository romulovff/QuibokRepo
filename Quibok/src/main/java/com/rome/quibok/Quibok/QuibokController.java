package com.rome.quibok.Quibok;

import com.rome.quibok.Quibok.DTOs.NewLombokFileRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;

@RestController
public class QuibokController {

    @PostMapping(value = "/newlombokfile", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody void postProvisionings(HttpServletResponse response, @RequestBody NewLombokFileRequest body) throws IOException {
        File directory = new File("results");
        File file = new File(directory + "/" + body.getFilename() + ".java");
        FileWriter fw = new FileWriter(file);

        directory.mkdir();
        file.createNewFile();

        Boolean isData = body.getIsData() != null ? body.getIsData() : false;
        Boolean isAllArgsConstructor = body.getIsAllArgsConstructor() != null ? body.getIsAllArgsConstructor() : false;
        Boolean isNoArgsConstructor = body.getIsNoArgsConstructor() != null ? body.getIsNoArgsConstructor() : false;

        QuibokUtils quibokUtils = new QuibokUtils(isData, isAllArgsConstructor, isNoArgsConstructor, fw);

        quibokUtils.addImports(file, body);
        quibokUtils.createClassWithParameters(file, body);

        fw.close();

        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + file.getName();

        response.setHeader(headerKey, headerValue);

        ServletOutputStream outputStream = response.getOutputStream();

        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));

        byte[] buffer = new byte[8192];
        int bytesRead = -1;

        while((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
    }

}
