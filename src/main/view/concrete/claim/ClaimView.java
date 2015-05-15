package main.view.concrete.claim;

import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import main.controller.ClaimController;
import main.controller.PersonController;
import main.localization.Loc;
import main.model.claim.Claim;
import main.view.StandardGridPane;
import main.view.stage.ImageStage;


/**
 * ClaimView.java
 */
public abstract class ClaimView<T extends Claim> extends StandardGridPane
{
    protected int rowNum = 0;
    protected T claim;
    protected boolean drawn = false;
    private int cellGap = 5;

    public ClaimView(T claim)
    {
        this.claim = claim;
        draw();
    }

    private void draw()
    {
        if (drawn)
        {
            getChildren();
            rowNum = 0;
        }

        initButtonPanel();
        initFields();

        drawn = true;
    }

    public void initButtonPanel()
    {
        AnchorPane buttonPane = new AnchorPane();

        // linking to the customer
        Button customerButton = new Button(Loc.c("view_customer"));
        customerButton.setOnAction(e -> PersonController.view(claim.getCustomer()));

        // edit person
        Button editButton = new Button(Loc.c("edit"));
        editButton.setOnAction(e -> ClaimController.edit(claim));

        // refresh the claim view
        Button refreshButton = new Button(Loc.c("refresh"));
        refreshButton.setOnAction(e -> draw());

        HBox rightAlignedButtons = new HBox();
        rightAlignedButtons.setSpacing(cellGap);
        rightAlignedButtons.getChildren().addAll(editButton, refreshButton);

        AnchorPane.setLeftAnchor(customerButton, 0d);
        AnchorPane.setRightAnchor(rightAlignedButtons, 0d);
        buttonPane.getChildren().addAll(customerButton, rightAlignedButtons);

        add(buttonPane, 0, rowNum++, 2, 1);
    }

    public void initFields()
    {
        // claimId
        add(new Label(Loc.c("claim_id")), 0 , rowNum);
        add(new Label(claim.getId() + ""), 1, rowNum++);

        // customer
        add(new Label(Loc.c("customer")), 0, rowNum);
        add(new Label(claim.getCustomer().getName()), 1, rowNum++);

        // date
        add(new Label(Loc.c("date_of_damages")), 0, rowNum);
        add(new Label(claim.getDate().toString()), 1, rowNum++);

        add(new Label(Loc.c("date_of_claim")), 0, rowNum);
        add(new Label(claim.getClaimDate().toString()), 1, rowNum++);

        add(new Separator(), 0, rowNum++, 2, 1);

        // insurance
        add(new Label(Loc.c("insurance")), 0, rowNum);
        add(new Label(claim.identify().getValue()), 1, rowNum++);

        if (!claim.getFilePathImage().equals("")) {
            add(new Label(Loc.c("image")), 0, rowNum);
            Hyperlink hyperlink = new Hyperlink(claim.getFilePathImage());
            hyperlink.setOnAction(e ->
                new ImageStage().showImage(claim.getImageFile())
            );
            add(hyperlink, 1, rowNum++);
        }

        // contacts
        add(new Label(Loc.c("contacts")), 0, rowNum);
        add(new Label(claim.getContacts()), 1, rowNum++);

        // amount
        add(new Label(Loc.c("amount")), 0, rowNum);
        add(new Label(claim.getAmount() + ""), 1, rowNum++);

        add(new Label(Loc.c("deductible")), 0, rowNum);
        add(new Label(claim.getDeductible() + ""), 1, rowNum++);

        // paymentStatus
        add(new Label(Loc.c("payment_status")), 0, rowNum);
        add(new Label(claim.getPaymentStatus().getValue()), 1, rowNum++);

        // status
        add(new Label(Loc.c("status")), 0, rowNum);
        add(new Label(claim.getStatus().getValue()), 1, rowNum++);

        add(new Separator(), 0, rowNum++, 2, 1);

        // desc
        add(new Label(Loc.c("description")), 0, rowNum);
        add(new Label(claim.getDesc()), 1, rowNum++);

        add(new Separator(), 0, rowNum++, 2, 1);

    }

    @Override
    public StandardGridPane getNode()
    {
        return this;
    }

}
