package com.rome.quibok.Quibok.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewLombokFileRequest {

    private String filename;
    private Boolean isData;
    private Boolean isAllArgsConstructor;
    private Boolean isNoArgsConstructor;
    private JSONObject properties;

}
