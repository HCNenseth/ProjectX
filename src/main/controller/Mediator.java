package main.controller;

/**
 * Created by alex on 4/23/15.
 */
public enum Mediator
{
    inst;

    Mediator() {}

    public void router(Signal s, Payload p)
    {
        switch (s) {
            case SEARCH:
                new SearchController(p);
                break;
            case FILE:
                new MenuBar(p);
                break;
            case ABOUT:
                AboutController.view();
                break;
            case REPORT:
                ReportController.view();
                break;
            case STATISTICS:
                StatisticsController.view(p);
                break;
            default:
                throw new IllegalStateException("Unknown signal type");
        }
    }
}
