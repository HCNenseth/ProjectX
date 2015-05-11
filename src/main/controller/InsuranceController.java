package main.controller;

import main.localization.Loc;
import main.model.insurance.InsuranceType;
import main.model.insurance.Insurance;
import main.model.insurance.property.House;
import main.model.insurance.property.VacationHouse;
import main.model.insurance.travel.Travel;
import main.model.insurance.vehicle.Boat;
import main.model.insurance.vehicle.Car;
import main.model.person.Person;
import main.view.Resources;
import main.view.concrete.insurance.*;
import main.view.form.Form;
import main.view.form.adapter.insurance.*;

/**
 * Created by alex on 4/26/15.
 */
public class InsuranceController
{
    private InsuranceController() {}

    public static void create(Person p, InsuranceType type)
    {
        Form f = new Form();
        switch (type) {
            case CAR:
                CarAdapter carAdapter = new CarAdapter(p);
                carAdapter.setOnDoneAction(InsuranceController::view);
                f.injectAdapter(carAdapter);
                Resources.inst.getOtp().injectObservableTab(Loc.c("new_car_insurance"),
                        f.getForm(), true);
                return;
            case BOAT:
                BoatAdapter boatAdapter = new BoatAdapter(p);
                boatAdapter.setOnDoneAction(InsuranceController::view);
                f.injectAdapter(boatAdapter);
                Resources.inst.getOtp().injectObservableTab(Loc.c("new_boat_insurance"),
                        f.getForm(), true);
                return;
            case HOUSE:
                HouseAdapter houseAdapter = new HouseAdapter(p);
                houseAdapter.setOnDoneAction(InsuranceController::view);
                f.injectAdapter(houseAdapter);
                Resources.inst.getOtp().injectObservableTab(Loc.c("new_house_insurance"),
                        f.getForm(), true);
                return;
            case VACATION_HOUSE:
                VacationHouseAdapter vacationHouseAdapter = new VacationHouseAdapter(p);
                vacationHouseAdapter.setOnDoneAction(InsuranceController::view);
                f.injectAdapter(vacationHouseAdapter);
                Resources.inst.getOtp().injectObservableTab(Loc.c("new_vacation_house_insurance"),
                        f.getForm(), true);
                return;
            case TRAVEL:
                TravelAdapter travelAdapter = new TravelAdapter(p);
                travelAdapter.setOnDoneAction(InsuranceController::view);
                f.injectAdapter(travelAdapter);
                Resources.inst.getOtp().injectObservableTab(Loc.c("new_travel_insurance"),
                        f.getForm(), true);
                return;
            default:
                throw new IllegalStateException("Unknown Insurance type");
        }
    }

    public static void view(Insurance insurance)
    {
        // Remove all tabs dealing with this object
        Resources.inst.getOtp().closeObservableTabs(insurance);

        InsuranceView view;

        switch (insurance.identify()) {
            case CAR:
                view = new CarView((Car)insurance);
                Resources.inst.getOtp().injectObservableTab(Loc.c("car_insurance"),
                        view.getNode(), insurance, true);
                return;
            case BOAT:
                view = new BoatView((Boat)insurance);
                Resources.inst.getOtp().injectObservableTab(Loc.c("boat_insurance"),
                        view.getNode(), insurance, true);
                return;
            case HOUSE:
                view = new HouseView((House)insurance);
                Resources.inst.getOtp().injectObservableTab(Loc.c("house_insurance"),
                        view.getNode(), insurance, true);
                return;
            case VACATION_HOUSE:
                view = new VacationHouseView((VacationHouse)insurance);
                Resources.inst.getOtp().injectObservableTab(Loc.c("vacation_house_insurance"),
                        view.getNode(), insurance, true);
                return;
            case TRAVEL:
                view = new TravelView((Travel)insurance);
                Resources.inst.getOtp().injectObservableTab(Loc.c("travel_insurance"),
                        view.getNode(), insurance, true);
                return;
            default:
                throw new IllegalStateException("Unknown Insurance type");
        }
    }

    public static void edit(Insurance insurance)
    {
        // Remove all tabs dealing with this object
        Resources.inst.getOtp().closeObservableTabs(insurance);

        Form f = new Form();
        switch (insurance.identify()) {
            case CAR:
                CarAdapter carAdapter = new CarAdapter(insurance.getCustomer(),
                        (Car)insurance);
                carAdapter.setOnDoneAction(InsuranceController::view);
                f.injectAdapter(carAdapter);
                break;
            case BOAT:
                BoatAdapter boatAdapter = new BoatAdapter(insurance.getCustomer(),
                        (Boat)insurance);
                boatAdapter.setOnDoneAction(InsuranceController::view);
                f.injectAdapter(boatAdapter);
                break;
            case HOUSE:
                HouseAdapter houseAdapter = new HouseAdapter(insurance.getCustomer(),
                        (House)insurance);
                houseAdapter.setOnDoneAction(InsuranceController::view);
                f.injectAdapter(houseAdapter);
                break;
            case VACATION_HOUSE:
                VacationHouseAdapter vacationHouseAdapter = new VacationHouseAdapter(
                        insurance.getCustomer(), (VacationHouse)insurance);
                vacationHouseAdapter.setOnDoneAction(InsuranceController::view);
                f.injectAdapter(vacationHouseAdapter);
                break;
            case TRAVEL:
                TravelAdapter travelAdapter = new TravelAdapter(insurance.getCustomer(),
                        (Travel)insurance);
                travelAdapter.setOnDoneAction(InsuranceController::view);
                f.injectAdapter(travelAdapter);
                break;
            default:
                throw new IllegalStateException("Unknown Insurance type");
        }

        Resources.inst.getOtp().injectObservableTab(insurance.identify().getValue(),
                f.getForm(), insurance, true);
    }
}
