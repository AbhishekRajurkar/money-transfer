package com.test.revolute.moneytransfer.controller;

import com.test.revolute.moneytransfer.model.Transaction;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.URISyntaxException;
import static org.junit.Assert.*;


public class ITMoneyTransferControllerTest extends BaseIT{

    @Test
    public void testMoneyTransferFromOneAccountToAnother_success() throws URISyntaxException, IOException {
        URI uri = builder.setPath("/transfer").build();
        BigDecimal amount = new BigDecimal(10).setScale(4, RoundingMode.HALF_EVEN);
        Transaction transaction = new Transaction(amount,123l,124l);

        String jsonInString = mapper.writeValueAsString(transaction);
        StringEntity entity = new StringEntity(jsonInString);
        HttpPut request = new HttpPut(uri);
        request.setHeader("Content-type", "application/json");
        request.setEntity(entity);

        HttpResponse response = client.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();
        assertEquals(HttpStatus.SC_OK,statusCode);
    }


    @Test
    public void testMoneyTransferFromOneInvalidAccountToAnother_should_fail() throws URISyntaxException, IOException {
        URI uri = builder.setPath("/transfer").build();
        BigDecimal amount = new BigDecimal(10).setScale(4, RoundingMode.HALF_EVEN);
        Transaction transaction = new Transaction(amount,238l,124l);

        String jsonInString = mapper.writeValueAsString(transaction);
        StringEntity entity = new StringEntity(jsonInString);
        HttpPut request = new HttpPut(uri);
        request.setHeader("Content-type", "application/json");
        request.setEntity(entity);

        HttpResponse response = client.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();
        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR,statusCode);
    }

    @Test
    public void testMoneyTransferInsufficientFunds_should_fail() throws URISyntaxException, IOException {
        URI uri = builder.setPath("/transfer").build();
        BigDecimal amount = new BigDecimal(2000).setScale(4, RoundingMode.HALF_EVEN);
        Transaction transaction = new Transaction(amount,123l,124l);

        String jsonInString = mapper.writeValueAsString(transaction);
        StringEntity entity = new StringEntity(jsonInString);
        HttpPut request = new HttpPut(uri);
        request.setHeader("Content-type", "application/json");
        request.setEntity(entity);

        HttpResponse response = client.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();
        assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR,statusCode);
    }


}
