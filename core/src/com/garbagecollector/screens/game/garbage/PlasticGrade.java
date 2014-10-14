package com.garbagecollector.screens.game.garbage;

/**
 * Created by dmytroplekhotkin on 10/12/14.
 */
public enum PlasticGrade {
    PET("Polyethylene terephthalate"),
    HDPE("High-density polyethylene"),
    PVC("Polyvinyl chloride"),
    LDPE("Low-density polyethylene"),
    PP("Polypropylene"),
    PS("Polystyrene"),
    O("Other");


    private final String description;

    /**
     * @param description
     */
    private PlasticGrade(final String description) {
        this.description = description;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return description;
    }

}
