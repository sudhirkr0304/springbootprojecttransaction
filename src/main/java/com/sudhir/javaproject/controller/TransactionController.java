package com.sudhir.javaproject.controller;

import com.sudhir.javaproject.model.Transaction;
import com.sudhir.javaproject.model.TransactionObject;
import com.sudhir.javaproject.model.TransactionService;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TransactionController {
    private final String encryptionPassword = "sudhirkumar";
    @Autowired
    TransactionService transactionService;

    @RequestMapping(value = "/transaction" ,method = RequestMethod.POST)
    public ResponseEntity<String> transactionRequest(@RequestBody TransactionObject transactionObject) {


        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(encryptionPassword);
        String myEncryptedText = textEncryptor.encrypt(transactionObject.toString());
        String uri = "http://localhost:8088/encryptedTransaction";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(myEncryptedText, headers);
        String response = restTemplate.postForObject(uri,entity,String.class);
        System.out.println(response);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(value = "encryptedTransaction" , method = RequestMethod.POST)
    public ResponseEntity<String> encryptedTransaction(@RequestBody String data) {
        System.out.println(data);
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(encryptionPassword);

        String[] decryptedData = textEncryptor.decrypt(data).split(" ");
        TransactionObject transactionObject = new TransactionObject(decryptedData[0],decryptedData[1],decryptedData[2],decryptedData[3] , decryptedData[4]);
        System.out.println(transactionObject);

        Transaction transaction = new Transaction(decryptedData[0],decryptedData[1],decryptedData[2],decryptedData[3] , decryptedData[4]);
        transactionService.saveTransaction(transaction);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
