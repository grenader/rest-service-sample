package com.grenader.restserver.fakerestserver;

import com.grenader.restserver.fakerestserver.model.ObjectRequest;
import com.grenader.restserver.fakerestserver.model.ObjectResponse;
import com.grenader.restserver.fakerestserver.model.TransactionRequest;
import com.grenader.restserver.fakerestserver.model.TransactionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ApiTransactionController {

    @Value("#{new Float('${api.application.version}')}")
    private float appVersion;

    @GetMapping(value = "/api/version", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getVersion() {
        return "{\"version\": "+appVersion+"}";
    }

    @PostMapping(value = "/api/transaction", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TransactionResponse createTransaction(@Valid @RequestBody TransactionRequest transRequest) {
        return new TransactionResponse(transRequest.getUserId());
    }

    @PostMapping(value = "/api/object", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ObjectResponse createObject(@Valid @RequestBody ObjectRequest objectRequest) {
        return new ObjectResponse(objectRequest);
    }
}
