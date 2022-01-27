package com.paycomet.androidRestSDK.old.Model.Basic;

public class PTPVProduct {
    /**
     * Description of the transaction
     */
    private String owner;

    /**
     * Description of the product
     */
    private String description;

    /**
     * Risk score value of the transaction. Between 0 and 100
     */
    private String scoring;

    /**
     * Creates a product. Used when executing a purchase.
     *
     * @param owner Description of the transaction. Optional.
     * @param description Description of the product. Optional.
     */
    public PTPVProduct(String owner, String description) {
        this.owner = owner;
        this.description = description;
    }

    /**
     * Creates a product. Used when executing a purchase.
     *
     * @param owner Description of the transaction. Optional.
     * @param description Description of the product. Optional.
     * @param scoring Risk score value of the transaction. Between 0 and 100. Optional.
     */
    public PTPVProduct(String owner, String description, String scoring) {
        this.owner = owner;
        this.description = description;
        this.scoring = scoring;
    }

    public String getDescription() {
        return description;
    }

    public String getOwner() {
        return owner;
    }

    public String getScoring() {
        return scoring;
    }
}
