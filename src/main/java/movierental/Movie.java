package movierental;

public record Movie(String title, PriceCode priceCode) {

    public boolean isNewRelease() {
        return PriceCode.NEW_RELEASE == priceCode;
    }
}

