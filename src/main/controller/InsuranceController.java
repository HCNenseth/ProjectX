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
 * InsuranceController.java
 */
public class InsuranceController
{
    private InsuranceController() {}
    private static Form f;

    public static void create(Person p, InsuranceType type)
    {
        f = new Form();
        InsuranceAdapter<? extends Insurance> insurance;
        String title;

        switch (type) {
            case CAR:
                title = Loc.c("new_car_insurance");
                insurance = new CarAdapter(p);
                break;
            case BOAT:
                title = Loc.c("new_boat_insurance");
                insurance = new BoatAdapter(p);
                break;
            case HOUSE:
                title = Loc.c("new_house_insurance");
                insurance = new HouseAdapter(p);
                break;
            case VACATION_HOUSE:
                title = Loc.c("new_vacation_house_insurance");
                insurance = new VacationHouseAdapter(p);
                break;
            case TRAVEL:
                title = Loc.c("new_travel_insurance");
                insurance = new TravelAdapter(p);
                break;
            default:
                throw new IllegalStateException("Unknown Insurance type");
        }

        insurance.setOnDoneAction(InsuranceController::view);
        f.injectAdapter(insurance);
        Resources.inst.getOtp().injectObservableTab(title,
                f.getForm(), f, true);
    }

    public static void view(Insurance insurance)
    {
        // Remove all tabs dealing with this object
        Resources.inst.getOtp().closeObservableTabs(insurance);
        Resources.inst.getOtp().closeObservableTabs(f);

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

        f = new Form();
        InsuranceAdapter<? extends Insurance> insuranceAdapter;

        switch (insurance.identify()) {
            case CAR:
                insuranceAdapter = new CarAdapter(insurance.getCustomer(),
                        (Car)insurance);
                break;
            case BOAT:
                insuranceAdapter = new BoatAdapter(insurance.getCustomer(),
                        (Boat)insurance);
                break;
            case HOUSE:
                insuranceAdapter = new HouseAdapter(insurance.getCustomer(),
                        (House)insurance);
                break;
            case VACATION_HOUSE:
                insuranceAdapter = new VacationHouseAdapter(insurance.getCustomer(),
                        (VacationHouse)insurance);
                break;
            case TRAVEL:
                insuranceAdapter = new TravelAdapter(insurance.getCustomer(),
                        (Travel)insurance);
                break;
            default:
                throw new IllegalStateException("Unknown Insurance type");
        }

        insuranceAdapter.setOnDoneAction(InsuranceController::view);
        f.injectAdapter(insuranceAdapter);

        Resources.inst.getOtp().injectObservableTab(insurance.identify().getValue(),
                f.getForm(), insurance, true);
    }
}
