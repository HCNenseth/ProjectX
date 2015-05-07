package main.view.concrete.statistics;

import javafx.scene.control.Label;
import main.model.claim.Claim;
import main.view.StandardGridPane;

import java.util.List;

/**
 * Created by alex on 5/7/15.
 */
public class ClaimStatisticsView extends StandardGridPane
{
    private List<Claim> claims;

    public ClaimStatisticsView(List<Claim> claims)
    {
        super(1);
        this.claims = claims;

        add(new Label("Fill me up baby!"), 0, 0);
    }

    @Override
    public StandardGridPane getNode() { return this; }
}
