package main.controller;

import main.localization.Loc;
import main.model.insurance.Insurance;
import main.model.insurance.property.House;
import main.model.insurance.travel.Travel;
import main.model.insurance.vehicle.Boat;
import main.model.insurance.vehicle.Car;
import main.view.Resources;
import main.view.concrete.*;
import main.view.form.Form;
import main.view.form.adapter.BoatAdapter;
import main.view.form.adapter.CarAdapter;
import main.view.form.adapter.HouseAdapter;
import main.view.form.adapter.TravelAdapter;
//import main.view.form.adapter.TravelAdapter;

/**
 * Created by alex on 4/26/15.
 */
class InsuranceController
{
    private InsuranceController() {}

    public static void create()
    {
        // TODO implement create
    }

    public static void view(Insurance i)
    {
        InsuranceView view;

        switch (i.identify()) {
            case CAR:
                view = new CarView((Car)i);
                Resources.inst.getOtp().injectObservableTab(Loc.get("car_insurance"),
                        view.getNode(), i, true);
                return;
            case BOAT:
                view = new BoatView((Boat)i);
                Resources.inst.getOtp().injectObservableTab(Loc.get("boat_insurance"),
                        view.getNode(), i, true);
                return;
            case HOUSE:
                view = new HouseView((House)i);
                Resources.inst.getOtp().injectObservableTab(Loc.get("house_insurance"),
                        view.getNode(), i, true);
                return;
            case TRAVEL:
                view = new TravelView((Travel)i);
                Resources.inst.getOtp().injectObservableTab(Loc.get("travel_insurance"),
                        view.getNode(), i, true);
                return;
            default: return;
        }
    }

    public static void edit(Insurance i)
    {
        Form f = new Form();
        switch (i.identify()) {
            case CAR:
                CarAdapter carAdapter = new CarAdapter(i.getCustomer(), (Car)i);
                carAdapter.setOnDoneAction(InsuranceController::view);
                f.injectAdapter(carAdapter);
                break;
            case BOAT:
                BoatAdapter boatAdapter = new BoatAdapter(i.getCustomer(), (Boat)i);
                boatAdapter.setOnDoneAction(InsuranceController::view);
                f.injectAdapter(boatAdapter);
                break;
            case HOUSE:
                HouseAdapter houseAdapter = new HouseAdapter(i.getCustomer(), (House)i);
                houseAdapter.setOnDoneAction(InsuranceController::view);
                f.injectAdapter(houseAdapter);
                break;
            case TRAVEL:
                TravelAdapter travelAdapter = new TravelAdapter(i.getCustomer(), (Travel)i);
                travelAdapter.setOnDoneAction(InsuranceController::view);
                f.injectAdapter(travelAdapter);
                break;
            default: return;
        }

        Resources.inst.getOtp().injectObservableTab(i.identify().getValue(),
                f.getForm(), i, true);
    }

}
