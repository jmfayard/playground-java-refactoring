package movierental

@JvmRecord
data class Movie(
    val title: String,
    val priceCode: PriceCode,
) {
    fun isNewRelease(): Boolean =
        PriceCode.NEW_RELEASE == priceCode
}

