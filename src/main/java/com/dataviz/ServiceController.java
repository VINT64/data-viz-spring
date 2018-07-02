package com.dataviz;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

@Controller
public class ServiceController {
    private static final String quandlTemplate = "https://www.quandl.com/api/v3/datasets/%s/%s.json";
    private static final String apiKey = "DKczFdjuL_16KZVxeZKk";

    @RequestMapping(value="/services/dummyRequest", method = RequestMethod.GET, produces = "application/json")
     public @ResponseBody String success(
   /* public ModelAndView success( */
        @RequestParam(name="database_code", required=true) String databaseCode,
        @RequestParam(name="dataset_code", required=true) String datasetCode,
        @RequestParam(name="start_date", required=true) String startDate,
        @RequestParam(name="end_date", required=true) String endDate,
        @RequestParam(name="order", required=true) String order,
        @RequestParam(name="collapse", required=true) String collapse,
        @RequestParam(name="transformation", required=true) String transformation,
        @RequestParam(name="limit", defaultValue="0") String limit,
        @RequestParam(name="column_index", defaultValue ="-1") String columnIndex
        ){
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("api_key", apiKey);
        params.put("transformation", transformation);
        params.put("collapse", collapse);
        params.put("order", order);
        params.put("end_date", endDate);
        params.put("start_date", startDate);
        if(Integer.valueOf(limit)>0){
            params.put("limit", limit);
        }
        if(Integer.valueOf(columnIndex)>-1){
            params.put("column_index", columnIndex);
        }
        String url = String.format(quandlTemplate, databaseCode, datasetCode) ;
        String result = null;
        try {
            result = restTemplate.getForObject(url, String.class, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
        /*return new ModelAndView("test");*/
    }
}
