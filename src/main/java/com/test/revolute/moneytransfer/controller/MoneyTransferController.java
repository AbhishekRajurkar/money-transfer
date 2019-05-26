package com.test.revolute.moneytransfer.controller;

import com.test.revolute.moneytransfer.model.Transaction;
import com.test.revolute.moneytransfer.service.MoneyTransferService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/transfer")
@Produces(MediaType.APPLICATION_JSON)
public class MoneyTransferController {

    private MoneyTransferService moneyTransferService;

    public MoneyTransferController() {
        moneyTransferService = new MoneyTransferService();
    }

    @PUT
    public Response transferFund(Transaction transaction) {
        try {
            moneyTransferService.transferFunds(transaction);
            return Response.status(Response.Status.OK).build();
        } catch (Exception ex) {
            throw new WebApplicationException("Transaction failed with reason - "+ ex.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }

    }
}
