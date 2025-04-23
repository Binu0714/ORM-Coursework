package com.assignment.orm.service.orm_final_course_work_health_care.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class InvoiceController {

    @FXML
    private Label lblInvoiceDate;

    @FXML
    private Label lblPatientId;

    @FXML
    private Label lblPatientName;

    @FXML
    private Label lblPaymentDate;

    @FXML
    private Label lblPaymentFee;

    @FXML
    private Label lblProgramName;

    @FXML
    private Label lblSessionDate;

    @FXML
    private Label lblSessionId;

    @FXML
    private Label lblStatus;


    private PaymentController paymentController;

    public void setInvoiceController(PaymentController paymentController) {
        this.paymentController = paymentController;

        lblPaymentFee.setText(String.valueOf(paymentController.getInvoiceDto().getFee()));
        lblInvoiceDate.setText(String.valueOf(paymentController.getInvoiceDto().getInvoiceDate()));
        lblPatientId.setText(String.valueOf(paymentController.getInvoiceDto().getPatientId()));
        lblPatientName.setText(String.valueOf(paymentController.getInvoiceDto().getPatientName()));
        lblPaymentDate.setText(String.valueOf(paymentController.getInvoiceDto().getInvoiceDate()));
        lblPaymentFee.setText(String.valueOf(paymentController.getInvoiceDto().getFee()));
        lblProgramName.setText(String.valueOf(paymentController.getInvoiceDto().getProgramName()));
        lblSessionDate.setText(String.valueOf(paymentController.getInvoiceDto().getSessionDate()));
        lblSessionId.setText(String.valueOf(paymentController.getInvoiceDto().getSessionId()));
        lblStatus.setText(String.valueOf(paymentController.getInvoiceDto().getStatus()));
    }




}
