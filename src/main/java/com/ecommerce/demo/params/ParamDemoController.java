package com.ecommerce.demo.params;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller to demonstrate the difference between @PathVariable, @RequestParam, and @RequestBody.
 */
@RestController
@RequestMapping("/params")
public class ParamDemoController {

    /**
     * 1. @PathVariable: Used to extract data from the URL path itself.
     * URL Example: GET http://localhost/params/path/123
     */
    @GetMapping("/path/{id}")
    public Map<String, Object> testPathVariable(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        response.put("type", "@PathVariable");
        response.put("receivedId", id);
        response.put("usage", "Used for identifying a specific resource (e.g., /users/1)");
        return response;
    }

    /**
     * 2. @RequestParam: Used to extract query parameters from the URL (after the '?').
     * URL Example: GET http://localhost/params/query?name=Junie&active=true
     */
    @GetMapping("/query")
    public Map<String, Object> testRequestParam(
            @RequestParam String name,
            @RequestParam(required = false, defaultValue = "false") boolean active) {
        
        Map<String, Object> response = new HashMap<>();
        response.put("type", "@RequestParam");
        response.put("receivedName", name);
        response.put("receivedActive", active);
        response.put("usage", "Used for filtering, sorting, or optional data (e.g., ?page=1&sort=desc)");
        return response;
    }

    /**
     * 3. @RequestBody: Used to extract the entire JSON object from the body of the request.
     * Usually used with POST, PUT, or PATCH.
     */
    @PostMapping("/body")
    public Map<String, Object> testRequestBody(@Valid @RequestBody UserDemoDTO user) {
        Map<String, Object> response = new HashMap<>();
        response.put("type", "@RequestBody");
        response.put("receivedUser", user);
        response.put("usage", "Used for creating or updating complex objects (JSON)");
        return response;
    }
}
