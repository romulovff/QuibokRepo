package com.rome.quibok.Quibok;

import com.rome.quibok.Quibok.DTOs.NewLombokFileRequest;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class QuibokUtils {

    Boolean isData;
    Boolean isAllArgsConstructor;
    Boolean isNoArgsConstructor;
    FileWriter fw;

    QuibokUtils(Boolean isData, Boolean isAllArgsConstructor, Boolean isNoArgsConstructor, FileWriter fw) {
        this.isData = isData;
        this.isAllArgsConstructor = isAllArgsConstructor;
        this.isNoArgsConstructor = isNoArgsConstructor;
        this.fw = fw;
    }

    public void addImports(File file, NewLombokFileRequest body) throws IOException {
        if (this.isData)
            this.fw.write("import lombok.Data;\n");
        if (this.isAllArgsConstructor)
            this.fw.write("import lombok.AllArgsConstructor;\n");
        if (this.isNoArgsConstructor)
            this.fw.write("import lombok.NoArgsConstructor;\n");

        this.fw.write("\n\n");
    }

    public void createClassWithParameters(File file, NewLombokFileRequest body) throws IOException {
        if (this.isData)
            this.fw.write("@Data\n");
        if (this.isAllArgsConstructor)
            this.fw.write("@AllArgsConstructor\n");
        if (this.isNoArgsConstructor)
            this.fw.write("@NoArgsConstructor\n");
        this.fw.write("public class " + body.getFilename() + " {");
        this.fw.write("\n\n");
        this.createObjectAttributes(file, body);
        this.fw.write("\n");
        this.fw.write("}");
    }

    public void createObjectAttributes(File file, NewLombokFileRequest body) throws IOException {
        JSONObject dbJsonObj = new JSONObject(body.getProperties());
        HashMap<String, Object> map = new HashMap<>();

        Iterator<String> iter = dbJsonObj.keySet().iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            this.fw.write("\tprivate " + dbJsonObj.get(key) + " " + key + ";\n");
        }
    }

}
