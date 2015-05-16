package main.controller;

/**
 * Mediator.java
 */
public enum Mediator
{
    /**
     * The inst.
     */
    inst;

    /**
     * Instantiates a new Mediator.
     */
    Mediator()
    {
    }

    /**
     * Router void.
     *
     * @param s the s
     * @param p the p
     */
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
                ReportController.view(p);
                break;
            case STATISTICS:
                StatisticsController.view(p);
                break;
            default:
                throw new IllegalStateException("Unknown signal type");
        }
    }
}
