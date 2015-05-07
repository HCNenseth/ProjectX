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
                Resources.inst.getOtp().injectObservableTab(Loc.c("new_vacationhouse_insuarance"),
                        f.getForm(), true);
                return;
            case TRAVEL:
                TravelAdapter travelAdapter = new TravelAdapter(p);
                travelAdapter.setOnDoneAction(InsuranceController::view);
                f.injectAdapter(travelAdapter);
                Resources.inst.getOtp().injectObservableTab(Loc.c("new_travel_insurance"),
                        f.getForm(), true);
                return;
            default: return;
        }
    }

    public static void view(Insurance i)
    {
        InsuranceView view;

        switch (i.identify()) {
            case CAR:
                view = new CarView((Car)i);
                Resources.inst.getOtp().injectObservableTab(Loc.c("car_insurance"),
                        view.getNode(), i, true);
                return;
            case BOAT:
                view = new BoatView((Boat)i);
                Resources.inst.getOtp().injectObservableTab(Loc.c("boat_insurance"),
                        view.getNode(), i, true);
                return;
            case HOUSE:
                view = new HouseView((House)i);
                Resources.inst.getOtp().injectObservableTab(Loc.c("house_insurance"),
                        view.getNode(), i, true);
                return;
            case VACATION_HOUSE:
                view = new VacationHouseView((VacationHouse)i);
                Resources.inst.getOtp().injectObservableTab(Loc.c("vacationHouse_insurance"),
                        view.getNode(), i, true);
                return;
            case TRAVEL:
                view = new TravelView((Travel)i);
                Resources.inst.getOtp().injectObservableTab(Loc.c("travel_insurance"),
                        view.getNode(), i, true);
                return;
            default: return;
        }
    }

    public static void edit(Insurance i)
    {
        Resources.inst.getOtp().closeObservableTabs(i);

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
            case VACATION_HOUSE:
                VacationHouseAdapter vacationHouseAdapter = new VacationHouseAdapter(i.getCustomer(), (VacationHouse)i);
                vacationHouseAdapter.setOnDoneAction(InsuranceController::view);
                f.injectAdapter(vacationHouseAdapter);
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
