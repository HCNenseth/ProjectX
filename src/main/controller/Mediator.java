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
                new Search(p);
                break;
            default:
        }

    }
}
